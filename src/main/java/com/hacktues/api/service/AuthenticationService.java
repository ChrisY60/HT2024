package com.hacktues.api.service;

import com.hacktues.api.DTO.StudentRegisterRequest;
import com.hacktues.api.DTO.UserLoginRequest;
import com.hacktues.api.DTO.UserResponse;
import com.hacktues.api.DTO.TeacherRegisterRequest;
import com.hacktues.api.entity.User;

public interface AuthenticationService {
    UserResponse registerTeacher(TeacherRegisterRequest teacherRegisterRequest);
    UserResponse registerStudent(StudentRegisterRequest studentRegisterRequest);
    UserResponse login(UserLoginRequest userLoginRequest);
    User getCurrentUser();
}
