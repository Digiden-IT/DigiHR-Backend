package com.digiHR.leave.repository;

import com.digiHR.leave.model.Leave;
import org.springframework.data.jpa.domain.Specification;

public class LeaveSpecification {
    public static Specification<Leave> filterByRequestedBy( Long requestedById ) {
        if ( requestedById == null )
            return Specification.where( null );

        return ( root, query, criteriaBuilder ) -> criteriaBuilder.equal( root.get( "employee" ).get( "id" ), requestedById );
    }
}
