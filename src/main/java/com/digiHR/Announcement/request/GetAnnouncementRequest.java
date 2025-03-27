package com.digiHR.Announcement.request;

import lombok.Data;

import java.util.Date;

@Data
public class GetAnnouncementRequest {
    private String postedBy;
    private Date announcmentDate;
}
