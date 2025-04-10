package com.digiHR.announcement.controller;

import com.digiHR.announcement.request.AddAnnouncementRequest;
import com.digiHR.announcement.request.GetAnnouncementRequest;
import com.digiHR.announcement.request.UpdateAnnouncementRequest;
import com.digiHR.announcement.response.AnnouncementResponse;
import com.digiHR.utility.response.PaginatedApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.digiHR.announcement.service.AnnouncementService;


import java.util.List;

@RestController
@RequiredArgsConstructor( onConstructor_ = @Autowired )
@RequestMapping( "/announcements" )
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    public PaginatedApiResponse<List<AnnouncementResponse>> getAllAnnouncements( GetAnnouncementRequest request, Pageable pageable ) {
        return announcementService.getAllAnnouncements( request, pageable );
    }

    @PostMapping
    public ResponseEntity<AnnouncementResponse> createAnnouncement( @RequestBody AddAnnouncementRequest request ) {
        AnnouncementResponse response =announcementService.createAnnouncement( request );
        return ResponseEntity.ok( response );
    }


    @PatchMapping( "/{id}" )
    public ResponseEntity<AnnouncementResponse> updateAnnouncement( @PathVariable Long id, @RequestBody UpdateAnnouncementRequest request ) {
        AnnouncementResponse updatedAnnouncement = announcementService.updateAnnouncement( id, request );
        return ResponseEntity.ok( updatedAnnouncement );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<String> deleteAnnouncement( @PathVariable Long id ) {
        announcementService.deleteAnnouncement( id );
        return ResponseEntity.ok( "announcement deleted successfully" );
    }


}
