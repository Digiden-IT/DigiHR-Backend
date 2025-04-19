package com.digiHR.leave.repository;

import com.digiHR.leave.RequestStatus;
import com.digiHR.leave.model.Leave;
import com.digiHR.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>, JpaSpecificationExecutor<Leave> {

    List<Leave> findByEmployeeAndRequestStatus( User employee, RequestStatus requestStatus );

    @Query(value = "SELECT COALESCE( SUM( EXTRACT( DAY FROM (end_date - start_date) ) + 1 ), 0 ) " +
            "FROM leaves " +
            "WHERE employee_id = ?1 AND status = ?2", nativeQuery = true )
    Integer sumLeaveDays( Long employeeId, String status );

}
