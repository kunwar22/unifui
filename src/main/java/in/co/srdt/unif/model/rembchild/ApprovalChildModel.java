package in.co.srdt.unif.model.rembchild;

public class ApprovalChildModel {
	private long approvalid;

	private long approvallevel;

	private String approvalstatus;

	private String approverpersonname;

	private String approverpersonnumber;

	private String modulename;

	private String requestdate;

	private String requesterpersonname;

	private String requesterpersonnumber;

	private long requestid;

	private String submitdate;


	public ApprovalChildModel() {

	}

	public long getApprovalid() {
		return approvalid;
	}

	public void setApprovalid(long approvalid) {
		this.approvalid = approvalid;
	}

	public long getApprovallevel() {
		return approvallevel;
	}

	public void setApprovallevel(long approvallevel) {
		this.approvallevel = approvallevel;
	}

	public String getApprovalstatus() {
		return approvalstatus;
	}

	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}

	public String getApproverpersonname() {
		return approverpersonname;
	}

	public void setApproverpersonname(String approverpersonname) {
		this.approverpersonname = approverpersonname;
	}

	public String getApproverpersonnumber() {
		return approverpersonnumber;
	}

	public void setApproverpersonnumber(String approverpersonnumber) {
		this.approverpersonnumber = approverpersonnumber;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}

	public String getRequesterpersonname() {
		return requesterpersonname;
	}

	public void setRequesterpersonname(String requesterpersonname) {
		this.requesterpersonname = requesterpersonname;
	}

	public String getRequesterpersonnumber() {
		return requesterpersonnumber;
	}

	public void setRequesterpersonnumber(String requesterpersonnumber) {
		this.requesterpersonnumber = requesterpersonnumber;
	}

	public long getRequestid() {
		return requestid;
	}

	public void setRequestid(long requestid) {
		this.requestid = requestid;
	}

	public String getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(String submitdate) {
		this.submitdate = submitdate;
	}

	public ApprovalChildModel(long approvalid, long approvallevel, String approvalstatus, String approverpersonname, String approverpersonnumber, String modulename, String requestdate, String requesterpersonname, String requesterpersonnumber, long requestid, String submitdate) {
		this.approvalid = approvalid;
		this.approvallevel = approvallevel;
		this.approvalstatus = approvalstatus;
		this.approverpersonname = approverpersonname;
		this.approverpersonnumber = approverpersonnumber;
		this.modulename = modulename;
		this.requestdate = requestdate;
		this.requesterpersonname = requesterpersonname;
		this.requesterpersonnumber = requesterpersonnumber;
		this.requestid = requestid;
		this.submitdate = submitdate;
	}

	@Override
	public String toString() {
		return "ApprovalChildModel{" +
				"approvalid=" + approvalid +
				", approvallevel=" + approvallevel +
				", approvalstatus='" + approvalstatus + '\'' +
				", approverpersonname='" + approverpersonname + '\'' +
				", approverpersonnumber='" + approverpersonnumber + '\'' +
				", modulename='" + modulename + '\'' +
				", requestdate='" + requestdate + '\'' +
				", requesterpersonname='" + requesterpersonname + '\'' +
				", requesterpersonnumber='" + requesterpersonnumber + '\'' +
				", requestid=" + requestid +
				", submitdate='" + submitdate + '\'' +
				'}';
	}
}
