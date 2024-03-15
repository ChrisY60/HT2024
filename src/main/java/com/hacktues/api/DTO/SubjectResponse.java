package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SubjectResponse {
    private final Long id;
    private final String name;
    private final TeacherResponse teacher;
}
