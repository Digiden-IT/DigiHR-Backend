package com.digiHR.leavecountsetting.service;
/*
import com.digiHR.leavecountsetting.Request.AddLeaveCountSettingRequest;
import com.digiHR.leavecountsetting.model.LeaveCountSetting;
import com.digiHR.leavecountsetting.model.LeaveCountSettingAdapter;
import com.digiHR.leavecountsetting.repository.LeaveCountSettingRepository;
import com.digiHR.user.model.User;
import com.digiHR.user.repository.UserRepository;
import com.digiHR.user.request.GetUserRequest;
import com.digiHR.user.response.UserResponse;
import com.digiHR.user.service.UserService;
import com.digiHR.utility.response.PaginatedApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class LeaveCountSettingService {

    private final LeaveCountSettingRepository leaveCountSettingRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public String initializeLeaveCountForActiveUsers( AddLeaveCountSettingRequest request ) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter( LeaveCountSetting.class, new LeaveCountSettingAdapter() )
                .create();

        String totalLeaveJson = request.getTotalLeave();
        LeaveCountSetting settingFromJson = gson.fromJson( totalLeaveJson, LeaveCountSetting.class );
        String totalLeaveValue = settingFromJson.getTotalLeave();

        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setIsActive( null );
        getUserRequest.setRole( null );

        PaginatedApiResponse<List<UserResponse>> paginatedResponse = userService.getUsers( getUserRequest, Pageable.unpaged() );
        List<UserResponse> allUserResponses = paginatedResponse.getData();

        for ( UserResponse userResponse : allUserResponses ) {

            User user = userRepository.findById(userResponse.getId()).orElse( null );
            boolean exists = leaveCountSettingRepository.existsByUser(user);

            if ( !exists ) {
                LeaveCountSetting setting = new LeaveCountSetting();
                setting.setTotalLeave( totalLeaveValue );
                leaveCountSettingRepository.save( setting );
            }
        }
        return "Leave count initialized with additional fields for active users.";
    }
}*/
