package in.co.srdt.unif.model.reports;

public class PayslipReportParameters {
    String buid;
    String calid;
    String paygroupid;
    String personnumber;
    String natureofemp;

    public PayslipReportParameters() {
    }

    public PayslipReportParameters(String buid, String calid, String paygroupid, String personnumber, String natureofemp) {
        this.buid = buid;
        this.calid = calid;
        this.paygroupid = paygroupid;
        this.personnumber = personnumber;
        this.natureofemp = natureofemp;
    }

    public String getBuid() {
        return buid;
    }

    public void setBuid(String buid) {
        this.buid = buid;
    }

    public String getCalid() {
        return calid;
    }

    public void setCalid(String calid) {
        this.calid = calid;
    }

    public String getPaygroupid() {
        return paygroupid;
    }

    public void setPaygroupid(String paygroupid) {
        this.paygroupid = paygroupid;
    }

    public String getPersonnumber() {
        return personnumber;
    }

    public void setPersonnumber(String personnumber) {
        this.personnumber = personnumber;
    }

    public String getNatureofemp() {
        return natureofemp;
    }

    public void setNatureofemp(String natureofemp) {
        this.natureofemp = natureofemp;
    }
}
