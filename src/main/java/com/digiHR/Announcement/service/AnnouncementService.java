package com.digiHR.Announcement.service;

import com.digiHR.Announcement.model.Announcement;
import com.digiHR.Announcement.repository.AnnouncementRepository;
import com.digiHR.Announcement.request.AddAnnouncementRequest;
import com.digiHR.Announcement.response.AnnouncementResponse;
import com.digiHR.user.response.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class AnnouncementService {

    private AnnouncementRepository announcementRepository;

    public List<Announcement> getAllAnnouncements() {

        return announcementRepository.findAll();
    }

    @Transactional
    public void deleteAnnouncement(long id) {
        if (!announcementRepository.existsById(id)) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        announcementRepository.deleteById(id);
    }

    public AnnouncementResponse createAnnouncement(AddAnnouncementRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();

        Announcement announcement = new Announcement(request, loggedInUser);
        announcement = announcementRepository.save(announcement);

        return new AnnouncementResponse( announcement );
    }

    public Announcement updateAnnouncement(Announcement announcement) {
        return announcementRepository.save( announcement );
    }
}
