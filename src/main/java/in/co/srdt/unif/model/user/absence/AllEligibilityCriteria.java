package in.co.srdt.unif.model.user.absence;

public class AllEligibilityCriteria {

	
	private String eligibilitycriterianame;
	private String criteriavalueid;
	
	public AllEligibilityCriteria() {
		
	}

	public AllEligibilityCriteria(String eligibilitycriterianame, String criteriavalueid) {
		super();
		this.eligibilitycriterianame = eligibilitycriterianame;
		this.criteriavalueid = criteriavalueid;
	}

	public String getEligibilitycriterianame() {
		return eligibilitycriterianame;
	}

	public void setEligibilitycriterianame(String eligibilitycriterianame) {
		this.eligibilitycriterianame = eligibilitycriterianame;
	}

	public String getCriteriavalueid() {
		return criteriavalueid;
	}

	public void setCriteriavalueid(String criteriavalueid) {
		this.criteriavalueid = criteriavalueid;
	}

	@Override
	public String toString() {
		return "AllEligibilityCriteria [eligibilitycriterianame=" + eligibilitycriterianame + ", criteriavalueid="
				+ criteriavalueid + "]";
	}

	
}
