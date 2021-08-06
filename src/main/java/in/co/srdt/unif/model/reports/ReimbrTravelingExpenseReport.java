package in.co.srdt.unif.model.reports;

public class ReimbrTravelingExpenseReport {

	private String traveldate;

	private String expensestype;

	private String expensesdescription;

	private String actualamountpaid;

	private String approvedamount;

	private String remarks;

	public ReimbrTravelingExpenseReport(String traveldate, String expensestype, String expensesdescription,
			String actualamountpaid, String approvedamount, String remarks) {
		this.traveldate = traveldate;
		this.expensestype = expensestype;
		this.expensesdescription = expensesdescription;
		this.actualamountpaid = actualamountpaid;
		this.approvedamount = approvedamount;
		this.remarks = remarks;
	}

	public ReimbrTravelingExpenseReport() {
	}

	public String getTraveldate() {
		return traveldate;
	}

	public void setTraveldate(String traveldate) {
		this.traveldate = traveldate;
	}

	public String getExpensestype() {
		return expensestype;
	}

	public void setExpensestype(String expensestype) {
		this.expensestype = expensestype;
	}

	public String getExpensesdescription() {
		return expensesdescription;
	}

	public void setExpensesdescription(String expensesdescription) {
		this.expensesdescription = expensesdescription;
	}

	public String getActualamountpaid() {
		return actualamountpaid;
	}

	public void setActualamountpaid(String actualamountpaid) {
		this.actualamountpaid = actualamountpaid;
	}

	public String getApprovedamount() {
		return approvedamount;
	}

	public void setApprovedamount(String approvedamount) {
		this.approvedamount = approvedamount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "ReimbrTravelingExpenseReport [traveldate=" + traveldate + ", expensestype=" + expensestype
				+ ", expensesdescription=" + expensesdescription + ", actualamountpaid=" + actualamountpaid
				+ ", approvedamount=" + approvedamount + ", remarks=" + remarks + "]";
	}

	
}
