package com.digiHR.holiday.repository;

import com.digiHR.holiday.model.Holiday;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Date;

public class HolidaySpecification {

    public static Specification<Holiday> filterByHolidayDateRange(Date startDate, Date endDate ) {
        if ( startDate == null || endDate == null )
            return Specification.where( null );
        return ( root, query, criteriaBuilder ) -> criteriaBuilder.between( root.get( "date" ), startDate, endDate );
    }

    public static Specification<Holiday> filterByIsUpcoming( Boolean isUpcoming ) {
        if ( isUpcoming == null )
            return Specification.where( null );

        LocalDate today = LocalDate.now();
        return ( root, query, criteriaBuilder ) -> isUpcoming
                ? criteriaBuilder.greaterThanOrEqualTo( root.get( "date" ), today )
                : criteriaBuilder.lessThan( root.get( "date" ), today );
    }

}
