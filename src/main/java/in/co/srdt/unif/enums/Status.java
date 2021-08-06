package in.co.srdt.unif.enums;

public enum Status {

	Active("Active"),
	Inactive("Inactive");
	
	private String description;

	private Status(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	
	
}
