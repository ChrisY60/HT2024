package com.hacktues.api.service;

import com.hacktues.api.DTO.UserLoginRequest;
import com.hacktues.api.DTO.UserResponse;
import com.hacktues.api.DTO.TeacherRegisterRequest;

public interface AuthenticationService {
    UserResponse registerTeacher(TeacherRegisterRequest teacherRegisterRequest);
    UserResponse loginTeacher(UserLoginRequest teacherLoginRequest);
}
