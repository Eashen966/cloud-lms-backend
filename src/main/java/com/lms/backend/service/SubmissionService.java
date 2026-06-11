package com.lms.backend.service;

import com.lms.backend.model.Assignment;
import com.lms.backend.model.Submission;
import com.lms.backend.repository.AssignmentRepository;
import com.lms.backend.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;

    public SubmissionService(SubmissionRepository submissionRepository, AssignmentRepository assignmentRepository) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
    }

    // Business Logic: File Submission & Deadline Status Tracking Calculation
    public Submission submitAssignment(Submission submission) {
        Assignment assignment = assignmentRepository.findById(submission.getAssignment().getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment contextual link missing"));

        // Automated State Verification Engine
        if (LocalDateTime.now().isAfter(assignment.getDueDate())) {
            submission.setStatus(Submission.Status.LATE);
        } else {
            submission.setStatus(Submission.Status.SUBMITTED);
        }

        submission.setSubmissionDate(LocalDateTime.now());
        return submissionRepository.save(submission);
    }

    // Business Logic: Grading and Feedback Management
    public Submission gradeSubmission(Integer submissionId, Integer grade, String feedback) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        submission.setGrade(grade);
        submission.setFeedback(feedback);
        return submissionRepository.save(submission);
    }

    public List<Submission> getSubmissionsForAssignment(Integer assignmentId) {
        return submissionRepository.findByAssignmentAssignmentId(assignmentId);
    }

    public List<Submission> getStudentSubmissions(Integer studentId) {
        return submissionRepository.findByStudentUserId(studentId);
    }
}