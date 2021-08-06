package in.co.srdt.unif.model.emailconfig;

import javax.validation.constraints.NotBlank;

public class EmailConfigurationSearchModel {

	private String createdby;
	private String emailid;
	private long id;
	private String pasresetkey;
	private String password;
	private String portno;
	private String smtphost;
	private String updatedby;
	private String status;
	
	public EmailConfigurationSearchModel() {
		super();
	}
	
	public EmailConfigurationSearchModel(String createdby, String emailid, long id, String pasresetkey, String password,
			String portno, String smtphost, String updatedby, String status) {
		super();
		this.createdby = createdby;
		this.emailid = emailid;
		this.id = id;
		this.pasresetkey = pasresetkey;
		this.password = password;
		this.portno = portno;
		this.smtphost = smtphost;
		this.updatedby = updatedby;
		this.status = status;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPasresetkey() {
		return pasresetkey;
	}

	public void setPasresetkey(String pasresetkey) {
		this.pasresetkey = pasresetkey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPortno() {
		return portno;
	}

	public void setPortno(String portno) {
		this.portno = portno;
	}

	public String getSmtphost() {
		return smtphost;
	}

	public void setSmtphost(String smtphost) {
		this.smtphost = smtphost;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmailConfigurationModel [createdby=" + createdby + ", emailid=" + emailid + ", id=" + id
				+ ", pasresetkey=" + pasresetkey + ", password=" + password + ", portno=" + portno + ", smtphost="
				+ smtphost + ", updatedby=" + updatedby + ", status=" + status + "]";
	}
	
}
