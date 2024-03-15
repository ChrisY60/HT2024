package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class GradeResponse {
    private final Double grade;
    private final String comment;
    private final Date gradedDate;
}
