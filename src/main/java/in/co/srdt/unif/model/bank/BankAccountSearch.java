package in.co.srdt.unif.model.bank;

public class BankAccountSearch {

	   private String accountholdername ;
	   private String accounttype ;
	   private String bankaccountnumber ;
	   private long bankaccountsid ;
	   private String branchifsccode ;
	   private String personid ;
	   private String personnumber ;
	   
	   private String bankname;
	   private String bankaddress;
	
    public BankAccountSearch() {
		
	}

	public BankAccountSearch(String accountholdername, String accounttype, String bankaccountnumber,
			long bankaccountsid, String branchifsccode, String personid, String personnumber, String bankname,
			String bankaddress) {
		super();
		this.accountholdername = accountholdername;
		this.accounttype = accounttype;
		this.bankaccountnumber = bankaccountnumber;
		this.bankaccountsid = bankaccountsid;
		this.branchifsccode = branchifsccode;
		this.personid = personid;
		this.personnumber = personnumber;
		this.bankname = bankname;
		this.bankaddress = bankaddress;
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
		return "BankAccountSearch [accountholdername=" + accountholdername + ", accounttype=" + accounttype
				+ ", bankaccountnumber=" + bankaccountnumber + ", bankaccountsid=" + bankaccountsid
				+ ", branchifsccode=" + branchifsccode + ", personid=" + personid + ", personnumber=" + personnumber
				+ ", bankname=" + bankname + ", bankaddress=" + bankaddress + "]";
	}

	

}
