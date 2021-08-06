package in.co.srdt.unif.model.payroll;

public class Employee {
    String hiredate;
    String hrstatus;
    String payrollstatus;
    String personid;
    String personname;
    String personnumber;
    String emailaid;
    String dateofbirth;
    String employeetype;
    String designation;
    String baselocation;
    String worklocation;
    String joiningdate;
    String phonenumber;
    String businessunit;
    String department;
    String job;
    String natureofemployement;
    String employeecategory;

    public Employee() {
    }

    public Employee(String hiredate, String hrstatus, String payrollstatus, String personid, String personname, String personnumber, String emailaid, String dateofbirth, String employeetype, String designation, String baselocation, String worklocation, String joiningdate, String phonenumber, String businessunit, String department, String job, String natureofemployement, String employeecategory) {
        this.hiredate = hiredate;
        this.hrstatus = hrstatus;
        this.payrollstatus = payrollstatus;
        this.personid = personid;
        this.personname = personname;
        this.personnumber = personnumber;
        this.emailaid = emailaid;
        this.dateofbirth = dateofbirth;
        this.employeetype = employeetype;
        this.designation = designation;
        this.baselocation = baselocation;
        this.worklocation = worklocation;
        this.joiningdate = joiningdate;
        this.phonenumber = phonenumber;
        this.businessunit = businessunit;
        this.department = department;
        this.job = job;
        this.natureofemployement = natureofemployement;
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

    public String getEmailaid() {
        return emailaid;
    }

    public void setEmailaid(String emailaid) {
        this.emailaid = emailaid;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBaselocation() {
        return baselocation;
    }

    public void setBaselocation(String baselocation) {
        this.baselocation = baselocation;
    }

    public String getWorklocation() {
        return worklocation;
    }

    public void setWorklocation(String worklocation) {
        this.worklocation = worklocation;
    }

    public String getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(String joiningdate) {
        this.joiningdate = joiningdate;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBusinessunit() {
        return businessunit;
    }

    public void setBusinessunit(String businessunit) {
        this.businessunit = businessunit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getNatureofemployement() {
        return natureofemployement;
    }

    public void setNatureofemployement(String natureofemployement) {
        this.natureofemployement = natureofemployement;
    }

    public String getEmployeecategory() {
        return employeecategory;
    }

    public void setEmployeecategory(String employeecategory) {
        this.employeecategory = employeecategory;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "hiredate='" + hiredate + '\'' +
                ", hrstatus='" + hrstatus + '\'' +
                ", payrollstatus='" + payrollstatus + '\'' +
                ", personid='" + personid + '\'' +
                ", personname='" + personname + '\'' +
                ", personnumber='" + personnumber + '\'' +
                ", emailaid='" + emailaid + '\'' +
                ", dateofbirth='" + dateofbirth + '\'' +
                ", employeetype='" + employeetype + '\'' +
                ", designation='" + designation + '\'' +
                ", baselocation='" + baselocation + '\'' +
                ", worklocation='" + worklocation + '\'' +
                ", joiningdate='" + joiningdate + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", businessunit='" + businessunit + '\'' +
                ", department='" + department + '\'' +
                ", job='" + job + '\'' +
                ", natureofemployement='" + natureofemployement + '\'' +
                ", employeecategory='" + employeecategory + '\'' +
                '}';
    }
}
