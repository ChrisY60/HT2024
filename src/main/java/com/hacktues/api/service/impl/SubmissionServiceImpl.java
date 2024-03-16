package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.SubmissionResponse;
import com.hacktues.api.entity.*;
import com.hacktues.api.repository.AssignmentRepository;
import com.hacktues.api.repository.StudentRepository;
import com.hacktues.api.repository.SubmissionRepository;
import com.hacktues.api.service.AuthenticationService;
import com.hacktues.api.service.StorageService;
import com.hacktues.api.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;
    private final StorageService storageService;
    private final AuthenticationService authenticationService;

    @Override
    public void submit(Long assignmentId, List<MultipartFile> files) {
        Assignment assignment = assignmentRepository
                .findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found!"));
        User user = authenticationService.getCurrentUser();
        Submission submission = new Submission();
        Student student = studentRepository.findStudentByUserId(user.getId());

        submission.setAssignment(assignment);
        submission.setStudent(student);
        submission.setHandedIn(true);

        List<FilePath> filePaths = files.stream()
                .map(file -> storageService.uploadFile(
                        file,
                        user.getSchool().getName() + "-" + user.getClass().getName() + "-" + UUID.randomUUID()
                ))
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

    @Override
    public SubmissionResponse getSubmissionFilesByStudent(Long assignmentId, Long studentId) {
        return null;
    }
}
