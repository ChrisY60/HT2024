package com.hacktues.api.service;

import com.hacktues.api.entity.FilePath;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {
    FilePath uploadFile(MultipartFile file, String blobName);

    List<?> uploadFiles(List<MultipartFile> files);
}
