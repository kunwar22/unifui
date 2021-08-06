package in.co.srdt.unif.model.reports;

public class LwpReport {
	
	private String personNumber;
	private String personName;
	private String businessunit;
	private String lwpstatus;
	private String lwpdate;
	private String lwpdescription;
	
	public String getPersonNumber() {
		return personNumber;
	}
	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getBusinessunit() {
		return businessunit;
	}
	public void setBusinessunit(String businessunit) {
		this.businessunit = businessunit;
	}
	public String getLwpstatus() {
		return lwpstatus;
	}
	public void setLwpstatus(String lwpstatus) {
		this.lwpstatus = lwpstatus;
	}
	public String getLwpdate() {
		return lwpdate;
	}
	public void setLwpdate(String lwpdate) {
		this.lwpdate = lwpdate;
	}
	public String getLwpdescription() {
		return lwpdescription;
	}
	public void setLwpdescription(String lwpdescription) {
		this.lwpdescription = lwpdescription;
	}
	@Override
	public String toString() {
		return "LwpReport [personNumber=" + personNumber + ", personName=" + personName + ", businessunit="
				+ businessunit + ", lwpstatus=" + lwpstatus + ", lwpdate=" + lwpdate + ", lwpdescription="
				+ lwpdescription + "]";
	}
	public LwpReport(String personNumber, String personName, String businessunit, String lwpstatus, String lwpdate,
			String lwpdescription) {
		super();
		this.personNumber = personNumber;
		this.personName = personName;
		this.businessunit = businessunit;
		this.lwpstatus = lwpstatus;
		this.lwpdate = lwpdate;
		this.lwpdescription = lwpdescription;
	}
	public LwpReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
