package com.hacktues.api.service;

import com.hacktues.api.entity.FilePath;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    FilePath uploadFile(MultipartFile file, String blobName);
}
