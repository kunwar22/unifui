package in.co.srdt.unif.enums;

public enum MaritalStatus {
	
	Common_Law("Common-Law"),
	Divorced("Divorced"),
	Legally_Separated("Legally_Separated"),
	Married("Married"),
	Others("Others"),
	Single("Single"),
	Widowed("Widowed");
	
	private String description;

	private MaritalStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


}
