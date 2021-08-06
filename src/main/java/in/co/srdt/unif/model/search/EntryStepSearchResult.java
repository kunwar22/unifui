package in.co.srdt.unif.model.search;

public class EntryStepSearchResult {
	
	private long actionid;
	
	private long gradestepid;
	
	private long gradesid;
	
	private int stepno;
	
	private String stepname;
	
	public EntryStepSearchResult() {
		
	}

	public EntryStepSearchResult(long actionid, long gradestepid, long gradesid, int stepno, String stepname) {
		super();
		this.actionid = actionid;
		this.gradestepid = gradestepid;
		this.gradesid = gradesid;
		this.stepno = stepno;
		this.stepname = stepname;
	}

	@Override
	public String toString() {
		return "EntryStepSearchResult [actionid=" + actionid + ", gradestepid=" + gradestepid + ", gradesid=" + gradesid
				+ ", stepno=" + stepno + ", stepname=" + stepname + "]";
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

	public int getStepno() {
		return stepno;
	}

	public void setStepno(int stepno) {
		this.stepno = stepno;
	}

	public String getStepname() {
		return stepname;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	
	
}
