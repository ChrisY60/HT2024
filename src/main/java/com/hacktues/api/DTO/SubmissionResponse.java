package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SubmissionResponse {
    private final Double grade;
    private final String filePath;
}
