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
import com.digiHR.leavecountsetting.Request.LeaveCountSetting;
import com.digiHR.leavecountsetting.model.Setting;
import com.digiHR.leavecountsetting.repository.SettingRepository;
import com.digiHR.user.Department;
import com.digiHR.user.model.User;
import com.digiHR.user.service.LoggedInUserService;
import com.digiHR.utility.exceptions.NotFoundException;
import com.digiHR.utility.response.EnumResponse;
import com.digiHR.utility.response.PaginatedApiResponse;
import com.google.gson.Gson;
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
    private final SettingRepository settingRepository;

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
                .and( LeaveSpecification.filterByRequestStatus( request.getRequestStatus() ) )
                .and( LeaveSpecification.filterByLeaveReason( request.getLeaveReason() ) );
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

        Leave updatedLeave = leaveRepository.save( leave );
        return new LeaveResponse( updatedLeave);
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

    @Transactional
    public UserLeaveSummaryResponse getLeaveSummaryForUser() {
        User user = loggedInUserService.getLoginUser();

        Setting setting = settingRepository.findTopByOrderByIdDesc()
                .orElseThrow( () -> new NotFoundException( "Leave Setting", 1L ) );

        Gson gson = new Gson();
        LeaveCountSetting leaveCountSetting = gson.fromJson( setting.getLeaveCountSetting(), LeaveCountSetting.class );

        Integer totalLeave =  leaveCountSetting.getTotalCasualLeaves() + leaveCountSetting.getTotalSickLeaves() + leaveCountSetting.getTotalVacationLeaves() ;
        Integer approvedLeaveCount = leaveRepository.sumLeaveDays( user.getId(), RequestStatus.APPROVED.toString() );
        Integer pendingLeaveCount = leaveRepository.sumLeaveDays( user.getId(), RequestStatus.PENDING.toString() );
        Integer availableLeave = totalLeave - approvedLeaveCount - pendingLeaveCount;

        return new UserLeaveSummaryResponse( totalLeave, approvedLeaveCount, pendingLeaveCount, availableLeave );
    }
}
