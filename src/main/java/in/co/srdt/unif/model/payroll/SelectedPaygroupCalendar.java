package in.co.srdt.unif.model.payroll;

public class SelectedPaygroupCalendar {
    long paygroupid;
    long calendarid;
    long stgid;

    public SelectedPaygroupCalendar() {
    }

    public SelectedPaygroupCalendar(long paygroupid, long calendarid, long stgid) {
        this.paygroupid = paygroupid;
        this.calendarid = calendarid;
        this.stgid = stgid;
    }

    public long getPaygroupid() {
        return paygroupid;
    }

    public void setPaygroupid(long paygroupid) {
        this.paygroupid = paygroupid;
    }

    public long getCalendarid() {
        return calendarid;
    }

    public void setCalendarid(long calendarid) {
        this.calendarid = calendarid;
    }

    public long getStgid() {
        return stgid;
    }

    public void setStgid(long stgid) {
        this.stgid = stgid;
    }

    @Override
    public String toString() {
        return "SelectedPaygroupCalendar{" +
                "paygroupid=" + paygroupid +
                ", calendarid=" + calendarid +
                ", stgid=" + stgid +
                '}';
    }
}
