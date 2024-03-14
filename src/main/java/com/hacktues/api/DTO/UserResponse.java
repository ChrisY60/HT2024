package com.hacktues.api.DTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserResponse {
    @NonNull
    private String firstName;
    @NonNull
    private String middleName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String school;
}
