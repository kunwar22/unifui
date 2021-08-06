package in.co.srdt.unif.model.taxdeclaration.interesthomeloan;

public class PayTaxIncLossSourceShow {

	private long inclodID;
	private String description;
	private double yearLimit;
	private String status;
	private String personNumber;
	private double decAmount;
	private double verAmount;
	private String remarks;
	private String attachments;	
	private String losdecription;
	
	public PayTaxIncLossSourceShow() {
		super();
	}
	
	public PayTaxIncLossSourceShow(long inclodID, String description, double yearLimit, String status,
			String personNumber, double decAmount, double verAmount, String remarks, String attachments,
			String losdecription) {
		super();
		this.inclodID = inclodID;
		this.description = description;
		this.yearLimit = yearLimit;
		this.status = status;
		this.personNumber = personNumber;
		this.decAmount = decAmount;
		this.verAmount = verAmount;
		this.remarks = remarks;
		this.attachments = attachments;
		this.losdecription = losdecription;
	}

	public long getInclodID() {
		return inclodID;
	}

	public void setInclodID(long inclodID) {
		this.inclodID = inclodID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getYearLimit() {
		return yearLimit;
	}

	public void setYearLimit(double yearLimit) {
		this.yearLimit = yearLimit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public double getDecAmount() {
		return decAmount;
	}

	public void setDecAmount(double decAmount) {
		this.decAmount = decAmount;
	}

	public double getVerAmount() {
		return verAmount;
	}

	public void setVerAmount(double verAmount) {
		this.verAmount = verAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public String getLosdecription() {
		return losdecription;
	}

	public void setLosdecription(String losdecription) {
		this.losdecription = losdecription;
	}

	@Override
	public String toString() {
		return "PayTaxIncLossSourceShow [inclodID=" + inclodID + ", description=" + description + ", yearLimit="
				+ yearLimit + ", status=" + status + ", personNumber=" + personNumber + ", decAmount=" + decAmount
				+ ", verAmount=" + verAmount + ", remarks=" + remarks + ", attachments=" + attachments
				+ ", losdecription=" + losdecription + "]";
	}
	
	
	
}
