package com.digiHR.leave.service;

import com.digiHR.leave.LeaveReason;
import com.digiHR.leave.RequestStatus;
import com.digiHR.leave.model.Leave;
import com.digiHR.leave.repository.LeaveRepository;
import com.digiHR.leave.repository.LeaveSpecification;
import com.digiHR.leave.request.AddLeaveRequest;
import com.digiHR.leave.request.GetLeaveRequest;
import com.digiHR.leave.request.UpdateLeaveStatusRequest;
import com.digiHR.leave.response.FilterOptionResponse;
import com.digiHR.leave.response.LeaveResponse;
import com.digiHR.leave.response.UserLeaveSummaryResponse;
import com.digiHR.leavecountsetting.model.LeaveCountSetting;
import com.digiHR.leavecountsetting.repository.LeaveCountSettingRepository;
import com.digiHR.user.Department;
import com.digiHR.user.model.User;
import com.digiHR.user.service.LoggedInUserService;
import com.digiHR.utility.response.PaginatedApiResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final LoggedInUserService loggedInUserService;

    @PersistenceContext
    private final EntityManager entityManager;
    private final LeaveCountSettingRepository leaveCountSettingRepository;

    public PaginatedApiResponse<List<LeaveResponse>> getLeaves( GetLeaveRequest request, Pageable pageable ) {

        Specification<Leave> leaveSpecification = getLeaveSpecification( request );
        Page<Leave> leavePage = leaveRepository.findAll( leaveSpecification, pageable );

        List<LeaveResponse> leaveResponses = leavePage.stream()
                .map( LeaveResponse::new )
                .toList();

        return new PaginatedApiResponse<>( leaveResponses, pageable.getPageNumber(),
                leavePage.getTotalPages(), leavePage.getTotalElements() );
    }

    private Specification<Leave> getLeaveSpecification( GetLeaveRequest request ) {
        return LeaveSpecification.filterByRequestedBy( request.getRequestedBy() );
    }

    public LeaveResponse applyLeave( AddLeaveRequest request ) {

        User user = loggedInUserService.getLoginUser();
        Leave leave = new Leave( request, user );
        leave.setRequestStatus( RequestStatus.PENDING );
        leave = leaveRepository.save( leave );

        return new LeaveResponse( leave );
    }

    @Transactional
    public LeaveResponse updateLeaveStatus( Long id, UpdateLeaveStatusRequest request ) {
        Leave leave = entityManager.getReference( Leave.class, id );

        if ( request.getRequestStatus() != null )
            leave.setRequestStatus( request.getRequestStatus() );

        Leave updatedLeaveStatus = leaveRepository.save( leave );
        return new LeaveResponse( updatedLeaveStatus );
    }
    public FilterOptionResponse getFilterOptions() {

        List<Department> departments = List.of( Department.values() );
        List<RequestStatus> requestStatus = List.of( RequestStatus.values() );
        List<LeaveReason> leaveReason = List.of( LeaveReason.values() );
        return new FilterOptionResponse( departments, leaveReason, requestStatus );
    }

    /*public UserLeaveSummaryResponse getLeaveSummaryForUser() {
        User user = loggedInUserService.getLoginUser();
        int totalLeave = leaveCountSettingRepository.findByUser( user )
                .map( LeaveCountSetting::getTotalLeave )
                .orElse( 0 );

        List<Leave> approvedLeaves = leaveRepository.findByEmployeeAndRequestStatus(user, RequestStatus.APPROVED);
        List<Leave> pendingLeaves  = leaveRepository.findByEmployeeAndRequestStatus(user, RequestStatus.PENDING);

        int usedLeave = approvedLeaves.stream()
                .mapToInt( leave -> ( int ) ChronoUnit.DAYS.between( leave.getStartDate(), leave.getEndDate()) + 1 )
                .sum();

        int pendingLeave = pendingLeaves.stream()
                .mapToInt( leave -> ( int ) ChronoUnit.DAYS.between( leave.getStartDate(), leave.getEndDate()) + 1 )
                .sum();
        int availableLeave = totalLeave - usedLeave - pendingLeave;

        return new UserLeaveSummaryResponse( usedLeave, pendingLeave, availableLeave );
    }*/
}
