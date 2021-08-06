package in.co.srdt.unif.model.eligibilityreimb;

public class AllCeilingLimits {

	private long eligibilityid;	
	private String reimbmaxceilinglimit;	
	private long reimbursementid;
	
	public AllCeilingLimits() {
		
	}

	public AllCeilingLimits(long eligibilityid, String reimbmaxceilinglimit, long reimbursementid) {
		super();
		this.eligibilityid = eligibilityid;
		this.reimbmaxceilinglimit = reimbmaxceilinglimit;
		this.reimbursementid = reimbursementid;
	}

	public long getEligibilityid() {
		return eligibilityid;
	}

	public void setEligibilityid(long eligibilityid) {
		this.eligibilityid = eligibilityid;
	}

	public String getReimbmaxceilinglimit() {
		return reimbmaxceilinglimit;
	}

	public void setReimbmaxceilinglimit(String reimbmaxceilinglimit) {
		this.reimbmaxceilinglimit = reimbmaxceilinglimit;
	}

	public long getReimbursementid() {
		return reimbursementid;
	}

	public void setReimbursementid(long reimbursementid) {
		this.reimbursementid = reimbursementid;
	}

	@Override
	public String toString() {
		return "AllCeilingLimits [eligibilityid=" + eligibilityid + ", reimbmaxceilinglimit=" + reimbmaxceilinglimit
				+ ", reimbursementid=" + reimbursementid + "]";
	}
		
}
