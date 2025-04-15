package com.digiHR.leave.request;

import com.digiHR.leave.LeaveReason;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddLeaveRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveReason leaveReason;
}
