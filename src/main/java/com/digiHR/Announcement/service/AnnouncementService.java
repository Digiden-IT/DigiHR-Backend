package com.digiHR.Announcement.service;

import com.digiHR.Announcement.model.Announcement;
import com.digiHR.Announcement.repository.AnnouncementRepository;
import com.digiHR.Announcement.request.AddAnnouncementRequest;
import com.digiHR.Announcement.response.AnnouncementResponse;
import com.digiHR.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class AnnouncementService {

    private AnnouncementRepository announcementRepository;

    public List<Announcement> getAllAnnouncements() {

        return announcementRepository.findAll();
    }

    public void deleteAnnouncement(long id) {
        announcementRepository.deleteById(id);
    }

    public AnnouncementResponse createAnnouncement(AddAnnouncementRequest request) {

        Announcement announcement = new Announcement( request );
        announcement= announcementRepository.save( announcement );
        return new AnnouncementResponse( announcement );
    }

    public Announcement updateAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }
}
