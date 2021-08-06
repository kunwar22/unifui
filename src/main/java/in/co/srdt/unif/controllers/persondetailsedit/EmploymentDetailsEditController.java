package in.co.srdt.unif.controllers.persondetailsedit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.BankType;
import in.co.srdt.unif.enums.Identifying;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.employmentmanagement.EmployementInfoManageMaster;
import in.co.srdt.unif.model.employmentmanagement.JobData;
import in.co.srdt.unif.model.employmentmanagement.ManagerDetails;
import in.co.srdt.unif.model.employmentmanagement.ManagerDetailsWrapper;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.newperson.BankDetails;
import in.co.srdt.unif.model.newperson.Lovbinding;
import in.co.srdt.unif.model.newperson.Newperson;
import in.co.srdt.unif.model.newperson.SupervisorSearchresult;
import in.co.srdt.unif.model.payroll.PaygroupLOV;
import in.co.srdt.unif.model.search.GradeStepByGradeId;
import in.co.srdt.unif.model.search.LocationSearchResult;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@SessionAttributes("pnum")
@RequestMapping("/employee_edit")
public class EmploymentDetailsEditController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationGateway appgateway;
	

	@Autowired
	private HttpHeaders headers;

	public String opentab_="EMP_DTL_TAB";
	
	
	@RequestMapping("/manageemployeedetail/{perno}/{actionid}/{effdate}")
    public String manageemployeedetail(@PathVariable("perno") String perno,@PathVariable("actionid") String actionid,@PathVariable("effdate") String effdate,HttpServletRequest req, Model model, @ModelAttribute("pnum")String pnum, SingleResponseModel res) {
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		Login login = (Login)req.getSession().getAttribute("login");
		//System.out.println(login.getEmplid());
		
		EmployementInfoManageMaster emplMaster=null;
		String urlgetempldetails="";
		if(perno.equals("z") && effdate.equals("z")) {
			 urlgetempldetails = appgateway.getAppgateway() + "/PersonData/PersonalRecord/getEmployementInfoByPersonNo/"+pnum;
		}else {
			 urlgetempldetails = appgateway.getAppgateway() + "/PersonData/PersonalRecord/getEmployementInfoByActonId/"+pnum+"/"+actionid+"/"+effdate;
		}
		
		
		ResponseEntity<EmployementInfoManageMaster> response = restTemplate.exchange(urlgetempldetails, HttpMethod.GET, request, EmployementInfoManageMaster.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			emplMaster = response.getBody(); 
		}
		
		//System.out.println("Employee Details ::"+ emplMaster.toString());
		//System.out.println("PERSON NUMBER ::"+pnum);

		CommonLOV[] country = null;
		String urlcountry = appgateway.getAppgateway() + "/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response3 = restTemplate.exchange(urlcountry, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response3.getStatusCode() == HttpStatus.OK) {
			country = response3.getBody();
		}
		
		LocationSearchResult[] locationId = null;
		String urlLocation = appgateway.getAppgateway()+"/Location/locationSearchList";
		String payLoad = "{" + "\"name\"" + ":\"" + "" + "\"," + "\"code\"" + ":\"" + "" + "\","
				+ "\"country\"" + ":\"" + "" + "\"," + "\"status\"" + ":\"" + "Active" + "\"" + "}";
		HttpEntity<String> request02 = new HttpEntity<String>(payLoad, headers);
		//System.out.println("Payload ::"+ payLoad);
		ResponseEntity<LocationSearchResult[]> response06 = restTemplate.exchange(urlLocation, HttpMethod.POST, request02,
				LocationSearchResult[].class);
		if (response06.getStatusCode() == HttpStatus.OK) {
			locationId = response06.getBody();
		}
		
		PaygroupLOV[] paygroup=null;
		String urlpaygroup = appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
		ResponseEntity<PaygroupLOV[]> response31 = restTemplate.exchange(urlpaygroup, HttpMethod.GET, request,
				PaygroupLOV[].class);
		if (response31.getStatusCode() == HttpStatus.OK) {
			paygroup = response31.getBody();
		}
		
		CommonLOV[] hrstatus=null;
		String urlhrstatus = appgateway.getAppgateway() + "/General/loadHRStatus";
		ResponseEntity<CommonLOV[]> response30 = restTemplate.exchange(urlhrstatus, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response30.getStatusCode() == HttpStatus.OK) {
			hrstatus = response30.getBody();
		}
		
		CommonLOV[] nature_of_employement=null;
		String urlnatofempl = appgateway.getAppgateway() + "/General/loadNatureOfEmployement";
		ResponseEntity<CommonLOV[]> response25 = restTemplate.exchange(urlnatofempl, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response25.getStatusCode() == HttpStatus.OK) {
			nature_of_employement = response25.getBody();
		}
		
		CommonLOV[] employee_category=null;
		String urlemp_cat = appgateway.getAppgateway() + "/General/loadEmployeeCategory";
		ResponseEntity<CommonLOV[]> response26 = restTemplate.exchange(urlemp_cat, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response26.getStatusCode() == HttpStatus.OK) {
			employee_category = response26.getBody();
		}
		
		CommonLOV[] staff=null;
		String url_staff = appgateway.getAppgateway() + "/General/loadStaff";
		ResponseEntity<CommonLOV[]> response27 = restTemplate.exchange(url_staff, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response27.getStatusCode() == HttpStatus.OK) {
			staff = response27.getBody();
		}
		
		CommonLOV[] projectom=null;
		String url_projectom = appgateway.getAppgateway() + "/General/loadProjectOM";
		ResponseEntity<CommonLOV[]> response28 = restTemplate.exchange(url_projectom, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response28.getStatusCode() == HttpStatus.OK) {
			projectom = response28.getBody();
		}
		
		CommonLOV[] regortemp=null;
		String urlregortemp = appgateway.getAppgateway() + "/General/loadRegularTemporary";
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlregortemp, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			regortemp = response2.getBody();
		}
		
		CommonLOV[] fullorpart=null;
		String urlrfullorpart = appgateway.getAppgateway() + "/General/loadFullTimePartTime";
		ResponseEntity<CommonLOV[]> response12 = restTemplate.exchange(urlrfullorpart, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response12.getStatusCode() == HttpStatus.OK) {
			fullorpart = response12.getBody();
		}
		
		CommonLOV[] workertype=null;
		String urltype = appgateway.getAppgateway() + "/General/loadWorkerType";
		ResponseEntity<CommonLOV[]> response4 = restTemplate.exchange(urltype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			workertype = response4.getBody();
		}
		
		CommonLOV[] probperiod=null;
		String urlprobperiod = appgateway.getAppgateway() + "/General/loadProbationPeriod";
		ResponseEntity<CommonLOV[]> response5 = restTemplate.exchange(urlprobperiod, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response5.getStatusCode() == HttpStatus.OK) {
			probperiod = response5.getBody();
		}

		CommonLOV[] noticeperiod=null;
		String urlnoticeperiod = appgateway.getAppgateway() + "/General/loadNoticePeriod";
		ResponseEntity<CommonLOV[]> response6 = restTemplate.exchange(urlnoticeperiod, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response6.getStatusCode() == HttpStatus.OK) {
			noticeperiod = response6.getBody();
		}

		CommonLOV[] hireaction=null;
		String urlhireaction = appgateway.getAppgateway() + "/Action/loadAllManagementAction";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlhireaction, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response7.getStatusCode() == HttpStatus.OK) {
			hireaction = response7.getBody();
		}

		CommonLOV[] workercat=null;
		String urlworkercat = appgateway.getAppgateway() + "/General/loadWorkerCategory";
		ResponseEntity<CommonLOV[]> response16 = restTemplate.exchange(urlworkercat, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response16.getStatusCode() == HttpStatus.OK) {
			workercat = response16.getBody();
		}
		
		CommonLOV[] managertype=null;
		String urlmanagertype = appgateway.getAppgateway() + "/ManagerType/getAllManagerTypeLOV";
		ResponseEntity<CommonLOV[]> response20 = restTemplate.exchange(urlmanagertype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response20.getStatusCode() == HttpStatus.OK) {
			managertype = response20.getBody();
		}
		
		CommonLOV[] workingmanager=null;
		String urlworkingmanager = appgateway.getAppgateway() + "/General/loadYesNo";
		ResponseEntity<CommonLOV[]> response24 = restTemplate.exchange(urlworkingmanager, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response24.getStatusCode() == HttpStatus.OK) {
			workingmanager = response24.getBody();
		}
		

		CommonLOV[] assignCat=null;
		String urlassignCat = appgateway.getAppgateway() + "/General/loadAssignmentCategory";
		ResponseEntity<CommonLOV[]> response11 = restTemplate.exchange(urlassignCat, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response11.getStatusCode() == HttpStatus.OK) {
			assignCat = response11.getBody();
		}
		CommonLOV[] freq=null;
		String urlfreq = appgateway.getAppgateway() + "/General/loadFrequncy";
		ResponseEntity<CommonLOV[]> response13 = restTemplate.exchange(urlfreq, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response13.getStatusCode() == HttpStatus.OK) {
			freq = response13.getBody();
		}
		CommonLOV[] saltype=null;
		String urlsaltype = appgateway.getAppgateway() + "/General/loadSalaryType";
		ResponseEntity<CommonLOV[]> response14 = restTemplate.exchange(urlsaltype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response14.getStatusCode() == HttpStatus.OK) {
			saltype = response14.getBody();
		}

		model.addAttribute("acctype", BankType.values());
		model.addAttribute("type", saltype);
		model.addAttribute("freq",freq);
		model.addAttribute("assignCat",assignCat);
		model.addAttribute("paygroup",paygroup);
		model.addAttribute("country", country);
		model.addAttribute("location",locationId);
		model.addAttribute("hrstatus",hrstatus);
		model.addAttribute("workingmanager", workingmanager);
		model.addAttribute("managertype", managertype);
		model.addAttribute("workercat", workercat);
		model.addAttribute("hireaction", hireaction);
		model.addAttribute("regortemp", regortemp);
		model.addAttribute("fullorpart", fullorpart);
		model.addAttribute("workertype", workertype);
		model.addAttribute("probperiod", probperiod);
		model.addAttribute("noticeperiod", noticeperiod);
		model.addAttribute("nature_of_employement", nature_of_employement);
		model.addAttribute("employee_category", employee_category);
		model.addAttribute("staff", staff);
		model.addAttribute("projectom", projectom);
		model.addAttribute("status", Status.values());
		model.addAttribute("manager",Identifying.values());


		/*
		 * if(emplMaster.getJobdata().getEffectiveenddate().equals("4712-12-31")) {
		 * emplMaster.getJobdata().setEffectiveenddate(" "); }
		 */
		//System.out.println("Insid"+emplMaster.getBankdetails().get(1).getStatus());
		model.addAttribute("empl",emplMaster);
		model.addAttribute("response",res);
		model.addAttribute("opentab_",opentab_);
		opentab_="EMP_DTL_TAB";
        return "fragments/EditEmployementDetails :: EditEmployement";
    }
	
	
	@ResponseBody
	@RequestMapping("/gradestepbind/{grade}")
	public GradeStepByGradeId[] gradeStepByGrade(@PathVariable("grade") String grade, Model model, Lovbinding lovbinding, HttpServletRequest req) {

		//System.out.println("Inside GradeStep Bind");
		GradeStepByGradeId[] sd1 = null;
		// http://192.200.12.83:8181/ActionReason/getAllReasonByActionId/1
		String urlp = appgateway.getAppgateway() + "/Grades/getGradeStepsByGradeId/" + grade;

		// headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<GradeStepByGradeId[]> response = restTemplate.exchange(urlp, HttpMethod.GET, request, GradeStepByGradeId[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd1 = response.getBody();

		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		
		}
		lovbinding = (Lovbinding)req.getSession().getAttribute("lovbinding");
		//lovbinding.setGradesteplov(sd1);
		//System.out.println(sd1.toString());

		//model.addAttribute("reasonbyaction", sd1);

		return sd1;

	}


	@RequestMapping(value= "/addcorrectbank", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String addcorrectbank(BankDetails bankDetails, Model model, HttpServletRequest req, String tab, @ModelAttribute("pnum")String pnum )
	{
		//System.out.println("jobdata::"+jobdata.toString());
		Login login = (Login)req.getSession().getAttribute("login");
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);

		bankDetails.setCreatedby(login.getEmplid());
		bankDetails.setUpdatedby(login.getEmplid());
		System.out.println("Correct Data :: "+bankDetails.toString());
		HttpEntity<BankDetails> request = new HttpEntity<BankDetails>(bankDetails, headers);
		String url="";
		if(bankDetails.getBankaccountsid()!=0) {
			url = appgateway.getAppgateway()+"/PersonManagement/BankAccountsDetails/correctBankAccountsDetails";
			ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
			}
			opentab_=tab;
			return manageemployeedetail("z","z","z",req, model, pnum, res);
		}
		if(bankDetails.getBankaccountsid()==0) {
			bankDetails.setPersonnumber(pnum);
			url = appgateway.getAppgateway()+"/PersonManagement/BankAccountsDetails/saveBankAccountsDetails";
			ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.POST, request, SingleResponseModel.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
			}
			opentab_=tab;
			return manageemployeedetail("z","z","z",req, model, pnum, res);
		}
		return manageemployeedetail("z","z","z",req, model, pnum, res);
	}


	@RequestMapping(value= "/correctupdateEMPL", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String correctupdateEMPL(JobData jobdata, Model model,HttpServletRequest req, String tab,@ModelAttribute("pnum")String pnum )
	{
		//System.out.println("jobdata::"+jobdata.toString());
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<JobData> request = new HttpEntity<JobData>(jobdata, headers);
		String url="";
		if(jobdata.getMode().equals("Correct")) {
			 url = appgateway.getAppgateway()+"/Employement/Manage/correctJobDataDetails";
		}
		if(jobdata.getMode().equals("Update")) {
			 url = appgateway.getAppgateway()+"/Employement/Manage/updateJobDataDetails";
		}
		Login login = (Login)req.getSession().getAttribute("login");
		jobdata.setCreatedby(login.getEmplid());
		jobdata.setUpdatedby(login.getEmplid());
		
		if(jobdata.getEffectiveenddate()=="" || jobdata.getEffectiveenddate()==null) {
			jobdata.setEffectiveenddate("4712-12-31");
		}
		System.out.println("Correct Data :: "+jobdata.toString());
		
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) { 
			res = response.getBody(); 
			//System.out.println("Correct Data :: "+res.toString());
		}
		opentab_=tab;
		//String method=;
		
		return manageemployeedetail("z","z","z",req, model, pnum, res);
		/* return "fragments/enterprise/create/createPosition:: createPosition"; */

	}
	
	@ResponseBody
	@RequestMapping("/managerbind")
	public SupervisorSearchresult[] bindMgr(HttpServletRequest requestFromDT, @ModelAttribute("newperson")  Newperson newperson) {

		String personnumber = requestFromDT.getParameter("personnumber");
		String name = requestFromDT.getParameter("firstname");
		String hiredate = requestFromDT.getParameter("hiredate");
		//Newperson newperson=new Newperson();

		//requestFromDT.getSession().getAttribute("newperson");

	//	System.out.println("Insidemanagerbind Controller");

		String url = appgateway.getAppgateway() + "/SuperviorsLov/getSuperviorsLovList";
		SupervisorSearchresult[] mgrObj = null;
		String payload = "{" + "\"name\"" + ":\"" + name + "\"," + "\"newpersonhiredate\""
				+ ":\"" +hiredate+ "\"," + "\"personid\"" + ":\"\"," + "\"personnumber\"" + ":\"" + personnumber + "\"}";
	//	System.out.println("Payload::::" + payload);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<SupervisorSearchresult[]> response = restTemplate.exchange(url, HttpMethod.POST, request, SupervisorSearchresult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			mgrObj = response.getBody();

		//	System.out.println("mgrObj::::" + mgrObj);
		}

		return mgrObj;

	}

	@RequestMapping(value= "/managercorrect", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String correctmanager(ManagerDetailsWrapper managerDetailsWrapper, Model model,HttpServletRequest req, String tab,@ModelAttribute("pnum")String pnum )
	{
		//System.out.println(tab);
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<List<ManagerDetails>> request = new HttpEntity<List<ManagerDetails>>(managerDetailsWrapper.getManagerdetails(), headers);
		String url="";
		
		url = appgateway.getAppgateway()+"/Employement/Manage/correctAndSaveManagerDetails";
		
		Login login = (Login)req.getSession().getAttribute("login");
		for(int i=0; i<managerDetailsWrapper.getManagerdetails().size(); i++) {
			if(managerDetailsWrapper.getManagerdetails().get(i).getPersonnumber()==null || managerDetailsWrapper.getManagerdetails().get(i).getPersonnumber().equals("")) {
				managerDetailsWrapper.getManagerdetails().get(i).setPersonnumber(pnum);
			}
			/*if(managerDetailsWrapper.getManagerdetails().get(i).getEffectivestartdate()==null || managerDetailsWrapper.getManagerdetails().get(i).getEffectivestartdate().equals("")) {
				Date date=new Date();
				managerDetailsWrapper.getManagerdetails().get(i).setEffectivestartdate("");
			}*/
			managerDetailsWrapper.getManagerdetails().get(i).setCreatedby(login.getEmplid());
			managerDetailsWrapper.getManagerdetails().get(i).setUpdatedby(login.getEmplid());
			//System.out.println("MANAGER DATA "+i+" :: "+managerDetailsWrapper.getManagerdetails().get(i).toString());
		}

		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) { 
			res = response.getBody(); 
		}
		opentab_=tab;
		//String method=;
		return manageemployeedetail("z","z","z",req, model, pnum, res);
		/* return "fragments/enterprise/create/createPosition:: createPosition"; */
	}

	/*
	@RequestMapping(value= "/additionalcorrectupdate", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String additionalcorrectupdate( LmrcAdditionalDetails lmrcAdditionalDetails, Model model,HttpServletRequest req, String tab,@ModelAttribute("pnum")String pnum )
	{
	//	System.out.println(tab);
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<LmrcAdditionalDetails> request = new HttpEntity<LmrcAdditionalDetails>(lmrcAdditionalDetails, headers);
		String url="";
		
		if(lmrcAdditionalDetails.getMode().equals("Correct")) {
			url = appgateway.getAppgateway()+"/Employement/Manage/correctLmrcAdditionalDetails";
		}else if(lmrcAdditionalDetails.getMode().equals("Update")) {
			url = appgateway.getAppgateway()+"/Employement/Manage/updateLmrcAdditionalDetails";
		}
		
		Login login = (Login)req.getSession().getAttribute("login");
		
			lmrcAdditionalDetails.setCreatedby(login.getEmplid());
			lmrcAdditionalDetails.setUpdatedby(login.getEmplid());
			
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) { 
			res = response.getBody(); 
		}
		opentab_=tab;
		//String method=;
		
		return manageemployeedetail("z","z","z",req, model, pnum, res);
		// return "fragments/enterprise/create/createPosition:: createPosition";

	}
	*/
	
	
	@ResponseBody
	@GetMapping("/viewhistory")
	public JobData[] getHistory(@ModelAttribute("pnum")String pnum) {
		String url = appgateway.getAppgateway()+"/Employement/Manage/getPersonJobHistory/"+pnum;
		JobData[] emplhistory = null;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<JobData[]> response = restTemplate.exchange(url, HttpMethod.GET, request, JobData[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			emplhistory = response.getBody();
		}
		return emplhistory;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/getbankdetailsUpdate/{bankaccountid}")
	public BankDetails updateBankDetails(@PathVariable("bankaccountid") String bankaccountid, Model model, HttpServletRequest req) {
		
		BankDetails sd1 = null;		
		String urlp = appgateway.getAppgateway() + "/PersonManagement/BankAccountsDetails/getBankAccountsDetailsById/" + bankaccountid;
		
		System.out.println("bakjssss"+urlp);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<BankDetails> response = restTemplate.exchange(urlp, HttpMethod.GET, request, BankDetails.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd1 = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());		
		}
		
		return sd1;

	}
	
	
	
	
	@RequestMapping(value= "/correctupdateBankDetails", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String correctupdateBankDetails(BankDetails bankDetails, Model model,HttpServletRequest req, String tab,@ModelAttribute("pnum")String pnum )
	{
		
		SingleResponseModel res=new SingleResponseModel();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<BankDetails> request = new HttpEntity<BankDetails>(bankDetails, headers);
		String url="";
		
			 url = appgateway.getAppgateway()+"/PersonManagement/BankAccountsDetails/updateBankAccountsDetails";
		
		Login login = (Login)req.getSession().getAttribute("login");
		bankDetails.setCreatedby(login.getEmplid());
		bankDetails.setUpdatedby(login.getEmplid());
		
		
		System.out.println("Correct Data :: "+bankDetails.toString());
		
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url,HttpMethod.PUT, request, SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) { 
			res = response.getBody(); 
			//System.out.println("Correct Data :: "+res.toString());
		}
		opentab_=tab;
		//String method=;
		
		return manageemployeedetail("z","z","z",req, model, pnum, res);
		/* return "fragments/enterprise/create/createPosition:: createPosition"; */

	}
	
}
