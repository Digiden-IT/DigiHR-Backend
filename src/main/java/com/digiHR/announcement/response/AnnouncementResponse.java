package com.digiHR.announcement.response;

import com.digiHR.announcement.model.Announcement;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AnnouncementResponse {
    private Long id;
    private String title;
    private String description;
    private String authorName;
    private String announcementDate;

    public AnnouncementResponse( Announcement announcement ) {
        this.id = announcement.getId();
        this.title = announcement.getTitle();
        this.description = announcement.getDescription();
        this.authorName = ( announcement.getPostedBy() != null ) ? announcement.getPostedBy().getName() : null;
        SimpleDateFormat formatter = new SimpleDateFormat( "MMM d, h:mm a" );
        this.announcementDate = formatter.format( announcement.getAnnouncementDate() );
    }

}

