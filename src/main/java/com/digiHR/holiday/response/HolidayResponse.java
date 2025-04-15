package com.digiHR.holiday.response;

import com.digiHR.holiday.model.Holiday;
import lombok.Data;
import java.util.Calendar;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;

@Data
public class HolidayResponse {

    private Long id;
    private String holidayName;
    private String date;
    private String dayOfWeek;

    public HolidayResponse( Holiday holiday ) {
        this.id = holiday.getId();
        this.holidayName = holiday.getHolidayName();

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "MMMM dd, yyyy" );
        this.date = dateFormatter.format( holiday.getDate() );
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( holiday.getDate() );
        int dayOfWeekIndex = calendar.get( Calendar.DAY_OF_WEEK );
        this.dayOfWeek = new DateFormatSymbols().getWeekdays()[ dayOfWeekIndex ];
    }
}
