package com.digiHR.user;


import lombok.Getter;

@Getter
public enum EmployeeType {
    FULL_TIME ( "Full-time" ),
    PART_TIME ( "Part-time" ),
    CONTRACTUAL( "Contractual" );

    private final String value;

    EmployeeType( String value ) {
        this.value = value;
    }
}
