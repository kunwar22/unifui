package in.co.srdt.unif.model.approval;

public class ApprovalSave {
	
	private long approvalId;
	private long approve1;
	private long approve2;
	private long approve3;
	private long approve4;
	private long approve5;
	private long approve6;
	private String category;
	private String modulename;
	private long reimburseType;
	
	public ApprovalSave() {
	}

	public ApprovalSave(long approvalId, long approve1, long approve2, long approve3, long approve4, long approve5,
			long approve6, String category, String modulename, long reimburseType) {
		super();
		this.approvalId = approvalId;
		this.approve1 = approve1;
		this.approve2 = approve2;
		this.approve3 = approve3;
		this.approve4 = approve4;
		this.approve5 = approve5;
		this.approve6 = approve6;
		this.category = category;
		this.modulename = modulename;
		this.reimburseType = reimburseType;
	}

	public long getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(long approvalId) {
		this.approvalId = approvalId;
	}

	public long getApprove1() {
		return approve1;
	}

	public void setApprove1(long approve1) {
		this.approve1 = approve1;
	}

	public long getApprove2() {
		return approve2;
	}

	public void setApprove2(long approve2) {
		this.approve2 = approve2;
	}

	public long getApprove3() {
		return approve3;
	}

	public void setApprove3(long approve3) {
		this.approve3 = approve3;
	}

	public long getApprove4() {
		return approve4;
	}

	public void setApprove4(long approve4) {
		this.approve4 = approve4;
	}

	public long getApprove5() {
		return approve5;
	}

	public void setApprove5(long approve5) {
		this.approve5 = approve5;
	}

	public long getApprove6() {
		return approve6;
	}

	public void setApprove6(long approve6) {
		this.approve6 = approve6;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public long getReimburseType() {
		return reimburseType;
	}

	public void setReimburseType(long reimburseType) {
		this.reimburseType = reimburseType;
	}

	@Override
	public String toString() {
		return "ApprovalSave [approvalId=" + approvalId + ", approve1=" + approve1 + ", approve2=" + approve2
				+ ", approve3=" + approve3 + ", approve4=" + approve4 + ", approve5=" + approve5 + ", approve6="
				+ approve6 + ", category=" + category + ", modulename=" + modulename + ", reimburseType="
				+ reimburseType + "]";
	}
		
}