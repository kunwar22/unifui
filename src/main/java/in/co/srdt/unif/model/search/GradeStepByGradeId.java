package in.co.srdt.unif.model.search;

public class GradeStepByGradeId {
	
	private long actionid;
	private long gradestepid;
	private long gradesid;
	private long stepno;
	private String stepname;
	private double stepamount;
	
	public GradeStepByGradeId() {
		
	}
	
	public GradeStepByGradeId(long actionid, long gradestepid, long gradesid, long stepno, String stepname,
			double stepamount) {
		super();
		this.actionid = actionid;
		this.gradestepid = gradestepid;
		this.gradesid = gradesid;
		this.stepno = stepno;
		this.stepname = stepname;
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

	public long getGradesid() {
		return gradesid;
	}

	public void setGradesid(long gradesid) {
		this.gradesid = gradesid;
	}

	public long getStepno() {
		return stepno;
	}

	public void setStepno(long stepno) {
		this.stepno = stepno;
	}

	public String getStepname() {
		return stepname;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	public double getStepamount() {
		return stepamount;
	}

	public void setStepamount(double stepamount) {
		this.stepamount = stepamount;
	}

	@Override
	public String toString() {
		return "GradeStepByGradeId [actionid=" + actionid + ", gradestepid=" + gradestepid + ", gradesid=" + gradesid
				+ ", stepno=" + stepno + ", stepname=" + stepname + ", stepamount=" + stepamount + "]";
	}
	
	
	    
	  

}
