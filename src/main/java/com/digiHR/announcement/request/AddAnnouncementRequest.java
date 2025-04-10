package com.digiHR.announcement.request;

import lombok.Data;

@Data
public class AddAnnouncementRequest {
    private String title;
    private String description;
}
