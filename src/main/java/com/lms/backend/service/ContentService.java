package com.lms.backend.service;

import com.lms.backend.model.Content;
import com.lms.backend.repository.ContentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content uploadContent(Content content) {
        return contentRepository.save(content);
    }

    public List<Content> getContentByCourse(Integer courseId) {
        return contentRepository.findByCourseCourseId(courseId);
    }
}