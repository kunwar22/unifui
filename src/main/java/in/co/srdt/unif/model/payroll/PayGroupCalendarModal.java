package in.co.srdt.unif.model.payroll;

public class PayGroupCalendarModal {
    String paygroupString;
    String calendarString;

    public PayGroupCalendarModal() {
    }

    public PayGroupCalendarModal(String paygroupString, String calendarString) {
        this.paygroupString = paygroupString;
        this.calendarString = calendarString;
    }

    public String getPaygroupString() {
        return paygroupString;
    }

    public void setPaygroupString(String paygroupString) {
        this.paygroupString = paygroupString;
    }

    public String getCalendarString() {
        return calendarString;
    }

    public void setCalendarString(String calendarString) {
        this.calendarString = calendarString;
    }

    @Override
    public String toString() {
        return "PayGroupCalendarModal{" +
                "paygroupString='" + paygroupString + '\'' +
                ", calendarString='" + calendarString + '\'' +
                '}';
    }
}
