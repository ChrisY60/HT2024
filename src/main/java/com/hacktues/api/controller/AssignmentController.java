package com.hacktues.api.controller;

import com.hacktues.api.DTO.AssignmentCreateRequest;
import com.hacktues.api.DTO.AssignmentCreateRequestTemp;
import com.hacktues.api.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/subjects/{subjectId}/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<Void> createAssignment(@PathVariable Long subjectId,
                                                 @RequestPart("assignmentCreateRequest") AssignmentCreateRequest assignmentCreateRequest,
                                                 @RequestPart("files") List<MultipartFile> files) {
        assignmentService.createAssignment(subjectId, assignmentCreateRequest, files);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAssignmentsBySubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsBySubjectId(subjectId));
    }

    @PostMapping("/temp")
    public ResponseEntity<?> createAssignmentTemp(@PathVariable Long subjectId, @RequestBody AssignmentCreateRequestTemp assignmentCreateRequest) {
        return ResponseEntity.ok().build();
    }
}
