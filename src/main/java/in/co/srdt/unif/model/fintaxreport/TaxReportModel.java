package in.co.srdt.unif.model.fintaxreport;

public class TaxReportModel {
	
public  String section;
	
	public  String description;
	
	public  Integer ammount;

	public TaxReportModel () {

	}

	public TaxReportModel (String section, String description, Integer ammount) {
		super();
		this.section = section;
		this.description = description;
		this.ammount = ammount;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAmmount() {
		return ammount;
	}

	public void setAmmount(Integer ammount) {
		this.ammount = ammount;
	}

}
