package in.co.srdt.unif.model.taxdeclaration.previousemplincome;

public class PreviousEmployementModel {
	
	private long emplmid;
	private String personNumber;
	private String years;
	private double d_amt_prv_inc;
	private double v_amt_prv_inc;
	private double d_amt_prv_tax;
	private double v_amt_prv_tax;
	private long status;
	private String status_descr;
	private String remark;
	private String attachment;
	
	public PreviousEmployementModel() {
		super();
	}

	public PreviousEmployementModel(long emplmid, String personNumber, String years, double d_amt_prv_inc,
			double v_amt_prv_inc, double d_amt_prv_tax, double v_amt_prv_tax, long status, String status_descr,
			String remark, String attachment) {
		super();
		this.emplmid = emplmid;
		this.personNumber = personNumber;
		this.years = years;
		this.d_amt_prv_inc = d_amt_prv_inc;
		this.v_amt_prv_inc = v_amt_prv_inc;
		this.d_amt_prv_tax = d_amt_prv_tax;
		this.v_amt_prv_tax = v_amt_prv_tax;
		this.status = status;
		this.status_descr = status_descr;
		this.remark = remark;
		this.attachment = attachment;
	}

	public long getEmplmid() {
		return emplmid;
	}

	public void setEmplmid(long emplmid) {
		this.emplmid = emplmid;
	}

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public double getD_amt_prv_inc() {
		return d_amt_prv_inc;
	}

	public void setD_amt_prv_inc(double d_amt_prv_inc) {
		this.d_amt_prv_inc = d_amt_prv_inc;
	}

	public double getV_amt_prv_inc() {
		return v_amt_prv_inc;
	}

	public void setV_amt_prv_inc(double v_amt_prv_inc) {
		this.v_amt_prv_inc = v_amt_prv_inc;
	}

	public double getD_amt_prv_tax() {
		return d_amt_prv_tax;
	}

	public void setD_amt_prv_tax(double d_amt_prv_tax) {
		this.d_amt_prv_tax = d_amt_prv_tax;
	}

	public double getV_amt_prv_tax() {
		return v_amt_prv_tax;
	}

	public void setV_amt_prv_tax(double v_amt_prv_tax) {
		this.v_amt_prv_tax = v_amt_prv_tax;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getStatus_descr() {
		return status_descr;
	}

	public void setStatus_descr(String status_descr) {
		this.status_descr = status_descr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@Override
	public String toString() {
		return "PreviousEmployementModel [emplmid=" + emplmid + ", personNumber=" + personNumber + ", years=" + years
				+ ", d_amt_prv_inc=" + d_amt_prv_inc + ", v_amt_prv_inc=" + v_amt_prv_inc + ", d_amt_prv_tax="
				+ d_amt_prv_tax + ", v_amt_prv_tax=" + v_amt_prv_tax + ", status=" + status + ", status_descr="
				+ status_descr + ", remark=" + remark + ", attachment=" + attachment + "]";
	}


}
