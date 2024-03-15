package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.SubmissionRequest;
import com.hacktues.api.entity.Assignment;
import com.hacktues.api.entity.Student;
import com.hacktues.api.entity.Submission;
import com.hacktues.api.entity.User;
import com.hacktues.api.mapper.SubmissionMapper;
import com.hacktues.api.repository.AssignmentRepository;
import com.hacktues.api.repository.StudentRepository;
import com.hacktues.api.repository.SubmissionRepository;
import com.hacktues.api.repository.UserRepository;
import com.hacktues.api.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final SubmissionMapper submissionMapper;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    @Override
    public void submit(Long assignmentId, SubmissionRequest submissionRequest) {
        //TODO: Get file path from Azure and save it in the submission
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new RuntimeException("Assignment not found"));
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(currentUserEmail).orElseThrow();
        Submission submission = submissionMapper.toSubmission(submissionRequest);
        Student student = studentRepository.findStudentByUserId(user.getId());

        submission.setAssignment(assignment);
        submission.setStudent(student);

        submissionRepository.save(submission);
    }
}
