package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.GradesWithSubjectResponse;
import com.hacktues.api.entity.Grade;
import com.hacktues.api.entity.Student;
import com.hacktues.api.entity.User;
import com.hacktues.api.mapper.GradeMapper;
import com.hacktues.api.repository.StudentRepository;
import com.hacktues.api.service.GradesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class GradesServiceImpl implements GradesService {
    private final StudentRepository studentRepository;
    private final GradeMapper gradeMapper;

    @Override
    public List<GradesWithSubjectResponse> getGrades() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentRepository.findStudentByUserId(currentUser.getId());
        List<Grade> grades = student.getGrades();

        return grades.stream()
                .collect(groupingBy(Grade::getSubject, mapping(gradeMapper::toGradeResponse, toList())))
                .entrySet().stream()
                .map(entry -> new GradesWithSubjectResponse(entry.getKey().getName(), entry.getValue()))
                .toList();
    }
}
