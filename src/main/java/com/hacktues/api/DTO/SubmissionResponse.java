package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class SubmissionResponse {
    private Long id;
    private GradeResponse gradeResponse;
    private List<String> files;
    private boolean graded;
}
