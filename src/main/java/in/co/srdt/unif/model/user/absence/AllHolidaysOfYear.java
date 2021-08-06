package in.co.srdt.unif.model.user.absence;

public class AllHolidaysOfYear {
	private String holidaydate;
	private String halfday;
	
	public AllHolidaysOfYear() {
		
	}

	public AllHolidaysOfYear(String holidaydate, String halfday) {
		super();
		this.holidaydate = holidaydate;
		this.halfday = halfday;
	}

	public String getHolidaydate() {
		return holidaydate;
	}

	public void setHolidaydate(String holidaydate) {
		this.holidaydate = holidaydate;
	}

	public String getHalfday() {
		return halfday;
	}

	public void setHalfday(String halfday) {
		this.halfday = halfday;
	}

	@Override
	public String toString() {
		return "AllHolidaysOfYear [holidaydate=" + holidaydate + ", halfday=" + halfday + "]";
	}
	

}
