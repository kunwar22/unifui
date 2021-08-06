package in.co.srdt.unif.model.rembelectricity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ElectricityReimbursementModel {
	private long reimburseid;
	private String reimbursename = "Electricity";
	private long electricityid;

	@Min(value = 1,message = "Please select OM/Project")
	private long omproject;
	
	@NotBlank(message = "Please select From Date.")
	private String fromdate;
	
	@NotBlank(message = "Please select To Date.")
	private String todate;
		
	@Min(value=1,message ="Please Fill Billed Units" )
	private long billedunits;
	
	@Min(value=1,message ="Please Fill Billed Amount")
	private long billedamt;
	
	@Min(value=1,message = "Please Fill Claimed Units")
	private long claimedunit;
	
	@Min(value=1,message = "Please Fill Claimed Amount")
	private long claimedamt;
	private long apramt;
	private String dates;
	
	private String attachments;
	private String attachhidden;

	@Min(value=1,message ="Please fill vehicle used" )
	private long vehicleused;
	private String personnumber;
	private String personid;
	private String status;
	
	private String submitStatus;
	
	
	public ElectricityReimbursementModel() {
		super();
	}


	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
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

	

	public long getOmproject() {
		return omproject;
	}



	public void setOmproject(long omproject) {
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

	public long getVehicleused() {
		return vehicleused;
	}

	public void setVehicleused(long vehicleused) {
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

	public String getAttachhidden() {
		return attachhidden;
	}

	public void setAttachhidden(String attachhidden) {
		this.attachhidden = attachhidden;
	}

	

	


	public ElectricityReimbursementModel(long reimburseid, String reimbursename, long electricityid,
			@Min(value = 1, message = "Please select OM/Project") long omproject,
			@NotBlank(message = "Please select From Date.") String fromdate,
			@NotBlank(message = "Please select To Date.") String todate,
			@Min(value = 1, message = "Please Fill Billed Units") long billedunits,
			@Min(value = 1, message = "Please Fill Billed Amount") long billedamt,
			@Min(value = 1, message = "Please Fill Claimed Units") long claimedunit,
			@Min(value = 1, message = "Please Fill Claimed Amount") long claimedamt, long apramt, String dates,
			String attachments, String attachhidden,
			@Min(value = 1, message = "Please fill vehicle used") long vehicleused, String personnumber,
			String personid, String status, String submitStatus) {
		super();
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.electricityid = electricityid;
		this.omproject = omproject;
		this.fromdate = fromdate;
		this.todate = todate;
		this.billedunits = billedunits;
		this.billedamt = billedamt;
		this.claimedunit = claimedunit;
		this.claimedamt = claimedamt;
		this.apramt = apramt;
		this.dates = dates;
		this.attachments = attachments;
		this.attachhidden = attachhidden;
		this.vehicleused = vehicleused;
		this.personnumber = personnumber;
		this.personid = personid;
		this.status = status;
		this.submitStatus = submitStatus;
	}


	@Override
	public String toString() {
		return "ElectricityReimbursementModel [reimburseid=" + reimburseid + ", reimbursename=" + reimbursename
				+ ", electricityid=" + electricityid + ", omproject=" + omproject + ", fromdate=" + fromdate
				+ ", todate=" + todate + ", billedunits=" + billedunits + ", billedamt=" + billedamt + ", claimedunit="
				+ claimedunit + ", claimedamt=" + claimedamt + ", apramt=" + apramt + ", dates=" + dates
				+ ", attachments=" + attachments + ", attachhidden=" + attachhidden + ", vehicleused=" + vehicleused
				+ ", personnumber=" + personnumber + ", personid=" + personid + ", status=" + status + ", submitStatus="
				+ submitStatus + "]";
	}



}
