package in.co.srdt.unif.enums;

public enum NationalIdType {
	

	Aadhaar_Number("Aadhaar_Number"),
	National_Identifier("National_Identifier"),
	Permanent_Account_Number("Permanent_Account_Number");
	
	private String description;

	private NationalIdType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


}
