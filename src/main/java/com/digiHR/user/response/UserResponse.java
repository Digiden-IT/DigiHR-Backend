package com.digiHR.user.response;

import com.digiHR.user.Role;
import com.digiHR.user.model.User;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;

    public UserResponse( User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
