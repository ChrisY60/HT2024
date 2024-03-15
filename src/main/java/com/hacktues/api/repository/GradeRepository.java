package com.hacktues.api.repository;

import com.hacktues.api.entity.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository {
    List<Grade> findByStudentId(Long studentId);
}
