package com.hacktues.api.controller;

import com.azure.core.annotation.Post;
import com.hacktues.api.DTO.MaterialCreateRequest;
import com.hacktues.api.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> createMaterial(@PathVariable Long subjectId, @RequestBody MaterialCreateRequest materialCreateRequest) {
        materialService.createMaterial(subjectId, materialCreateRequest);
        return ResponseEntity.ok().build();
    }
}
