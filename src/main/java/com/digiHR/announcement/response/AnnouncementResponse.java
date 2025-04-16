package com.digiHR.announcement.response;

import com.digiHR.announcement.model.Announcement;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AnnouncementResponse {
    private Long id;
    private String title;
    private String description;
    private String authorName;
    @JsonFormat( pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING )
    private Date announcementDate;

    public AnnouncementResponse( Announcement announcement ) {
        this.id = announcement.getId();
        this.title = announcement.getTitle();
        this.description = announcement.getDescription();
        this.announcementDate = announcement.getAnnouncementDate();
        this.authorName = (announcement.getPostedBy() != null) ? announcement.getPostedBy().getName() : null;
    }

}

