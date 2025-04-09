package com.digiHR.holiday.model;


import com.digiHR.holiday.request.AddHolidayRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "holidays")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "holiday_name", nullable = false)
    private String holidayName;

    @Column(name = "date", nullable = false, unique = true)
    private LocalDate date;

    public Holiday(AddHolidayRequest addHolidayRequest) {
        this.holidayName = addHolidayRequest.getHolidayName();
        this.date = addHolidayRequest.getDate();
    }
}
