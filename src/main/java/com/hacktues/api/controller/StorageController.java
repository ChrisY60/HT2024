package com.hacktues.api.controller;

import com.hacktues.api.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        System.out.println("In uploadFiles method");
        System.out.println("Files: " + files.size());
        for (MultipartFile file : files) {
            storageService.uploadFile(file, UUID.randomUUID().toString());
        }
        System.out.println("Files uploaded");
    }

    @PostMapping("/files_temp")
    public ResponseEntity<?> uploadFilesTemp(@RequestParam("files") List<MultipartFile> files) {
        System.out.println("In uploadFilesTemp method");
        System.out.println("Files: " + files.size());

        List<?> filePaths = storageService.uploadFiles(files);
        List<Long> filePathsIds = filePaths.stream().map(fp -> ((com.hacktues.api.entity.FilePath) fp).getId()).toList();

        System.out.println("Files uploaded");
        return ResponseEntity.ok(filePathsIds);
    }
}
