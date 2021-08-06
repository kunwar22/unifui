package in.co.srdt.unif.model.taxdeclaration.hra;

import java.util.Date;

public class HraRentDeclaration  {
	
	private long rentId;	
	private String personNumber;
	private int calender_period;
	private String month;
	private String taxyear;
	private String eff_startdt;
	private String eff_enddt;
	private double d_amt;
	private double v_amt;
	private String status;
	private String remarks;
	private String attachment;
	private String createdby;
	private String modifiedby;
	private String createddate;
	private String lastupdateddate;
	
	public HraRentDeclaration() {
	}

	public HraRentDeclaration(long rentId, String personNumber, int calender_period, String month, String taxyear, String eff_startdt, String eff_enddt, double d_amt, double v_amt, String status, String remarks, String attachment, String createdby, String modifiedby, String createddate, String lastupdateddate) {
		this.rentId = rentId;
		this.personNumber = personNumber;
		this.calender_period = calender_period;
		this.month = month;
		this.taxyear = taxyear;
		this.eff_startdt = eff_startdt;
		this.eff_enddt = eff_enddt;
		this.d_amt = d_amt;
		this.v_amt = v_amt;
		this.status = status;
		this.remarks = remarks;
		this.attachment = attachment;
		this.createdby = createdby;
		this.modifiedby = modifiedby;
		this.createddate = createddate;
		this.lastupdateddate = lastupdateddate;
	}

	public long getRentId() {
		return rentId;
	}

	public void setRentId(long rentId) {
		this.rentId = rentId;
	}

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public int getCalender_period() {
		return calender_period;
	}

	public void setCalender_period(int calender_period) {
		this.calender_period = calender_period;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getTaxyear() {
		return taxyear;
	}

	public void setTaxyear(String taxyear) {
		this.taxyear = taxyear;
	}

	public String getEff_startdt() {
		return eff_startdt;
	}

	public void setEff_startdt(String eff_startdt) {
		this.eff_startdt = eff_startdt;
	}

	public String getEff_enddt() {
		return eff_enddt;
	}

	public void setEff_enddt(String eff_enddt) {
		this.eff_enddt = eff_enddt;
	}

	public double getD_amt() {
		return d_amt;
	}

	public void setD_amt(double d_amt) {
		this.d_amt = d_amt;
	}

	public double getV_amt() {
		return v_amt;
	}

	public void setV_amt(double v_amt) {
		this.v_amt = v_amt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getLastupdateddate() {
		return lastupdateddate;
	}

	public void setLastupdateddate(String lastupdateddate) {
		this.lastupdateddate = lastupdateddate;
	}

	@Override
	public String toString() {
		return "HraRentDeclaration{" +
				"rentId=" + rentId +
				", personNumber='" + personNumber + '\'' +
				", calender_period=" + calender_period +
				", month='" + month + '\'' +
				", taxyear='" + taxyear + '\'' +
				", eff_startdt=" + eff_startdt +
				", eff_enddt=" + eff_enddt +
				", d_amt=" + d_amt +
				", v_amt=" + v_amt +
				", status='" + status + '\'' +
				", remarks='" + remarks + '\'' +
				", attachment='" + attachment + '\'' +
				", createdby='" + createdby + '\'' +
				", modifiedby='" + modifiedby + '\'' +
				", createddate='" + createddate + '\'' +
				", lastupdateddate='" + lastupdateddate + '\'' +
				'}';
	}
}
