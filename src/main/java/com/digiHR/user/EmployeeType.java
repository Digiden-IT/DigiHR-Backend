package com.digiHR.user;


import lombok.Getter;

@Getter
public enum EmployeeType {
    FULL_TIME ( "Full-time" ),
    PART_TIME ( "Part-time" ),
    CONTRATUAL ( "Contratual" ),;

    final String type;
    EmployeeType( String type ) {
        this.type = type;
    }

}
