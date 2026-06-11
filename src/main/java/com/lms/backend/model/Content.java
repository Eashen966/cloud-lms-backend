package com.lms.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Integer contentId;

    @Column(nullable = false, length = 150)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    private ContentType contentType;

    @Column(name = "url_link", nullable = false, columnDefinition = "TEXT")
    private String urlLink;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public enum ContentType {
        LECTURE_NOTE, VIDEO_LINK, SYLLABUS
    }

    // Getters and Setters
    public Integer getContentId() { return contentId; }
    public void setContentId(Integer contentId) { this.contentId = contentId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public ContentType getContentType() { return contentType; }
    public void setContentType(ContentType contentType) { this.contentType = contentType; }

    public String getUrlLink() { return urlLink; }
    public void setUrlLink(String urlLink) { this.urlLink = urlLink; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}