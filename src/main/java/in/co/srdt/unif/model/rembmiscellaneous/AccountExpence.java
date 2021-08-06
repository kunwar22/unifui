package in.co.srdt.unif.model.rembmiscellaneous;

public class AccountExpence {

	  private long  accountcode;
	  private String description;
	
	public AccountExpence() {
		
	}

	public long getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(long accountcode) {
		this.accountcode = accountcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AccountExpence [accountcode=" + accountcode + ", description=" + description + "]";
	}

	
	
}
