package in.co.srdt.unif.model.reports;

import java.util.LinkedHashMap;

public class VendorPersonDetails {

	private String personnumber;

	private String personname;

	private String position;

	private String pfnumber;

	private String prannumber;

	private String uannumber;

	private String payscale;

	private int basicpay;

	private int gradepay;

	private int total;

	LinkedHashMap<String, String> elementdata;

	public VendorPersonDetails(String personnumber, String personname, String position, String pfnumber,
			String prannumber, String uannumber, String payscale, int basicpay, int gradepay, int total,
			LinkedHashMap<String, String> elementlist) {
		this.personnumber = personnumber;
		this.personname = personname;
		this.position = position;
		this.pfnumber = pfnumber;
		this.prannumber = prannumber;
		this.uannumber = uannumber;
		this.payscale = payscale;
		this.basicpay = basicpay;
		this.gradepay = gradepay;
		this.total = total;
		this.elementdata = elementlist;
	}

	public int getBasicpay() {
		return basicpay;
	}

	public void setBasicpay(int basicpay) {
		this.basicpay = basicpay;
	}

	public int getGradepay() {
		return gradepay;
	}

	public void setGradepay(int gradepay) {
		this.gradepay = gradepay;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public LinkedHashMap<String, String> getElementdata() {
		return elementdata;
	}

	public void setElementdata(LinkedHashMap<String, String> elementdata) {
		this.elementdata = elementdata;
	}

	public VendorPersonDetails() {
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPfnumber() {
		return pfnumber;
	}

	public void setPfnumber(String pfnumber) {
		this.pfnumber = pfnumber;
	}

	public String getPrannumber() {
		return prannumber;
	}

	public void setPrannumber(String prannumber) {
		this.prannumber = prannumber;
	}

	public String getUannumber() {
		return uannumber;
	}

	public void setUannumber(String uannumber) {
		this.uannumber = uannumber;
	}

	public String getPayscale() {
		return payscale;
	}

	public void setPayscale(String payscale) {
		this.payscale = payscale;
	}

}
