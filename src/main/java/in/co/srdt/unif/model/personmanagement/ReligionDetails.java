package in.co.srdt.unif.model.personmanagement;

public class ReligionDetails {

	private long actionid;

	private long religiondetailsid;

	private String personid;

	private String personnumber;

	private long religion;

	private String religiondsc;

	private String asofdate;

	private String effectivestartdate;

	private String effectiveenddate;
	
	private String createdby;

	private String updatedby;

	public ReligionDetails() {
	}

	public ReligionDetails(long actionid, long religiondetailsid, String personid, String personnumber, long religion,
			String religiondsc, String asofdate, String effectivestartdate, String effectiveenddate, String createdby,
			String updatedby) {
		super();
		this.actionid = actionid;
		this.religiondetailsid = religiondetailsid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.religion = religion;
		this.religiondsc = religiondsc;
		this.asofdate = asofdate;
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

	public long getReligiondetailsid() {
		return religiondetailsid;
	}

	public void setReligiondetailsid(long religiondetailsid) {
		this.religiondetailsid = religiondetailsid;
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

	public long getReligion() {
		return religion;
	}

	public void setReligion(long religion) {
		this.religion = religion;
	}

	public String getReligiondsc() {
		return religiondsc;
	}

	public void setReligiondsc(String religiondsc) {
		this.religiondsc = religiondsc;
	}

	public String getAsofdate() {
		return asofdate;
	}

	public void setAsofdate(String asofdate) {
		this.asofdate = asofdate;
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
		return "ReligionDetails [actionid=" + actionid + ", religiondetailsid=" + religiondetailsid + ", personid="
				+ personid + ", personnumber=" + personnumber + ", religion=" + religion + ", religiondsc="
				+ religiondsc + ", asofdate=" + asofdate + ", effectivestartdate=" + effectivestartdate
				+ ", effectiveenddate=" + effectiveenddate + ", createdby=" + createdby + ", updatedby=" + updatedby
				+ "]";
	}

}
