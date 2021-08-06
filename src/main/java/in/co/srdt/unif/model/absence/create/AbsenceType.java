package in.co.srdt.unif.model.absence.create;

public class AbsenceType {

	private long actionid;// ato generated id

	private long absencetypeid;// ato generated id

	private String code;

	private String name;

	private String description;

	private long accrualfrequncy;

	private String accrualfrequncyname;

	private long accrualon;

	private String accrualonname;

	private String isprorate; // set Yes/No

	private String numberofdays;

	private long eligibilityid;

	private String eligibilityidname;

	// new from here
	private String accrualtype;

	private String carryforward;// set Yes/No

	private String carryforwardlimit;

	private String incash;// set Yes/No

	private String incashabledays;

	private String islapse;// set Yes/No

	private String minlenthofservice;

	private String maxtake;

	private String maxnegativebalance;

	// Update Modele from here on 12-08-2020
	private String partofweeklyoff;// set Yes/No

	private String partofholiday;// set Yes/No

	private String partofbeforeweeklyoff;// set Yes/No
	
	private String partofbeforeholiday;// set Yes/No
	
	private String partofafterweeklyoff;// set Yes/No

	private String partofafterholiday;// set Yes/No
	
	private String status;
	
	private String effectstartdate;
	
	private long repeatingperiodid;

	private String repeatingperiodname;


	public AbsenceType() {

	}


	public AbsenceType(long actionid, long absencetypeid, String code, String name, String description,
			long accrualfrequncy, String accrualfrequncyname, long accrualon, String accrualonname, String isprorate,
			String numberofdays, long eligibilityid, String eligibilityidname, String accrualtype, String carryforward,
			String carryforwardlimit, String incash, String incashabledays, String islapse, String minlenthofservice,
			String maxtake, String maxnegativebalance, String partofweeklyoff, String partofholiday,
			String partofbeforeweeklyoff, String partofbeforeholiday, String partofafterweeklyoff,
			String partofafterholiday, String status, String effectstartdate, long repeatingperiodid,
			String repeatingperiodname) {
		super();
		this.actionid = actionid;
		this.absencetypeid = absencetypeid;
		this.code = code;
		this.name = name;
		this.description = description;
		this.accrualfrequncy = accrualfrequncy;
		this.accrualfrequncyname = accrualfrequncyname;
		this.accrualon = accrualon;
		this.accrualonname = accrualonname;
		this.isprorate = isprorate;
		this.numberofdays = numberofdays;
		this.eligibilityid = eligibilityid;
		this.eligibilityidname = eligibilityidname;
		this.accrualtype = accrualtype;
		this.carryforward = carryforward;
		this.carryforwardlimit = carryforwardlimit;
		this.incash = incash;
		this.incashabledays = incashabledays;
		this.islapse = islapse;
		this.minlenthofservice = minlenthofservice;
		this.maxtake = maxtake;
		this.maxnegativebalance = maxnegativebalance;
		this.partofweeklyoff = partofweeklyoff;
		this.partofholiday = partofholiday;
		this.partofbeforeweeklyoff = partofbeforeweeklyoff;
		this.partofbeforeholiday = partofbeforeholiday;
		this.partofafterweeklyoff = partofafterweeklyoff;
		this.partofafterholiday = partofafterholiday;
		this.status = status;
		this.effectstartdate = effectstartdate;
		this.repeatingperiodid = repeatingperiodid;
		this.repeatingperiodname = repeatingperiodname;
	}


	public long getActionid() {
		return actionid;
	}


	public void setActionid(long actionid) {
		this.actionid = actionid;
	}


	public long getAbsencetypeid() {
		return absencetypeid;
	}


	public void setAbsencetypeid(long absencetypeid) {
		this.absencetypeid = absencetypeid;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
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


	public long getAccrualfrequncy() {
		return accrualfrequncy;
	}


	public void setAccrualfrequncy(long accrualfrequncy) {
		this.accrualfrequncy = accrualfrequncy;
	}


	public String getAccrualfrequncyname() {
		return accrualfrequncyname;
	}


	public void setAccrualfrequncyname(String accrualfrequncyname) {
		this.accrualfrequncyname = accrualfrequncyname;
	}


	public long getAccrualon() {
		return accrualon;
	}


	public void setAccrualon(long accrualon) {
		this.accrualon = accrualon;
	}


	public String getAccrualonname() {
		return accrualonname;
	}


	public void setAccrualonname(String accrualonname) {
		this.accrualonname = accrualonname;
	}


	public String getIsprorate() {
		return isprorate;
	}


	public void setIsprorate(String isprorate) {
		this.isprorate = isprorate;
	}


	public String getNumberofdays() {
		return numberofdays;
	}


	public void setNumberofdays(String numberofdays) {
		this.numberofdays = numberofdays;
	}


	public long getEligibilityid() {
		return eligibilityid;
	}


	public void setEligibilityid(long eligibilityid) {
		this.eligibilityid = eligibilityid;
	}


	public String getEligibilityidname() {
		return eligibilityidname;
	}


	public void setEligibilityidname(String eligibilityidname) {
		this.eligibilityidname = eligibilityidname;
	}


	public String getAccrualtype() {
		return accrualtype;
	}


	public void setAccrualtype(String accrualtype) {
		this.accrualtype = accrualtype;
	}


	public String getCarryforward() {
		return carryforward;
	}


	public void setCarryforward(String carryforward) {
		this.carryforward = carryforward;
	}


	public String getCarryforwardlimit() {
		return carryforwardlimit;
	}


	public void setCarryforwardlimit(String carryforwardlimit) {
		this.carryforwardlimit = carryforwardlimit;
	}


	public String getIncash() {
		return incash;
	}


	public void setIncash(String incash) {
		this.incash = incash;
	}


	public String getIncashabledays() {
		return incashabledays;
	}


	public void setIncashabledays(String incashabledays) {
		this.incashabledays = incashabledays;
	}


	public String getIslapse() {
		return islapse;
	}


	public void setIslapse(String islapse) {
		this.islapse = islapse;
	}


	public String getMinlenthofservice() {
		return minlenthofservice;
	}


	public void setMinlenthofservice(String minlenthofservice) {
		this.minlenthofservice = minlenthofservice;
	}


	public String getMaxtake() {
		return maxtake;
	}


	public void setMaxtake(String maxtake) {
		this.maxtake = maxtake;
	}


	public String getMaxnegativebalance() {
		return maxnegativebalance;
	}


	public void setMaxnegativebalance(String maxnegativebalance) {
		this.maxnegativebalance = maxnegativebalance;
	}


	public String getPartofweeklyoff() {
		return partofweeklyoff;
	}


	public void setPartofweeklyoff(String partofweeklyoff) {
		this.partofweeklyoff = partofweeklyoff;
	}


	public String getPartofholiday() {
		return partofholiday;
	}


	public void setPartofholiday(String partofholiday) {
		this.partofholiday = partofholiday;
	}


	public String getPartofbeforeweeklyoff() {
		return partofbeforeweeklyoff;
	}


	public void setPartofbeforeweeklyoff(String partofbeforeweeklyoff) {
		this.partofbeforeweeklyoff = partofbeforeweeklyoff;
	}


	public String getPartofbeforeholiday() {
		return partofbeforeholiday;
	}


	public void setPartofbeforeholiday(String partofbeforeholiday) {
		this.partofbeforeholiday = partofbeforeholiday;
	}


	public String getPartofafterweeklyoff() {
		return partofafterweeklyoff;
	}


	public void setPartofafterweeklyoff(String partofafterweeklyoff) {
		this.partofafterweeklyoff = partofafterweeklyoff;
	}


	public String getPartofafterholiday() {
		return partofafterholiday;
	}


	public void setPartofafterholiday(String partofafterholiday) {
		this.partofafterholiday = partofafterholiday;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getEffectstartdate() {
		return effectstartdate;
	}


	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}


	public long getRepeatingperiodid() {
		return repeatingperiodid;
	}


	public void setRepeatingperiodid(long repeatingperiodid) {
		this.repeatingperiodid = repeatingperiodid;
	}


	public String getRepeatingperiodname() {
		return repeatingperiodname;
	}


	public void setRepeatingperiodname(String repeatingperiodname) {
		this.repeatingperiodname = repeatingperiodname;
	}


	@Override
	public String toString() {
		return "AbsenceType [actionid=" + actionid + ", absencetypeid=" + absencetypeid + ", code=" + code + ", name="
				+ name + ", description=" + description + ", accrualfrequncy=" + accrualfrequncy
				+ ", accrualfrequncyname=" + accrualfrequncyname + ", accrualon=" + accrualon + ", accrualonname="
				+ accrualonname + ", isprorate=" + isprorate + ", numberofdays=" + numberofdays + ", eligibilityid="
				+ eligibilityid + ", eligibilityidname=" + eligibilityidname + ", accrualtype=" + accrualtype
				+ ", carryforward=" + carryforward + ", carryforwardlimit=" + carryforwardlimit + ", incash=" + incash
				+ ", incashabledays=" + incashabledays + ", islapse=" + islapse + ", minlenthofservice="
				+ minlenthofservice + ", maxtake=" + maxtake + ", maxnegativebalance=" + maxnegativebalance
				+ ", partofweeklyoff=" + partofweeklyoff + ", partofholiday=" + partofholiday
				+ ", partofbeforeweeklyoff=" + partofbeforeweeklyoff + ", partofbeforeholiday=" + partofbeforeholiday
				+ ", partofafterweeklyoff=" + partofafterweeklyoff + ", partofafterholiday=" + partofafterholiday
				+ ", status=" + status + ", effectstartdate=" + effectstartdate + ", repeatingperiodid="
				+ repeatingperiodid + ", repeatingperiodname=" + repeatingperiodname + "]";
	}
	
	

	

}