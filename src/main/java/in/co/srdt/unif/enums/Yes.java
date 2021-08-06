package in.co.srdt.unif.enums;

public enum Yes {

	Yes("Yes");
	
	
	private String description;

	private Yes(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	
}
