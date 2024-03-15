package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class AssignmentCreateRequest {
    private final String name;
    private final String description;
    private final Date deadline;
}
