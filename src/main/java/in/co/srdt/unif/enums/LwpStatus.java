package in.co.srdt.unif.enums;

public enum LwpStatus {

	Payout("Payout"), 
	Recovery("Recovery");

	private String description;

	private LwpStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
