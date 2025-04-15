/*package com.digiHR.leavecountsetting.repository;

import com.digiHR.leavecountsetting.model.LeaveCountSetting;
import com.digiHR.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveCountSettingRepository extends JpaRepository<LeaveCountSetting, Long> {
    boolean existsByUser( User user );
    Optional<LeaveCountSetting> findByUser( User user );
}*/
