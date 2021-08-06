package in.co.srdt.unif.model.newperson;

public class BankDetails {

	private String accountholdername;
	private String accounttype;
	private String bankaccountnumber;
	private long bankaccountsid;
	private String branchifsccode;
	private String personid;
	private String personnumber;
	private String bankname;
	private String bankaddress;
	private String mode;
	private String createdby;
	private String updatedby;
	private String status;
	
	private String effectivestartdate;
	private String lastupdateddate;

	public BankDetails() {

	}

	public String getAccountholdername() {
		return accountholdername;
	}

	public void setAccountholdername(String accountholdername) {
		this.accountholdername = accountholdername;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getBankaccountnumber() {
		return bankaccountnumber;
	}

	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}

	public long getBankaccountsid() {
		return bankaccountsid;
	}

	public void setBankaccountsid(long bankaccountsid) {
		this.bankaccountsid = bankaccountsid;
	}

	public String getBranchifsccode() {
		return branchifsccode;
	}

	public void setBranchifsccode(String branchifsccode) {
		this.branchifsccode = branchifsccode;
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

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankaddress() {
		return bankaddress;
	}

	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEffectivestartdate() {
		return effectivestartdate;
	}

	public void setEffectivestartdate(String effectivestartdate) {
		this.effectivestartdate = effectivestartdate;
	}

	public String getLastupdateddate() {
		return lastupdateddate;
	}

	public void setLastupdateddate(String lastupdateddate) {
		this.lastupdateddate = lastupdateddate;
	}

	@Override
	public String toString() {
		return "BankDetails [accountholdername=" + accountholdername + ", accounttype=" + accounttype
				+ ", bankaccountnumber=" + bankaccountnumber + ", bankaccountsid=" + bankaccountsid
				+ ", branchifsccode=" + branchifsccode + ", personid=" + personid + ", personnumber=" + personnumber
				+ ", bankname=" + bankname + ", bankaddress=" + bankaddress + ", mode=" + mode + ", createdby="
				+ createdby + ", updatedby=" + updatedby + ", status=" + status + ", effectivestartdate="
				+ effectivestartdate + ", lastupdateddate=" + lastupdateddate + "]";
	}

	
	
}
