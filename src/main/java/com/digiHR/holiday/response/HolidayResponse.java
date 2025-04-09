package com.digiHR.holiday.response;

import com.digiHR.holiday.model.Holiday;
import lombok.Data;
import java.time.LocalDate;

@Data
public class HolidayResponse {

    private Long id;
    private String holidayName;
    private LocalDate date;

    public HolidayResponse(Holiday holiday) {
        this.id = holiday.getId();
        this.holidayName = holiday.getHolidayName();
        this.date = holiday.getDate();
    }
}
