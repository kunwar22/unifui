package in.co.srdt.unif.model.payroll;

public class IdentifiedEmployees {
    long identifiedid = 0;
    String job;
    String hrstatus;
    String payrollstatus;
    String personnumber;
    String personid;
    long position;
    String status = "off";
    String calendarid;
    String calendarcode = "";
    String personname;
    String dates = "";

    public IdentifiedEmployees() {
    }

    public IdentifiedEmployees(long identifiedid, String job, String hrstatus, String payrollstatus, String personnumber, String personid, long position, String status, String calendarid, String calendarcode, String personname, String dates) {
        this.identifiedid = identifiedid;
        this.job = job;
        this.hrstatus = hrstatus;
        this.payrollstatus = payrollstatus;
        this.personnumber = personnumber;
        this.personid = personid;
        this.position = position;
        this.status = status;
        this.calendarid = calendarid;
        this.calendarcode = calendarcode;
        this.personname = personname;
        this.dates = dates;
    }

    public long getIdentifiedid() {
        return identifiedid;
    }

    public void setIdentifiedid(long identifiedid) {
        this.identifiedid = identifiedid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHrstatus() {
        return hrstatus;
    }

    public void setHrstatus(String hrstatus) {
        this.hrstatus = hrstatus;
    }

    public String getPayrollstatus() {
        return payrollstatus;
    }

    public void setPayrollstatus(String payrollstatus) {
        this.payrollstatus = payrollstatus;
    }

    public String getPersonnumber() {
        return personnumber;
    }

    public void setPersonnumber(String personnumber) {
        this.personnumber = personnumber;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCalendarid() {
        return calendarid;
    }

    public void setCalendarid(String calendarid) {
        this.calendarid = calendarid;
    }

    public String getCalendarcode() {
        return calendarcode;
    }

    public void setCalendarcode(String calendarcode) {
        this.calendarcode = calendarcode;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "IdentifiedEmployees{" +
                "identifiedid=" + identifiedid +
                ", job='" + job + '\'' +
                ", hrstatus='" + hrstatus + '\'' +
                ", payrollstatus='" + payrollstatus + '\'' +
                ", personnumber='" + personnumber + '\'' +
                ", personid='" + personid + '\'' +
                ", position=" + position +
                ", status='" + status + '\'' +
                ", calendarid='" + calendarid + '\'' +
                ", calendarcode='" + calendarcode + '\'' +
                ", personname='" + personname + '\'' +
                ", dates='" + dates + '\'' +
                '}';
    }
}
