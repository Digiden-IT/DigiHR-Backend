package com.digiHR.user;

import lombok.Getter;

@Getter
public enum Department {

    HR("hr"),
    BACKEND("backend"),
    FRONTEND("frontend"),
    MARKETING("marketing"),
    SALES("sales"),
    OPERATIONS("operations"),
    ADMIN("admin"),
    ENGINEERING("engineering"),
    OTHER("other");

    final String department;

    Department(String department) {
        this.department = department;
    }
}
