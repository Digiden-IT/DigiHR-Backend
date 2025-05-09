package com.digiHR.setting.repository;

import com.digiHR.setting.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

    Optional<Setting> findTopByOrderByIdDesc();
}
