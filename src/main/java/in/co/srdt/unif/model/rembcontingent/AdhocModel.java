package in.co.srdt.unif.model.rembcontingent;

import java.util.ArrayList;
import java.util.List;

public class AdhocModel {
	private long reimburseid=6;
	private String reimbursename="Contingent";
	private String personnumber;
	private String personid;
	private long claimid;
	private String status="Saved";
	private String submitdate;
	private long claimamt;
	private double approvedamt;
	private String submitStatus;
	List<AdhocListModel> travelbillclaimdetails= new ArrayList<>();

	public AdhocModel() {
		super();
	}

	public AdhocModel(long reimburseid, String reimbursename, String personnumber, String personid, long claimid, String status, String submitdate, long claimamt, double approvedamt, String submitStatus, List<AdhocListModel> travelbillclaimdetails) {
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.personnumber = personnumber;
		this.personid = personid;
		this.claimid = claimid;
		this.status = status;
		this.submitdate = submitdate;
		this.claimamt = claimamt;
		this.approvedamt = approvedamt;
		this.submitStatus = submitStatus;
		this.travelbillclaimdetails = travelbillclaimdetails;
	}

	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

	public long getReimburseid() {
		return reimburseid;
	}

	public void setReimburseid(long reimburseid) {
		this.reimburseid = reimburseid;
	}

	public String getReimbursename() {
		return reimbursename;
	}

	public void setReimbursename(String reimbursename) {
		this.reimbursename = reimbursename;
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

	public long getClaimid() {
		return claimid;
	}

	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(String submitdate) {
		this.submitdate = submitdate;
	}

	public long getClaimamt() {
		return claimamt;
	}

	public void setClaimamt(long claimamt) {
		this.claimamt = claimamt;
	}

	public double getApprovedamt() {
		return approvedamt;
	}

	public void setApprovedamt(double approvedamt) {
		this.approvedamt = approvedamt;
	}

	public List<AdhocListModel> getTravelbillclaimdetails() {
		return travelbillclaimdetails;
	}

	public void setTravelbillclaimdetails(List<AdhocListModel> travelbillclaimdetails) {
		this.travelbillclaimdetails = travelbillclaimdetails;
	}

	@Override
	public String toString() {
		return "AdhocModel [reimburseid=" + reimburseid + ", reimbursename=" + reimbursename + ", personnumber="
				+ personnumber + ", personid=" + personid + ", claimid=" + claimid + ", status=" + status
				+ ", submitdate=" + submitdate + ", claimamt=" + claimamt + ", approvedamt=" + approvedamt
				+ ", travelbillclaimdetails=" + travelbillclaimdetails + "]";
	}
	
}
