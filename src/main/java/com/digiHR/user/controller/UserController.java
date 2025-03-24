package com.digiHR.user.controller;

import com.digiHR.user.request.AddUserRequest;
import com.digiHR.user.response.UserResponse;
import com.digiHR.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor( onConstructor_ = @Autowired )
public class UserController {

    private final UserService userService = null;

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody AddUserRequest request ) {
        UserResponse response = userService.addUser( request );
        return ResponseEntity.ok( response );
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> userResponses = userService.getUsers();
        return ResponseEntity.ok( userResponses );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UserResponse> getUser( @PathVariable Long id ) {
        UserResponse user = userService.getUser( id );
        return ResponseEntity.ok( user );
    }

    @PutMapping( "/{id}")
    public ResponseEntity<UserResponse> updateUser( @PathVariable Long id, @RequestBody AddUserRequest request ) {

        UserResponse updatedUser = userService.updateUser(id, request);

        return ResponseEntity.ok( updatedUser );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserResponse>> filterUsers(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String employeeCode,
            @RequestParam(required = false) String bloodGroup,
            @RequestParam(required = false) Date dateOfBirth){
        List<UserResponse> filteredUsers = userService.filterUsers(department, role, isActive, name, employeeCode, bloodGroup,dateOfBirth);
        return ResponseEntity.ok(filteredUsers);
    }

}
