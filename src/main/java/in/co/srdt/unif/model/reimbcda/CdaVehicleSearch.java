package in.co.srdt.unif.model.reimbcda;

public class CdaVehicleSearch {
	
	private long claimid;
	private long employeelocation;
	private String physicalloaction;
	private String periodfrom;
	private String periodto;
	private long noofdays;
	private long noofmonths;
	private double cdaclaimamount;
	private double vehicleallowanceamount;
	private String vehicleused;
	private String personid;
	private String personnumber;
	private long reimburseid;
	private String reimbursename;
	private String attachment;
	private String approvalofhodcerti;
	private String declarationstatus;
	private String filepath;
	private String filename;
	private String attachment1;
	private String filepath1;
	private String filename1;
	private String createddate;
	private double approvedamt;
	private String status;
	
	public CdaVehicleSearch() {
		super();
	}

	public long getClaimid() {
		return claimid;
	}

	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}

	public long getEmployeelocation() {
		return employeelocation;
	}

	public void setEmployeelocation(long employeelocation) {
		this.employeelocation = employeelocation;
	}

	public String getPhysicalloaction() {
		return physicalloaction;
	}

	public void setPhysicalloaction(String physicalloaction) {
		this.physicalloaction = physicalloaction;
	}

	public String getPeriodfrom() {
		return periodfrom;
	}

	public void setPeriodfrom(String periodfrom) {
		this.periodfrom = periodfrom;
	}

	public String getPeriodto() {
		return periodto;
	}

	public void setPeriodto(String periodto) {
		this.periodto = periodto;
	}

	public long getNoofdays() {
		return noofdays;
	}

	public void setNoofdays(long noofdays) {
		this.noofdays = noofdays;
	}

	public long getNoofmonths() {
		return noofmonths;
	}

	public void setNoofmonths(long noofmonths) {
		this.noofmonths = noofmonths;
	}

	public double getCdaclaimamount() {
		return cdaclaimamount;
	}

	public void setCdaclaimamount(double cdaclaimamount) {
		this.cdaclaimamount = cdaclaimamount;
	}

	public double getVehicleallowanceamount() {
		return vehicleallowanceamount;
	}

	public void setVehicleallowanceamount(double vehicleallowanceamount) {
		this.vehicleallowanceamount = vehicleallowanceamount;
	}

	public String getVehicleused() {
		return vehicleused;
	}

	public void setVehicleused(String vehicleused) {
		this.vehicleused = vehicleused;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
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

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getApprovalofhodcerti() {
		return approvalofhodcerti;
	}

	public void setApprovalofhodcerti(String approvalofhodcerti) {
		this.approvalofhodcerti = approvalofhodcerti;
	}

	public String getDeclarationstatus() {
		return declarationstatus;
	}

	public void setDeclarationstatus(String declarationstatus) {
		this.declarationstatus = declarationstatus;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getAttachment1() {
		return attachment1;
	}

	public void setAttachment1(String attachment1) {
		this.attachment1 = attachment1;
	}

	public String getFilepath1() {
		return filepath1;
	}

	public void setFilepath1(String filepath1) {
		this.filepath1 = filepath1;
	}

	public String getFilename1() {
		return filename1;
	}

	public void setFilename1(String filename1) {
		this.filename1 = filename1;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
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

	public CdaVehicleSearch(long claimid, long employeelocation, String physicalloaction, String periodfrom, String periodto, long noofdays, long noofmonths, double cdaclaimamount, double vehicleallowanceamount, String vehicleused, String personid, String personnumber, long reimburseid, String reimbursename, String attachment, String approvalofhodcerti, String declarationstatus, String filepath, String filename, String attachment1, String filepath1, String filename1, String createddate, double approvedamt, String status) {
		this.claimid = claimid;
		this.employeelocation = employeelocation;
		this.physicalloaction = physicalloaction;
		this.periodfrom = periodfrom;
		this.periodto = periodto;
		this.noofdays = noofdays;
		this.noofmonths = noofmonths;
		this.cdaclaimamount = cdaclaimamount;
		this.vehicleallowanceamount = vehicleallowanceamount;
		this.vehicleused = vehicleused;
		this.personid = personid;
		this.personnumber = personnumber;
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.attachment = attachment;
		this.approvalofhodcerti = approvalofhodcerti;
		this.declarationstatus = declarationstatus;
		this.filepath = filepath;
		this.filename = filename;
		this.attachment1 = attachment1;
		this.filepath1 = filepath1;
		this.filename1 = filename1;
		this.createddate = createddate;
		this.approvedamt = approvedamt;
		this.status = status;
	}

	@Override
	public String toString() {
		return "CdaVehicleSearch{" +
				"claimid=" + claimid +
				", employeelocation=" + employeelocation +
				", physicalloaction='" + physicalloaction + '\'' +
				", periodfrom='" + periodfrom + '\'' +
				", periodto='" + periodto + '\'' +
				", noofdays=" + noofdays +
				", noofmonths=" + noofmonths +
				", cdaclaimamount=" + cdaclaimamount +
				", vehicleallowanceamount=" + vehicleallowanceamount +
				", vehicleused='" + vehicleused + '\'' +
				", personid='" + personid + '\'' +
				", personnumber='" + personnumber + '\'' +
				", reimburseid=" + reimburseid +
				", reimbursename='" + reimbursename + '\'' +
				", attachment='" + attachment + '\'' +
				", approvalofhodcerti='" + approvalofhodcerti + '\'' +
				", declarationstatus='" + declarationstatus + '\'' +
				", filepath='" + filepath + '\'' +
				", filename='" + filename + '\'' +
				", attachment1='" + attachment1 + '\'' +
				", filepath1='" + filepath1 + '\'' +
				", filename1='" + filename1 + '\'' +
				", createddate='" + createddate + '\'' +
				", approvedamt=" + approvedamt +
				", status='" + status + '\'' +
				'}';
	}
}
