package in.co.srdt.unif.model.user.corehr;

public class DocumentView {
	private long id;

	private String personnumber;
	
	private String personid;

	private String documentname;

	private String description;

	private String categoryname;
	
	private String filename;
	
	private String uploaddate;
	
	private String filepath;

	public  DocumentView() {
		
	}

	public DocumentView(long id, String personnumber, String personid, String documentname, String description,
			String categoryname, String filename, String uploaddate, String filepath) {
		super();
		this.id = id;
		this.personnumber = personnumber;
		this.personid = personid;
		this.documentname = documentname;
		this.description = description;
		this.categoryname = categoryname;
		this.filename = filename;
		this.uploaddate = uploaddate;
		this.filepath=filepath;
	}
	

	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getDocumentname() {
		return documentname;
	}

	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}

	@Override
	public String toString() {
		return "DocumentView [id=" + id + ", personnumber=" + personnumber + ", personid=" + personid
				+ ", documentname=" + documentname + ", description=" + description + ", categoryname=" + categoryname
				+ ", filename=" + filename + ", uploaddate=" + uploaddate + "]";
	}
	
}
