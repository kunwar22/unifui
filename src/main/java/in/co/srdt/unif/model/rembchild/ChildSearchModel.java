package in.co.srdt.unif.model.rembchild;

import java.util.ArrayList;
import java.util.List;

public class ChildSearchModel {

	private String reimburseid;

	private String reimbursename;

	private String childclaimid;

	private String acadyear;

	private String feetype;

	private String personnumber;

	private String personid;
	
	private String status;
	
	private double totalfees;
	
	private String dates;
	
	private double apramt;

//	private List<ChildModel> childs;
		
	private String approvestatus;

	private String approveamt;

	public ChildSearchModel() {
//		childs = new ArrayList<>();
//		childs.add(new ChildModel());
	}

	public String getReimburseid() {
		return reimburseid;
	}

	public void setReimburseid(String reimburseid) {
		this.reimburseid = reimburseid;
	}

	public String getReimbursename() {
		return reimbursename;
	}

	public void setReimbursename(String reimbursename) {
		this.reimbursename = reimbursename;
	}

	public String getChildclaimid() {
		return childclaimid;
	}

	public void setChildclaimid(String childclaimid) {
		this.childclaimid = childclaimid;
	}

	public String getAcadyear() {
		return acadyear;
	}

	public void setAcadyear(String acadyear) {
		this.acadyear = acadyear;
	}

	public String getFeetype() {
		return feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalfees() {
		return totalfees;
	}

	public void setTotalfees(double totalfees) {
		this.totalfees = totalfees;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public double getApramt() {
		return apramt;
	}

	public void setApramt(double apramt) {
		this.apramt = apramt;
	}

	public String getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(String approvestatus) {
		this.approvestatus = approvestatus;
	}

	public String getApproveamt() {
		return approveamt;
	}

	public void setApproveamt(String approveamt) {
		this.approveamt = approveamt;
	}

	@Override
	public String toString() {
		return "ChildSearchModel [reimburseid=" + reimburseid + ", reimbursename=" + reimbursename + ", childclaimid="
				+ childclaimid + ", acadyear=" + acadyear + ", feetype=" + feetype + ", personnumber=" + personnumber
				+ ", personid=" + personid + ", status=" + status + ", totalfees=" + totalfees + ", dates=" + dates
				+ ", apramt=" + apramt + ", approvestatus=" + approvestatus + ", approveamt=" + approveamt + "]";
	}

	

	
	

}
