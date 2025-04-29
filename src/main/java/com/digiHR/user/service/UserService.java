package com.digiHR.user.service;

import com.digiHR.user.*;
import com.digiHR.user.model.User;
import com.digiHR.user.repository.UserRepository;
import com.digiHR.user.request.AddUserRequest;
import com.digiHR.user.request.GetUserRequest;
import com.digiHR.user.response.FilterOptionResponse;
import com.digiHR.user.response.UserResponse;
import com.digiHR.utility.exceptions.NotFoundException;
import com.digiHR.utility.response.EnumResponse;
import com.digiHR.utility.response.PaginatedApiResponse;
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

import java.util.Arrays;
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

    public UserResponse getUser( Long id ) {
        return new UserResponse(
                Objects.requireNonNull( userRepository.findById( id )
                       .orElseThrow( () -> new NotFoundException( "user", id ) ) )
        );
    }

    public PaginatedApiResponse<List<UserResponse>> getUsers( GetUserRequest request, Pageable pageable ) {

        Specification<User> userSpecification = getUserSpecification( request );
        Page<User> userPage = userRepository.findAll( userSpecification, pageable );

        List<UserResponse> userResponseList = userPage.stream()
                .map( this::getUserResponse )
                .toList();

        return new PaginatedApiResponse<>(
                userResponseList,
                pageable.getPageNumber(),
                userPage.getTotalPages(),
                userPage.getTotalElements()
        );
    }

    public UserResponse getUserResponse( User user ) {

        UserResponse userResponse = new UserResponse();
        userResponse.setId( user.getId() );
        userResponse.setName( user.getName() );
        userResponse.setDepartment(String.valueOf( new EnumResponse( user.getDepartment().getvalue(), user.getDepartment().toString() ) ) );
        userResponse.setEmail( user.getEmail() );
        userResponse.setRole( String.valueOf( new EnumResponse(user.getRole().getValue(), user.getRole().toString() ) ) );        userResponse.setDateOfJoining( user.getDateOfJoining() );
        return userResponse;
    }

    private Specification<User> getUserSpecification( GetUserRequest request ) {

        return filterByIsActive( request.getIsActive() )
                .and( filterByIsDeletedNull().or( filterByIsDeletedFalse() ) )
                .and( filterByRoleIn( request.getRole() == null ? null : List.of( request.getRole() ) ) );
    }

    @Transactional
    public UserResponse updateUser( Long id, AddUserRequest request ) {

        User user = entityManager.getReference( User.class, id );
        evictUserCache( user.getEmail() );
        evictUserIdCache( id );

        if ( request.getName() != null )
            user.setName( request.getName() );

        if ( request.getEmail() != null )
            user.setEmail( request.getEmail() );

        if ( request.getPhoneNumber() != null )
            user.setPhoneNumber( request.getPhoneNumber() );

        if ( request.getDesignation() != null )
            user.setDesignation( request.getDesignation() );

        if ( request.getDepartment() != null )
            user.setDepartment( request.getDepartment() );

        if ( request.getAddress() != null )
            user.setAddress( request.getAddress() );

        if ( request.getEmployeeType() != null )
            user.setEmployeeType( request.getEmployeeType() );

        User updatedUser = userRepository.save( user );
        return new UserResponse( updatedUser );
    }

    @Transactional
    public void deleteUser( Long id ) {

        User user = entityManager.getReference( User.class, id );
        user.setIsDeleted( true );
        userRepository.save( user );
    }

    public FilterOptionResponse getFilterOptions() {

        List<EnumResponse> departments = Arrays.stream( Department.values() )
                .map( department -> new EnumResponse( department.getvalue(), department.toString() ) )
                .collect( Collectors.toList() );

        List<EnumResponse> employeeTypes = Arrays.stream( EmployeeType.values() )
                .map( employeeType -> new EnumResponse( employeeType.getvalue(), employeeType.toString() ) )
                .collect( Collectors.toList() );

        List<EnumResponse> roles = Arrays.stream( Role.values() )
                .map( role -> new EnumResponse( role.getValue(), role.toString() ) )
                .collect( Collectors.toList() );

        List<EnumResponse>bloodGroups = Arrays.stream( BloodGroup.values() )
                .map( bloodGroup -> new EnumResponse( bloodGroup.getvalue(), bloodGroup.toString() ) )
                .collect( Collectors.toList() );

        List<EnumResponse>genders = Arrays.stream( Gender.values() )
                .map( gender -> new EnumResponse( gender.getvalue(), gender.toString() ) )
                .collect( Collectors.toList() );

        return new FilterOptionResponse( departments, roles, employeeTypes, bloodGroups, genders );
    }
}
