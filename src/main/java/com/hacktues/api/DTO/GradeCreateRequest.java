package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GradeCreateRequest {
    private Double grade;
    private String comment;
}
