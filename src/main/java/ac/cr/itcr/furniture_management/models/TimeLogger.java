//package ac.cr.itcr.furniture_management.models;
//
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "time_logger")
//public class TimeLogger {
//
//    @Id
//    @Column(name = "num_employee")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int numEmployee;
//
//    //TODO FOREIGN KEYS
//    private int idProduct;
//
//    @Column(name = "double_hours")
//    private int doubleHours;
//
//    @Column(name = "extra_hours")
//    private int extraHours;
//
//    @Column(name = "ordinary_hours")
//    private int ordinaryHours;
//
//    private int extra_hours;
//
//    @Column(name = "date_time_logger")
//    private Date dateTimeLogger;
//
//
//    public TimeLogger(int numEmployee, int idProduct, int doubleHours, int extraHours, int ordinaryHours, int extra_hours, Date dateTimeLogger) {
//        super();
//        this.numEmployee = numEmployee;
//        this.idProduct = idProduct;
//        this.doubleHours = doubleHours;
//        this.extraHours = extraHours;
//        this.ordinaryHours = ordinaryHours;
//        this.extra_hours = extra_hours;
//        this.dateTimeLogger = dateTimeLogger;
//    }
//
//    public TimeLogger() {
//
//    }
//
//
//    public int getNumEmployee() {
//        return numEmployee;
//    }
//
//    public void setNumEmployee(int numEmployee) {
//        this.numEmployee = numEmployee;
//    }
//
//    public int getIdProduct() {
//        return idProduct;
//    }
//
//    public void setIdProduct(int idProduct) {
//        this.idProduct = idProduct;
//    }
//
//    public int getDoubleHours() {
//        return doubleHours;
//    }
//
//    public void setDoubleHours(int doubleHours) {
//        this.doubleHours = doubleHours;
//    }
//
//    public int getExtraHours() {
//        return extraHours;
//    }
//
//    public void setExtraHours(int extraHours) {
//        this.extraHours = extraHours;
//    }
//
//    public int getOrdinaryHours() {
//        return ordinaryHours;
//    }
//
//    public void setOrdinaryHours(int ordinaryHours) {
//        this.ordinaryHours = ordinaryHours;
//    }
//
//    public int getExtra_hours() {
//        return extra_hours;
//    }
//
//    public void setExtra_hours(int extra_hours) {
//        this.extra_hours = extra_hours;
//    }
//
//    public Date getDateTimeLogger() {
//        return dateTimeLogger;
//    }
//
//    public void setDateTimeLogger(Date dateTimeLogger) {
//        this.dateTimeLogger = dateTimeLogger;
//    }
//
//
//}
