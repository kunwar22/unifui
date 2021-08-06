package in.co.srdt.unif.model.reports;

public class VendorSummeryReport {

	private String vendorno;

	private String vendorname;

	private String numberofemployee;

	private String totalamount;

	public VendorSummeryReport(String vendorno, String vendorname, String numberofemployee, String sumaoumt) {
		this.vendorno = vendorno;
		this.vendorname = vendorname;
		this.numberofemployee = numberofemployee;
		this.totalamount = sumaoumt;
	}

	public VendorSummeryReport() {
	}

	public String getVendorno() {
		return vendorno;
	}

	public void setVendorno(String vendorno) {
		this.vendorno = vendorno;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public String getNumberofemployee() {
		return numberofemployee;
	}

	public void setNumberofemployee(String numberofemployee) {
		this.numberofemployee = numberofemployee;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	@Override
	public String toString() {
		return "VendorSummeryReport [vendorno=" + vendorno + ", vendorname=" + vendorname + ", numberofemployee="
				+ numberofemployee + ", totalamount=" + totalamount + "]";
	}
	
}
