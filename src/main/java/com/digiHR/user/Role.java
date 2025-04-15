package com.digiHR.user;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN( "Admin" ),
    USER( "User" );

    private final String name;

    public String getvalue(){
        return name;
    }

    Role( String name ) {
        this.name = name;
    }
}
