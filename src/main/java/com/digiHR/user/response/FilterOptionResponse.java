package com.digiHR.user.response;


import com.digiHR.user.*;
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
    private List<EnumResponse> roles;
    private List<EnumResponse> employeeTypes;
    private List<EnumResponse> bloodGroups;
    private List<EnumResponse> genders;
}
