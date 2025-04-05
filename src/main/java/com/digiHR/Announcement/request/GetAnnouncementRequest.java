package com.digiHR.Announcement.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class GetAnnouncementRequest {
    private Long postedBy;
    private LocalDateTime announcementDate;
}
