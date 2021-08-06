package in.co.srdt.unif.model.reimbcda;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CdaVehicle {
	

	  private long claimid;
	  
	  @Min(value = 1, message = "Please enter Employee Location.")

	  private long employeelocation;
	  
	 @NotBlank(message = "Please enter Physical Location.")
	  private String physicalloaction;
	 
	 @NotBlank(message = "Please enter Period From.")
	  private String periodfrom;
	 
	 @NotBlank(message = "Please enter Period To.")
	  private String periodto;
	 
	 @Min(value = 1, message = "Please enter no of days.")

	  private long noofdays;
	 
	 @Min(value = 1, message = "Please enter no of months.")
	  private long noofmonths;
	 
	 @Min(value = 1, message = "Please enter CDA claim Amount.")
	  private long cdaclaimamount;
	 
	 //@Min(value = 1, message = "Please enter Vehicle Allowance Amount.")
	  private long vehicleallowanceamount;
	 
	 @NotBlank(message = "Please enterVehicle used.")
	  private String vehicleused;
	 
	  private String personid;
	  private String personnumber;
	  private long reimburseid;
	  private String reimbursename;
	  private String attachment;
	  //@NotBlank(message = "Please enter Approval of Hod Certificate.")
	  private String approvalofhodcerti;
	  private String filepath;
	  private String filename;
	  private String attachment1;
	  private String filepath1;
	  private String filename1;
	  private long approvedamt;
	  private String createddate;
	  private String status;
	  private String filepres1;
	  private String filepres2;
	  private String attachhidden1;
	  private String attachhidden2;
	  
	  private String hoddate;
	  private String flag;
	  
	  public CdaVehicle() {
		super();
	  }

	public CdaVehicle(long claimid, @Min(value = 1, message = "Please enter Employee Location.") long employeelocation,
			@NotBlank(message = "Please enter Physical Location.") String physicalloaction,
			@NotBlank(message = "Please enter Period From.") String periodfrom,
			@NotBlank(message = "Please enter Period To.") String periodto,
			@Min(value = 1, message = "Please enter no of days.") long noofdays,
			@Min(value = 1, message = "Please enter no of months.") long noofmonths,
			@Min(value = 1, message = "Please enter CDA claim Amount.") long cdaclaimamount,
			long vehicleallowanceamount, @NotBlank(message = "Please enterVehicle used.") String vehicleused,
			String personid, String personnumber, long reimburseid, String reimbursename, String attachment,
			String approvalofhodcerti, String filepath, String filename, String attachment1, String filepath1,
			String filename1, long approvedamt, String createddate, String status, String filepres1, String filepres2,
			String attachhidden1, String attachhidden2, String hoddate) {
		super();
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
		this.filepath = filepath;
		this.filename = filename;
		this.attachment1 = attachment1;
		this.filepath1 = filepath1;
		this.filename1 = filename1;
		this.approvedamt = approvedamt;
		this.createddate = createddate;
		this.status = status;
		this.filepres1 = filepres1;
		this.filepres2 = filepres2;
		this.attachhidden1 = attachhidden1;
		this.attachhidden2 = attachhidden2;
		this.hoddate = hoddate;
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

	public long getCdaclaimamount() {
		return cdaclaimamount;
	}

	public void setCdaclaimamount(long cdaclaimamount) {
		this.cdaclaimamount = cdaclaimamount;
	}

	public long getVehicleallowanceamount() {
		return vehicleallowanceamount;
	}

	public void setVehicleallowanceamount(long vehicleallowanceamount) {
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

	public long getApprovedamt() {
		return approvedamt;
	}

	public void setApprovedamt(long approvedamt) {
		this.approvedamt = approvedamt;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFilepres1() {
		return filepres1;
	}

	public void setFilepres1(String filepres1) {
		this.filepres1 = filepres1;
	}

	public String getFilepres2() {
		return filepres2;
	}

	public void setFilepres2(String filepres2) {
		this.filepres2 = filepres2;
	}

	public String getAttachhidden1() {
		return attachhidden1;
	}

	public void setAttachhidden1(String attachhidden1) {
		this.attachhidden1 = attachhidden1;
	}

	public String getAttachhidden2() {
		return attachhidden2;
	}

	public void setAttachhidden2(String attachhidden2) {
		this.attachhidden2 = attachhidden2;
	}

	public String getHoddate() {
		return hoddate;
	}

	public void setHoddate(String hoddate) {
		this.hoddate = hoddate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "CdaVehicle [claimid=" + claimid + ", employeelocation=" + employeelocation + ", physicalloaction="
				+ physicalloaction + ", periodfrom=" + periodfrom + ", periodto=" + periodto + ", noofdays=" + noofdays
				+ ", noofmonths=" + noofmonths + ", cdaclaimamount=" + cdaclaimamount + ", vehicleallowanceamount="
				+ vehicleallowanceamount + ", vehicleused=" + vehicleused + ", personid=" + personid + ", personnumber="
				+ personnumber + ", reimburseid=" + reimburseid + ", reimbursename=" + reimbursename + ", attachment="
				+ attachment + ", approvalofhodcerti=" + approvalofhodcerti + ", filepath=" + filepath + ", filename="
				+ filename + ", attachment1=" + attachment1 + ", filepath1=" + filepath1 + ", filename1=" + filename1
				+ ", approvedamt=" + approvedamt + ", createddate=" + createddate + ", status=" + status
				+ ", filepres1=" + filepres1 + ", filepres2=" + filepres2 + ", attachhidden1=" + attachhidden1
				+ ", attachhidden2=" + attachhidden2 + ", hoddate=" + hoddate + "]";
	}
	  
	  
}
