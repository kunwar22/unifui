package in.co.srdt.unif.model.create;

public class GradeSteps {

	private long gradesid;

	private String stepno;

	private String stepname;
	
	private long stepamount;
	
	private long actionid;

	private long gradestepid;

	private String additionalatr;

	public GradeSteps() {
	}

	public GradeSteps(long gradesid, String stepno, String stepname, long stepamount, long actionid, long gradestepid,
			String additionalatr) {
		super();
		this.gradesid = gradesid;
		this.stepno = stepno;
		this.stepname = stepname;
		this.stepamount = stepamount;
		this.actionid = actionid;
		this.gradestepid = gradestepid;
		this.additionalatr = additionalatr;
	}

	public long getGradesid() {
		return gradesid;
	}

	public void setGradesid(long gradesid) {
		this.gradesid = gradesid;
	}

	public String getStepno() {
		return stepno;
	}

	public void setStepno(String stepno) {
		this.stepno = stepno;
	}

	public String getStepname() {
		return stepname;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	public long getStepamount() {
		return stepamount;
	}

	public void setStepamount(long stepamount) {
		this.stepamount = stepamount;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getGradestepid() {
		return gradestepid;
	}

	public void setGradestepid(long gradestepid) {
		this.gradestepid = gradestepid;
	}

	public String getAdditionalatr() {
		return additionalatr;
	}

	public void setAdditionalatr(String additionalatr) {
		this.additionalatr = additionalatr;
	}

	@Override
	public String toString() {
		return "GradeSteps [gradesid=" + gradesid + ", stepno=" + stepno + ", stepname=" + stepname + ", stepamount="
				+ stepamount + ", actionid=" + actionid + ", gradestepid=" + gradestepid + ", additionalatr="
				+ additionalatr + "]";
	}


}
