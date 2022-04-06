package ac.cr.itcr.furniture_management.models;

import java.util.Date;

public class HistoricalTimeLogger {

    private Date initialDate;

    private Date endingDate;

    public HistoricalTimeLogger(Date initialDate, Date endingDate) {
        this.initialDate = initialDate;
        this.endingDate = endingDate;
    }

    public HistoricalTimeLogger() {

    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }
}
