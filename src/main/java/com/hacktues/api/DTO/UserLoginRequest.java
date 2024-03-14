package com.hacktues.api.DTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserLoginRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
