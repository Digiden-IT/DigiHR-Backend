package com.digiHR.holiday.service;

import com.digiHR.holiday.model.Holiday;
import com.digiHR.holiday.repository.HolidayRepository;
import com.digiHR.holiday.request.AddHolidayRequest;
import com.digiHR.holiday.request.GetHolidayRequest;
import com.digiHR.holiday.response.HolidayResponse;
import com.digiHR.utility.exceptions.NotFoundException;
import com.digiHR.utility.response.PaginatedApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import static com.digiHR.holiday.repository.HolidaySpecification.*;

@Service
@RequiredArgsConstructor( onConstructor_ = {@Autowired} )
public class HolidayService {

    private final HolidayRepository holidayRepository;

    public HolidayResponse addHoliday( AddHolidayRequest addHolidayRequest ) {
        Holiday holiday = new Holiday();
        holiday.setHolidayName( addHolidayRequest.getHolidayName() );
        holiday.setDate( addHolidayRequest.getDate() );
        holiday = holidayRepository.save( holiday );
        return new HolidayResponse( holiday );
    }

    public HolidayResponse getHoliday( Long id ) {
        return new HolidayResponse(
                Objects.requireNonNull( holidayRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( "holiday", id ) ) )
        );
    }

    public PaginatedApiResponse<List<HolidayResponse>> getHolidays( GetHolidayRequest request, Pageable pageable ) {

        Specification<Holiday> holidaySpecification = getHolidaySpecification ( request );
        Page<Holiday> holidayPage = holidayRepository.findAll( holidaySpecification, pageable);

        List<HolidayResponse> holidayResponseList = holidayPage.stream()
                .map( HolidayResponse ::new )
                .toList();

        return new PaginatedApiResponse<>(
                holidayResponseList,
                pageable.getPageNumber(),
                holidayPage.getTotalPages(),
                holidayPage.getTotalElements()
        );
    }

    private Specification<Holiday> getHolidaySpecification ( GetHolidayRequest request ) {

        if (Objects.equals( request.getIsUpcoming(), true) )
            return filterByIsUpcoming( request.getIsUpcoming() );
        return filterByHolidayDateRange( request.getHolidayDateStart(), request.getHolidayDateEnd() );
    }

    @Transactional
    public void deleteHoliday( Long id ) {
        Holiday holiday = holidayRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( "Holiday", id ) );
        holidayRepository.delete( holiday );
    }
}
