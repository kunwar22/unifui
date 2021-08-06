package in.co.srdt.unif.model.payroll;

public class PayrollProcessedData {
    String calendarcode;
    String personnumber;
    String personname;
    String payamt;
    String abbreviation;

    public PayrollProcessedData() {
    }

    public PayrollProcessedData(String calendarcode, String personnumber, String personname, String payamt, String abbreviation) {
        this.calendarcode = calendarcode;
        this.personnumber = personnumber;
        this.personname = personname;
        this.payamt = payamt;
        this.abbreviation = abbreviation;
    }

    public String getCalendarcode() {
        return calendarcode;
    }

    public void setCalendarcode(String calendarcode) {
        this.calendarcode = calendarcode;
    }

    public String getPersonnumber() {
        return personnumber;
    }

    public void setPersonnumber(String personnumber) {
        this.personnumber = personnumber;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getPayamt() {
        return payamt;
    }

    public void setPayamt(String payamt) {
        this.payamt = payamt;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return "PayrollProcessedData{" +
                "calendarcode='" + calendarcode + '\'' +
                ", personnumber='" + personnumber + '\'' +
                ", personname='" + personname + '\'' +
                ", payamt='" + payamt + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                '}';
    }
}
