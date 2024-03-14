package com.hacktues.api.repository;

import com.hacktues.api.entity.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
    Optional<StudentClass> findByName(String name);
}
