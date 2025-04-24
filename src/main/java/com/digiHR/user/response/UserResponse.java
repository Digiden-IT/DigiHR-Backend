package com.digiHR.user.response;

import com.digiHR.user.*;
import com.digiHR.user.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String employeeCode;
    private String designation;
    private String dateOfBirth;
    private String address;
    private String department;
    private String role;
    private String bloodGroup;
    private String gender;
    private String employeeType;
    private String dateOfJoining;

    public UserResponse( User user ) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.employeeCode = user.getEmployeeCode();
        this.employeeType = user.getEmployeeType().getvalue();
        this.designation = user.getDesignation();
        this.dateOfBirth = user.getDateOfBirth();
        this.address = user.getAddress();
        this.department = user.getDepartment().getvalue();
        this.gender = user.getGender().getvalue();
        this.role = user.getRole().getvalue();
        this.bloodGroup = user.getBloodGroup().getvalue();
        this.dateOfJoining = user.getDateOfJoining();
    }
}
