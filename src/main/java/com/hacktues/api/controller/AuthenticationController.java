package com.hacktues.api.controller;

import com.hacktues.api.DTO.StudentRegisterRequest;
import com.hacktues.api.DTO.TeacherRegisterRequest;
import com.hacktues.api.DTO.UserLoginRequest;
import com.hacktues.api.DTO.UserResponse;
import com.hacktues.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register-teacher")
    public ResponseEntity<UserResponse> registerTeacher(@RequestBody TeacherRegisterRequest teacherRegisterRequest) {
        return ResponseEntity.ok(authenticationService.registerTeacher(teacherRegisterRequest));
    }

    @PostMapping("/register-student")
    public ResponseEntity<UserResponse> registerStudent(@RequestBody StudentRegisterRequest studentRegisterRequest) {
        return ResponseEntity.ok(authenticationService.registerStudent(studentRegisterRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest userLoginDTO) {
        return ResponseEntity.ok(authenticationService.login(userLoginDTO));
    }
}
