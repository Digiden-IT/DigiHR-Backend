package com.digiHR.Announcement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@Table (name= "announcments")
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = " Title")
    private String title;

    @Column(name = "Content")
    private String Description;

    @Column(name = " Posted By")
    private String author;

    @Column(name = "Date")
    private Date date;
}
