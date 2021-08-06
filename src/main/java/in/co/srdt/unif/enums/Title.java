package in.co.srdt.unif.enums;

public enum Title {
	
	Doctor("Doctor"),
	Miss("Miss"),
	Mr("Mr."),
	Mrs("Mrs."),
	Ms("Ms.");
	
	private String description;
	
	private Title(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
