package com.lms.backend.repository;

import com.lms.backend.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    List<Content> findByCourseCourseId(Integer courseId);
}