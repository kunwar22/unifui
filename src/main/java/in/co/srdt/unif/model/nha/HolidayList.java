package in.co.srdt.unif.model.nha;

public class HolidayList {

	public String holidayid;
	public String holidayname;
	public String holidaydate;
	public String locationid;
	
	public HolidayList() {
		super();
	}

	public HolidayList(String holidayid, String holidayname, String holidaydate, String locationid) {
		super();
		this.holidayid = holidayid;
		this.holidayname = holidayname;
		this.holidaydate = holidaydate;
		this.locationid = locationid;
	}

	public String getHolidayid() {
		return holidayid;
	}

	public void setHolidayid(String holidayid) {
		this.holidayid = holidayid;
	}

	public String getHolidayname() {
		return holidayname;
	}

	public void setHolidayname(String holidayname) {
		this.holidayname = holidayname;
	}

	public String getHolidaydate() {
		return holidaydate;
	}

	public void setHolidaydate(String holidaydate) {
		this.holidaydate = holidaydate;
	}

	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	@Override
	public String toString() {
		return "GetHolidayList [holidayid=" + holidayid + ", holidayname=" + holidayname + ", holidaydate="
				+ holidaydate + ", locationid=" + locationid + "]";
	}
	
	

}
