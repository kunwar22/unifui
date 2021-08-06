package in.co.srdt.unif.model.scheduleprocess;

public class EmployeeAutoIncrement {

    private long jobdataactionid; //*-
    private String personnumber; //*-
    private String personid;
    private String departments;
    private String location;
    private long businessunitid;
    private String personname;
    private String natureofemployement; //*-
    private String payrollstatus;
    private String monthofincrement;
    private String gradename;
    private long gradeid; //*-
    private String gradestepsamount; //*-
    private long gradestepsid; //*-
    private long nextstepid; //*-
    private String nextstepamount; //*-
    private long lmrcactionid; //*-
    private double rop; //*-
    private String effectivestartdate; //*-
    private String effectiveenddate="4712-12-31"; //*-
    private String flag="off"; //*// -
    private double nextrop;//*
    private String designation;
    private String lastincrementeffectivedate;

    public EmployeeAutoIncrement() {
    }

    public EmployeeAutoIncrement(long jobdataactionid, String personnumber, String personid, String departments, String location, long businessunitid, String personname, String natureofemployement, String payrollstatus, String monthofincrement, String gradename, long gradeid, String gradestepsamount, long gradestepsid, long nextstepid, String nextstepamount, long lmrcactionid, double rop, String effectivestartdate, String effectiveenddate, String flag, double nextrop, String designation, String lastincrementeffectivedate) {
        this.jobdataactionid = jobdataactionid;
        this.personnumber = personnumber;
        this.personid = personid;
        this.departments = departments;
        this.location = location;
        this.businessunitid = businessunitid;
        this.personname = personname;
        this.natureofemployement = natureofemployement;
        this.payrollstatus = payrollstatus;
        this.monthofincrement = monthofincrement;
        this.gradename = gradename;
        this.gradeid = gradeid;
        this.gradestepsamount = gradestepsamount;
        this.gradestepsid = gradestepsid;
        this.nextstepid = nextstepid;
        this.nextstepamount = nextstepamount;
        this.lmrcactionid = lmrcactionid;
        this.rop = rop;
        this.effectivestartdate = effectivestartdate;
        this.effectiveenddate = effectiveenddate;
        this.flag = flag;
        this.nextrop = nextrop;
        this.designation = designation;
        this.lastincrementeffectivedate = lastincrementeffectivedate;
    }

    public double getNextrop() {
        return nextrop;
    }

    public void setNextrop(double nextrop) {
        this.nextrop = nextrop;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLastincrementeffectivedate() {
        return lastincrementeffectivedate;
    }

    public void setLastincrementeffectivedate(String lastincrementeffectivedate) {
        this.lastincrementeffectivedate = lastincrementeffectivedate;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getPayrollstatus() {
        return payrollstatus;
    }

    public void setPayrollstatus(String payrollstatus) {
        this.payrollstatus = payrollstatus;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public String getGradestepsamount() {
        return gradestepsamount;
    }

    public void setGradestepsamount(String gradestepsamount) {
        this.gradestepsamount = gradestepsamount;
    }

    public String getNextstepamount() {
        return nextstepamount;
    }

    public void setNextstepamount(String nextstepamount) {
        this.nextstepamount = nextstepamount;
    }

    public long getLmrcactionid() {
        return lmrcactionid;
    }

    public void setLmrcactionid(long lmrcactionid) {
        this.lmrcactionid = lmrcactionid;
    }

    public double getRop() {
        return rop;
    }

    public void setRop(double rop) {
        this.rop = rop;
    }

    public String getEffectivestartdate() {
        return effectivestartdate;
    }

    public void setEffectivestartdate(String effectivestartdate) {
        this.effectivestartdate = effectivestartdate;
    }

    public String getEffectiveenddate() {
        return effectiveenddate;
    }

    public void setEffectiveenddate(String effectiveenddate) {
        this.effectiveenddate = effectiveenddate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public long getJobdataactionid() {
        return jobdataactionid;
    }

    public void setJobdataactionid(long jobdataactionid) {
        this.jobdataactionid = jobdataactionid;
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

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNatureofemployement() {
        return natureofemployement;
    }

    public void setNatureofemployement(String natureofemployement) {
        this.natureofemployement = natureofemployement;
    }

    public String getMonthofincrement() {
        return monthofincrement;
    }

    public void setMonthofincrement(String monthofincrement) {
        this.monthofincrement = monthofincrement;
    }

    public long getBusinessunitid() {
        return businessunitid;
    }

    public void setBusinessunitid(long businessunitid) {
        this.businessunitid = businessunitid;
    }

    public long getGradeid() {
        return gradeid;
    }

    public void setGradeid(long gradeid) {
        this.gradeid = gradeid;
    }

    public long getGradestepsid() {
        return gradestepsid;
    }

    public void setGradestepsid(long gradestepsid) {
        this.gradestepsid = gradestepsid;
    }

    public long getNextstepid() {
        return nextstepid;
    }

    public void setNextstepid(long nextstepid) {
        this.nextstepid = nextstepid;
    }

    @Override
    public String toString() {
        return "EmployeeAutoIncrement{" +
                "jobdataactionid=" + jobdataactionid +
                ", personnumber='" + personnumber + '\'' +
                ", personid='" + personid + '\'' +
                ", departments='" + departments + '\'' +
                ", location='" + location + '\'' +
                ", businessunitid=" + businessunitid +
                ", personname='" + personname + '\'' +
                ", natureofemployement='" + natureofemployement + '\'' +
                ", payrollstatus='" + payrollstatus + '\'' +
                ", monthofincrement='" + monthofincrement + '\'' +
                ", gradename='" + gradename + '\'' +
                ", gradeid=" + gradeid +
                ", gradestepsamount='" + gradestepsamount + '\'' +
                ", gradestepsid=" + gradestepsid +
                ", nextstepid=" + nextstepid +
                ", nextstepamount='" + nextstepamount + '\'' +
                ", lmrcactionid=" + lmrcactionid +
                ", rop=" + rop +
                ", effectivestartdate='" + effectivestartdate + '\'' +
                ", effectiveenddate='" + effectiveenddate + '\'' +
                ", flag='" + flag + '\'' +
                ", nextrop=" + nextrop +
                ", designation='" + designation + '\'' +
                ", lastincrementeffectivedate='" + lastincrementeffectivedate + '\'' +
                '}';
    }
}
