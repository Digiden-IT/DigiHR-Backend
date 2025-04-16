package com.digiHR.announcement.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class GetAnnouncementRequest {
    private Long postedBy;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date announcementDateStart;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date announcementDateEnd;
}
