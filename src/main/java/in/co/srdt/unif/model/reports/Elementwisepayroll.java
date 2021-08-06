package in.co.srdt.unif.model.reports;

public class Elementwisepayroll {
	private String personnumber;
	private String personname;
	private String calendarid;
	private double payamt;
	private String calendarcode;
	private String abbreviation;
	private String elementtype;
	private String paiddays;
	
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
	public double getPayamt() {
		return payamt;
	}
	public void setPayamt(double payamt) {
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
	public String getElementtype() {
		return elementtype;
	}
	public void setElementtype(String elementtype) {
		this.elementtype = elementtype;
	}
	public String getPaiddays() {
		return paiddays;
	}
	public void setPaiddays(String paiddays) {
		this.paiddays = paiddays;
	}
	@Override
	public String toString() {
		return "Elementwisepayroll [personnumber=" + personnumber + ", personname=" + personname + ", calendarid="
				+ calendarid + ", payamt=" + payamt + ", calendarcode=" + calendarcode + ", abbreviation="
				+ abbreviation + ", elementtype=" + elementtype + ", paiddays=" + paiddays + "]";
	}
	public Elementwisepayroll(String personnumber, String personname, String calendarid, double payamt,
			String calendarcode, String abbreviation, String elementtype, String paiddays) {
		super();
		this.personnumber = personnumber;
		this.personname = personname;
		this.calendarid = calendarid;
		this.payamt = payamt;
		this.calendarcode = calendarcode;
		this.abbreviation = abbreviation;
		this.elementtype = elementtype;
		this.paiddays = paiddays;
	}
	public Elementwisepayroll() {
		super();
	}

	
}
