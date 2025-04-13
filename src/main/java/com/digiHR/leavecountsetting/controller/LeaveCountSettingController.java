package com.digiHR.leavecountsetting.controller;

import com.digiHR.leavecountsetting.Request.AddLeaveCountSettingRequest;
import com.digiHR.leavecountsetting.service.LeaveCountSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor( onConstructor_ = @Autowired)
@RequestMapping( "/leavecountsetting" )

public class LeaveCountSettingController {
    private final LeaveCountSettingService leaveCountSettingService;

    @PostMapping()
    public ResponseEntity<String> initializeLeaveCountForActiveUsers( @RequestBody AddLeaveCountSettingRequest request ) {
        String response = leaveCountSettingService.initializeLeaveCountForActiveUsers( request );
        return ResponseEntity.ok( response );
    }
}
