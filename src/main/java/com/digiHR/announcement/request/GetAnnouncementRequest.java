package com.digiHR.announcement.request;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class GetAnnouncementRequest {
    private Long postedBy;
    private LocalDateTime announcementDateStart;
    private LocalDateTime announcementDateEnd;
}
