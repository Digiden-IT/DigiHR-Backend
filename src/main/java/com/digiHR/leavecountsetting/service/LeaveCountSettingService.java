package com.digiHR.leavecountsetting.service;

import com.digiHR.leavecountsetting.Request.AddLeaveCountSettingRequest;
import com.digiHR.leavecountsetting.model.LeaveCountSetting;
import com.digiHR.leavecountsetting.repository.LeaveCountSettingRepository;
import com.digiHR.user.model.User;
import com.digiHR.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor( onConstructor_ = @Autowired)
public class LeaveCountSettingService {

    private final LeaveCountSettingRepository leaveCountSettingRepository;
    private final UserRepository userRepository;

    public String initializeLeaveCountForActiveUsers( AddLeaveCountSettingRequest request ) {
        List<User> activeUsers = userRepository.findByIsActiveTrue();

        for ( User user : activeUsers ) {
            boolean exists = leaveCountSettingRepository.existsByUser( user );

            if (!exists) {
                LeaveCountSetting setting = new LeaveCountSetting();
                setting.setUser( user );
                setting.setTotalLeave( request.getTotalLeave() );
                leaveCountSettingRepository.save( setting );
            }
        }

        return "Leave count initialized with additional fields for active users.";
    }
}
