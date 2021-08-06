package in.co.srdt.unif.model.absence.create;

public class AbsenceSearchResult {

	private long actionid;
	
	private long absencetypeid;

	private long absenceid;

	private String code;

	private String name;

	private String description;

	private String accrualfrequncy;

	private String accrualon;

	private String eligibilityid;

	private String accrualtype;

	private String status;

	public AbsenceSearchResult() {
		
	}

	public AbsenceSearchResult(long actionid, long absencetypeid, long absenceid, String code, String name,
			String description, String accrualfrequncy, String accrualon, String eligibilityid, String accrualtype,
			String status) {
		super();
		this.actionid = actionid;
		this.absencetypeid = absencetypeid;
		this.absenceid = absenceid;
		this.code = code;
		this.name = name;
		this.description = description;
		this.accrualfrequncy = accrualfrequncy;
		this.accrualon = accrualon;
		this.eligibilityid = eligibilityid;
		this.accrualtype = accrualtype;
		this.status = status;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getAbsencetypeid() {
		return absencetypeid;
	}

	public void setAbsencetypeid(long absencetypeid) {
		this.absencetypeid = absencetypeid;
	}

	public long getAbsenceid() {
		return absenceid;
	}

	public void setAbsenceid(long absenceid) {
		this.absenceid = absenceid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccrualfrequncy() {
		return accrualfrequncy;
	}

	public void setAccrualfrequncy(String accrualfrequncy) {
		this.accrualfrequncy = accrualfrequncy;
	}

	public String getAccrualon() {
		return accrualon;
	}

	public void setAccrualon(String accrualon) {
		this.accrualon = accrualon;
	}

	public String getEligibilityid() {
		return eligibilityid;
	}

	public void setEligibilityid(String eligibilityid) {
		this.eligibilityid = eligibilityid;
	}

	public String getAccrualtype() {
		return accrualtype;
	}

	public void setAccrualtype(String accrualtype) {
		this.accrualtype = accrualtype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AbsenceSearchResult [actionid=" + actionid + ", absencetypeid=" + absencetypeid + ", absenceid="
				+ absenceid + ", code=" + code + ", name=" + name + ", description=" + description
				+ ", accrualfrequncy=" + accrualfrequncy + ", accrualon=" + accrualon + ", eligibilityid="
				+ eligibilityid + ", accrualtype=" + accrualtype + ", status=" + status + "]";
	}

	
	
	

	
}
