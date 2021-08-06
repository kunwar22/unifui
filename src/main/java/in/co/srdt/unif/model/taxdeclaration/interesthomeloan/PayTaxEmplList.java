package in.co.srdt.unif.model.taxdeclaration.interesthomeloan;

import java.util.List;

public class PayTaxEmplList {

	private String finyear;
	private List<PayTaxIncLossSourceShow> person;
	private String personnumber;
	private String descr1;
	private String descr2;
	
	public PayTaxEmplList() {
		super();
	}

	public PayTaxEmplList(String finyear, List<PayTaxIncLossSourceShow> person, String personnumber, String descr1, String descr2) {
		this.finyear = finyear;
		this.person = person;
		this.personnumber = personnumber;
		this.descr1 = descr1;
		this.descr2 = descr2;
	}

	public String getDescr1() {
		return descr1;
	}

	public void setDescr1(String descr1) {
		this.descr1 = descr1;
	}

	public String getDescr2() {
		return descr2;
	}

	public void setDescr2(String descr2) {
		this.descr2 = descr2;
	}

	public String getFinyear() {
		return finyear;
	}

	public void setFinyear(String finyear) {
		this.finyear = finyear;
	}

	public List<PayTaxIncLossSourceShow> getPerson() {
		return person;
	}

	public void setPerson(List<PayTaxIncLossSourceShow> person) {
		this.person = person;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	@Override
	public String toString() {
		return "PayTaxEmplList{" +
				"finyear='" + finyear + '\'' +
				", person=" + person +
				", personnumber='" + personnumber + '\'' +
				", descr1='" + descr1 + '\'' +
				", descr2='" + descr2 + '\'' +
				'}';
	}
}
