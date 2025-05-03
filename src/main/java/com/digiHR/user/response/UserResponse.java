package com.digiHR.user.response;

import com.digiHR.user.model.User;
import com.digiHR.utility.response.EnumResponse;
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
    private EnumResponse department;
    private EnumResponse role;
    private EnumResponse bloodGroup;
    private EnumResponse gender;
    private EnumResponse employeeType;

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
        this.department = user.getDepartment() != null ?
                new EnumResponse( user.getDepartment().getValue(), user.getDepartment().name() ) : null;
        this.role = user.getRole() != null ?
                new EnumResponse( user.getRole().getValue(), user.getRole().name() ) : null;
        this.bloodGroup = user.getBloodGroup() != null ?
                new EnumResponse( user.getBloodGroup().getValue() , user.getBloodGroup().name() ) : null;
        this.gender = user.getGender() != null ?
                new EnumResponse( user.getGender().getvalue() , user.getGender().name() ) : null;
        this.employeeType = user.getEmployeeType() != null ?
                new EnumResponse( user.getEmployeeType().getValue() , user.getEmployeeType().name() ) : null;
    }
}
