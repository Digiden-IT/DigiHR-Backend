package com.digiHR.leavecountsetting.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LeaveCountSetting {

    private Integer totalCasualLeaves;
    private Integer totalSickLeaves;
    private Integer totalVacationLeaves;
}
