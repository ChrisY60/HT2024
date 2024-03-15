package com.hacktues.api.service;

import com.hacktues.api.DTO.GradeResponse;
import com.hacktues.api.DTO.GradesWithSubjectResponse;

import java.util.List;

public interface GradesService {
    List<GradesWithSubjectResponse> getGrades();
}
