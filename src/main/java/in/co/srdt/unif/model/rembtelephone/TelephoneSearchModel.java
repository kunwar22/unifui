package in.co.srdt.unif.model.rembtelephone;

public class TelephoneSearchModel {
	private String reimburseid;
	private String reimbursename;
	private String telephoneclaimid;
	private String personnumber;
	private String personid;
	private String phoneno;
	private String fromdate;
	private String todate;
	private String duration;
	private String requestedamt;
	private String approvedamt="0";
	private String omproject;
	private String status=null;
	private String submiteddates;
	private String attachments;
	public TelephoneSearchModel() {
		super();
	}
	public TelephoneSearchModel(String reimburseid, String reimbursename, String telephoneclaimid, String personnumber,
			String personid, String phoneno, String fromdate, String todate, String duration, String requestedamt,
			String approvedamt, String omproject, String status, String submiteddates, String attachments) {
		super();
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.telephoneclaimid = telephoneclaimid;
		this.personnumber = personnumber;
		this.personid = personid;
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
	}
	@Override
	public String toString() {
		return "TelephoneSearchModel [reimburseid=" + reimburseid + ", reimbursename=" + reimbursename
				+ ", telephoneclaimid=" + telephoneclaimid + ", personnumber=" + personnumber + ", personid=" + personid
				+ ", phoneno=" + phoneno + ", fromdate=" + fromdate + ", todate=" + todate + ", duration=" + duration
				+ ", requestedamt=" + requestedamt + ", approvedamt=" + approvedamt + ", omproject=" + omproject
				+ ", status=" + status + ", submiteddates=" + submiteddates + ", attachments=" + attachments + "]";
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
	public String getTelephoneclaimid() {
		return telephoneclaimid;
	}
	public void setTelephoneclaimid(String telephoneclaimid) {
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getRequestedamt() {
		return requestedamt;
	}
	public void setRequestedamt(String requestedamt) {
		this.requestedamt = requestedamt;
	}
	public String getApprovedamt() {
		return approvedamt;
	}
	public void setApprovedamt(String approvedamt) {
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
	
	
	
}
