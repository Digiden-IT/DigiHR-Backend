package com.digiHR.Announcement.response;

import com.digiHR.Announcement.model.Announcement;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AnnouncementResponse {
    private long id;
    private String title;
    private String description;
    private String postedBy;
    private LocalDateTime announcementDate;

    public AnnouncementResponse(Announcement announcement ){
        this.id=announcement.getId();
        this.title=announcement.getTitle();
        this.description=announcement.getDescription();
        this.postedBy=announcement.getPostedBy();
        this.announcementDate=announcement.getAnnouncementDate();
    }

}

