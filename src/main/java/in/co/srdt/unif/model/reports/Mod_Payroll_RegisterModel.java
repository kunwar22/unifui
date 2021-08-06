package in.co.srdt.unif.model.reports;

import java.util.LinkedHashMap;
import java.util.List;

public class Mod_Payroll_RegisterModel {

	private List<LinkedHashMap<String,String>> data;
	private String cols;
	private String cur;
	private String pre;
	private String busid;
	
	
	public Mod_Payroll_RegisterModel() {
		super();
	}

	
	public Mod_Payroll_RegisterModel(List<LinkedHashMap<String, String>> data, String cols, String cur, String pre,
			String busid) {
		super();
		this.data = data;
		this.cols = cols;
		this.cur = cur;
		this.pre = pre;
		this.busid = busid;
	}


	public List<LinkedHashMap<String, String>> getData() {
		return data;
	}




	public void setData(List<LinkedHashMap<String, String>> data) {
		this.data = data;
	}


	public String getCols() {
		return cols;
	}
	public void setCols(String cols) {
		this.cols = cols;
	}


	public String getCur() {
		return cur;
	}


	public void setCur(String cur) {
		this.cur = cur;
	}


	public String getPre() {
		return pre;
	}


	public void setPre(String pre) {
		this.pre = pre;
	}

	public String getBusid() {
		return busid;
	}


	public void setBusid(String busid) {
		this.busid = busid;
	}


	@Override
	public String toString() {
		return "Mod_Payroll_RegisterModel [data=" + data + ", cols=" + cols + ", cur=" + cur + ", pre=" + pre
				+ ", busid=" + busid + "]";
	}


}
