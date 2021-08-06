package in.co.srdt.unif.model.holiday;

public class HolidaySearch {
	
	
	private String name;

	private String holidaytypename;

	private String description;

	private long holidayid;

	private String date;
	
	private String status;
	
	public HolidaySearch() {
		super();
	}

	public HolidaySearch(String name, String holidaytypename, String description, long holidayid, String date,
			String status) {
		super();
		this.name = name;
		this.holidaytypename = holidaytypename;
		this.description = description;
		this.holidayid = holidayid;
		this.date = date;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHolidaytypename() {
		return holidaytypename;
	}

	public void setHolidaytypename(String holidaytypename) {
		this.holidaytypename = holidaytypename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getHolidayid() {
		return holidayid;
	}

	public void setHolidayid(long holidayid) {
		this.holidayid = holidayid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "HolidaySearch [name=" + name + ", holidaytypename=" + holidaytypename + ", description=" + description
				+ ", holidayid=" + holidayid + ", date=" + date + ", status=" + status + "]";
	}

	

}
