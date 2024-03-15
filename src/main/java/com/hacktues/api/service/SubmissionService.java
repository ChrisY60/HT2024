package com.hacktues.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubmissionService {
    void submit(Long assignmentId, List<MultipartFile> files);
    List<String> getSubmissionFiles(Long assignmentId);
}
