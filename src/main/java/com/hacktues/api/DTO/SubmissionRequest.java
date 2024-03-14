package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class SubmissionRequest {
    private final MultipartFile file;
}
