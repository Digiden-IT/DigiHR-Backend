package com.digiHR.user.service;

import com.digiHR.user.model.User;
import com.digiHR.user.repository.UserRepository;
import com.digiHR.user.request.AddUserRequest;
import com.digiHR.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor( onConstructor_ = {@Autowired} )
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse addUser(AddUserRequest addUserRequest ) {
        User user = new User( addUserRequest );
        user.setPassword( passwordEncoder.encode( addUserRequest.getPassword() ) );
        user = userRepository.save( user );
        return new UserResponse( user );
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map( UserResponse::new )
                .collect( Collectors.toList() );
    }

    public UserResponse getUser( Long id ) {
        return new UserResponse( Objects.requireNonNull( userRepository.findById( id ).orElse( null ) ) );
    }


    public UserResponse updateUser(Long id, AddUserRequest request) {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + id);
        }

        User existingUser = existingUserOpt.get();

        // Update only non-null fields
        if (request.getName() != null) existingUser.setName(request.getName());
        if (request.getEmail() != null) existingUser.setEmail(request.getEmail());
        if (request.getPassword() != null) existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
        if (request.getPhoneNumber() != null) existingUser.setPhoneNumber(request.getPhoneNumber());
        if (request.getDesignation() != null) existingUser.setDesignation(request.getDesignation());
        if (request.getDepartment() != null) existingUser.setDepartment(request.getDepartment());
        if (request.getGender() != null) existingUser.setGender(request.getGender());
        if (request.getBloodGroup() != null) existingUser.setBloodGroup(request.getBloodGroup());
        if (request.getAddress() != null) existingUser.setAddress(request.getAddress());
        if (request.getEmployeeType() != null) existingUser.setEmployeeType(request.getEmployeeType());
        if (request.getDateOfJoining() != null) existingUser.setDateOfJoining(request.getDateOfJoining());
        if (request.getTotalLeaves() != null) existingUser.setTotalLeaves(Integer.valueOf(request.getTotalLeaves()));
        if (request.getIsActive() != null) existingUser.setIsActive(request.getIsActive());
        if (request.getRole() != null) existingUser.setRole(request.getRole());
        if (request.getRefreshToken() != null) existingUser.setRefreshToken(request.getRefreshToken());

        User updatedUser = userRepository.save(existingUser);
        return new UserResponse(updatedUser);
    }

    // Delete a user
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    // Filter users based on query parameters
    public List<UserResponse> filterUsers(String department, String role, Boolean isActive, String name, String employeeCode, String bloodGroup, Date dateOfBirth) {
        return userRepository.findAll().stream()
                .filter(user -> (department == null || (user.getDepartment() != null && user.getDepartment().name().equalsIgnoreCase(department))))
                .filter(user -> (role == null || (user.getRole() != null && user.getRole().name().equalsIgnoreCase(role))))
                .filter(user -> (isActive == null || Boolean.TRUE.equals(user.getIsActive())))
                .filter(user -> (name == null || (user.getName() != null && user.getName().toLowerCase().contains(name.toLowerCase()))))
                .filter(user -> (employeeCode == null || (user.getEmployeeCode() != null && user.getEmployeeCode().equalsIgnoreCase(employeeCode))))
                .filter(user -> (bloodGroup == null || (user.getBloodGroup() != null && user.getBloodGroup().equalsIgnoreCase(bloodGroup))))
                .filter(user -> (dateOfBirth == null || (user.getDateOfBirth() != null && user.getDateOfBirth().equals(dateOfBirth.toString()))))
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

}
