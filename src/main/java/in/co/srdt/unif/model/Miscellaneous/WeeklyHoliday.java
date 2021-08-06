package in.co.srdt.unif.model.Miscellaneous;

public class WeeklyHoliday {

	
	private long weeklyholidayid;

	private String day;

	private String weeks;

	private String halfday;

	private String year;

	private long repeatingperiodid;

    //private String repeatingperiodname; remove
	
	private String repeatingperiodstartdate; // add new

	private String repeatingperiodenddate;// add new

	public WeeklyHoliday() {

	}

	public WeeklyHoliday(long weeklyholidayid, String day, String weeks, String halfday, String year,
			long repeatingperiodid, String repeatingperiodstartdate, String repeatingperiodenddate) {
		super();
		this.weeklyholidayid = weeklyholidayid;
		this.day = day;
		this.weeks = weeks;
		this.halfday = halfday;
		this.year = year;
		this.repeatingperiodid = repeatingperiodid;
		this.repeatingperiodstartdate = repeatingperiodstartdate;
		this.repeatingperiodenddate = repeatingperiodenddate;
	}

	public long getWeeklyholidayid() {
		return weeklyholidayid;
	}

	public void setWeeklyholidayid(long weeklyholidayid) {
		this.weeklyholidayid = weeklyholidayid;
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

	public long getRepeatingperiodid() {
		return repeatingperiodid;
	}

	public void setRepeatingperiodid(long repeatingperiodid) {
		this.repeatingperiodid = repeatingperiodid;
	}

	public String getRepeatingperiodstartdate() {
		return repeatingperiodstartdate;
	}

	public void setRepeatingperiodstartdate(String repeatingperiodstartdate) {
		this.repeatingperiodstartdate = repeatingperiodstartdate;
	}

	public String getRepeatingperiodenddate() {
		return repeatingperiodenddate;
	}

	public void setRepeatingperiodenddate(String repeatingperiodenddate) {
		this.repeatingperiodenddate = repeatingperiodenddate;
	}

	@Override
	public String toString() {
		return "WeeklyHoliday [weeklyholidayid=" + weeklyholidayid + ", day=" + day + ", weeks=" + weeks + ", halfday="
				+ halfday + ", year=" + year + ", repeatingperiodid=" + repeatingperiodid
				+ ", repeatingperiodstartdate=" + repeatingperiodstartdate + ", repeatingperiodenddate="
				+ repeatingperiodenddate + "]";
	}

	
	
	
}
