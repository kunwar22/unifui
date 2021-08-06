package in.co.srdt.unif.model.elementautoassign;

public class AutoAssignSearch {

    private String employeecategory;
    private String hiredate;
    private String hrstatus;
    private String natureofemployement;
    private String payrollstatus;
    private String personid;
    private String personname;
    private String personnumber;
    private String efectenddate;
    private long mappingid;

    public AutoAssignSearch() {
    }

    public AutoAssignSearch(String employeecategory, String hiredate, String hrstatus, String natureofemployement, String payrollstatus, String personid, String personname, String personnumber, String efectenddate, long mappingid) {
        this.employeecategory = employeecategory;
        this.hiredate = hiredate;
        this.hrstatus = hrstatus;
        this.natureofemployement = natureofemployement;
        this.payrollstatus = payrollstatus;
        this.personid = personid;
        this.personname = personname;
        this.personnumber = personnumber;
        this.efectenddate = efectenddate;
        this.mappingid = mappingid;
    }

    @Override
    public String toString() {
        return "AutoAssignSearch{" +
                "employeecategory='" + employeecategory + '\'' +
                ", hiredate='" + hiredate + '\'' +
                ", hrstatus='" + hrstatus + '\'' +
                ", natureofemployement='" + natureofemployement + '\'' +
                ", payrollstatus='" + payrollstatus + '\'' +
                ", personid='" + personid + '\'' +
                ", personname='" + personname + '\'' +
                ", personnumber='" + personnumber + '\'' +
                ", efectenddate='" + efectenddate + '\'' +
                ", mappingid=" + mappingid +
                '}';
    }

    public String getEfectenddate() {
        return efectenddate;
    }

    public void setEfectenddate(String efectenddate) {
        this.efectenddate = efectenddate;
    }

    public long getMappingid() {
        return mappingid;
    }

    public void setMappingid(long mappingid) {
        this.mappingid = mappingid;
    }

    public String getEmployeecategory() {
        return employeecategory;
    }

    public void setEmployeecategory(String employeecategory) {
        this.employeecategory = employeecategory;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getHrstatus() {
        return hrstatus;
    }

    public void setHrstatus(String hrstatus) {
        this.hrstatus = hrstatus;
    }

    public String getNatureofemployement() {
        return natureofemployement;
    }

    public void setNatureofemployement(String natureofemployement) {
        this.natureofemployement = natureofemployement;
    }

    public String getPayrollstatus() {
        return payrollstatus;
    }

    public void setPayrollstatus(String payrollstatus) {
        this.payrollstatus = payrollstatus;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getPersonnumber() {
        return personnumber;
    }

    public void setPersonnumber(String personnumber) {
        this.personnumber = personnumber;
    }
}

