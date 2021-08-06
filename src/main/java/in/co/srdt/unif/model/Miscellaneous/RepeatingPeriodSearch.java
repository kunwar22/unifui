package in.co.srdt.unif.model.Miscellaneous;

public class RepeatingPeriodSearch {

	private long repeatingperiodid;
	private String name;
	private String description;
	private String code;
	private String status;
	private String periodtypename;
	private String periodlengthname;
	private String previewperiodstartdate;
	private String previewperiodenddate;
	
	
	
	public RepeatingPeriodSearch() {
		super();
	}



	public RepeatingPeriodSearch(long repeatingperiodid, String name, String description, String code, String status,
			String periodtypename, String periodlengthname, String previewperiodstartdate,
			String previewperiodenddate) {
		super();
		this.repeatingperiodid = repeatingperiodid;
		this.name = name;
		this.description = description;
		this.code = code;
		this.status = status;
		this.periodtypename = periodtypename;
		this.periodlengthname = periodlengthname;
		this.previewperiodstartdate = previewperiodstartdate;
		this.previewperiodenddate = previewperiodenddate;
	}



	@Override
	public String toString() {
		return "RepeatingPeriodSearch [repeatingperiodid=" + repeatingperiodid + ", name=" + name + ", description="
				+ description + ", code=" + code + ", status=" + status + ", periodtypename=" + periodtypename
				+ ", periodlengthname=" + periodlengthname + ", previewperiodstartdate=" + previewperiodstartdate
				+ ", previewperiodenddate=" + previewperiodenddate + "]";
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



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getPeriodtypename() {
		return periodtypename;
	}



	public void setPeriodtypename(String periodtypename) {
		this.periodtypename = periodtypename;
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





}
