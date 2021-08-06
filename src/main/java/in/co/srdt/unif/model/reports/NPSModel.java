package in.co.srdt.unif.model.reports;

public class NPSModel {	
		
		private String personnumber;
		private String personname;
		private String natureofempl;
		private String position;
		private String prannumber;
		private String deptname;
		private String gradename;
		private int snps;
		private int nps;
		
		public NPSModel() {
			super();
		}

		public NPSModel(String personnumber, String personname, String natureofempl, String position, String prannumber,
				String deptname, String gradename, int snps, int nps) {
			super();
			this.personnumber = personnumber;
			this.personname = personname;
			this.natureofempl = natureofempl;
			this.position = position;
			this.prannumber = prannumber;
			this.deptname = deptname;
			this.gradename = gradename;
			this.snps = snps;
			this.nps = nps;
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

		public String getPrannumber() {
			return prannumber;
		}

		public void setPrannumber(String prannumber) {
			this.prannumber = prannumber;
		}

		public String getDeptname() {
			return deptname;
		}

		public void setDeptname(String deptname) {
			this.deptname = deptname;
		}

		public String getGradename() {
			return gradename;
		}

		public void setGradename(String gradename) {
			this.gradename = gradename;
		}

		public int getSnps() {
			return snps;
		}

		public void setSnps(int snps) {
			this.snps = snps;
		}

		public int getNps() {
			return nps;
		}

		public void setNps(int nps) {
			this.nps = nps;
		}

		@Override
		public String toString() {
			return "NPSModel [personnumber=" + personnumber + ", personname=" + personname + ", natureofempl="
					+ natureofempl + ", position=" + position + ", prannumber=" + prannumber + ", deptname=" + deptname
					+ ", gradename=" + gradename + ", snps=" + snps + ", nps=" + nps + "]";
		}
		
	}