package com.digiHR.leave;

import lombok.Getter;

@Getter
public enum LeaveReason {
    CASUAL_LEAVE( "Casual Leave" ),
    SICK_LEAVE( "Sick Leave" ),
    VACATION_LEAVE( "Vacation Leave" );

    private final String value;

    LeaveReason( String value ) {
        this.value = value;
    }
}
