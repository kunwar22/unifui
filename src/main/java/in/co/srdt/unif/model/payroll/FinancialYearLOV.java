package in.co.srdt.unif.model.payroll;

public class FinancialYearLOV {

    private long yearid;
    private String years;
    private String status;
    private String startdate;
    private String enddate;
    private long id;
    private String assessmentyear;

    public FinancialYearLOV() {
    }

    public FinancialYearLOV(long yearid, String years, String status, String startdate, String enddate, long id, String assessmentyear) {
        this.yearid = yearid;
        this.years = years;
        this.status = status;
        this.startdate = startdate;
        this.enddate = enddate;
        this.id = id;
        this.assessmentyear = assessmentyear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAssessmentyear() {
        return assessmentyear;
    }

    public void setAssessmentyear(String assessmentyear) {
        this.assessmentyear = assessmentyear;
    }

    public long getYearid() {
        return yearid;
    }

    public void setYearid(long yearid) {
        this.yearid = yearid;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
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

    @Override
    public String toString() {
        return "FinancialYearLOV{" +
                "yearid=" + yearid +
                ", years='" + years + '\'' +
                ", status='" + status + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", id=" + id +
                ", assessmentyear='" + assessmentyear + '\'' +
                '}';
    }
}
