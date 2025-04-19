package com.digiHR.leave;

import lombok.Getter;

@Getter
public enum RequestStatus {
    APPROVED( "Approved" ),
    PENDING( "Pending" ),
    REJECTED( "Rejected" );

    private final String name;

    RequestStatus( String name ) {
        this.name = name;
    }
}
