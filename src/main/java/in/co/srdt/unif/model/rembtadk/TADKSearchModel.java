package in.co.srdt.unif.model.rembtadk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TADKSearchModel {
	private long claimid;
	private String tadkname;
	private String month;
	private long reimburseid;
	private String reimbursename;
	private String personid;
	private String personnumber;
	private long approvedamt;
	private long claimamt;
	private long eligibleamt;
	private String comment;
	private String createddate;
	private String status;
	
	public TADKSearchModel() {
		super();
	}

	public TADKSearchModel(long claimid, String tadkname, String month, long reimburseid, String reimbursename,
			String personid, String personnumber, long approvedamt, long claimamt, long eligibleamt, String comment,
			String createddate, String status) {
		super();
		this.claimid = claimid;
		this.tadkname = tadkname;
		this.month = month;
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.personid = personid;
		this.personnumber = personnumber;
		this.approvedamt = approvedamt;
		this.claimamt = claimamt;
		this.eligibleamt = eligibleamt;
		this.comment = comment;
		this.createddate = createddate;
		this.status = status;
	}

	public long getClaimid() {
		return claimid;
	}

	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}

	public String getTadkname() {
		return tadkname;
	}

	public void setTadkname(String tadkname) {
		this.tadkname = tadkname;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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

	public long getApprovedamt() {
		return approvedamt;
	}

	public void setApprovedamt(long approvedamt) {
		this.approvedamt = approvedamt;
	}

	public long getClaimamt() {
		return claimamt;
	}

	public void setClaimamt(long claimamt) {
		this.claimamt = claimamt;
	}

	public long getEligibleamt() {
		return eligibleamt;
	}

	public void setEligibleamt(long eligibleamt) {
		this.eligibleamt = eligibleamt;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreateddate() {
		return createddate;
	}



	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TADKSearchModel [claimid=" + claimid + ", tadkname=" + tadkname + ", month=" + month + ", reimburseid="
				+ reimburseid + ", reimbursename=" + reimbursename + ", personid=" + personid + ", personnumber="
				+ personnumber + ", approvedamt=" + approvedamt + ", claimamt=" + claimamt + ", eligibleamt="
				+ eligibleamt + ", comment=" + comment + ", createddate=" + createddate + ", status=" + status + "]";
	}

	
	
	
}
