package in.co.srdt.unif.model.vendordetails;

import java.util.List;

public class VendorDetails {

	private long id;
	private String vendorid;		
	private String vendorname;
	private String vendorno;
	private String vendorcode;
	private String vendoraddress;
	private String status;
	private String createdby;
	private String modifiedby;
	private String createddate;
	private String lastupdateddate;
	private String isactive;
	private List<VendorElementMapping> vendorelementmappingmodel;
	private List<VendorPersonMapping> vendorpersonmappingmodel;
//	private String personnumber;
//	private String personname;

	public VendorDetails() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVendorid() {
		return vendorid;
	}

	public void setVendorid(String vendorid) {
		this.vendorid = vendorid;
	}

//	public String getPersonnumber() {
//		return personnumber;
//	}
//
//	public void setPersonnumber(String personnumber) {
//		this.personnumber = personnumber;
//	}
//
//	public String getPersonname() {
//		return personname;
//	}
//
//	public void setPersonname(String personname) {
//		this.personname = personname;
//	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public String getVendorno() {
		return vendorno;
	}

	public void setVendorno(String vendorno) {
		this.vendorno = vendorno;
	}

	public String getVendorcode() {
		return vendorcode;
	}

	public void setVendorcode(String vendorcode) {
		this.vendorcode = vendorcode;
	}

	public String getVendoraddress() {
		return vendoraddress;
	}

	public void setVendoraddress(String vendoraddress) {
		this.vendoraddress = vendoraddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<VendorElementMapping> getVendorelementmappingmodel() {
		return vendorelementmappingmodel;
	}

	public void setVendorelementmappingmodel(List<VendorElementMapping> vendorelementmappingmodel) {
		this.vendorelementmappingmodel = vendorelementmappingmodel;
	}

	public List<VendorPersonMapping> getVendorpersonmappingmodel() {
		return vendorpersonmappingmodel;
	}

	public void setVendorpersonmappingmodel(List<VendorPersonMapping> vendorpersonmappingmodel) {
		this.vendorpersonmappingmodel = vendorpersonmappingmodel;
	}

	@Override
	public String toString() {
		return "VendorDetails [id=" + id + ", vendorid=" + vendorid + ", vendorname=" + vendorname + ", vendorno="
				+ vendorno + ", vendorcode=" + vendorcode + ", vendoraddress=" + vendoraddress + ", status=" + status
				+ ", createdby=" + createdby + ", modifiedby=" + modifiedby + ", createddate=" + createddate
				+ ", lastupdateddate=" + lastupdateddate + ", isactive=" + isactive + ", vendorelementmappingmodel="
				+ vendorelementmappingmodel + ", vendorpersonmappingmodel=" + vendorpersonmappingmodel + "]";
	}

	

	
}
