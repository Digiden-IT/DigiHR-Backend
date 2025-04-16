package com.digiHR.leave.response;

import com.digiHR.leave.LeaveReason;
import com.digiHR.leave.RequestStatus;
import com.digiHR.leave.model.Leave;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
public class LeaveResponse {
   private Long id;
   private String employeeName;

   @JsonFormat( pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING )
   private Date startDate;

   @JsonFormat( pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING )
   private Date endDate;
   private LeaveReason leaveReason;
   private RequestStatus requestStatus;

   public LeaveResponse( Leave leave ) {
       this.id=leave.getId();
       this.employeeName = ( leave.getEmployee() != null ) ? leave.getEmployee().getName() : null;
       this.startDate = leave.getStartDate();
       this.endDate= leave.getEndDate();
       this.leaveReason=leave.getLeaveReason();
       this.requestStatus = leave.getRequestStatus();
   }
}
