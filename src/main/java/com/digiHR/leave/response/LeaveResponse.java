package com.digiHR.leave.response;

import com.digiHR.leave.RequestStatus;
import com.digiHR.leave.model.Leave;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveResponse {
   private Long id;
   private String employeeName;
   private LocalDate startDate;
   private LocalDate endDate;
   private String reason;
   private RequestStatus requestStatus;

   public LeaveResponse( Leave leave ) {
       this.id=leave.getId();
       this.employeeName = (leave.getEmployee() != null) ? leave.getEmployee().getName() : null;
       this.startDate=leave.getStartDate();
       this.endDate=leave.getEndDate();
       this.reason=leave.getReason();
       this.requestStatus = (leave.getRequestStatus() != null) ? leave.getRequestStatus(): null;
   }
}
