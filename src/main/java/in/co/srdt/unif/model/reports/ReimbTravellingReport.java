package in.co.srdt.unif.model.reports;

import java.util.List;

public class ReimbTravellingReport {

	private String bussnesunit;
	
	private String employeenumber;

	private String employeename;

	private String designation;

	private String claimdate;

	private String travelstartdate;

	private String travelenddate;

	private String visitingcity1;

	private String visitingcity2;

	private String visitingcity3;

	private double totalclaimamount;

	private double totalapprovedamount;

	private String netpayablerecovery;

	private String apinvoiceno;

	private String approveddate;

	private String approvercomments;
	
	private List<ReimbrTravelingExpenseReport> travelingExpense;

	public ReimbTravellingReport() {
		super();
	}

	public ReimbTravellingReport(String bussnesunit, String employeenumber, String employeename, String designation,
			String claimdate, String travelstartdate, String travelenddate, String visitingcity1, String visitingcity2,
			String visitingcity3, double totalclaimamount, double totalapprovedamount, String netpayablerecovery,
			String apinvoiceno, String approveddate, String approvercomments,
			List<ReimbrTravelingExpenseReport> travelingExpense) {
		super();
		this.bussnesunit = bussnesunit;
		this.employeenumber = employeenumber;
		this.employeename = employeename;
		this.designation = designation;
		this.claimdate = claimdate;
		this.travelstartdate = travelstartdate;
		this.travelenddate = travelenddate;
		this.visitingcity1 = visitingcity1;
		this.visitingcity2 = visitingcity2;
		this.visitingcity3 = visitingcity3;
		this.totalclaimamount = totalclaimamount;
		this.totalapprovedamount = totalapprovedamount;
		this.netpayablerecovery = netpayablerecovery;
		this.apinvoiceno = apinvoiceno;
		this.approveddate = approveddate;
		this.approvercomments = approvercomments;
		this.travelingExpense = travelingExpense;
	}

	public String getBussnesunit() {
		return bussnesunit;
	}

	public void setBussnesunit(String bussnesunit) {
		this.bussnesunit = bussnesunit;
	}

	public String getEmployeenumber() {
		return employeenumber;
	}

	public void setEmployeenumber(String employeenumber) {
		this.employeenumber = employeenumber;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getClaimdate() {
		return claimdate;
	}

	public void setClaimdate(String claimdate) {
		this.claimdate = claimdate;
	}

	public String getTravelstartdate() {
		return travelstartdate;
	}

	public void setTravelstartdate(String travelstartdate) {
		this.travelstartdate = travelstartdate;
	}

	public String getTravelenddate() {
		return travelenddate;
	}

	public void setTravelenddate(String travelenddate) {
		this.travelenddate = travelenddate;
	}

	public String getVisitingcity1() {
		return visitingcity1;
	}

	public void setVisitingcity1(String visitingcity1) {
		this.visitingcity1 = visitingcity1;
	}

	public String getVisitingcity2() {
		return visitingcity2;
	}

	public void setVisitingcity2(String visitingcity2) {
		this.visitingcity2 = visitingcity2;
	}

	public String getVisitingcity3() {
		return visitingcity3;
	}

	public void setVisitingcity3(String visitingcity3) {
		this.visitingcity3 = visitingcity3;
	}

	public double getTotalclaimamount() {
		return totalclaimamount;
	}

	public void setTotalclaimamount(double totalclaimamount) {
		this.totalclaimamount = totalclaimamount;
	}

	public double getTotalapprovedamount() {
		return totalapprovedamount;
	}

	public void setTotalapprovedamount(double totalapprovedamount) {
		this.totalapprovedamount = totalapprovedamount;
	}

	public String getNetpayablerecovery() {
		return netpayablerecovery;
	}

	public void setNetpayablerecovery(String netpayablerecovery) {
		this.netpayablerecovery = netpayablerecovery;
	}

	public String getApinvoiceno() {
		return apinvoiceno;
	}

	public void setApinvoiceno(String apinvoiceno) {
		this.apinvoiceno = apinvoiceno;
	}

	public String getApproveddate() {
		return approveddate;
	}

	public void setApproveddate(String approveddate) {
		this.approveddate = approveddate;
	}

	public String getApprovercomments() {
		return approvercomments;
	}

	public void setApprovercomments(String approvercomments) {
		this.approvercomments = approvercomments;
	}

	public List<ReimbrTravelingExpenseReport> getTravelingExpense() {
		return travelingExpense;
	}

	public void setTravelingExpense(List<ReimbrTravelingExpenseReport> travelingExpense) {
		this.travelingExpense = travelingExpense;
	}

	@Override
	public String toString() {
		return "ReimbTravellingReport [bussnesunit=" + bussnesunit + ", employeenumber=" + employeenumber
				+ ", employeename=" + employeename + ", designation=" + designation + ", claimdate=" + claimdate
				+ ", travelstartdate=" + travelstartdate + ", travelenddate=" + travelenddate + ", visitingcity1="
				+ visitingcity1 + ", visitingcity2=" + visitingcity2 + ", visitingcity3=" + visitingcity3
				+ ", totalclaimamount=" + totalclaimamount + ", totalapprovedamount=" + totalapprovedamount
				+ ", netpayablerecovery=" + netpayablerecovery + ", apinvoiceno=" + apinvoiceno + ", approveddate="
				+ approveddate + ", approvercomments=" + approvercomments + ", travelingExpense=" + travelingExpense
				+ "]";
	}

	

}
