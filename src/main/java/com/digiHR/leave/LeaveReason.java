package com.digiHR.leave;

import lombok.Getter;

@Getter
public enum LeaveReason {
    CASUALEAVE(  "casual_leave" ),
    SICKLEAVE(  "sick_leave" ),
    FAMILYVACATION(  "family_vacation" );

    final String name;

    LeaveReason( String name ) {
        this.name = name;
    }
}
