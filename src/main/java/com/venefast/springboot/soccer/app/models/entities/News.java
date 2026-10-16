package com.venefast.springboot.soccer.app.models.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "noticias")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_noticia")
    private Long id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String title;

    @Lob
    @Column(name = "contenido", nullable = false, columnDefinition = "CLOB")
    private String content;

    @Column(name = "imagen_url", length = 300)
    private String imageUrl;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime publicationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_autor", nullable = false)
    private User author;

    public News() {
    }

    public News(Long id, String title, String content, String imageUrl, LocalDateTime publicationDate, User author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.publicationDate = publicationDate;
        this.author = author;
    }

    public News(String title, String content, String imageUrl, LocalDateTime publicationDate, User author) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.publicationDate = publicationDate;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
