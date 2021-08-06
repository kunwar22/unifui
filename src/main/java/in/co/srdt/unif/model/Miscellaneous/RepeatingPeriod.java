package in.co.srdt.unif.model.Miscellaneous;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RepeatingPeriod {
	
	private long actionid;

	private long repeatingperiodid;
	
	@NotBlank(message = "Please enter the Name.")
	private String name;
	
	@NotBlank(message = "Please enter the Code.")
	private String code;

	@NotBlank(message = "Please enter the Description.")
	private String description;
	
	@Min(value = 1, message = "Please select the Period Type.")
	private long periodtype;

	private String periodtypename;

	@Min(value = 1, message = "Please select the Period Length.")
	private long periodlength;

	private String periodlengthname;

	@NotBlank(message = "Please enter the Start Date.")
	private String previewperiodstartdate;

	@NotBlank(message = "Please enter the End Date.")
	private String previewperiodenddate;
	
	private String status;

	public RepeatingPeriod() {
		
	}

	public RepeatingPeriod(long actionid, long repeatingperiodid,
			@NotBlank(message = "Please enter the Name.") String name,
			@NotBlank(message = "Please enter the Code.") String code,
			@NotBlank(message = "Please enter the Description.") String description,
			@Min(value = 1, message = "Please select the Period Type.") long periodtype, String periodtypename,
			@Min(value = 1, message = "Please select the Period Length.") long periodlength, String periodlengthname,
			@NotBlank(message = "Please enter the Start Date.") String previewperiodstartdate,
			@NotBlank(message = "Please enter the End Date.") String previewperiodenddate, String status) {
		super();
		this.actionid = actionid;
		this.repeatingperiodid = repeatingperiodid;
		this.name = name;
		this.code = code;
		this.description = description;
		this.periodtype = periodtype;
		this.periodtypename = periodtypename;
		this.periodlength = periodlength;
		this.periodlengthname = periodlengthname;
		this.previewperiodstartdate = previewperiodstartdate;
		this.previewperiodenddate = previewperiodenddate;
		this.status = status;
	}

	public long getActionid() {
		return actionid;
	}

	public void setActionid(long actionid) {
		this.actionid = actionid;
	}

	public long getRepeatingperiodid() {
		return repeatingperiodid;
	}

	public void setRepeatingperiodid(long repeatingperiodid) {
		this.repeatingperiodid = repeatingperiodid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getPeriodtype() {
		return periodtype;
	}

	public void setPeriodtype(long periodtype) {
		this.periodtype = periodtype;
	}

	public String getPeriodtypename() {
		return periodtypename;
	}

	public void setPeriodtypename(String periodtypename) {
		this.periodtypename = periodtypename;
	}

	public long getPeriodlength() {
		return periodlength;
	}

	public void setPeriodlength(long periodlength) {
		this.periodlength = periodlength;
	}

	public String getPeriodlengthname() {
		return periodlengthname;
	}

	public void setPeriodlengthname(String periodlengthname) {
		this.periodlengthname = periodlengthname;
	}

	public String getPreviewperiodstartdate() {
		return previewperiodstartdate;
	}

	public void setPreviewperiodstartdate(String previewperiodstartdate) {
		this.previewperiodstartdate = previewperiodstartdate;
	}

	public String getPreviewperiodenddate() {
		return previewperiodenddate;
	}

	public void setPreviewperiodenddate(String previewperiodenddate) {
		this.previewperiodenddate = previewperiodenddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RepeatingPeriod [actionid=" + actionid + ", repeatingperiodid=" + repeatingperiodid + ", name=" + name
				+ ", code=" + code + ", description=" + description + ", periodtype=" + periodtype + ", periodtypename="
				+ periodtypename + ", periodlength=" + periodlength + ", periodlengthname=" + periodlengthname
				+ ", previewperiodstartdate=" + previewperiodstartdate + ", previewperiodenddate="
				+ previewperiodenddate + ", status=" + status + "]";
	}
	

	
}
