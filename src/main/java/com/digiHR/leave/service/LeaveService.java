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
import com.digiHR.user.Department;
import com.digiHR.user.model.User;
import com.digiHR.user.service.LoggedInUserService;
import com.digiHR.utility.response.EnumResponse;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final LoggedInUserService loggedInUserService;

    @PersistenceContext
    private final EntityManager entityManager;

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
        return LeaveSpecification.filterByRequestedBy( request.getRequestedBy() )
                .and(LeaveSpecification.filterByRequestStatus(request.getRequestStatus()))
                .and(LeaveSpecification.filterByLeaveReason(request.getLeaveReason()));
    }

    public LeaveResponse applyForLeave( AddLeaveRequest request ) {

        User user = loggedInUserService.getLoginUser();
        Leave leave = new Leave( request, user );
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

        List<EnumResponse> departments = Arrays.stream( Department.values() )
                .map( department -> new EnumResponse( department.name(), department.toString()) )
                .collect( Collectors.toList() );

        List<EnumResponse> leaveReasons = Arrays.stream( LeaveReason.values() )
                .map( reason -> new EnumResponse( reason.name(), reason.toString()) )
                .collect( Collectors.toList() );

        List<EnumResponse> requestStatuses = Arrays.stream( RequestStatus.values() )
                .map( status -> new EnumResponse( status.name(), status.toString()) )
                .collect( Collectors.toList() );

        return new FilterOptionResponse( departments, leaveReasons, requestStatuses );
    }


    /*public UserLeaveSummaryResponse getLeaveSummaryForUser() {
        User user = loggedInUserService.getLoginUser();
        int totalLeave = leaveCountSettingRepository.findByUser( user )
                .map( Setting::getTotalLeave )
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
