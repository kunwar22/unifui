package in.co.srdt.unif.controllers.reimbursement;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import in.co.srdt.unif.controllers.SendMail;
import in.co.srdt.unif.model.approvalnotification.ApproverDetails;
import in.co.srdt.unif.model.user.absence.CommonDescription;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import in.co.srdt.unif.enums.MediAdvance;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.medicaladvance.MedHospitalTypeLOVModel;
import in.co.srdt.unif.model.medicaladvance.MedicalAdvanceModel;
import in.co.srdt.unif.model.medicaladvance.MedicalAdvanceSearchModel;
import in.co.srdt.unif.model.medicaladvance.MedicalReimDependentModel;
import in.co.srdt.unif.model.rembchild.ApprovalChildModel;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/reimbursement")
public class MedicalAdvanceController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	HttpHeaders headers;

	@Autowired
	ApplicationGateway appGateway;

	@Autowired
	SmartValidator validator;

	@Autowired(required = true)
	private EligibilityReimbursement eligibilityreimb;

	@Autowired(required = true)
	private SendMail sendnotimail;

	Login userlogin = null;
	String RID = "9";

/**** SEARCH PAGE ****/
	@RequestMapping("/medicalAdvance")
	public String medicalAdvance(HttpSession session, HttpServletRequest request, Model model) 
	{

		userlogin = (Login) request.getSession().getAttribute("login");
		PersonInformation empdetails = new PersonInformation();
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"+userlogin.getEmplid();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> emprequest = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) 
		{
			empdetails = employeePersonalDetailResponse.getBody();
		}

		model.addAttribute("personInfo", empdetails);
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("pname",empdetails.getPersonname());

		return "forms/reimbursement/medicalAdvance/medicalAdvanceSearch :: medicalAdvanceSearch";
	}

/**** NEW PAGE ****/
	@RequestMapping("/medicaladvancerequest")
	public String createMedicalAdvanceRequest(HttpSession session, HttpServletRequest request, Model model) 
	{
		userlogin = (Login) request.getSession().getAttribute("login");
		
		/*String RID = "9";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, userlogin.getEmplid());
		model.addAttribute("empent", ceiling);*/
		headers.setContentType(MediaType.APPLICATION_JSON);
		PersonInformation empdetails = new PersonInformation();
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();
		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) 
		{
			empdetails = employeePersonalDetailResponse.getBody();
		}
		model.addAttribute("personInfo", empdetails);
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("pname",empdetails.getPersonname());
		CommonLOV[] illnesslov = null;

		String urlIllness = appGateway.getAppgatewaypyrl_sandhya() + "/api/IllnessType/getillnesTypeLOV";
		HttpEntity<String> illnessrequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> illnessLovResponse = restTemplate.exchange(urlIllness, HttpMethod.GET,
				illnessrequest, CommonLOV[].class);
		if (illnessLovResponse.getStatusCode() == HttpStatus.OK) {
			illnesslov = illnessLovResponse.getBody();
		}
		model.addAttribute("illness", illnesslov);

		MedicalReimDependentModel[] mediremdepmodel = null;
		String urlMediDep = appGateway.getAppgateway() + "/PersonManagement/DependentDetails/getDependentDetailsForMedical/"+ userlogin.getEmplid();
		HttpEntity<String> medideprequest = new HttpEntity<String>(headers);
		ResponseEntity<MedicalReimDependentModel[]> medidepresponse = restTemplate.exchange(urlMediDep, HttpMethod.GET,
				medideprequest, MedicalReimDependentModel[].class);
		if (medidepresponse.getStatusCode() == HttpStatus.OK) {
			mediremdepmodel = medidepresponse.getBody();
		}
		model.addAttribute("medidep", mediremdepmodel);

		MedHospitalTypeLOVModel[] medhostype = null;
		String urlHospitalType = appGateway.getAppgatewaypyrl_sandhya() + "/api/hospitallov/gethospitaltypelov";
		HttpEntity<String> hostyperequest = new HttpEntity<String>(headers);
		ResponseEntity<MedHospitalTypeLOVModel[]> medhosresponse = restTemplate.exchange(urlHospitalType,
				HttpMethod.GET, hostyperequest, MedHospitalTypeLOVModel[].class);
		if (medhosresponse.getStatusCode() == HttpStatus.OK) {
			medhostype = medhosresponse.getBody();
		}
		model.addAttribute("hostype", medhostype);

		MedicalAdvanceModel mediadvmodel = new MedicalAdvanceModel();
		model.addAttribute("mediadvmodel", mediadvmodel);
		model.addAttribute("mediadvance", MediAdvance.values());
		model.addAttribute("reimbursename", "Medical Advance");
		
		return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
	}

	@ResponseBody
	@RequestMapping("/getHospitalNames/{hospid}")
	public MedHospitalTypeLOVModel medhostyplovmodel(@PathVariable("hospid") String hospitalid, HttpSession session,
			HttpServletRequest request, Model model) 
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		MedHospitalTypeLOVModel hosname = null;
		String urlHospitalNames = appGateway.getAppgatewaypyrl_sandhya() + "/api/hospitallov/gethospitaltypelovbyid/"
				+ hospitalid;
		HttpEntity<String> hosnamerequest = new HttpEntity<String>(headers);
		ResponseEntity<MedHospitalTypeLOVModel> medhosnameresponse = restTemplate.exchange(urlHospitalNames,
				HttpMethod.GET, hosnamerequest, MedHospitalTypeLOVModel.class);
		if (medhosnameresponse.getStatusCode() == HttpStatus.OK) {
			hosname = medhosnameresponse.getBody();
		}
		//System.out.println("Hospital Names : " + hosname.toString());

		return hosname;
	}


	@PostMapping(value = "/requestMedicalAdvance")
	public String requestMedicalAdvance(@ModelAttribute MedicalAdvanceModel mediadvmodel,
			@RequestParam("file") MultipartFile file, String statusid, HttpServletRequest request, Model model,
			BindingResult bindingResult) throws IOException, MessagingException {
		model.addAttribute("mediadvmodel", mediadvmodel);
		userlogin = (Login) request.getSession().getAttribute("login");
		//System.out.println("mediadvmodel::::::::"+mediadvmodel.toString());
		/*String RID = "9";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, userlogin.getEmplid());
		model.addAttribute("empent", ceiling);*/
		
		userlogin = (Login) request.getSession().getAttribute("login");
		headers.setContentType(MediaType.APPLICATION_JSON);

		PersonInformation empdetails = new PersonInformation();
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			empdetails = employeePersonalDetailResponse.getBody();
		}
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", empdetails);
		model.addAttribute("pname",empdetails.getPersonname());

		CommonLOV[] illnesslov = null;

		String urlIllness = appGateway.getAppgatewaypyrl_sandhya() + "/api/IllnessType/getillnesTypeLOV";
		HttpEntity<String> illnessrequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> illnessLovResponse = restTemplate.exchange(urlIllness, HttpMethod.GET,
				illnessrequest, CommonLOV[].class);
		if (illnessLovResponse.getStatusCode() == HttpStatus.OK) 
		{
			illnesslov = illnessLovResponse.getBody();
		}
		model.addAttribute("illness", illnesslov);

		MedicalReimDependentModel[] mediremdepmodel = null;
		String urlMediDep = appGateway.getAppgateway() + "/PersonManagement/DependentDetails/getDependentDetailsForMedical/"+ userlogin.getEmplid();
		HttpEntity<String> medideprequest = new HttpEntity<String>(headers);
		ResponseEntity<MedicalReimDependentModel[]> medidepresponse = restTemplate.exchange(urlMediDep, HttpMethod.GET,
				medideprequest, MedicalReimDependentModel[].class);
		if (medidepresponse.getStatusCode() == HttpStatus.OK) 
		{
			mediremdepmodel = medidepresponse.getBody();
		}
		model.addAttribute("medidep", mediremdepmodel);
		
		MedHospitalTypeLOVModel[] medhostype = null;
		String urlHospitalType = appGateway.getAppgatewaypyrl_sandhya() + "/api/hospitallov/gethospitaltypelov";
		HttpEntity<String> hostyperequest = new HttpEntity<String>(headers);
		ResponseEntity<MedHospitalTypeLOVModel[]> medhosresponse = restTemplate.exchange(urlHospitalType,
				HttpMethod.GET, hostyperequest, MedHospitalTypeLOVModel[].class);
		if (medhosresponse.getStatusCode() == HttpStatus.OK) 
		{
			medhostype = medhosresponse.getBody();
		}
		model.addAttribute("hostype", medhostype);
		
		model.addAttribute("mediadvance", MediAdvance.values());
		
		validator.validate(mediadvmodel, bindingResult);
		model.addAttribute("bindingResult", bindingResult);
		model.addAttribute("mediadvmodel", mediadvmodel);
		
		/****ADDED FOR HOSPITAL TYPE LOV VALUE ON PAGE AFTER VALIDATION ERROR BY UTSAV****/
		
		if(mediadvmodel.getHospitaltypeid()!=0) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		MedHospitalTypeLOVModel hosname = null;
		String urlHospitalNames = appGateway.getAppgatewaypyrl_sandhya() + "/api/hospitallov/gethospitaltypelovbyid/"
				+mediadvmodel.getHospitaltypeid() ;
		HttpEntity<String> hosnamerequest = new HttpEntity<String>(headers);
		ResponseEntity<MedHospitalTypeLOVModel> medhosnameresponse = restTemplate.exchange(urlHospitalNames,
				HttpMethod.GET, hosnamerequest, MedHospitalTypeLOVModel.class);
		if (medhosnameresponse.getStatusCode() == HttpStatus.OK) {
			hosname = medhosnameresponse.getBody();
		}
		
		/********************************************************************************/
		
		model.addAttribute("hospitalname",hosname.getHospitalnamelov());
		}
		
		if (bindingResult.hasErrors()) 
		{	
			return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
		}

		String location = mediadvmodel.getAttachments();
		String filePath = new File("").getAbsolutePath() + File.separator + location;
		String storePath = location;

		if (!file.isEmpty()) 
		{
			// CREATE DIRECTORY IF NOT EXISTS
			File dir = new File(filePath);
			
			if (!dir.exists()) 
			{
				dir.mkdirs();
			}

			// WRITE FILES TO DIRECTORY AND ENTRY INTO THE DATABASE
			try
			{
				// CREATING UNIQUE FILENAME
				String empl = userlogin.getEmplid();
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				String generatedFileName = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss" + "'" + empl + "." + extension + "'").format(new Date());
				
				File target = new File(filePath + File.separator + generatedFileName);
				int readByteCount = 0;
				byte[] buffer = new byte[4096];

				BufferedInputStream in = new BufferedInputStream(file.getInputStream());
				FileOutputStream out = new FileOutputStream(target);
				
				while ((readByteCount = in.read(buffer)) != -1) 
				{
					out.write(buffer, 0, readByteCount);
				}
				storePath += "/" + generatedFileName;
				mediadvmodel.setAttachments(storePath);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
				model.addAttribute("result", "IOEXCEPTION");
				return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
			}
			catch (IllegalArgumentException e) 
			{
				e.printStackTrace();
				model.addAttribute("result", "ILLEGALARG");
				return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
			} catch (Exception e)
			{
				e.printStackTrace();
				model.addAttribute("result", "WRITE_ERROR");
				return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
			}
		} 
		else 
		{
			mediadvmodel.setAttachments(mediadvmodel.getAttachhidden());
		}
		
		String urlmediadvsave = appGateway.getAppgatewaypyrl_sandhya()+"/api/medicaladvance/claimmedicaladvance";
		
		MedicalAdvanceModel medicaladvmod = null;
		
		HttpEntity<MedicalAdvanceModel> medadvreq = new HttpEntity<MedicalAdvanceModel>(mediadvmodel, headers);
		
		ResponseEntity<MedicalAdvanceModel> mediadvresponse = restTemplate.exchange(urlmediadvsave, HttpMethod.POST, medadvreq,
				MedicalAdvanceModel.class);
		
		if (mediadvresponse.getStatusCode() == HttpStatus.OK) 
		{
			medicaladvmod = mediadvresponse.getBody();
			//System.out.println("medical adv model :::::: "+medicaladvmod);
		} 
		else 
		{
			//System.out.println(mediadvresponse.getStatusCode());
			model.addAttribute("result", "LOG_ERROR");
			return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
		}

		model.addAttribute("personInfo", empdetails);
		model.addAttribute("pname",empdetails.getPersonname());
		model.addAttribute("illness", illnesslov);
		model.addAttribute("medidep", mediremdepmodel);
		model.addAttribute("hostype", medhostype);
		model.addAttribute("mediadvance", MediAdvance.values());
		model.addAttribute("mediadvmodel", mediadvmodel);
		model.addAttribute("reimbursename", "Medical Advance");
		model.addAttribute("result",medicaladvmod.getSubmitStatus());



		return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
	}


	@ResponseBody
	@GetMapping("/getMedicalAdvances")
	public MedicalAdvanceSearchModel[] getMedicalAdvancesList(HttpSession session, HttpServletRequest request, Model model)
	{
		userlogin = (Login) request.getSession().getAttribute("login"); // fetching Persion Number

		headers.setContentType(MediaType.APPLICATION_JSON);
		
		MedicalAdvanceSearchModel[] mediadvsrch = null;
		
		String urlMediAdvSearch = appGateway.getAppgatewaypyrl_sandhya()+"/api/medicaladvance/getmedicaladvancebypersonnumber/"+userlogin.getEmplid();
		HttpEntity<String> mediadv_search_request = new HttpEntity<String>(headers);
		
		ResponseEntity<MedicalAdvanceSearchModel[]> mediadvresponse = restTemplate.exchange(urlMediAdvSearch, HttpMethod.GET,mediadv_search_request,MedicalAdvanceSearchModel[].class);
		
		if (mediadvresponse.getStatusCode() == HttpStatus.OK) {
			mediadvsrch = mediadvresponse.getBody();			
		}
		
		return mediadvsrch;
	}
	
	@RequestMapping("/modifyMedicalAdvance/{mediclaimid}/{display_mode}")
	public String modifyMedicalAdvance(@PathVariable("mediclaimid") int mediclaimid,
			@PathVariable("display_mode") String display_mode, Model model, HttpServletRequest requestdata)
	{
		userlogin = (Login) requestdata.getSession().getAttribute("login");
		
		/*String RID = "9";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, userlogin.getEmplid());
		model.addAttribute("empent", ceiling);*/
		model.addAttribute("reimbursename", "Medical Advance");
		headers.setContentType(MediaType.APPLICATION_JSON);

		PersonInformation empdetails = new PersonInformation();

		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();
		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			empdetails = employeePersonalDetailResponse.getBody();
		}
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", empdetails);
		model.addAttribute("pname",empdetails.getPersonname());

		CommonLOV[] illnesslov = null;

		String urlIllness = appGateway.getAppgatewaypyrl_sandhya() + "/api/IllnessType/getillnesTypeLOV";
		HttpEntity<String> illnessrequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> illnessLovResponse = restTemplate.exchange(urlIllness, HttpMethod.GET,
				illnessrequest, CommonLOV[].class);
		if (illnessLovResponse.getStatusCode() == HttpStatus.OK) {
			illnesslov = illnessLovResponse.getBody();
		}
		model.addAttribute("illness", illnesslov);

		MedicalReimDependentModel[] mediremdepmodel = null;
		String urlMediDep = appGateway.getAppgateway() + "/PersonManagement/DependentDetails/getDependentDetailsForMedical/"+ userlogin.getEmplid();
		HttpEntity<String> medideprequest = new HttpEntity<String>(headers);
		ResponseEntity<MedicalReimDependentModel[]> medidepresponse = restTemplate.exchange(urlMediDep, HttpMethod.GET,
				medideprequest, MedicalReimDependentModel[].class);
		if (medidepresponse.getStatusCode() == HttpStatus.OK) {
			mediremdepmodel = medidepresponse.getBody();
		}
		model.addAttribute("medidep", mediremdepmodel);

		MedHospitalTypeLOVModel[] medhostype = null;
		String urlHospitalType = appGateway.getAppgatewaypyrl_sandhya() + "/api/hospitallov/gethospitaltypelov";
		HttpEntity<String> hostyperequest = new HttpEntity<String>(headers);
		ResponseEntity<MedHospitalTypeLOVModel[]> medhosresponse = restTemplate.exchange(urlHospitalType,
				HttpMethod.GET, hostyperequest, MedHospitalTypeLOVModel[].class);
		if (medhosresponse.getStatusCode() == HttpStatus.OK) {
			medhostype = medhosresponse.getBody();
		}
		model.addAttribute("hostype", medhostype);

		MedicalAdvanceModel mediadvmodel = null;
		
		String urlmediclaim = appGateway.getAppgatewaypyrl_sandhya() +"/api/medicaladvance/getmedicaladvancebyid/"+mediclaimid;
		HttpEntity<String> get_mediclaim_request = new HttpEntity<String>(headers);

		ResponseEntity<MedicalAdvanceModel> mediclaimresponse = restTemplate.exchange(urlmediclaim, HttpMethod.GET, get_mediclaim_request,
				MedicalAdvanceModel.class);

		if (mediclaimresponse.getStatusCode() == HttpStatus.OK) {
			mediadvmodel = mediclaimresponse.getBody();
		} else {
			System.out.println("Request Failed with response code : " + mediclaimresponse.getStatusCode());
		}
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		MedHospitalTypeLOVModel hosname = null;
		String urlHospitalNames = appGateway.getAppgatewaypyrl_sandhya() + "/api/hospitallov/gethospitaltypelovbyid/"
				+mediadvmodel.getHospitaltypeid() ;
		HttpEntity<String> hosnamerequest = new HttpEntity<String>(headers);
		ResponseEntity<MedHospitalTypeLOVModel> medhosnameresponse = restTemplate.exchange(urlHospitalNames,
				HttpMethod.GET, hosnamerequest, MedHospitalTypeLOVModel.class);
		if (medhosnameresponse.getStatusCode() == HttpStatus.OK) {
			hosname = medhosnameresponse.getBody();
		}

		model.addAttribute("hospitalname",hosname.getHospitalnamelov());
		model.addAttribute("personInfo", empdetails);
		model.addAttribute("pname",empdetails.getPersonname());
		model.addAttribute("mediadvmodel", mediadvmodel);
		model.addAttribute("mediadvance", MediAdvance.values());
		model.addAttribute("display_mode", display_mode);
		
		ApprovalChildModel[] apr = null;
		String aprroveurl =appGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" 
		+ RID + "/" + mediclaimid + "/" + userlogin.getEmplid();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			
		}

		model.addAttribute("approverdata", apr);
		
		return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
		
	}
	
	@RequestMapping(value="/updateMedicalAdvance/{mode}",method=RequestMethod.POST)
	public String updateMedicalAdvance(@PathVariable("mode") String display_mode,@ModelAttribute MedicalAdvanceModel mediadvmodel, @RequestParam("file") MultipartFile file, 
		HttpServletRequest request, Model model, BindingResult bindingResult) throws IOException, MessagingException {
		//system.out.println("mediadvmodel: "+mediadvmodel);
		userlogin = (Login) request.getSession().getAttribute("login");
		headers.setContentType(MediaType.APPLICATION_JSON);
		PersonInformation empdetails = new PersonInformation();
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + userlogin.getEmplid();
		HttpEntity<String> emprequest = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);
	
		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			empdetails = employeePersonalDetailResponse.getBody();
		}
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", empdetails);
		model.addAttribute("pname",empdetails.getPersonname());
		CommonLOV[] illnesslov = null;
	
		/*String RID = "9";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, userlogin.getEmplid());
		model.addAttribute("empent", ceiling);*/
		
		String urlIllness = appGateway.getAppgatewaypyrl_sandhya() + "/api/IllnessType/getillnesTypeLOV";
		HttpEntity<String> illnessrequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> illnessLovResponse = restTemplate.exchange(urlIllness, HttpMethod.GET, illnessrequest,
				CommonLOV[].class);
		if (illnessLovResponse.getStatusCode() == HttpStatus.OK) {
			illnesslov = illnessLovResponse.getBody();
		}
		model.addAttribute("illness", illnesslov);
	
		MedicalReimDependentModel[] mediremdepmodel = null;
		String urlMediDep = appGateway.getAppgateway() + "/PersonManagement/DependentDetails/getDependentDetailsForMedical/"+ userlogin.getEmplid();
		HttpEntity<String> medideprequest = new HttpEntity<String>(headers);
		ResponseEntity<MedicalReimDependentModel[]> medidepresponse = restTemplate.exchange(urlMediDep, HttpMethod.GET,
				medideprequest, MedicalReimDependentModel[].class);
		if (medidepresponse.getStatusCode() == HttpStatus.OK) {
			mediremdepmodel = medidepresponse.getBody();
		}
		model.addAttribute("medidep", mediremdepmodel);
		MedHospitalTypeLOVModel[] medhostype = null;
		String urlHospitalType = appGateway.getAppgatewaypyrl_sandhya() + "/api/hospitallov/gethospitaltypelov";
		HttpEntity<String> hostyperequest = new HttpEntity<String>(headers);
		ResponseEntity<MedHospitalTypeLOVModel[]> medhosresponse = restTemplate.exchange(urlHospitalType, HttpMethod.GET,
				hostyperequest, MedHospitalTypeLOVModel[].class);
		if (medhosresponse.getStatusCode() == HttpStatus.OK) {
			medhostype = medhosresponse.getBody();
		}
		//System.out.println("Hospital Types : " + medhostype.toString());
		model.addAttribute("hostype", medhostype);
	
		headers.setContentType(MediaType.APPLICATION_JSON);
		MedHospitalTypeLOVModel hosname = null;
		String urlHospitalNames = appGateway.getAppgatewaypyrl_sandhya() + "/api/hospitallov/gethospitaltypelovbyid/"
				+mediadvmodel.getHospitaltypeid() ;
		HttpEntity<String> hosnamerequest = new HttpEntity<String>(headers);
		ResponseEntity<MedHospitalTypeLOVModel> medhosnameresponse = restTemplate.exchange(urlHospitalNames,
				HttpMethod.GET, hosnamerequest, MedHospitalTypeLOVModel.class);
		if (medhosnameresponse.getStatusCode() == HttpStatus.OK) {
			hosname = medhosnameresponse.getBody();
		}
		
		model.addAttribute("hospitalname",hosname.getHospitalnamelov());
		
//		if (statusid.equals("edit")) {
//			mediadvmodel.setStatus("Saved");
//		} else if (statusid.equals("submit")) {
//			mediadvmodel.setStatus("submitted");
//		}
		model.addAttribute("mediadvance", MediAdvance.values());
		//model.addAttribute("display_mode", display_mode);
	
		validator.validate(mediadvmodel, bindingResult);

		model.addAttribute("bindingResult", bindingResult);
		model.addAttribute("mediadvmodel", mediadvmodel);
		model.addAttribute("personInfo", empdetails);
		model.addAttribute("pname",empdetails.getPersonname());
		model.addAttribute("illness", illnesslov);
		model.addAttribute("medidep", mediremdepmodel);
		model.addAttribute("hostype", medhostype);
		model.addAttribute("mediadvance", MediAdvance.values());
		model.addAttribute("mediadvmodel", mediadvmodel);
		model.addAttribute("reimbursename", "Medical Advance");
		model.addAttribute("display_mode","edit");
		//model.addAttribute("hospitalname",hosname.getHospitalnamelov());
		if (bindingResult.hasErrors()) {
			return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
		}
	
		String location = mediadvmodel.getAttachments();
		String filePath = new File("").getAbsolutePath() + File.separator + location;
		String storePath = location;
	
		if (!file.isEmpty()) {
			// CREATE DIRECTORY IF NOT EXISTS
			File dir = new File(filePath);
	
			if (!dir.exists()) {
				dir.mkdirs();
			}
	
			// WRITE FILES TO DIRECTORY AND ENTRY INTO THE DATABASE
			try {
				// CREATING UNIQUE FILENAME
				String empl = userlogin.getEmplid();
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss" + "'" + empl + "." + extension + "'")
						.format(new Date());
	
				File target = new File(filePath + File.separator + generatedFileName);
				int readByteCount = 0;
				byte[] buffer = new byte[4096];
	
				BufferedInputStream in = new BufferedInputStream(file.getInputStream());
				FileOutputStream out = new FileOutputStream(target);
	
				while ((readByteCount = in.read(buffer)) != -1) {
					//System.out.println("file read");
					out.write(buffer, 0, readByteCount);
				}
				storePath += "/" + generatedFileName;
				mediadvmodel.setAttachments(storePath);
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("result", "IOEXCEPTION");
				return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				model.addAttribute("result", "ILLEGALARG");
				return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("result", "WRITE_ERROR");
				return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
			}
		} else {
			mediadvmodel.setAttachments(mediadvmodel.getAttachhidden());
		}
	
		String urlmediadvupdate = appGateway.getAppgatewaypyrl_sandhya() + "/api/medicaladvance/updatemedicaladvance";
	
		MedicalAdvanceModel medicaladvmod = null;
	
		HttpEntity<MedicalAdvanceModel> medadvreq = new HttpEntity<MedicalAdvanceModel>(mediadvmodel, headers);
	
		ResponseEntity<MedicalAdvanceModel> mediadvresponse = restTemplate.exchange(urlmediadvupdate, HttpMethod.PUT,
				medadvreq, MedicalAdvanceModel.class);
	
		if (mediadvresponse.getStatusCode() == HttpStatus.OK) {
			medicaladvmod = mediadvresponse.getBody();
			//System.out.println("medical adv model :::::: " + medicaladvmod.toString());
		} else {
			System.out.println(mediadvresponse.getStatusCode());
			model.addAttribute("result", "LOG_ERROR");
			return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
		}
		
		model.addAttribute("display_mode","edit");
		model.addAttribute("result",medicaladvmod.getSubmitStatus());
//System.out.println("submit stat :: "+mediadvmodel.getSubmitStatus()+" save response :: "+);
		if(medicaladvmod.getSubmitStatus().equals("Success") && mediadvmodel.getStatus().equals("Submitted")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = appGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+mediadvmodel.getClaimid()+"/"+userlogin.getEmplid();
			System.out.println("URL APPROVER DATA :: "+getapprovalperson);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emailrequest = new HttpEntity<String>(headers);
			ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, emailrequest, ApproverDetails.class);
			if (apprdetailsres.getStatusCode() == HttpStatus.OK) {
				apprdetails = apprdetailsres.getBody();
			}
			System.out.println("Approver Details :: "+apprdetails.toString());
			ApproverDetails finalApprdetails = apprdetails;
			PersonInformation finalEmpdetails = empdetails;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						sendnotimail.sendnotificationmail(finalApprdetails, finalEmpdetails.getPersonname(),userlogin.getEmplid(),"Medical Advance");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}
		return "forms/reimbursement/medicalAdvance/medicalAdvanceRequest :: medicalAdvanceRequest";
	}


	//////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping("/approvalMedicalAdvance/{mediclaimid}/{display_mode}/{status}")
	public String approvalMedicalAdvance(@PathVariable("mediclaimid") int mediclaimid, @PathVariable("status") String status,
										 @PathVariable("display_mode") String display_mode, Model model, HttpServletRequest requestdata)
	{

		headers.setContentType(MediaType.APPLICATION_JSON);
		userlogin = (Login) requestdata.getSession().getAttribute("login");

		/*String RID = "9";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, userlogin.getEmplid());
		model.addAttribute("empent", ceiling);*/
		model.addAttribute("reimbursename", "Medical Advance");

		MedicalAdvanceModel mediadvmodel = null;
		String urlmediclaim = appGateway.getAppgatewaypyrl_sandhya() +"/api/medicaladvance/getmedicaladvancebyid/"+mediclaimid;
		HttpEntity<String> get_mediclaim_request = new HttpEntity<String>(headers);
		ResponseEntity<MedicalAdvanceModel> mediclaimresponse = restTemplate.exchange(urlmediclaim, HttpMethod.GET, get_mediclaim_request,
				MedicalAdvanceModel.class);
		if (mediclaimresponse.getStatusCode() == HttpStatus.OK) {
			mediadvmodel = mediclaimresponse.getBody();
		} else {
			System.out.println("Request Failed with response code : " + mediclaimresponse.getStatusCode());
		}

		NotificationModel notify=null;
		String urlNotify = appGateway.getAppgateway()+"/Notification/getApprovalDataByPersonnoStatus/"+RID+"/" + mediclaimid+"/"+userlogin.getEmplid()+"/"+status;
		//System.out.println(urlNotify);
		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET, get_mediclaim_request, NotificationModel.class);
		if(responseNotify.getStatusCode() == HttpStatus.OK) {
			notify=responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		model.addAttribute("APPRAMT",notify.getApprovedamount());

		PersonInformation empdetails = new PersonInformation();
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ mediadvmodel.getPersonnumber();
		HttpEntity<String> emprequest = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			empdetails = employeePersonalDetailResponse.getBody();
		}
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+mediadvmodel.getPersonnumber()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", empdetails);
		model.addAttribute("pname",empdetails.getPersonname());
		model.addAttribute("mediadvmodel", mediadvmodel);
		model.addAttribute("display_mode", display_mode);

		ApprovalChildModel[] apr = null;
		String aprroveurl =appGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/"
				+ RID + "/" + mediclaimid + "/" + mediadvmodel.getPersonnumber();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK
		) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");

		}
		model.addAttribute("approverdata", apr);

		return "forms/reimbursement/medicalAdvance/medicalAdvanceApproval :: medicalAdvanceApproval";

	}


	@RequestMapping(value = "/mediadvApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel mediadvApprovalUpdate(@PathVariable("_status") String _status,String msg, String approvedamt, long claimid, HttpServletRequest reques,Model model) throws IOException, MessagingException {
		SingleResponseModel res = new SingleResponseModel();

		Login login = (Login) reques.getSession().getAttribute("login");

		headers.setContentType(MediaType.APPLICATION_JSON);
		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String RID="9";
		//NotificationModel approvalchange=null;
		String urlNotify = appGateway.getAppgateway()+"/Notification/getUnreadApprovalData/"+RID+"/" + claimid+"/"+login.getEmplid();
		//System.out.println(urlNotify);
		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET, request1Noti, NotificationModel.class);
		if(responseNotify.getStatusCode() == HttpStatus.OK) {
			notify=responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		model.addAttribute("NotifynavBar", notify);

		
		notify.setStatus(_status);
		notify.setMessage(msg);
		//System.out.println("Approved Amount"+ approvedamt);
		notify.setApprovedamount(Double.parseDouble(approvedamt));
		//System.out.println(notify.toString());

		String url = appGateway.getAppgateway() + "/Notification/submittedAprovelById";

		HttpEntity<NotificationModel> request = new HttpEntity<NotificationModel>(notify,headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, SingleResponseModel.class);
		if(response.getStatusCode() == HttpStatus.OK) {
			res=response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}

		if(res.getStatus().equals("true") && _status.equals("Approved")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = appGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+claimid+"/"+notify.getSubmittedbypersonno();
			System.out.println("URL APPROVER DATA :: "+getapprovalperson);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emprequest = new HttpEntity<String>(headers);
			ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, emprequest, ApproverDetails.class);
			if (apprdetailsres.getStatusCode() == HttpStatus.OK) {
				apprdetails = apprdetailsres.getBody();
			}
			System.out.println("Approver Details :: "+apprdetails.toString());
			ApproverDetails finalApprdetails = apprdetails;
			NotificationModel finalNotify = notify;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"Medical Advance");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}else if(res.getStatus().equals("true") && _status.equals("Rejected")){

			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emprequest = new HttpEntity<String>(headers);
			PersonInformation personInformation = null;
			String url1 = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + notify.getSubmittedbypersonno();
			ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url1, HttpMethod.GET, emprequest, PersonInformation.class);
			if (responsePerso.getStatusCode() == HttpStatus.OK) {
				personInformation = responsePerso.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(responsePerso.getStatusCode());
			}
			NotificationModel finalNotify = notify;
			PersonInformation finalPersonInformation = personInformation;
			System.out.println("URL REJECT DATA :: "+personInformation.getEmailaid()+" :: "+notify.getSubmittedbypersonname()+" :: "+claimid+" ::");
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						sendnotimail.sendrejectionmail(finalPersonInformation.getEmailaid(),finalNotify.getSubmittedbypersonname(),claimid,"Medical Advance");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}


		return res;
	}
}
