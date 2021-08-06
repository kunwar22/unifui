package in.co.srdt.unif.model.medicaladvance;

public class MedHospitalNameLOVModel {
	private long hospitalnamelovid;
	private String name;
	private String description;
	private String hospitaltypelov;
	
	public MedHospitalNameLOVModel() {
		super();
	}

	public MedHospitalNameLOVModel(long hospitalnamelovid, String name, String description, String hospitaltypelov) {
		super();
		this.hospitalnamelovid = hospitalnamelovid;
		this.name = name;
		this.description = description;
		this.hospitaltypelov = hospitaltypelov;
	}

	public long getHospitalnamelovid() {
		return hospitalnamelovid;
	}

	public void setHospitalnamelovid(long hospitalnamelovid) {
		this.hospitalnamelovid = hospitalnamelovid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHospitaltypelov() {
		return hospitaltypelov;
	}

	public void setHospitaltypelov(String hospitaltypelov) {
		this.hospitaltypelov = hospitaltypelov;
	}

	@Override
	public String toString() {
		return "MedHospitalNameLOVModel [hospitalnamelovid=" + hospitalnamelovid + ", name=" + name + ", description="
				+ description + ", hospitaltypelov=" + hospitaltypelov + "]";
	}
		
}
