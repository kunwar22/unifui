package in.co.srdt.unif.model.payroll;

public class PaygroupLOV {
	
	private String descr;
	private String createdby;
	private String name;
	private long paygroupid;
	
	public PaygroupLOV() {
		super();
	}

	public PaygroupLOV(String descr, String createdby, String name, long paygroupid) {
		super();
		this.descr = descr;
		this.createdby = createdby;
		this.name = name;
		this.paygroupid = paygroupid;
	}

	@Override
	public String toString() {
		return "PaygroupLOV [descr=" + descr + ", createdby=" + createdby + ", name=" + name + ", paygroupid="
				+ paygroupid + "]";
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPaygroupid() {
		return paygroupid;
	}

	public void setPaygroupid(long paygroupid) {
		this.paygroupid = paygroupid;
	}
	
	

}
