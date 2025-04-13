package com.digiHR.leavecountsetting.model;

import com.digiHR.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table( name = "settings" )
@Getter
@Setter
public class LeaveCountSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn( name = "userId" )
    private User user;

    @Column( name = "totalLeave" )
    private int totalLeave;
}
