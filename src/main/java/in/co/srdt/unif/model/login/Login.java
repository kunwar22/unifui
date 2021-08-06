package in.co.srdt.unif.model.login;

public class Login {
    private String loginid;
    private String pwd;
    private String access_tocken;
    private long id;
    private String emplid;
    private String emailid;
    private String isactive;
    private String createdby;

    public Login() {
    }

    public Login(String loginid, String pwd, String access_tocken, long id, String emplid, String emailid, String isactive, String createdby) {
        this.loginid = loginid;
        this.pwd = pwd;
        this.access_tocken = access_tocken;
        this.id = id;
        this.emplid = emplid;
        this.emailid = emailid;
        this.isactive = isactive;
        this.createdby = createdby;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAccess_tocken() {
        return access_tocken;
    }

    public void setAccess_tocken(String access_tocken) {
        this.access_tocken = access_tocken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }
}
