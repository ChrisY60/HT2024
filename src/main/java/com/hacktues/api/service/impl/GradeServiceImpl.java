package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.GradeCreateRequest;
import com.hacktues.api.DTO.GradesWithSubjectResponse;
import com.hacktues.api.entity.*;
import com.hacktues.api.mapper.GradeMapper;
import com.hacktues.api.repository.StudentRepository;
import com.hacktues.api.repository.SubjectRepository;
import com.hacktues.api.repository.SubmissionRepository;
import com.hacktues.api.repository.TeacherRepository;
import com.hacktues.api.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final StudentRepository studentRepository;
    private final GradeMapper gradeMapper;
    private final TeacherRepository teacherRepository;
    private final SubmissionRepository submissionRepository;

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

    @Override
    public void gradeSubmission(Long submissionId, GradeCreateRequest gradeCreateRequest) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Teacher teacher = teacherRepository.findTeacherByUserId(currentUser.getId());
        Grade grade = gradeMapper.toGrade(gradeCreateRequest);
        Submission submission = submissionRepository.findById(submissionId).orElseThrow();

        grade.setTeacher(teacher);
        grade.setStudent(submission.getStudent());
        grade.setSubject(submission.getAssignment().getSubject());

        submission.setGraded(true);
        submissionRepository.save(submission);
    }
}
