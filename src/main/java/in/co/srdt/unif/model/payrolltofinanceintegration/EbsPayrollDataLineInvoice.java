package in.co.srdt.unif.model.payrolltofinanceintegration;

public class EbsPayrollDataLineInvoice {

	private long linesyncid;

	private long headersyncid;

	private String invoiceid;

	private String invoicelineid;

	private String linetypelookupcode;

	private long amount;

	private String accountingdate;

	private String description;

	private String distcodeconcatenated;

	private String orgid;

	public EbsPayrollDataLineInvoice(long linesyncid, long headersyncid, String invoiceid, String invoicelineid,
			String linetypelookupcode, long amount, String accountingdate, String description,
			String distcodeconcatenated, String orgid) {
		this.linesyncid = linesyncid;
		this.headersyncid = headersyncid;
		this.invoiceid = invoiceid;
		this.invoicelineid = invoicelineid;
		this.linetypelookupcode = linetypelookupcode;
		this.amount = amount;
		this.accountingdate = accountingdate;
		this.description = description;
		this.distcodeconcatenated = distcodeconcatenated;
		this.orgid = orgid;
	}

	public EbsPayrollDataLineInvoice() {
	}

	public long getLinesyncid() {
		return linesyncid;
	}

	public void setLinesyncid(long linesyncid) {
		this.linesyncid = linesyncid;
	}

	public long getHeadersyncid() {
		return headersyncid;
	}

	public void setHeadersyncid(long headersyncid) {
		this.headersyncid = headersyncid;
	}

	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getInvoicelineid() {
		return invoicelineid;
	}

	public void setInvoicelineid(String invoicelineid) {
		this.invoicelineid = invoicelineid;
	}

	public String getLinetypelookupcode() {
		return linetypelookupcode;
	}

	public void setLinetypelookupcode(String linetypelookupcode) {
		this.linetypelookupcode = linetypelookupcode;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getAccountingdate() {
		return accountingdate;
	}

	public void setAccountingdate(String accountingdate) {
		this.accountingdate = accountingdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDistcodeconcatenated() {
		return distcodeconcatenated;
	}

	public void setDistcodeconcatenated(String distcodeconcatenated) {
		this.distcodeconcatenated = distcodeconcatenated;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	
	
}
