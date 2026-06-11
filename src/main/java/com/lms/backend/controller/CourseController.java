package com.lms.backend.controller;

import com.lms.backend.model.Course;
import com.lms.backend.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<?> enroll(@PathVariable Integer courseId, @RequestBody Map<String, Integer> payload) {
        try {
            Integer studentId = payload.get("studentId");
            return ResponseEntity.ok(courseService.enrollStudent(courseId, studentId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}