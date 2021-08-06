package in.co.srdt.unif.model.user.absence;

public class AllWeeklyHolidays {

	private String day;
	private String weeks;
	private String halfday;
	private String year;
	
	public AllWeeklyHolidays() {
		
	}

	public AllWeeklyHolidays(String day, String weeks, String halfday, String year) {
		super();
		this.day = day;
		this.weeks = weeks;
		this.halfday = halfday;
		this.year = year;
	}

	@Override
	public String toString() {
		return "AllWeeklyHolidays [day=" + day + ", weeks=" + weeks + ", halfday=" + halfday + ", year=" + year + "]";
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public String getHalfday() {
		return halfday;
	}

	public void setHalfday(String halfday) {
		this.halfday = halfday;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	
	
}
