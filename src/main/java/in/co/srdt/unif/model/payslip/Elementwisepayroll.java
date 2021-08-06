package in.co.srdt.unif.model.payslip;

public class Elementwisepayroll {
	private String personnumber;
	private String personname;
	private String calendarid;
	private double payamt;
	private String calendarcode;
	private String abbreviation;
	private String elementtype;

	public String getElementtype() {
		return elementtype;
	}

	public void setElementtype(String elementtype) {
		this.elementtype = elementtype;
	}

	public Elementwisepayroll(String personnumber, String personname, String calendarid, /* long val, */ double payamt,
			String calendarcode, String abbreviation, String elementtype) {
		super();
		this.personnumber = personnumber;
		this.personname = personname;
		this.calendarid = calendarid;
		this.payamt = payamt;
		this.calendarcode = calendarcode;
		this.abbreviation = abbreviation;
		this.elementtype = elementtype;
	}

	public Elementwisepayroll() {
		super();
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

	@Override
	public String toString() {
		return "Elementwisepayroll [personnumber=" + personnumber + ", personname=" + personname + ", calendarid="
				+ calendarid + ", payamt=" + payamt + ", calendarcode=" + calendarcode + ", abbreviation="
				+ abbreviation + ", abbreviation=" + abbreviation + "]";
	}
}
