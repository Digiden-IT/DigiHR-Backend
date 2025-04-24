package com.digiHR.setting.controller;

import com.digiHR.setting.Request.LeaveCountSetting;
import com.digiHR.setting.response.LeaveCountSettingResponse;
import com.digiHR.setting.service.SettingService;
import com.digiHR.utility.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor( onConstructor_ = @Autowired )
@RequestMapping( "/setting" )

public class SettingController {

    private final SettingService settingService;

    @PostMapping("/leave-count")
    public ApiResponse<?> saveLeaveSetting( @RequestBody LeaveCountSetting setting ) {
        settingService.saveLeaveSetting( setting );
        return new ApiResponse<>( "Leave setting saved successfully", 200 );
    }

    @GetMapping( "/leave-count" )
    public LeaveCountSettingResponse getLeaveSetting() {
        return settingService.getLeaveSetting();
    }
}
