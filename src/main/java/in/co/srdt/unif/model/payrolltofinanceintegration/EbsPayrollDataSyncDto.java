package in.co.srdt.unif.model.payrolltofinanceintegration;

public class EbsPayrollDataSyncDto {

	private long payrollebssyncid;

	private String status;

	private Long ledgerid;

	private String accountingdate;

	private String currencycode;

	private String datecreated;

	private String actualflag;

	private String userjecategoryname;

	private String userjesourcename;

	private String currencyconversionDate;

	private String usercurrencyconversiontype;

	private Long currencyconversionrate;

	private Long EnteredDr;

	private Long enteredcr;

	private Long accounteddr;

	private Long accountedcr;

	private String transactiondate;

	private String reference1;

	private String reference2;

	private String reference4;

	private String reference5;

	private String reference10;

	private String periodname;

	private String functionalcurrencycode;

	private Long codecombinationid;

	private String datecreatedingl;

	private Long setofbooksid;

	private String codecombinationidinterim;

	private Long createdby;

	public EbsPayrollDataSyncDto() {
	}

	public EbsPayrollDataSyncDto(long payrollebssyncid, String status, Long ledgerid, String accountingdate,
								 String currencycode, String datecreated, String actualflag, String userjecategoryname,
								 String userjesourcename, String currencyconversionDate, String usercurrencyconversiontype,
								 Long currencyconversionrate, Long enteredDr, Long enteredcr, Long accounteddr, Long accountedcr,
								 String transactiondate, String reference1, String reference2, String reference4, String reference5,
								 String reference10, String periodname, String functionalcurrencycode, Long codecombinationid,
								 String datecreatedingl, Long setofbooksid, String codecombinationidinterim, Long createdby) {
		super();
		this.payrollebssyncid = payrollebssyncid;
		this.status = status;
		this.ledgerid = ledgerid;
		this.accountingdate = accountingdate;
		this.currencycode = currencycode;
		this.datecreated = datecreated;
		this.actualflag = actualflag;
		this.userjecategoryname = userjecategoryname;
		this.userjesourcename = userjesourcename;
		this.currencyconversionDate = currencyconversionDate;
		this.usercurrencyconversiontype = usercurrencyconversiontype;
		this.currencyconversionrate = currencyconversionrate;
		EnteredDr = enteredDr;
		this.enteredcr = enteredcr;
		this.accounteddr = accounteddr;
		this.accountedcr = accountedcr;
		this.transactiondate = transactiondate;
		this.reference1 = reference1;
		this.reference2 = reference2;
		this.reference4 = reference4;
		this.reference5 = reference5;
		this.reference10 = reference10;
		this.periodname = periodname;
		this.functionalcurrencycode = functionalcurrencycode;
		this.codecombinationid = codecombinationid;
		this.datecreatedingl = datecreatedingl;
		this.setofbooksid = setofbooksid;
		this.codecombinationidinterim = codecombinationidinterim;
		this.createdby = createdby;
	}

	public Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

	public long getPayrollebssyncid() {
		return payrollebssyncid;
	}

	public void setPayrollebssyncid(long payrollebssyncid) {
		this.payrollebssyncid = payrollebssyncid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getLedgerid() {
		return ledgerid;
	}

	public void setLedgerid(Long ledgerid) {
		this.ledgerid = ledgerid;
	}

	public String getAccountingdate() {
		return accountingdate;
	}

	public void setAccountingdate(String accountingdate) {
		this.accountingdate = accountingdate;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public String getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}

	public String getActualflag() {
		return actualflag;
	}

	public void setActualflag(String actualflag) {
		this.actualflag = actualflag;
	}

	public String getUserjecategoryname() {
		return userjecategoryname;
	}

	public void setUserjecategoryname(String userjecategoryname) {
		this.userjecategoryname = userjecategoryname;
	}

	public String getUserjesourcename() {
		return userjesourcename;
	}

	public void setUserjesourcename(String userjesourcename) {
		this.userjesourcename = userjesourcename;
	}

	public String getCurrencyconversionDate() {
		return currencyconversionDate;
	}

	public void setCurrencyconversionDate(String currencyconversionDate) {
		this.currencyconversionDate = currencyconversionDate;
	}

	public String getUsercurrencyconversiontype() {
		return usercurrencyconversiontype;
	}

	public void setUsercurrencyconversiontype(String usercurrencyconversiontype) {
		this.usercurrencyconversiontype = usercurrencyconversiontype;
	}

	public Long getCurrencyconversionrate() {
		return currencyconversionrate;
	}

	public void setCurrencyconversionrate(Long currencyconversionrate) {
		this.currencyconversionrate = currencyconversionrate;
	}

	public Long getEnteredDr() {
		return EnteredDr;
	}

	public void setEnteredDr(Long enteredDr) {
		EnteredDr = enteredDr;
	}

	public Long getEnteredcr() {
		return enteredcr;
	}

	public void setEnteredcr(Long enteredcr) {
		this.enteredcr = enteredcr;
	}

	public Long getAccounteddr() {
		return accounteddr;
	}

	public void setAccounteddr(Long accounteddr) {
		this.accounteddr = accounteddr;
	}

	public Long getAccountedcr() {
		return accountedcr;
	}

	public void setAccountedcr(Long accountedcr) {
		this.accountedcr = accountedcr;
	}

	public String getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(String transactiondate) {
		this.transactiondate = transactiondate;
	}

	public String getReference1() {
		return reference1;
	}

	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	public String getReference2() {
		return reference2;
	}

	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}

	public String getReference4() {
		return reference4;
	}

	public void setReference4(String reference4) {
		this.reference4 = reference4;
	}

	public String getReference5() {
		return reference5;
	}

	public void setReference5(String reference5) {
		this.reference5 = reference5;
	}

	public String getReference10() {
		return reference10;
	}

	public void setReference10(String reference10) {
		this.reference10 = reference10;
	}

	public String getPeriodname() {
		return periodname;
	}

	public void setPeriodname(String periodname) {
		this.periodname = periodname;
	}

	public String getFunctionalcurrencycode() {
		return functionalcurrencycode;
	}

	public void setFunctionalcurrencycode(String functionalcurrencycode) {
		this.functionalcurrencycode = functionalcurrencycode;
	}

	public Long getCodecombinationid() {
		return codecombinationid;
	}

	public void setCodecombinationid(Long codecombinationid) {
		this.codecombinationid = codecombinationid;
	}

	public String getDatecreatedingl() {
		return datecreatedingl;
	}

	public void setDatecreatedingl(String datecreatedingl) {
		this.datecreatedingl = datecreatedingl;
	}

	public Long getSetofbooksid() {
		return setofbooksid;
	}

	public void setSetofbooksid(Long setofbooksid) {
		this.setofbooksid = setofbooksid;
	}

	public String getCodecombinationidinterim() {
		return codecombinationidinterim;
	}

	public void setCodecombinationidinterim(String codecombinationidinterim) {
		this.codecombinationidinterim = codecombinationidinterim;
	}

	@Override
	public String toString() {
		return "EbsPayrollDataSyncDto{" +
				"payrollebssyncid=" + payrollebssyncid +
				", status='" + status + '\'' +
				", ledgerid=" + ledgerid +
				", accountingdate='" + accountingdate + '\'' +
				", currencycode='" + currencycode + '\'' +
				", datecreated='" + datecreated + '\'' +
				", actualflag='" + actualflag + '\'' +
				", userjecategoryname='" + userjecategoryname + '\'' +
				", userjesourcename='" + userjesourcename + '\'' +
				", currencyconversionDate='" + currencyconversionDate + '\'' +
				", usercurrencyconversiontype='" + usercurrencyconversiontype + '\'' +
				", currencyconversionrate=" + currencyconversionrate +
				", EnteredDr=" + EnteredDr +
				", enteredcr=" + enteredcr +
				", accounteddr=" + accounteddr +
				", accountedcr=" + accountedcr +
				", transactiondate='" + transactiondate + '\'' +
				", reference1='" + reference1 + '\'' +
				", reference2='" + reference2 + '\'' +
				", reference4='" + reference4 + '\'' +
				", reference5='" + reference5 + '\'' +
				", reference10='" + reference10 + '\'' +
				", periodname='" + periodname + '\'' +
				", functionalcurrencycode='" + functionalcurrencycode + '\'' +
				", codecombinationid=" + codecombinationid +
				", datecreatedingl='" + datecreatedingl + '\'' +
				", setofbooksid=" + setofbooksid +
				", codecombinationidinterim='" + codecombinationidinterim + '\'' +
				", createdby=" + createdby +
				'}';
	}
}
