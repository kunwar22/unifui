package in.co.srdt.unif.model.holiday;

public class HolidayLocationLOV {

	public long id;
	
	public String description;
	
	public String checked;

	public HolidayLocationLOV() {
	}

	public HolidayLocationLOV(long id, String description, String checked) {
		super();
		this.id = id;
		this.description = description;
		this.checked = checked;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	
	
}
