package com.lms.backend.controller;

import com.lms.backend.model.Submission;
import com.lms.backend.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<Submission> submit(@RequestBody Submission submission) {
        return ResponseEntity.ok(submissionService.submitAssignment(submission));
    }

    @PutMapping("/{submissionId}/grade")
    public ResponseEntity<?> grade(@PathVariable Integer submissionId, @RequestBody Map<String, Object> payload) {
        Integer grade = (Integer) payload.get("grade");
        String feedback = (String) payload.get("feedback");
        return ResponseEntity.ok(submissionService.gradeSubmission(submissionId, grade, feedback));
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<?> getByAssignment(@PathVariable Integer assignmentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsForAssignment(assignmentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getByStudent(@PathVariable Integer studentId) {
        return ResponseEntity.ok(submissionService.getStudentSubmissions(studentId));
    }
}