package in.co.srdt.unif.model;

public class CommonLOV {

	public long id;
	
	public String description;

	public CommonLOV(long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public CommonLOV() {
	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "CommonLOV [id=" + id + ", description=" + description + "]";
	}
	
	
	
	
}
