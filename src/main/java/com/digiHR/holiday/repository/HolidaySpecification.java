package com.digiHR.holiday.repository;

import com.digiHR.holiday.model.Holiday;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class HolidaySpecification {

    public static Specification<Holiday> filterByHolidayDateRange( Date startDate, Date endDate ) {
        if ( startDate == null || endDate == null ) {
            return Specification.where( null );
        }
        return ( root, query, criteriaBuilder ) -> {
            return criteriaBuilder.between(
                    criteriaBuilder.function("DATE", Date.class, root.get( "date" ) ),
                    startDate, endDate
            );
        };
    }

    public static Specification<Holiday> filterByIsUpcoming( Boolean isUpcoming ) {
        if ( isUpcoming == null )
            return Specification.where( null );

        LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
        return ( root, query, criteriaBuilder ) -> isUpcoming
                ? criteriaBuilder.greaterThanOrEqualTo(root.get( "date" ), startOfToday )
                : criteriaBuilder.lessThan(root.get( "date" ), startOfToday );

    }
}
