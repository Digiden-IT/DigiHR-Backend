package com.digiHR.leave.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddLeaveRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
