package in.co.srdt.unif.enums;

public enum Vehiclelov {

	UPMRC("UPMRC"),
	Self("Self");
	
	private String description;

	private Vehiclelov(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	
	
}
