package in.co.srdt.unif.model.holiday;

import java.util.ArrayList;
import java.util.List;


public class Holiday {

	private long actionid;

	private long holidayid;

	private String name;

	private String description;

	private String date;

	private long holidaytypeid;

	private String holidaytypename;

	private String standardhours;

	private String halfday;

	private String status;
	
	private List<HolidayLocation> location;

	public Holiday() {
		location = new ArrayList<>();
		location.add(new HolidayLocation());
	}

	public Holiday(long actionid, long holidayid, String name, String description, String date, long holidaytypeid,
			String holidaytypename, String standardhours, String halfday, String status,
			List<HolidayLocation> location) {
		super();
		this.actionid = actionid;
		this.holidayid = holidayid;
		this.name = name;
		this.description = description;
		this.date = date;
		this.holidaytypeid = holidaytypeid;
		this.holidaytypename = holidaytypename;
		this.standardhours = standardhours;
		this.halfday = halfday;
		this.status = status;
		this.location = location;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getHolidaytypeid() {
		return holidaytypeid;
	}

	public void setHolidaytypeid(long holidaytypeid) {
		this.holidaytypeid = holidaytypeid;
	}

	public String getHolidaytypename() {
		return holidaytypename;
	}

	public void setHolidaytypename(String holidaytypename) {
		this.holidaytypename = holidaytypename;
	}

	public String getStandardhours() {
		return standardhours;
	}

	public void setStandardhours(String standardhours) {
		this.standardhours = standardhours;
	}

	public String getHalfday() {
		return halfday;
	}

	public void setHalfday(String halfday) {
		this.halfday = halfday;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<HolidayLocation> getLocation() {
		return location;
	}

	public void setLocation(List<HolidayLocation> location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Holiday [actionid=" + actionid + ", holidayid=" + holidayid + ", name=" + name + ", description="
				+ description + ", date=" + date + ", holidaytypeid=" + holidaytypeid + ", holidaytypename="
				+ holidaytypename + ", standardhours=" + standardhours + ", halfday=" + halfday + ", status=" + status
				+ ", location=" + location + "]";
	}
	
	
	
}