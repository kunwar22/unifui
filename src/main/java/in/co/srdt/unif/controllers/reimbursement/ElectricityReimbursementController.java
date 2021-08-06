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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.rembchild.ApprovalChildModel;
import in.co.srdt.unif.model.rembelectricity.ElectricityReimbursementModel;
import in.co.srdt.unif.model.rembelectricity.ElectricitySearchModel;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/reimbursement")
public class ElectricityReimbursementController {
	/* Controllers for Telephone Remb - Snigdhaa Vaish */

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

	/************************************************************************
	 * Electricity Reimburseent
	 **********************************************/

	@RequestMapping("/electricity")
	public String electricityReimbursement(HttpSession session, HttpServletRequest request, Model model) {
		/***************************************
		 * Fetching Details of Employee Starts
		 ****************************************************************/

		userlogin = (Login) request.getSession().getAttribute("login");
		PersonInformation personInfo = new PersonInformation();

		/* Getting Employee Details from API */
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"+userlogin.getEmplid(); // userlogin.getEmplid();

	//	System.out.println("URL for getting employee details :: " + urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();

		//	System.out.println("Employee Personal Details ==>" + personInfo);
		}

		model.addAttribute("personInfo", personInfo);
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		/**************************************
		 * Fetching Details of Employee Ends
		 *******************************************************/

		return "forms/reimbursement/electricity/electricitySearch :: electricitySearch";
	}

	@RequestMapping("/electricityclaim")
	public String createElectricityClaimReimbursement(HttpSession session, HttpServletRequest request, Model model) {
		/***************************************
		 * Fetching Details of Employee Starts
		 ****************************************************************/

		userlogin = (Login) request.getSession().getAttribute("login");
		PersonInformation personInfo = new PersonInformation();

		 String RID="4";
		 String ceiling="";
		 ceiling=eligibilityreimb.getCeilinglimit(RID,userlogin.getEmplid());
		 model.addAttribute("ceilingamt",ceiling);
		/* Project / OM LOV used */
		CommonLOV[] omproject = null;

		/* Vehicle used LOV */
		CommonLOV[] vehicle_used = null;

		/* Getting Employee Details from API */
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"+userlogin.getEmplid(); // userlogin.getEmplid();

		//System.out.println("URL for getting employee details :: " + urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();

		//	System.out.println("Employee Personal Details ==>" + personInfo);
		}

		/* Vehicle used LOV loading starts */
		String urlVehicleLOV = appGateway.getAppgateway() + "/General/loadVehicleUsedLOV";

		HttpEntity<String> vehiclerequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> vehicleLovResponse = restTemplate.exchange(urlVehicleLOV, HttpMethod.GET,
				vehiclerequest, CommonLOV[].class);

		if (vehicleLovResponse.getStatusCode() == HttpStatus.OK) {
			vehicle_used = vehicleLovResponse.getBody();
			//System.out.println("Vehicle Used  ==>" + vehicle_used);
		}

		/* Vehicle used LOV loading ends */

		/* OM Project LOV loading starts */
		String urlOmProject = appGateway.getAppgateway() + "/General/loadProjectOM";

		HttpEntity<String> omprojectrequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> omProjectLovResponse = restTemplate.exchange(urlOmProject, HttpMethod.GET,
				omprojectrequest, CommonLOV[].class);

		if (omProjectLovResponse.getStatusCode() == HttpStatus.OK) {
			omproject = omProjectLovResponse.getBody();
			//System.out.println("OM Project  ==>" + omproject);
		}

		/* OM Project LOV loading ends */

		ElectricityReimbursementModel electremodel = new ElectricityReimbursementModel();

		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);

		model.addAttribute("personInfo", personInfo);
		model.addAttribute("vehicles", vehicle_used);
		model.addAttribute("omproject", omproject);
		model.addAttribute("electricitymodel", electremodel);

		/**************************************
		 * Fetching Details of Employee Ends
		 *******************************************************/

		return "forms/reimbursement/electricity/electricityClaim :: electricityClaim";
	}

	@ResponseBody
	@RequestMapping("/getElectricityReimbursement")
	public ElectricitySearchModel[] electricityReimbursementModel(HttpSession session, HttpServletRequest request,
			Model model) {
		userlogin = (Login) request.getSession().getAttribute("login"); // fetching Persion Number

		headers.setContentType(MediaType.APPLICATION_JSON);

		ElectricitySearchModel[] search_data = null;

		String urlelectricityreimbursementSearch = appGateway.getAppgatewaypyrl_sandhya()
				+ "/api/electric/geteletricityclaimbypersonnbr/"+userlogin.getEmplid();

		//System.out.println("URL for electricity claim search :: " + urlelectricityreimbursementSearch);
		HttpEntity<String> electricityreimbursement_search_request = new HttpEntity<String>(headers);

		ResponseEntity<ElectricitySearchModel[]> electric_response = restTemplate.exchange(
				urlelectricityreimbursementSearch, HttpMethod.GET, electricityreimbursement_search_request,
				ElectricitySearchModel[].class);

		if (electric_response.getStatusCode() == HttpStatus.OK) {
			search_data = electric_response.getBody();
		//	System.out.println("Electricity Search Model : " + search_data);
		}

		return search_data;
	}

	/* POST MAPPING WITH ATTACHMENTS starts here */

	@PostMapping(value = "/saveElectricityReimbursement")
	public String electricityReimbursementSubmit(HttpSession session,@ModelAttribute ElectricityReimbursementModel electricrembmodel,
			@RequestParam("file") MultipartFile file, String statusid, HttpServletRequest request, Model model,
			BindingResult bindingResult) throws IOException, MessagingException {
		//System.out.println("Inside Electricity Save Controller");

		userlogin = (Login) request.getSession().getAttribute("login");
		model.addAttribute("display_mode", "edit");

		/* Project / OM LOV used */
		CommonLOV[] omproject = null;

		/* Vehicle used LOV */
		CommonLOV[] vehicle_used = null;
		String RID="4";
		String ceiling="";
		ceiling=eligibilityreimb.getCeilinglimit(RID,userlogin.getEmplid());
		model.addAttribute("ceilingamt",ceiling);

		/* Vehicle used LOV loading starts */
		String urlVehicleLOV = appGateway.getAppgateway() + "/General/loadVehicleUsedLOV";

		HttpEntity<String> vehiclerequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> vehicleLovResponse = restTemplate.exchange(urlVehicleLOV, HttpMethod.GET,
				vehiclerequest, CommonLOV[].class);

		if (vehicleLovResponse.getStatusCode() == HttpStatus.OK) {
			vehicle_used = vehicleLovResponse.getBody();
		//	System.out.println("Vehicle Used  ==>" + vehicle_used);
		}

		/* Vehicle used LOV loading ends */

		/* OM Project LOV loading starts */
		String urlOmProject = appGateway.getAppgateway() + "/General/loadProjectOM";

		HttpEntity<String> omprojectrequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> omProjectLovResponse = restTemplate.exchange(urlOmProject, HttpMethod.GET,
				omprojectrequest, CommonLOV[].class);

		if (omProjectLovResponse.getStatusCode() == HttpStatus.OK) {
			omproject = omProjectLovResponse.getBody();
		//	System.out.println("OM Project  ==>" + omproject);
		}

		/* OM Project LOV loading ends */

		PersonInformation personInfo = new PersonInformation();

		/* Getting Employee Details from API */
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"+userlogin.getEmplid();	// +
																										// userlogin.getEmplid();

	//	System.out.println("URL for getting employee details :: " + urlEmployeeDetails);
	//	System.out.println("STATUS :: " + statusid);


		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();

		//	System.out.println("Employee Personal Details ==>" + personInfo);
		}

		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);

		model.addAttribute("personInfo", personInfo);

		validator.validate(electricrembmodel, bindingResult);

		model.addAttribute("bindingResult", bindingResult);
		model.addAttribute("electricitymodel", electricrembmodel);

		//System.out.println("\n\n\n\n BINDING ERRORS ::::::::::::::     " + bindingResult.getAllErrors() + "\n\n\n\n");
		model.addAttribute("vehicles", vehicle_used);
		model.addAttribute("omproject", omproject);
		if (bindingResult.hasErrors()) {
		//	System.out.println("==========================================inside binding errors===========================");
			return "forms/reimbursement/electricity/electricityClaim :: electricityClaim";
		}


/*************************FILE ATTACHMENT PART START ONLY EXECUTE WHEN FILE SELECTED****************************/
		if(!file.isEmpty()) {

			String location = electricrembmodel.getAttachments();
			String filePath = new File("").getAbsolutePath() + File.separator + location;
			String storePath = location;


			//CREATE DIRECTORY IF NOT EXISTS
			File dir = new File(filePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}


			//WRITE FILES TO DIRECTORY AND ENTRY INTO THE DATABASE

			try {

				//CREATING UNIQUE FILENAME
				String empl = userlogin.getEmplid();

				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss" + "'" + empl + "." + extension + "'").format(new Date());


				File target = new File(filePath + File.separator + generatedFileName);
				int readByteCount = 0;
				byte[] buffer = new byte[20480];

				BufferedInputStream in = new BufferedInputStream(file.getInputStream());
				FileOutputStream out = new FileOutputStream(target);
				while ((readByteCount = in.read(buffer)) != -1) {
					out.write(buffer, 0, readByteCount);
				}

				storePath += "/" + generatedFileName;
				electricrembmodel.setAttachments(storePath); ////Model should be yours

			}
			catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("result", "IOEXCEPTION");
				return "forms/reimbursement/electricity/electricityClaim :: electricityClaim";			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
				model.addAttribute("result", "ILLEGALARG");
				return "forms/reimbursement/electricity/electricityClaim :: electricityClaim";			}
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("result", "WRITE_ERROR");
				return "forms/reimbursement/electricity/electricityClaim :: electricityClaim";			}
		}else {
			electricrembmodel.setAttachments(electricrembmodel.getAttachhidden());

		}
		/*************************FILE ATTACHMENT PART END****************************/
		if (statusid.equals("save")) {
			electricrembmodel.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			electricrembmodel.setStatus("submitted");
		}else if (statusid.equals("Save&Submit")) {
			electricrembmodel.setStatus("submitted");
		}


		electricrembmodel.setPersonid(userlogin.getEmplid());
		//System.out.println("DATA :::: " + electricrembmodel.toString());
		String urlsave = appGateway.getAppgatewaypyrl_sandhya() + "/api/electric/claimelectricreimburse";

		ElectricityReimbursementModel res = null;
		
		HttpEntity<ElectricityReimbursementModel> request1 = new HttpEntity<ElectricityReimbursementModel>(
				electricrembmodel, headers);
		ResponseEntity<ElectricityReimbursementModel> response = restTemplate.exchange(urlsave, HttpMethod.POST,
				request1, ElectricityReimbursementModel.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println(response.getStatusCode());
			model.addAttribute("result", "LOG_ERROR");
			return "forms/reimbursement/electricity/electricityClaim :: electricityClaim";
		}
		//System.out.println(res.getSubmitStatus());
		model.addAttribute("result", "Success");
		if(!res.getSubmitStatus().equals("alreadyApplied")) {
			res.setSubmitStatus("");
		}
		System.out.println("SUBMITSTATUS :: |"+res.getSubmitStatus()+"|");
		model.addAttribute("submitstatus",res.getSubmitStatus());

		if(res.getSubmitStatus().equals("") && statusid.equals("submit")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = appGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+electricrembmodel.getElectricityid()+"/"+userlogin.getEmplid();
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emailrequest = new HttpEntity<String>(headers);
			ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, emailrequest, ApproverDetails.class);
			if (apprdetailsres.getStatusCode() == HttpStatus.OK) {
				apprdetails = apprdetailsres.getBody();
			}
			System.out.println("Approver Details :: "+apprdetails.toString());

			ApproverDetails finalApprdetails = apprdetails;
			PersonInformation finalPersonInfo = personInfo;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						sendnotimail.sendnotificationmail(finalApprdetails, finalPersonInfo.getPersonname(),userlogin.getEmplid(),"Electricity Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

		return "forms/reimbursement/electricity/electricityClaim :: electricityClaim";
	}


	@RequestMapping("/editElectricityReimbursement/{electricityclaimid}/{display_mode}")
	public String editElectricityReimbursement(@PathVariable("electricityclaimid") int electricityclaimid,
			@PathVariable("display_mode") String display_mode, Model model, HttpServletRequest requestdata) {
		//System.out.println("Electricity Claim ID : " + electricityclaimid);
		//System.out.println("Display Mode : " + display_mode);

		userlogin = (Login) requestdata.getSession().getAttribute("login");
		PersonInformation personInfo = new PersonInformation();

		/* Getting Employee Details from API */
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"+userlogin.getEmplid(); // userlogin.getEmplid();

		//System.out.println("URL for getting employee details :: " + urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();

		//	System.out.println("Employee Personal Details ==>" + personInfo);
		}

		/* Vehicle used LOV */
		CommonLOV[] vehicle_used = null;

		/* Project / OM LOV used */
		CommonLOV[] omproject = null;

		ElectricityReimbursementModel electricitymodel = null;

		String urlelectricitydetails = appGateway.getAppgatewaypyrl_sandhya() + "/api/electric/geteletricityclaimid/"
				+ electricityclaimid;

		//System.out.println("URL for getting electricity details :: " + urlelectricitydetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> get_electricity_request = new HttpEntity<String>(headers);

		ResponseEntity<ElectricityReimbursementModel> electricresponse = restTemplate.exchange(urlelectricitydetails,
				HttpMethod.GET, get_electricity_request, ElectricityReimbursementModel.class);

		if (electricresponse.getStatusCode() == HttpStatus.OK) {
			electricitymodel = electricresponse.getBody();
			//System.out.println("data::" + electricitymodel);
		} else {
			System.out.println("Request Failed with response code : " + electricresponse.getStatusCode());
		}

		/* Vehicle used LOV loading starts */
		String urlVehicleLOV = appGateway.getAppgateway() + "/General/loadVehicleUsedLOV";

		HttpEntity<String> vehiclerequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> vehicleLovResponse = restTemplate.exchange(urlVehicleLOV, HttpMethod.GET,
				vehiclerequest, CommonLOV[].class);

		if (vehicleLovResponse.getStatusCode() == HttpStatus.OK) {
			vehicle_used = vehicleLovResponse.getBody();
			//System.out.println("Vehicle Used  ==>" + vehicle_used);
		}

		/* Vehicle used LOV loading ends */

		/* OM Project LOV loading starts */
		String urlOmProject = appGateway.getAppgateway() + "/General/loadProjectOM";

		HttpEntity<String> omprojectrequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> omProjectLovResponse = restTemplate.exchange(urlOmProject, HttpMethod.GET,
				omprojectrequest, CommonLOV[].class);

		if (omProjectLovResponse.getStatusCode() == HttpStatus.OK) {
			omproject = omProjectLovResponse.getBody();
			//System.out.println("OM Project  ==>" + omproject);
		}

		/* OM Project LOV loading ends */

		String RID="4";
		String ceiling="";
		ceiling=eligibilityreimb.getCeilinglimit(RID,userlogin.getEmplid());
		model.addAttribute("ceilingamt",ceiling);

		////*****Updated by ravi***/////
		ApprovalChildModel[] apr = null;
		String aprroveurl = appGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + electricityclaimid + "/" + userlogin.getEmplid();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseapr.getStatusCode());
		}
		model.addAttribute("approverdata", apr);

		////////////**********End Updated by Ravi*************/////////////

		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);

		model.addAttribute("electricitymodel", electricitymodel);
		model.addAttribute("vehicles", vehicle_used);
		model.addAttribute("omproject", omproject);
		model.addAttribute("display_mode", display_mode);
		model.addAttribute("personInfo", personInfo);

		return "forms/reimbursement/electricity/electricityClaim :: electricityClaim";

	}


	//////////***********Updated by ravi-APPROVAL **************************\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

//	@RequestMapping("/viewElectricityReimbursementApproval/{electricityclaimid}/{display_mode}")
//	public String viewElectricityReimbursementApproval(@PathVariable("electricityclaimid") int electricityclaimid,
//											   @PathVariable("display_mode") String display_mode, Model model, HttpServletRequest requestdata) {
//
//		userlogin = (Login) requestdata.getSession().getAttribute("login");
//		PersonInformation personInfo = new PersonInformation();
//
//		/* Getting Employee Details from API */
//		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"+userlogin.getEmplid(); // userlogin.getEmplid();
//
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		HttpEntity<String> emprequest = new HttpEntity<String>(headers);
//
//		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
//				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);
//
//		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
//			personInfo = employeePersonalDetailResponse.getBody();
//
//			System.out.println("Employee Personal Details ==>" + personInfo);
//		}
//		/* Vehicle used LOV */
//		CommonLOV[] vehicle_used = null;
//		/* Project / OM LOV used */
//		CommonLOV[] omproject = null;
//
//		ElectricityReimbursementModel electricitymodel = null;
//		String urlelectricitydetails = appGateway.getAppgatewaypyrl() + "/api/electric/geteletricityclaimid/"
//				+ electricityclaimid;
//
//		System.out.println("URL for getting electricity details :: " + urlelectricitydetails);
//
//		HttpEntity<String> get_electricity_request = new HttpEntity<String>(headers);
//
//		ResponseEntity<ElectricityReimbursementModel> electricresponse = restTemplate.exchange(urlelectricitydetails,
//				HttpMethod.GET, get_electricity_request, ElectricityReimbursementModel.class);
//
//		if (electricresponse.getStatusCode() == HttpStatus.OK) {
//			electricitymodel = electricresponse.getBody();
//
//		} else {
//			System.out.println("Request Failed with response code : " + electricresponse.getStatusCode());
//		}
//
//		/* Vehicle used LOV loading starts */
//		String urlVehicleLOV = appGateway.getAppgateway() + "/General/loadVehicleUsedLOV";
//
//		HttpEntity<String> vehiclerequest = new HttpEntity<String>(headers);
//		ResponseEntity<CommonLOV[]> vehicleLovResponse = restTemplate.exchange(urlVehicleLOV, HttpMethod.GET,
//				vehiclerequest, CommonLOV[].class);
//
//		if (vehicleLovResponse.getStatusCode() == HttpStatus.OK) {
//			vehicle_used = vehicleLovResponse.getBody();
//
//		}
//
//		/* Vehicle used LOV loading ends */
//
//		/* OM Project LOV loading starts */
//		String urlOmProject = appGateway.getAppgateway() + "/General/loadProjectOM";
//
//		HttpEntity<String> omprojectrequest = new HttpEntity<String>(headers);
//		ResponseEntity<CommonLOV[]> omProjectLovResponse = restTemplate.exchange(urlOmProject, HttpMethod.GET,
//				omprojectrequest, CommonLOV[].class);
//
//		if (omProjectLovResponse.getStatusCode() == HttpStatus.OK) {
//			omproject = omProjectLovResponse.getBody();
//			System.out.println("OM Project  ==>" + omproject);
//		}
//
//		/* OM Project LOV loading ends */
//		String RID="4";
//
//		String ceiling="";
//		ceiling=eligibilityreimb.getCeilinglimit(RID,userlogin.getEmplid());
//		model.addAttribute("ceilingamt",ceiling);
//
//		String urlNotify = appGateway.getAppgateway()+"/Notification/getUnreadApprovalData/"+RID+"/" + electricityclaimid+"/"+userlogin.getEmplid();
//
//		System.out.println(urlNotify);
//
//		headers.add("Authorization", AppConstants.ACCESS_TOKEN);
//		NotificationModel notify=null;
//		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
//
//		//NotificationModel approvalchange=null;
//		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET, request1Noti, NotificationModel.class);
//
//		if(responseNotify.getStatusCode() == HttpStatus.OK) {
//			notify=responseNotify.getBody();
//		} else {
//			System.out.println("Request Failed");
//			System.out.println(responseNotify.getStatusCode());
//		}
//		String aprroveurl = appGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + electricityclaimid + "/" + notify.getSubmittedbypersonno();
//
//
//		////*****Updated by ravi***/////
//		ApprovalChildModel[] apr = null;
//
//		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
//		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);
//
//		if (responseapr.getStatusCode() == HttpStatus.OK) {
//			apr = responseapr.getBody();
//		} else {
//			System.out.println("Request Failed");
//			System.out.println(responseapr.getStatusCode());
//		}
//		model.addAttribute("approverdata", apr);
//
//		////////////**********End Updated by Ravi*************/////////////
//
//
//
//
//		model.addAttribute("electricitymodel", electricitymodel);
//		model.addAttribute("vehicles", vehicle_used);
//		model.addAttribute("omproject", omproject);
//		model.addAttribute("display_mode", display_mode);
//		model.addAttribute("personInfo", personInfo);
//
//		return "forms/reimbursement/electricity/electricityClaimApproval :: electricityClaimapproval";
//
//	}


	@RequestMapping("/viewElectricityReimbursementApproval/{electricityclaimid}/{display_mode}/{status}")
	public String viewElectricityReimbursementApproval(@PathVariable("electricityclaimid") int electricityclaimid, @PathVariable("status") String status,
													   @PathVariable("display_mode") String display_mode, Model model, HttpServletRequest requestdata) {

		userlogin = (Login) requestdata.getSession().getAttribute("login");
		PersonInformation personInfo = new PersonInformation();

		String RID="4";

		String urlNotify = appGateway.getAppgateway()+"/Notification/getApprovalDataByPersonnoStatus/"+RID+"/" + electricityclaimid+"/"+userlogin.getEmplid()+"/"+status;

		//System.out.println(urlNotify);

		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);

		//NotificationModel approvalchange=null;
		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET, request1Noti, NotificationModel.class);

		if(responseNotify.getStatusCode() == HttpStatus.OK) {
			notify=responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}

		model.addAttribute("APPRAMT",notify.getApprovedamount());
		/* Getting Employee Details from API */


		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"+notify.getSubmittedbypersonno(); // userlogin.getEmplid();

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();

			//System.out.println("Employee Personal Details ==>" + personInfo);
		}
		/* Vehicle used LOV */
		CommonLOV[] vehicle_used = null;
		/* Project / OM LOV used */
		CommonLOV[] omproject = null;

		ElectricityReimbursementModel electricitymodel = null;
		String urlelectricitydetails = appGateway.getAppgatewaypyrl_sandhya() + "/api/electric/geteletricityclaimid/"
				+ electricityclaimid;

		//System.out.println("URL for getting electricity details :: " + urlelectricitydetails);

		HttpEntity<String> get_electricity_request = new HttpEntity<String>(headers);

		ResponseEntity<ElectricityReimbursementModel> electricresponse = restTemplate.exchange(urlelectricitydetails,
				HttpMethod.GET, get_electricity_request, ElectricityReimbursementModel.class);

		if (electricresponse.getStatusCode() == HttpStatus.OK) {
			electricitymodel = electricresponse.getBody();

		} else {
			System.out.println("Request Failed with response code : " + electricresponse.getStatusCode());
		}

		/* Vehicle used LOV loading starts */
		String urlVehicleLOV = appGateway.getAppgateway() + "/General/loadVehicleUsedLOV";

		HttpEntity<String> vehiclerequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> vehicleLovResponse = restTemplate.exchange(urlVehicleLOV, HttpMethod.GET,
				vehiclerequest, CommonLOV[].class);

		if (vehicleLovResponse.getStatusCode() == HttpStatus.OK) {
			vehicle_used = vehicleLovResponse.getBody();

		}

		/* Vehicle used LOV loading ends */

		/* OM Project LOV loading starts */
		String urlOmProject = appGateway.getAppgateway() + "/General/loadProjectOM";

		HttpEntity<String> omprojectrequest = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> omProjectLovResponse = restTemplate.exchange(urlOmProject, HttpMethod.GET,
				omprojectrequest, CommonLOV[].class);

		if (omProjectLovResponse.getStatusCode() == HttpStatus.OK) {
			omproject = omProjectLovResponse.getBody();
			//System.out.println("OM Project  ==>" + omproject);
		}

		/* OM Project LOV loading ends */

		String ceiling="";
		ceiling=eligibilityreimb.getCeilinglimit(RID,notify.getSubmittedbypersonno());
		model.addAttribute("ceilingamt",ceiling);

		String aprroveurl = appGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + electricityclaimid + "/" + notify.getSubmittedbypersonno();


		////*****Updated by ravi***/////
		ApprovalChildModel[] apr = null;

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseapr.getStatusCode());
		}
		model.addAttribute("approverdata", apr);

		////////////**********End Updated by Ravi*************/////////////



		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+notify.getSubmittedbypersonno()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("electricitymodel", electricitymodel);
		model.addAttribute("vehicles", vehicle_used);
		model.addAttribute("omproject", omproject);
		model.addAttribute("display_mode", display_mode);
		model.addAttribute("personInfo", personInfo);

		return "forms/reimbursement/electricity/electricityClaimApproval :: electricityClaimapproval";

	}

	@RequestMapping(value = "/electricityApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel electricityApprovalUpdate(@PathVariable("_status") String _status, String msg, double approvedamt, long electricityid, HttpServletRequest reques, Model model) throws IOException, MessagingException {
		SingleResponseModel res = new SingleResponseModel();

		Login login = (Login) reques.getSession().getAttribute("login");


		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String RID="4";
		//NotificationModel approvalchange=null;
		String urlNotify = appGateway.getAppgateway()+"/Notification/getUnreadApprovalData/"+RID+"/" + electricityid+"/"+login.getEmplid();
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
		notify.setApprovedamount(approvedamt);
		///System.out.println(notify.toString());

		String url = appGateway.getAppgateway() + "/Notification/submittedAprovelById";

		HttpEntity<NotificationModel> request = new HttpEntity<NotificationModel>(notify,headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, SingleResponseModel.class);
		if(response.getStatusCode() == HttpStatus.OK) {
			res=response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}

		/*if(res.getStatus().equals("Success"))
		{
			tele.setReimburseid(notify.getModuleid());						////change on the basis of different reimbursment//////
			tele.setTelephoneclaimid(claimid);
			tele.setStatus(_status);
			tele.setApprovedamt(approvedamt);

			HttpEntity<TelephoneModel> request1 = new HttpEntity<TelephoneModel>(tele, httpHeaders);
			ResponseEntity<TelephoneModel> responseTelephone = restTemplate.exchange(reimburseStatusURL, HttpMethod.POST, request1, TelephoneModel.class);

			if (responseTelephone.getStatusCode() == HttpStatus.OK) {
				tele = responseTelephone.getBody();
			} else {
			//	System.out.println(response.getStatusCode());

			}
		}*/
		if(res.getStatus().equals("true")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = appGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+electricityid+"/"+notify.getSubmittedbypersonno();
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"Electricity Reimbursement");
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

	/* Fetching Declaration Form starts--------------------Asmita */
	@RequestMapping(value="/claimform/{electricityclaimid}",method=RequestMethod.GET)
	public String getDeclaration(@PathVariable("electricityclaimid") long electricityclaimid,Model model,HttpServletRequest request)
	{
		userlogin = (Login) request.getSession().getAttribute("login");
		PersonInformation personInfo = new PersonInformation();

		/* Getting Employee Details from API */

		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();

		//System.out.println("URL for getting employee details :: " + urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();

			//System.out.println("Employee Personal Details ==>" + personInfo);
		}

		model.addAttribute("personInfo", personInfo);
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		//System.out.println("electricity claimid ::::::::::: "+electricityclaimid);
		/**************************************
		 * Fetching Details of Employee Ends
		 *******************************************************/

		ElectricitySearchModel electricitySearchModel = null;

		String urlelectricreimbursementSearch = appGateway.getAppgatewaypyrl_sandhya()
				+ "/api/electric/geteletricityclaimid/"+electricityclaimid;
		//System.out.println("URL for electricity claim search :: " + urlelectricreimbursementSearch);
		HttpEntity<String> electricityreimbursement_search_request = new HttpEntity<String>(headers);

		ResponseEntity<ElectricitySearchModel> electricity_response = restTemplate.exchange(urlelectricreimbursementSearch,
				HttpMethod.GET, electricityreimbursement_search_request, ElectricitySearchModel.class);

		if (electricity_response.getStatusCode() == HttpStatus.OK) {
			electricitySearchModel = electricity_response.getBody();
			//System.out.println("Electricity Search Model : " + electricitySearchModel);
		}


		model.addAttribute("electricitymodel", electricitySearchModel);


		return "forms/reimbursement/electricity/electricityClaimForm";
	}
	/* Fetching Declaration Form ends--------------------Asmita */

}
