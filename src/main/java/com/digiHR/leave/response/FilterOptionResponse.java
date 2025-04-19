package com.digiHR.leave.response;

import com.digiHR.leave.LeaveReason;
import com.digiHR.leave.RequestStatus;
import com.digiHR.user.Department;
import com.digiHR.utility.response.EnumResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterOptionResponse {

    private List<EnumResponse> departments;
    private List<EnumResponse> leaveReason;
    private List<EnumResponse> requestStatus;
}
