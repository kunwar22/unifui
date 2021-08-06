package in.co.srdt.unif.model.taxdeclaration.hra;

public class HraOwnerDetails {
	
	private long ownerId;	
	private String personNumber;
	private String fromdate;
	private String todate;
	private String name;
	private String address;
	private String pan;
	private String attachment;
	private String createdby;
	private String modifiedby;
	private String createddate;
	private String lastupdateddate;
	
	public HraOwnerDetails() {
	}


	public HraOwnerDetails(long ownerId, String personNumber, String fromdate, String todate, String name,
			String address, String pan, String attachment, String createdby, String modifiedby, String createddate,
			String lastupdateddate) {
		super();
		this.ownerId = ownerId;
		this.personNumber = personNumber;
		this.fromdate = fromdate;
		this.todate = todate;
		this.name = name;
		this.address = address;
		this.pan = pan;
		this.attachment = attachment;
		this.createdby = createdby;
		this.modifiedby = modifiedby;
		this.createddate = createddate;
		this.lastupdateddate = lastupdateddate;
	}




	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
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


	@Override
	public String toString() {
		return "HraOwnerDetails [ownerId=" + ownerId + ", personNumber=" + personNumber + ", fromdate=" + fromdate
				+ ", todate=" + todate + ", name=" + name + ", address=" + address + ", pan=" + pan + ", attachment="
				+ attachment + ", createdby=" + createdby + ", modifiedby=" + modifiedby + ", createddate="
				+ createddate + ", lastupdateddate=" + lastupdateddate + "]";
	}


	
}
