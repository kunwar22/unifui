package in.co.srdt.unif.model.reports;

public class BankAdviceRembModel {

	private String personnumber;
	private String personname;
	private String bankaccountnumber;
	private String branchifsccode;
	private String bankname;
	private int reimapprovedamt;
	
	public BankAdviceRembModel() {
		super();
	}

	public BankAdviceRembModel(String personnumber, String personname, String bankaccountnumber, String branchifsccode,
			String bankname, int reimapprovedamt) {
		super();
		this.personnumber = personnumber;
		this.personname = personname;
		this.bankaccountnumber = bankaccountnumber;
		this.branchifsccode = branchifsccode;
		this.bankname = bankname;
		this.reimapprovedamt = reimapprovedamt;
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

	public String getBankaccountnumber() {
		return bankaccountnumber;
	}

	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}

	public String getBranchifsccode() {
		return branchifsccode;
	}

	public void setBranchifsccode(String branchifsccode) {
		this.branchifsccode = branchifsccode;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public int getReimapprovedamt() {
		return reimapprovedamt;
	}

	public void setReimapprovedamt(int reimapprovedamt) {
		this.reimapprovedamt = reimapprovedamt;
	}

	@Override
	public String toString() {
		return "BankAdviceRembModel [personnumber=" + personnumber + ", personname=" + personname
				+ ", bankaccountnumber=" + bankaccountnumber + ", branchifsccode=" + branchifsccode + ", bankname="
				+ bankname + ", reimapprovedamt=" + reimapprovedamt + "]";
	}
	
}
