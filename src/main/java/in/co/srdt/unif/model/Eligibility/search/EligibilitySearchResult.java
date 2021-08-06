package in.co.srdt.unif.model.Eligibility.search;

public class EligibilitySearchResult {

	private String eligibilityid;

	private String eligibilityname;
	
	private String eligibilitydescription;

	
	public EligibilitySearchResult() {
		
	}


	public EligibilitySearchResult(String eligibilityid, String eligibilityname, String eligibilitydescription) {
		super();
		this.eligibilityid = eligibilityid;
		this.eligibilityname = eligibilityname;
		this.eligibilitydescription = eligibilitydescription;
	}


	public String getEligibilityid() {
		return eligibilityid;
	}


	public void setEligibilityid(String eligibilityid) {
		this.eligibilityid = eligibilityid;
	}


	public String getEligibilityname() {
		return eligibilityname;
	}


	public void setEligibilityname(String eligibilityname) {
		this.eligibilityname = eligibilityname;
	}


	public String getEligibilitydescription() {
		return eligibilitydescription;
	}


	public void setEligibilitydescription(String eligibilitydescription) {
		this.eligibilitydescription = eligibilitydescription;
	}

	
	

	
}
