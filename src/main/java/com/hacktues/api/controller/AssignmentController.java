package com.hacktues.api.controller;

import com.hacktues.api.DTO.AssignmentCreateRequest;
import com.hacktues.api.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/subjects/{subjectId}/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<Void> createAssignment(@PathVariable Long subjectId, @RequestBody AssignmentCreateRequest assignmentCreateRequest) {
        assignmentService.createAssignment(subjectId, assignmentCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAssignmentsBySubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsBySubjectId(subjectId));
    }
}
