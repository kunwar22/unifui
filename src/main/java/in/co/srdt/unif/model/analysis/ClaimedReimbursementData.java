package in.co.srdt.unif.model.analysis;

public class ClaimedReimbursementData {
	private String moduleid;
	private String reimbursetype;
	private String pendingcount;
	private String approvedcount;
	private String rejectedcount;
	private String avgcount;
	
	public ClaimedReimbursementData() {
		super();
	}

	public ClaimedReimbursementData(String moduleid, String reimbursetype, String pendingcount, String approvedcount,
			String rejectedcount, String avgcount) {
		super();
		this.moduleid = moduleid;
		this.reimbursetype = reimbursetype;
		this.pendingcount = pendingcount;
		this.approvedcount = approvedcount;
		this.rejectedcount = rejectedcount;
		this.avgcount = avgcount;
	}

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getReimbursetype() {
		return reimbursetype;
	}

	public void setReimbursetype(String reimbursetype) {
		this.reimbursetype = reimbursetype;
	}

	public String getPendingcount() {
		return pendingcount;
	}

	public void setPendingcount(String pendingcount) {
		this.pendingcount = pendingcount;
	}

	public String getApprovedcount() {
		return approvedcount;
	}

	public void setApprovedcount(String approvedcount) {
		this.approvedcount = approvedcount;
	}

	public String getRejectedcount() {
		return rejectedcount;
	}

	public void setRejectedcount(String rejectedcount) {
		this.rejectedcount = rejectedcount;
	}

	public String getAvgcount() {
		return avgcount;
	}

	public void setAvgcount(String avgcount) {
		this.avgcount = avgcount;
	}

	@Override
	public String toString() {
		return "ClaimedReimbursementData [moduleid=" + moduleid + ", reimbursetype=" + reimbursetype + ", pendingcount="
				+ pendingcount + ", approvedcount=" + approvedcount + ", rejectedcount=" + rejectedcount + ", avgcount="
				+ avgcount + "]";
	}
		
}
