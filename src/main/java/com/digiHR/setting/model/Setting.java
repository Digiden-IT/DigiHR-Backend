package com.digiHR.setting.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "settings" )
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( length = 1000 )
    private String leaveCountSetting;
}
