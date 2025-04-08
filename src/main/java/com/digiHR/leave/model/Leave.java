package com.digiHR.leave.model;

import com.digiHR.leave.Status;
import com.digiHR.leave.request.AddLeaveRequest;
import com.digiHR.user.Role;
import com.digiHR.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table ( name = "leaves" )
@NoArgsConstructor
@AllArgsConstructor
public class Leave {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "employee_id", foreignKey = @ForeignKey( name = "fk_leave_employee_id" ) )
    private User employee;

    @Column( name = "startDate" )
    private LocalDate startDate;

    @Column( name = "endDate" )
    private LocalDate endDate;

    @Column( name = "reason" )
    private String reason;

    @Enumerated( EnumType.STRING )
    @Column( name = "status" )
    private Status status;

    public Leave( AddLeaveRequest request, User employee ) {
        this.employee = employee;
        this.startDate = request.getStartDate();
        this.endDate = request.getEndDate();
        this.reason = request.getReason();
    }
}
