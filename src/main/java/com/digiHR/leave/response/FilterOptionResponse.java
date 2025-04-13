package com.digiHR.leave.response;

import com.digiHR.leave.LeaveReason;
import com.digiHR.leave.RequestStatus;
import com.digiHR.user.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterOptionResponse {
    private List<Department> departments;
    private List<LeaveReason> leaveReason;
    private List<RequestStatus> requestStatus;
}
