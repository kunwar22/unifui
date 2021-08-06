package in.co.srdt.unif.enums;

public enum EmailType {

	Work_Email("Work_Email"),
	Home_Email("Home_Email");
	
	private String description;

	private EmailType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
