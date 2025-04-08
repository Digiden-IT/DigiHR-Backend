package com.digiHR.user;

import lombok.Getter;

@Getter
public enum Department {
    HR( "Hr" ),
    MARKETING( "Marketing" ),
    SALES( "Sales" ),
    ADMIN( "Admin" ),
    TECHNOLOGY( "Technology" ),
    FINANCE( "Finance" ),
    OTHER( "other" );

    private final String department;

    Department( String department ) {
        this.department = department;
    }
}
