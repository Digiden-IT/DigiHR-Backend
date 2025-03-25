package com.digiHR.Announcement.model;

import com.digiHR.Announcement.request.AddAnnouncementRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@Table (name= "announcements")
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "postedBy")
    private String postedBy;

    @Column(name = "announcementDate")
    private Date announcementDate;

    public Announcement(AddAnnouncementRequest request) {
        this.title = request.getTitle();
        this.description = request.getDescription();
    }
}
