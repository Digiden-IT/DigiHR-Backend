package com.digiHR.user;


import lombok.Getter;

@Getter
public enum EmployeeType {
    FULL_TIME ( "Full-time" ),
    PART_TIME ( "Part-time" ),
    CONTRATUAL ( "Contratual" );

    private final String type;

    public String getvalue(){
        return type;
    }

    EmployeeType( String type ) {
        this.type = type;
    }
}
