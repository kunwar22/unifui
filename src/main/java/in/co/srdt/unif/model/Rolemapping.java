package in.co.srdt.unif.model;

import java.util.ArrayList;
import java.util.List;

public class Rolemapping {

	private long userid;
	
	private String createdby;
	
	private List<Long> actionids=new ArrayList<>();

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public List<Long> getActionids() {
		return actionids;
	}

	public void setActionids(List<Long> actionids) {
		this.actionids = actionids;
	}

	@Override
	public String toString() {
		return "Rolemapping [userid=" + userid + ", createdby=" + createdby + ", actionids=" + actionids + "]";
	}
	
	
}
