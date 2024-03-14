package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class SubjectGradesResponse {
    private final List<GradeResponse> grades;
    private final String subject;
}
