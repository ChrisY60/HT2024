package com.hacktues.api.controller;

import com.hacktues.api.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/storage")
public class StorageController {
    private final StorageService storageService;

    @PostMapping("/file")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        storageService.uploadFile(file, "test-1");
    }

    @PostMapping("/files")
    public void uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        for (MultipartFile file : files) {
            storageService.uploadFile(file, UUID.randomUUID().toString());
        }
    }
}
