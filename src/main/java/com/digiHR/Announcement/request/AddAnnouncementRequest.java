package com.digiHR.Announcement.request;

import lombok.Data;

import java.util.Date;

@Data
public class AddAnnouncementRequest {
    private String title;
    private String description;
}
