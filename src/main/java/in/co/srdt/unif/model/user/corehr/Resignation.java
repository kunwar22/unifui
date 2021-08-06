package in.co.srdt.unif.model.user.corehr;
import javax.validation.constraints.*;

public class Resignation {
	
	private long resignationid;
	
	private String personid;

	private String acceptedpersonid;

	private String accepteddateoftermination;

	private String actualterminationdate;

	private String notificationdate;
	
	
	
	@Min(value = 1, message = "Please select the reason.")
	private long reason;
	
	private String reasonname;
	
	@NotBlank(message = "Comments must not be empty.")
	private String comment;
	
	public Resignation() {
		
	}

	@Override
	public String toString() {
		return "Resignation [resignationid=" + resignationid + ", personid=" + personid + ", acceptedpersonid="
				+ acceptedpersonid + ", accepteddateoftermination=" + accepteddateoftermination
				+ ", actualterminationdate=" + actualterminationdate + ", notificationdate=" + notificationdate
				+ ", reason=" + reason + ", reasonname=" + reasonname + ", comment=" + comment + "]";
	}

	public Resignation(long resignationid, String personid, String acceptedpersonid, String accepteddateoftermination,
			String actualterminationdate, String notificationdate, @NotNull long reason, String reasonname,
			@NotBlank String comment) {
		super();
		this.resignationid = resignationid;
		this.personid = personid;
		this.acceptedpersonid = acceptedpersonid;
		this.accepteddateoftermination = accepteddateoftermination;
		this.actualterminationdate = actualterminationdate;
		this.notificationdate = notificationdate;
		this.reason = reason;
		this.reasonname = reasonname;
		this.comment = comment;
	}

	public long getResignationid() {
		return resignationid;
	}

	public void setResignationid(long resignationid) {
		this.resignationid = resignationid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getAcceptedpersonid() {
		return acceptedpersonid;
	}

	public void setAcceptedpersonid(String acceptedpersonid) {
		this.acceptedpersonid = acceptedpersonid;
	}

	public String getAccepteddateoftermination() {
		return accepteddateoftermination;
	}

	public void setAccepteddateoftermination(String accepteddateoftermination) {
		this.accepteddateoftermination = accepteddateoftermination;
	}

	public String getActualterminationdate() {
		return actualterminationdate;
	}

	public void setActualterminationdate(String actualterminationdate) {
		this.actualterminationdate = actualterminationdate;
	}

	public String getNotificationdate() {
		return notificationdate;
	}

	public void setNotificationdate(String notificationdate) {
		this.notificationdate = notificationdate;
	}

	public long getReason() {
		return reason;
	}

	public void setReason(long reason) {
		this.reason = reason;
	}

	public String getReasonname() {
		return reasonname;
	}

	public void setReasonname(String reasonname) {
		this.reasonname = reasonname;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
