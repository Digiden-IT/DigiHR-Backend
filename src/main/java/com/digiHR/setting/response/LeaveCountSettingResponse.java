package com.digiHR.setting.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaveCountSettingResponse {
    private Integer totalCasualLeaves;
    private Integer totalSickLeaves;
    private Integer totalVacationLeaves;
}
