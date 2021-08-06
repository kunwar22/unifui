package in.co.srdt.unif.model.taxdeclaration.chapter6A;

import java.util.List;

public class Chapter6AWrapper {

	private String personnumber;

	private String years;

	private List<Chapter6A> taxEmployeeChapter6A;

	public Chapter6AWrapper(String personnumber, String years, List<Chapter6A> taxEmployeeChapter6A) {
		this.personnumber = personnumber;
		this.years = years;
		this.taxEmployeeChapter6A = taxEmployeeChapter6A;
	}

	public Chapter6AWrapper() {
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public List<Chapter6A> getTaxEmployeeChapter6A() {
		return taxEmployeeChapter6A;
	}

	public void setTaxEmployeeChapter6A(List<Chapter6A> taxEmployeeChapter6A) {
		this.taxEmployeeChapter6A = taxEmployeeChapter6A;
	}

	@Override
	public String toString() {
		return "Chapter6AWrapper [personnumber=" + personnumber + ", years=" + years + ", taxEmployeeChapter6A="
				+ taxEmployeeChapter6A + "]";
	}

	

}
