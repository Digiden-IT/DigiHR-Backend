package com.digiHR.user;


import lombok.Getter;

@Getter
public enum EmployeeType {
    FULLTIME ( "Full-time" ),
    PARTTIME ( "Part-time" ),
    CONTRATUAL ( "Contratual" ),;

    final String type;
    EmployeeType( String type ) {
        this.type = type;
    }

}
