package in.co.srdt.unif.model.create;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CreateAor {
	
	private long actionid;

	private long aorid;

	private String personid;

	private String personnumber;
	
	@NotBlank(message = "Please enter responsibility name.")
	private String responsibilityname;

   @Min(value = 1, message = "Please enter responsibility type.")
	private long responsibilitytype;

   @NotBlank(message = "Please enter from date.")
	private String fromdate;

	private String todate;

	private long legalemployer;
	
	private String legalemployername;

	private long location;
	
	private String locationname;

	public CreateAor() {
		
	}

	public CreateAor(long actionid, long aorid, String personid, String personnumber,
			@NotBlank(message = "Please enter responsibility name.") String responsibilityname,
			@Min(value = 1, message = "Please enter responsibility type.") long responsibilitytype,
			@NotBlank(message = "Please enter from date.") String fromdate, String todate, long legalemployer,
			String legalemployername, long location, String locationname) {
		super();
		this.actionid = actionid;
		this.aorid = aorid;
		this.personid = personid;
		this.personnumber = personnumber;
		this.responsibilityname = responsibilityname;
		this.responsibilitytype = responsibilitytype;
		this.fromdate = fromdate;
		this.todate = todate;
		this.legalemployer = legalemployer;
		this.legalemployername = legalemployername;
		this.location = location;
		this.locationname = locationname;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
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

	public long getResponsibilitytype() {
		return responsibilitytype;
	}

	public void setResponsibilitytype(long responsibilitytype) {
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

	public long getLegalemployer() {
		return legalemployer;
	}

	public void setLegalemployer(long legalemployer) {
		this.legalemployer = legalemployer;
	}

	public String getLegalemployername() {
		return legalemployername;
	}

	public void setLegalemployername(String legalemployername) {
		this.legalemployername = legalemployername;
	}

	public long getLocation() {
		return location;
	}

	public void setLocation(long location) {
		this.location = location;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	@Override
	public String toString() {
		return "CreateAreaOfResponsibility [actionid=" + actionid + ", aorid=" + aorid + ", personid=" + personid
				+ ", personnumber=" + personnumber + ", responsibilityname=" + responsibilityname
				+ ", responsibilitytype=" + responsibilitytype + ", fromdate=" + fromdate + ", todate=" + todate
				+ ", legalemployer=" + legalemployer + ", legalemployername=" + legalemployername + ", location="
				+ location + ", locationname=" + locationname + "]";
	}
	
}
