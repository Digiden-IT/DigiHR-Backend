package com.digiHR.user.model;

import com.digiHR.user.Department;
import com.digiHR.user.EmployeeType;
import com.digiHR.user.Gender;
import com.digiHR.user.Role;
import com.digiHR.user.request.AddUserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String name;

    @Column(name = "employee_code", unique = true)
    private String employeeCode;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "designation")
    private String designation;

    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_type")
    private EmployeeType employeeType;

    @Column(name = "date_of_joining")
    private String dateOfJoining;

    @Column(name = "total_leaves")
    private Integer totalLeaves;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "refresh_token")
    private String refreshToken;

    public User(AddUserRequest addUserRequest) {
        this.name = addUserRequest.getName();
        this.email = addUserRequest.getEmail();
        this.phoneNumber = addUserRequest.getPhoneNumber();
        this.dateOfBirth = addUserRequest.getDateOfBirth();
        this.designation = addUserRequest.getDesignation();
        this.department = addUserRequest.getDepartment();
        this.gender = addUserRequest.getGender();
        this.bloodGroup = addUserRequest.getBloodGroup();
        this.address = addUserRequest.getAddress();
        this.employeeType = addUserRequest.getEmployeeType();
        this.dateOfJoining = addUserRequest.getDateOfJoining();
        this.totalLeaves = Integer.valueOf(addUserRequest.getTotalLeaves());
        this.password = addUserRequest.getPassword();
        this.isActive = addUserRequest.getIsActive() != null ? addUserRequest.getIsActive() : true;
        this.role = addUserRequest.getRole();
        this.refreshToken = addUserRequest.getRefreshToken();
    }
}
