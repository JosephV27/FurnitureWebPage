package ac.cr.itcr.furniture_management.repositories;

import ac.cr.itcr.furniture_management.models.TimeLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface TimeLoggerRepository extends JpaRepository<TimeLogger, Integer> {

    @Procedure(name = "getHoursWorkedByDay")
    @Modifying
    @Transactional
    List<TimeLogger> getHoursWorkedByDate(@Param("v_initial_date") Date initialDate, @Param("v_ending_date") Date endingDate);

    @Procedure(name = "deleteTimeLogger")
    @Modifying
    @Transactional
    void deleteTimeLogger(@Param("v_num_employee") int numEmployee, @Param("v_date_time_logger") Date dateTimeLogger);

    @Query(value = "SELECT SUM(ordinary_hours) + SUM(extra_hours) + SUM(double_hours) FROM time_logger WHERE EXTRACT(MONTH FROM date_time_logger) = EXTRACT(MONTH FROM SYSDATE)", nativeQuery = true)
    int getTotalHoursWorkedByActualMonth();

}
