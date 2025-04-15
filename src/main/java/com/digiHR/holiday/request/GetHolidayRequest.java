package com.digiHR.holiday.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class GetHolidayRequest {

    private LocalDate holidayDateStart;
    private LocalDate holidayDateEnd;
    private Boolean isUpcoming;
}
