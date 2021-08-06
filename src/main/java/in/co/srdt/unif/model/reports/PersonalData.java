package in.co.srdt.unif.model.reports;

public class PersonalData {
	private String sno;
	private String empid;
	private String empname;
	private String empstatus;
	private String assignmentstatus;
	private String gender;
	private String fathername;
	private String designation;
	private String department;
	private String doj;
	private String dob;
	private String email;
	private String address;
	private String marital;
	private String phone;
	private String pan;
	private String adhar;
	private String pran;
	private String uan;
	private String pfnumber;
	private String monthofincrement;
	private String bankname;
	private String bankaccount;
	private String ifsc;
	private String natureofemployment;
	private String centralgovemployee;
	private String empcategory;
	private String staff;
	private String projectom;
	private String job;
	private String comppaidaccom;//quarter value
	private String comppaidaccomlease;//lease value
	private String hra;
	private String vehicleused;
	private String leavingreason;
	private String notifieddate;
	private String actualtermination;
	private String laststandardprocess;
	private String finalprocessdate;
	private String worklocation;
		
	public PersonalData() {
		super();
	}
		
	public PersonalData(String sno, String empid, String empname, String empstatus, String assignmentstatus,
			String gender, String fathername, String designation, String department, String doj, String dob,
			String email, String address, String marital, String phone, String pan, String adhar, String pran,
			String uan, String pfnumber, String monthofincrement, String bankname, String bankaccount, String ifsc,
			String natureofemployment, String centralgovemployee, String empcategory, String staff, String projectom,
			String job, String comppaidaccom, String comppaidaccomlease, String hra, String vehicleused, String leavingreason, String notifieddate,
			String actualtermination, String laststandardprocess, String finalprocessdate, String worklocation) {
		super();
		this.sno = sno;
		this.empid = empid;
		this.empname = empname;
		this.empstatus = empstatus;
		this.assignmentstatus = assignmentstatus;
		this.gender = gender;
		this.fathername = fathername;
		this.designation = designation;
		this.department = department;
		this.doj = doj;
		this.dob = dob;
		this.email = email;
		this.address = address;
		this.marital = marital;
		this.phone = phone;
		this.pan = pan;
		this.adhar = adhar;
		this.pran = pran;
		this.uan = uan;
		this.pfnumber = pfnumber;
		this.monthofincrement = monthofincrement;
		this.bankname = bankname;
		this.bankaccount = bankaccount;
		this.ifsc = ifsc;
		this.natureofemployment = natureofemployment;
		this.centralgovemployee = centralgovemployee;
		this.empcategory = empcategory;
		this.staff = staff;
		this.projectom = projectom;
		this.job = job;
		this.comppaidaccom = comppaidaccom;
		this.comppaidaccomlease = comppaidaccomlease;
		this.hra = hra;
		this.vehicleused = vehicleused;
		this.leavingreason = leavingreason;
		this.notifieddate = notifieddate;
		this.actualtermination = actualtermination;
		this.laststandardprocess = laststandardprocess;
		this.finalprocessdate = finalprocessdate;
		this.worklocation = worklocation;
	}


	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpstatus() {
		return empstatus;
	}
	public void setEmpstatus(String empstatus) {
		this.empstatus = empstatus;
	}
	public String getAssignmentstatus() {
		return assignmentstatus;
	}
	public void setAssignmentstatus(String assignmentstatus) {
		this.assignmentstatus = assignmentstatus;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMarital() {
		return marital;
	}
	public void setMarital(String marital) {
		this.marital = marital;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAdhar() {
		return adhar;
	}
	public void setAdhar(String adhar) {
		this.adhar = adhar;
	}
	public String getPran() {
		return pran;
	}
	public void setPran(String pran) {
		this.pran = pran;
	}
	public String getUan() {
		return uan;
	}
	public void setUan(String uan) {
		this.uan = uan;
	}
	public String getPfnumber() {
		return pfnumber;
	}
	public void setPfnumber(String pfnumber) {
		this.pfnumber = pfnumber;
	}
	public String getMonthofincrement() {
		return monthofincrement;
	}
	public void setMonthofincrement(String monthofincrement) {
		this.monthofincrement = monthofincrement;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankaccount() {
		return bankaccount;
	}
	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getNatureofemployment() {
		return natureofemployment;
	}
	public void setNatureofemployment(String natureofemployment) {
		this.natureofemployment = natureofemployment;
	}
	public String getCentralgovemployee() {
		return centralgovemployee;
	}
	public void setCentralgovemployee(String centralgovemployee) {
		this.centralgovemployee = centralgovemployee;
	}
	public String getEmpcategory() {
		return empcategory;
	}
	public void setEmpcategory(String empcategory) {
		this.empcategory = empcategory;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String getProjectom() {
		return projectom;
	}
	public void setProjectom(String projectom) {
		this.projectom = projectom;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getComppaidaccom() {
		return comppaidaccom;
	}
	public void setComppaidaccom(String comppaidaccom) {
		this.comppaidaccom = comppaidaccom;
	}	
	
	public String getComppaidaccomlease() {
		return comppaidaccomlease;
	}

	public void setComppaidaccomlease(String comppaidaccomlease) {
		this.comppaidaccomlease = comppaidaccomlease;
	}

	public String getHra() {
		return hra;
	}
	public void setHra(String hra) {
		this.hra = hra;
	}
	public String getVehicleused() {
		return vehicleused;
	}
	public void setVehicleused(String vehicleused) {
		this.vehicleused = vehicleused;
	}
	public String getLeavingreason() {
		return leavingreason;
	}
	public void setLeavingreason(String leavingreason) {
		this.leavingreason = leavingreason;
	}
	public String getNotifieddate() {
		return notifieddate;
	}
	public void setNotifieddate(String notifieddate) {
		this.notifieddate = notifieddate;
	}
	public String getActualtermination() {
		return actualtermination;
	}
	public void setActualtermination(String actualtermination) {
		this.actualtermination = actualtermination;
	}
	public String getLaststandardprocess() {
		return laststandardprocess;
	}
	public void setLaststandardprocess(String laststandardprocess) {
		this.laststandardprocess = laststandardprocess;
	}
	public String getFinalprocessdate() {
		return finalprocessdate;
	}
	public void setFinalprocessdate(String finalprocessdate) {
		this.finalprocessdate = finalprocessdate;
	}
	public String getWorklocation() {
		return worklocation;
	}
	public void setWorklocation(String worklocation) {
		this.worklocation = worklocation;
	}

	@Override
	public String toString() {
		return "PersonalData [sno=" + sno + ", empid=" + empid + ", empname=" + empname + ", empstatus=" + empstatus
				+ ", assignmentstatus=" + assignmentstatus + ", gender=" + gender + ", fathername=" + fathername
				+ ", designation=" + designation + ", department=" + department + ", doj=" + doj + ", dob=" + dob
				+ ", email=" + email + ", address=" + address + ", marital=" + marital + ", phone=" + phone + ", pan="
				+ pan + ", adhar=" + adhar + ", pran=" + pran + ", uan=" + uan + ", pfnumber=" + pfnumber
				+ ", monthofincrement=" + monthofincrement + ", bankname=" + bankname + ", bankaccount=" + bankaccount
				+ ", ifsc=" + ifsc + ", natureofemployment=" + natureofemployment + ", centralgovemployee="
				+ centralgovemployee + ", empcategory=" + empcategory + ", staff=" + staff + ", projectom=" + projectom
				+ ", job=" + job + ", comppaidaccom=" + comppaidaccom + ", comppaidaccomlease=" + comppaidaccomlease
				+ ", hra=" + hra + ", vehicleused=" + vehicleused + ", leavingreason=" + leavingreason
				+ ", notifieddate=" + notifieddate + ", actualtermination=" + actualtermination
				+ ", laststandardprocess=" + laststandardprocess + ", finalprocessdate=" + finalprocessdate
				+ ", worklocation=" + worklocation + "]";
	}
	
		
}
