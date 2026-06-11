package com.lms.backend.controller;

import com.lms.backend.model.Content;
import com.lms.backend.service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping
    public ResponseEntity<Content> upload(@RequestBody Content content) {
        return ResponseEntity.ok(contentService.uploadContent(content));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getByCourse(@PathVariable Integer courseId) {
        return ResponseEntity.ok(contentService.getContentByCourse(courseId));
    }
}