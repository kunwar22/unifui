package in.co.srdt.unif.model.rembctg;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CtgReimburse {

	private long claimid;
	@Min(value = 1, message = "Please enter Work location.")
	private long worklocationid;
	@Min(value = 1, message = "Please enter Om Project.")
	private long omprojectid;
	
	private String worklocation;

	private String omproject;
	@NotBlank(message = "Please enter Transfer City.")
	private String transfersamecity;
	@NotBlank(message = "Please enter City From.")
	private String cityfrom;
	@NotBlank(message = "Please enter City To.")
	private String cityto;
	@NotBlank(message = "Please enter Transfer Date.")
	private String transferdate;
	@NotBlank(message = "Please enter Journey Date.")
	private String journeydate;
	private String personid;
	private String personnumber;
	private long reimburseid;
	private String reimbursename;
	//@NotBlank(message = "Please enter Claim Status.")
	private String ctgclaimstatus;
	
	@NotBlank(message = "Please enter Total Amount.")
	private String totalactualpaidamount;
	@NotBlank(message="Please enter Claim Amount.")
	private String totalclaimamount;
	private String status;
	
	private String salaryslippreviousorganization;
	@NotBlank(message = "Please enter Joining Enclose.")
	private String transferorjoiningenclose;
//	@NotBlank(message = "Please enter Personal Chain.")
	private String tranportationofpersonaleffectchain;
	private String receiptenclose;
//	@NotBlank(message = "Please enter Personal Convey.")
	private String transportationpersonalconvey;
	private String ownvehicle;
	private String attachment1;
	private String filepath1;
	private String filename1;
	private String attachment2;
	private String filepath2;
	private String filename2;
	private String attachment3;
	private String filepath3;
	private String filename3;
	private double approvedamt;
	private String createdat;
	private String attachhidden1;
	private String attachhidden2;
	private String attachhidden3;
	
	private String filepres1;
	private String filepres2;
	private String filepres3;
	
	////////////////////////////
	private String filepath4;
	private String filepath5;
	private String attachhidden4;
	private String attachhidden5;
	private String filepres4;
	private String filepres5;
	private String attachment4;
	private String attachment5;	
	private String fair;
	private String ctgclaimamount;
	private String personaleffectclaimamount;
	private String vehicleclaimamount;
	private String fairclaimamount;	
	///////////////////////////
	
	private double ctgapproveamount;
	private double personaleffectapproveamount;
	private double vehicleapproveamount;
	private double fairapproveamount;
	
	
	public CtgReimburse() {
		super();
	}


	public long getClaimid() {
		return claimid;
	}


	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}


	public long getWorklocationid() {
		return worklocationid;
	}


	public void setWorklocationid(long worklocationid) {
		this.worklocationid = worklocationid;
	}


	public long getOmprojectid() {
		return omprojectid;
	}


	public void setOmprojectid(long omprojectid) {
		this.omprojectid = omprojectid;
	}


	public String getWorklocation() {
		return worklocation;
	}


	public void setWorklocation(String worklocation) {
		this.worklocation = worklocation;
	}


	public String getOmproject() {
		return omproject;
	}


	public void setOmproject(String omproject) {
		this.omproject = omproject;
	}


	public String getTransfersamecity() {
		return transfersamecity;
	}


	public void setTransfersamecity(String transfersamecity) {
		this.transfersamecity = transfersamecity;
	}


	public String getCityfrom() {
		return cityfrom;
	}


	public void setCityfrom(String cityfrom) {
		this.cityfrom = cityfrom;
	}


	public String getCityto() {
		return cityto;
	}


	public void setCityto(String cityto) {
		this.cityto = cityto;
	}


	public String getTransferdate() {
		return transferdate;
	}


	public void setTransferdate(String transferdate) {
		this.transferdate = transferdate;
	}


	public String getJourneydate() {
		return journeydate;
	}


	public void setJourneydate(String journeydate) {
		this.journeydate = journeydate;
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


	public String getCtgclaimstatus() {
		return ctgclaimstatus;
	}


	public void setCtgclaimstatus(String ctgclaimstatus) {
		this.ctgclaimstatus = ctgclaimstatus;
	}


	public String getTotalactualpaidamount() {
		return totalactualpaidamount;
	}


	public void setTotalactualpaidamount(String totalactualpaidamount) {
		this.totalactualpaidamount = totalactualpaidamount;
	}


	public String getTotalclaimamount() {
		return totalclaimamount;
	}


	public void setTotalclaimamount(String totalclaimamount) {
		this.totalclaimamount = totalclaimamount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getSalaryslippreviousorganization() {
		return salaryslippreviousorganization;
	}


	public void setSalaryslippreviousorganization(String salaryslippreviousorganization) {
		this.salaryslippreviousorganization = salaryslippreviousorganization;
	}


	public String getTransferorjoiningenclose() {
		return transferorjoiningenclose;
	}


	public void setTransferorjoiningenclose(String transferorjoiningenclose) {
		this.transferorjoiningenclose = transferorjoiningenclose;
	}


	public String getTranportationofpersonaleffectchain() {
		return tranportationofpersonaleffectchain;
	}


	public void setTranportationofpersonaleffectchain(String tranportationofpersonaleffectchain) {
		this.tranportationofpersonaleffectchain = tranportationofpersonaleffectchain;
	}


	public String getReceiptenclose() {
		return receiptenclose;
	}


	public void setReceiptenclose(String receiptenclose) {
		this.receiptenclose = receiptenclose;
	}


	public String getTransportationpersonalconvey() {
		return transportationpersonalconvey;
	}


	public void setTransportationpersonalconvey(String transportationpersonalconvey) {
		this.transportationpersonalconvey = transportationpersonalconvey;
	}


	public String getOwnvehicle() {
		return ownvehicle;
	}


	public void setOwnvehicle(String ownvehicle) {
		this.ownvehicle = ownvehicle;
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


	public String getAttachment2() {
		return attachment2;
	}


	public void setAttachment2(String attachment2) {
		this.attachment2 = attachment2;
	}


	public String getFilepath2() {
		return filepath2;
	}


	public void setFilepath2(String filepath2) {
		this.filepath2 = filepath2;
	}


	public String getFilename2() {
		return filename2;
	}


	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}


	public String getAttachment3() {
		return attachment3;
	}


	public void setAttachment3(String attachment3) {
		this.attachment3 = attachment3;
	}


	public String getFilepath3() {
		return filepath3;
	}


	public void setFilepath3(String filepath3) {
		this.filepath3 = filepath3;
	}


	public String getFilename3() {
		return filename3;
	}


	public void setFilename3(String filename3) {
		this.filename3 = filename3;
	}


	public double getApprovedamt() {
		return approvedamt;
	}


	public void setApprovedamt(double approvedamt) {
		this.approvedamt = approvedamt;
	}


	public String getCreatedat() {
		return createdat;
	}


	public void setCreatedat(String createdat) {
		this.createdat = createdat;
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


	public String getAttachhidden3() {
		return attachhidden3;
	}


	public void setAttachhidden3(String attachhidden3) {
		this.attachhidden3 = attachhidden3;
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


	public String getFilepres3() {
		return filepres3;
	}


	public void setFilepres3(String filepres3) {
		this.filepres3 = filepres3;
	}


	public String getFilepath4() {
		return filepath4;
	}


	public void setFilepath4(String filepath4) {
		this.filepath4 = filepath4;
	}


	public String getFilepath5() {
		return filepath5;
	}


	public void setFilepath5(String filepath5) {
		this.filepath5 = filepath5;
	}


	public String getAttachhidden4() {
		return attachhidden4;
	}


	public void setAttachhidden4(String attachhidden4) {
		this.attachhidden4 = attachhidden4;
	}


	public String getAttachhidden5() {
		return attachhidden5;
	}


	public void setAttachhidden5(String attachhidden5) {
		this.attachhidden5 = attachhidden5;
	}


	public String getFilepres4() {
		return filepres4;
	}


	public void setFilepres4(String filepres4) {
		this.filepres4 = filepres4;
	}


	public String getFilepres5() {
		return filepres5;
	}


	public void setFilepres5(String filepres5) {
		this.filepres5 = filepres5;
	}


	public String getAttachment4() {
		return attachment4;
	}


	public void setAttachment4(String attachment4) {
		this.attachment4 = attachment4;
	}


	public String getAttachment5() {
		return attachment5;
	}


	public void setAttachment5(String attachment5) {
		this.attachment5 = attachment5;
	}


	public String getFair() {
		return fair;
	}


	public void setFair(String fair) {
		this.fair = fair;
	}


	public String getCtgclaimamount() {
		return ctgclaimamount;
	}


	public void setCtgclaimamount(String ctgclaimamount) {
		this.ctgclaimamount = ctgclaimamount;
	}


	public String getPersonaleffectclaimamount() {
		return personaleffectclaimamount;
	}


	public void setPersonaleffectclaimamount(String personaleffectclaimamount) {
		this.personaleffectclaimamount = personaleffectclaimamount;
	}


	public String getVehicleclaimamount() {
		return vehicleclaimamount;
	}


	public void setVehicleclaimamount(String vehicleclaimamount) {
		this.vehicleclaimamount = vehicleclaimamount;
	}


	public String getFairclaimamount() {
		return fairclaimamount;
	}


	public void setFairclaimamount(String fairclaimamount) {
		this.fairclaimamount = fairclaimamount;
	}


	public double getCtgapproveamount() {
		return ctgapproveamount;
	}


	public void setCtgapproveamount(double ctgapproveamount) {
		this.ctgapproveamount = ctgapproveamount;
	}


	public double getPersonaleffectapproveamount() {
		return personaleffectapproveamount;
	}


	public void setPersonaleffectapproveamount(double personaleffectapproveamount) {
		this.personaleffectapproveamount = personaleffectapproveamount;
	}


	public double getVehicleapproveamount() {
		return vehicleapproveamount;
	}


	public void setVehicleapproveamount(double vehicleapproveamount) {
		this.vehicleapproveamount = vehicleapproveamount;
	}


	public double getFairapproveamount() {
		return fairapproveamount;
	}


	public void setFairapproveamount(double fairapproveamount) {
		this.fairapproveamount = fairapproveamount;
	}


	public CtgReimburse(long claimid, @Min(value = 1, message = "Please enter Work location.") long worklocationid,
			@Min(value = 1, message = "Please enter Om Project.") long omprojectid, String worklocation,
			String omproject, @NotBlank(message = "Please enter Transfer City.") String transfersamecity,
			@NotBlank(message = "Please enter City From.") String cityfrom,
			@NotBlank(message = "Please enter City To.") String cityto,
			@NotBlank(message = "Please enter Transfer Date.") String transferdate,
			@NotBlank(message = "Please enter Journey Date.") String journeydate, String personid, String personnumber,
			long reimburseid, String reimbursename, String ctgclaimstatus,
			@NotBlank(message = "Please enter Total Amount.") String totalactualpaidamount,
			@NotBlank(message = "Please enter Claim Amount.") String totalclaimamount, String status,
			String salaryslippreviousorganization,
			@NotBlank(message = "Please enter Joining Enclose.") String transferorjoiningenclose,
			String tranportationofpersonaleffectchain, String receiptenclose, String transportationpersonalconvey,
			String ownvehicle, String attachment1, String filepath1, String filename1, String attachment2,
			String filepath2, String filename2, String attachment3, String filepath3, String filename3,
			double approvedamt, String createdat, String attachhidden1, String attachhidden2, String attachhidden3,
			String filepres1, String filepres2, String filepres3, String filepath4, String filepath5,
			String attachhidden4, String attachhidden5, String filepres4, String filepres5, String attachment4,
			String attachment5, String fair, String ctgclaimamount, String personaleffectclaimamount,
			String vehicleclaimamount, String fairclaimamount, double ctgapproveamount,
			double personaleffectapproveamount, double vehicleapproveamount, double fairapproveamount) {
		super();
		this.claimid = claimid;
		this.worklocationid = worklocationid;
		this.omprojectid = omprojectid;
		this.worklocation = worklocation;
		this.omproject = omproject;
		this.transfersamecity = transfersamecity;
		this.cityfrom = cityfrom;
		this.cityto = cityto;
		this.transferdate = transferdate;
		this.journeydate = journeydate;
		this.personid = personid;
		this.personnumber = personnumber;
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.ctgclaimstatus = ctgclaimstatus;
		this.totalactualpaidamount = totalactualpaidamount;
		this.totalclaimamount = totalclaimamount;
		this.status = status;
		this.salaryslippreviousorganization = salaryslippreviousorganization;
		this.transferorjoiningenclose = transferorjoiningenclose;
		this.tranportationofpersonaleffectchain = tranportationofpersonaleffectchain;
		this.receiptenclose = receiptenclose;
		this.transportationpersonalconvey = transportationpersonalconvey;
		this.ownvehicle = ownvehicle;
		this.attachment1 = attachment1;
		this.filepath1 = filepath1;
		this.filename1 = filename1;
		this.attachment2 = attachment2;
		this.filepath2 = filepath2;
		this.filename2 = filename2;
		this.attachment3 = attachment3;
		this.filepath3 = filepath3;
		this.filename3 = filename3;
		this.approvedamt = approvedamt;
		this.createdat = createdat;
		this.attachhidden1 = attachhidden1;
		this.attachhidden2 = attachhidden2;
		this.attachhidden3 = attachhidden3;
		this.filepres1 = filepres1;
		this.filepres2 = filepres2;
		this.filepres3 = filepres3;
		this.filepath4 = filepath4;
		this.filepath5 = filepath5;
		this.attachhidden4 = attachhidden4;
		this.attachhidden5 = attachhidden5;
		this.filepres4 = filepres4;
		this.filepres5 = filepres5;
		this.attachment4 = attachment4;
		this.attachment5 = attachment5;
		this.fair = fair;
		this.ctgclaimamount = ctgclaimamount;
		this.personaleffectclaimamount = personaleffectclaimamount;
		this.vehicleclaimamount = vehicleclaimamount;
		this.fairclaimamount = fairclaimamount;
		this.ctgapproveamount = ctgapproveamount;
		this.personaleffectapproveamount = personaleffectapproveamount;
		this.vehicleapproveamount = vehicleapproveamount;
		this.fairapproveamount = fairapproveamount;
	}


	@Override
	public String toString() {
		return "CtgReimburse [claimid=" + claimid + ", worklocationid=" + worklocationid + ", omprojectid="
				+ omprojectid + ", worklocation=" + worklocation + ", omproject=" + omproject + ", transfersamecity="
				+ transfersamecity + ", cityfrom=" + cityfrom + ", cityto=" + cityto + ", transferdate=" + transferdate
				+ ", journeydate=" + journeydate + ", personid=" + personid + ", personnumber=" + personnumber
				+ ", reimburseid=" + reimburseid + ", reimbursename=" + reimbursename + ", ctgclaimstatus="
				+ ctgclaimstatus + ", totalactualpaidamount=" + totalactualpaidamount + ", totalclaimamount="
				+ totalclaimamount + ", status=" + status + ", salaryslippreviousorganization="
				+ salaryslippreviousorganization + ", transferorjoiningenclose=" + transferorjoiningenclose
				+ ", tranportationofpersonaleffectchain=" + tranportationofpersonaleffectchain + ", receiptenclose="
				+ receiptenclose + ", transportationpersonalconvey=" + transportationpersonalconvey + ", ownvehicle="
				+ ownvehicle + ", attachment1=" + attachment1 + ", filepath1=" + filepath1 + ", filename1=" + filename1
				+ ", attachment2=" + attachment2 + ", filepath2=" + filepath2 + ", filename2=" + filename2
				+ ", attachment3=" + attachment3 + ", filepath3=" + filepath3 + ", filename3=" + filename3
				+ ", approvedamt=" + approvedamt + ", createdat=" + createdat + ", attachhidden1=" + attachhidden1
				+ ", attachhidden2=" + attachhidden2 + ", attachhidden3=" + attachhidden3 + ", filepres1=" + filepres1
				+ ", filepres2=" + filepres2 + ", filepres3=" + filepres3 + ", filepath4=" + filepath4 + ", filepath5="
				+ filepath5 + ", attachhidden4=" + attachhidden4 + ", attachhidden5=" + attachhidden5 + ", filepres4="
				+ filepres4 + ", filepres5=" + filepres5 + ", attachment4=" + attachment4 + ", attachment5="
				+ attachment5 + ", fair=" + fair + ", ctgclaimamount=" + ctgclaimamount + ", personaleffectclaimamount="
				+ personaleffectclaimamount + ", vehicleclaimamount=" + vehicleclaimamount + ", fairclaimamount="
				+ fairclaimamount + ", ctgapproveamount=" + ctgapproveamount + ", personaleffectapproveamount="
				+ personaleffectapproveamount + ", vehicleapproveamount=" + vehicleapproveamount
				+ ", fairapproveamount=" + fairapproveamount + "]";
	}
	

}
