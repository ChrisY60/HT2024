package com.hacktues.api.controller;

import com.hacktues.api.service.GradeService;
import com.hacktues.api.service.GradesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/grades")
public class GradeController {
    private final GradeService gradeService;

    @GetMapping
    public ResponseEntity<?> getGrades() {
        return ResponseEntity.ok(gradeService.getGrades());
    }
}
