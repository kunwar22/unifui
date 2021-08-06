package in.co.srdt.unif.model.personmanagement;

import java.util.ArrayList;
import java.util.List;


public class NationalDetailsWrapper {
	
	private List<NationalDetails> natdetails;

	public NationalDetailsWrapper() {
		natdetails = new ArrayList<>();
		natdetails.add(new NationalDetails());
	}

	public List<NationalDetails> getNatdetails() {
		return natdetails;
	}

	public void setNatdetails(List<NationalDetails> natdetails) {
		this.natdetails = natdetails;
	}

}
