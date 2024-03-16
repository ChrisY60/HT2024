package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StudentResponse {
    private final Long id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
}
