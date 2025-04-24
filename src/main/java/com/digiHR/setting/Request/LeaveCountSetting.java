package com.digiHR.setting.Request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveCountSetting {
    private Integer totalCasualLeaves;
    private Integer totalSickLeaves;
    private Integer totalVacationLeaves;
}
