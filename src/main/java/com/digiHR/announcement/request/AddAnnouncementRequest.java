package com.digiHR.announcement.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AddAnnouncementRequest {
    private String title;
    private String description;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date announcementDate;
}
