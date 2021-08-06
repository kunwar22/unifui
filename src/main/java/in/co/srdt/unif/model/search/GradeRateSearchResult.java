package in.co.srdt.unif.model.search;

public class GradeRateSearchResult {

	private long actionid;

	private long graderateid;

	private String name;

	private String effectstartdate;

	private String ratetype;

	private String ratefrequency;

	private String currancycode;

	private String status;

	public GradeRateSearchResult() {
	}

	public GradeRateSearchResult(long actionid, long graderateid, String name, String effectstartdate, String ratetype,
			String ratefrequency, String currancycode, String status) {
		super();
		this.actionid = actionid;
		this.graderateid = graderateid;
		this.name = name;
		this.effectstartdate = effectstartdate;
		this.ratetype = ratetype;
		this.ratefrequency = ratefrequency;
		this.currancycode = currancycode;
		this.status = status;
	}

	@Override
	public String toString() {
		return "GradeRateSearchResult [actionid=" + actionid + ", graderateid=" + graderateid + ", name=" + name
				+ ", effectstartdate=" + effectstartdate + ", ratetype=" + ratetype + ", ratefrequency=" + ratefrequency
				+ ", currancycode=" + currancycode + ", status=" + status + "]";
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getGraderateid() {
		return graderateid;
	}

	public void setGraderateid(long graderateid) {
		this.graderateid = graderateid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEffectstartdate() {
		return effectstartdate;
	}

	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}

	public String getRatetype() {
		return ratetype;
	}

	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}

	public String getRatefrequency() {
		return ratefrequency;
	}

	public void setRatefrequency(String ratefrequency) {
		this.ratefrequency = ratefrequency;
	}

	public String getCurrancycode() {
		return currancycode;
	}

	public void setCurrancycode(String currancycode) {
		this.currancycode = currancycode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
