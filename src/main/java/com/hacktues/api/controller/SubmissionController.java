package com.hacktues.api.controller;

import com.hacktues.api.DTO.SubmissionRequest;
import com.hacktues.api.service.SubmissionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/assignments/{assignment_id}/submissions")
public class SubmissionController {
    private final SubmissionService submissionService;

    @PostMapping
    public void submitAssignment(@PathVariable Long assignment_id, @RequestBody SubmissionRequest submissionRequest) {
        submissionService.submit(assignment_id, submissionRequest);
    }

    @GetMapping
    public List<String> getSubmissionFiles(@PathVariable Long assignment_id) {
        return submissionService.getSubmissionFiles(assignment_id);
    }
}
