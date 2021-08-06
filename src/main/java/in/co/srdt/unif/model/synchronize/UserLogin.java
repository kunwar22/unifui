package in.co.srdt.unif.model.synchronize;

public class UserLogin 
{
	private long id;

	private String loginid;
	
	private String emplid;

	private String emailid;

	private String createdby;

	public UserLogin() {
		super();
	}

	public UserLogin(long id, String loginid, String emplid, String emailid, String createdby) {
		super();
		this.id = id;
		this.loginid = loginid;
		this.emplid = emplid;
		this.emailid = emailid;
		this.createdby = createdby;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getEmplid() {
		return emplid;
	}

	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Override
	public String toString() {
		return "UserLogin [id=" + id + ", loginid=" + loginid + ", emplid=" + emplid + ", emailid=" + emailid
				+ ", createdby=" + createdby + "]";
	}
	
}
