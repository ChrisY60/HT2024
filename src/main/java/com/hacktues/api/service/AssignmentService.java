package com.hacktues.api.service;

import com.hacktues.api.DTO.AssignmentResponse;
import com.hacktues.api.entity.Assignment;

import java.util.List;

public interface AssignmentService {
    List<AssignmentResponse> getAssignmentsBySubject(String subjectName);
}
