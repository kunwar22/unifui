package in.co.srdt.unif.model.bank;

import javax.validation.constraints.NotBlank;

public class CreateBankAccountsDetails {
	
	@NotBlank(message = "Please enter Account Holder Name.")
   private String accountholdername ;
	@NotBlank(message = "Please enter Account Type.")
   private String accounttype ;
	@NotBlank(message = "Please enter Bank Acount Number.")
   private String bankaccountnumber ;
   private long bankaccountsid ;
   @NotBlank(message = "Please enter IFSC Code")
   private String branchifsccode ;
   private String personid ;
   private String personnumber ;
   
   @NotBlank(message = "Please enter Bank Name.")
   private String bankname;
   @NotBlank(message = "Please enter Bank Address.")
   private String bankaddress;
	private String createdby;
	private String updatedby;

   
   public CreateBankAccountsDetails() {
	super();
}

	public CreateBankAccountsDetails(@NotBlank(message = "Please enter Account Holder Name.") String accountholdername, @NotBlank(message = "Please enter Account Type.") String accounttype, @NotBlank(message = "Please enter Bank Acount Number.") String bankaccountnumber, long bankaccountsid, @NotBlank(message = "Please enter IFSC Code") String branchifsccode, String personid, String personnumber, @NotBlank(message = "Please enter Bank Name.") String bankname, @NotBlank(message = "Please enter Bank Address.") String bankaddress, String createdby, String updatedby) {
		this.accountholdername = accountholdername;
		this.accounttype = accounttype;
		this.bankaccountnumber = bankaccountnumber;
		this.bankaccountsid = bankaccountsid;
		this.branchifsccode = branchifsccode;
		this.personid = personid;
		this.personnumber = personnumber;
		this.bankname = bankname;
		this.bankaddress = bankaddress;
		this.createdby = createdby;
		this.updatedby = updatedby;
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


	@Override
	public String toString() {
		return "CreateBankAccountsDetails{" +
				"accountholdername='" + accountholdername + '\'' +
				", accounttype='" + accounttype + '\'' +
				", bankaccountnumber='" + bankaccountnumber + '\'' +
				", bankaccountsid=" + bankaccountsid +
				", branchifsccode='" + branchifsccode + '\'' +
				", personid='" + personid + '\'' +
				", personnumber='" + personnumber + '\'' +
				", bankname='" + bankname + '\'' +
				", bankaddress='" + bankaddress + '\'' +
				", createdby='" + createdby + '\'' +
				", updatedby='" + updatedby + '\'' +
				'}';
	}
}
