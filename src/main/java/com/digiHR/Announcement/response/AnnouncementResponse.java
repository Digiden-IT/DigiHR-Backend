package com.digiHR.Announcement.response;

import com.digiHR.Announcement.model.Announcement;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AnnouncementResponse {
    private Long id;
    private String title;
    private String description;
    private String authorName;
    private LocalDateTime announcementDate;

    public AnnouncementResponse( Announcement announcement ) {
        this.id = announcement.getId();
        this.title = announcement.getTitle();
        this.description = announcement.getDescription();
        this.announcementDate = announcement.getAnnouncementDate();
        this.authorName = announcement.getPostedBy() != null
                ? announcement.getPostedBy().getName() : null;
    }

}

