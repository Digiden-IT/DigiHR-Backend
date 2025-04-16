package com.digiHR.leave.request;

import com.digiHR.leave.LeaveReason;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AddLeaveRequest {
    private Date startDate;
    private Date endDate;
    private LeaveReason leaveReason;
}
