package com.hacktues.api.service;

import com.hacktues.api.DTO.AssignmentCreateRequest;
import com.hacktues.api.DTO.AssignmentResponse;
import com.hacktues.api.entity.Assignment;

import java.util.List;

public interface AssignmentService {
    List<AssignmentResponse> getAssignmentsBySubjectId(Long subjectId);

    void createAssignment(Long subjectId, AssignmentCreateRequest assignmentCreateRequest);
}
