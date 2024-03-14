package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.TeacherRegisterRequest;
import com.hacktues.api.DTO.UserLoginRequest;
import com.hacktues.api.DTO.UserResponse;
import com.hacktues.api.entity.Teacher;
import com.hacktues.api.entity.User;
import com.hacktues.api.enums.Role;
import com.hacktues.api.repository.SchoolRepository;
import com.hacktues.api.repository.TeacherRepository;
import com.hacktues.api.repository.UserRepository;
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

import static com.hacktues.api.mapper.TeacherMapper.TEACHER_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @Override
    public UserResponse registerTeacher(TeacherRegisterRequest teacherRegisterRequest) {
        if (userRepository.existsByEmail(teacherRegisterRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Teacher teacher = TEACHER_MAPPER.fromRegisterRequest(teacherRegisterRequest);
        teacher.getUser().setPassword(passwordEncoder.encode(teacherRegisterRequest.getPassword()));
        teacher.getUser().setRole(Role.valueOf("TEACHER"));
        teacher.getUser().setSchool(schoolRepository.findByName(
                teacherRegisterRequest.getSchool()).orElseThrow(() -> new RuntimeException("School not found"))
        );
        teacherRepository.save(teacher);

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(teacher.getUser().getEmail());
        userResponse.setToken(jwtGenerator.generateToken(teacher.getUser()));

        return userResponse;
    }

    @Override
    public UserResponse loginTeacher(UserLoginRequest teacherLoginRequest) {
        User user;
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            teacherLoginRequest.getEmail(),
                            teacherLoginRequest.getPassword()
                    )
            );
            user = (User) authentication.getPrincipal();
        } catch (Exception e) {
            if (userRepository.existsByEmail(teacherLoginRequest.getEmail())) {
                throw new BadCredentialsException("Wrong password!", e);
            } else {
                throw new BadCredentialsException("Wrong username!", e);
            }
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setToken(jwtGenerator.generateToken(user));

        return userResponse;
    }
}
