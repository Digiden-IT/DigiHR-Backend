package com.digiHR.holiday.response;

import com.digiHR.holiday.model.Holiday;
import lombok.Data;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
public class HolidayResponse {

    private Long id;
    private String holidayName;
    private LocalDate date;
    private String dayOfWeek;

    public HolidayResponse( Holiday holiday ) {
        this.id = holiday.getId();
        this.holidayName = holiday.getHolidayName();
        this.date = holiday.getDate();
        this.dayOfWeek = holiday.getDate().getDayOfWeek()
                .getDisplayName( TextStyle.FULL, Locale.ENGLISH );
    }
}
