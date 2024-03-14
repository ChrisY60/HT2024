package com.hacktues.api.controller;

import com.hacktues.api.DTO.MaterialResponse;
import com.hacktues.api.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/materials")
public class MaterialsController {
    private final MaterialService materialService;

    @GetMapping(path = "/{subjectName}")
    public List<MaterialResponse> getMaterialsBySubject(@PathVariable String subjectName) {
        return materialService.getMaterialsBySubject(subjectName);
    }
}
