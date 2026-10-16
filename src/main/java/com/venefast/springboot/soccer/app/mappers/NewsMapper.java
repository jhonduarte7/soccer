package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.NewsDto;
import com.venefast.springboot.soccer.app.models.entities.News;
import com.venefast.springboot.soccer.app.models.entities.User;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public NewsDto toDto(News entity) {
        if (entity == null) {
            return null;
        }
        Long authorId = entity.getAuthor() != null ? entity.getAuthor().getId() : null;
        String authorUsername = entity.getAuthor() != null ? entity.getAuthor().getUsername() : null;

        return new NewsDto(
            entity.getId(),
            entity.getTitle(),
            entity.getContent(),
            entity.getImageUrl(),
            entity.getPublicationDate(),
            authorId,
            authorUsername
        );
    }

    public News toEntity(NewsDto dto, User author) {
        if (dto == null) {
            return null;
        }
        News news = new News();
        news.setId(dto.id());
        news.setTitle(dto.title());
        news.setContent(dto.content());
        news.setImageUrl(dto.imageUrl());
        news.setPublicationDate(dto.publicationDate());
        news.setAuthor(author);
        return news;
    }
}
