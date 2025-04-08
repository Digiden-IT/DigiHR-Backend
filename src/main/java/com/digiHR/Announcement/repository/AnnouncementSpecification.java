package com.digiHR.Announcement.repository;

import com.digiHR.Announcement.model.Announcement;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class AnnouncementSpecification {

    public static Specification<Announcement> filterByPostedBy( Long postedById ) {
        if ( postedById == null ) {
            return Specification.where(null );
        }
        return (root, query, criteriaBuilder ) -> criteriaBuilder.equal( root.get( "postedBy" ).get( "id" ), postedById );
    }

    public static Specification<Announcement> filterByAnnouncementDateRange( LocalDateTime startDate, LocalDateTime endDate ) {
        if ( startDate == null || endDate == null ) {
            return Specification.where(null );
        }
        return (root, query, criteriaBuilder ) -> criteriaBuilder.between( root.get( "announcementDate" ), startDate, endDate );
    }
}
