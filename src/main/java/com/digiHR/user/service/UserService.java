package com.digiHR.user.service;

import com.digiHR.user.model.User;
import com.digiHR.user.repository.UserRepository;
import com.digiHR.user.request.AddUserRequest;
import com.digiHR.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
}
