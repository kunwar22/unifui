package in.co.srdt.unif.model;

public class UserId {

	private long id;
	private String loginid;
	private String emplid;
	private String emailid;
	private String pwd;
	private String createdby;
	private String isactive;
	private String access_token;
	
	public UserId() {
		
	}

	public UserId(long id, String loginid, String emplid, String emailid, String pwd, String createdby, String isactive,
			String access_token) {
		super();
		this.id = id;
		this.loginid = loginid;
		this.emplid = emplid;
		this.emailid = emailid;
		this.pwd = pwd;
		this.createdby = createdby;
		this.isactive = isactive;
		this.access_token = access_token;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}	
}
