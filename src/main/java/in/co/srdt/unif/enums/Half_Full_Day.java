package in.co.srdt.unif.enums;

public enum Half_Full_Day {

	Halfday("Yes"),
	Fullday("No");
	
	private String description;

	private Half_Full_Day(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	
	
}
