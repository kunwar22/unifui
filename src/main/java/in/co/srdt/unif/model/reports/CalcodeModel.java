package in.co.srdt.unif.model.reports;

public class CalcodeModel {

	private Long calculationid;
	private String abbreviation;
	private String processdate;
	private String isretro;
	private String payamt;
	private String calendarcode;
	private String elementtype;
	private Long elementid;
	private String originalcalendarcode;
	
	
	public CalcodeModel() {
		super();
	}


	public CalcodeModel(Long calculationid, String abbreviation, String processdate,
			String isretro, String payamt, String calendarcode, String elementtype, Long elementid,
			String originalcalendarcode) {
		super();
		this.calculationid = calculationid;
		this.abbreviation = abbreviation;
		this.processdate = processdate;
		this.isretro = isretro;
		this.payamt = payamt;
		this.calendarcode = calendarcode;
		this.elementtype = elementtype;
		this.elementid = elementid;
		this.originalcalendarcode = originalcalendarcode;
	}


	public Long getCalculationid() {
		return calculationid;
	}


	public void setCalculationid(Long calculationid) {
		this.calculationid = calculationid;
	}


	public String getAbbreviation() {
		return abbreviation;
	}


	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}


	public String getProcessdate() {
		return processdate;
	}


	public void setProcessdate(String processdate) {
		this.processdate = processdate;
	}


	public String getIsretro() {
		return isretro;
	}


	public void setIsretro(String isretro) {
		this.isretro = isretro;
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


	public String getElementtype() {
		return elementtype;
	}


	public void setElementtype(String elementtype) {
		this.elementtype = elementtype;
	}


	public Long getElementid() {
		return elementid;
	}


	public void setElementid(Long elementid) {
		this.elementid = elementid;
	}


	public String getOriginalcalendarcode() {
		return originalcalendarcode;
	}


	public void setOriginalcalendarcode(String originalcalendarcode) {
		this.originalcalendarcode = originalcalendarcode;
	}


	@Override
	public String toString() {
		return "CalcodeModel [calculationid=" + calculationid + ", abbreviation="
				+ abbreviation + ", processdate=" + processdate + ", isretro=" + isretro + ", payamt=" + payamt
				+ ", calendarcode=" + calendarcode + ", elementtype=" + elementtype + ", elementid=" + elementid
				+ ", originalcalendarcode=" + originalcalendarcode + "]";
	}
}
