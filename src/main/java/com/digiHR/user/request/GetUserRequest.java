package com.digiHR.user.request;


import com.digiHR.user.Role;
import lombok.Data;

@Data
public class GetUserRequest {

    private Boolean isActive;
    private Role role;
}
