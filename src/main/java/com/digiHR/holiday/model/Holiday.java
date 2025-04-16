package com.digiHR.holiday.model;


import com.digiHR.holiday.request.AddHolidayRequest;
import com.digiHR.utility.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table( name = "holidays" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holiday extends AuditableEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "holiday_name", nullable = false )
    private String holidayName;

    @Column( name = "date", nullable = false )
    private Date date;

    public Holiday( AddHolidayRequest addHolidayRequest ) {
        this.holidayName = addHolidayRequest.getHolidayName();
        this.date = addHolidayRequest.getDate();
    }
}
