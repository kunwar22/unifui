package in.co.srdt.unif.model.rembmiscellaneous;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class MiscelleneousReimburse {

	private long claimid;
	
	private String worklocation;
	private String omproject;
	@Min(value = 1, message = "Please enter Work Location.")
	private long worklocationid;
	@Min(value = 1, message = "Please enter OM/Project Location.")
	private long omprojectid;
	@NotBlank(message = "Please enter Expence Description.")
	private String expensedescription;
	@NotBlank(message = "Please enter Bill No.")
	private String billno;
	@NotBlank(message = "Please enter Bill Date.")
	private String billdate;
	private String personid;
	private String personnumber;
	private long reimburseid;
	private String reimbursename;
	@NotBlank(message = "Please enter Claim Amount.")
	private String claimamount;
	@NotBlank(message = "Please enter paid to.")
	private String paidto;
	@NotBlank(message = "Please enter Expence Code.")
	private String expenseaccountcode;
	@NotBlank(message = "Please enter Account Description.")
	private String accountdescription;
	@NotBlank(message = "Please enter Vehical Used.")
	private String vehicleused;
	private String attachment;
	private String filepath;
	private String filename ;
	private double approvedamt;
	private String createdat;
	private String status;
	private String attachhidden;
	
	public MiscelleneousReimburse()
	{
		
	}

	public String getAttachhidden() {
		return attachhidden;
	}

	public void setAttachhidden(String attachhidden) {
		this.attachhidden = attachhidden;
	}

	public long getClaimid() {
		return claimid;
	}

	public void setClaimid(long claimid) {
		this.claimid = claimid;
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

	public String getExpensedescription() {
		return expensedescription;
	}

	public void setExpensedescription(String expensedescription) {
		this.expensedescription = expensedescription;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getBilldate() {
		return billdate;
	}

	public void setBilldate(String billdate) {
		this.billdate = billdate;
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

	public String getClaimamount() {
		return claimamount;
	}

	public void setClaimamount(String claimamount) {
		this.claimamount = claimamount;
	}

	public String getPaidto() {
		return paidto;
	}

	public void setPaidto(String paidto) {
		this.paidto = paidto;
	}

	public String getExpenseaccountcode() {
		return expenseaccountcode;
	}

	public void setExpenseaccountcode(String expenseaccountcode) {
		this.expenseaccountcode = expenseaccountcode;
	}

	public String getAccountdescription() {
		return accountdescription;
	}

	public void setAccountdescription(String accountdescription) {
		this.accountdescription = accountdescription;
	}

	public String getVehicleused() {
		return vehicleused;
	}

	public void setVehicleused(String vehicleused) {
		this.vehicleused = vehicleused;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MiscelleneousReimburse{" +
				"claimid=" + claimid +
				", worklocation='" + worklocation + '\'' +
				", omproject='" + omproject + '\'' +
				", worklocationid=" + worklocationid +
				", omprojectid=" + omprojectid +
				", expensedescription='" + expensedescription + '\'' +
				", billno='" + billno + '\'' +
				", billdate='" + billdate + '\'' +
				", personid='" + personid + '\'' +
				", personnumber='" + personnumber + '\'' +
				", reimburseid=" + reimburseid +
				", reimbursename='" + reimbursename + '\'' +
				", claimamount='" + claimamount + '\'' +
				", paidto='" + paidto + '\'' +
				", expenseaccountcode='" + expenseaccountcode + '\'' +
				", accountdescription='" + accountdescription + '\'' +
				", vehicleused='" + vehicleused + '\'' +
				", attachment='" + attachment + '\'' +
				", filepath='" + filepath + '\'' +
				", filename='" + filename + '\'' +
				", approvedamt=" + approvedamt +
				", createdat='" + createdat + '\'' +
				", status='" + status + '\'' +
				", attachhidden='" + attachhidden + '\'' +
				'}';
	}

	public MiscelleneousReimburse(long claimid, String worklocation, String omproject, @Min(value = 1, message = "Please enter Work Location.") long worklocationid, @Min(value = 1, message = "Please enter OM/Project Location.") long omprojectid, @NotBlank(message = "Please enter Expence Description.") String expensedescription, @NotBlank(message = "Please enter Bill No.") String billno, @NotBlank(message = "Please enter Bill Date.") String billdate, String personid, String personnumber, long reimburseid, String reimbursename, @NotBlank(message = "Please enter Claim Amount.") String claimamount, @NotBlank(message = "Please enter paid to.") String paidto, @NotBlank(message = "Please enter Expence Code.") String expenseaccountcode, @NotBlank(message = "Please enter Account Description.") String accountdescription, @NotBlank(message = "Please enter Vehical Used.") String vehicleused, String attachment, String filepath, String filename, double approvedamt, String createdat, String status, String attachhidden) {
		this.claimid = claimid;
		this.worklocation = worklocation;
		this.omproject = omproject;
		this.worklocationid = worklocationid;
		this.omprojectid = omprojectid;
		this.expensedescription = expensedescription;
		this.billno = billno;
		this.billdate = billdate;
		this.personid = personid;
		this.personnumber = personnumber;
		this.reimburseid = reimburseid;
		this.reimbursename = reimbursename;
		this.claimamount = claimamount;
		this.paidto = paidto;
		this.expenseaccountcode = expenseaccountcode;
		this.accountdescription = accountdescription;
		this.vehicleused = vehicleused;
		this.attachment = attachment;
		this.filepath = filepath;
		this.filename = filename;
		this.approvedamt = approvedamt;
		this.createdat = createdat;
		this.status = status;
		this.attachhidden = attachhidden;
	}
}
