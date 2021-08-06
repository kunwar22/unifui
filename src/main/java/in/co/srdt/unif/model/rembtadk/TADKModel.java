package in.co.srdt.unif.model.rembtadk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class TADKModel {
	private long claimid;
	
	@NotBlank(message = "Please fill name of TADK")
	private String tadkname;
	
	@NotBlank(message = "Please select month for TADK reimbursement")
	private String month;
	private long reimburseid;
	private String reimbursename="TADK";
	private String personid;
	private String personnumber;
	private String approvedamt;

	@NotBlank(message="Please Fill Claim Amount")
	private String claimamt;
	private long eligibleamt;
	private String comment;
	private String createddate;
	private String status;
	private String attachment;
	private String attachhidden;
	
	public TADKModel() {
		super();		
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
	public String getApprovedamt() {
		return approvedamt;
	}
	public void setApprovedamt(String aprovedamt) {
		this.approvedamt = aprovedamt;
	}
	public String getClaimamt() {
		return claimamt;
	}
	public void setClaimamt(String claimamt) {
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
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDateTime today = LocalDateTime.now();
		this.createddate = dtf.format(today);
		
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}		

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachhidden() {
		return attachhidden;
	}

	public void setAttachhidden(String attachhidden) {
		this.attachhidden = attachhidden;
	}

	public TADKModel(long claimid, @NotBlank(message = "Please fill name of TADK") String tadkname, @NotBlank(message = "Please select month for TADK reimbursement") String month, long reimburseid, String reimbursename, String personid, String personnumber, String approvedamt, @NotBlank(message = "Please Fill Claim Amount") String claimamt, long eligibleamt, String comment, String createddate, String status, String attachment, String attachhidden) {
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
		this.attachment = attachment;
		this.attachhidden = attachhidden;
	}

	@Override
	public String toString() {
		return "TADKModel{" +
				"claimid=" + claimid +
				", tadkname='" + tadkname + '\'' +
				", month='" + month + '\'' +
				", reimburseid=" + reimburseid +
				", reimbursename='" + reimbursename + '\'' +
				", personid='" + personid + '\'' +
				", personnumber='" + personnumber + '\'' +
				", approvedamt='" + approvedamt + '\'' +
				", claimamt='" + claimamt + '\'' +
				", eligibleamt=" + eligibleamt +
				", comment='" + comment + '\'' +
				", createddate='" + createddate + '\'' +
				", status='" + status + '\'' +
				", attachment='" + attachment + '\'' +
				", attachhidden='" + attachhidden + '\'' +
				'}';
	}
}
