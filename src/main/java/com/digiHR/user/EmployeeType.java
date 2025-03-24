package com.digiHR.user;


import lombok.Getter;

@Getter
public enum EmployeeType {
    OFFICE ( "office" ),
    REMOTE ( "remote" ),
    HYBRID ( "hybrid");


    final String type;
    EmployeeType(String type) {
        this.type = type;
    }

}
