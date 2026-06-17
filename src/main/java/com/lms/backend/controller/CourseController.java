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

    @PutMapping("/{courseId}")
    public ResponseEntity<?> update(@PathVariable Integer courseId, @RequestBody Course course) {
        try {
            return ResponseEntity.ok(courseService.updateCourse(courseId, course));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> delete(@PathVariable Integer courseId) {
        try {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok(Map.of("message", "Course deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/enrolled/{studentId}")
    public ResponseEntity<?> getEnrolled(@PathVariable Integer studentId) {
        try {
            return ResponseEntity.ok(courseService.getEnrolledCourses(studentId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
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