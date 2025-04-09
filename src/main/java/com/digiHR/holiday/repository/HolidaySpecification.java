package com.digiHR.holiday.repository;

import com.digiHR.holiday.model.Holiday;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class HolidaySpecification {


    public static Specification<Holiday> filterByHolidayDateRange( LocalDate startDate, LocalDate endDate ) {
        if ( startDate == null || endDate == null )
            return Specification.where(null );

        return (root, query, criteriaBuilder ) -> criteriaBuilder.between( root.get( "date" ), startDate, endDate );
    }
}
