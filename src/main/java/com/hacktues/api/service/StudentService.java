package com.hacktues.api.service;

import com.hacktues.api.DTO.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getStudentsByAssignmentId(Long assignmentId);
}
