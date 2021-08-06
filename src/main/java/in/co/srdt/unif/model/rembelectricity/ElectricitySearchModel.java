package in.co.srdt.unif.model.rembelectricity;

public class ElectricitySearchModel {

	private long reimburseid;
	private String reimbursename = "Electricity";
	private long electricityid;
	private String omproject;
	private String fromdate;
	private String todate;
	private long billedunits;
	private long billedamt;
	private long claimedunit;
	private long claimedamt;
	private long apramt;
	private String dates;
	private String attachments;
	private String vehicleused;
	private String personnumber;
	private String personid;
	private String status="saved";
	
	
	public ElectricitySearchModel() {
		super();
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


	public long getElectricityid() {
		return electricityid;
	}


	public void setElectricityid(long electricityid) {
		this.electricityid = electricityid;
	}


	public String getOmproject() {
		return omproject;
	}


	public void setOmproject(String omproject) {
		this.omproject = omproject;
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


	public long getBilledunits() {
		return billedunits;
	}


	public void setBilledunits(long billedunits) {
		this.billedunits = billedunits;
	}


	public long getBilledamt() {
		return billedamt;
	}


	public void setBilledamt(long billedamt) {
		this.billedamt = billedamt;
	}


	public long getClaimedunit() {
		return claimedunit;
	}


	public void setClaimedunit(long claimedunit) {
		this.claimedunit = claimedunit;
	}


	public long getClaimedamt() {
		return claimedamt;
	}


	public void setClaimedamt(long claimedamt) {
		this.claimedamt = claimedamt;
	}

	public long getApramt() {
		return apramt;
	}


	public void setApramt(long apramt) {
		this.apramt = apramt;
	}

	public String getDates() {
		return dates;
	}


	public void setDates(String dates) {
		this.dates = dates;
	}


	public String getAttachments() {
		return attachments;
	}


	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}


	public String getVehicleused() {
		return vehicleused;
	}


	public void setVehicleused(String vehicleused) {
		this.vehicleused = vehicleused;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ElectricitySearchModel [reimburseid=" + reimburseid + ", reimbursename=" + reimbursename
				+ ", electricityid=" + electricityid + ", omproject=" + omproject + ", fromdate=" + fromdate
				+ ", todate=" + todate + ", billedunits=" + billedunits + ", billedamt=" + billedamt + ", claimedunit="
				+ claimedunit + ", claimedamt=" + claimedamt + ", apramt=" + apramt + ", dates=" + dates
				+ ", attachments=" + attachments + ", vehicleused=" + vehicleused + ", personnumber=" + personnumber
				+ ", personid=" + personid + ", status=" + status + "]";
	}

	
}
