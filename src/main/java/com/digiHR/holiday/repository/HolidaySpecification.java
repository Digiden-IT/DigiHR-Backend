package com.digiHR.holiday.repository;

import com.digiHR.holiday.model.Holiday;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class HolidaySpecification {

    public static Specification<Holiday> filterByHolidayName(String holidayName) {
        return (root, query, cb) -> (holidayName == null || holidayName.isEmpty()) ? null :
                cb.like(cb.lower(root.get("holidayName")), "%" + holidayName.toLowerCase() + "%");
    }

    public static Specification<Holiday> filterByDate(LocalDate date) {
        return (root, query, cb) -> (date == null) ? null :
                cb.equal(root.get("date"), date);
    }


}
