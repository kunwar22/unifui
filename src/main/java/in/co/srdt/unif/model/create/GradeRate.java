package in.co.srdt.unif.model.create;

import java.util.List;

public class GradeRate {
	
	private long actionid;

	
	private long graderateid;

	private String name; 
	
	private String effectstartdate;

	private String effectenddate;

	
	private long ratetype;

	private String ratevom;


	private long ratefrequency;

	private float annualizationfactor;

	private long currancycode;
	
	private String status;

	private List<GradeRateValue> graderatevalue;
	
	
	
	private long rateid;

	private String ratetypename;

	private String ratefrequencyname;

	private String currancycodename;

	private String additionalatr;	


	public GradeRate() {
	}


	public GradeRate(long actionid, long graderateid, String name, String effectstartdate, String effectenddate,
			long ratetype, String ratevom, long ratefrequency, float annualizationfactor, long currancycode,
			String status, List<GradeRateValue> graderatevalue, long rateid, String ratetypename,
			String ratefrequencyname, String currancycodename, String additionalatr) {
		super();
		this.actionid = actionid;
		this.graderateid = graderateid;
		this.name = name;
		this.effectstartdate = effectstartdate;
		this.effectenddate = effectenddate;
		this.ratetype = ratetype;
		this.ratevom = ratevom;
		this.ratefrequency = ratefrequency;
		this.annualizationfactor = annualizationfactor;
		this.currancycode = currancycode;
		this.status = status;
		this.graderatevalue = graderatevalue;
		this.rateid = rateid;
		this.ratetypename = ratetypename;
		this.ratefrequencyname = ratefrequencyname;
		this.currancycodename = currancycodename;
		this.additionalatr = additionalatr;
	}


	@Override
	public String toString() {
		return "GradeRate [actionid=" + actionid + ", graderateid=" + graderateid + ", name=" + name
				+ ", effectstartdate=" + effectstartdate + ", effectenddate=" + effectenddate + ", ratetype=" + ratetype
				+ ", ratevom=" + ratevom + ", ratefrequency=" + ratefrequency + ", annualizationfactor="
				+ annualizationfactor + ", currancycode=" + currancycode + ", status=" + status + ", graderatevalue="
				+ graderatevalue + ", rateid=" + rateid + ", ratetypename=" + ratetypename + ", ratefrequencyname="
				+ ratefrequencyname + ", currancycodename=" + currancycodename + ", additionalatr=" + additionalatr
				+ "]";
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


	public String getEffectenddate() {
		return effectenddate;
	}


	public void setEffectenddate(String effectenddate) {
		this.effectenddate = effectenddate;
	}


	public long getRatetype() {
		return ratetype;
	}


	public void setRatetype(long ratetype) {
		this.ratetype = ratetype;
	}


	public String getRatevom() {
		return ratevom;
	}


	public void setRatevom(String ratevom) {
		this.ratevom = ratevom;
	}


	public long getRatefrequency() {
		return ratefrequency;
	}


	public void setRatefrequency(long ratefrequency) {
		this.ratefrequency = ratefrequency;
	}


	public float getAnnualizationfactor() {
		return annualizationfactor;
	}


	public void setAnnualizationfactor(float annualizationfactor) {
		this.annualizationfactor = annualizationfactor;
	}


	public long getCurrancycode() {
		return currancycode;
	}


	public void setCurrancycode(long currancycode) {
		this.currancycode = currancycode;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public List<GradeRateValue> getGraderatevalue() {
		return graderatevalue;
	}


	public void setGraderatevalue(List<GradeRateValue> graderatevalue) {
		this.graderatevalue = graderatevalue;
	}


	public long getRateid() {
		return rateid;
	}


	public void setRateid(long rateid) {
		this.rateid = rateid;
	}


	public String getRatetypename() {
		return ratetypename;
	}


	public void setRatetypename(String ratetypename) {
		this.ratetypename = ratetypename;
	}


	public String getRatefrequencyname() {
		return ratefrequencyname;
	}


	public void setRatefrequencyname(String ratefrequencyname) {
		this.ratefrequencyname = ratefrequencyname;
	}


	public String getCurrancycodename() {
		return currancycodename;
	}


	public void setCurrancycodename(String currancycodename) {
		this.currancycodename = currancycodename;
	}


	public String getAdditionalatr() {
		return additionalatr;
	}


	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}

	

}
