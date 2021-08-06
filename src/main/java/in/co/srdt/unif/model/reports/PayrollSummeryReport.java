package in.co.srdt.unif.model.reports;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PayrollSummeryReport {

	private String abbreviation;
	private String payamt;
	private String elementtype;
	
	public PayrollSummeryReport() {
		super();
	}

	public PayrollSummeryReport(String abbreviation, String payamt, String elementtype) {
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
		return "PayrollSummeryReport [abbreviation=" + abbreviation + ", payamt=" + payamt + ", elementtype="
				+ elementtype + "]";
	}
}
