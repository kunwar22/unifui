package in.co.srdt.unif.model.payroll;

public class PersonByOTPElement {
    long dataid;
    String personnumber;
    double otpamt;
    String processdate;
    long elementid;
    long calendarid;
    String elementname;
    String calendarcode;
    String personname;

    public PersonByOTPElement() {
    }

    public PersonByOTPElement(long dataid, String personnumber, double otpamt, String processdate, long elementid, long calendarid, String elementname, String calendarcode, String personname) {
        this.dataid = dataid;
        this.personnumber = personnumber;
        this.otpamt = otpamt;
        this.processdate = processdate;
        this.elementid = elementid;
        this.calendarid = calendarid;
        this.elementname = elementname;
        this.calendarcode = calendarcode;
        this.personname = personname;
    }

    public long getDataid() {
        return dataid;
    }

    public void setDataid(long dataid) {
        this.dataid = dataid;
    }

    public String getPersonnumber() {
        return personnumber;
    }

    public void setPersonnumber(String personnumber) {
        this.personnumber = personnumber;
    }

    public double getOtpamt() {
        return otpamt;
    }

    public void setOtpamt(double otpamt) {
        this.otpamt = otpamt;
    }

    public String getProcessdate() {
        return processdate;
    }

    public void setProcessdate(String processdate) {
        this.processdate = processdate;
    }

    public long getElementid() {
        return elementid;
    }

    public void setElementid(long elementid) {
        this.elementid = elementid;
    }

    public long getCalendarid() {
        return calendarid;
    }

    public void setCalendarid(long calendarid) {
        this.calendarid = calendarid;
    }

    public String getElementname() {
        return elementname;
    }

    public void setElementname(String elementname) {
        this.elementname = elementname;
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

    @Override
    public String toString() {
        return "PersonByOTPElement{" +
                "dataid=" + dataid +
                ", personnumber='" + personnumber + '\'' +
                ", otpamt=" + otpamt +
                ", processdate='" + processdate + '\'' +
                ", elementid=" + elementid +
                ", calendarid=" + calendarid +
                ", elementname='" + elementname + '\'' +
                ", calendarcode='" + calendarcode + '\'' +
                ", personname='" + personname + '\'' +
                '}';
    }
}
