package com.venefast.springboot.soccer.app.services;

import com.venefast.springboot.soccer.app.exceptions.ResourceNotFoundException;
import com.venefast.springboot.soccer.app.mappers.NewsMapper;
import com.venefast.springboot.soccer.app.models.dtos.NewsDto;
import com.venefast.springboot.soccer.app.models.entities.News;
import com.venefast.springboot.soccer.app.models.entities.User;
import com.venefast.springboot.soccer.app.repositories.NewsRepository;
import com.venefast.springboot.soccer.app.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final NewsMapper newsMapper;

    public NewsService(NewsRepository newsRepository, UserRepository userRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.newsMapper = newsMapper;
    }

    @Transactional(readOnly = true)
    public List<NewsDto> findAll() {
        return newsRepository.findAllByOrderByPublicationDateDesc().stream()
                .map(newsMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public NewsDto findById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News article not found with id: " + id));
        return newsMapper.toDto(news);
    }

    @Transactional(readOnly = true)
    public List<NewsDto> findByAuthorId(Long authorId) {
        return newsRepository.findByAuthorId(authorId).stream()
                .map(newsMapper::toDto)
                .toList();
    }

    public NewsDto create(NewsDto dto) {
        User author = userRepository.findById(dto.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + dto.authorId()));

        News news = newsMapper.toEntity(dto, author);
        news.setId(null);
        if (news.getPublicationDate() == null) {
            news.setPublicationDate(LocalDateTime.now());
        }
        News saved = newsRepository.save(news);
        return newsMapper.toDto(saved);
    }

    public NewsDto update(Long id, NewsDto dto) {
        News existing = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News article not found with id: " + id));
        User author = userRepository.findById(dto.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + dto.authorId()));

        existing.setTitle(dto.title());
        existing.setContent(dto.content());
        existing.setImageUrl(dto.imageUrl());
        if (dto.publicationDate() != null) {
            existing.setPublicationDate(dto.publicationDate());
        }
        existing.setAuthor(author);

        News updated = newsRepository.save(existing);
        return newsMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new ResourceNotFoundException("News article not found with id: " + id);
        }
        newsRepository.deleteById(id);
    }
}
