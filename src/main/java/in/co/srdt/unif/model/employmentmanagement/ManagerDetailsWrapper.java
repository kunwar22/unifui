package in.co.srdt.unif.model.employmentmanagement;

import java.util.ArrayList;
import java.util.List;


public class ManagerDetailsWrapper {
	


	
	private List<ManagerDetails> managerdetails;

	public ManagerDetailsWrapper() {
		managerdetails = new ArrayList<>();
		managerdetails.add(new ManagerDetails());
	}

	public List<ManagerDetails> getManagerdetails() {
		return managerdetails;
	}

	public void setManagerdetails(List<ManagerDetails> managerdetails) {
		this.managerdetails = managerdetails;
	}

	
	

}
