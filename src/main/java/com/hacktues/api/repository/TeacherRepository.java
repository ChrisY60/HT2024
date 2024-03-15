package com.hacktues.api.repository;

import com.hacktues.api.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findTeacherByUserId(Long id);
}