package in.co.srdt.unif.model.payroll;

public class PayrollSetupSearchModel {
	private String elementName;
	private String elementType;
	
	public PayrollSetupSearchModel() {
		super();
	}

	public PayrollSetupSearchModel(String elementName, String elementType) {
		super();
		this.elementName = elementName;
		this.elementType = elementType;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	@Override
	public String toString() {
		return "PayrollSetupSearchModel [elementName=" + elementName + ", elementType=" + elementType + "]";
	}	
	
}
