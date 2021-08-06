package in.co.srdt.unif.model.personmanagement;


public class DisablityDetails {

	private long actionid;

	private long disablityid;

	private String personid;

	private String personnumber;

	private String disable;

	
	private String asofdatedisablity;

	private String typeofdisablity;

	private float percentageofdisablity;

	private String effectivestartdate;

	private String effectiveenddate;
	
	private String createdby;

	private String updatedby;

	public DisablityDetails() {
	}

	public DisablityDetails(long actionid, long disablityid, String personid, String personnumber, String disable,
			String asofdatedisablity, String typeofdisablity, float percentageofdisablity, String effectivestartdate,
			String effectiveenddate, String createdby, String updatedby) {
		super();
		this.actionid = actionid;
		this.disablityid = disablityid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.disable = disable;
		this.asofdatedisablity = asofdatedisablity;
		this.typeofdisablity = typeofdisablity;
		this.percentageofdisablity = percentageofdisablity;
		this.effectivestartdate = effectivestartdate;
		this.effectiveenddate = effectiveenddate;
		this.createdby = createdby;
		this.updatedby = updatedby;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getDisablityid() {
		return disablityid;
	}

	public void setDisablityid(long disablityid) {
		this.disablityid = disablityid;
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

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String getAsofdatedisablity() {
		return asofdatedisablity;
	}

	public void setAsofdatedisablity(String asofdatedisablity) {
		this.asofdatedisablity = asofdatedisablity;
	}

	public String getTypeofdisablity() {
		return typeofdisablity;
	}

	public void setTypeofdisablity(String typeofdisablity) {
		this.typeofdisablity = typeofdisablity;
	}

	public float getPercentageofdisablity() {
		return percentageofdisablity;
	}

	public void setPercentageofdisablity(float percentageofdisablity) {
		this.percentageofdisablity = percentageofdisablity;
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

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@Override
	public String toString() {
		return "DisablityDetails [actionid=" + actionid + ", disablityid=" + disablityid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", disable=" + disable + ", asofdatedisablity=" + asofdatedisablity
				+ ", typeofdisablity=" + typeofdisablity + ", percentageofdisablity=" + percentageofdisablity
				+ ", effectivestartdate=" + effectivestartdate + ", effectiveenddate=" + effectiveenddate
				+ ", createdby=" + createdby + ", updatedby=" + updatedby + "]";
	}

	
}
