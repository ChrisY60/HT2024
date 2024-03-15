package com.hacktues.api.controller;

import com.hacktues.api.DTO.SchoolCreateRequest;
import com.hacktues.api.DTO.StudentClassCreateRequest;
import com.hacktues.api.entity.School;
import com.hacktues.api.entity.StudentClass;
import com.hacktues.api.mapper.SchoolMapper;
import com.hacktues.api.mapper.StudentClassMapper;
import com.hacktues.api.repository.SchoolRepository;
import com.hacktues.api.repository.StudentClassRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/auth/config")
public class ConfigController {
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;
    private final StudentClassRepository studentClassRepository;
    private final StudentClassMapper studentClassMapper;

    @PostMapping(path = "/school")
    public void createSchool(@RequestBody SchoolCreateRequest request) {
        School school = schoolMapper.fromRequest(request);
        schoolRepository.save(school);
    }

    @PostMapping(path = "/student-class")
    public void createStudentClass(@RequestBody StudentClassCreateRequest request) {
        StudentClass studentClass = studentClassMapper.fromRequest(request);
        School school = schoolRepository.findById(request.getSchoolId()).orElseThrow();
        studentClass.setSchool(school);

        studentClassRepository.save(studentClass);
    }
}
