package com.hacktues.api.service.impl;

import com.azure.storage.blob.*;
import com.hacktues.api.entity.FilePath;
import com.hacktues.api.repository.FileRepository;
import com.hacktues.api.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Value("${spring.cloud.azure.storage.blob.endpoint}")
    private String endpoint;

    private final FileRepository fileRepository;

    @Override
    public FilePath uploadFile(MultipartFile file, String blobName) {
        BlobClient blobClient = new BlobClientBuilder()
                .endpoint(endpoint)
                .containerName(containerName)
                .blobName(blobName)
                .buildClient();
        try {
            blobClient.upload(file.getInputStream(), file.getSize(), true);
            FilePath filePath = new FilePath();
            filePath.setPath(blobClient.getBlobUrl());
            fileRepository.save(filePath);
            return filePath;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Override
    public List<?> uploadFiles(List<MultipartFile> files) {
        List<FilePath> filePaths = new ArrayList<>();
        for (MultipartFile file : files) {
            FilePath filePath = uploadFile(file, file.getOriginalFilename());
            filePaths.add(filePath);
        }

        return filePaths;
    }
}
