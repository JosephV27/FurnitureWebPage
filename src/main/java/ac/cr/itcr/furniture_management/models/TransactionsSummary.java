package ac.cr.itcr.furniture_management.models;

public class TransactionsSummary {

    private int activeEmployees;
    private int InactiveEmployees;
    private int SalariesByMonth;
    private int SalariesByYear;
    private int TotalHoursWorkedByActualMonth;

    public TransactionsSummary(int activeEmployees, int inactiveEmployees, int salariesByMonth, int salariesByYear, int totalHoursWorkedByActualMonth) {
        this.activeEmployees = activeEmployees;
        InactiveEmployees = inactiveEmployees;
        SalariesByMonth = salariesByMonth;
        SalariesByYear = salariesByYear;
        TotalHoursWorkedByActualMonth = totalHoursWorkedByActualMonth;
    }

    public int getActiveEmployees() {
        return activeEmployees;
    }

    public void setActiveEmployees(int activeEmployees) {
        this.activeEmployees = activeEmployees;
    }

    public int getInactiveEmployees() {
        return InactiveEmployees;
    }

    public void setInactiveEmployees(int inactiveEmployees) {
        InactiveEmployees = inactiveEmployees;
    }

    public int getSalariesByMonth() {
        return SalariesByMonth;
    }

    public void setSalariesByMonth(int salariesByMonth) {
        SalariesByMonth = salariesByMonth;
    }

    public int getSalariesByYear() {
        return SalariesByYear;
    }

    public void setSalariesByYear(int salariesByYear) {
        SalariesByYear = salariesByYear;
    }

    public int getTotalHoursWorkedByActualMonth() {
        return TotalHoursWorkedByActualMonth;
    }

    public void setTotalHoursWorkedByActualMonth(int totalHoursWorkedByActualMonth) {
        TotalHoursWorkedByActualMonth = totalHoursWorkedByActualMonth;
    }
}
