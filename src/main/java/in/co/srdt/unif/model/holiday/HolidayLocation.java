package in.co.srdt.unif.model.holiday;

public class HolidayLocation {

	
	private long actionid;// ato generat
	
	private long holidayid;// ato generat

	private long locationid;
	
	private String locationname;// dont use for paylod
	
	private String msg;

	public HolidayLocation() {
		
	}

	public HolidayLocation(long actionid, long holidayid, long locationid, String locationname, String msg) {
		super();
		this.actionid = actionid;
		this.holidayid = holidayid;
		this.locationid = locationid;
		this.locationname = locationname;
		this.msg = msg;
	}
	
	

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getHolidayid() {
		return holidayid;
	}

	public void setHolidayid(long holidayid) {
		this.holidayid = holidayid;
	}

	public long getLocationid() {
		return locationid;
	}

	public void setLocationid(long locationid) {
		this.locationid = locationid;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public String getMsg() {
		return msg="check";
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "HolidayLocation [actionid=" + actionid + ", holidayid=" + holidayid + ", locationid=" + locationid
				+ ", locationname=" + locationname + ", msg=" + msg + "]";
	}

	

}
