package com.digiHR.leave;

import lombok.Getter;

@Getter
public enum RequestStatus {
    APPROVED( "Approved" ),
    REJECTED( "Rejected" ),
    PENDING( "Pending" );

    private final String name;

    RequestStatus( String name ) {
        this.name = name;
    }
}
