package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.StudentRegisterRequest;
import com.hacktues.api.DTO.TeacherRegisterRequest;
import com.hacktues.api.DTO.UserLoginRequest;
import com.hacktues.api.DTO.UserResponse;
import com.hacktues.api.entity.Student;
import com.hacktues.api.entity.Teacher;
import com.hacktues.api.entity.User;
import com.hacktues.api.enums.Role;
import com.hacktues.api.repository.*;
import com.hacktues.api.security.JWTGenerator;
import com.hacktues.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.hacktues.api.mapper.TeacherMapper.TEACHER_MAPPER;
import static com.hacktues.api.mapper.StudentMapper.STUDENT_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;
    private final StudentClassRepository studentClassRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @Override
    public UserResponse registerTeacher(TeacherRegisterRequest teacherRegisterRequest) {
        if (userRepository.existsByEmail(teacherRegisterRequest.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        Teacher teacher = TEACHER_MAPPER.fromRegisterRequest(teacherRegisterRequest);
        teacher.getUser().setPassword(passwordEncoder.encode(teacherRegisterRequest.getPassword()));
        teacher.getUser().setRole(Role.valueOf("TEACHER"));
        teacher.getUser().setSchool(schoolRepository.findByName(
                teacherRegisterRequest.getSchool()).orElseThrow(() -> new RuntimeException("School not found!"))
        );
        teacherRepository.save(teacher);

        UserResponse userResponse = new UserResponse();
        userResponse.setToken(jwtGenerator.generateToken(teacher.getUser()));

        return userResponse;
    }

    @Override
    public UserResponse registerStudent(StudentRegisterRequest studentRegisterRequest) {
        if (userRepository.existsByEmail(studentRegisterRequest.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        Student student = STUDENT_MAPPER.fromRegisterRequest(studentRegisterRequest);
        student.getUser().setPassword(passwordEncoder.encode(studentRegisterRequest.getPassword()));
        student.getUser().setRole(Role.valueOf("STUDENT"));
        student.getUser().setSchool(schoolRepository.findByName(
                studentRegisterRequest.getSchool()).orElseThrow(() -> new RuntimeException("School not found!"))
        );
        student.setStudentClass(studentClassRepository.findByName(
                studentRegisterRequest.getStudentClass())
                .orElseThrow(() -> new RuntimeException("Class not found!"))
        );
        UUID accessCode = UUID.fromString(studentRegisterRequest.getAccessCode());
        System.out.println(student.getStudentClass().getAccessCode());
        System.out.println(accessCode);
        if (!student.getStudentClass().getAccessCode().equals(accessCode)) {
            throw new RuntimeException("Invalid access code!");
        }
        studentRepository.save(student);

        UserResponse userResponse = new UserResponse();
        userResponse.setToken(jwtGenerator.generateToken(student.getUser()));

        return userResponse;
    }

    @Override
    public UserResponse login(UserLoginRequest userLoginRequest) {
        User user;
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginRequest.getEmail(),
                            userLoginRequest.getPassword()
                    )
            );
            user = (User) authentication.getPrincipal();
        } catch (Exception e) {
            if (userRepository.existsByEmail(userLoginRequest.getEmail())) {
                throw new BadCredentialsException("Wrong password!", e);
            } else {
                throw new BadCredentialsException("Wrong username!", e);
            }
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserResponse userResponse = new UserResponse();
        userResponse.setToken(jwtGenerator.generateToken(user));

        return userResponse;
    }
}
