package in.co.srdt.unif.enums;

public enum Frequency {

	Monthly("Monthly"),
	Yearly("Yearly");
	
	private String description;

	private Frequency(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


	
}
