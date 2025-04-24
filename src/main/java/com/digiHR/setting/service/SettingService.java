package com.digiHR.setting.service;


import com.digiHR.setting.Request.LeaveCountSetting;
import com.digiHR.setting.model.Setting;
import com.digiHR.setting.repository.SettingRepository;
import com.digiHR.setting.response.LeaveCountSettingResponse;
import com.digiHR.user.repository.UserRepository;
import com.digiHR.utility.exceptions.NotFoundException;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class SettingService {

    private final SettingRepository settingRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveLeaveSetting( LeaveCountSetting leaveCountSetting ) {

        String json = new Gson().toJson( leaveCountSetting );

        Setting setting = new Setting();
        setting.setLeaveCountSetting( json );
        settingRepository.save( setting );

        userRepository.updateAllActiveUserLeaves(
                leaveCountSetting.getTotalCasualLeaves(),
                leaveCountSetting.getTotalSickLeaves(),
                leaveCountSetting.getTotalVacationLeaves()
        );
    }

    @Transactional( readOnly = true )
    public LeaveCountSettingResponse getLeaveSetting() {
        Setting setting = settingRepository.findTopByOrderByIdDesc()
                .orElseThrow( () -> new NotFoundException( "Leave Setting", 1L ) );

        Gson gson = new Gson();
        LeaveCountSetting leaveCountSetting = gson.fromJson( setting.getLeaveCountSetting(), LeaveCountSetting.class );
        return new LeaveCountSettingResponse(
                leaveCountSetting.getTotalCasualLeaves(),
                leaveCountSetting.getTotalSickLeaves(),
                leaveCountSetting.getTotalVacationLeaves()
        );
    }
}
