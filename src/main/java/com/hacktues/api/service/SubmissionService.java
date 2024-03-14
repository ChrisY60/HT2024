package com.hacktues.api.service;

import com.hacktues.api.DTO.SubmissionRequest;

public interface SubmissionService {
    void submit(Long assignmentId, SubmissionRequest submissionRequest);
}
