package com.digiHR.announcement.repository;

import com.digiHR.announcement.model.Announcement;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;
import java.util.Date;

public class AnnouncementSpecification {

    public static Specification<Announcement> filterByAuthor( Long postedById ) {
        if ( postedById == null )
            return Specification.where(null );

        return ( root, query, criteriaBuilder ) -> criteriaBuilder.equal( root.get( "postedBy" ).get( "id" ), postedById );
    }

    public static Specification<Announcement> filterByAnnouncementDateRange( Date startDate, Date endDate ) {
        if ( startDate == null || endDate == null )
            return Specification.where(null );

        return ( root, query, criteriaBuilder ) -> criteriaBuilder.between( root.get( "announcementDate" ), startDate, endDate );
    }
}
