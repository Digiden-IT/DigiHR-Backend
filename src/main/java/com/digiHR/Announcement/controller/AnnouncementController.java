package com.digiHR.Announcement.controller;

import com.digiHR.Announcement.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.digiHR.Announcement.service.AnnouncementService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/announcements")
public class AnnouncementController {


    @RequestMapping("/")
    public String index() {
        return "Hello World";

    }
    @Autowired
    private  AnnouncementService announcementService;

    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> ann = announcementService. getAllAnnouncements();
        return ResponseEntity.ok( ann );
    }

    @PostMapping
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        Announcement ann =announcementService.createAnnouncement(announcement);
        return ResponseEntity.ok( ann );
    }

    @PutMapping
    public ResponseEntity<Announcement> updateAnnouncement(@RequestBody Announcement announcement) {
        Announcement ann= announcementService. updateAnnouncement(announcement);
        return ResponseEntity.ok( ann );
    }
    @DeleteMapping
    public void deleteAnnouncement(@RequestBody Announcement ann){
        announcementService.deleteAnnouncement(ann);
    }

}
