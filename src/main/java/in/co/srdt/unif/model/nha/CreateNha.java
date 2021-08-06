package in.co.srdt.unif.model.nha;

public class CreateNha {

	public String description;
	public long holidayid;
	public String holidayname;
	public long location;
	public long nhaid;
	public String period;
	public String personnumber;
	public String status;
	
	public CreateNha() {
		super();
	}

	
	
	public CreateNha(String description, long holidayid, String holidayname, long location, long nhaid, String period,
			String personnumber, String status) {
		super();
		this.description = description;
		this.holidayid = holidayid;
		this.holidayname = holidayname;
		this.location = location;
		this.nhaid = nhaid;
		this.period = period;
		this.personnumber = personnumber;
		this.status = status;
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



	public String getHolidayname() {
		return holidayname;
	}



	public void setHolidayname(String holidayname) {
		this.holidayname = holidayname;
	}



	public long getLocation() {
		return location;
	}



	public void setLocation(long location) {
		this.location = location;
	}



	public long getNhaid() {
		return nhaid;
	}



	public void setNhaid(long nhaid) {
		this.nhaid = nhaid;
	}



	public String getPeriod() {
		return period;
	}



	public void setPeriod(String period) {
		this.period = period;
	}



	public String getPersonnumber() {
		return personnumber;
	}



	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "CreateNha [description=" + description + ", holidayid=" + holidayid + ", holidayname=" + holidayname
				+ ", location=" + location + ", nhaid=" + nhaid + ", period=" + period + ", personnumber="
				+ personnumber + ", status=" + status + "]";
	}



	
	
}
