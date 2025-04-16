package com.digiHR.leave.repository;

import com.digiHR.leave.RequestStatus;
import com.digiHR.leave.model.Leave;
import com.digiHR.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>, JpaSpecificationExecutor<Leave> {

    List<Leave> findByEmployeeAndRequestStatus( User employee, RequestStatus requestStatus );

    @Query( "SELECT COALESCE(SUM(DATEDIFF(l.endDate, l.startDate) + 1), 0 ) "
            + "FROM Leave l WHERE l.employee = :employee AND l.requestStatus = :status")
    Integer countByEmployeeAndRequestStatus( @Param("employee") User employee,
                                         @Param("status") RequestStatus status );
}
