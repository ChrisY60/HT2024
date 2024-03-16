package com.hacktues.api.service;

import com.hacktues.api.DTO.GradeCreateRequest;
import com.hacktues.api.DTO.GradesWithSubjectResponse;

import java.util.List;

public interface GradeService {
    List<GradesWithSubjectResponse> getGrades();

    void gradeSubmission(Long submissionId, GradeCreateRequest gradeCreateRequest);
}
