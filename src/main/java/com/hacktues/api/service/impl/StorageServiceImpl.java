package com.hacktues.api.service.impl;

import com.azure.storage.blob.*;
import com.hacktues.api.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Value("${spring.cloud.azure.storage.blob.endpoint}")
    private String endpoint;

    @Override
    public String uploadFile(MultipartFile file, String blobName) {
        BlobClient blobClient = new BlobClientBuilder()
                .endpoint(endpoint)
                .containerName(containerName)
                .blobName(blobName)
                .buildClient();
        try {
            blobClient.upload(file.getInputStream(), file.getSize(), true);
            return blobClient.getBlobUrl();
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}
