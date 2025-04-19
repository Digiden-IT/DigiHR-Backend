package com.digiHR.leavecountsetting.repository;

import com.digiHR.leavecountsetting.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

    Optional<Setting> findTopByOrderByIdDesc();
}
