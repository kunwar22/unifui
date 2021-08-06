package in.co.srdt.unif.model.create;

public class LookupSave {
	
	private String effectstartdate;
	private String lookup;
	private String lookupcode;
	private String lookuptype;
	private String lookuptypedescription;
	private String status;
	private long id;
	
	public LookupSave() {
		
	}

	public LookupSave(String effectstartdate, String lookup, String lookupcode, String lookuptype,
			String lookuptypedescription, String status, long id) {
		super();
		this.effectstartdate = effectstartdate;
		this.lookup = lookup;
		this.lookupcode = lookupcode;
		this.lookuptype = lookuptype;
		this.lookuptypedescription = lookuptypedescription;
		this.status = status;
		this.id = id;
	}

	@Override
	public String toString() {
		return "LookupSave [effectstartdate=" + effectstartdate + ", lookup=" + lookup + ", lookupcode=" + lookupcode
				+ ", lookuptype=" + lookuptype + ", lookuptypedescription=" + lookuptypedescription + ", status="
				+ status + ", id=" + id + "]";
	}

	public String getEffectstartdate() {
		return effectstartdate;
	}

	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}

	public String getLookup() {
		return lookup;
	}

	public void setLookup(String lookup) {
		this.lookup = lookup;
	}

	public String getLookupcode() {
		return lookupcode;
	}

	public void setLookupcode(String lookupcode) {
		this.lookupcode = lookupcode;
	}

	public String getLookuptype() {
		return lookuptype;
	}

	public void setLookuptype(String lookuptype) {
		this.lookuptype = lookuptype;
	}

	public String getLookuptypedescription() {
		return lookuptypedescription;
	}

	public void setLookuptypedescription(String lookuptypedescription) {
		this.lookuptypedescription = lookuptypedescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
