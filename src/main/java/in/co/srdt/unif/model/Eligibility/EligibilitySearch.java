package in.co.srdt.unif.model.Eligibility;

public class EligibilitySearch {
  private long 	eligibilityid;
  private String  eligibilityname;
  private String  eligibilitydescription;

  public EligibilitySearch() {
	
}

public EligibilitySearch(long eligibilityid, String eligibilityname, String eligibilitydescription) {
	super();
	this.eligibilityid = eligibilityid;
	this.eligibilityname = eligibilityname;
	this.eligibilitydescription = eligibilitydescription;
}

public long getEligibilityid() {
	return eligibilityid;
}

public void setEligibilityid(long eligibilityid) {
	this.eligibilityid = eligibilityid;
}

public String getEligibilityname() {
	return eligibilityname;
}

public void setEligibilityname(String eligibilityname) {
	this.eligibilityname = eligibilityname;
}

public String getEligibilitydescription() {
	return eligibilitydescription;
}

public void setEligibilitydescription(String eligibilitydescription) {
	this.eligibilitydescription = eligibilitydescription;
}

@Override
public String toString() {
	return "EligibilitySearch [eligibilityid=" + eligibilityid + ", eligibilityname=" + eligibilityname
			+ ", eligibilitydescription=" + eligibilitydescription + "]";
}
  
  
  

}
