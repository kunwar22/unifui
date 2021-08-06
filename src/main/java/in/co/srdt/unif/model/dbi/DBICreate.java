package in.co.srdt.unif.model.dbi;

public class DBICreate {

	private long id;
    private String title;
    private String titlecolumnname;
    private String squery;
    private String createdby;
    private String updatedby;
    private String creationdate;
    private String lastupdateddate;
    private String status;
    private String sttribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    
	public DBICreate() {
		super();
	}

	public DBICreate(long id, String title, String squery, String createdby, String updatedby, String creationdate,
			String lastupdateddate, String titlecolumnname, String status, String sttribute1, String attribute2,
			String attribute3, String attribute4, String attribute5) {
		super();
		this.id = id;
		this.title = title;
		this.squery = squery;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.creationdate = creationdate;
		this.lastupdateddate = lastupdateddate;
		this.titlecolumnname = titlecolumnname;
		this.status = status;
		this.sttribute1 = sttribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.attribute5 = attribute5;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSquery() {
		return squery;
	}

	public void setSquery(String squery) {
		this.squery = squery;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getLastupdateddate() {
		return lastupdateddate;
	}

	public void setLastupdateddate(String lastupdateddate) {
		this.lastupdateddate = lastupdateddate;
	}

	public String getTitlecolumnname() {
		return titlecolumnname;
	}

	public void setTitlecolumnname(String titlecolumnname) {
		this.titlecolumnname = titlecolumnname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSttribute1() {
		return sttribute1;
	}

	public void setSttribute1(String sttribute1) {
		this.sttribute1 = sttribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Override
	public String toString() {
		return "DBICreate [id=" + id + ", title=" + title + ", squery=" + squery + ", createdby=" + createdby
				+ ", updatedby=" + updatedby + ", creationdate=" + creationdate + ", lastupdateddate=" + lastupdateddate
				+ ", titlecolumnname=" + titlecolumnname + ", status=" + status + ", sttribute1=" + sttribute1
				+ ", attribute2=" + attribute2 + ", attribute3=" + attribute3 + ", attribute4=" + attribute4
				+ ", attribute5=" + attribute5 + "]";
	}
    
    
    
}
