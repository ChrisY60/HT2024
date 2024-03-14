package com.hacktues.api.DTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserRegisterRequest {
    @NonNull
    private String firstName;
    @NonNull
    private String middleName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String school;
    @NonNull
    private String role;
    private String studentClass;
    private Integer classNumber;
}
