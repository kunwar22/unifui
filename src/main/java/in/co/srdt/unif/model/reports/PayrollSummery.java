package in.co.srdt.unif.model.reports;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PayrollSummery {

	private String personid;
	private String personnumber;
	private String personname;
	private String dateofbirth;
	private String employeetype;
	private String designation;
	private String baselocation;
	private String worklocation;
	private String joiningdate;
	private String phonenumber;
	private String emailaid;
	private String gender;
	private String maritalstatus;
	private String hrstatus;
	private String payrollstatus;
	private String businessunit;
	private String department;
	private String job;
	private String scale;
	private String level;
	private String pfnumber;
	private String prannumber;
	private String uannumber;
	private String monthofincrement;
	private String aadharnumber;
	private String pannumber;
	private String accounttype;
	private String bankaccountnumber;
	private String branchifsccode;
	private String bankname;
	private String bankaddress;
	private List<PayrollData> payrolldata;
	
	
	public PayrollSummery() {
		payrolldata = new ArrayList<>();
		payrolldata.add(new PayrollData());
	}
	
	public PayrollSummery(String personid, String personnumber, String personname, String dateofbirth,
			String employeetype, String designation, String baselocation, String worklocation, String joiningdate,
			String phonenumber, String emailaid, String gender, String maritalstatus, String hrstatus,
			String payrollstatus, String businessunit, String department, String job, String scale, String level,
			String pfnumber, String prannumber, String uannumber, String monthofincrement, String aadharnumber,
			String pannumber, String accounttype, String bankaccountnumber, String branchifsccode, String bankname,
			String bankaddress, List<PayrollData> payrolldata) {
		super();
		this.personid = personid;
		this.personnumber = personnumber;
		this.personname = personname;
		this.dateofbirth = dateofbirth;
		this.employeetype = employeetype;
		this.designation = designation;
		this.baselocation = baselocation;
		this.worklocation = worklocation;
		this.joiningdate = joiningdate;
		this.phonenumber = phonenumber;
		this.emailaid = emailaid;
		this.gender = gender;
		this.maritalstatus = maritalstatus;
		this.hrstatus = hrstatus;
		this.payrollstatus = payrollstatus;
		this.businessunit = businessunit;
		this.department = department;
		this.job = job;
		this.scale = scale;
		this.level = level;
		this.pfnumber = pfnumber;
		this.prannumber = prannumber;
		this.uannumber = uannumber;
		this.monthofincrement = monthofincrement;
		this.aadharnumber = aadharnumber;
		this.pannumber = pannumber;
		this.accounttype = accounttype;
		this.bankaccountnumber = bankaccountnumber;
		this.branchifsccode = branchifsccode;
		this.bankname = bankname;
		this.bankaddress = bankaddress;
		this.payrolldata = payrolldata;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
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

	public String getEmailaid() {
		return emailaid;
	}

	public void setEmailaid(String emailaid) {
		this.emailaid = emailaid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalstatus() {
		return maritalstatus;
	}

	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
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

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPfnumber() {
		return pfnumber;
	}

	public void setPfnumber(String pfnumber) {
		this.pfnumber = pfnumber;
	}

	public String getPrannumber() {
		return prannumber;
	}

	public void setPrannumber(String prannumber) {
		this.prannumber = prannumber;
	}

	public String getUannumber() {
		return uannumber;
	}

	public void setUannumber(String uannumber) {
		this.uannumber = uannumber;
	}

	public String getMonthofincrement() {
		return monthofincrement;
	}

	public void setMonthofincrement(String monthofincrement) {
		this.monthofincrement = monthofincrement;
	}

	public String getAadharnumber() {
		return aadharnumber;
	}

	public void setAadharnumber(String aadharnumber) {
		this.aadharnumber = aadharnumber;
	}

	public String getPannumber() {
		return pannumber;
	}

	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getBankaccountnumber() {
		return bankaccountnumber;
	}

	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}

	public String getBranchifsccode() {
		return branchifsccode;
	}

	public void setBranchifsccode(String branchifsccode) {
		this.branchifsccode = branchifsccode;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankaddress() {
		return bankaddress;
	}

	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}

	public List<PayrollData> getPayrolldata() {
		return payrolldata;
	}

	public void setPayrolldata(List<PayrollData> payrolldata) {
		this.payrolldata = payrolldata;
	}
	

	@Override
	public String toString() {
		return "PayrollSummery [personid=" + personid + ", personnumber=" + personnumber + ", personname=" + personname
				+ ", dateofbirth=" + dateofbirth + ", employeetype=" + employeetype + ", designation=" + designation
				+ ", baselocation=" + baselocation + ", worklocation=" + worklocation + ", joiningdate=" + joiningdate
				+ ", phonenumber=" + phonenumber + ", emailaid=" + emailaid + ", gender=" + gender + ", maritalstatus="
				+ maritalstatus + ", hrstatus=" + hrstatus + ", payrollstatus=" + payrollstatus + ", businessunit="
				+ businessunit + ", department=" + department + ", job=" + job + ", scale=" + scale + ", level=" + level
				+ ", pfnumber=" + pfnumber + ", prannumber=" + prannumber + ", uannumber=" + uannumber
				+ ", monthofincrement=" + monthofincrement + ", aadharnumber=" + aadharnumber + ", pannumber="
				+ pannumber + ", accounttype=" + accounttype + ", bankaccountnumber=" + bankaccountnumber
				+ ", branchifsccode=" + branchifsccode + ", bankname=" + bankname + ", bankaddress=" + bankaddress
				+ ", payrolldata=" + payrolldata + "]";
	}

	
	
}
