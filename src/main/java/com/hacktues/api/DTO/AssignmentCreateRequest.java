package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class AssignmentCreateRequest {
    private final String name;
    private final String description;
    private final Date deadline;
    private final String studentClassName;
}
