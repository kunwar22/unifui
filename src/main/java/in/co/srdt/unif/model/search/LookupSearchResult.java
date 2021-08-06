package in.co.srdt.unif.model.search;

public class LookupSearchResult {
	
	private String lookuptype;
	private String lookuptypedescription;
	
	
	public LookupSearchResult() {
		
	}


	public LookupSearchResult(String lookuptype, String lookuptypedescription) {
		super();
		this.lookuptype = lookuptype;
		this.lookuptypedescription = lookuptypedescription;
	}


	@Override
	public String toString() {
		return "LookupSearchResult [lookuptype=" + lookuptype + ", lookuptypedescription=" + lookuptypedescription
				+ "]";
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

	
}
