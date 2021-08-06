package in.co.srdt.unif.model.newperson;

import javax.validation.constraints.Min;

public class UPMRCAdditionalDetails {

	private long actionid;

	private long lmrcbasicdetailsid;

	private String personid;

	private String personnumber;

	@Min(value = 1,message = "Please select Nature of Employment.")
	private long natureofemployement;

	private long employeecategory;

	private long staff;

	private long projectom;

	private String houseonleave; 
	
	private String effectiveenddate="4712-12-31";
	
	private double prevorgbasicsalary;

	private String prevorglocation;

	private String prevorgcompanyname;

	private String effectivestartdate;
	
	private String createdby;

	private String updatedby;
	
	private String attribute2;
	
	private String attribute3;
	
	private String attribute4;

	private String pfnumber;

	private String prannumber;

	private String esinumber;

	private String uannumber;

	private  String pfeffectivestartdate;

	private String monthofincrement;
	
	public UPMRCAdditionalDetails() {
	}

	public UPMRCAdditionalDetails(String monthofincrement, long actionid, long lmrcbasicdetailsid, String personid, String personnumber, @Min(value = 1, message = "Please select Nature of Employment.") long natureofemployement, long employeecategory, long staff, long projectom, String houseonleave, String effectiveenddate, double prevorgbasicsalary, String prevorglocation, String prevorgcompanyname, String effectivestartdate, String createdby, String updatedby, String attribute2, String attribute3, String attribute4, String pfnumber, String prannumber, String esinumber, String uannumber, String pfeffectivestartdate) {
		this.actionid = actionid;
		this.lmrcbasicdetailsid = lmrcbasicdetailsid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.natureofemployement = natureofemployement;
		this.employeecategory = employeecategory;
		this.staff = staff;
		this.projectom = projectom;
		this.houseonleave = houseonleave;
		this.effectiveenddate = effectiveenddate;
		this.prevorgbasicsalary = prevorgbasicsalary;
		this.prevorglocation = prevorglocation;
		this.prevorgcompanyname = prevorgcompanyname;
		this.effectivestartdate = effectivestartdate;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.pfnumber = pfnumber;
		this.prannumber = prannumber;
		this.esinumber = esinumber;
		this.uannumber = uannumber;
		this.pfeffectivestartdate = pfeffectivestartdate;
		this.monthofincrement = monthofincrement;
	}

	@Override
	public String toString() {
		return "UPMRCAdditionalDetails{" +
				"actionid=" + actionid +
				", lmrcbasicdetailsid=" + lmrcbasicdetailsid +
				", personid='" + personid + '\'' +
				", personnumber='" + personnumber + '\'' +
				", natureofemployement=" + natureofemployement +
				", employeecategory=" + employeecategory +
				", staff=" + staff +
				", projectom=" + projectom +
				", houseonleave='" + houseonleave + '\'' +
				", effectiveenddate='" + effectiveenddate + '\'' +
				", prevorgbasicsalary=" + prevorgbasicsalary +
				", prevorglocation='" + prevorglocation + '\'' +
				", prevorgcompanyname='" + prevorgcompanyname + '\'' +
				", effectivestartdate='" + effectivestartdate + '\'' +
				", createdby='" + createdby + '\'' +
				", updatedby='" + updatedby + '\'' +
				", attribute2='" + attribute2 + '\'' +
				", attribute3='" + attribute3 + '\'' +
				", attribute4='" + attribute4 + '\'' +
				", pfnumber='" + pfnumber + '\'' +
				", prannumber='" + prannumber + '\'' +
				", esinumber='" + esinumber + '\'' +
				", uannumber='" + uannumber + '\'' +
				", pfeffectivestartdate='" + pfeffectivestartdate + '\'' +
				", monthofincrement='" + monthofincrement + '\'' +
				'}';
	}

	public String getMonthofincrement() {
		return monthofincrement;
	}

	public void setMonthofincrement(String monthofincrement) {
		this.monthofincrement = monthofincrement;
	}

	public String getPfeffectivestartdate() {
		return pfeffectivestartdate;
	}

	public void setPfeffectivestartdate(String pfeffectivestartdate) {
		this.pfeffectivestartdate = pfeffectivestartdate;
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

	public String getEsinumber() {
		return esinumber;
	}

	public void setEsinumber(String esinumber) {
		this.esinumber = esinumber;
	}

	public String getUannumber() {
		return uannumber;
	}

	public void setUannumber(String uannumber) {
		this.uannumber = uannumber;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getLmrcbasicdetailsid() {
		return lmrcbasicdetailsid;
	}

	public void setLmrcbasicdetailsid(long lmrcbasicdetailsid) {
		this.lmrcbasicdetailsid = lmrcbasicdetailsid;
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

	public long getNatureofemployement() {
		return natureofemployement;
	}

	public void setNatureofemployement(long natureofemployement) {
		this.natureofemployement = natureofemployement;
	}

	public long getEmployeecategory() {
		return employeecategory;
	}

	public void setEmployeecategory(long employeecategory) {
		this.employeecategory = employeecategory;
	}

	public long getStaff() {
		return staff;
	}

	public void setStaff(long staff) {
		this.staff = staff;
	}

	public long getProjectom() {
		return projectom;
	}

	public void setProjectom(long projectom) {
		this.projectom = projectom;
	}

	public String getHouseonleave() {
		return houseonleave;
	}

	public void setHouseonleave(String houseonleave) {
		this.houseonleave = houseonleave;
	}

	public String getEffectiveenddate() {
		return effectiveenddate;
	}

	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}

	public double getPrevorgbasicsalary() {
		return prevorgbasicsalary;
	}

	public void setPrevorgbasicsalary(double prevorgbasicsalary) {
		this.prevorgbasicsalary = prevorgbasicsalary;
	}

	public String getPrevorglocation() {
		return prevorglocation;
	}

	public void setPrevorglocation(String prevorglocation) {
		this.prevorglocation = prevorglocation;
	}

	public String getPrevorgcompanyname() {
		return prevorgcompanyname;
	}

	public void setPrevorgcompanyname(String prevorgcompanyname) {
		this.prevorgcompanyname = prevorgcompanyname;
	}

	public String getEffectivestartdate() {
		return effectivestartdate;
	}

	public void setEffectivestartdate(String effectivestartdate) {
		this.effectivestartdate = effectivestartdate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

}
