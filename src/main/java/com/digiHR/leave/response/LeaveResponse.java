package com.digiHR.leave.response;

import com.digiHR.leave.LeaveReason;
import com.digiHR.leave.RequestStatus;
import com.digiHR.leave.model.Leave;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class LeaveResponse {
   private Long id;
   private String employeeName;
   private String requestDate;

   @JsonFormat( pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING )
   private Date startDate;

   @JsonFormat( pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING )
   private Date endDate;
   private int numberOfDays;
   private String leaveReason;
   private String requestStatus;

   public LeaveResponse( Leave leave ) {
       this.id=leave.getId();
       this.employeeName = ( leave.getEmployee() != null ) ? leave.getEmployee().getName() : null;
       SimpleDateFormat formatter = new SimpleDateFormat( "MMM d, yyyy" );
       this.requestDate = formatter.format( leave.getRequestDate() );
       this.startDate = leave.getStartDate();
       this.endDate = leave.getEndDate();
       this.numberOfDays = calculateNumberOfDays( leave.getStartDate(), leave.getEndDate() );
       this.leaveReason = leave.getLeaveReason() != null ? leave.getLeaveReason().name() : null;
       this.requestStatus = leave.getRequestStatus() != null ? leave.getRequestStatus().name() : null;
   }
    private int calculateNumberOfDays( Date start, Date end ) {
        long diff = end.getTime() - start.getTime();
        long days = ( diff / (1000 * 60 * 60 * 24) ) + 1;
        return (int) days;
    }
}
