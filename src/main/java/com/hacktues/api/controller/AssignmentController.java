package com.hacktues.api.controller;

import com.hacktues.api.DTO.AssignmentCreateRequest;
import com.hacktues.api.DTO.AssignmentResponse;
import com.hacktues.api.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/subjects/{subjectId}/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping
    public void createAssignment(@PathVariable Long subjectId, @RequestBody AssignmentCreateRequest assignmentCreateRequest) {
        assignmentService.createAssignment(subjectId, assignmentCreateRequest);
    }

    @GetMapping
    public List<AssignmentResponse> getAssignmentsBySubject(@PathVariable Long subjectId) {
        return assignmentService.getAssignmentsBySubjectId(subjectId);
    }
}
