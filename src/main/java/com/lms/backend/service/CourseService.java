package com.lms.backend.service;

import com.lms.backend.model.Course;
import com.lms.backend.model.User;
import com.lms.backend.repository.CourseRepository;
import com.lms.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Business Logic: Enrollment Processing & Capacity Constraint Verification
    @Transactional
    public Course enrollStudent(Integer courseId, Integer studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student user context not found"));

        if (student.getRole() != User.Role.STUDENT) {
            throw new RuntimeException("Only Student accounts can enroll in courses!");
        }
        if (course.getEnrolledStudents().size() >= course.getEnrollmentCapacity()) {
            throw new RuntimeException("Enrollment processing rejected: Course is at peak capacity!");
        }

        course.getEnrolledStudents().add(student);
        return courseRepository.save(course); // Saves to cross-reference table automatically
    }
}