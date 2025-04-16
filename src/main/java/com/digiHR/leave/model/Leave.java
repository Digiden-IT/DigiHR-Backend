package com.digiHR.leave.model;

import com.digiHR.leave.LeaveReason;
import com.digiHR.leave.RequestStatus;
import com.digiHR.leave.request.AddLeaveRequest;
import com.digiHR.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@Data
@Entity
@Table ( name = "leaves" )
@NoArgsConstructor
@AllArgsConstructor
public class Leave {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "startDate" )
    private Date startDate;

    @Column( name = "endDate" )
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column( name = "leaveReason" )
    private LeaveReason leaveReason;

    @Enumerated( EnumType.STRING )
    @Column( name = "status" )
    private RequestStatus requestStatus;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "employee_id", foreignKey = @ForeignKey( name = "fk_leave_employee_id" ) )
    private User employee;

    public Leave( AddLeaveRequest request, User employee ) {
        this.employee = employee;
        this.startDate =request.getStartDate();
        this.endDate = request.getEndDate();
        this.leaveReason = request.getLeaveReason();
        this.requestStatus=RequestStatus.PENDING;
    }
}
