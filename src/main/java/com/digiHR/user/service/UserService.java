package com.digiHR.user.service;

import com.digiHR.user.model.User;
import com.digiHR.user.repository.UserRepository;
import com.digiHR.user.request.AddUserRequest;
import com.digiHR.user.response.UserResponse;
import com.digiHR.user.utility.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

    @PersistenceContext
    private final EntityManager entityManager;

    @CacheEvict( value = "userCache", key = "#email" )
    public void evictUserCache( String email ) {}

    @CacheEvict( value = "userIdCache", key= "#id" )
    public void evictUserIdCache( Long id ) {}


    public UserResponse addUser( AddUserRequest addUserRequest ) {
        User user = new User( addUserRequest );
        user.setPassword( passwordEncoder.encode( addUserRequest.getPassword() ) );
        user = userRepository.save( user );
        return new UserResponse( user );
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map( UserResponse::new )
                .collect( Collectors.toList() );
    }

    public UserResponse getUser( Long id ) {
        return new UserResponse( Objects.requireNonNull( userRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( "user", id ) ) )
        );
    }


    public UserResponse updateUser(Long id, AddUserRequest request) {

        User user = entityManager.getReference( User.class, id );

        evictUserCache( user.getEmail() );
        evictUserIdCache( id );

        // Update only non-null fields
        if (request.getName() != null)
            user.setName(request.getName());

        if (request.getEmail() != null)
            user.setEmail(request.getEmail());

        if (request.getPassword() != null)
            user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getPhoneNumber() != null)
            user.setPhoneNumber(request.getPhoneNumber());

        if (request.getDesignation() != null)
            user.setDesignation(request.getDesignation());

        if (request.getDepartment() != null)
            user.setDepartment(request.getDepartment());

        if (request.getGender() != null)
            user.setGender(request.getGender());

        if (request.getBloodGroup() != null)
            user.setBloodGroup(request.getBloodGroup());

        if (request.getAddress() != null)
            user.setAddress(request.getAddress());

        if (request.getEmployeeType() != null)
            user.setEmployeeType(request.getEmployeeType());

        if (request.getDateOfJoining() != null)
            user.setDateOfJoining(request.getDateOfJoining());

        if (request.getTotalLeaves() != null)
            user.setTotalLeaves(Integer.valueOf(request.getTotalLeaves()));

        if (request.getIsActive() != null)
            user.setIsActive(request.getIsActive());

        if (request.getRole() != null)
            user.setRole(request.getRole());

        if (request.getRefreshToken() != null)
            user.setRefreshToken(request.getRefreshToken());

        User updatedUser = userRepository.save(user);
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
