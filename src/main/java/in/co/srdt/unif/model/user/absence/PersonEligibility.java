package in.co.srdt.unif.model.user.absence;

public class PersonEligibility {

	
	
	private String description;
	
	public PersonEligibility() {
		
	}

	public PersonEligibility(String description) {
		super();
		this.description = description;
	}

	@Override
	public String toString() {
		return "PersonEligibility [description=" + description + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
