package com.digiHR.leave.controller;

import com.digiHR.leave.repository.LeaveRepository;
import com.digiHR.leave.request.AddLeaveRequest;
import com.digiHR.leave.request.GetLeaveRequest;
import com.digiHR.leave.request.UpdateLeaveStatusRequest;
import com.digiHR.leave.response.FilterOptionResponse;
import com.digiHR.leave.response.LeaveResponse;
import com.digiHR.leave.response.UserLeaveSummaryResponse;
import com.digiHR.leave.service.LeaveService;
import com.digiHR.utility.response.PaginatedApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor( onConstructor_ = @Autowired )
@RequestMapping( "/leaves" )
public class LeaveController {

    private final LeaveService leaveService;
    private final LeaveRepository leaveRepository;

    @GetMapping
    public PaginatedApiResponse<List<LeaveResponse>> getLeaves( GetLeaveRequest request, Pageable pageable ) {
        return leaveService.getLeaves( request, pageable );
    }

    @PostMapping
    public ResponseEntity<LeaveResponse> applyForLeave( @RequestBody AddLeaveRequest request ) {
        LeaveResponse response = leaveService.applyForLeave( request );
        return ResponseEntity.ok( response );
    }

    @PatchMapping( "/{id}" )
    public ResponseEntity<LeaveResponse> updateLeaveStatus( @PathVariable Long id, @RequestBody UpdateLeaveStatusRequest request ) {
        LeaveResponse updatedLeaveStatus = leaveService.updateLeaveStatus( id, request );
        return ResponseEntity.ok( updatedLeaveStatus );
    }

    @GetMapping( "/filter-options" )
    public ResponseEntity<FilterOptionResponse> getFilterOptions() {
        FilterOptionResponse options = leaveService.getFilterOptions();
        return ResponseEntity.ok( options );
    }

    @GetMapping( "/leave-summary" )
    public ResponseEntity<UserLeaveSummaryResponse> getLeaveSummaryForLoggedInUser() {
        UserLeaveSummaryResponse leaveSummary = leaveService.getLeaveSummaryForUser();
        return ResponseEntity.ok( leaveSummary );
    }


}
