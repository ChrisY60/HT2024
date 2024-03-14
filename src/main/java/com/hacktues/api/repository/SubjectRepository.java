package com.hacktues.api.repository;

import com.hacktues.api.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT s FROM subjects JOIN s.students st WHERE st.id = :studentId")
    List<Subject> findAllByStudentId(Long studentId);
}
