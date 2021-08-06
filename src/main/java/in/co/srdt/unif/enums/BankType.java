package in.co.srdt.unif.enums;

public enum BankType {

	HDFC("HDFC"), 
	NonHDFC("Non HDFC");

	private String description;

	private BankType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
