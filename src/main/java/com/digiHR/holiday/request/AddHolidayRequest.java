package com.digiHR.holiday.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddHolidayRequest {

    private String holidayName;
    private LocalDate date;
}
