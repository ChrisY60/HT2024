package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.StudentResponse;
import com.hacktues.api.entity.Assignment;
import com.hacktues.api.entity.Student;
import com.hacktues.api.mapper.StudentMapper;
import com.hacktues.api.repository.AssignmentRepository;
import com.hacktues.api.repository.StudentRepository;
import com.hacktues.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final AssignmentRepository assignmentRepository;


    @Override
    public List<StudentResponse> getStudentsByAssignmentId(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new RuntimeException("Assignment not found!"));
        List<Student> students = studentRepository.findStudentsNameByStudentClassId(assignment.getSubject().getStudentClass().getId());

        return studentMapper.toStudentResponseList(students);
    }
}
