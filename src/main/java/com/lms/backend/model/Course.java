package com.lms.backend.model;

import jakarta.persistence.*;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "course_code", nullable = false, unique = true, length = 20)
    private String courseCode;

    @Column(name = "course_name", nullable = false, length = 150)
    private String courseName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String syllabus;

    @Column(name = "enrollment_capacity", nullable = false)
    private Integer enrollmentCapacity;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;

    @ManyToMany
    @JoinTable(
        name = "course_enrollments",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonIgnoreProperties("passwordHash") // Security safeguard
    private Set<User> enrolledStudents;

    // Getters and Setters
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSyllabus() { return syllabus; }
    public void setSyllabus(String syllabus) { this.syllabus = syllabus; }

    public Integer getEnrollmentCapacity() { return enrollmentCapacity; }
    public void setEnrollmentCapacity(Integer enrollmentCapacity) { this.enrollmentCapacity = enrollmentCapacity; }

    public User getInstructor() { return instructor; }
    public void setInstructor(User instructor) { this.instructor = instructor; }

    public Set<User> getEnrolledStudents() { return enrolledStudents; }
    public void setEnrolledStudents(Set<User> enrolledStudents) { this.enrolledStudents = enrolledStudents; }
}