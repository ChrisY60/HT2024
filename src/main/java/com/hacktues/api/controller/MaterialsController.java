package com.hacktues.api.controller;

import com.hacktues.api.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/subjects/{subjectId}/materials")
public class MaterialsController {
    private final MaterialService materialService;

    @GetMapping
    public ResponseEntity<?> getMaterialsBySubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(materialService.getMaterialsBySubjectId(subjectId));
    }
}
