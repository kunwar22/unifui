package in.co.srdt.unif.model.payroll;

public class CalendarPeriod {
    private int paygroupid;
    private int calendarid;
    private String groupname;
    private String calendarcode;
    private int calendarperiod;
    private int days;
    private String status;
    private String startdate;
    private String enddate;
    private String createddate = "";
    private String createdby;
    private String selecttype;
    private String financialyear;

    public CalendarPeriod() {
    }

    public CalendarPeriod(int paygroupid, int calendarid, String groupname, String calendarcode, int calendarperiod, int days, String status, String startdate, String enddate, String createddate, String createdby, String selecttype, String financialyear) {
        this.paygroupid = paygroupid;
        this.calendarid = calendarid;
        this.groupname = groupname;
        this.calendarcode = calendarcode;
        this.calendarperiod = calendarperiod;
        this.days = days;
        this.status = status;
        this.startdate = startdate;
        this.enddate = enddate;
        this.createddate = createddate;
        this.createdby = createdby;
        this.selecttype = selecttype;
        this.financialyear = financialyear;
    }

    public String getFinancialyear() {
        return financialyear;
    }

    public void setFinancialyear(String financialyear) {
        this.financialyear = financialyear;
    }

    public int getPaygroupid() {
        return paygroupid;
    }

    public void setPaygroupid(int paygroupid) {
        this.paygroupid = paygroupid;
    }

    public int getCalendarid() {
        return calendarid;
    }

    public void setCalendarid(int calendarid) {
        this.calendarid = calendarid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getCalendarcode() {
        return calendarcode;
    }

    public void setCalendarcode(String calendarcode) {
        this.calendarcode = calendarcode;
    }

    public int getCalendarperiod() {
        return calendarperiod;
    }

    public void setCalendarperiod(int calendarperiod) {
        this.calendarperiod = calendarperiod;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getSelecttype() {
        return selecttype;
    }

    public void setSelecttype(String selecttype) {
        this.selecttype = selecttype;
    }

    @Override
    public String toString() {
        return "CalendarPeriod{" +
                "paygroupid=" + paygroupid +
                ", calendarid=" + calendarid +
                ", groupname='" + groupname + '\'' +
                ", calendarcode='" + calendarcode + '\'' +
                ", calendarperiod=" + calendarperiod +
                ", days=" + days +
                ", status='" + status + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", createddate='" + createddate + '\'' +
                ", createdby='" + createdby + '\'' +
                ", selecttype='" + selecttype + '\'' +
                ", financialyear='" + financialyear + '\'' +
                '}';
    }
}
