package com.digiHR.Announcement.controller;

import com.digiHR.Announcement.model.Announcement;
import com.digiHR.Announcement.request.AddAnnouncementRequest;
import com.digiHR.Announcement.response.AnnouncementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.digiHR.Announcement.service.AnnouncementService;

import java.util.List;

@RestController
@RequiredArgsConstructor( onConstructor_ = @Autowired )
@RequestMapping("/announcements")
public class AnnouncementController {

    private  AnnouncementService announcementService;

    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> announcement = announcementService. getAllAnnouncements();
        return ResponseEntity.ok( announcement );
    }

    @PostMapping
    public ResponseEntity<AnnouncementResponse> createAnnouncement(@RequestBody AddAnnouncementRequest request) {
        AnnouncementResponse response =announcementService.createAnnouncement( request );
        return ResponseEntity.ok( response );
    }

    @PutMapping
    public ResponseEntity<Announcement> updateAnnouncement(@RequestBody Announcement announcement ) {
        announcement= announcementService. updateAnnouncement( announcement );
        return ResponseEntity.ok( announcement );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable long id) {
        try {
            announcementService.deleteAnnouncement(id);
            return ResponseEntity.ok("Announcement deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
