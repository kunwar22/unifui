package in.co.srdt.unif.model.approvalnotification;

public class ApproverDetails {
	private String submittedtopersonno;
	private String submittedtopersonname;
	private String submittedondate;
	private String submittedtopersonemail;
	
	public ApproverDetails() {
	}

	public ApproverDetails(String submittedtopersonno, String submittedtopersonname, String submittedondate,
			String submittedtopersonemail) {
		this.submittedtopersonno = submittedtopersonno;
		this.submittedtopersonname = submittedtopersonname;
		this.submittedondate = submittedondate;
		this.submittedtopersonemail = submittedtopersonemail;
	}

	@Override
	public String toString() {
		return "ApproverDetails [submittedtopersonno=" + submittedtopersonno + ", submittedtopersonname="
				+ submittedtopersonname + ", submittedondate=" + submittedondate + ", submittedtopersonemail="
				+ submittedtopersonemail + "]";
	}

	public String getSubmittedtopersonno() {
		return submittedtopersonno;
	}

	public void setSubmittedtopersonno(String submittedtopersonno) {
		this.submittedtopersonno = submittedtopersonno;
	}

	public String getSubmittedtopersonname() {
		return submittedtopersonname;
	}

	public void setSubmittedtopersonname(String submittedtopersonname) {
		this.submittedtopersonname = submittedtopersonname;
	}

	public String getSubmittedondate() {
		return submittedondate;
	}

	public void setSubmittedondate(String submittedondate) {
		this.submittedondate = submittedondate;
	}

	public String getSubmittedtopersonemail() {
		return submittedtopersonemail;
	}

	public void setSubmittedtopersonemail(String submittedtopersonemail) {
		this.submittedtopersonemail = submittedtopersonemail;
	}
	
	
	
	
}
