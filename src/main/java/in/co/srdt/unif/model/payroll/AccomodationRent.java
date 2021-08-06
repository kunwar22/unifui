package in.co.srdt.unif.model.payroll;

public class AccomodationRent {
    public long leaseId;
    public String personNumber;
    public String personName;
    public long accomodationTypeId;
    public String accomodationType;
    public String startdate;
    public String enddate;
    public String pmtToVendor_amt;
    public String leaseRecovery_amt;
    public String modifiedBy;
    public String createdBy;
    public String qtrRentRecovery_amt;
    
    private double calculateamt;
    private double overrideamt;

    public AccomodationRent() {
    }

	public long getLeaseId() {
		return leaseId;
	}

	public void setLeaseId(long leaseId) {
		this.leaseId = leaseId;
	}

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public long getAccomodationTypeId() {
		return accomodationTypeId;
	}

	public void setAccomodationTypeId(long accomodationTypeId) {
		this.accomodationTypeId = accomodationTypeId;
	}

	public String getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(String accomodationType) {
		this.accomodationType = accomodationType;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getPmtToVendor_amt() {
		return pmtToVendor_amt;
	}

	public void setPmtToVendor_amt(String pmtToVendor_amt) {
		this.pmtToVendor_amt = pmtToVendor_amt;
	}

	public String getLeaseRecovery_amt() {
		return leaseRecovery_amt;
	}

	public void setLeaseRecovery_amt(String leaseRecovery_amt) {
		this.leaseRecovery_amt = leaseRecovery_amt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getQtrRentRecovery_amt() {
		return qtrRentRecovery_amt;
	}

	public void setQtrRentRecovery_amt(String qtrRentRecovery_amt) {
		this.qtrRentRecovery_amt = qtrRentRecovery_amt;
	}

	public double getCalculateamt() {
		return calculateamt;
	}

	public void setCalculateamt(double calculateamt) {
		this.calculateamt = calculateamt;
	}

	public double getOverrideamt() {
		return overrideamt;
	}

	public void setOverrideamt(double overrideamt) {
		this.overrideamt = overrideamt;
	}

	@Override
	public String toString() {
		return "AccomodationRent [leaseId=" + leaseId + ", personNumber=" + personNumber + ", personName=" + personName
				+ ", accomodationTypeId=" + accomodationTypeId + ", accomodationType=" + accomodationType
				+ ", startdate=" + startdate + ", enddate=" + enddate + ", pmtToVendor_amt=" + pmtToVendor_amt
				+ ", leaseRecovery_amt=" + leaseRecovery_amt + ", modifiedBy=" + modifiedBy + ", createdBy=" + createdBy
				+ ", qtrRentRecovery_amt=" + qtrRentRecovery_amt + ", calculateamt=" + calculateamt + ", overrideamt="
				+ overrideamt + "]";
	}

    
}
