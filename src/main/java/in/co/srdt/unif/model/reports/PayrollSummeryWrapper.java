package in.co.srdt.unif.model.reports;

import java.util.ArrayList;
import java.util.List;

public class PayrollSummeryWrapper {

	private List<PayrollSummery> payrollsummary;

	public PayrollSummeryWrapper() {
		payrollsummary = new ArrayList<>();
		payrollsummary.add(new PayrollSummery());
	}

	public List<PayrollSummery> getPayrollsummary() {
		return payrollsummary;
	}

	public void setPayrollsummary(List<PayrollSummery> payrollsummary) {
		this.payrollsummary = payrollsummary;
	}

	
	
}
