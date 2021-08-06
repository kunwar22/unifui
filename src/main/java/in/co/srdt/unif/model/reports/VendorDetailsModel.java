package in.co.srdt.unif.model.reports;

import java.util.List;

public class VendorDetailsModel {

	private String payeename;

	private String vendoraddress;
	
	private String desrc1;

	private String vendornumber;

	private List<VendorPersonDetails> persondetails;

	public VendorDetailsModel() {
	}


	public VendorDetailsModel(String payeename, String vendoraddress, String desrc1, String vendornumber,
			List<VendorPersonDetails> persondetails) {
		super();
		this.payeename = payeename;
		this.vendoraddress = vendoraddress;
		this.desrc1 = desrc1;
		this.vendornumber = vendornumber;
		this.persondetails = persondetails;
	}


	public List<VendorPersonDetails> getPersondetails() {
		return persondetails;
	}

	public void setPersondetails(List<VendorPersonDetails> persondetails) {
		this.persondetails = persondetails;
	}

	public String getPayeename() {
		return payeename;
	}

	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}

	public String getVendoraddress() {
		return vendoraddress;
	}

	public void setVendoraddress(String vendoraddress) {
		this.vendoraddress = vendoraddress;
	}

	public String getVendornumber() {
		return vendornumber;
	}

	public void setVendornumber(String vendornumber) {
		this.vendornumber = vendornumber;
	}

		
	public String getDesrc1() {
		return desrc1;
	}


	public void setDesrc1(String desrc1) {
		this.desrc1 = desrc1;
	}


	@Override
	public String toString() {
		return "VendorDetailsModel [payeename=" + payeename + ", vendoraddress=" + vendoraddress + ", desrc1=" + desrc1
				+ ", vendornumber=" + vendornumber + ", persondetails=" + persondetails + "]";
	}


	

}
