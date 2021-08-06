package in.co.srdt.unif.model.mss;

public class ApprovalActions {

	private long approvalid;

	private String modulename;

	private String requesterpersonnumber;

	private String requesterpersonname;

	private long requestid;

	private long approvallevel;

	private String submitdate;

	private String requestdate;

	private String approverpersonnumber;

	private String approverpersonname;

	private String message;

	private double approvedamt;
	
	private String approvalstatus;
	
	private double finalapprovedamt;

	private double climedamt;
	
	private String finalstatus;
	
	private long moduleid;
	
	private String buname;

	public ApprovalActions() {
	}
	

	public String getBuname() {
		return buname;
	}



	public void setBuname(String buname) {
		this.buname = buname;
	}



	public double getClimedamt() {
		return climedamt;
	}

	public void setClimedamt(double climedamt) {
		this.climedamt = climedamt;
	}

	public long getApprovalid() {
		return approvalid;
	}

	public void setApprovalid(long approvalid) {
		this.approvalid = approvalid;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getRequesterpersonnumber() {
		return requesterpersonnumber;
	}

	public void setRequesterpersonnumber(String requesterpersonnumber) {
		this.requesterpersonnumber = requesterpersonnumber;
	}

	public String getRequesterpersonname() {
		return requesterpersonname;
	}

	public void setRequesterpersonname(String requesterpersonname) {
		this.requesterpersonname = requesterpersonname;
	}

	public long getRequestid() {
		return requestid;
	}

	public void setRequestid(long requestid) {
		this.requestid = requestid;
	}

	public long getApprovallevel() {
		return approvallevel;
	}

	public void setApprovallevel(long approvallevel) {
		this.approvallevel = approvallevel;
	}

	public String getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(String submitdate) {
		this.submitdate = submitdate;
	}

	public String getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}

	public String getApproverpersonnumber() {
		return approverpersonnumber;
	}

	public void setApproverpersonnumber(String approverpersonnumber) {
		this.approverpersonnumber = approverpersonnumber;
	}

	public String getApproverpersonname() {
		return approverpersonname;
	}

	public void setApproverpersonname(String approverpersonname) {
		this.approverpersonname = approverpersonname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getApprovedamt() {
		return approvedamt;
	}

	public void setApprovedamt(double approvedamt) {
		this.approvedamt = approvedamt;
	}

	public String getApprovalstatus() {
		return approvalstatus;
	}

	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}

	public double getFinalapprovedamt() {
		return finalapprovedamt;
	}

	public void setFinalapprovedamt(double finalapprovedamt) {
		this.finalapprovedamt = finalapprovedamt;
	}

	public String getFinalstatus() {
		return finalstatus;
	}

	public void setFinalstatus(String finalstatus) {
		this.finalstatus = finalstatus;
	}

	public long getModuleid() {
		return moduleid;
	}

	public void setModuleid(long moduleid) {
		this.moduleid = moduleid;
	}

	@Override
	public String toString() {
		return "ApprovalActions{" +
				"approvalid=" + approvalid +
				", modulename='" + modulename + '\'' +
				", requesterpersonnumber='" + requesterpersonnumber + '\'' +
				", requesterpersonname='" + requesterpersonname + '\'' +
				", requestid=" + requestid +
				", approvallevel=" + approvallevel +
				", submitdate='" + submitdate + '\'' +
				", requestdate='" + requestdate + '\'' +
				", approverpersonnumber='" + approverpersonnumber + '\'' +
				", approverpersonname='" + approverpersonname + '\'' +
				", message='" + message + '\'' +
				", approvedamt=" + approvedamt +
				", approvalstatus='" + approvalstatus + '\'' +
				", finalapprovedamt=" + finalapprovedamt +
				", climedamt=" + climedamt +
				", finalstatus='" + finalstatus + '\'' +
				", moduleid=" + moduleid +
				'}';
	}
}
