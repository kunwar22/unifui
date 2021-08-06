package in.co.srdt.unif.model.user.absence;

public class AbsenceLOV {

	private long id;
	private String description;
	private long repeatingperiodid;


	public AbsenceLOV() {
	}


	public AbsenceLOV(long id, String description, long repeatingperiodid) {
		super();
		this.id = id;
		this.description = description;
		this.repeatingperiodid = repeatingperiodid;
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


	public long getRepeatingperiodid() {
		return repeatingperiodid;
	}


	public void setRepeatingperiodid(long repeatingperiodid) {
		this.repeatingperiodid = repeatingperiodid;
	}


	@Override
	public String toString() {
		return "AbsenceLOV [id=" + id + ", description=" + description + ", repeatingperiodid=" + repeatingperiodid
				+ "]";
	}

	
	
	
	
}
