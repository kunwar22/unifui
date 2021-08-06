package in.co.srdt.unif.model.reimbursementsetup;

import java.util.Date;

public class RembursementType {

	private long actionid;

	private long rembursementtypeid;

	private long rembursementnameid;
	
	private String rembursementname;

	private String effectivestartdate;

	private String effectiveenddate="4712-12-31";

	private String status;
	
	private String additionalatr;

	public RembursementType() {
	
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

	public long getRembursementnameid() {
		return rembursementnameid;
	}

	public void setRembursementnameid(long rembursementnameid) {
		this.rembursementnameid = rembursementnameid;
	}

	public String getRembursementname() {
		return rembursementname;
	}

	public void setRembursementname(String rembursementname) {
		this.rembursementname = rembursementname;
	}

	public String getEffectivestartdate() {
		return effectivestartdate;
	}

	public void setEffectivestartdate(String effectivestartdate) {
		this.effectivestartdate = effectivestartdate;
	}

	public String getEffectiveenddate() {
		return effectiveenddate;
	}

	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdditionalatr() {
		return additionalatr;
	}

	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}

	@Override
	public String toString() {
		return "RembursementType [actionid=" + actionid + ", rembursementtypeid=" + rembursementtypeid
				+ ", rembursementnameid=" + rembursementnameid + ", rembursementname=" + rembursementname
				+ ", effectivestartdate=" + effectivestartdate + ", effectiveenddate=" + effectiveenddate + ", status="
				+ status + ", additionalatr=" + additionalatr + "]";
	}
	

	
	
}
