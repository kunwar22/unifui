package in.co.srdt.unif.model.invoice;



public class RmbrsEbsSyncTable {

	private long syncid;

	private Long reimburseid;

	private String reimbursename;

	private String venderid;

	private String siteid;

	private String invoiceno;

	private String personnumber;
	
	private String personname;

	private double approvedamt;

	private String status;

	private String ebsinvoiceid;

	private String ebsbasetablestatus;

	private String businessunitid;

	private String businessunitname;
	
	private String processdate;

	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	private String attribute5;

	public RmbrsEbsSyncTable() {
	}

	public RmbrsEbsSyncTable(long syncid, Long reimburseid, String reimbursename, String venderid, String siteid,
			String invoiceno, String personnumber,String personame,  double approvedamt, String status, String ebsinvoiceid,
			String ebsbasetablestatus, String businessunitid, String businessunitname, String processdate,
			String attribute1, String attribute2, String attribute3, String attribute4, String attribute5) {
		super();
		this.syncid = syncid;
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.venderid = venderid;
		this.siteid = siteid;
		this.invoiceno = invoiceno;
		this.personnumber = personnumber;
		this.personname = personame;
		this.approvedamt = approvedamt;
		this.status = status;
		this.ebsinvoiceid = ebsinvoiceid;
		this.ebsbasetablestatus = ebsbasetablestatus;
		this.businessunitid = businessunitid;
		this.businessunitname = businessunitname;
		this.processdate = processdate;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.attribute5 = attribute5;
	}

	public long getSyncid() {
		return syncid;
	}

	public void setSyncid(long syncid) {
		this.syncid = syncid;
	}

	public Long getReimburseid() {
		return reimburseid;
	}

	public void setReimburseid(Long reimburseid) {
		this.reimburseid = reimburseid;
	}

	public String getReimbursename() {
		return reimbursename;
	}

	public void setReimbursename(String reimbursename) {
		this.reimbursename = reimbursename;
	}

	public String getVenderid() {
		return venderid;
	}

	public void setVenderid(String venderid) {
		this.venderid = venderid;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}
	
	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public double getApprovedamt() {
		return approvedamt;
	}

	public void setApprovedamt(double approvedamt) {
		this.approvedamt = approvedamt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEbsinvoiceid() {
		return ebsinvoiceid;
	}

	public void setEbsinvoiceid(String ebsinvoiceid) {
		this.ebsinvoiceid = ebsinvoiceid;
	}

	public String getEbsbasetablestatus() {
		return ebsbasetablestatus;
	}

	public void setEbsbasetablestatus(String ebsbasetablestatus) {
		this.ebsbasetablestatus = ebsbasetablestatus;
	}

	public String getBusinessunitid() {
		return businessunitid;
	}

	public void setBusinessunitid(String businessunitid) {
		this.businessunitid = businessunitid;
	}

	public String getBusinessunitname() {
		return businessunitname;
	}

	public void setBusinessunitname(String businessunitname) {
		this.businessunitname = businessunitname;
	}

	public String getProcessdate() {
		return processdate;
	}

	public void setProcessdate(String processdate) {
		this.processdate = processdate;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Override
	public String toString() {
		return "RmbrsEbsSyncTable [syncid=" + syncid + ", reimburseid=" + reimburseid + ", reimbursename="
				+ reimbursename + ", venderid=" + venderid + ", siteid=" + siteid + ", invoiceno=" + invoiceno
				+ ", personnumber=" + personnumber + ", personname=" + personname + ", approvedamt=" + approvedamt
				+ ", status=" + status + ", ebsinvoiceid=" + ebsinvoiceid + ", ebsbasetablestatus=" + ebsbasetablestatus
				+ ", businessunitid=" + businessunitid + ", businessunitname=" + businessunitname + ", processdate="
				+ processdate + ", attribute1=" + attribute1 + ", attribute2=" + attribute2 + ", attribute3="
				+ attribute3 + ", attribute4=" + attribute4 + ", attribute5=" + attribute5 + "]";
	}

		

}
