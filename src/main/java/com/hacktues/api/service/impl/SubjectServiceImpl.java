package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.SubjectResponse;
import com.hacktues.api.entity.Student;
import com.hacktues.api.entity.Subject;
import com.hacktues.api.entity.User;
import com.hacktues.api.mapper.SubjectMapper;
import com.hacktues.api.repository.SubjectRepository;
import com.hacktues.api.repository.TeacherRepository;
import com.hacktues.api.repository.UserRepository;
import com.hacktues.api.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final UserRepository userRepository;
    private final SubjectMapper subjectMapper;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<SubjectResponse> getSubjects() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(currentUserEmail).orElseThrow();
        Student student = Student.class.cast(user);

        List<Subject> subjects = subjectRepository.findSubjectsByStudentClassId(student.getStudentClass().getId());

        return subjectMapper.toSubjectResponseList(subjects);
    }
}
