package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StudentClassCreateRequest {
    private String name;
    private Long schoolId;
}
