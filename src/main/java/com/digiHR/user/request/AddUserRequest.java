package com.digiHR.user.request;

import com.digiHR.user.Role;
import lombok.Data;

@Data
public class AddUserRequest {
    private String name;
    private String password;
    private String email;
    private Role role;
}
