package in.co.srdt.unif.model.payroll;

import java.util.List;

public class CalanderPeriodWrapper {

    private List<CalendarPeriod> calendarPeriod;

    public CalanderPeriodWrapper() {
    }

    public CalanderPeriodWrapper(List<CalendarPeriod> calendarPeriod) {
        this.calendarPeriod = calendarPeriod;
    }

    public List<CalendarPeriod> getCalendarPeriod() {
        return calendarPeriod;
    }

    public void setCalendarPeriod(List<CalendarPeriod> calendarPeriod) {
        this.calendarPeriod = calendarPeriod;
    }

    @Override
    public String toString() {
        return "CalanderPeriodWrapper{" +
                "calendarPeriod=" + calendarPeriod +
                '}';
    }
}
