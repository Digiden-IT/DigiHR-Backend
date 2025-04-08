package com.digiHR.Announcement.model;

import com.digiHR.Announcement.request.AddAnnouncementRequest;
import com.digiHR.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table ( name= "announcements" )
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "title" )
    private String title;

    @Column( name = "description" )
    private String description;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "author_id", foreignKey = @ForeignKey( name = "fk_announcement_author_id" ) )
    private User postedBy;

    @Column( name = "announcementDate" )
    private LocalDateTime announcementDate;

    public Announcement( AddAnnouncementRequest request, User user ) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.postedBy = user;
        this.announcementDate = LocalDateTime.now();
    }
}
