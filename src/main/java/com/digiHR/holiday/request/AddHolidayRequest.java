package com.digiHR.holiday.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AddHolidayRequest {

    private String holidayName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
