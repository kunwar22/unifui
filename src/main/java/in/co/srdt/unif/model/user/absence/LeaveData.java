package in.co.srdt.unif.model.user.absence;

public class LeaveData {
	private String day;
	private String week;
	private String half;
	private String year;
	private String date;
	
	public LeaveData() {
		
	}

	public LeaveData(String day, String week, String half, String year, String date) {
		super();
		this.day = day;
		this.week = week;
		this.half = half;
		this.year = year;
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getHalf() {
		return half;
	}

	public void setHalf(String half) {
		this.half = half;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "LeaveData [day=" + day + ", week=" + week + ", half=" + half + ", year=" + year + ", date=" + date
				+ "]";
	}
	
	

}
