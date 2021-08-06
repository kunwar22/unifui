package in.co.srdt.unif.model.newperson;
public class AlternateDetails {
	
	private String alternateId;
	private String employeeId; 
	private String thridPartySystemId;
	
	public AlternateDetails(String alternateId, String employeeId, String thridPartySystemId) {
		super();
		this.alternateId = alternateId;
		this.employeeId = employeeId;
		this.thridPartySystemId = thridPartySystemId;
	}

	public String getAlternateId() {
		return alternateId;
	}

	public void setAlternateId(String alternateId) {
		this.alternateId = alternateId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getThridPartySystemId() {
		return thridPartySystemId;
	}

	public void setThridPartySystemId(String thridPartySystemId) {
		this.thridPartySystemId = thridPartySystemId;
	}
	
	
}
