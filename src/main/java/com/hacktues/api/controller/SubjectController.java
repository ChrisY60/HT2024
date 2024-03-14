package com.hacktues.api.controller;

import com.hacktues.api.DTO.SubjectResponse;
import com.hacktues.api.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping
    public List<SubjectResponse> getSubjects() {
        return subjectService.getSubjects();
    }
}
