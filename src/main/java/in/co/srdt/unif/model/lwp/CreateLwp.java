package in.co.srdt.unif.model.lwp;

public class CreateLwp {
	private String description;
	private String lwp="1";
	private long lwpid;
	private String period;
	private String personnumber;
	private String status;
	private String personid;
	private String leaveflag;
	private String createdby;
	private String updatedby;

	public CreateLwp() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLwp() {
		return lwp;
	}

	public void setLwp(String lwp) {
		this.lwp = lwp;
	}

	public long getLwpid() {
		return lwpid;
	}

	public void setLwpid(long lwpid) {
		this.lwpid = lwpid;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getLeaveflag() {
		return leaveflag;
	}

	public void setLeaveflag(String leaveflag) {
		this.leaveflag = leaveflag;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@Override
	public String toString() {
		return "CreateLwp{" +
				"description='" + description + '\'' +
				", lwp='" + lwp + '\'' +
				", lwpid=" + lwpid +
				", period='" + period + '\'' +
				", personnumber='" + personnumber + '\'' +
				", status='" + status + '\'' +
				", personid='" + personid + '\'' +
				", leaveflag='" + leaveflag + '\'' +
				", createdby='" + createdby + '\'' +
				", updatedby='" + updatedby + '\'' +
				'}';
	}
}
