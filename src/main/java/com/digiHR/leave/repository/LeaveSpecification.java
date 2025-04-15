package com.digiHR.leave.repository;

import com.digiHR.leave.LeaveReason;
import com.digiHR.leave.RequestStatus;
import com.digiHR.leave.model.Leave;
import org.springframework.data.jpa.domain.Specification;

public class LeaveSpecification {
    public static Specification<Leave> filterByRequestedBy( Long requestedById ) {

        if ( requestedById == null )
            return Specification.where( null );

        return ( root, query, criteriaBuilder ) -> criteriaBuilder.equal( root.get( "employee" ).get( "id" ), requestedById );
    }

    public static Specification<Leave> filterByRequestStatus( RequestStatus status ) {

        if ( status == null )
            return Specification.where( null );

        return ( root, query, criteriaBuilder ) -> criteriaBuilder.equal( root.get( "requestStatus" ), status );
    }

    public static Specification<Leave> filterByLeaveReason( LeaveReason reason ) {

        if ( reason == null )
            return Specification.where( null );

        return ( root, query, criteriaBuilder ) -> criteriaBuilder.equal( root.get( "leaveReason" ), reason );
    }

}
