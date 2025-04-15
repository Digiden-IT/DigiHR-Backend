package com.digiHR.user.repository;

import com.digiHR.user.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email );

    @Modifying
    @Query(
            "update User " +
            "set refreshToken = ?2 " +
            "where id = ?1"
    )
    void updateRefreshTokenById( long id, String refreshToken );

    @Cacheable( value = "userByIdCache", key = "#currentUserId", unless = "#result == null" )
    User getUserById( Long currentUserId );

}
