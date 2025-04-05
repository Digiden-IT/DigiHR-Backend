package com.digiHR.user.request;

import com.digiHR.user.*;
import lombok.Data;

@Data
public class AddUserRequest {
    private String name;
    private String password;
    private String email;
    private String dateOfBirth;
    private String phoneNumber;
    private String designation;
    private Department department;
    private Gender gender;
    private BloodGroup bloodGroup;
    private String address;
    private EmployeeType employeeType;
    private String dateOfJoining;
    private String totalLeaves;
    private Boolean isActive;
    private Role role;
    private String refreshToken;

}
