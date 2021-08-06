package in.co.srdt.unif.model.payslip;

public class PayrollData {
	
	private String personnumber;
	private String personname;
	private String calendarid;
	private String payamt;
	private String calendarcode;
	private String abbreviation;
	private String elementtype;
	private String paiddays;
	public PayrollData() {
		super();
	}
	public PayrollData(String personnumber, String personname, String calendarid, String payamt, String calendarcode,
			String abbreviation,String elementtype) {
		super();
		this.personnumber = personnumber;
		this.personname = personname;
		this.calendarid = calendarid;
		this.payamt = payamt;
		this.calendarcode = calendarcode;
		this.abbreviation = abbreviation;
		this.elementtype= elementtype;
	}
	
	
	public PayrollData(String personnumber, String personname, String calendarid, String payamt, String calendarcode,
			String abbreviation, String elementtype, String paiddays) {
		this.personnumber = personnumber;
		this.personname = personname;
		this.calendarid = calendarid;
		this.payamt = payamt;
		this.calendarcode = calendarcode;
		this.abbreviation = abbreviation;
		this.elementtype = elementtype;
		this.paiddays = paiddays;
	}
	public String getPaiddays() {
		return paiddays;
	}
	public void setPaiddays(String paiddays) {
		this.paiddays = paiddays;
	}
	public String getElementtype() {
		return elementtype;
	}
	public void setElementtype(String elementtype) {
		this.elementtype = elementtype;
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
	public String getCalendarid() {
		return calendarid;
	}
	public void setCalendarid(String calendarid) {
		this.calendarid = calendarid;
	}
	public String getPayamt() {
		return payamt;
	}
	public void setPayamt(String payamt) {
		this.payamt = payamt;
	}
	public String getCalendarcode() {
		return calendarcode;
	}
	public void setCalendarcode(String calendarcode) {
		this.calendarcode = calendarcode;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
}
