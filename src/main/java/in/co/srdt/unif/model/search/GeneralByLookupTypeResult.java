package in.co.srdt.unif.model.search;

public class GeneralByLookupTypeResult {
	
	private String effectstartdate;
	private String lookuptypedescription;
	private String lookupcode;
	private String lookup;
	private String status;
	private long id;
	private String lookuptype;
	
	public GeneralByLookupTypeResult() {
		
	}

	public GeneralByLookupTypeResult(String effectstartdate, String lookuptypedescription, String lookupcode,
			String lookup, String status, long id, String lookuptype) {
		super();
		this.effectstartdate = effectstartdate;
		this.lookuptypedescription = lookuptypedescription;
		this.lookupcode = lookupcode;
		this.lookup = lookup;
		this.status = status;
		this.id=id;
		this.lookuptype=lookuptype;
	}

	

	
	

	@Override
	public String toString() {
		return "GeneralByLookupTypeResult [effectstartdate=" + effectstartdate + ", lookuptypedescription="
				+ lookuptypedescription + ", lookupcode=" + lookupcode + ", lookup=" + lookup + ", status=" + status
				+ ", id=" + id + ", lookuptype=" + lookuptype + "]";
	}

	
	
	public String getLookuptype() {
		return lookuptype;
	}

	public void setLookuptype(String lookuptype) {
		this.lookuptype = lookuptype;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEffectstartdate() {
		return effectstartdate;
	}

	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}

	public String getLookuptypedescription() {
		return lookuptypedescription;
	}

	public void setLookuptypedescription(String lookuptypedescription) {
		this.lookuptypedescription = lookuptypedescription;
	}

	public String getLookupcode() {
		return lookupcode;
	}

	public void setLookupcode(String lookupcode) {
		this.lookupcode = lookupcode;
	}

	public String getLookup() {
		return lookup;
	}

	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
