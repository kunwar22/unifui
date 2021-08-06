package in.co.srdt.unif.model.user.corehr;


public class UserDocument {
	
	private String description;
	private String documentname;
	private String filename;
	private String filepath;
	private long id;
	private String personid;
	private String personnumber;
	private long type;
	private String typedsc;

	public UserDocument() {
		
	}
	
	

	public UserDocument(String description, String documentname, String filename, String filepath, long id,
			String personid, String personnumber, long type, String typedsc) {
		super();
		this.description = description;
		this.documentname = documentname;
		this.filename = filename;
		this.filepath = filepath;
		this.id = id;
		this.personid = personid;
		this.personnumber = personnumber;
		this.type = type;
		this.typedsc = typedsc;
	}



	@Override
	public String toString() {
		return "UserDocument [description=" + description + ", documentname=" + documentname + ", filename=" + filename
				+ ", filepath=" + filepath + ", id=" + id + ", personid=" + personid + ", personnumber=" + personnumber
				+ ", type=" + type + ", typedsc=" + typedsc + "]";
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocumentname() {
		return documentname;
	}

	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public String getTypedsc() {
		return typedsc;
	}

	public void setTypedsc(String typedsc) {
		this.typedsc = typedsc;
	}
	
	
	
}
