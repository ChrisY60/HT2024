package com.hacktues.api.DTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class StudentRegisterRequest {
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
    private String studentClass;
    @NonNull
    private Integer classNumber;
    @NonNull
    private String accessCode;
}
