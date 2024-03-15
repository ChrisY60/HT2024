package com.hacktues.api.service;

import com.hacktues.api.DTO.SubmissionRequest;

import java.util.List;

public interface SubmissionService {
    void submit(Long assignmentId, SubmissionRequest submissionRequest);
    List<String> getSubmissionFiles(Long assignmentId);
}
