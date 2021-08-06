package in.co.srdt.unif.model.absence.create;


public class AbsenceProration {

	private long actionid;//ato generated id

	private long prorationid;//ato generated id

	private int fromday;

	private int today;

	private float noofleaves;

	private long absencetypeid;//ato generated id

	public AbsenceProration() {
		
	}
	


	public AbsenceProration(long actionid, long prorationid, int fromday, int today, float noofleaves,
			long absencetypeid) {
		super();
		this.actionid = actionid;
		this.prorationid = prorationid;
		this.fromday = fromday;
		this.today = today;
		this.noofleaves = noofleaves;
		this.absencetypeid = absencetypeid;
	}

	

	public long getActionid() {
		return actionid;
	}



	public void setActionid(long actionid) {
		this.actionid = actionid;
	}



	public long getProrationid() {
		return prorationid;
	}



	public void setProrationid(long prorationid) {
		this.prorationid = prorationid;
	}



	public int getFromday() {
		return fromday;
	}



	public void setFromday(int fromday) {
		this.fromday = fromday;
	}



	public int getToday() {
		return today;
	}



	public void setToday(int today) {
		this.today = today;
	}



	public float getNoofleaves() {
		return noofleaves;
	}



	public void setNoofleaves(float noofleaves) {
		this.noofleaves = noofleaves;
	}



	public long getAbsencetypeid() {
		return absencetypeid;
	}



	public void setAbsencetypeid(long absencetypeid) {
		this.absencetypeid = absencetypeid;
	}



	@Override
	public String toString() {
		return "AbsenceProration [actionid=" + actionid + ", prorationid=" + prorationid + ", fromday=" + fromday
				+ ", today=" + today + ", noofleaves=" + noofleaves + ", absencetypeid=" + absencetypeid + "]";
	}
	
	
}
