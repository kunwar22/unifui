package in.co.srdt.unif.model.roleassignment;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class UserAutoRoleMapping {

	private long automappid;

	@NotBlank(message = "Please Enter Mapping Name")
	private String mappingname;
	
	@NotBlank(message = "Please Select Mapping Status")
	private String status;
	
	private String modifyby;

	private List<AutoMappingCriteria> automappcriteria;

	private List<AutoMappingRole> automappingrole;

	public UserAutoRoleMapping() {
		automappcriteria = new ArrayList<>();
		automappcriteria.add(new AutoMappingCriteria());
		
		automappingrole = new ArrayList<>();
		automappingrole.add(new AutoMappingRole());
	}

	public UserAutoRoleMapping(long automappid, @NotBlank(message = "Please Enter Mapping Name") String mappingname,
			@NotBlank(message = "Please Select Mapping Status") String status, String modifyby,
			List<AutoMappingCriteria> automappcriteria, List<AutoMappingRole> automappingrole) {
		super();
		this.automappid = automappid;
		this.mappingname = mappingname;
		this.status = status;
		this.modifyby = modifyby;
		this.automappcriteria = automappcriteria;
		this.automappingrole = automappingrole;
	}


	public long getAutomappid() {
		return automappid;
	}

	public void setAutomappid(long automappid) {
		this.automappid = automappid;
	}

	public String getMappingname() {
		return mappingname;
	}

	public void setMappingname(String mappingname) {
		this.mappingname = mappingname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AutoMappingCriteria> getAutomappcriteria() {
		return automappcriteria;
	}

	public void setAutomappcriteria(List<AutoMappingCriteria> automappcriteria) {
		this.automappcriteria = automappcriteria;
	}

	public List<AutoMappingRole> getAutomappingrole() {
		return automappingrole;
	}

	public void setAutomappingrole(List<AutoMappingRole> automappingrole) {
		this.automappingrole = automappingrole;
	}

	public String getModifyby() {
		return modifyby;
	}

	public void setModifyby(String modifyby) {
		this.modifyby = modifyby;
	}

	@Override
	public String toString() {
		return "UserAutoRoleMapping [automappid=" + automappid + ", mappingname=" + mappingname + ", status=" + status
				+ ", automappcriteria=" + automappcriteria + ", automappingrole=" + automappingrole + "]";
	}
	
	
	
}
	