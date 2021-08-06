package in.co.srdt.unif.controllers;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.BankType;
import in.co.srdt.unif.enums.Frequency;
import in.co.srdt.unif.enums.SalaryType;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.Position;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.newperson.AddressDetails;
import in.co.srdt.unif.model.newperson.Lovbinding;
import in.co.srdt.unif.model.newperson.NationalDetails;
import in.co.srdt.unif.model.newperson.Newperson;
import in.co.srdt.unif.model.newperson.SupervisorSearchresult;
import in.co.srdt.unif.model.payroll.PaygroupLOV;
import in.co.srdt.unif.model.search.GradeStepByGradeId;
import in.co.srdt.unif.model.search.LocationSearchResult;
import in.co.srdt.unif.utils.ApplicationGateway;

@SessionAttributes("newperson")
@Controller
@RequestMapping("/newperson")
public class HireEmployeeController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationGateway appgateway;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	SmartValidator validator;
	
	int flg=0;
	String pernum="";

	@RequestMapping("/manageHireEmployee")
	public String createNewperson(Model model,
			@ModelAttribute("lovbinding") Lovbinding lovbinding, HttpServletRequest req, SessionStatus status) {
		status.setComplete();
		model.addAttribute("finalres","0");
		model.addAttribute("newperson", new Newperson());
		CommonLOV[] countryDetailsObj = null;
		CommonLOV[] assignCat = null;
		CommonLOV[] regortemp = null;
		CommonLOV[] fullorpart = null;
		CommonLOV[] workertype = null;
		CommonLOV[] probperiod = null;
		CommonLOV[] noticeperiod = null;
		CommonLOV[] hireaction = null;
		CommonLOV[] gender = null;
		CommonLOV[] managername = null;
		CommonLOV[] managertype = null;

		CommonLOV[] titleLov = null;
		CommonLOV[] nationalid = null;
		CommonLOV[] countrycode = null;
		CommonLOV[] phonetype = null;
		CommonLOV[] emailtype = null;
		CommonLOV[] maritalstatus = null;
		CommonLOV[] persontype = null;
		CommonLOV[] workercat = null;
		CommonLOV[] religion = null;
		CommonLOV[] Maritalstatus = null;
		CommonLOV[] assignmentstatus = null;

		CommonLOV[] state = null;
		//CommonLOV[] hirereason = null;
		CommonLOV[] workingmanager = null;
		CommonLOV[] nature_of_employement= null;
		CommonLOV[] employee_category=null;
		CommonLOV[] staff=null;
		CommonLOV[] projectom=null;
		
		CommonLOV[] hrstatus=null;
		
		PaygroupLOV[] paygroup=null;

		//model.addAttribute("newperson", newperson);

		

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		
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
			lovbinding.setLocationLOV(locationId);
		}

		
		
		String urlpaygroup = appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
		ResponseEntity<PaygroupLOV[]> response31 = restTemplate.exchange(urlpaygroup, HttpMethod.GET, request,
				PaygroupLOV[].class);
		if (response31.getStatusCode() == HttpStatus.OK) {
			paygroup = response31.getBody();
			lovbinding.setPaygroupLOV(paygroup);
		}
		
		String urlhrstatus = appgateway.getAppgateway() + "/General/loadHRStatus";
		ResponseEntity<CommonLOV[]> response30 = restTemplate.exchange(urlhrstatus, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response30.getStatusCode() == HttpStatus.OK) {
			hrstatus = response30.getBody();
			lovbinding.setHrstatuslov(hrstatus);
		}
		
		String urlnatofempl = appgateway.getAppgateway() + "/General/loadNatureOfEmployement";
		ResponseEntity<CommonLOV[]> response25 = restTemplate.exchange(urlnatofempl, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response25.getStatusCode() == HttpStatus.OK) {
			nature_of_employement = response25.getBody();
			lovbinding.setNature_of_employment(nature_of_employement);
		}
		
		String urlemp_cat = appgateway.getAppgateway() + "/General/loadEmployeeCategory";
		ResponseEntity<CommonLOV[]> response26 = restTemplate.exchange(urlemp_cat, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response26.getStatusCode() == HttpStatus.OK) {
			employee_category = response26.getBody();
			lovbinding.setEmployee_category(employee_category);
			
		}
		
		
		String url_staff = appgateway.getAppgateway() + "/General/loadStaff";
		ResponseEntity<CommonLOV[]> response27 = restTemplate.exchange(url_staff, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response27.getStatusCode() == HttpStatus.OK) {
			staff = response27.getBody();
			lovbinding.setStaff(staff);
			
		}
		
		String url_projectom = appgateway.getAppgateway() + "/General/loadProjectOM";
		ResponseEntity<CommonLOV[]> response28 = restTemplate.exchange(url_projectom, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response28.getStatusCode() == HttpStatus.OK) {
			projectom = response28.getBody();
			lovbinding.setProjectom(projectom);
		}
		
		
		String urlcountrydetails = appgateway.getAppgateway() + "/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlcountrydetails, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			countryDetailsObj = response.getBody();
			lovbinding.setCountrylov(countryDetailsObj);

		}

		String urlassignManager = appgateway.getAppgateway() + "/ManagerType/getAllManagerTypeLOV";
		ResponseEntity<CommonLOV[]> responsemanager = restTemplate.exchange(urlassignManager, HttpMethod.GET, request,
				CommonLOV[].class);
		if (responsemanager.getStatusCode() == HttpStatus.OK) {
			managertype = responsemanager.getBody();
			lovbinding.setManagertypelov(managertype);
		}

		String urlassignCat = appgateway.getAppgateway() + "/General/loadAssignmentCategory";
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlassignCat, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			assignCat = response1.getBody();
			lovbinding.setAssignmentcatlov(assignCat);
		}
		String urlregortemp = appgateway.getAppgateway() + "/General/loadRegularTemporary";
		ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlregortemp, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			regortemp = response2.getBody();
			lovbinding.setRegulartemplov(regortemp);
		}
		String urlrfullorpart = appgateway.getAppgateway() + "/General/loadFullTimePartTime";
		ResponseEntity<CommonLOV[]> response3 = restTemplate.exchange(urlrfullorpart, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response3.getStatusCode() == HttpStatus.OK) {
			fullorpart = response3.getBody();
			lovbinding.setFullpartlov(fullorpart);
		}
		String urltype = appgateway.getAppgateway() + "/General/loadWorkerType";
		ResponseEntity<CommonLOV[]> response4 = restTemplate.exchange(urltype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			workertype = response4.getBody();
			lovbinding.setWorkertypelov(workertype);
		}

		String urlprobperiod = appgateway.getAppgateway() + "/General/loadProbationPeriod";
		ResponseEntity<CommonLOV[]> response5 = restTemplate.exchange(urlprobperiod, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response5.getStatusCode() == HttpStatus.OK) {
			probperiod = response5.getBody();
			lovbinding.setProbationlov(probperiod);
		}

		String urlnoticeperiod = appgateway.getAppgateway() + "/General/loadNoticePeriod";
		ResponseEntity<CommonLOV[]> response6 = restTemplate.exchange(urlnoticeperiod, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response6.getStatusCode() == HttpStatus.OK) {
			noticeperiod = response6.getBody();
			lovbinding.setNoticelov(noticeperiod);
		}

		String urlhireaction = appgateway.getAppgateway() + "/Action/loadAllActionByType";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlhireaction, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response7.getStatusCode() == HttpStatus.OK) {
			hireaction = response7.getBody();
			lovbinding.setHireactionlov(hireaction);
		}

		String urlgender = appgateway.getAppgateway() + "/General/loadGender";
		ResponseEntity<CommonLOV[]> response8 = restTemplate.exchange(urlgender, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response8.getStatusCode() == HttpStatus.OK) {
			gender = response8.getBody();
			lovbinding.setGenderlov(gender);
		}

		String urltitle = appgateway.getAppgateway() + "/General/loadTitle";
		ResponseEntity<CommonLOV[]> response9 = restTemplate.exchange(urltitle, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response9.getStatusCode() == HttpStatus.OK) {
			titleLov = response9.getBody();
			lovbinding.setTitleLov(titleLov);
		}

		String urlnatid = appgateway.getAppgateway() + "/NatinalId/getAllNatinalId";
		ResponseEntity<CommonLOV[]> response10 = restTemplate.exchange(urlnatid, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response10.getStatusCode() == HttpStatus.OK) {
			nationalid = response10.getBody();
			lovbinding.setNationaltypelov(nationalid);
		}

		String urlcntrycode = appgateway.getAppgateway() + "/Country/getAllCountryPhoneCode";
		ResponseEntity<CommonLOV[]> response11 = restTemplate.exchange(urlcntrycode, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response11.getStatusCode() == HttpStatus.OK) {
			countrycode = response11.getBody();
			lovbinding.setCntrycode(countrycode);
		}

		String urlphonetype = appgateway.getAppgateway() + "/General/loadPhoneType";
		ResponseEntity<CommonLOV[]> response12 = restTemplate.exchange(urlphonetype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response12.getStatusCode() == HttpStatus.OK) {
			phonetype = response12.getBody();
			lovbinding.setPhonetypelov(phonetype);
		}

		String urlemailtype = appgateway.getAppgateway() + "/General/loadEmailType";
		ResponseEntity<CommonLOV[]> response13 = restTemplate.exchange(urlemailtype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response13.getStatusCode() == HttpStatus.OK) {
			emailtype = response13.getBody();
			lovbinding.setEmailtypelov(emailtype);
		}

		String urlmaritalstat = appgateway.getAppgateway() + "/General/loadMaritalStatus";
		ResponseEntity<CommonLOV[]> response14 = restTemplate.exchange(urlmaritalstat, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response14.getStatusCode() == HttpStatus.OK) {
			maritalstatus = response14.getBody();
		}

		String urlpersontype = appgateway.getAppgateway() + "/General/loadPersonType";
		ResponseEntity<CommonLOV[]> response15 = restTemplate.exchange(urlpersontype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response15.getStatusCode() == HttpStatus.OK) {
			persontype = response15.getBody();
			lovbinding.setPersontypelov(persontype);
		}

		String urlworkercat = appgateway.getAppgateway() + "/General/loadWorkerCategory";
		ResponseEntity<CommonLOV[]> response16 = restTemplate.exchange(urlworkercat, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response16.getStatusCode() == HttpStatus.OK) {
			workercat = response16.getBody();
			lovbinding.setWorkercatlov(workercat);
		}

		String urlreligion = appgateway.getAppgateway() + "/Religion/getAllReligionLOV";
		ResponseEntity<CommonLOV[]> response17 = restTemplate.exchange(urlreligion, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response17.getStatusCode() == HttpStatus.OK) {
			religion = response17.getBody();
			lovbinding.setReligionlov(religion);
		}

		String urlmarital = appgateway.getAppgateway() + "/General/loadMaritalStatus";
		ResponseEntity<CommonLOV[]> response18 = restTemplate.exchange(urlmarital, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response18.getStatusCode() == HttpStatus.OK) {
			Maritalstatus = response18.getBody();
			lovbinding.setMaritalstatuslov(Maritalstatus);
		}

		String urlassignment = appgateway.getAppgateway() + "/AssignamentStatus/getAllAssignamentStatusLOV";
		ResponseEntity<CommonLOV[]> response19 = restTemplate.exchange(urlassignment, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response19.getStatusCode() == HttpStatus.OK) {
			assignmentstatus = response19.getBody();
			lovbinding.setAssignmentstatuslov(assignmentstatus);
		}

		String urlmanagertype = appgateway.getAppgateway() + "/ManagerType/getAllManagerTypeLOV";
		ResponseEntity<CommonLOV[]> response20 = restTemplate.exchange(urlmanagertype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response20.getStatusCode() == HttpStatus.OK) {
			managertype = response20.getBody();
			lovbinding.setManagertypelov(managertype);
		}

		String urlstate = appgateway.getAppgateway() + "/State/getAllStateByCountry/1";
		ResponseEntity<CommonLOV[]> response21 = restTemplate.exchange(urlstate, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response21.getStatusCode() == HttpStatus.OK) {
			state = response21.getBody();
			lovbinding.setStatelov(state);
		}

		
		String urlworkingmanager = appgateway.getAppgateway() + "/General/loadYesNo";
		ResponseEntity<CommonLOV[]> response24 = restTemplate.exchange(urlworkingmanager, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response24.getStatusCode() == HttpStatus.OK) {
			workingmanager = response24.getBody();
			lovbinding.setWorkingmanagerlov(workingmanager);

		}
		
		CommonLOV[] freq=null;
		String urlfreq = appgateway.getAppgateway() + "/General/loadFrequncy";
		ResponseEntity<CommonLOV[]> response32 = restTemplate.exchange(urlfreq, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response32.getStatusCode() == HttpStatus.OK) {
			freq = response32.getBody();
			lovbinding.setFreqLOV(freq);
		}
		
		CommonLOV[] saltype=null;
		String urlsaltype = appgateway.getAppgateway() + "/General/loadSalaryType";
		ResponseEntity<CommonLOV[]> response33 = restTemplate.exchange(urlsaltype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response33.getStatusCode() == HttpStatus.OK) {
			saltype = response33.getBody();
			lovbinding.setSaltypeLOV(saltype);
		}

		CommonLOV[] hirereason=null;
		String urlhirereason = appgateway.getAppgateway() + "/ActionReason/getAllReasonByActionId/1";
		ResponseEntity<CommonLOV[]> responsehire = restTemplate.exchange(urlhirereason, HttpMethod.GET, request,
				CommonLOV[].class);
		if (responsehire.getStatusCode() == HttpStatus.OK) {
			hirereason = responsehire.getBody();
			//lovbinding = (Lovbinding)req.getSession().getAttribute("lovbinding");
			lovbinding.setHirereasonlov(hirereason);
		}

		
		
		
		model.addAttribute("acctype", BankType.values());
		model.addAttribute("prim","true");
		model.addAttribute("type",saltype);
		model.addAttribute("freq",freq);

		model.addAttribute("workingmanager", workingmanager);
		//model.addAttribute("hirereason", hirereason);
		model.addAttribute("state", state);
		model.addAttribute("managertype", managertype);
		model.addAttribute("assignstatus", assignmentstatus);
		model.addAttribute("Maritalstatus", Maritalstatus);
		// model.addAttribute("lovbinding", lovbinding);
		model.addAttribute("religion", religion);
		model.addAttribute("workercat", workercat);
		model.addAttribute("persontype", persontype);
		model.addAttribute("maritalstatus", maritalstatus);
		model.addAttribute("emailtype", emailtype);
		model.addAttribute("phonetype", phonetype);
		model.addAttribute("countrycode", countrycode);
		model.addAttribute("natid", nationalid);
		// model.addAttribute("title", title);
		model.addAttribute("gender", gender);
		model.addAttribute("hireaction", hireaction);
		model.addAttribute("assignCat", assignCat);
		model.addAttribute("regortemp", regortemp);
		model.addAttribute("fullorpart", fullorpart);
		model.addAttribute("workertype", workertype);
		model.addAttribute("probperiod", probperiod);
		model.addAttribute("noticeperiod", noticeperiod);
		model.addAttribute("CountryDetails", countryDetailsObj);
		model.addAttribute("nature_of_employement", nature_of_employement);
		model.addAttribute("employee_category", employee_category);
		model.addAttribute("staff", staff);
		model.addAttribute("projectom", projectom);
		// model.addAttribute("yesorno", Identifying.values());
		model.addAttribute("status", Status.values());
		//model.addAttribute("type", SalaryType.values());

		req.getSession().setAttribute("lovbinding", lovbinding);

		return "fragments/hireEmployee :: hireEmployee";
	}

	
	
	@ResponseBody
	@RequestMapping("/posbind")
	public Position[] bindPos(HttpServletRequest requestFromDT) {

		String searchcode = requestFromDT.getParameter("code");
		String searchname = requestFromDT.getParameter("name");
		String searchstatus = requestFromDT.getParameter("status");
		String buid = requestFromDT.getParameter("buId");
		String depid = requestFromDT.getParameter("deptid");

		//System.out.println("Inside SearchPositionWithBUID Controller");

		String url = appgateway.getAppgateway() + "/Position/getPositionByBUAndDepartemtId";
		Position[] posObj = null;
		String payload = "{" + "\"buId\"" + ":\"" + buid + "\"," + "\"code\"" + ":\"" + searchcode + "\"," + "\"name\""
				+ ":\"" + searchname + "\"," + "\"status\"" + ":\"" + searchstatus + "\"," + "\"deptId\"" + ":\"" + depid + "\"}";
	//	System.out.println("Payload::::" + payload);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<Position[]> response = restTemplate.exchange(url, HttpMethod.POST, request, Position[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			posObj = response.getBody();

		}

		//System.out.println(posObj.toString());
		return posObj;

	}

	@ResponseBody
	@RequestMapping("/pnumcheck/{newpnum}")
	public String pnumcheck(HttpServletRequest requestFromDT, @PathVariable("newpnum") String newpnum) {

		String url = appgateway.getAppgateway() + "/PersonData/PersonalRecord/checkPersonNumber/"+newpnum;

		SingleResponseModel res = new SingleResponseModel();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.GET, request, SingleResponseModel.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		}

		return res.getStatus();

	}


	@RequestMapping("/EditEmployemntDetails/updateDetails")
	public String EditEmploymentDetails(Model model) {

		//System.out.println("Edite Employement Details");

		return "fragments/EditEmployementDetails :: EditEmployement";

	}

	@RequestMapping("/editPersonInfo")
	public String editPersonInfo(Model model) {

		//System.out.println("Hello Person");

		model.addAttribute("status", Status.values());
		return "fragments/personEdit :: personEdit";
	}

//	@PostMapping("/nextandsaveperson")
//	public String newperson(@ModelAttribute("newperson") Newperson newperson,Model model, String cmd,HttpServletRequest req)
//	{
//		System.out.println("newperson : " + newperson.getNationaldetails().get(0).getNationalid());
//		
//		//String urlsave=appgateway.getAppgateway()+"/PersonData/PersonalRecord/savePersonalRecord";
//		String urlsave="http://192.200.12.83:8182/PersonData/PersonalRecord/savePersonalRecord";
//		System.out.println("url :"+urlsave);
//		
//		SingleResponseModel res = new SingleResponseModel();
//		if(cmd.equals("Save") )
//		{
//			System.out.println("save :");
//              HttpEntity<Newperson> request = new HttpEntity<Newperson>(newperson, headers);
//			
//			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlsave,HttpMethod.POST,request, SingleResponseModel.class);
//			if(response.getStatusCode() == HttpStatus.OK) {
//				res = response.getBody();
//				System.out.print("DATA:::::::::::::::::::"+res);
//			} else {
//				System.out.println("Request Failed");
//				System.out.println(response.getStatusCode());
//			}
//			
//		}
//		else
//		{
//			model.addAttribute("lovbinding",req.getSession().getAttribute("lovbinding"));
//			model.addAttribute("newperson",newperson);
//
//		}
//		System.out.println("Master Data :::::::::::::::::::"+newperson.toString());
//		System.out.println("Personal Recorde Data :::::::::::::::::::"+newperson.getPersonalrecord().toString());
//
//		System.out.println("Job data Data :::::::::::::::::::"+newperson.getJobdata().toString());
//
//		System.out.println("Address Details Data :::::::::::::::::::"+newperson.getAddressdetails().toString());
//
//		System.out.println("National Details Data :::::::::::::::::::"+newperson.getNationaldetails().toString());
//
//		System.out.println("Marrital Details Data :::::::::::::::::::"+newperson.getMaritaldetails().toString());
//		System.out.println("Email  Details Data :::::::::::::::::::"+newperson.getEmaildetails().toString());
//		System.out.println("Phone Details Data :::::::::::::::::::"+newperson.getContactdetails().toString());
//
//		//System.out.println("newperson : " + req.getSession().getAttribute("lovbinding"));
//		return "fragments/hireEmployee :: "+cmd+"";
//	}

	@ModelAttribute("newperson")
	private Newperson getNewperson() {
		return new Newperson();
	}

	/************************* HIRE EMPLOYEE CONTROLLER ***********************/

	@ResponseBody
	@RequestMapping("/actionreasonbind/{selectHireActionId}")
	public CommonLOV[] reasonByActionId(@PathVariable("selectHireActionId") String actionid, Model model) {

		CommonLOV[] sd1 = null;
		// http://192.200.12.83:8181/ActionReason/getAllReasonByActionId/1
		String urlp = appgateway.getAppgateway() + "/ActionReason/getAllReasonByActionId/" + actionid;

		// headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlp, HttpMethod.GET, request, CommonLOV[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd1 = response.getBody();

		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		model.addAttribute("reasonbyaction", sd1);

		return sd1;

	}

	
	
	/****************************************************************************/

	@GetMapping("/removechild/{index}/{flg}")
	public @ResponseBody String removeChild(@SessionAttribute("newperson") Newperson newperson, @PathVariable int index,@PathVariable String flg)
	{
		
		//System.out.println("flg : " + flg);
		if(flg.equals("national"))
		{
			newperson.getNationaldetails().remove(index);

		}
		else if(flg.equals("phone"))
		{
			newperson.getContactdetails().remove(index);

		}
		else if(flg.equals("email"))
		{
			newperson.getEmaildetails().remove(index);

		}
		else if(flg.equals("mailUncheck"))
		{
			//System.out.println("inside mail_uncheck");
			for(int i=0;i<newperson.getEmaildetails().size();i++) {
				if(i!=index) {
					newperson.getEmaildetails().get(i).setPrimary("No");
				}
				
			}

		}
		else if(flg.equals("natUncheck"))
		{
			//System.out.println("inside Nat_uncheck");
			for(int j=0;j<newperson.getNationaldetails().size();j++) {
				if(j!=index) {
					newperson.getNationaldetails().get(j).setPrimary("No");
				}
				
			}
			

		}
		else if(flg.equals("phoneUncheck"))
		{
			//System.out.println("inside phone_uncheck");
			for(int k=0;k<newperson.getContactdetails().size();k++) {
				if(k!=index) {
					newperson.getContactdetails().get(k).setPrimary("No");
				}
				
			}
			

		}
		return "removed";
	}

		@PostMapping("/nextandsaveperson")
	public String newperson(@ModelAttribute("newperson")  Newperson newperson, BindingResult bindingResult,
		Model model, @ModelAttribute("lovbinding") Lovbinding lovbinding, String cmd,String currentcmd,String hidLegal,String hidBU, String hidJOB, String hidDEPT, String hidPOS, String hidLOC, String hidGRADE, String hidNatofEmpl, HttpServletRequest req, SessionStatus status) {
		// urlsave=appgateway.getAppgateway()+"/PersonData/PersonalRecord/savePersonalRecord";
			model.addAttribute("finalres","0");
			String urlsave = appgateway.getAppgateway()+"/PersonData/PersonalRecord/savePersonalRecord";
			model.addAttribute("status", Status.values());
			model.addAttribute("type", SalaryType.values());
			model.addAttribute("freq", Frequency.values());
			model.addAttribute("acctype", BankType.values());
		//System.out.println("newperson : " + newperson.getNationaldetails().get(0).getNationalid());
			SingleResponseModel res = new SingleResponseModel();
			//System.out.println("legal hd: " + newperson.getLegalentityname());
		//	System.out.println("url :" + urlsave);
			/*
			CommonLOV[] hirereason=null;
			
			if(flg==0 && newperson.getJobdata().getHireaction()!=0) {
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requesthire = new HttpEntity<String>(headers);
			
			//System.out.println(newperson.getJobdata().getHireaction());
			String urlhirereason = appgateway.getAppgateway() + "/ActionReason/getAllReasonByActionId/"+newperson.getJobdata().getHireaction();
			ResponseEntity<CommonLOV[]> responsehire = restTemplate.exchange(urlhirereason, HttpMethod.GET, requesthire,
					CommonLOV[].class);
			if (responsehire.getStatusCode() == HttpStatus.OK) {
				hirereason = responsehire.getBody();
				lovbinding = (Lovbinding)req.getSession().getAttribute("lovbinding");
				lovbinding.setHirereasonlov(hirereason);
			}
			flg+=1;
			}*/
			//System.out.println(hirereason.toString());
			//System.out.println(lovbinding.getHirereasonlov().toString());
			//req.getSession().setAttribute("lovbinding",lovbinding);
			model.addAttribute("lovbinding", req.getSession().getAttribute("lovbinding"));

			model.addAttribute("newperson", newperson);
			//System.out.println("currentcmd :" + currentcmd);

			if(currentcmd.equals("identificationdetail")) {
			newperson.setLegalentityname(hidLegal);
			newperson.setNaturename(hidNatofEmpl);
			//System.out.println("Nature of Employment"+newperson.getNaturename());
			validator.validate(newperson.getPersonalrecord(), bindingResult);
			validator.validate(newperson.getUpmrcaddon(), bindingResult);
			//validator.validate(newperson.getJobdata().getHiredate(),bindingResult);
			for (NationalDetails national : newperson.getNationaldetails()) {
				 validator.validate(national, bindingResult);
				//System.out.println("cmd=" + bindingResult.getFieldError("nationaltype").getDefaultMessage());
			}

			//bindingResult.addError(new FieldError("bindingResult",));
			//System.out.println("ERROR :: " + bindingResult.getAllErrors().toString());

			model.addAttribute("bindingResult", bindingResult);

			boolean natprim=false;
			System.out.println("Size :: "+newperson.getNationaldetails().size());
			for(int z=0;z<newperson.getNationaldetails().size();z++){
				System.out.println("Primary Value :: "+newperson.getNationaldetails().get(z).getPrimary());
				if(newperson.getNationaldetails().get(z).getPrimary().equals("Yes")){
					natprim=true;
					break;
				}
			}
			if(!natprim){
				System.out.println("All Primary uncheck.");
				model.addAttribute("prim","false");
				return "fragments/hireEmployee :: " + currentcmd;
			}else{
				natprim=false;
			}

			//System.out.println("ERROR :: " + bindingResult.getFieldError("legalentity").getDefaultMessage());
			/*if (bindingResult.getFieldError("personalrecord.title") != null) {
				System.out.println("ERROR :: " + bindingResult.getFieldError("personalrecord.title").getDefaultMessage());


			}*/
			//System.out.println("Country :: "+bindingResult.getFieldError("addressdetails[0].country").getDefaultMessage() );
			if (bindingResult.hasErrors()) {
				//System.out.println("cmd=" + bindingResult.getFieldError("title").getDefaultMessage());
				return "fragments/hireEmployee :: " + currentcmd;

			}

		}
		else if(currentcmd.equals("personaldetail")) {
			//validator.validate(newperson.getAddressdetails(),bindingResult);

////			validator.validate(newperson.getMaritaldetails(),bindingResult);
			
			for (AddressDetails addressDetails : newperson.getAddressdetails()) {
				validator.validate(addressDetails, bindingResult);
			}
			
			model.addAttribute("bindingResult", bindingResult);
			//System.out.println("errot=" + bindingResult.getFieldError("maritalstatus").getDefaultMessage());
			if (bindingResult.hasErrors()) {
				//System.out.println("cmd=" + bindingResult.getFieldError("title").getDefaultMessage());
				return "fragments/hireEmployee :: " + currentcmd;

			}

		}else if(currentcmd.equals("employmentInformation")) {
			newperson.setBuname(hidBU);
			newperson.setJobname(hidJOB);
			newperson.setDepartname(hidDEPT);
			newperson.setPositionname(hidPOS);
			newperson.setLocationname(hidLOC);
			validator.validate(newperson.getJobdata(), bindingResult);
			model.addAttribute("bindingResult", bindingResult);
			//System.out.println("BINDING :: "+bindingResult.getAllErrors().toString());
			if (bindingResult.hasErrors()) {
				return "fragments/hireEmployee :: " + currentcmd;
			}
		}else if(currentcmd.equals("compensation")) {
			newperson.setGradename(hidGRADE);
		}
		if (cmd.equals("Save")) {
			//System.out.println("MODEL :: " + newperson.toString());

			//System.out.println("save :");
			Login login= (Login) req.getSession().getAttribute("login");
			newperson.getJobdata().setCreatedby(login.getEmplid());
			newperson.getJobdata().setUpdatedby(login.getEmplid());

			if (newperson.getPersonnumber().equals("") || newperson.getPersonnumber().equals(null)){
				model.addAttribute("finalres","1");
				return "fragments/hireEmployee :: " + currentcmd;
			}
			pernum=newperson.getPersonnumber();

			//System.out.println("Master Data :::::::::::::::::::" + newperson.toString());
			//System.out.println("Personal Recorde Data :::::::::" + newperson.getPersonalrecord().toString());
			//System.out.println("Job data Data :::::::::::::::::" + newperson.getJobdata().toString());
			//System.out.println("Address Details Data ::::::::::" + newperson.getAddressdetails().toString());
			//System.out.println("National Details Data :::::::::" + newperson.getNationaldetails().toString());
			//System.out.println("Marital Details Data :::::::::" + newperson.getMaritaldetails().toString());
			//System.out.println("Email  Details Data :::::::::::" + newperson.getEmaildetails().toString());
			//System.out.println("Contact Details Data ::::::::::" + newperson.getContactdetails().toString());
			//System.out.println("Manager Type Data :::::::::::::" + newperson.getManagertypes().toString());
			//System.out.println("Additional Details ::::::::::::" + newperson.getUpmrcaddon().toString());
			//System.out.println("Compensaton :::::::::::::::::::" + newperson.getSalarybasis().toString());
			//System.out.println("BAnk Details ::::::::::::::::::" + newperson.getBankaccountdetails().toString());

			HttpEntity<Newperson> request = new HttpEntity<Newperson>(newperson, headers);
			ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlsave, HttpMethod.POST, request,
					SingleResponseModel.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
				System.out.print("DATA:::::::::::::::::::" + res);
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			//System.out.println(res.getStatus());

			
			if(res.getStatus().equals("Success")) {
				flg=0;
				status.setComplete();
				model.addAttribute("finalres","You have successfully saved the hiring data.");
				return "fragments/hireEmployee :: hireEmployee";
				//session.removeAttribute("newperson");
				//session.removeAttribute("lovbinding");
			}

		} 
		
		//System.out.println("Master Data :::::::::::::::::::" + newperson.toString());
		//System.out.println("Personal Recorde Data :::::::::" + newperson.getPersonalrecord().toString());
		//System.out.println("Job data Data :::::::::::::::::" + newperson.getJobdata().toString());
		//System.out.println("Address Details Data ::::::::::" + newperson.getAddressdetails().toString());
		//System.out.println("National Details Data :::::::::" + newperson.getNationaldetails().toString());
		//System.out.println("Marital Details Data :::::::::" + newperson.getMaritaldetails().toString());
		//System.out.println("Email  Details Data :::::::::::" + newperson.getEmaildetails().toString());
		//System.out.println("Contact Details Data ::::::::::" + newperson.getContactdetails().toString());
		//System.out.println("Manager Type Data :::::::::::::" + newperson.getManagertypes().toString());
		//System.out.println("Additional Details ::::::::::::" + newperson.getUpmrcaddon().toString());
		//System.out.println("Compensaton :::::::::::::::::::" + newperson.getSalarybasis().toString());
		
		return "fragments/hireEmployee :: " + cmd + "";
	}
	
	@PostMapping()
	public String personalDetail()
	{
		return "";
	}

	/******************************************************************************/



	@ResponseBody
	@RequestMapping("/managerbind")
	public SupervisorSearchresult[] bindMgr(HttpServletRequest requestFromDT, @ModelAttribute("newperson")  Newperson newperson) {

		String pernbr = requestFromDT.getParameter("pernbr");
		String name = requestFromDT.getParameter("firstname");
		String hiredate = requestFromDT.getParameter("hiredate");
		//Newperson newperson=new Newperson();

		//requestFromDT.getSession().getAttribute("newperson");

		//System.out.println("Insidemanagerbind Controller");

		String url = appgateway.getAppgateway() + "/SuperviorsLov/getSuperviorsLovList";
		SupervisorSearchresult[] mgrObj = null;
		String payload = "{" + "\"name\"" + ":\"" + name + "\"," + "\"newpersonhiredate\""
				+ ":\"" +newperson.getJobdata().getHiredate()+ "\"," + "\"personid\"" + ":\"\"," + "\"personnumber\"" + ":\"" + pernbr + "\"}";
		//System.out.println("Payload::::" + payload);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<SupervisorSearchresult[]> response = restTemplate.exchange(url, HttpMethod.POST, request, SupervisorSearchresult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			mgrObj = response.getBody();

			//System.out.println("mgrObj::::" + mgrObj);
		}

		return mgrObj;

	}
	
	
	@GetMapping("/removemanager/{index}")
	public @ResponseBody String removeChild(@SessionAttribute("newperson") Newperson newperson, @PathVariable int index)
	{
			//System.out.println("flg : " + flg);
			newperson.getManagertypes().remove(index);
		return "removed";
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
		lovbinding.setGradesteplov(sd1);
		//System.out.println(sd1.toString());

		//model.addAttribute("reasonbyaction", sd1);

		return sd1;

	}

	@RequestMapping("/openelementassignment")
	public String eleassgn(Model model){
		System.out.println("PERSON ::"+pernum);
		model.addAttribute("pnum",pernum);
		pernum="";
		return "forms/payroll/transactions/elementAssignment :: elementAssignment";
	}


}
