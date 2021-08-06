package in.co.srdt.unif.model.analysis;

public class HolidayData {
	private String description;
	private String holidaydate;
	private String holidayname;
	private String location;
	
	public HolidayData() {
		super();
	}

	public HolidayData(String description, String holidaydate, String holidayname, String location) {
		super();
		this.description = description;
		this.holidaydate = holidaydate;
		this.holidayname = holidayname;
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHolidaydate() {
		return holidaydate;
	}

	public void setHolidaydate(String holidaydate) {
		this.holidaydate = holidaydate;
	}

	public String getHolidayname() {
		return holidayname;
	}

	public void setHolidayname(String holidayname) {
		this.holidayname = holidayname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "HolidayData [description=" + description + ", holidaydate=" + holidaydate + ", holidayname="
				+ holidayname + ", location=" + location + "]";
	}
		
}
