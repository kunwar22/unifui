package in.co.srdt.unif.model.rembchild;

public class Dependent_ChildModel {

	private String childname;
	
	private String childdob;
	
	private String childgendar;
	
	private String istwins;
	
	private String disability;

	public Dependent_ChildModel() {
		super();
	}

	public Dependent_ChildModel(String childname, String childdob, String childgendar, String istwins,String disability) {
		super();
		this.childname = childname;
		this.childdob = childdob;
		this.childgendar = childgendar;
		this.istwins = istwins;
		this.disability = disability;
	}

	public String getChildname() {
		return childname;
	}

	public void setChildname(String childname) {
		this.childname = childname;
	}

	public String getChilddob() {
		return childdob;
	}

	public void setChilddob(String childdob) {
		this.childdob = childdob;
	}

	public String getChildgendar() {
		return childgendar;
	}

	public void setChildgendar(String childgendar) {
		this.childgendar = childgendar;
	}

	public String getIstwins() {
		return istwins;
	}

	public void setIstwins(String istwins) {
		this.istwins = istwins;
	}	
	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	@Override
	public String toString() {
		return "Dependent_ChildModel [childname=" + childname + ", childdob=" + childdob + ", childgendar="
				+ childgendar + ", istwins=" + istwins + ", disability=" + disability + "]";
	}

	
	
	

}
