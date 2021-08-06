package in.co.srdt.unif.model.create;


public class CreateLocation {

	    private long actionid;
	    private long locationid;
	    private String effectstartdate;
	    private String effectenddate;
	    private String name;
	    private String description;
	    private String code;
	    private int addresstype;
	    private int country;
	    private int state;
	    private int timezone;
	    private String addressline1;
	    private String addressline2;
	    private String addressline3;
	    private String citytown;
	    private int pincode;
	    private String emailid;
	    private String phoneno;
	    private String status;
	    private String additionalatr;
	    
		public CreateLocation() {
			
		}
		
		public CreateLocation(long actionid, long locationid, String effectstartdate, String effectenddate, String name,
				String description, String code, int addresstype, int country, int state, int timezone,
				String addressline1, String addressline2, String addressline3, String citytown, int pincode,
				String emailid, String phoneno, String status,String additionalatr) {
			super();
			this.actionid = actionid;
			this.locationid = locationid;
			this.effectstartdate = effectstartdate;
			this.effectenddate = effectenddate;
			this.name = name;
			this.description = description;
			this.code = code;
			this.addresstype = addresstype;
			this.country = country;
			this.state = state;
			this.timezone = timezone;
			this.addressline1 = addressline1;
			this.addressline2 = addressline2;
			this.addressline3 = addressline3;
			this.citytown = citytown;
			this.pincode = pincode;
			this.emailid = emailid;
			this.phoneno = phoneno;
			this.status = status;
			this.additionalatr=additionalatr;
		}

		public long getActionid() {
			return actionid;
		}
		public void setActionid(long actionid) {
			this.actionid = actionid;
		}
		public long getLocationid() {
			return locationid;
		}
		public void setLocationid(int locationid) {
			this.locationid = locationid;
		}
		public String getEffectstartdate() {
			return effectstartdate;
		}
		public void setEffectstartdate(String effectstartdate) {
			this.effectstartdate = effectstartdate;
		}
		public String getEffectenddate() {
			return effectenddate;
		}
		public void setEffectenddate(String effectenddate) {
			this.effectenddate = effectenddate;
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
		public int getAddresstype() {
			return addresstype;
		}
		public void setAddresstype(int addresstype) {
			this.addresstype = addresstype;
		}
		public int getCountry() {
			return country;
		}
		public void setCountry(int country) {
			this.country = country;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public int getTimezone() {
			
			return timezone != 0 ? timezone : 0;
		}
		public void setTimezone(int timezone) {
			this.timezone = timezone;
			
		}
		public String getAddressline1() {
			return addressline1;
		}
		public void setAddressline1(String addressline1) {
			this.addressline1 = addressline1;
		}
		public String getAddressline2() {
			return addressline2;
		}
		public void setAddressline2(String addressline2) {
			this.addressline2 = addressline2;
		}
		public String getAddressline3() {
			return addressline3;
		}
		public void setAddressline3(String addressline3) {
			this.addressline3 = addressline3;
		}
		public String getCitytown() {
			return citytown;
		}
		public void setCitytown(String citytown) {
			this.citytown = citytown;
		}
		public int getPincode() {
			return pincode;
		}
		public void setPincode(int pincode) {
			this.pincode = pincode;
		}
		public String getEmailid() {
			return emailid;
		}
		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}
		public String getPhoneno() {
			return phoneno;
		}
		public void setPhoneno(String phoneno) {
			this.phoneno = phoneno;
		}
		public String getStatus() {
			return status != null ? status : "";
		}
		public void setStatus(String status) {
			this.status = status;
		}	
		
		public String getAddtionalatr() {
			return additionalatr;
		}

		public void setAdditionalatr(String additionalatr) {
			this.additionalatr = additionalatr;
		}
		
}
