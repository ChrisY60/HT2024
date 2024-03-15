package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.SubjectResponse;
import com.hacktues.api.DTO.TeacherResponse;
import com.hacktues.api.entity.*;
import com.hacktues.api.enums.Role;
import com.hacktues.api.mapper.SubjectMapper;
import com.hacktues.api.repository.StudentRepository;
import com.hacktues.api.repository.SubjectRepository;
import com.hacktues.api.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class SubjectServiceImplTest {

    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;
    private SubjectMapper subjectMapper;
    private SubjectServiceImpl subjectService;

    @BeforeEach
    void setUp() {
        subjectRepository = mock(SubjectRepository.class);
        studentRepository = mock(StudentRepository.class);
        TeacherRepository teacherRepository = mock(TeacherRepository.class);
        subjectMapper = mock(SubjectMapper.class);
        subjectService = new SubjectServiceImpl(subjectMapper, subjectRepository, studentRepository, teacherRepository);

        User currentUser = new User();
        currentUser.setRole(Role.STUDENT);
        currentUser.setId(1L);
        mockSecurityContext(currentUser);

        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findStudentByUserId(currentUser.getId())).thenReturn(student);

        StudentClass studentClass = new StudentClass();
        studentClass.setId(1L);
        student.setStudentClass(studentClass);
    }

    private void mockSecurityContext(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }

    @Test
    void getSubjects_studentRole() {
        User currentUser = new User();
        currentUser.setRole(Role.STUDENT);
        currentUser.setId(1L);
        mockSecurityContext(currentUser);

        Student student = mock(Student.class);
        StudentClass studentClass = new StudentClass();
        studentClass.setId(1L);
        when(student.getStudentClass()).thenReturn(studentClass);
        when(studentRepository.findStudentByUserId(currentUser.getId())).thenReturn(student);

        Subject subject1 = new Subject();
        subject1.setId(1L);
        subject1.setName("Subject 1");
        Subject subject2 = new Subject();
        subject2.setId(2L);
        subject2.setName("Subject 2");

        List<Subject> subjects = List.of(subject1, subject2);

        List<SubjectResponse> expectedResponses = List.of(
                new SubjectResponse(1L, "Subject 1", new TeacherResponse("John", "Doe", "Math")), // Provide teacher details here
                new SubjectResponse(2L, "Subject 2", new TeacherResponse("Jane", "Smith", "Physics")) // Provide teacher details here
        );
        when(subjectMapper.toSubjectResponseList(subjects)).thenReturn(expectedResponses);

        when(subjectRepository.findSubjectsByStudentClassId(student.getStudentClass().getId())).thenReturn(subjects);

        List<SubjectResponse> actualResponses = subjectService.getSubjects();

        assertEquals(expectedResponses, actualResponses);
    }
}