package com.digiHR.user;

public enum BloodGroup {

    A_POSITIVE( "A+" ),
    A_NEGATIVE( "A-" ),
    B_POSITIVE( "B+" ),
    B_NEGATIVE( "B-" ),
    O_POSITIVE( "O+" ),
    O_NEGATIVE( "O-"),
    AB_POSITIVE( "AB+" ),
    AB_NEGATIVE( "AB-" );

    final String bloodGroup;

    BloodGroup( String bloodGroup ) {
        this.bloodGroup = bloodGroup;
    }
}
