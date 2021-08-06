package in.co.srdt.unif.model.rembchild;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Component
public class ChildReimburseModel {

	private int reimburseid; // this will be auto generated

	private String reimbursename;
	
	private int childclaimid;
	
	@NotBlank(message = "Please select Academic Year")
	private String acadyear;

	@NotBlank(message = "Please select Fees Type")
	private String feetype;

	private String personnumber;

	private String personid;
	
	private String status;
	
	private long totalfees;
	
	private String dates;
	
	private long apramt;

	private String submitStatus;

	private List<ChildModel> childs;

	public ChildReimburseModel() {
		childs = new ArrayList<>();
		childs.add(new ChildModel());
	}

	public ChildReimburseModel(int reimburseid, String reimbursename, int childclaimid, @NotBlank(message = "Please select Academic Year") String acadyear, @NotBlank(message = "Please select Fees Type") String feetype, String personnumber, String personid, String status, long totalfees, String dates, long apramt, String submitStatus, List<ChildModel> childs) {
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.childclaimid = childclaimid;
		this.acadyear = acadyear;
		this.feetype = feetype;
		this.personnumber = personnumber;
		this.personid = personid;
		this.status = status;
		this.totalfees = totalfees;
		this.dates = dates;
		this.apramt = apramt;
		this.submitStatus = submitStatus;
		this.childs = childs;
	}

	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

	public int getReimburseid() {
		return reimburseid;
	}

	public void setReimburseid(int reimburseid) {
		this.reimburseid = reimburseid;
	}
	
	public String getReimbursename() {
		return reimbursename;
	}

	public void setReimbursename(String reimbursename) {
		this.reimbursename = reimbursename;
	}	

	public int getChildclaimid() {
		return childclaimid;
	}

	public void setChildclaimid(int childclaimid) {
		this.childclaimid = childclaimid;
	}

	public String getacadyear() {
		return acadyear;
	}

	public void setacadyear(String acadyear) {
		this.acadyear = acadyear;
	}

	public String getfeetype() {
		return feetype;
	}

	public void setfeetype(String feetype) {
		this.feetype = feetype;
	}

	public String getpersonnumber() {
		return personnumber;
	}

	public void setpersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public String getpersonid() {
		return personid;
	}

	public void setpersonid(String personid) {
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

	public void setTotalfees(long totalfees) {
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

	public void setApramt(long apramt) {
		this.apramt = apramt;
	}

	public List<ChildModel> getchilds() {
		return childs;
	}

	public void setchilds(List<ChildModel> childs) {
		this.childs = childs;
	}

	@Override
	public String toString() {
		return "ChildReimburseModel [reimburseid=" + reimburseid + ", reimbursename=" + reimbursename
				+ ", childclaimid=" + childclaimid + ", acadyear=" + acadyear + ", feetype=" + feetype
				+ ", personnumber=" + personnumber + ", personid=" + personid + ", status=" + status + ", totalfees="
				+ totalfees + ", dates=" + dates + ", apramt=" + apramt + ", childs=" + childs + "]";
	}


}
