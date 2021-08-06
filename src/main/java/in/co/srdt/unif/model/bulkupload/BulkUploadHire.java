package in.co.srdt.unif.model.bulkupload;

public class BulkUploadHire {
	

	private String personnumber;
	private String attribute1;
	private String attribute2;
	private String modifyby;
	
	public BulkUploadHire() {
		
	}



	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
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

	public String getModifyby() {
		return modifyby;
	}

	public void setModifyby(String modifyby) {
		this.modifyby = modifyby;
	}

	@Override
	public String toString() {
		return "BulkUploadHire [ personnumber=" + personnumber + ", attribute1=" + attribute1
				+ ", attribute2=" + attribute2 + ", modifyby=" + modifyby + "]";
	}
	
	
}
