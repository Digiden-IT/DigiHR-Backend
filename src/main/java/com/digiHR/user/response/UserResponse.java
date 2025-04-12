package com.digiHR.user.response;

import com.digiHR.user.*;
import com.digiHR.user.model.User;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phone_number;
    private Department department;
    private Role role;
    private String employeeCode;
    private EmployeeType employeeType;
    private String designation;
    private String dateOfBirth;
    private Gender gender;
    private String address;
    private BloodGroup bloodGroup;
    private String dateOfJoining;

    public UserResponse( User user ) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.department = user.getDepartment();
        this.role = user.getRole();
        this.employeeCode = user.getEmployeeCode();
        this.employeeType = user.getEmployeeType();
        this.designation = user.getDesignation();
        this.dateOfBirth = user.getDateOfBirth();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.bloodGroup = user.getBloodGroup();
        this.dateOfJoining = user.getDateOfJoining();
    }
}
