package in.co.srdt.unif.model.newperson;

public class ReligionDetails {

	private long actionid;

	private long religiondetailsid;

	private String personid;

	private String personnumber;

	private long religion;

	private String asofdate;
	
	private String effectiveenddate="4712-12-31";

	public ReligionDetails() {
	}

	public ReligionDetails(long actionid, long religiondetailsid, String personid, String personnumber, long religion,
			String asofdate, String effectiveenddate) {
		super();
		this.actionid = actionid;
		this.religiondetailsid = religiondetailsid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.religion = religion;
		this.asofdate = asofdate;
		this.effectiveenddate = effectiveenddate;
	}

	@Override
	public String toString() {
		return "ReligionDetails [actionid=" + actionid + ", religiondetailsid=" + religiondetailsid + ", personid="
				+ personid + ", personnumber=" + personnumber + ", religion=" + religion + ", asofdate=" + asofdate
				+ ", effectiveenddate=" + effectiveenddate + "]";
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

	public String getAsofdate() {
		return asofdate;
	}

	public void setAsofdate(String asofdate) {
		this.asofdate = asofdate;
	}

	public String getEffectiveenddate() {
		return effectiveenddate;
	}

	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}

	
}
