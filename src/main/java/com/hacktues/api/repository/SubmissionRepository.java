package com.hacktues.api.repository;

import com.hacktues.api.entity.Assignment;
import com.hacktues.api.entity.Student;
import com.hacktues.api.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Optional<Submission> findSubmissionByAssignmentAndStudent(Assignment assignment, Student student);
}
