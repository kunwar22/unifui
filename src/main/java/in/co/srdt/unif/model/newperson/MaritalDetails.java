package in.co.srdt.unif.model.newperson;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;

public class MaritalDetails {

	private long actionid;

	private long maritalid;

	private String personid;

	private String personnumber;
	
	private String effectiveenddate="4712-12-31";

	@Min(value = 1, message = "Please select marital status.")
	private long maritalstatus;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String merriagedate;

	public MaritalDetails() {
		super();
	}

	public MaritalDetails(long actionid, long maritalid, String personid, String personnumber, String effectiveenddate,
			@Min(value = 1, message = "Please select marital Status.") long maritalstatus, String merriagedate) {
		super();
		this.actionid = actionid;
		this.maritalid = maritalid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.effectiveenddate = effectiveenddate;
		this.maritalstatus = maritalstatus;
		this.merriagedate = merriagedate;
	}

	@Override
	public String toString() {
		return "MaritalDetails [actionid=" + actionid + ", maritalid=" + maritalid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", effectiveenddate=" + effectiveenddate + ", maritalstatus="
				+ maritalstatus + ", merriagedate=" + merriagedate + "]";
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

	public String getEffectiveenddate() {
		return effectiveenddate;
	}

	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}

	public long getMaritalstatus() {
		return maritalstatus;
	}

	public void setMaritalstatus(long maritalstatus) {
		this.maritalstatus = maritalstatus;
	}

	public String getMerriagedate() {
		return merriagedate;
	}

	public void setMerriagedate(String merriagedate) {
		this.merriagedate = merriagedate;
	}

	

	
}
