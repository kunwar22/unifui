package in.co.srdt.unif.model.search;

public class AreaOfResponsibilityResult {
	
	private long aorid;

	private String personid;

	private String personnumber;

	private String responsibilityname;

	private String responsibilitytype;

	private String fromdate;

	private String todate;

	private String legalemployer;

	private String location;

	public AreaOfResponsibilityResult() {
		
	}

	public AreaOfResponsibilityResult(long aorid, String personid, String personnumber, String responsibilityname,
			String responsibilitytype, String fromdate, String todate, String legalemployer, String location) {
		super();
		this.aorid = aorid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.responsibilityname = responsibilityname;
		this.responsibilitytype = responsibilitytype;
		this.fromdate = fromdate;
		this.todate = todate;
		this.legalemployer = legalemployer;
		this.location = location;
	}

	public long getAorid() {
		return aorid;
	}

	public void setAorid(long aorid) {
		this.aorid = aorid;
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

	public String getResponsibilityname() {
		return responsibilityname;
	}

	public void setResponsibilityname(String responsibilityname) {
		this.responsibilityname = responsibilityname;
	}

	public String getResponsibilitytype() {
		return responsibilitytype;
	}

	public void setResponsibilitytype(String responsibilitytype) {
		this.responsibilitytype = responsibilitytype;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getLegalemployer() {
		return legalemployer;
	}

	public void setLegalemployer(String legalemployer) {
		this.legalemployer = legalemployer;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "AreaOfResponsibilityResult [aorid=" + aorid + ", personid=" + personid + ", personnumber="
				+ personnumber + ", responsibilityname=" + responsibilityname + ", responsibilitytype="
				+ responsibilitytype + ", fromdate=" + fromdate + ", todate=" + todate + ", legalemployer="
				+ legalemployer + ", location=" + location + "]";
	}

	

}
