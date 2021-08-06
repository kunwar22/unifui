package in.co.srdt.unif.model.approval;

public class ApprovalLOV {
	
	private long id;
	
	private String name;
	
	private String personnumber;

	
	
	public ApprovalLOV() {
	}



	public ApprovalLOV(long id, String name, String personnumber) {
		super();
		this.id = id;
		this.name = name;
		this.personnumber = personnumber;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPersonnumber() {
		return personnumber;
	}



	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}



	@Override
	public String toString() {
		return "ApprovalLOV [id=" + id + ", name=" + name + ", personnumber=" + personnumber + "]";
	}
	
	

}
