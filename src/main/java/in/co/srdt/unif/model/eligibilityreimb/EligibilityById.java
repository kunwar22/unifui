package in.co.srdt.unif.model.eligibilityreimb;

public class EligibilityById {
	private String criteriavalueid;
	private String eligibilitycriterianame;
	
	public EligibilityById(String criteriavalueid, String eligibilitycriterianame) {
		super();
		this.criteriavalueid = criteriavalueid;
		this.eligibilitycriterianame = eligibilitycriterianame;
	}

	public EligibilityById() {
		
	}

	public String getCriteriavalueid() {
		return criteriavalueid;
	}

	public void setCriteriavalueid(String criteriavalueid) {
		this.criteriavalueid = criteriavalueid;
	}

	public String getEligibilitycriterianame() {
		return eligibilitycriterianame;
	}

	public void setEligibilitycriterianame(String eligibilitycriterianame) {
		this.eligibilitycriterianame = eligibilitycriterianame;
	}

	@Override
	public String toString() {
		return "EligibilityById [criteriavalueid=" + criteriavalueid + ", eligibilitycriterianame="
				+ eligibilitycriterianame + "]";
	}
	
}
