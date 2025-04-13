package com.digiHR.leave.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLeaveSummaryResponse {
    private int usedLeave;
    private int pendingLeave;
    private int availableLeave;

}
