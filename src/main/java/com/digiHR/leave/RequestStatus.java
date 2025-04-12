package com.digiHR.leave;

import lombok.Getter;

@Getter
public enum RequestStatus {
    APPROVED( "approved" ),
    REJECTED( "rejected" ),
    PENDING( "pending" );

    final String name;

    RequestStatus( String name ) {
        this.name = name;
    }
}
