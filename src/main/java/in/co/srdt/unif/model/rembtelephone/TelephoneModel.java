package in.co.srdt.unif.model.rembtelephone;

import javax.validation.constraints.NotBlank;

public class TelephoneModel {
	
	private long reimburseid;
	private String reimbursename;
	private long telephoneclaimid;
	private String personnumber;
	private String personid;
	private String submitStatus;
	
	
	@NotBlank(message = "Please enter phone number.")
	private String phoneno;
	
	@NotBlank(message = "Please select From Date.")
	private String fromdate;
	
	@NotBlank(message = "Please select To Date.")
	private String todate;
	private int duration;
	
	@NotBlank(message = "Please fill claim amount.")
	private String requestedamt ;
	
	private double approvedamt;
	
	@NotBlank(message = "Please select OM.")
	private String omproject;
	
	private String status;
	private String submiteddates;
	private String attachments;
	private String attachhidden;
	
	
	public TelephoneModel() {
	}


	public TelephoneModel(long reimburseid, String reimbursename, long telephoneclaimid, String personnumber,
			String personid, String submitStatus, @NotBlank(message = "Please enter phone number.") String phoneno,
			@NotBlank(message = "Please select From Date.") String fromdate,
			@NotBlank(message = "Please select To Date.") String todate, int duration,
			@NotBlank(message = "Please fill claim amount.") String requestedamt, double approvedamt,
			@NotBlank(message = "Please select OM.") String omproject, String status, String submiteddates,
			String attachments, String attachhidden) {
		super();
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.telephoneclaimid = telephoneclaimid;
		this.personnumber = personnumber;
		this.personid = personid;
		this.submitStatus = submitStatus;
		this.phoneno = phoneno;
		this.fromdate = fromdate;
		this.todate = todate;
		this.duration = duration;
		this.requestedamt = requestedamt;
		this.approvedamt = approvedamt;
		this.omproject = omproject;
		this.status = status;
		this.submiteddates = submiteddates;
		this.attachments = attachments;
		this.attachhidden = attachhidden;
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


	public long getTelephoneclaimid() {
		return telephoneclaimid;
	}


	public void setTelephoneclaimid(long telephoneclaimid) {
		this.telephoneclaimid = telephoneclaimid;
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


	public String getSubmitStatus() {
		return submitStatus;
	}


	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}


	public String getPhoneno() {
		return phoneno;
	}


	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}


	public String getFromdate() {
		return fromdate;
	}


	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}


	public String getTodate() {
		return todate;
	}


	public void setTodate(String todate) {
		this.todate = todate;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getRequestedamt() {
		return requestedamt;
	}


	public void setRequestedamt(String requestedamt) {
		this.requestedamt = requestedamt;
	}


	public double getApprovedamt() {
		return approvedamt;
	}


	public void setApprovedamt(double approvedamt) {
		this.approvedamt = approvedamt;
	}


	public String getOmproject() {
		return omproject;
	}


	public void setOmproject(String omproject) {
		this.omproject = omproject;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getSubmiteddates() {
		return submiteddates;
	}


	public void setSubmiteddates(String submiteddates) {
		this.submiteddates = submiteddates;
	}


	public String getAttachments() {
		return attachments;
	}


	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}


	public String getAttachhidden() {
		return attachhidden;
	}


	public void setAttachhidden(String attachhidden) {
		this.attachhidden = attachhidden;
	}


	@Override
	public String toString() {
		return "TelephoneModel [reimburseid=" + reimburseid + ", reimbursename=" + reimbursename + ", telephoneclaimid="
				+ telephoneclaimid + ", personnumber=" + personnumber + ", personid=" + personid + ", submitStatus="
				+ submitStatus + ", phoneno=" + phoneno + ", fromdate=" + fromdate + ", todate=" + todate
				+ ", duration=" + duration + ", requestedamt=" + requestedamt + ", approvedamt=" + approvedamt
				+ ", omproject=" + omproject + ", status=" + status + ", submiteddates=" + submiteddates
				+ ", attachments=" + attachments + ", attachhidden=" + attachhidden + "]";
	}
		
}
