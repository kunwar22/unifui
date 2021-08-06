package in.co.srdt.unif.model.newperson;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

public class Newperson {
	
	private Long personaltblactionid;

	private String personid;

	private String personnumber;

	private String legalentityname;
	private String buname;
	
	private String gradename;

	private String jobname;

	private String  departname;

	private String locationname;
	private String positionname;
	private String naturename;
	private String effectiveenddate="4712-12-31";

	private  String createdby;
	private  String updatedby;

	SalaryBasisDetails salarybasis=new SalaryBasisDetails();


	PersonalRecord personalrecord = new PersonalRecord();
	JobData jobdata = new JobData();
	// AddressDetails addressdetails = new AddressDetails();
	ReligionDetails religiondetails = new ReligionDetails();
	MaritalDetails maritaldetails = new MaritalDetails();
	UPMRCAdditionalDetails upmrcaddon= new UPMRCAdditionalDetails();
	BankDetails bankaccountdetails= new BankDetails();


	private List<@Min(value = 1, message = "Please select National Id.") NationalDetails> nationaldetails;
	private List<ContactDetails> contactdetails;
	private List<EmailDetails> emaildetails;

	private List<AddressDetails> addressdetails;
	private List<ManagerDetails> managertypes;

	public List<ManagerDetails> getManagertypes() {
		return managertypes;
	}

	public void setManagertypes(List<ManagerDetails> managertypes) {
		this.managertypes = managertypes;
	}

	// private List<JobData> jobdt;
	public Newperson() {
		nationaldetails = new ArrayList<>();
		nationaldetails.add(new NationalDetails());

		contactdetails = new ArrayList<>();
		contactdetails.add(new ContactDetails());

		emaildetails = new ArrayList<>();
		emaildetails.add(new EmailDetails());

		addressdetails = new ArrayList<>();
		addressdetails.add(new AddressDetails());

		managertypes = new ArrayList<>();
		managertypes.add(new ManagerDetails());
		// jobdt = new ArrayList<>();
		// jobdt.add(new JobData());

		// national.remove(0);

	}

	public BankDetails getBankaccountdetails() {
		return bankaccountdetails;
	}

	public void setBankaccountdetails(BankDetails bankaccountdetails) {
		this.bankaccountdetails = bankaccountdetails;
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

	public String getEffectiveenddate() {
		return effectiveenddate;
	}

	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}

	public String getNaturename() {
		return naturename;
	}

	public void setNaturename(String naturename) {
		this.naturename = naturename;
	}

	public SalaryBasisDetails getSalarybasis() {
		return salarybasis;
	}

	public void setSalarybasis(SalaryBasisDetails salarybasis) {
		this.salarybasis = salarybasis;
	}

	public UPMRCAdditionalDetails getUpmrcaddon() {
		return upmrcaddon;
	}

	public void setUpmrcaddon(UPMRCAdditionalDetails upmrcaddon) {
		this.upmrcaddon = upmrcaddon;
	}

	public Long getPersonaltblactionid() {
		return personaltblactionid;
	}

	public void setPersonaltblactionid(Long personaltblactionid) {
		this.personaltblactionid = personaltblactionid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public PersonalRecord getPersonalrecord() {
		return personalrecord;
	}

	public void setPersonalrecord(PersonalRecord personalrecord) {
		this.personalrecord = personalrecord;
	}

	public JobData getJobdata() {
		return jobdata;
	}

	public void setJobdata(JobData jobdata) {
		this.jobdata = jobdata;
	}

	public List<AddressDetails> getAddressdetails() {
		return addressdetails;
	}

	public void setAddressdetails(List<AddressDetails> addressdetails) {
		this.addressdetails = addressdetails;
	}

	public ReligionDetails getReligiondetails() {
		return religiondetails;
	}

	public void setReligiondetails(ReligionDetails religiondetails) {
		this.religiondetails = religiondetails;
	}

	public MaritalDetails getMaritaldetails() {
		return maritaldetails;
	}

	public void setMaritaldetails(MaritalDetails maritaldetails) {
		this.maritaldetails = maritaldetails;
	}

	public List<NationalDetails> getNationaldetails() {
		return nationaldetails;
	}

	public void setNationaldetails(List<NationalDetails> nationaldetails) {
		this.nationaldetails = nationaldetails;
	}

	public List<ContactDetails> getContactdetails() {
		return contactdetails;
	}

	public void setContactdetails(List<ContactDetails> contactdetails) {
		this.contactdetails = contactdetails;
	}

	public List<EmailDetails> getEmaildetails() {
		return emaildetails;
	}

	public void setEmaildetails(List<EmailDetails> emaildetails) {
		this.emaildetails = emaildetails;
	}

	public String getLegalentityname() {
		return legalentityname;
	}

	public void setLegalentityname(String legalentityname) {
		this.legalentityname = legalentityname;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getDepartname() {
		return departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public String getPositionname() {
		return positionname;
	}

	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}

	public String getBuname() {
		return buname;
	}

	public void setBuname(String buname) {
		this.buname = buname;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	@Override
	public String toString() {
		return "Newperson{" +
				"personaltblactionid=" + personaltblactionid +
				", personid='" + personid + '\'' +
				", personnumber='" + personnumber + '\'' +
				", legalentityname='" + legalentityname + '\'' +
				", buname='" + buname + '\'' +
				", gradename='" + gradename + '\'' +
				", jobname='" + jobname + '\'' +
				", departname='" + departname + '\'' +
				", locationname='" + locationname + '\'' +
				", positionname='" + positionname + '\'' +
				", naturename='" + naturename + '\'' +
				", effectiveenddate='" + effectiveenddate + '\'' +
				", createdby='" + createdby + '\'' +
				", updatedby='" + updatedby + '\'' +
				", salarybasis=" + salarybasis +
				", personalrecord=" + personalrecord +
				", jobdata=" + jobdata +
				", religiondetails=" + religiondetails +
				", maritaldetails=" + maritaldetails +
				", upmrcaddon=" + upmrcaddon +
				", nationaldetails=" + nationaldetails +
				", contactdetails=" + contactdetails +
				", emaildetails=" + emaildetails +
				", addressdetails=" + addressdetails +
				", managertypes=" + managertypes +
				'}';
	}
}