package com.digiHR.leave.request;

import com.digiHR.leave.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddLeaveRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
