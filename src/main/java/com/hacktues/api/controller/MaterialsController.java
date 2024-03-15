package com.hacktues.api.controller;

import com.azure.core.annotation.Post;
import com.hacktues.api.DTO.MaterialCreateRequest;
import com.hacktues.api.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/subjects/{subjectId}/materials")
public class MaterialsController {
    private final MaterialService materialService;

    @GetMapping
    public ResponseEntity<?> getMaterialsBySubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(materialService.getMaterialsBySubjectId(subjectId));
    }

    @PostMapping
    public ResponseEntity<Void> createMaterial(@PathVariable Long subjectId,
                                               @RequestPart("materialCreateRequest") MaterialCreateRequest materialCreateRequest,
                                               @RequestPart("files") List<MultipartFile> files) {
        materialService.createMaterial(subjectId, materialCreateRequest, files);
        return ResponseEntity.ok().build();
    }
}
