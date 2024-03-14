package com.hacktues.api.repository;

import com.hacktues.api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByUserId(Long id);
}
