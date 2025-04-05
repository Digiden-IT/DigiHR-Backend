package com.digiHR.user.controller;

import com.digiHR.user.request.AddUserRequest;
import com.digiHR.user.request.GetUserRequest;
import com.digiHR.user.response.FilterOptionResponse;
import com.digiHR.user.response.UserResponse;
import com.digiHR.user.service.UserService;
import com.digiHR.user.utility.response.PaginatedApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> addUser( @RequestBody AddUserRequest request ) {
        UserResponse response = userService.addUser( request );
        return ResponseEntity.ok( response );
    }

    @GetMapping
    public ResponseEntity<?> getUsers(GetUserRequest request, Pageable pageable ) {
        PaginatedApiResponse<List<UserResponse>> userResponses = userService.getUsers( request, pageable);
        return ResponseEntity.ok( userResponses );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserResponse> getUser( @PathVariable Long id ) {
        UserResponse user = userService.getUser( id );
        return ResponseEntity.ok( user );
    }

    @PutMapping( "/{id}")
    public ResponseEntity<UserResponse> updateUser( @PathVariable Long id, @RequestBody AddUserRequest request ) {

         UserResponse updatedUser = userService.updateUser( id, request );

        return ResponseEntity.ok( updatedUser );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser( @PathVariable Long id ) {
        userService.deleteUser( id );
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/filter-options")
    public ResponseEntity<FilterOptionResponse> getFilterOptions() {
        FilterOptionResponse options = userService.getFilterOptions();
        return ResponseEntity.ok(options);
    }

}
