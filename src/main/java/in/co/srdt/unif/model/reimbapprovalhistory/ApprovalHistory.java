package in.co.srdt.unif.model.reimbapprovalhistory;

public class ApprovalHistory {
	
	private String message;
	private String approvallevel;
	private String approvedamt;
	private String submitdate;
	private String approverpersonname;
	private String status;
	private String taxableincome;
	private String nontaxableincome;
	
	public ApprovalHistory() {
	}

	public ApprovalHistory(String message, String approvallevel, String approvedamt, String submitdate, String approverpersonname, String status, String taxableincome, String nontaxableincome) {
		this.message = message;
		this.approvallevel = approvallevel;
		this.approvedamt = approvedamt;
		this.submitdate = submitdate;
		this.approverpersonname = approverpersonname;
		this.status = status;
		this.taxableincome = taxableincome;
		this.nontaxableincome = nontaxableincome;
	}

	public String getTaxableincome() {
		return taxableincome;
	}

	public void setTaxableincome(String taxableincome) {
		this.taxableincome = taxableincome;
	}

	public String getNontaxableincome() {
		return nontaxableincome;
	}

	public void setNontaxableincome(String nontaxableincome) {
		this.nontaxableincome = nontaxableincome;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getApprovallevel() {
		return approvallevel;
	}

	public void setApprovallevel(String approvallevel) {
		this.approvallevel = approvallevel;
	}

	public String getApprovedamt() {
		return approvedamt;
	}

	public void setApprovedamt(String approvedamt) {
		this.approvedamt = approvedamt;
	}

	public String getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(String submitdate) {
		this.submitdate = submitdate;
	}

	public String getApproverpersonname() {
		return approverpersonname;
	}

	public void setApproverpersonname(String approverpersonname) {
		this.approverpersonname = approverpersonname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ApprovalHistory{" +
				"message='" + message + '\'' +
				", approvallevel='" + approvallevel + '\'' +
				", approvedamt='" + approvedamt + '\'' +
				", submitdate='" + submitdate + '\'' +
				", approverpersonname='" + approverpersonname + '\'' +
				", status='" + status + '\'' +
				", taxableincome='" + taxableincome + '\'' +
				", nontaxableincome='" + nontaxableincome + '\'' +
				'}';
	}
}
