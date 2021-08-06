package in.co.srdt.unif.model.payroll;


public class IdentifiedPaygroupCalendar {
    long calendarid;
    long paygroupid;
    long stgid;

    public IdentifiedPaygroupCalendar() {
    }

    public IdentifiedPaygroupCalendar(long calendarid, long paygroupid, long stgid) {
        this.calendarid = calendarid;
        this.paygroupid = paygroupid;
        this.stgid = stgid;
    }

    public long getCalendarid() {
        return calendarid;
    }

    public void setCalendarid(long calendarid) {
        this.calendarid = calendarid;
    }

    public long getPaygroupid() {
        return paygroupid;
    }

    public void setPaygroupid(long paygroupid) {
        this.paygroupid = paygroupid;
    }

    public long getStgid() {
        return stgid;
    }

    public void setStgid(long stgid) {
        this.stgid = stgid;
    }

    @Override
    public String toString() {
        return "IdentifiedPaygroupCalendar{" +
                "calendarid=" + calendarid +
                ", paygroupid=" + paygroupid +
                ", stgid=" + stgid +
                '}';
    }
}
