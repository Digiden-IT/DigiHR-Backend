package com.digiHR.announcement.service;

import com.digiHR.announcement.model.Announcement;
import com.digiHR.announcement.repository.AnnouncementRepository;
import com.digiHR.announcement.request.AddAnnouncementRequest;
import com.digiHR.announcement.request.GetAnnouncementRequest;
import com.digiHR.announcement.request.UpdateAnnouncementRequest;
import com.digiHR.announcement.response.AnnouncementResponse;
import com.digiHR.user.model.User;
import com.digiHR.user.service.LoggedInUserService;
import com.digiHR.utility.exceptions.NotFoundException;
import com.digiHR.utility.response.PaginatedApiResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.digiHR.announcement.repository.AnnouncementSpecification.filterByAnnouncementDateRange;
import static com.digiHR.announcement.repository.AnnouncementSpecification.filterByAuthor;

@Service
@RequiredArgsConstructor( onConstructor_ = @Autowired)
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final LoggedInUserService loggedInUserService;

    @PersistenceContext
    private final EntityManager entityManager;

    public PaginatedApiResponse<List<AnnouncementResponse>> getAllAnnouncements( GetAnnouncementRequest request, Pageable pageable ) {

        Specification<Announcement> announcementSpecification = getAnnouncementSpecification( request );
        Page<Announcement> announcementPage = announcementRepository.findAll( announcementSpecification, pageable );
        List<AnnouncementResponse> announcementResponses = announcementPage.stream()
                .map( AnnouncementResponse::new )
                .toList();

        return new PaginatedApiResponse<>( announcementResponses, pageable.getPageNumber(),
                announcementPage.getTotalPages(), announcementPage.getTotalElements() );
    }

    private Specification<Announcement> getAnnouncementSpecification( GetAnnouncementRequest request ) {
        return filterByAuthor( request.getPostedBy() )
                .and( filterByAnnouncementDateRange( request.getAnnouncementDateStart(), request.getAnnouncementDateEnd() ) );
    }


    @Transactional
    public void deleteAnnouncement( Long id ) {
        if ( !announcementRepository.existsById( id ) ) {
            throw new NotFoundException( "announcement", id );
        }
        announcementRepository.deleteById( id );
    }


    public AnnouncementResponse createAnnouncement( AddAnnouncementRequest request ) {

        User user = loggedInUserService.getLoginUser();
        Announcement announcement = new Announcement( request, user );
        announcement = announcementRepository.save( announcement );

        return new AnnouncementResponse( announcement );
    }

    @Transactional
    public AnnouncementResponse updateAnnouncement( Long id, UpdateAnnouncementRequest request ) {
        Announcement announcement = entityManager.getReference( Announcement.class, id );

        if ( request.getTitle() != null )
            announcement.setTitle( request.getTitle() );

        if ( request.getDescription() != null )
            announcement.setDescription( request.getDescription() );

        Announcement updatedAnnouncement = announcementRepository.save( announcement );
        return new AnnouncementResponse( updatedAnnouncement );
    }
}