package com.digiHR.holiday.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AddHolidayRequest {

    private String holidayName;
    private Date date;
}
