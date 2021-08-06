package in.co.srdt.unif.enums;

public enum Identifying {

	Yes("Yes"),
	No("No");
	
	private String description;

	private Identifying(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	
}
