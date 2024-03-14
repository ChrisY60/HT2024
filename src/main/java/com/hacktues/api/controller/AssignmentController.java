package com.hacktues.api.controller;

import com.hacktues.api.DTO.AssignmentResponse;
import com.hacktues.api.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @GetMapping(path = "/{subjectName}")
    public List<AssignmentResponse> getAssignmentsBySubject(@PathVariable String subjectName) {
        return assignmentService.getAssignmentsBySubject(subjectName);
    }
}
