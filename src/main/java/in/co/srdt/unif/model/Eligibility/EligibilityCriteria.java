package in.co.srdt.unif.model.Eligibility;


public class EligibilityCriteria { //  Chield Class with Eligibility Criteria and Criteria Value **

	private long eligibilitycriteriaid;

	private long eligibilityid;

	private String eligibilitycriterianame;

    private String criteriavaluename;

    private String criteriavalueid;

    
    public EligibilityCriteria() {
		
	}

    public long getEligibilitycriteriaid() {
        return eligibilitycriteriaid;
    }



    public void setEligibilitycriteriaid(long eligibilitycriteriaid) {
        this.eligibilitycriteriaid = eligibilitycriteriaid;
    }

    public long getEligibilityid() {
        return eligibilityid;
    }

    public void setEligibilityid(long eligibilityid) {
        this.eligibilityid = eligibilityid;
    }


    public String getEligibilitycriterianame() {
        return eligibilitycriterianame;
    }

    public void setEligibilitycriterianame(String eligibilitycriterianame) {
        this.eligibilitycriterianame = eligibilitycriterianame;
    }

    public String getCriteriavaluename() {
        return criteriavaluename;
    }

    public void setCriteriavaluename(String criteriavaluename) {
        this.criteriavaluename = criteriavaluename;
    }


    public String getCriteriavalueid() {
        return criteriavalueid;
    }

    public void setCriteriavalueid(String criteriavalueid) {
        this.criteriavalueid = criteriavalueid;
    }

    @Override
    public String toString() {
        return "EligibilityCriteria{" +
                "eligibilitycriteriaid=" + eligibilitycriteriaid +
                ", eligibilityid=" + eligibilityid +
                ", eligibilitycriterianame='" + eligibilitycriterianame + '\'' +
                ", criteriavaluename='" + criteriavaluename + '\'' +
                ", criteriavalueid='" + criteriavalueid + '\'' +
                '}';
    }

    public EligibilityCriteria(long eligibilitycriteriaid, long eligibilityid, String eligibilitycriterianame, String criteriavaluename, String criteriavalueid) {
        this.eligibilitycriteriaid = eligibilitycriteriaid;
        this.eligibilityid = eligibilityid;
        this.eligibilitycriterianame = eligibilitycriterianame;
        this.criteriavaluename = criteriavaluename;
        this.criteriavalueid = criteriavalueid;
    }
}
