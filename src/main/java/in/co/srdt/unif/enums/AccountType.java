package in.co.srdt.unif.enums;

public enum AccountType {

	HDFC("HDFC"), 
	NON_HDFC("Non HDFC");

	private String description;

	private AccountType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
