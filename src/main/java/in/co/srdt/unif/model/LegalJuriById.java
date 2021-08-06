package in.co.srdt.unif.model;

public class LegalJuriById {
	private Long legaljurisdictionid;
	private String identifyingjurisdiction;
	private String tinorpan;
	public LegalJuriById() {
		super();
	}
	public LegalJuriById(Long legaljurisdictionid, String identifyingjurisdiction, String tinorpan) {
		super();
		this.legaljurisdictionid = legaljurisdictionid;
		this.identifyingjurisdiction = identifyingjurisdiction;
		this.tinorpan = tinorpan;
	}
	public Long getLegaljurisdictionid() {
		return legaljurisdictionid;
	}
	public void setLegaljurisdictionid(Long legaljurisdictionid) {
		this.legaljurisdictionid = legaljurisdictionid;
	}
	public String getIdentifyingjurisdiction() {
		return identifyingjurisdiction;
	}
	public void setIdentifyingjurisdiction(String identifyingjurisdiction) {
		this.identifyingjurisdiction = identifyingjurisdiction;
	}
	public String getTinorpan() {
		return tinorpan;
	}
	public void setTinorpan(String tinorpan) {
		this.tinorpan = tinorpan;
	}
	
	
	
}
