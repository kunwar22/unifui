package in.co.srdt.unif.enums;

public enum PhoneType {

	Work_Phone("Work_Phone"),
	Home_Phone("Home_Phone");
	
	private String description;

	private PhoneType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	
}
