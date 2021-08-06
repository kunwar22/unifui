package in.co.srdt.unif.enums;

public enum Days {

	Sunday("Sunday"),
	Monday("Monday"),
	Tuesday("Tuesday"),
	Wednesday("Wednesday"),
	Thursday("Thursday"),
	Friday("Friday"),
	Saturday("Saturday");
	
	private String description;

	private Days(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	
	
}
