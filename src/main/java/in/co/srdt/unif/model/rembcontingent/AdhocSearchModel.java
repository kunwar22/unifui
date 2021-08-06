package in.co.srdt.unif.model.rembcontingent;

public class AdhocSearchModel {
	private String claimid;
	private String reimburseid;
	private String reimbursename;
	private String personnumber;
	private String personid;
	private String status;
	private String submitdate;
	private String claimamt;
	private String approvedamt;
	private String travelbillclaimdetails;
	
	public AdhocSearchModel() {
		super();
	}

	public AdhocSearchModel(String claimid, String reimburseid, String reimbursename, String personnumber,
			String personid, String status, String submitdate, String claimamt, String approvedamt,
			String travelbillclaimdetails) {
		super();
		this.claimid = claimid;
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.personnumber = personnumber;
		this.personid = personid;
		this.status = status;
		this.submitdate = submitdate;
		this.claimamt = claimamt;
		this.approvedamt = approvedamt;
		this.travelbillclaimdetails = travelbillclaimdetails;
	}

	public String getClaimid() {
		return claimid;
	}

	public void setClaimid(String claimid) {
		this.claimid = claimid;
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

	public String getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(String submitdate) {
		this.submitdate = submitdate;
	}

	public String getClaimamt() {
		return claimamt;
	}

	public void setClaimamt(String claimamt) {
		this.claimamt = claimamt;
	}

	public String getApprovedamt() {
		return approvedamt;
	}

	public void setApprovedamt(String approvedamt) {
		this.approvedamt = approvedamt;
	}

	public String getTravelbillclaimdetails() {
		return travelbillclaimdetails;
	}

	public void setTravelbillclaimdetails(String travelbillclaimdetails) {
		this.travelbillclaimdetails = travelbillclaimdetails;
	}

	@Override
	public String toString() {
		return "AdhocSearchModel [claimid=" + claimid + ", reimburseid=" + reimburseid + ", reimbursename="
				+ reimbursename + ", personnumber=" + personnumber + ", personid=" + personid + ", status=" + status
				+ ", submitdate=" + submitdate + ", claimamt=" + claimamt + ", approvedamt=" + approvedamt
				+ ", travelbillclaimdetails=" + travelbillclaimdetails + "]";
	}
	
}
