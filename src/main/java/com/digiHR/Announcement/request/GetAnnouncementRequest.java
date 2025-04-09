package com.digiHR.Announcement.request;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class GetAnnouncementRequest {
    private Long postedBy;
    private LocalDateTime announcementDateStart;
    private LocalDateTime announcementDateEnd;
}
