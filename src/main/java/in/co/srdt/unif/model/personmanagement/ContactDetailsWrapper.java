package in.co.srdt.unif.model.personmanagement;

import java.util.ArrayList;
import java.util.List;


public class ContactDetailsWrapper {
	


	
	private List<ContactDetails> contactdetails;

	public ContactDetailsWrapper() {
		contactdetails = new ArrayList<>();
		contactdetails.add(new ContactDetails());
	}

	public List<ContactDetails> getContactdetails() {
		return contactdetails;
	}

	public void setContactdetails(List<ContactDetails> contactdetails) {
		this.contactdetails = contactdetails;
	}


	
	
	
	

}
