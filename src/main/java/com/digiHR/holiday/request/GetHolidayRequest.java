package com.digiHR.holiday.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class GetHolidayRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date holidayDateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date holidayDateEnd;
    private Boolean isUpcoming;
}

