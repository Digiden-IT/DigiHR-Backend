package com.digiHR.leave.service;

import com.digiHR.leave.LeaveReason;
import com.digiHR.leave.RequestStatus;
import com.digiHR.leave.model.Leave;
import com.digiHR.leave.repository.LeaveRepository;
import com.digiHR.leave.repository.LeaveSpecification;
import com.digiHR.leave.request.AddLeaveRequest;
import com.digiHR.leave.request.GetLeaveRequest;
import com.digiHR.leave.response.FilterOptionResponse;
import com.digiHR.leave.response.LeaveResponse;
import com.digiHR.user.Department;
import com.digiHR.user.EmployeeType;
import com.digiHR.user.model.User;
import com.digiHR.user.service.LoggedInUserService;
import com.digiHR.utility.response.PaginatedApiResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.digiHR.leave.repository.LeaveSpecification.filterByRequestedBy;

@Service
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final LoggedInUserService loggedInUserService;

    public PaginatedApiResponse<List<LeaveResponse>> getLeaves( GetLeaveRequest request, Pageable pageable ) {

        Specification<Leave> leaveSpecification = getLeaveSpecification( request );
        Page<Leave> leavePage = leaveRepository.findAll( leaveSpecification, pageable );

        List<LeaveResponse> leaveResponses = leavePage.stream()
                .map( LeaveResponse::new ) // Assuming you have a constructor or mapper
                .toList();

        return new PaginatedApiResponse<>( leaveResponses, pageable.getPageNumber(),
                leavePage.getTotalPages(), leavePage.getTotalElements() );
    }

    private Specification<Leave> getLeaveSpecification( GetLeaveRequest request ) {
        return LeaveSpecification.filterByRequestedBy( request.getRequestedBy() );
    }

    public LeaveResponse applyLeave( AddLeaveRequest request ) {

        User user = loggedInUserService.getLoginUser();
        System.out.println(user.getName());
        Leave leave = new Leave( request, user );
        leave = leaveRepository.save( leave );

        return new LeaveResponse( leave );
    }

    public FilterOptionResponse getFilterOptions() {

        List<Department> departments = List.of( Department.values() );
        List<RequestStatus> requestStatus = List.of( RequestStatus.values() );
        List<LeaveReason> leaveReason = List.of( LeaveReason.values() );
        return new FilterOptionResponse( departments, leaveReason, requestStatus );
    }
}
