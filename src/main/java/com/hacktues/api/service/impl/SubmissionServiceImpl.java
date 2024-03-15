package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.SubmissionRequest;
import com.hacktues.api.entity.*;
import com.hacktues.api.mapper.SubmissionMapper;
import com.hacktues.api.repository.AssignmentRepository;
import com.hacktues.api.repository.StudentRepository;
import com.hacktues.api.repository.SubmissionRepository;
import com.hacktues.api.service.AuthenticationService;
import com.hacktues.api.service.StorageService;
import com.hacktues.api.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final SubmissionMapper submissionMapper;
    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;
    private final StorageService storageService;
    private final AuthenticationService authenticationService;

    @Override
    public void submit(Long assignmentId, SubmissionRequest submissionRequest) {
        Assignment assignment = assignmentRepository
                .findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found!"));
        User user = authenticationService.getCurrentUser();
        Submission submission = submissionMapper.toSubmission(submissionRequest);
        Student student = studentRepository.findStudentByUserId(user.getId());

        submission.setAssignment(assignment);
        submission.setStudent(student);
        List<FilePath> filePaths = submissionRequest.getFiles().stream()
                .map(file -> {
                    FilePath filePath = new FilePath();
                    filePath.setPath(storageService.uploadFile(
                            file,
                            user.getSchool() + "-" + user.getClass() + "-" + UUID.randomUUID()
                        )
                    );
                    return filePath;
                })
                .toList();
        submission.setFilePaths(filePaths);
        submissionRepository.save(submission);
    }

    @Override
    public List<String> getSubmissionFiles(Long assignmentId) {
        User user = authenticationService.getCurrentUser();
        Assignment assignment = assignmentRepository
                .findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found!"));
        Student student = studentRepository.findStudentByUserId(user.getId());

        return submissionRepository
                .findSubmissionByAssignmentAndStudent(assignment, student)
                .orElseThrow()
                .getFilePaths()
                .stream()
                .map(FilePath::getPath)
                .toList();
    }
}
