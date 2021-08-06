package in.co.srdt.unif.model.reimbursementconsolidation;

public class ShowReimbursementDetails {
	private long claimid;
	private String personid;
	private String personnumber;
	private long reimburseid;
	private String reimbursename;
	private double claimedamount;
	private double approvedamount;
	private String status;
	private String createddate;
	private String personname;
	public ShowReimbursementDetails() {
	
	}
	public long getClaimid() {
		return claimid;
	}
	public void setClaimid(long claimid) {
		this.claimid = claimid;
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
	public double getClaimedamount() {
		return claimedamount;
	}
	public void setClaimedamount(double claimedamount) {
		this.claimedamount = claimedamount;
	}
	public double getApprovedamount() {
		return approvedamount;
	}
	public void setApprovedamount(double approvedamount) {
		this.approvedamount = approvedamount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public ShowReimbursementDetails(long claimid, String personid, String personnumber, long reimburseid,
			String reimbursename, double claimedamount, double approvedamount, String status, String createddate,
			String personname) {
		super();
		this.claimid = claimid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.claimedamount = claimedamount;
		this.approvedamount = approvedamount;
		this.status = status;
		this.createddate = createddate;
		this.personname = personname;
	}
	@Override
	public String toString() {
		return "ShowReimbursementDetails [claimid=" + claimid + ", personid=" + personid + ", personnumber="
				+ personnumber + ", reimburseid=" + reimburseid + ", reimbursename=" + reimbursename
				+ ", claimedamount=" + claimedamount + ", approvedamount=" + approvedamount + ", status=" + status
				+ ", createddate=" + createddate + ", personname=" + personname + "]";
	}
	
	

}
