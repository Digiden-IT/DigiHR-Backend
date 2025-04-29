package com.digiHR.user.response;

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
    private String dateOfJoining;
    private String department;
    private String role;
    private String bloodGroup;
    private String gender;
    private String employeeType;

    public UserResponse( User user ) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.employeeCode = user.getEmployeeCode();
        this.designation = user.getDesignation();
        this.dateOfBirth = user.getDateOfBirth();
        this.address = user.getAddress();
        this.dateOfJoining = user.getDateOfJoining();
        this.department = user.getDepartment() != null ? user.getDepartment().name() : null;
        this.role = user.getRole() != null ? user.getRole().name() : null;
        this.bloodGroup = user.getBloodGroup() != null ? user.getBloodGroup().name() : null;
        this.gender = user.getGender() != null ? user.getGender().name() : null;
        this.employeeType = user.getEmployeeType() != null ? user.getEmployeeType().name() : null;
    }
}
