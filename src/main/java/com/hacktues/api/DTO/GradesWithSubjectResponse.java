package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class GradesWithSubjectResponse {
    private final String name;
    private final List<GradeResponse> grades;
}
