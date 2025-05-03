package com.digiHR.leave;

import lombok.Getter;

@Getter
public enum RequestStatus {
    APPROVED( "Approved" ),
    PENDING( "Pending" ),
    REJECTED( "Rejected" );

    private final String value;

    RequestStatus( String value ) {
        this.value = value;
    }
}
