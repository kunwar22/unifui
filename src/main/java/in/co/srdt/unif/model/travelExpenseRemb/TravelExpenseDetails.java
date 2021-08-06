package in.co.srdt.unif.model.travelExpenseRemb;

import javax.validation.constraints.NotBlank;

public class TravelExpenseDetails {
    private long id;
	@NotBlank(message = "Please enter Expense Type.")
    private String expencetype;
	@NotBlank(message = "Please enter Travel Date.")
    private String traveldate;
	@NotBlank(message = "Please enter Expense Description.")
    private String expencedescription;
	@NotBlank(message = "Please enter Amount paid.")
    private String amountpaid;
	private String amountpaid2;
	private String attachments;
	private String attachhidden;
	private String filepres;

	private String Option;

	public TravelExpenseDetails() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExpencetype() {
		return expencetype;
	}

	public void setExpencetype(String expencetype) {
		this.expencetype = expencetype;
	}

	public String getTraveldate() {
		return traveldate;
	}

	public void setTraveldate(String traveldate) {
		this.traveldate = traveldate;
	}

	public String getExpencedescription() {
		return expencedescription;
	}

	public void setExpencedescription(String expencedescription) {
		this.expencedescription = expencedescription;
	}

	public String getAmountpaid() {
		return amountpaid;
	}

	public void setAmountpaid(String amountpaid) {
		this.amountpaid = amountpaid;
	}

	public String getAmountpaid2() {
		return amountpaid2;
	}

	public void setAmountpaid2(String amountpaid2) {
		this.amountpaid2 = amountpaid2;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public String getAttachhidden() {
		return attachhidden;
	}

	public void setAttachhidden(String attachhidden) {
		this.attachhidden = attachhidden;
	}

	public String getFilepres() {
		return filepres;
	}

	public void setFilepres(String filepres) {
		this.filepres = filepres;
	}

	public String getOption() {
		return Option;
	}

	public void setOption(String option) {
		Option = option;
	}

	@Override
	public String toString() {
		return "TravelExpenseDetails{" +
				"id='" + id + '\'' +
				", expencetype='" + expencetype + '\'' +
				", traveldate='" + traveldate + '\'' +
				", expencedescription='" + expencedescription + '\'' +
				", amountpaid='" + amountpaid + '\'' +
				", amountpaid2='" + amountpaid2 + '\'' +
				", attachments='" + attachments + '\'' +
				", attachhidden='" + attachhidden + '\'' +
				", filepres='" + filepres + '\'' +
				", Option='" + Option + '\'' +
				'}';
	}
}
