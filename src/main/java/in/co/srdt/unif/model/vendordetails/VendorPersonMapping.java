package in.co.srdt.unif.model.vendordetails;

public class VendorPersonMapping {

	private long personmappid;
	private String vendorid;
	private String personnumber;
	private String personname;
	private String createddate;
	private String lastupdateddate;
	private String isactive;
	private String natureofemployement;
	private String vendorname;
	private String createdby;
	private String modifiedby;

	public VendorPersonMapping() {

	}

	public long getPersonmappid() {
		return personmappid;
	}

	public void setPersonmappid(long personmappid) {
		this.personmappid = personmappid;
	}

	public String getVendorid() {
		return vendorid;
	}

	public void setVendorid(String vendorid) {
		this.vendorid = vendorid;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
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

	public String getNatureofemployement() {
		return natureofemployement;
	}

	public void setNatureofemployement(String natureofemployement) {
		this.natureofemployement = natureofemployement;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
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

	@Override
	public String toString() {
		return "VendorPersonMapping [personmappid=" + personmappid + ", vendorid=" + vendorid + ", personnumber="
				+ personnumber + ", personname=" + personname + ", createddate=" + createddate + ", lastupdateddate="
				+ lastupdateddate + ", isactive=" + isactive + ", natureofemployement=" + natureofemployement
				+ ", vendorname=" + vendorname + ", createdby=" + createdby + ", modifiedby=" + modifiedby + "]";
	}
	
}
