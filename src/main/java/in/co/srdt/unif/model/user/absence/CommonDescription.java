package in.co.srdt.unif.model.user.absence;

public class CommonDescription {

	private String description;


	public CommonDescription() {
	}


	public CommonDescription(String description) {
		super();
		this.description = description;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "CommonDescription [description=" + description + "]";
	}
	
}
