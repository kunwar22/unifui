package in.co.srdt.unif.model.reimbursementsetup;

public class RembursementCeilingLimit {

	private long actionid;

	private long rembursementtypeid;

	private long eligibilityid;
	
	private String eligibilitydsc;

	private String cielingmin;

	private String cielingmax;

	public RembursementCeilingLimit() {
		
	}
	
	

	public String getEligibilitydsc() {
		return eligibilitydsc;
	}



	public void setEligibilitydsc(String eligibilitydsc) {
		this.eligibilitydsc = eligibilitydsc;
	}



	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getRembursementtypeid() {
		return rembursementtypeid;
	}

	public void setRembursementtypeid(long rembursementtypeid) {
		this.rembursementtypeid = rembursementtypeid;
	}

	public long getEligibilityid() {
		return eligibilityid;
	}

	public void setEligibilityid(long eligibilityid) {
		this.eligibilityid = eligibilityid;
	}

	public String getCielingmin() {
		return cielingmin;
	}

	public void setCielingmin(String cielingmin) {
		this.cielingmin = cielingmin;
	}

	public String getCielingmax() {
		return cielingmax;
	}

	public void setCielingmax(String cielingmax) {
		this.cielingmax = cielingmax;
	}



	@Override
	public String toString() {
		return "RembursementCeilingLimit [actionid=" + actionid + ", rembursementtypeid=" + rembursementtypeid
				+ ", eligibilityid=" + eligibilityid + ", eligibilitydsc=" + eligibilitydsc + ", cielingmin="
				+ cielingmin + ", cielingmax=" + cielingmax + "]";
	}

	
	
	
	
}
