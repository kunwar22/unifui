package in.co.srdt.unif.model.reports;

public class BankSalaryAdvice {

	private String personnumber;
	private String personname;
	private String grossamount;
	private String tds;
	private String totaldeduction;
	private String neypay;
	
	public BankSalaryAdvice() {
		super();
	}

	public BankSalaryAdvice(String personnumber, String personname, String grossamount, String tds,
			String totaldeduction, String neypay) {
		super();
		this.personnumber = personnumber;
		this.personname = personname;
		this.grossamount = grossamount;
		this.tds = tds;
		this.totaldeduction = totaldeduction;
		this.neypay = neypay;
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

	public String getGrossamount() {
		return grossamount;
	}

	public void setGrossamount(String grossamount) {
		this.grossamount = grossamount;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public String getTotaldeduction() {
		return totaldeduction;
	}

	public void setTotaldeduction(String totaldeduction) {
		this.totaldeduction = totaldeduction;
	}

	public String getNeypay() {
		return neypay;
	}

	public void setNeypay(String neypay) {
		this.neypay = neypay;
	}

	@Override
	public String toString() {
		return "BankSalaryAdvice [personnumber=" + personnumber + ", personname=" + personname + ", grossamount="
				+ grossamount + ", tds=" + tds + ", totaldeduction=" + totaldeduction + ", neypay=" + neypay + "]";
	}
	
	
}
