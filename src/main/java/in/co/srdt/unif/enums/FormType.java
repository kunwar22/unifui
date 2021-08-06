package in.co.srdt.unif.enums;

public enum FormType {

	PartA("Part A"),
	PartB("Part B");
	
	private String description;

	private FormType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
