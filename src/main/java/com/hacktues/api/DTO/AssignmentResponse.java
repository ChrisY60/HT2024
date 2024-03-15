package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class AssignmentResponse {
    private final Long id;
    private final String name;
    private final Date deadline;
    private final String description;
}
