package com.digiHR.user.service;

import com.digiHR.user.*;
import com.digiHR.user.model.User;
import com.digiHR.user.repository.UserRepository;
import com.digiHR.user.request.AddUserRequest;
import com.digiHR.user.request.GetUserRequest;
import com.digiHR.user.response.FilterOptionResponse;
import com.digiHR.user.response.UserResponse;
import com.digiHR.user.utility.exceptions.NotFoundException;
import com.digiHR.user.utility.response.PaginatedApiResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.digiHR.user.repository.UserSpecification.*;

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

//    public List<UserResponse> getUsers() {
//        return userRepository.findAll()
//                .stream()
//                .map( UserResponse::new )
//                .collect( Collectors.toList() );
//    }

    public UserResponse getUser( Long id ) {
        return new UserResponse(
                Objects.requireNonNull( userRepository.findById( id )
                       .orElseThrow( () -> new NotFoundException( "user", id ) ) )
        );
    }

    public PaginatedApiResponse<List<UserResponse>> getUsers(GetUserRequest request, Pageable pageable) {
        Specification<User> userSpecification = getUserSpecification(request);

        Page<User> userPage = userRepository.findAll( userSpecification, pageable);

        List<UserResponse> userResponseList = userPage.stream()
                .map(UserResponse::new)
                .toList();

        return new PaginatedApiResponse<>(
                userResponseList,
                pageable.getPageNumber(), // <-- get the current page number from the Page object
                userPage.getTotalPages(),
                userPage.getTotalElements()
        );
    }

    private Specification<User> getUserSpecification( GetUserRequest request ) {

        Specification<User> userSpecification = filterByIsActive( request.getIsActive() )
                .and( filterByIsDeletedNull().or( filterByIsDeletedFalse() ) );

        if( request.getRole() != null )
            userSpecification = userSpecification.and( filterByRoleIn( List.of( request.getRole() ) ) );
        else
            userSpecification = userSpecification.and( filterByRoleIn( List.of( Role.ADMIN, Role.USER ) ) );

        return userSpecification;
    }

    public UserResponse updateUser( Long id, AddUserRequest request ) {

        User user = entityManager.getReference( User.class, id );

        evictUserCache( user.getEmail() );
        evictUserIdCache( id );

        // Update only non-null fields
        if ( request.getName() != null )
            user.setName( request.getName() );

        if ( request.getEmail() != null )
            user.setEmail( request.getEmail() );

        if ( request.getDateOfBirth() != null )
            user.setDateOfBirth( request.getDateOfBirth() );

        if ( request.getPassword() != null )
            user.setPassword( passwordEncoder.encode(request.getPassword()) );

        if ( request.getPhoneNumber() != null )
            user.setPhoneNumber( request.getPhoneNumber() );

        if ( request.getDesignation() != null )
            user.setDesignation( request.getDesignation() );

        if ( request.getDepartment() != null )
            user.setDepartment( request.getDepartment() );

        if ( request.getGender() != null )
            user.setGender( request.getGender() );

        if ( request.getBloodGroup() != null )
            user.setBloodGroup( request.getBloodGroup() );

        if ( request.getAddress() != null )
            user.setAddress( request.getAddress() );

        if ( request.getEmployeeType() != null )
            user.setEmployeeType( request.getEmployeeType() );

        if ( request.getDateOfJoining() != null )
            user.setDateOfJoining( request.getDateOfJoining() );

        if ( request.getTotalLeaves() != null )
            user.setTotalLeaves( Integer.valueOf(request.getTotalLeaves()) );

        if ( request.getIsActive() != null )
            user.setIsActive( request.getIsActive() );

        if ( request.getRole() != null )
            user.setRole( request.getRole() );

        if ( request.getRefreshToken() != null )
            user.setRefreshToken( request.getRefreshToken() );

        User updatedUser = userRepository.save( user );
        return new UserResponse( updatedUser );
    }

    // Delete a user
    @Transactional
    public void deleteUser( Long id ) {

        User user = entityManager.getReference( User.class, id );
        user.setIsDeleted( true );
        userRepository.save( user );
    }

    public FilterOptionResponse getFilterOptions() {

        List<Department> departments = List.of(Department.values());
        List<EmployeeType> employeeTypes = List.of(EmployeeType.values());
        List<Role> roles = List.of(Role.values());

        List<BloodGroup>bloodGroups = List.of(BloodGroup.values());
        List<Gender>genders = List.of(Gender.values());

        return new FilterOptionResponse(departments, roles, employeeTypes, bloodGroups, genders);
    }
}
