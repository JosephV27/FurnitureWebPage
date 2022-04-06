package ac.cr.itcr.furniture_management.services;


import ac.cr.itcr.furniture_management.models.TimeLogger;
import ac.cr.itcr.furniture_management.repositories.TimeLoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TimeLoggerService {

    private static final String insertHoursTimeLogger = "SELECT time_logger_managment.insert_hours_time_logger(?1, ?2, ?3, ?4, ?5, ?6) FROM DUAL";

    @PersistenceContext
    private EntityManager em;

    @Autowired
    TimeLoggerRepository timeLoggerRepository;

    public String save(TimeLogger timeLogger){

        String result = (String)  em.createNativeQuery(insertHoursTimeLogger)
                .setParameter(1, timeLogger.getNumEmployee())
                .setParameter(2, timeLogger.getIdProduct())
                .setParameter(3, timeLogger.getOrdinaryHours())
                .setParameter(4, timeLogger.getExtraHours())
                .setParameter(5, timeLogger.getDoubleHours())
                .setParameter(6, timeLogger.getDateTimeLogger())
                .getSingleResult();

        return result;
    }

    public List<TimeLogger> findbyDates(Date initialDate, Date endingDate) {
        return  timeLoggerRepository.getHoursWorkedByDate(initialDate, endingDate);
    }

    public void deleteTimeLogger(int numEmployee, String dateTimeLogger) throws ParseException {
        Date formattedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateTimeLogger.split(" ")[0]);
        System.out.println(numEmployee);
        System.out.println(formattedDate);
        timeLoggerRepository.deleteTimeLogger(numEmployee, formattedDate);
    }
}
