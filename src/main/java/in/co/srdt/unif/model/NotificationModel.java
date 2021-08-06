package in.co.srdt.unif.model;

public class NotificationModel {
    private long notificationid;

    private String submittedbypersonno;

    private String submittedbypersonname;

    private String message;

    private String submittedtopersonno;

    private String submittedtopersonname;

    private String submittedondate;

    private String status;

    private String modulename;

    private String actiondate;

    private long moduleid;

    private long requestid;

    private long approvallevel;

    private double approvedamount;

    private double taxableincome;

    private double nontaxableincome;

    private String flag="off";

    public NotificationModel() {

    }

    public NotificationModel(long notificationid, String submittedbypersonno, String submittedbypersonname, String message, String submittedtopersonno, String submittedtopersonname, String submittedondate, String status, String modulename, String actiondate, long moduleid, long requestid, long approvallevel, double approvedamount, double taxableincome, double nontaxableincome) {
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
        this.moduleid = moduleid;
        this.requestid = requestid;
        this.approvallevel = approvallevel;
        this.approvedamount = approvedamount;
        this.taxableincome = taxableincome;
        this.nontaxableincome = nontaxableincome;
    }

    public double getTaxableincome() {
        return taxableincome;
    }

    public void setTaxableincome(double taxableincome) {
        this.taxableincome = taxableincome;
    }

    public double getNontaxableincome() {
        return nontaxableincome;
    }

    public void setNontaxableincome(double nontaxableincome) {
        this.nontaxableincome = nontaxableincome;
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

    public long getModuleid() {
        return moduleid;
    }

    public void setModuleid(long moduleid) {
        this.moduleid = moduleid;
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

    public double getApprovedamount() {
        return approvedamount;
    }

    public void setApprovedamount(double approvedamount) {
        this.approvedamount = approvedamount;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

	@Override
	public String toString() {
		return "NotificationModel{" +
				"notificationid=" + notificationid +
				", submittedbypersonno='" + submittedbypersonno + '\'' +
				", submittedbypersonname='" + submittedbypersonname + '\'' +
				", message='" + message + '\'' +
				", submittedtopersonno='" + submittedtopersonno + '\'' +
				", submittedtopersonname='" + submittedtopersonname + '\'' +
				", submittedondate='" + submittedondate + '\'' +
				", status='" + status + '\'' +
				", modulename='" + modulename + '\'' +
				", actiondate='" + actiondate + '\'' +
				", moduleid=" + moduleid +
				", requestid=" + requestid +
				", approvallevel=" + approvallevel +
				", approvedamount=" + approvedamount +
				", taxableincome=" + taxableincome +
				", nontaxableincome=" + nontaxableincome +
				", flag='" + flag + '\'' +
				'}';
	}
}
