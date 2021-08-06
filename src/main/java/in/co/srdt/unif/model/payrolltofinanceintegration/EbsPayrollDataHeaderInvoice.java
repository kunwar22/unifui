package in.co.srdt.unif.model.payrolltofinanceintegration;
import java.util.List;

public class EbsPayrollDataHeaderInvoice {

	
	private long headersyncid;

	
	private String invoiceid;
	
	private String invoicenum;
	
	private String invoicetypeLookupcode;
	

	private String invoicedate;

	private long vendorid;
	
	private String vendornum;

	private String vendorsitecode;

	private long invoiceamount;
	
	private String invoicecurrencycode;
	
	private String termsname;
	
	private String description;
	
	private String attribute1;
	
	private String attribute2;
	
	private String attribute12;
	
	private String attribute13;
	
	private String source;
	
	private String paymentcurrencycode;
	
	private String doccategorycode;
	
	private String paymentmethodlookupcode;
	
	private String paygrouplookupcode;
	
	private String gldate;
	
	private String orgid;
	
	private String operatingunit;

	private String srdtstatus;
	
	private List<EbsPayrollDataLineInvoice> LineInvoice;

	public EbsPayrollDataHeaderInvoice(long headersyncid, String invoiceid, String invoicenum,
			String invoicetypeLookupcode, String invoicedate, long vendorid, String vendornum, String vendorsitecode,
			long invoiceamount, String invoicecurrencycode, String termsname, String description, String attribute1,
			String attribute2, String attribute12, String attribute13, String source, String paymentcurrencycode,
			String doccategorycode, String paymentmethodlookupcode, String paygrouplookupcode, String gldate,
			String orgid, String operatingunit,String srdtstatus, List<EbsPayrollDataLineInvoice> lineInvoice) {
		this.headersyncid = headersyncid;
		this.invoiceid = invoiceid;
		this.invoicenum = invoicenum;
		this.invoicetypeLookupcode = invoicetypeLookupcode;
		this.invoicedate = invoicedate;
		this.vendorid = vendorid;
		this.vendornum = vendornum;
		this.vendorsitecode = vendorsitecode;
		this.invoiceamount = invoiceamount;
		this.invoicecurrencycode = invoicecurrencycode;
		this.termsname = termsname;
		this.description = description;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute12 = attribute12;
		this.attribute13 = attribute13;
		this.source = source;
		this.paymentcurrencycode = paymentcurrencycode;
		this.doccategorycode = doccategorycode;
		this.paymentmethodlookupcode = paymentmethodlookupcode;
		this.paygrouplookupcode = paygrouplookupcode;
		this.gldate = gldate;
		this.orgid = orgid;
		this.operatingunit = operatingunit;
		this.srdtstatus = srdtstatus;
		LineInvoice = lineInvoice;
	}

	public EbsPayrollDataHeaderInvoice() {
		super();
	}

	public String getSrdtstatus() {
		return srdtstatus;
	}

	public void setSrdtstatus(String srdtstatus) {
		this.srdtstatus = srdtstatus;
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

	public String getInvoicenum() {
		return invoicenum;
	}

	public void setInvoicenum(String invoicenum) {
		this.invoicenum = invoicenum;
	}

	public String getInvoicetypeLookupcode() {
		return invoicetypeLookupcode;
	}

	public void setInvoicetypeLookupcode(String invoicetypeLookupcode) {
		this.invoicetypeLookupcode = invoicetypeLookupcode;
	}

	public String getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}

	public long getVendorid() {
		return vendorid;
	}

	public void setVendorid(long vendorid) {
		this.vendorid = vendorid;
	}

	public String getVendornum() {
		return vendornum;
	}

	public void setVendornum(String vendornum) {
		this.vendornum = vendornum;
	}

	public String getVendorsitecode() {
		return vendorsitecode;
	}

	public void setVendorsitecode(String vendorsitecode) {
		this.vendorsitecode = vendorsitecode;
	}

	public long getInvoiceamount() {
		return invoiceamount;
	}

	public void setInvoiceamount(long invoiceamount) {
		this.invoiceamount = invoiceamount;
	}

	public String getInvoicecurrencycode() {
		return invoicecurrencycode;
	}

	public void setInvoicecurrencycode(String invoicecurrencycode) {
		this.invoicecurrencycode = invoicecurrencycode;
	}

	public String getTermsname() {
		return termsname;
	}

	public void setTermsname(String termsname) {
		this.termsname = termsname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPaymentcurrencycode() {
		return paymentcurrencycode;
	}

	public void setPaymentcurrencycode(String paymentcurrencycode) {
		this.paymentcurrencycode = paymentcurrencycode;
	}

	public String getDoccategorycode() {
		return doccategorycode;
	}

	public void setDoccategorycode(String doccategorycode) {
		this.doccategorycode = doccategorycode;
	}

	public String getPaymentmethodlookupcode() {
		return paymentmethodlookupcode;
	}

	public void setPaymentmethodlookupcode(String paymentmethodlookupcode) {
		this.paymentmethodlookupcode = paymentmethodlookupcode;
	}

	public String getPaygrouplookupcode() {
		return paygrouplookupcode;
	}

	public void setPaygrouplookupcode(String paygrouplookupcode) {
		this.paygrouplookupcode = paygrouplookupcode;
	}

	public String getGldate() {
		return gldate;
	}

	public void setGldate(String gldate) {
		this.gldate = gldate;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOperatingunit() {
		return operatingunit;
	}

	public void setOperatingunit(String operatingunit) {
		this.operatingunit = operatingunit;
	}

	public List<EbsPayrollDataLineInvoice> getLineInvoice() {
		return LineInvoice;
	}

	public void setLineInvoice(List<EbsPayrollDataLineInvoice> lineInvoice) {
		LineInvoice = lineInvoice;
	}
	
	
	
}
