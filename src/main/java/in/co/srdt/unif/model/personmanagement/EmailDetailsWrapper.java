package in.co.srdt.unif.model.personmanagement;

import java.util.ArrayList;
import java.util.List;


public class EmailDetailsWrapper {
	
	private List<EmailDetails> emailDetails;

	public EmailDetailsWrapper() {
		emailDetails = new ArrayList<>();
		emailDetails.add(new EmailDetails());
	}

	public List<EmailDetails> getEmailDetails() {
		return emailDetails;
	}

	public void setEmailDetails(List<EmailDetails> emailDetails) {
		this.emailDetails = emailDetails;
	}

}
