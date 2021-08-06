package in.co.srdt.unif.enums;

public enum Months {

	January("January"),
	February("February"),
	March("March"),
	April("April"),
	May("May"),
	June("June"),
	July("July"),
	August("August"),
	September("September"),
	October("October"),
	November("November"),
	December("December");
	
	private String description;

	private Months(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	

	
}
