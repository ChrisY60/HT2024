package com.hacktues.api.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@RequiredArgsConstructor
public class SubmissionRequest {
    private final List<MultipartFile> files;
}
