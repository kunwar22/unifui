package in.co.srdt.unif.model.create;

public class SingleResponseModel {

	private String message;
	
	private String status;

	private long calendarid;

	private String calendarcode;

	public SingleResponseModel() {
		
	}

	public SingleResponseModel(String message, String status, long calendarid, String calendarcode) {
		this.message = message;
		this.status = status;
		this.calendarid = calendarid;
		this.calendarcode = calendarcode;
	}

	public String getCalendarcode() {
		return calendarcode;
	}

	public void setCalendarcode(String calendarcode) {
		this.calendarcode = calendarcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCalendarid() {
		return calendarid;
	}

	public void setCalendarid(long calendarid) {
		this.calendarid = calendarid;
	}


	@Override
	public String toString() {
		return "SingleResponseModel{" +
				"message='" + message + '\'' +
				", status='" + status + '\'' +
				", calendarid=" + calendarid +
				", calendarcode='" + calendarcode + '\'' +
				'}';
	}
}