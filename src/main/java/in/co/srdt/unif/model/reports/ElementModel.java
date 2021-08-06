package in.co.srdt.unif.model.reports;

public class ElementModel {

	private String abbreviation;
	private String payamt;
	private String elementtype;
	
	public ElementModel() {
		super();
	}

	public ElementModel(String abbreviation, String payamt, String elementtype) {
		super();
		this.abbreviation = abbreviation;
		this.payamt = payamt;
		this.elementtype = elementtype;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getPayamt() {
		return payamt;
	}

	public void setPayamt(String payamt) {
		this.payamt = payamt;
	}

	public String getElementtype() {
		return elementtype;
	}

	public void setElementtype(String elementtype) {
		this.elementtype = elementtype;
	}

	@Override
	public String toString() {
		return "ElementModel [abbreviation=" + abbreviation + ", payamt=" + payamt + ", elementtype=" + elementtype
				+ "]";
	}
	
	
}
