package in.co.srdt.unif.model.user.absence;

public class AllAbsenceType {

	private long absencetypeid;
	private String absencetypename;
	private long eligibilityid;
	private long repeatingperiodid;
	
	public AllAbsenceType() {
		
	}

	public AllAbsenceType(long absencetypeid, String absencetypename, long eligibilityid, long repeatingperiodid) {
		super();
		this.absencetypeid = absencetypeid;
		this.absencetypename = absencetypename;
		this.eligibilityid = eligibilityid;
		this.repeatingperiodid = repeatingperiodid;
	}

	public long getAbsencetypeid() {
		return absencetypeid;
	}

	public void setAbsencetypeid(long absencetypeid) {
		this.absencetypeid = absencetypeid;
	}

	public String getAbsencetypename() {
		return absencetypename;
	}

	public void setAbsencetypename(String absencetypename) {
		this.absencetypename = absencetypename;
	}

	public long getEligibilityid() {
		return eligibilityid;
	}

	public void setEligibilityid(long eligibilityid) {
		this.eligibilityid = eligibilityid;
	}

	public long getRepeatingperiodid() {
		return repeatingperiodid;
	}

	public void setRepeatingperiodid(long repeatingperiodid) {
		this.repeatingperiodid = repeatingperiodid;
	}

	@Override
	public String toString() {
		return "AllAbsenceType [absencetypeid=" + absencetypeid + ", absencetypename=" + absencetypename
				+ ", eligibilityid=" + eligibilityid + ", repeatingperiodid=" + repeatingperiodid + "]";
	}

    
}
