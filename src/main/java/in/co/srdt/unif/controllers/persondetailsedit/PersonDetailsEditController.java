package in.co.srdt.unif.controllers.persondetailsedit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.srdt.unif.model.payroll.CalendarPeriod;
import in.co.srdt.unif.model.payslip.PayrollData;
import in.co.srdt.unif.model.payslip.PayrollSummery;
import in.co.srdt.unif.model.salarycard.PayrollSalCardElements;
import in.co.srdt.unif.model.salarycard.PayrollSalaryCard;
import in.co.srdt.unif.model.user.absence.CommonDescription;
import in.co.srdt.unif.model.user.corehr.DocumentView;
import in.co.srdt.unif.model.user.corehr.UserDocument;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.controllers.reports.GenerateExcelFiles;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.personmanagement.AddressDetails;
import in.co.srdt.unif.model.personmanagement.BiographicalInfo;
import in.co.srdt.unif.model.personmanagement.ContactDetailsWrapper;
import in.co.srdt.unif.model.personmanagement.EmailDetails;
import in.co.srdt.unif.model.personmanagement.EmailDetailsWrapper;
import in.co.srdt.unif.model.personmanagement.MaritalDetails;
import in.co.srdt.unif.model.personmanagement.NationalDetails;
import in.co.srdt.unif.model.personmanagement.NationalDetailsWrapper;
import in.co.srdt.unif.model.personmanagement.PersonalRecordManageMaster;
import in.co.srdt.unif.model.personmanagement.PersonalRecordMng;
import in.co.srdt.unif.model.reports.PayrollSalaryCards;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/personedit")
public class PersonDetailsEditController {
	
	private final GenerateExcelFiles genexfiles = new GenerateExcelFiles();
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationGateway appgateway;

	@Autowired
	private HttpHeaders headers;

	public String opentab="NAME_TAB";
	
	public String pnumber="";

	/* Variables for POOJA's CONTROLLER */

	double earPrev = 0;
	double earCur = 0;
	double earTot = 0;
	double dedPrev = 0;
	double dedCur = 0;
	double dedTot = 0;
	double empPrev = 0;
	double empCur = 0;
	double empTot = 0;
	double perPrev = 0;
	double perCur = 0;
	double perTot = 0;
	double netPrev = 0;
	double netCur = 0;
	double netTot = 0;

	double deduction = 0;
	double earning = 0;
	double contribution = 0;
	double net = 0;
	double n1,n2=0;

	String months = "";
	String paiddays = "";
	/* Variables for POOJA's CONTROLLER */


	@RequestMapping("/employeedetail/{personnumber}/{personname}/{personid}")
	public String managepersonedit(@PathVariable("personnumber")String personnumber,@PathVariable("personname")String personname,@PathVariable("personid")String personid, Model model, HttpServletRequest req) {
		//System.out.println("Inside Employee Detail Master");
		System.out.println("PersonID :: "+personid);
		//redAttributes.addFlashAttribute("pnum", personnumber);
		req.getSession().setAttribute("pnum", personnumber);
		req.getSession().setAttribute("pid", personid);
		pnumber=personnumber;
		personname=personname.replace("_"," ");
		///System.out.println(personnumber+" :::: "+personname);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+pnumber+"/Photo";
		ResponseEntity<CommonDescription> response = restTemplate.exchange(urlpropic, HttpMethod.GET, request, CommonDescription.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			comdes = response.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("pname",personname);
		model.addAttribute("pno",personnumber);


		return "fragments/EmployeeDetail_ :: employeedetails_";
	}
	
	@RequestMapping("/managepersonedit")
	public String managepersonedit(Model model, HttpServletRequest req, SingleResponseModel res) {
		//System.out.println("Inside Person Edit Controller");
		//
		
		PersonalRecordManageMaster personallrecords=null;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		Login login = (Login)req.getSession().getAttribute("login");
		//System.out.println(login.getEmplid());
		
		String urlgetpersonallrecord = appgateway.getAppgateway() + "/PersonData/PersonalRecord/getPersonalRecordByPersonNo/"+pnumber;
		ResponseEntity<PersonalRecordManageMaster> response = restTemplate.exchange(urlgetpersonallrecord, HttpMethod.GET, request, PersonalRecordManageMaster.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			personallrecords = response.getBody();
		}
		
		CommonLOV[] gender = null;
		String urlgender = appgateway.getAppgateway() + "/General/loadGender";
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlgender, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			gender = response1.getBody();
		}
		
		CommonLOV[] titleLov = null;
		String urltitle = appgateway.getAppgateway() + "/General/loadTitle";
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urltitle, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			titleLov = response2.getBody();
		}
		
		CommonLOV[] country = null;
		String urlcountry = appgateway.getAppgateway() + "/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response3 = restTemplate.exchange(urlcountry, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response3.getStatusCode() == HttpStatus.OK) {
			country = response3.getBody();
		}
		
		CommonLOV[] state = null;
		String urlstate = appgateway.getAppgateway() + "/State/getAllStateByCountry/1";
		ResponseEntity<CommonLOV[]> response4 = restTemplate.exchange(urlstate, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			state = response4.getBody();
		}
		
		CommonLOV[] addresstype = null;
		String urladdresstype = appgateway.getAppgateway() + "/General/loadAddressTypeObject";
		ResponseEntity<CommonLOV[]> response5 = restTemplate.exchange(urladdresstype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response5.getStatusCode() == HttpStatus.OK) {
			addresstype = response5.getBody();
		}
		
		CommonLOV[] phone_countrycode=null;
		String urlcntrycode = appgateway.getAppgateway() + "/Country/getAllCountryPhoneCode";
		ResponseEntity<CommonLOV[]> response6 = restTemplate.exchange(urlcntrycode, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response6.getStatusCode() == HttpStatus.OK) {
			phone_countrycode = response6.getBody();
		}

		CommonLOV[] phonetype=null;
		String urlphonetype = appgateway.getAppgateway() + "/General/loadPhoneType";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlphonetype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response7.getStatusCode() == HttpStatus.OK) {
			phonetype = response7.getBody();
		}
		
		CommonLOV[] emailtype=null;
		String urlemailtype = appgateway.getAppgateway() + "/General/loadEmailType";
		ResponseEntity<CommonLOV[]> response8 = restTemplate.exchange(urlemailtype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response8.getStatusCode() == HttpStatus.OK) {
			emailtype = response8.getBody();
		}
		
		CommonLOV[] maritalstatus=null;
		String urlmarital = appgateway.getAppgateway() + "/General/loadMaritalStatus";
		ResponseEntity<CommonLOV[]> response9 = restTemplate.exchange(urlmarital, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response9.getStatusCode() == HttpStatus.OK) {
			maritalstatus = response9.getBody();
		}
		
		CommonLOV[] nattype=null;
		String urlnattype = appgateway.getAppgateway() + "/NatinalId/getAllNatinalId";
		ResponseEntity<CommonLOV[]> response10 = restTemplate.exchange(urlnattype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response10.getStatusCode() == HttpStatus.OK) {
			nattype = response10.getBody();
		}
		
		CommonLOV[] bloodtype=null;
		String urlbloodtype = appgateway.getAppgateway() + "/General/loadBloodGroups";
		ResponseEntity<CommonLOV[]> response11 = restTemplate.exchange(urlbloodtype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response11.getStatusCode() == HttpStatus.OK) {
			bloodtype = response11.getBody();
		}
		
		
		//System.out.println("All Personal Record :: "+personallrecords.toString());
		model.addAttribute("bloodtype",bloodtype);
		model.addAttribute("gender", gender);
		model.addAttribute("title", titleLov);
		model.addAttribute("country", country);
		model.addAttribute("state", state);
		model.addAttribute("addresstype", addresstype);
		model.addAttribute("countrycode", phone_countrycode);
		model.addAttribute("phonetype", phonetype);
		model.addAttribute("emailtype", emailtype);
		model.addAttribute("status", Status.values());
		model.addAttribute("maritalstatus", maritalstatus);
		model.addAttribute("nattype", nattype);
		model.addAttribute("opentab",opentab);
		model.addAttribute("personallrecords",personallrecords);
		model.addAttribute("pno",pnumber);
		model.addAttribute("responsemsg",res);
		opentab="NAME_TAB";
		return "fragments/personEdit :: personEdit";
	}
	
	
	@RequestMapping(value= "/nametabcorrectupdate", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String nametabcorrectupdate(PersonalRecordMng personalRecordMng, Model model,HttpServletRequest req, String tab)
	{
		//System.out.println(tab);
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PersonalRecordMng> request = new HttpEntity<PersonalRecordMng>(personalRecordMng, headers);
		String url="";
		if(personalRecordMng.getMode().equals("Correct")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/correctPersonDetails";
		}
		if(personalRecordMng.getMode().equals("Update")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/updatePersonDetails";
		}
		Login login = (Login)req.getSession().getAttribute("login");
		personalRecordMng.setCreatedby(login.getEmplid());
		personalRecordMng.setUpdatedby(login.getEmplid());
		//System.out.println("Correct Data :: "+personalRecordMng.toString());
		
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) { 
			res = response.getBody(); 
		}
		opentab=tab;
		//String method=;
		
		return managepersonedit(model,req,res);
		/* return "fragments/enterprise/create/createPosition:: createPosition"; */

	}
	
	@PostMapping(value= "/addresstabcorrectupdate", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String adddrtabcorrectupdate(String mode, AddressDetails addressDetails, Model model,HttpServletRequest req, String tabaddr)
	{
		//System.out.println("Inside Address Correct Update");
		//System.out.println(tabaddr);
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AddressDetails> request = new HttpEntity<AddressDetails>(addressDetails, headers);
		String url="";
		
		Login login = (Login)req.getSession().getAttribute("login");
		addressDetails.setCreatedby(login.getEmplid());
		addressDetails.setUpdatedby(login.getEmplid());
		
		//System.out.println("Correct Data :: "+addressDetails.toString());
		
		if(mode.equals("Correct")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/correctSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.POST, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}
		if(mode.equals("Update")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/updateSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}
		
		opentab=tabaddr;
		//String method=;
		
		return managepersonedit(model,req,res);
		/* return "fragments/enterprise/create/createPosition:: createPosition"; */

	}
	
	@PostMapping(value= "/phonecorrect", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String phonecorrect(ContactDetailsWrapper contactDetails, Model model,HttpServletRequest req, String tab)
	{
		//System.out.println("Inside Phone Correct ");
		//System.out.println(tab);
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<List<in.co.srdt.unif.model.personmanagement.ContactDetails>> request = new HttpEntity<List<in.co.srdt.unif.model.personmanagement.ContactDetails>>(contactDetails.getContactdetails(), headers);
		String url="";
		
		Login login = (Login)req.getSession().getAttribute("login");
		for(int i=0; i<contactDetails.getContactdetails().size(); i++) {
			contactDetails.getContactdetails().get(i).setCreatedby(login.getEmplid());
			contactDetails.getContactdetails().get(i).setUpdatedby(login.getEmplid());
		//	System.out.println("Data "+i+" :: "+contactDetails.getContactdetails().get(i).toString());
		}
		
		 url = appgateway.getAppgateway()+"/PersonManagement/correctSavePhoneDetails";
		 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.POST, request, SingleResponseModel.class);
			if (response.getStatusCode() == HttpStatus.OK) { 
				res = response.getBody(); 
			}
		
		/*if(mode.equals("Correct")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/correctSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.POST, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}
		if(mode.equals("Update")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/updateSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}*/
		
		opentab=tab;
	//	System.out.println("tab::"+tab);
		//String method=;
		
		return managepersonedit(model,req,res);
		/* return "fragments/enterprise/create/createPosition:: createPosition"; */

	}

	@PostMapping(value= "/emailcorrect", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String emailcorrect(EmailDetailsWrapper emailDetails, Model model,HttpServletRequest req, String tab)
	{
	//	System.out.println("Inside Email Correct ");
		//System.out.println(tab);
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<List<EmailDetails>> request = new HttpEntity<List<EmailDetails>>(emailDetails.getEmailDetails(), headers);
		String url="";
		
		Login login = (Login)req.getSession().getAttribute("login");
		for(int i=0; i<emailDetails.getEmailDetails().size(); i++) {
			emailDetails.getEmailDetails().get(i).setCreatedby(login.getEmplid());
			emailDetails.getEmailDetails().get(i).setUpdatedby(login.getEmplid());
		//	System.out.println("Data "+i+" :: "+emailDetails.getEmailDetails().get(i).toString());
		}
		
		for(int j=0;j<emailDetails.getEmailDetails().size();j++) {
			System.out.println("DATA :::: "+ emailDetails.getEmailDetails().get(j).toString());
		}
		
		
		 url = appgateway.getAppgateway()+"/PersonManagement/correctSaveEmailDetails";
		 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.POST, request, SingleResponseModel.class);
			if (response.getStatusCode() == HttpStatus.OK) { 
				res = response.getBody(); 
			}
		
		/*if(mode.equals("Correct")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/correctSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.POST, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}
		if(mode.equals("Update")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/updateSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}*/
		
		opentab=tab;
	//	System.out.println("tab::"+tab);
		//String method=;
		
		return managepersonedit(model,req,res);
		/* return "fragments/enterprise/create/createPosition:: createPosition"; */

	}

	@PostMapping(value= "/natcorrect", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String nationaldetails(NationalDetailsWrapper natdetails, Model model,HttpServletRequest req, String tab)
	{
		//System.out.println("Inside National Correct ");
		//System.out.println(tab);
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<List<NationalDetails>> request = new HttpEntity<List<NationalDetails>>(natdetails.getNatdetails(), headers);
		String url="";
		
		Login login = (Login)req.getSession().getAttribute("login");
		for(int i=0; i<natdetails.getNatdetails().size(); i++) {
			natdetails.getNatdetails().get(i).setCreatedby(login.getEmplid());
			natdetails.getNatdetails().get(i).setUpdatedby(login.getEmplid());
		//	System.out.println("Data "+i+" :: "+natdetails.getNatdetails().get(i).toString());
		}
		
		 url = appgateway.getAppgateway()+"/PersonManagement/correctSaveNationalDetails";
		 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.POST, request, SingleResponseModel.class);
			if (response.getStatusCode() == HttpStatus.OK) { 
				res = response.getBody(); 
			}
		
		/*if(mode.equals("Correct")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/correctSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.POST, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}
		if(mode.equals("Update")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/updateSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}*/
		
		opentab=tab;
		//System.out.println("tab::"+tab);
		//String method=;
		
		return managepersonedit(model,req,res);
		/* return "fragments/enterprise/create/createPosition:: createPosition"; */

	}

	@PostMapping(value= "/maritalcorrect", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String maritaldetails(MaritalDetails maritaldetails, Model model,HttpServletRequest req, String tab)
	{
		//System.out.println("Inside Marital Correct ");
		//System.out.println(tab);
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<MaritalDetails> request = new HttpEntity<MaritalDetails>(maritaldetails, headers);
		String url="";
		
		Login login = (Login)req.getSession().getAttribute("login");
		/*for(int i=0; i<natdetails.getNatdetails().size(); i++) {
			natdetails.getNatdetails().get(i).setCreatedby(login.getEmplid());
			natdetails.getNatdetails().get(i).setUpdatedby(login.getEmplid());
			System.out.println("Data "+i+" :: "+natdetails.getNatdetails().get(i).toString());
		}*/
		maritaldetails.setCreatedby(login.getEmplid());
		maritaldetails.setUpdatedby(login.getEmplid());
		//System.out.println("Marital Details :: "+maritaldetails.toString());;
		url = appgateway.getAppgateway()+"/PersonManagement/correctMaritalDetails";
		 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
			if (response.getStatusCode() == HttpStatus.OK) { 
				res = response.getBody(); 
			}
		
		/*if(mode.equals("Correct")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/correctSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.POST, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}
		if(mode.equals("Update")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/updateSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}*/
		
		opentab=tab;
		//System.out.println("tab::"+tab);
		//String method=;
		
		return managepersonedit(model,req,res);
		/* return "fragments/enterprise/create/createPosition:: createPosition"; */

	}
	
	@PostMapping(value= "/biocorrect", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String biodetails(BiographicalInfo biographicalInfo, Model model,HttpServletRequest req, String tab)
	{
	///	System.out.println("Inside BIO Correct ");
		//System.out.println(tab);
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<BiographicalInfo> request = new HttpEntity<BiographicalInfo>(biographicalInfo, headers);
		String url="";
		
		Login login = (Login)req.getSession().getAttribute("login");
		/*for(int i=0; i<natdetails.getNatdetails().size(); i++) {
			natdetails.getNatdetails().get(i).setCreatedby(login.getEmplid());
			natdetails.getNatdetails().get(i).setUpdatedby(login.getEmplid());
			System.out.println("Data "+i+" :: "+natdetails.getNatdetails().get(i).toString());
		}*/
		biographicalInfo.setCreatedby(login.getEmplid());
		biographicalInfo.setUpdatedby(login.getEmplid());
		//System.out.println("BIO Details :: "+biographicalInfo.toString());;
		url = appgateway.getAppgateway()+"/PersonManagement/correctBiographicalinfo";
		 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
			if (response.getStatusCode() == HttpStatus.OK) { 
				res = response.getBody(); 
			}
		
		/*if(mode.equals("Correct")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/correctSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.POST, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}
		if(mode.equals("Update")) {
			 url = appgateway.getAppgateway()+"/PersonManagement/updateSaveAddressDetails";
			 ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
				if (response.getStatusCode() == HttpStatus.OK) { 
					res = response.getBody(); 
				}
		}*/
		
		opentab=tab;
		//System.out.println("tab::"+tab);
		//String method=;
		
		return managepersonedit(model,req,res);
		/* return "fragments/enterprise/create/createPosition:: createPosition"; */

	}

	
	
	@ResponseBody
	@RequestMapping("/getaddressbyactionid/{actionid}")
	public AddressDetails getaddrbyactionid(@PathVariable("actionid") String actionid, Model model,  HttpServletRequest req) {

		//System.out.println("Inside Address by action Id");
		AddressDetails sd1 = null;
		
		String urlp = appgateway.getAppgateway() + "/PersonManagement/getAddressDetailsByActionId/" + actionid;

		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<AddressDetails> response = restTemplate.exchange(urlp, HttpMethod.GET, request, AddressDetails.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd1 = response.getBody();

		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		
		}
		/*
		 * lovbinding = (Lovbinding)req.getSession().getAttribute("lovbinding");
		 * lovbinding.setGradesteplov(sd1);
		 */
		//System.out.println(sd1.toString());

		//model.addAttribute("reasonbyaction", sd1);

		return sd1;

	}


	@RequestMapping("/pmdocrecord")
	public String pmdocRecord(Model model,HttpServletRequest req) {
		model.addAttribute("res", "");
		CommonLOV[] doccat=null;
		Login login=(Login)req.getSession().getAttribute("login");

		//System.out.println("Hello "+login.getEmplid()+", Welcome in Document Record");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);


		String urldoccat = appgateway.getAppgateway()+"/DocumentCategory/getAllDocumentCategory";
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urldoccat, HttpMethod.GET, request, CommonLOV[].class);
		if(response.getStatusCode() == HttpStatus.OK) {
			doccat = response.getBody();
		}

		//String pid=login.getEmplid();
		DocumentView[] docs=null;
		String urlgetdoc = appgateway.getAppgateway()+"/UserDocuments/getUserDocumentsViewById/"+pnumber;
		ResponseEntity<DocumentView[]> response1 = restTemplate.exchange(urlgetdoc, HttpMethod.GET, request, DocumentView[].class);
		if(response1.getStatusCode() == HttpStatus.OK) {
			docs = response1.getBody();
		}

		CommonDescription pygrp= null;
		String urlpygrp= appgateway.getAppgateway()+"/AbsencePersonData/getPersonDataByGivenParameter/"+pnumber+"/paygroup";
		ResponseEntity<CommonDescription> pygrpresponse = restTemplate.exchange(urlpygrp, HttpMethod.GET, request, CommonDescription.class);
		if(pygrpresponse.getStatusCode() == HttpStatus.OK) {
			pygrp = pygrpresponse.getBody();
		}

		CalendarPeriod[] calcode = null;
		String urlcalcode= appgateway.getAppgateway_payroll()+"/api/paycalendar/getFinalizeCalendarsByPaygroupid/"+pygrp.getDescription();
		ResponseEntity<CalendarPeriod[]> calcoderesponse = restTemplate.exchange(urlcalcode, HttpMethod.GET, request, CalendarPeriod[].class);
		if(calcoderesponse.getStatusCode() == HttpStatus.OK) {
			calcode = calcoderesponse.getBody();
		}

		model.addAttribute("calcode", calcode);
		model.addAttribute("loginid",pnumber);
		model.addAttribute("doccat", doccat);
		model.addAttribute("docs", docs);
		return "fragments/pmdocrecord :: pmdocRecord";
	}
	//consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE headers = "content-type!=multipart/form-data"



	@RequestMapping(value = "/pmsavedocument", method = RequestMethod.POST)
	public String pmsaveDocs(@RequestParam("file") MultipartFile file, UserDocument userDocument, HttpServletRequest request, Model model) {

		Login login=(Login)request.getSession().getAttribute("login");

		String location = userDocument.getFilepath();
		String filePath = new File("").getAbsolutePath()+File.separator+userDocument.getFilepath();
		String storePath=location;

		if(!file.isEmpty()) {
			//CREATE DIRECTORY IF NOT EXISTS
			File dir = new File(filePath);
			if( !dir.exists() )
			{
				dir.mkdirs();
			}

			//WRITE FILES TO DIRECTORY AND ENTRY INTO THE DATABASE

			try
			{


				//String empl=login.getEmplid();


				String extension =  FilenameUtils.getExtension( file.getOriginalFilename() );

				String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"+"'"+pnumber+"."+extension+"'").format(new Date());

				File target = new File(filePath+File.separator+generatedFileName);
				int readByteCount = 0;
				byte[] buffer = new byte[20480];

				BufferedInputStream in= new BufferedInputStream(file.getInputStream());
				FileOutputStream out = new FileOutputStream(target);
				while( (readByteCount = in.read(buffer)) != -1)
				{
					out.write(buffer, 0, readByteCount);
				}

				storePath += "/"+generatedFileName;


				userDocument.setFilepath(storePath);



			}
			catch(IOException e)
			{

				e.printStackTrace();
				model.addAttribute("res", "IOEXCEPTION");
				return pmdocRecord(model,request);
			}
			catch(IllegalArgumentException e)
			{

				e.printStackTrace();
				model.addAttribute("res", "ILLEGALARG");
				return pmdocRecord(model,request);
			}
			catch(Exception e)
			{

				e.printStackTrace();
				model.addAttribute("res", "WRITE_ERROR");
				return pmdocRecord(model,request);
			}

		}else {
			userDocument.setFilepath(null);
		}

		//System.out.println("DATA :::: "+ userDocument.toString());
		String url = appgateway.getAppgateway() + "/UserDocuments/saveUserDocuments";


		SingleResponseModel msg = null;
		HttpEntity<UserDocument> request1 = new HttpEntity<UserDocument>(userDocument,headers);
		ResponseEntity<SingleResponseModel> response= restTemplate.exchange(url ,HttpMethod.POST,request1, SingleResponseModel.class);
		if(response.getStatusCode() == HttpStatus.OK) {
			msg = response.getBody();
		} else {
			//  System.out.println(response.getStatusCode());
			return "LOG_ERROR";
		}

		//  System.out.println(msg.getMessage());
		//System.out.println(msg.getStatus());
		storePath = location;

		model.addAttribute("res", "Success");
		return pmdocRecord(model,request);
	}


	//-----------------------Pooja Start(23-01-2021)-------------------------


	@RequestMapping("/payslip/{bus_id}/{calid}/{paygrpid}")
	public String getPayslip(@PathVariable("bus_id") String bus_id,  @PathVariable("calid") String calid,
							 @PathVariable("paygrpid") String paygrpid, HttpServletRequest request, Model model) {
		// System.out.println("BU:" + bus_id + " CAL:" + calid + " PYGRP:" + paygrpid);
		PayrollSummery[] payrlsum = null;
		String url = appgateway.getAppgateway_payroll() + "/api/runpayroll/getelementwisepayrollslipsummery";
		headers.setContentType(MediaType.APPLICATION_JSON);

		Login login = (Login) request.getSession().getAttribute("login");


		//String pnum=(String) request.getSession().getAttribute("pnum");

		String paysummPayLoad = "{" + "\"buid\"" + ":\"" + "" + "\"," + "\"calid\"" + ":\"" + calid + "\","
				+ "\"paygroupid\"" + ":\"" + "" + "\"," + "\"personnumber\"" + ":\"" + pnumber + "\"" + "}";
		//System.out.println("HHHHHH :::::" + paysummPayLoad);

		HttpEntity<String> payrep = new HttpEntity<String>(paysummPayLoad, headers);


		CommonDescription pygrp= null;
		String urlpygrp= appgateway.getAppgateway()+"/AbsencePersonData/getPersonDataByGivenParameter/"+pnumber+"/paygroup";
		ResponseEntity<CommonDescription> pygrpresponse = restTemplate.exchange(urlpygrp, HttpMethod.GET, payrep, CommonDescription.class);
		if(pygrpresponse.getStatusCode() == HttpStatus.OK) {
			pygrp = pygrpresponse.getBody();
		}

		CalendarPeriod[] calcode = null;
		String urlcalcode= appgateway.getAppgateway_payroll()+"/api/paycalendar/getFinalizeCalendarsByPaygroupid/"+pygrp.getDescription();
		ResponseEntity<CalendarPeriod[]> calcoderesponse = restTemplate.exchange(urlcalcode, HttpMethod.GET, payrep, CalendarPeriod[].class);
		if(calcoderesponse.getStatusCode() == HttpStatus.OK) {
			calcode = calcoderesponse.getBody();
		}

		//System.out.println("HHHHHH :::::" + urlcalcode);

		ResponseEntity<PayrollSummery[]> response = restTemplate.exchange(url, HttpMethod.POST, payrep,
				PayrollSummery[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			payrlsum = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		for (int i = 0; i < payrlsum.length; i++) {
			for (PayrollData data : payrlsum[i].getPayrolldata()) {

				if (data.getElementtype().equals("er")) {
					earning = earning + Double.parseDouble(data.getPayamt());
					months = payrlsum[0].getPayrolldata().get(0).getCalendarcode();
					paiddays = payrlsum[0].getPayrolldata().get(0).getPaiddays();
				} else if (data.getElementtype().equals("de")) {
					deduction = deduction + Double.parseDouble(data.getPayamt());
				} else if (data.getElementtype().equals("st") && (!data.getAbbreviation().equals("Total Earning"))
						&& (!data.getAbbreviation().equals("Total Deduction"))
						&& (!data.getAbbreviation().equals("Net Pay"))) {
					contribution = contribution + Double.parseDouble(data.getPayamt());
				}else if (data.getElementtype().equals("st") && (data.getAbbreviation().equals("Net Pay"))) {
					n1 = n1 + Double.parseDouble(data.getPayamt());
				}
			}
		}


		//System.out.println(months.substring(4,12));
		net = n1 - n2;

		model.addAttribute("payrlsum", payrlsum);
		model.addAttribute("paiddays", paiddays);
		model.addAttribute("months", months.substring(4,12));
		model.addAttribute("earning", earning);
		model.addAttribute("netmonthlysalary", net);
		model.addAttribute("deduction", deduction);
		model.addAttribute("contribution", contribution);

		return "fragments/payslip/payslip :: paySlip";

	}



	@RequestMapping("/pmsalarycard/{bus_id}/{calcode}/{paygrpid}")
	public String sal(@PathVariable("calcode") String calcode,@PathVariable("bus_id") String bus_id, @PathVariable("paygrpid") String paygrpid,
					  Model model, HttpServletRequest req) {

		double earPrev = 0;
		double earCur = 0;
		double earTot = 0;
		double dedPrev = 0;
		double dedCur = 0;
		double dedTot = 0;
		double empPrev = 0;
		double empCur = 0;
		double empTot = 0;
		double perPrev = 0;
		double perCur = 0;
		double perTot = 0;
		double netPrev = 0;
		double netCur = 0;
		double netTot = 0;


		headers.setContentType(MediaType.APPLICATION_JSON);
		Login login= (Login) req.getSession().getAttribute("login");



		//System.out.println("BU:"+bus_id+" CAL:"+calcode+" PYGRP:"+paygrpid);
		PayrollSalaryCard[] payrollSalaryCards = null;
		CommonLOV commonLOV=null;
		String businessunit="";
		//String pnum=(String) req.getSession().getAttribute("pnum");

		String buurl = appgateway.getAppgateway() +"/AbsencePersonData/getPersonDataByGivenParameter/"+pnumber+"/businessunit";
		headers.setContentType(MediaType.APPLICATION_JSON);
		//HttpHeaders head = new HttpHeaders();
		//	HttpEntity<String> request = new HttpEntity<String>("", head);
		String paysummPayLoad = "{" +
				"\"buid\"" + ":\"" + "" + "\"," +
				"\"calcode\"" + ":\"" + calcode + "\"," +
				"\"paygroupid\""+ ":\"" + ""+ "\"," +
				"\"personnumber\"" + ":\""	+ pnumber + "\"" +
				"}";
		HttpEntity<String> request = new HttpEntity<String>(paysummPayLoad, headers);


		//String buurl = appGateway.getAppgateway() +"AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/businessunit";
		//headers.setContentType(MediaType.APPLICATION_JSON);
		//HttpHeaders head = new HttpHeaders();
		//HttpEntity<String> request = new HttpEntity<String>("", head);
		ResponseEntity<CommonLOV> response = restTemplate.exchange(buurl, HttpMethod.GET, request,
				CommonLOV.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			commonLOV = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		//System.out.println("DATA::  " +commonLOV.toString());
		//String description=forBusinessunit.getDescription();



		String url = appgateway.getAppgateway_payroll()+"/api/mod/getsalarycard/"+calcode+"/"+commonLOV.getDescription()+"/"+pnumber;
		/*
		 * String paysummPayLoad = "{" + "\"buid\"" + ":\"" + "" + "\"," + "\"calcode\""
		 * + ":\"" + calcode + "\"," + "\"paygroupid\""+ ":\"" + ""+ "\"," +
		 * "\"personnumber\"" + ":\"" + login.getEmplid() + "\"" + "}";
		 */

		//System.out.println("URL  ::"+url);

		//HttpEntity<String> request = new HttpEntity<String>(paysummPayLoad, headers);
		ResponseEntity<PayrollSalaryCard[]> response1 = restTemplate.exchange(url, HttpMethod.GET, request,
				PayrollSalaryCard[].class);

		//System.out.println(paysummPayLoad);

		if (response1.getStatusCode() == HttpStatus.OK) {
			payrollSalaryCards = response1.getBody();
			//System.out.println("data::" + payrollSalaryCards.toString());
		} else {
			System.out.println("Request Failed");
			System.out.println(response1.getStatusCode());
		}
		for (int i = 0; i < payrollSalaryCards.length; i++) {

			for (PayrollSalCardElements data : payrollSalaryCards[i].getElem()) {
				if (data.getElement_type().equals("er")) {
					earPrev = earPrev + Double.parseDouble(data.getPrev_amt());
					earCur = earCur + Double.parseDouble(data.getCur_amt());
					earTot = earTot + Double.parseDouble(data.getTot_amt());
				}
				else if (data.getElement_type().equals("de")) {
					System.out.println("ELEMENT NAME ::: "+data.getElement_name()+"\t Previous Amount ::: "+data.getPrev_amt()+"\tCurrent Amount ::: "+data.getCur_amt());
					dedPrev = dedPrev + Double.parseDouble(data.getPrev_amt());
					dedCur = dedCur + Double.parseDouble(data.getCur_amt());
					dedTot = dedTot + Double.parseDouble(data.getTot_amt());
				}
				else if (data.getElement_type().equals("st")) {
					empPrev = empPrev + Double.parseDouble(data.getPrev_amt());
					empCur = empCur + Double.parseDouble(data.getCur_amt());
					empTot = empTot + Double.parseDouble(data.getTot_amt());
				}
				else if (data.getElement_type().equals("sp")) {
					perPrev = perPrev + Double.parseDouble(data.getPrev_amt());
					perCur = perCur + Double.parseDouble(data.getCur_amt());
					perTot = perTot + Double.parseDouble(data.getTot_amt());
				}
			}
		}

		netPrev = earPrev - dedPrev;
		netCur = earCur - dedCur;
		netTot = earTot - dedTot;

		model.addAttribute("sum_earn_cur_pay", earCur);
		model.addAttribute("sum_earn_pre_pay", earPrev);
		model.addAttribute("sum_earn_tot_pay", earTot);
		model.addAttribute("sum_ded_cur_pay", dedCur);
		model.addAttribute("sum_ded_pre_pay", dedPrev);
		model.addAttribute("sum_ded_tot_pay", dedTot);
		model.addAttribute("sum_emp_cur_pay", empCur);
		model.addAttribute("sum_emp_pre_pay", empPrev);
		model.addAttribute("sum_emp_tot_pay", empTot);
		model.addAttribute("sum_per_cur_pay", perCur);
		model.addAttribute("sum_per_pre_pay", perPrev);
		model.addAttribute("sum_per_tot_pay", perTot);
		model.addAttribute("sum_netPrev_pay", netPrev);
		model.addAttribute("sum_netCur_pay", netCur);
		model.addAttribute("sum_netTot_pay", netTot);

		model.addAttribute("salarycard", payrollSalaryCards);

		return "fragments/salarycard/salaryCard :: salaryCard";

	}


	//-----------------------Pooja End(23-01-2021)-------------------------


/* ASMITA DOWNLOAD PDF STARTS 06-05-2021 */
//	@ResponseBody
//	 @RequestMapping(value="/download/{calcode}", method = RequestMethod.GET)
//	    public String downloadFile(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("calcode") String calcode) throws IOException {
//	     
//		 Login login = (Login)request.getSession().getAttribute("login");
//		 pnumber = login.getEmplid();
//		 
//		 String filelocation = "/EmployeeDocs/LMRC_Payslips/";
//		 
//		 String filename = "TrialPayslips.pdf";
//		 
//		 String fullfilepath = filelocation + filename;
//		 
//		 model.addAttribute("fullfilepath",fullfilepath);
//
//		 return fullfilepath;
//		 
//		 
//	   }

@RequestMapping("/pmsalarycard/{calcodefrom}/{calcodeto}")
	public String sal(@PathVariable("calcodefrom") String calcodefrom, @PathVariable("calcodeto") String calcodeto, 
			Model model, HttpServletRequest req, HttpServletResponse response) {

		
		headers.setContentType(MediaType.APPLICATION_JSON);
		Login login= (Login) req.getSession().getAttribute("login");

		PayrollSalaryCards[] pyrlsalcards = null;
		CommonLOV commonLOV = null;
		String businessunit = "", paygroup = "";

//		System.out.println("Person Number ::: "+pnumber);
		String buurl = appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/" + pnumber + "/businessunit";
		
		String pgurl = appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/" + pnumber + "/paygroup";

		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> bupgrequest = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV> buresponse = restTemplate.exchange(buurl, HttpMethod.GET, bupgrequest, CommonLOV.class);

		ResponseEntity<CommonLOV> pgresponse = restTemplate.exchange(pgurl, HttpMethod.GET, bupgrequest, CommonLOV.class);
		
		if (buresponse.getStatusCode() == HttpStatus.OK) {
			commonLOV = buresponse.getBody();
			businessunit = commonLOV.getDescription();
		} else {
			System.out.println("Request Failed");
			System.out.println(buresponse.getStatusCode());
		}
		
		if (pgresponse.getStatusCode() == HttpStatus.OK) {
			commonLOV = pgresponse.getBody();
			paygroup = commonLOV.getDescription();
		} else {
			System.out.println("Request Failed");
			System.out.println(pgresponse.getStatusCode());
		}
				
//System.out.println("BU :: "+businessunit);
//System.out.println("PG :: "+paygroup);
		String url =appgateway.getAppgateway_payroll() + "/api/mod/getsalarycard/"+calcodefrom+"/"+calcodeto+"/"+businessunit+"/"+pnumber+"/"+paygroup;

		 HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<PayrollSalaryCards[]> response1 = restTemplate.exchange(url, HttpMethod.GET, request,
				PayrollSalaryCards[].class);

		if (response1.getStatusCode() == HttpStatus.OK) {
			pyrlsalcards = response1.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response1.getStatusCode());
		}
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=LMRC_SALARY_CARD_" + calcodefrom+"_to_"+calcodeto+".xlsx");
		try {
			genexfiles.WriteEmployeeSalaryCard(pyrlsalcards,calcodefrom,calcodeto,response.getOutputStream());
			response.flushBuffer();
		} catch(IOException e) {
			System.out.println("ERROR: " + e);

		}
		
		return null;

	}

}
