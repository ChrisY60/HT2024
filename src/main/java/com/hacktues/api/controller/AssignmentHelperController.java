package com.hacktues.api.controller;

import com.hacktues.api.DTO.StudentResponse;
import com.hacktues.api.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/assignments/{assignment_id}/students")
public class AssignmentHelperController {
    private StudentService studentService;

    @GetMapping
    public List<StudentResponse> getStudents(@PathVariable Long assignment_id) {
        return studentService.getStudentsByAssignmentId(assignment_id);
    }
}
