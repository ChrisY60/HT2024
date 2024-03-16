package com.hacktues.api.controller;

import com.hacktues.api.DTO.GradeCreateRequest;
import com.hacktues.api.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/submissions{submission_id}/grades")
public class SubmissionGraderController {
    private final GradeService gradeService;

    @PostMapping
    public void gradeSubmission(@PathVariable Long submission_id, @RequestBody GradeCreateRequest gradeCreateRequest) {
        gradeService.gradeSubmission(submission_id, gradeCreateRequest);
    }
}
