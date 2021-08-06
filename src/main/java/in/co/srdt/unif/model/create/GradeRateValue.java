package in.co.srdt.unif.model.create;

public class GradeRateValue {
	
	private long actionid;

	private long graderateid;  

	private long gradesid;

	private String effectstartdate;

	private String effectenddate;

	private float min;

	private float max;

	private float midvalue;
	
	
	private long ratevalueid;

	private String gradesname;

	private String gradescode;

	
	private String additionalatr;

	public GradeRateValue() {
	}

	public GradeRateValue(long actionid, long graderateid, long gradesid, String effectstartdate, String effectenddate,
			float min, float max, float midvalue, long ratevalueid, String gradesname, String gradescode,
			String additionalatr) {
		super();
		this.actionid = actionid;
		this.graderateid = graderateid;
		this.gradesid = gradesid;
		this.effectstartdate = effectstartdate;
		this.effectenddate = effectenddate;
		this.min = min;
		this.max = max;
		this.midvalue = midvalue;
		this.ratevalueid = ratevalueid;
		this.gradesname = gradesname;
		this.gradescode = gradescode;
		this.additionalatr = additionalatr;
	}

	@Override
	public String toString() {
		return "GradeRateValue [actionid=" + actionid + ", graderateid=" + graderateid + ", gradesid=" + gradesid
				+ ", effectstartdate=" + effectstartdate + ", effectenddate=" + effectenddate + ", min=" + min
				+ ", max=" + max + ", midvalue=" + midvalue + ", ratevalueid=" + ratevalueid + ", gradesname="
				+ gradesname + ", gradescode=" + gradescode + ", additionalatr=" + additionalatr + "]";
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

	public long getGradesid() {
		return gradesid;
	}

	public void setGradesid(long gradesid) {
		this.gradesid = gradesid;
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

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getMidvalue() {
		return midvalue;
	}

	public void setMidvalue(float midvalue) {
		this.midvalue = midvalue;
	}

	public long getRatevalueid() {
		return ratevalueid;
	}

	public void setRatevalueid(long ratevalueid) {
		this.ratevalueid = ratevalueid;
	}

	public String getGradesname() {
		return gradesname;
	}

	public void setGradesname(String gradesname) {
		this.gradesname = gradesname;
	}

	public String getGradescode() {
		return gradescode;
	}

	public void setGradescode(String gradescode) {
		this.gradescode = gradescode;
	}

	public String getAdditionalatr() {
		return additionalatr;
	}

	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}

	

}
