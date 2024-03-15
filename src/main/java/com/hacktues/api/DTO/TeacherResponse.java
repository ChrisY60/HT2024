package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TeacherResponse {
    private final String firstName;
    private final String middleName;
    private final String lastName;
}
