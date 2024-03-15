package com.hacktues.api.controller;

import com.hacktues.api.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/assignments/{assignment_id}/students")
public class AssignmentHelperController {
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getStudents(@PathVariable Long assignment_id) {
        return ResponseEntity.ok(studentService.getStudentsByAssignmentId(assignment_id));
    }
}
