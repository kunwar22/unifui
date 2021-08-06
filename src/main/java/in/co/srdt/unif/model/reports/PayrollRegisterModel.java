package in.co.srdt.unif.model.reports;

import java.util.List;

public class PayrollRegisterModel {

	private String personnumber;
	private String personname;
	private String businessunit;
	private String natureofempl;
	private String accounttype;
	private String position;
	private String prannumber;
	private String deptname;
	private String gradename;
	private String workingday;
	private List<ElementModel> elementAmt;
	
	public PayrollRegisterModel() {
		super();
	}

	public PayrollRegisterModel(String personnumber, String personname, String businessunit, String natureofempl,
			String accounttype, String position, String prannumber, String deptname, String gradename,
			String workingday, List<ElementModel> elementAmt) {
		super();
		this.personnumber = personnumber;
		this.personname = personname;
		this.businessunit = businessunit;
		this.natureofempl = natureofempl;
		this.accounttype = accounttype;
		this.position = position;
		this.prannumber = prannumber;
		this.deptname = deptname;
		this.gradename = gradename;
		this.workingday = workingday;
		this.elementAmt = elementAmt;
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

	public String getBusinessunit() {
		return businessunit;
	}

	public void setBusinessunit(String businessunit) {
		this.businessunit = businessunit;
	}

	public String getNatureofempl() {
		return natureofempl;
	}

	public void setNatureofempl(String natureofempl) {
		this.natureofempl = natureofempl;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPrannumber() {
		return prannumber;
	}

	public void setPrannumber(String prannumber) {
		this.prannumber = prannumber;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getWorkingday() {
		return workingday;
	}

	public void setWorkingday(String workingday) {
		this.workingday = workingday;
	}

	public List<ElementModel> getElementAmt() {
		return elementAmt;
	}

	public void setElementAmt(List<ElementModel> elementAmt) {
		this.elementAmt = elementAmt;
	}

	@Override
	public String toString() {
		return "PayrollRegisterModel [personnumber=" + personnumber + ", personname=" + personname + ", businessunit="
				+ businessunit + ", natureofempl=" + natureofempl + ", accounttype=" + accounttype + ", position="
				+ position + ", prannumber=" + prannumber + ", deptname=" + deptname + ", gradename=" + gradename
				+ ", workingday=" + workingday + ", elementAmt=" + elementAmt + "]";
	}

	
}
