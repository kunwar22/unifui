package in.co.srdt.unif.model.eligibilityreimb;

public class PersonData {
	private String description;

	public PersonData(String description) {
		super();
		this.description = description;
	}

	public PersonData() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PersonData [description=" + description + "]";
	}
	
}
