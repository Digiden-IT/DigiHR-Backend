package com.digiHR.holiday.controller;

import com.digiHR.holiday.request.AddHolidayRequest;
import com.digiHR.holiday.request.GetHolidayRequest;
import com.digiHR.holiday.response.HolidayResponse;
import com.digiHR.holiday.service.HolidayService;
import com.digiHR.utility.response.ApiResponse;
import com.digiHR.utility.response.PaginatedApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping( "/holidays" )
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class HolidayController {

    private final HolidayService holidayService;

    @PostMapping
    public ResponseEntity<HolidayResponse> addHoliday( @RequestBody AddHolidayRequest request ) {
        HolidayResponse response = holidayService.addHoliday( request );
        return ResponseEntity.ok( response );
    }

    @GetMapping
    public ResponseEntity<?> getAllHolidays( GetHolidayRequest request, Pageable pageable ) {
        PaginatedApiResponse<List<HolidayResponse>> holidayResponses = holidayService.getHolidays( request, pageable);
        return ResponseEntity.ok( holidayResponses );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<HolidayResponse> getHoliday( @PathVariable Long id ) {
        HolidayResponse holiday = holidayService.getHoliday( id );
        return ResponseEntity.ok( holiday );
    }

    @DeleteMapping( "/{id}" )
    public ApiResponse<?> deleteHoliday( @PathVariable Long id ) {
        holidayService.deleteHoliday( id );
        return ApiResponse.success( "Holiday deleted successfully" );
    }
}
