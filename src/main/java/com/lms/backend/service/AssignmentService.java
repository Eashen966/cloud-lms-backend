package com.lms.backend.service;

import com.lms.backend.model.Assignment;
import com.lms.backend.repository.AssignmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsByCourse(Integer courseId) {
        return assignmentRepository.findByCourseCourseId(courseId);
    }
}