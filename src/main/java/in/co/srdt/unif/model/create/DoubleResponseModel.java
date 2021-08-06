package in.co.srdt.unif.model.create;

public class DoubleResponseModel {

	private String message;
	
	private String status;

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

	@Override
	public String toString() {
		return "DoubleResponseModel [message=" + message + ", status=" + status + "]";
	}

	public DoubleResponseModel(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}

	public DoubleResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
