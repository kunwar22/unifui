package in.co.srdt.unif.model.rembentertainment;

public class EntertainmentSearchModel {
	
	private String comment;
	private String status;
	private long reimburseId;
	private String reimburseName;
	private String personNumber;
	private String personId;
	private long claimid;
	private String attachment;
	private long approvedAmt;
	private String financialYear;
	private String periodFrom;
	private String periodTo;
	private String amount;
	private String createdDate;
	private String createdBy;
	private String modifiedBy;
	private String lastUpdatedDate;
	private String isActive;
	    
	    
	public EntertainmentSearchModel() {
		
	}


	public EntertainmentSearchModel(String comment, String status, long reimburseId, String reimburseName,
			String personNumber, String personId, long claimid, String attachment, long approvedAmt,
			String financialYear, String periodFrom, String periodTo, String amount, String createdDate,
			String createdBy, String modifiedBy, String lastUpdatedDate, String isActive) {
		super();
		this.comment = comment;
		this.status = status;
		this.reimburseId = reimburseId;
		this.reimburseName = reimburseName;
		this.personNumber = personNumber;
		this.personId = personId;
		this.claimid = claimid;
		this.attachment = attachment;
		this.approvedAmt = approvedAmt;
		this.financialYear = financialYear;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.amount = amount;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.lastUpdatedDate = lastUpdatedDate;
		this.isActive = isActive;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public long getReimburseId() {
		return reimburseId;
	}


	public void setReimburseId(long reimburseId) {
		this.reimburseId = reimburseId;
	}


	public String getReimburseName() {
		return reimburseName;
	}


	public void setReimburseName(String reimburseName) {
		this.reimburseName = reimburseName;
	}


	public String getPersonNumber() {
		return personNumber;
	}


	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}


	public String getPersonId() {
		return personId;
	}


	public void setPersonId(String personId) {
		this.personId = personId;
	}


	public long getClaimid() {
		return claimid;
	}


	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}


	public String getAttachment() {
		return attachment;
	}


	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}


	public long getApprovedAmt() {
		return approvedAmt;
	}


	public void setApprovedAmt(long approvedAmt) {
		this.approvedAmt = approvedAmt;
	}


	public String getFinancialYear() {
		return financialYear;
	}


	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}


	public String getPeriodFrom() {
		return periodFrom;
	}


	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}


	public String getPeriodTo() {
		return periodTo;
	}


	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}


	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	@Override
	public String toString() {
		return "EntertainmentSearchModel [comment=" + comment + ", status=" + status + ", reimburseId=" + reimburseId
				+ ", reimburseName=" + reimburseName + ", personNumber=" + personNumber + ", personId=" + personId
				+ ", claimid=" + claimid + ", attachment=" + attachment + ", approvedAmt=" + approvedAmt
				+ ", financialYear=" + financialYear + ", periodFrom=" + periodFrom + ", periodTo=" + periodTo
				+ ", amount=" + amount + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", lastUpdatedDate=" + lastUpdatedDate + ", isActive=" + isActive + "]";
	}
	
}
