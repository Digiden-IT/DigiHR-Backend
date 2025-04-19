package com.digiHR.leave.request;

import com.digiHR.leave.RequestStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateLeaveStatusRequest {
    private RequestStatus requestStatus;
}
