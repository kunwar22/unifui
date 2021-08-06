package in.co.srdt.unif.model.reports;

public class EPFModel {
	
	private String personnumber;
	private String personname;
	private String natureofempl;
	private String position;
	private String workingday;
	private double grosssalary_c1;
	private double basicandfp_d;
	private double arrbasic_d1;
	private double dearnessallowance_e;
	private double arrdearnessallowance_e1;
	private double total_f;
	private double arrearpayment_g;
	private double employeededuction_h;
	private double employeededuction_i;
	private double employeevpf_i1;
	private double controfemployee_j;
	private double controfemployee_k;
	private double admincharge_l;
	private double total_m;
	
	public EPFModel() {
		super();
	}

	public EPFModel(String personnumber, String personname, String natureofempl, String position, String workingday,
			double grosssalary_c1, double basicandfp_d, double arrbasic_d1, double dearnessallowance_e,
			double arrdearnessallowance_e1, double total_f, double arrearpayment_g, double employeededuction_h,
			double employeededuction_i, double employeevpf_i1, double controfemployee_j, double controfemployee_k,
			double admincharge_l, double total_m) {
		super();
		this.personnumber = personnumber;
		this.personname = personname;
		this.natureofempl = natureofempl;
		this.position = position;
		this.workingday = workingday;
		this.grosssalary_c1 = grosssalary_c1;
		this.basicandfp_d = basicandfp_d;
		this.arrbasic_d1 = arrbasic_d1;
		this.dearnessallowance_e = dearnessallowance_e;
		this.arrdearnessallowance_e1 = arrdearnessallowance_e1;
		this.total_f = total_f;
		this.arrearpayment_g = arrearpayment_g;
		this.employeededuction_h = employeededuction_h;
		this.employeededuction_i = employeededuction_i;
		this.employeevpf_i1 = employeevpf_i1;
		this.controfemployee_j = controfemployee_j;
		this.controfemployee_k = controfemployee_k;
		this.admincharge_l = admincharge_l;
		this.total_m = total_m;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getNatureofempl() {
		return natureofempl;
	}

	public void setNatureofempl(String natureofempl) {
		this.natureofempl = natureofempl;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWorkingday() {
		return workingday;
	}

	public void setWorkingday(String workingday) {
		this.workingday = workingday;
	}

	public double getGrosssalary_c1() {
		return grosssalary_c1;
	}

	public void setGrosssalary_c1(double grosssalary_c1) {
		this.grosssalary_c1 = grosssalary_c1;
	}

	public double getBasicandfp_d() {
		return basicandfp_d;
	}

	public void setBasicandfp_d(double basicandfp_d) {
		this.basicandfp_d = basicandfp_d;
	}

	public double getArrbasic_d1() {
		return arrbasic_d1;
	}

	public void setArrbasic_d1(double arrbasic_d1) {
		this.arrbasic_d1 = arrbasic_d1;
	}

	public double getDearnessallowance_e() {
		return dearnessallowance_e;
	}

	public void setDearnessallowance_e(double dearnessallowance_e) {
		this.dearnessallowance_e = dearnessallowance_e;
	}

	public double getArrdearnessallowance_e1() {
		return arrdearnessallowance_e1;
	}

	public void setArrdearnessallowance_e1(double arrdearnessallowance_e1) {
		this.arrdearnessallowance_e1 = arrdearnessallowance_e1;
	}

	public double getTotal_f() {
		return total_f;
	}

	public void setTotal_f(double total_f) {
		this.total_f = total_f;
	}

	public double getArrearpayment_g() {
		return arrearpayment_g;
	}

	public void setArrearpayment_g(double arrearpayment_g) {
		this.arrearpayment_g = arrearpayment_g;
	}

	public double getEmployeededuction_h() {
		return employeededuction_h;
	}

	public void setEmployeededuction_h(double employeededuction_h) {
		this.employeededuction_h = employeededuction_h;
	}

	public double getEmployeededuction_i() {
		return employeededuction_i;
	}

	public void setEmployeededuction_i(double employeededuction_i) {
		this.employeededuction_i = employeededuction_i;
	}

	public double getEmployeevpf_i1() {
		return employeevpf_i1;
	}

	public void setEmployeevpf_i1(double employeevpf_i1) {
		this.employeevpf_i1 = employeevpf_i1;
	}

	public double getControfemployee_j() {
		return controfemployee_j;
	}

	public void setControfemployee_j(double controfemployee_j) {
		this.controfemployee_j = controfemployee_j;
	}

	public double getControfemployee_k() {
		return controfemployee_k;
	}

	public void setControfemployee_k(double controfemployee_k) {
		this.controfemployee_k = controfemployee_k;
	}

	public double getAdmincharge_l() {
		return admincharge_l;
	}

	public void setAdmincharge_l(double admincharge_l) {
		this.admincharge_l = admincharge_l;
	}

	public double getTotal_m() {
		return total_m;
	}

	public void setTotal_m(double total_m) {
		this.total_m = total_m;
	}

	@Override
	public String toString() {
		return "EPFModel [personnumber=" + personnumber + ", personname=" + personname + ", natureofempl="
				+ natureofempl + ", position=" + position + ", workingday=" + workingday + ", grosssalary_c1="
				+ grosssalary_c1 + ", basicandfp_d=" + basicandfp_d + ", arrbasic_d1=" + arrbasic_d1
				+ ", dearnessallowance_e=" + dearnessallowance_e + ", arrdearnessallowance_e1="
				+ arrdearnessallowance_e1 + ", total_f=" + total_f + ", arrearpayment_g=" + arrearpayment_g
				+ ", employeededuction_h=" + employeededuction_h + ", employeededuction_i=" + employeededuction_i
				+ ", employeevpf_i1=" + employeevpf_i1 + ", controfemployee_j=" + controfemployee_j
				+ ", controfemployee_k=" + controfemployee_k + ", admincharge_l=" + admincharge_l + ", total_m="
				+ total_m + "]";
	}

}
