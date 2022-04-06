package ac.cr.itcr.furniture_management.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.ParameterMode.*;

@Entity
@Table(name = "time_logger")
@NamedStoredProcedureQuery(
        name = "getHoursWorkedByDay",
        resultClasses = TimeLogger.class,
        procedureName = "consult_time_logger.get_hours_worked_by_date",
        parameters = {
                @StoredProcedureParameter(mode=IN, name="v_initial_date", type=Date.class),
                @StoredProcedureParameter(mode=IN, name="v_ending_date", type=Date.class),
                @StoredProcedureParameter(mode=REF_CURSOR, name="hours_worked_cursor", type=void.class)
        }
)

@NamedStoredProcedureQuery(
        name = "deleteTimeLogger",
        resultClasses = TimeLogger.class,
        procedureName = "time_logger_management.delete_hours_time_logger",
        parameters = {
                @StoredProcedureParameter(mode=IN, name="v_num_employee", type=int.class),
                @StoredProcedureParameter(mode=IN, name="v_date_time_logger", type=Date.class)
        }
)


public class TimeLogger implements Serializable {

    @Id
    @Column(name = "num_employee")
    private int numEmployee;

    //TODO FOREIGN KEYS
    @Column(name = "id_product")
    private int idProduct;

    @Column(name = "double_hours")
    private int doubleHours;

    @Column(name = "extra_hours")
    private int extraHours;

    @Column(name = "ordinary_hours")
    private int ordinaryHours;

    @Column(name = "date_time_logger")
    private Date dateTimeLogger;

    public TimeLogger(int numEmployee, int idProduct, int doubleHours, int extraHours, int ordinaryHours, int extra_hours, Date dateTimeLogger) {
        super();
        this.numEmployee = numEmployee;
        this.idProduct = idProduct;
        this.doubleHours = doubleHours;
        this.extraHours = extraHours;
        this.ordinaryHours = ordinaryHours;
        this.dateTimeLogger = dateTimeLogger;
    }

    public TimeLogger() {

    }

    public int getNumEmployee() {
        return numEmployee;
    }

    public void setNumEmployee(int numEmployee) {
        this.numEmployee = numEmployee;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getDoubleHours() {
        return doubleHours;
    }

    public void setDoubleHours(int doubleHours) {
        this.doubleHours = doubleHours;
    }

    public int getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(int extraHours) {
        this.extraHours = extraHours;
    }

    public int getOrdinaryHours() {
        return ordinaryHours;
    }

    public void setOrdinaryHours(int ordinaryHours) {
        this.ordinaryHours = ordinaryHours;
    }

    public Date getDateTimeLogger() {
        return dateTimeLogger;
    }

    public void setDateTimeLogger(Date dateTimeLogger) {
        this.dateTimeLogger = dateTimeLogger;
    }

    @Override
    public String toString() {
        return "TimeLogger{" +
                "numEmployee=" + numEmployee +
                ", idProduct=" + idProduct +
                ", doubleHours=" + doubleHours +
                ", extraHours=" + extraHours +
                ", ordinaryHours=" + ordinaryHours +
                ", dateTimeLogger=" + dateTimeLogger +
                '}';
    }

}
