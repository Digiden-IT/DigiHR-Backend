package com.digiHR.holiday.repository;

import com.digiHR.holiday.model.Holiday;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class HolidaySpecification {

//    public static Specification<Holiday> filterByHolidayDateRange(Date startDate, Date endDate ) {
//        if ( startDate == null || endDate == null )
//            return Specification.where( null );
//        return ( root, query, criteriaBuilder ) -> criteriaBuilder.between( root.get( "date" ), startDate, endDate );
//    }

    public static Specification<Holiday> filterByHolidayDateRange(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return Specification.where(null);
        }
        return (root, query, criteriaBuilder) -> {
            // Truncate the time part by comparing only the date portion of the Date object
            return criteriaBuilder.between(
                    criteriaBuilder.function("DATE", Date.class, root.get("date")),
                    startDate, endDate
            );
        };
    }

}
