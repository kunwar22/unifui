package in.co.srdt.unif.enums;

public enum SalaryType {

	None("None"),
	CTC("CTC"),
	Basic("Basic");
	
	private String description;

	private SalaryType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


	
}
