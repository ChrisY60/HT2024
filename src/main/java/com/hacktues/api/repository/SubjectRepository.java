package com.hacktues.api.repository;

import com.hacktues.api.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT s FROM Subject s JOIN s.studentClass sc WHERE sc.id = :studentClassId")
    List<Subject> findSubjectsByStudentClassId(Long studentClassId);

    List<Subject> findSubjectsByTeacherId(Long id);
}
