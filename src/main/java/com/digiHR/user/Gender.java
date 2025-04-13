package com.digiHR.user;

import lombok.Getter;


@Getter
public enum Gender {
    MALE( "Male" ),
    FEMALE( "Female" );

    private final String gender;

    public String getvalue(){
        return gender;
    }
    Gender( String gender ) {
        this.gender = gender;
    }
}
