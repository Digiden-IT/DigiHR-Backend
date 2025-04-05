package com.digiHR.Announcement.service;

import com.digiHR.Announcement.model.Announcement;
import com.digiHR.Announcement.repository.AnnouncementRepository;
import com.digiHR.Announcement.request.AddAnnouncementRequest;
import com.digiHR.Announcement.request.GetAnnouncementRequest;
import com.digiHR.Announcement.response.AnnouncementResponse;
import com.digiHR.user.model.User;
import com.digiHR.user.repository.UserRepository;
import com.digiHR.user.response.UserResponse;
import com.digiHR.user.service.LoggedInUserService;
import com.digiHR.utility.response.PaginatedApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class AnnouncementService {

    private AnnouncementRepository announcementRepository;
    private  LoggedInUserService loggedInUserService;
    public PaginatedApiResponse<List<AnnouncementResponse>> getAllAnnouncements(GetAnnouncementRequest request, Pageable pageable ) {

        Specification<Announcement> announcementSpecification = getAnnouncementSpecification( request );

        Page<Announcement> announcementPage = announcementRepository.findAll( announcementSpecification, pageable );

        List<AnnouncementResponse> announcementResponses = announcementPage.stream()
                .map( AnnouncementResponse::new )
                .toList();

        return new PaginatedApiResponse<>( announcementResponses, pageable.getPageNumber(),
                announcementPage.getTotalPages(), announcementPage.getTotalElements() );
    }

    private Specification<Announcement> getAnnouncementSpecification( GetAnnouncementRequest request ) {
        Specification<Announcement> announcementSpecification = Specification.where( null );

        if ( request.getPostedBy() != null ) {
            announcementSpecification = announcementSpecification.and(( root, query, cb ) ->
                    cb.equal( root.get("postedBy").get( "id" ), request.getPostedBy()) );
        }

        if ( request.getAnnouncementDate() != null ) {
            announcementSpecification = announcementSpecification.and(( root, query, cb ) ->
                    cb.equal( root.get( "announcementDate" ), request.getAnnouncementDate()) );
        }

        return announcementSpecification;
    }

    @Transactional
    public void deleteAnnouncement(long id) {
        if (!announcementRepository.existsById(id)) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        announcementRepository.deleteById(id);
    }

    public AnnouncementResponse createAnnouncement(AddAnnouncementRequest request) {
        User user= loggedInUserService.getLoginUser();

        Announcement announcement = new Announcement(request, user);
        announcement = announcementRepository.save(announcement);

        // Return response with author name
        return new AnnouncementResponse(announcement);
    }



    public Announcement updateAnnouncement(Announcement announcement) {
        return announcementRepository.save( announcement );
    }
}
