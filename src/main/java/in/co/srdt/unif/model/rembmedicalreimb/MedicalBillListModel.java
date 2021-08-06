package in.co.srdt.unif.model.rembmedicalreimb;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class MedicalBillListModel {
	
	private long billid;
	
	@NotBlank(message = "Enter Bill Description")
	private String billdescription;
	
	@NotBlank(message = "Enter Bill Number")
	private String billnumber;
	
	@NotBlank(message = "Enter Bill Date")
	private String billDate;
	
	@Min(value=1, message = "Enter Bill Amount")
	private double billamount;
	
	//@NotBlank(message = "Enter Remark")
	private String remark;
	private long claimid;
	
	public MedicalBillListModel() {
		super();
	}

	public MedicalBillListModel(long billid, @NotBlank(message = "Enter Bill Description") String billdescription,
			@NotBlank(message = "Enter Bill Number") String billnumber,
			@NotBlank(message = "Enter Bill Date") String billDate,
			@Min(value = 1, message = "Enter Bill Description") double billamount, String remark, long claimid) {
		super();
		this.billid = billid;
		this.billdescription = billdescription;
		this.billnumber = billnumber;
		this.billDate = billDate;
		this.billamount = billamount;
		this.remark = remark;
		this.claimid = claimid;
	}

	public long getBillid() {
		return billid;
	}

	public void setBillid(long billid) {
		this.billid = billid;
	}

	public String getBilldescription() {
		return billdescription;
	}

	public void setBilldescription(String billdescription) {
		this.billdescription = billdescription;
	}

	public String getBillnumber() {
		return billnumber;
	}

	public void setBillnumber(String billnumber) {
		this.billnumber = billnumber;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public double getBillamount() {
		return billamount;
	}

	public void setBillamount(double billamount) {
		this.billamount = billamount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getClaimid() {
		return claimid;
	}

	public void setClaimid(long claimid) {
		this.claimid = claimid;
	}

	@Override
	public String toString() {
		return "MedicalBillListModel [billid=" + billid + ", billdescription=" + billdescription + ", billnumber="
				+ billnumber + ", billDate=" + billDate + ", billamount=" + billamount + ", remark=" + remark
				+ ", claimid=" + claimid + "]";
	}
	
}
