package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.SubjectResponse;
import com.hacktues.api.entity.Student;
import com.hacktues.api.entity.Subject;
import com.hacktues.api.entity.Teacher;
import com.hacktues.api.entity.User;
import com.hacktues.api.mapper.SubjectMapper;
import com.hacktues.api.repository.StudentRepository;
import com.hacktues.api.repository.SubjectRepository;
import com.hacktues.api.repository.TeacherRepository;
import com.hacktues.api.service.SubjectService;
import com.hacktues.api.enums.Role;
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
    private final TeacherRepository teacherRepository;

    @Override
    public List<SubjectResponse> getSubjects() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser.getRole() == Role.STUDENT) {
            Student student = studentRepository.findStudentByUserId(currentUser.getId());
            List<Subject> subjects = subjectRepository.findSubjectsByStudentClassId(student.getStudentClass().getId());

            return subjectMapper.toSubjectResponseList(subjects);
        } else if(currentUser.getRole() == Role.TEACHER) {
            Teacher teacher = teacherRepository.findTeacherByUserId(currentUser.getId());
            List<Subject> subjects = subjectRepository.findSubjectsByTeacherId(teacher.getId());

            return subjectMapper.toSubjectResponseList(subjects);
        } else {
            return subjectMapper.toSubjectResponseList(subjectRepository.findAll());
        }
    }
}
