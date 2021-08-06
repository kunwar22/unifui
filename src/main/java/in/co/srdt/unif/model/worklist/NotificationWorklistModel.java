package in.co.srdt.unif.model.worklist;

public class NotificationWorklistModel {

	public long notificationid;
	public String submittedbypersonno;
	public String submittedbypersonname;
	public String message;
	public String submittedtopersonno;
	public String submittedtopersonname;
	public String submittedondate;
	public String status;
	public String modulename;
	public String actiondate;
	public String approvedamount;
	public String moduleid;
	public String requestid;
	public String approvallevel;
	public String approvallist;
//	public String fromdate;
//	public String todate;
	
	public NotificationWorklistModel() {
		super();
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//		LocalDateTime now = LocalDateTime.now();
//		
//		this.fromdate = dtf.format(now);
//		this.todate = dtf.format(now);
	}


	

	public NotificationWorklistModel(long notificationid, String submittedbypersonno, String submittedbypersonname,
			String message, String submittedtopersonno, String submittedtopersonname, String submittedondate,
			String status, String modulename, String actiondate, String approvedamount, String moduleid,
			String requestid, String approvallevel, String approvallist) {
		super();
		this.notificationid = notificationid;
		this.submittedbypersonno = submittedbypersonno;
		this.submittedbypersonname = submittedbypersonname;
		this.message = message;
		this.submittedtopersonno = submittedtopersonno;
		this.submittedtopersonname = submittedtopersonname;
		this.submittedondate = submittedondate;
		this.status = status;
		this.modulename = modulename;
		this.actiondate = actiondate;
		this.approvedamount = approvedamount;
		this.moduleid = moduleid;
		this.requestid = requestid;
		this.approvallevel = approvallevel;
		this.approvallist = approvallist;
		
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//		LocalDateTime now = LocalDateTime.now(); 
//		this.fromdate = dtf.format(now);
//		this.todate = dtf.format(now);
	}




	public long getNotificationid() {
		return notificationid;
	}


	public void setNotificationid(long notificationid) {
		this.notificationid = notificationid;
	}


	public String getSubmittedbypersonno() {
		return submittedbypersonno;
	}


	public void setSubmittedbypersonno(String submittedbypersonno) {
		this.submittedbypersonno = submittedbypersonno;
	}


	public String getSubmittedbypersonname() {
		return submittedbypersonname;
	}


	public void setSubmittedbypersonname(String submittedbypersonname) {
		this.submittedbypersonname = submittedbypersonname;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getSubmittedtopersonno() {
		return submittedtopersonno;
	}


	public void setSubmittedtopersonno(String submittedtopersonno) {
		this.submittedtopersonno = submittedtopersonno;
	}


	public String getSubmittedtopersonname() {
		return submittedtopersonname;
	}


	public void setSubmittedtopersonname(String submittedtopersonname) {
		this.submittedtopersonname = submittedtopersonname;
	}


	public String getSubmittedondate() {
		return submittedondate;
	}


	public void setSubmittedondate(String submittedondate) {
		this.submittedondate = submittedondate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getModulename() {
		return modulename;
	}


	public void setModulename(String modulename) {
		this.modulename = modulename;
	}


	public String getActiondate() {
		return actiondate;
	}


	public void setActiondate(String actiondate) {
		this.actiondate = actiondate;
	}


	public String getApprovedamount() {
		return approvedamount;
	}


	public void setApprovedamount(String approvedamount) {
		this.approvedamount = approvedamount;
	}


	public String getModuleid() {
		return moduleid;
	}


	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}


	public String getRequestid() {
		return requestid;
	}


	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}


	public String getApprovallevel() {
		return approvallevel;
	}


	public void setApprovallevel(String approvallevel) {
		this.approvallevel = approvallevel;
	}


	public String getApprovallist() {
		return approvallist;
	}


	public void setApprovallist(String approvallist) {
		this.approvallist = approvallist;
	}




	@Override
	public String toString() {
		return "NotificationWorklistModel [notificationid=" + notificationid + ", submittedbypersonno="
				+ submittedbypersonno + ", submittedbypersonname=" + submittedbypersonname + ", message=" + message
				+ ", submittedtopersonno=" + submittedtopersonno + ", submittedtopersonname=" + submittedtopersonname
				+ ", submittedondate=" + submittedondate + ", status=" + status + ", modulename=" + modulename
				+ ", actiondate=" + actiondate + ", approvedamount=" + approvedamount + ", moduleid=" + moduleid
				+ ", requestid=" + requestid + ", approvallevel=" + approvallevel + ", approvallist=" + approvallist
				+ "]";
	}

	
//	public String getFromdate() {
//		return fromdate;
//	}
//
//
//	public void setFromdate(String fromdate) {
//		this.fromdate = fromdate;
//	}
//
//
//	public String getTodate() {
//		return todate;
//	}
//
//
//	public void setTodate(String todate) {
//		this.todate = todate;
//	}




}
