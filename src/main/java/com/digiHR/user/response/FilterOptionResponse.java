package com.digiHR.user.response;


import com.digiHR.user.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterOptionResponse {

    private List<Department> departments;
    private List<Role> roles;
    private List<EmployeeType> employeeTypes;
    private List<BloodGroup> bloodGroups;
    private List<Gender> genders;


}
