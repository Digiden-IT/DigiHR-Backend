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
    private String phone_number;
    private String employeeCode;
    private String designation;
    private String dateOfBirth;
    private String address;
    private Department department;
    private Role role;
    private BloodGroup bloodGroup;
    private Gender gender;
    private EmployeeType employeeType;
    private String dateOfJoining;

    public UserResponse( User user ) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone_number = user.getPhoneNumber();
        this.employeeCode = user.getEmployeeCode();
        this.employeeType = user.getEmployeeType();
        this.designation = user.getDesignation();
        this.dateOfBirth = user.getDateOfBirth();
        this.address = user.getAddress();
        this.department = user.getDepartment();
        this.gender = user.getGender();
        this.role = user.getRole();
        this.bloodGroup = user.getBloodGroup();
        this.dateOfJoining = user.getDateOfJoining();
    }
}
