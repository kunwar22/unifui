package in.co.srdt.unif.model.vendordetails;

public class VendorElementMapping {

	private long elementmappid;
	private String vendorname;
	private Long element;
	private String elementname;
	private String createdby;
	private String modifiedby;
	private String createddate;
	private String lastupdateddate;
	private String isactive;
	private String vendorid;
	
	public VendorElementMapping() {
	
	}

	public long getElementmappid() {
		return elementmappid;
	}

	public void setElementmappid(long elementmappid) {
		this.elementmappid = elementmappid;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public Long getElement() {
		return element;
	}

	public void setElement(Long element) {
		this.element = element;
	}

	public String getElementname() {
		return elementname;
	}

	public void setElementname(String elementname) {
		this.elementname = elementname;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getLastupdateddate() {
		return lastupdateddate;
	}

	public void setLastupdateddate(String lastupdateddate) {
		this.lastupdateddate = lastupdateddate;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getVendorid() {
		return vendorid;
	}

	public void setVendorid(String vendorid) {
		this.vendorid = vendorid;
	}

	@Override
	public String toString() {
		return "VendorElementMapping [elementmappid=" + elementmappid + ", vendorname=" + vendorname + ", element="
				+ element + ", elementname=" + elementname + ", createdby=" + createdby + ", modifiedby=" + modifiedby
				+ ", createddate=" + createddate + ", lastupdateddate=" + lastupdateddate + ", isactive=" + isactive
				+ ", vendorid=" + vendorid + "]";
	}
	

	
}
