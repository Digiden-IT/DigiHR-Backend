package com.digiHR.leavecountsetting.service;


import com.digiHR.leavecountsetting.Request.LeaveCountSetting;
import com.digiHR.leavecountsetting.model.Setting;
import com.digiHR.leavecountsetting.repository.SettingRepository;
import com.digiHR.user.repository.UserRepository;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class SettingService {

    private final SettingRepository settingRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveLeaveSetting( LeaveCountSetting setting ) {

        String json = new Gson().toJson( setting );

        Setting entity = new Setting();
        entity.setLeaveCountSetting( json );

        settingRepository.save( entity );

        userRepository.updateAllActiveUserLeaves(
                setting.getTotalCasualLeaves(),
                setting.getTotalSickLeaves(),
                setting.getTotalVacationLeaves()
        );
    }

}
