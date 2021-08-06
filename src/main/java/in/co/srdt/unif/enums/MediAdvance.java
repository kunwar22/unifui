package in.co.srdt.unif.enums;

public enum MediAdvance {
	Self("Self"),
	Hospital("Hospital");
	
	
	private String description;

	private MediAdvance(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
