package in.co.srdt.unif.model.reports;

public class BankTypeReportModel {

	private String personnumber;
	private String personname;
	private String natureofemployement;
	private String bankaccountnumber;
	private String branchifsccode;
	private String bankname;
	private int netpay;
	
	public BankTypeReportModel() {
		super();
	}

	public BankTypeReportModel(String personnumber, String personname, String natureofemployement,
			String bankaccountnumber, String branchifsccode, String bankname, int netpay) {
		super();
		this.personnumber = personnumber;
		this.personname = personname;
		this.natureofemployement = natureofemployement;
		this.bankaccountnumber = bankaccountnumber;
		this.branchifsccode = branchifsccode;
		this.bankname = bankname;
		this.netpay = netpay;
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

	public String getNatureofemployement() {
		return natureofemployement;
	}

	public void setNatureofemployement(String natureofemployement) {
		this.natureofemployement = natureofemployement;
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

	public int getNetpay() {
		return netpay;
	}

	public void setNetpay(int netpay) {
		this.netpay = netpay;
	}

	@Override
	public String toString() {
		return "BankTypeReportModel [personnumber=" + personnumber + ", personname=" + personname
				+ ", natureofemployement=" + natureofemployement + ", bankaccountnumber=" + bankaccountnumber
				+ ", branchifsccode=" + branchifsccode + ", bankname=" + bankname + ", netpay=" + netpay + "]";
	}
	
	
	
	
	
	
}
