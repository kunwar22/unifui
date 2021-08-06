package in.co.srdt.unif.model.rembmedicalreimb;

import java.util.ArrayList;
import java.util.List;

public class MedHospitalTypeLOVModel {
	private long hospitaltypelovid;
	private String name;
	private String description;
	private String status;
	
	List<MedHospitalNameLOVModel> hospitalnamelov= new ArrayList<>();
	
	public MedHospitalTypeLOVModel() {
		super();
	}

	public MedHospitalTypeLOVModel(long hospitaltypelovid, String name, String description, String status,
			List<MedHospitalNameLOVModel> hospitalnamelov) {
		super();
		this.hospitaltypelovid = hospitaltypelovid;
		this.name = name;
		this.description = description;
		this.status = status;
		this.hospitalnamelov = hospitalnamelov;
	}

	public long getHospitaltypelovid() {
		return hospitaltypelovid;
	}

	public void setHospitaltypelovid(long hospitaltypelovid) {
		this.hospitaltypelovid = hospitaltypelovid;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MedHospitalNameLOVModel> getHospitalnamelov() {
		return hospitalnamelov;
	}

	public void setHospitalnamelov(List<MedHospitalNameLOVModel> hospitalnamelov) {
		this.hospitalnamelov = hospitalnamelov;
	}

	@Override
	public String toString() {
		return "MedHospitalTypeLOVModel [hospitaltypelovid=" + hospitaltypelovid + ", name=" + name + ", description="
				+ description + ", status=" + status + ", hospitalnamelov=" + hospitalnamelov + "]";
	}
		
}
