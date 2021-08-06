package in.co.srdt.unif.enums;

public enum Gender {
	
	Male("Male"),
	Female("Female"),
	Transgender("Transgender");
	
	private String description;

	private Gender(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


}
