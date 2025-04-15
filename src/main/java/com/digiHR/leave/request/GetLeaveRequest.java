package com.digiHR.leave.request;

import com.digiHR.leave.LeaveReason;
import com.digiHR.leave.RequestStatus;
import lombok.Data;

@Data
public class GetLeaveRequest {
   private Long requestedBy;
   private RequestStatus requestStatus;
   private LeaveReason leaveReason;
}
