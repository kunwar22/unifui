package in.co.srdt.unif.model.rembentertainment;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class EntertainmentReimbursementmodel {
	
	
	private long claimid;
	@Min(value = 1, message = "Please fill Amount.")
	private double amount;
	@NotBlank(message= "Please select From Date.")
	private String periodfrom;
	@NotBlank(message= "Please select To Date.")
	private String periodto;
	private String status;
	private String financialyear;
	private String personid;
	private String personnumber;
	private long reimburseid;
	private String reimbursename="Entertainment";
	private long approvedamt;
	private String comment;
	private String createdat;
	private String attachment;
	private String attachhidden;
	public EntertainmentReimbursementmodel() {
	
	}
	public long getClaimid() {
		return claimid;
	}
	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPeriodfrom() {
		return periodfrom;
	}
	public void setPeriodfrom(String periodfrom) {
		this.periodfrom = periodfrom;
	}
	public String getPeriodto() {
		return periodto;
	}
	public void setPeriodto(String periodto) {
		this.periodto = periodto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFinancialyear() {
		return financialyear;
	}
	public void setFinancialyear(String financialyear) {
		this.financialyear = financialyear;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getPersonnumber() {
		return personnumber;
	}
	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}
	public long getReimburseid() {
		return reimburseid;
	}
	public void setReimburseid(long reimburseid) {
		this.reimburseid = reimburseid;
	}
	public String getReimbursename() {
		return reimbursename;
	}
	public void setReimbursename(String reimbursename) {
		this.reimbursename = reimbursename;
	}
	public long getApprovedamt() {
		return approvedamt;
	}
	public void setApprovedamt(long approvedamt) {
		this.approvedamt = approvedamt;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreatedat() {
		return createdat;
	}
	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachhidden() {
		return attachhidden;
	}

	public void setAttachhidden(String attachhidden) {
		this.attachhidden = attachhidden;
	}

	@Override
	public String toString() {
		return "EntertainmentReimbursementmodel{" +
				"claimid=" + claimid +
				", amount=" + amount +
				", periodfrom='" + periodfrom + '\'' +
				", periodto='" + periodto + '\'' +
				", status='" + status + '\'' +
				", financialyear='" + financialyear + '\'' +
				", personid='" + personid + '\'' +
				", personnumber='" + personnumber + '\'' +
				", reimburseid=" + reimburseid +
				", reimbursename='" + reimbursename + '\'' +
				", approvedamt=" + approvedamt +
				", comment='" + comment + '\'' +
				", createdat='" + createdat + '\'' +
				", attachment='" + attachment + '\'' +
				", attachhidden='" + attachhidden + '\'' +
				'}';
	}

	
}
