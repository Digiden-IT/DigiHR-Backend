package com.digiHR.user.request;

import com.digiHR.user.Department;
import com.digiHR.user.EmployeeType;
import com.digiHR.user.Gender;
import com.digiHR.user.Role;
import lombok.Data;

@Data
public class AddUserRequest {
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String designation;
    private Department department;
    private Gender gender;
    private String bloodGroup;
    private String address;
    private EmployeeType employeeType;
    private String dateOfJoining;
    private String totalLeaves;
    private Boolean isActive;
    private Role role;
    private String refreshToken;
}
