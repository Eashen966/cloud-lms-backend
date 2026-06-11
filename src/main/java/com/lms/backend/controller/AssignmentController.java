package com.lms.backend.controller;

import com.lms.backend.model.Assignment;
import com.lms.backend.service.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<Assignment> create(@RequestBody Assignment assignment) {
        return ResponseEntity.ok(assignmentService.createAssignment(assignment));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getByCourse(@PathVariable Integer courseId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByCourse(courseId));
    }
}