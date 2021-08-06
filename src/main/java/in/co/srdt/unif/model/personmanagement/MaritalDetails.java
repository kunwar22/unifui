package in.co.srdt.unif.model.personmanagement;

public class MaritalDetails {

	private long actionid;

	private long maritalid;

	private String personid;

	private String personnumber;

	private long maritalstatus;

	private String maritalstatusdsc;
	
	private String marriagedate;

	private String effectivestartdate;

	private String effectiveenddate="4712-12-31";
	
	private String createdby;

	private String updatedby;

	public MaritalDetails() {
	}

	public MaritalDetails(long actionid, long maritalid, String personid, String personnumber, long maritalstatus,
			String maritalstatusdsc, String marriagedate, String effectivestartdate, String effectiveenddate,
			String createdby, String updatedby) {
		super();
		this.actionid = actionid;
		this.maritalid = maritalid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.maritalstatus = maritalstatus;
		this.maritalstatusdsc = maritalstatusdsc;
		this.marriagedate = marriagedate;
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

	public long getMaritalid() {
		return maritalid;
	}

	public void setMaritalid(long maritalid) {
		this.maritalid = maritalid;
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

	public long getMaritalstatus() {
		return maritalstatus;
	}

	public void setMaritalstatus(long maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getMaritalstatusdsc() {
		return maritalstatusdsc;
	}

	public void setMaritalstatusdsc(String maritalstatusdsc) {
		this.maritalstatusdsc = maritalstatusdsc;
	}

	public String getMarriagedate() {
		return marriagedate;
	}

	public void setMarriagedate(String marriagedate) {
		this.marriagedate = marriagedate;
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
		return "MaritalDetails [actionid=" + actionid + ", maritalid=" + maritalid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", maritalstatus=" + maritalstatus + ", maritalstatusdsc="
				+ maritalstatusdsc + ", marriagedate=" + marriagedate + ", effectivestartdate=" + effectivestartdate
				+ ", effectiveenddate=" + effectiveenddate +  ", createdby=" + createdby
				+ ", updatedby=" + updatedby + "]";
	}

}
