package com.digiHR.leave;

import lombok.Getter;

@Getter
public enum Status {
    APPROVED( "approved" ),
    REJECTED( "rejected" ),
    PENDING( "pending" );

    final String name;

    Status( String name ) {
        this.name = name;
    }
}
