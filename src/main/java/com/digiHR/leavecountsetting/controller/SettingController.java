package com.digiHR.leavecountsetting.controller;

import com.digiHR.leavecountsetting.Request.LeaveCountSetting;
import com.digiHR.leavecountsetting.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor( onConstructor_ = @Autowired )
@RequestMapping( "/leavecountsetting" )

public class SettingController {

    private final SettingService settingService;

    @PostMapping()
    public ResponseEntity<?> saveLeaveSetting( @RequestBody LeaveCountSetting setting ) {
        settingService.saveLeaveSetting( setting );
        return ResponseEntity.ok( "Saved" );
    }
}
