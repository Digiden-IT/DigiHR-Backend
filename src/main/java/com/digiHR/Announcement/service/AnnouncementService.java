package com.digiHR.Announcement.service;

import com.digiHR.Announcement.model.Announcement;
import com.digiHR.Announcement.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    public List<Announcement> getAllAnnouncements() {

        return announcementRepository.findAll();
    }

    public void deleteAnnouncement(long id) {
        announcementRepository.deleteById(id);
    }

    public Announcement createAnnouncement(Announcement announcement) {
         return announcementRepository.save(announcement);
    }

    public Announcement updateAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }
}
