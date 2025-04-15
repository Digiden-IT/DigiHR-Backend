package com.digiHR.user;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN( "Admin" ),
    USER( "User" );

    private final String role;

    public String getvalue(){
        return role;
    }

    Role( String role ) {
        this.role = role;
    }
}
