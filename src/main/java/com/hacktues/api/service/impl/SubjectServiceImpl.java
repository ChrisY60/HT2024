package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.SubjectResponse;
import com.hacktues.api.entity.Student;
import com.hacktues.api.entity.Subject;
import com.hacktues.api.entity.User;
import com.hacktues.api.mapper.SubjectMapper;
import com.hacktues.api.repository.StudentRepository;
import com.hacktues.api.repository.SubjectRepository;
import com.hacktues.api.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectMapper subjectMapper;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<SubjectResponse> getSubjects() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentRepository.findStudentByUserId(currentUser.getId());

        List<Subject> subjects = subjectRepository.findSubjectsByStudentClassId(student.getStudentClass().getId());

        return subjectMapper.toSubjectResponseList(subjects);
    }
}
