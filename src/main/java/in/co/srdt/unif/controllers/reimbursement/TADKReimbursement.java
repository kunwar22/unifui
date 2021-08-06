package in.co.srdt.unif.controllers.reimbursement;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import in.co.srdt.unif.controllers.SendMail;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.approvalnotification.ApproverDetails;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.rembchild.ApprovalChildModel;
import in.co.srdt.unif.model.rembtadk.TADKModel;
import in.co.srdt.unif.model.rembtadk.TADKSearchModel;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/reimbursement")
public class TADKReimbursement {

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
	 * TADK Reimburseent
	 **********************************************/

	/* ====================================================== LOADING SEARCH PAGE   ====================================================== */
	
	@RequestMapping("/tadk")
	public String tadkReimbursement(HttpSession session, HttpServletRequest request, Model model) {
		/***************************************
		 * Fetching Details of Employee Starts
		 ****************************************************************/
		
		  userlogin = (Login) request.getSession().getAttribute("login");
		  PersonInformation empdetails = new PersonInformation();
		  
			/* Getting Employee Details from API */ 
		  
		  String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"+userlogin.getEmplid();
		  
		  //System.out.println("URL for getting employee details :: " +   urlEmployeeDetails);
		  
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  
		  HttpEntity<String> emprequest = new HttpEntity<String>(headers);
		  
		  ResponseEntity<PersonInformation> employeePersonalDetailResponse =
		  restTemplate .exchange(urlEmployeeDetails, HttpMethod.GET, emprequest,
		  PersonInformation.class);
		  
		  if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
		  empdetails = employeePersonalDetailResponse.getBody();
		  
		 // System.out.println("Employee Personal Details ==>" + empdetails);
		  }
		  
		  model.addAttribute("personInfo", empdetails);
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		  
		  /**************************************
			 * Fetching Details of Employee Ends *
			 *******************************************************/

		return "forms/reimbursement/tadkReimbursement/tadkSearch :: tadkSearch";
	}
	
	
	/* ======================================================= LOADING CLAIM PAGE   ====================================================== */

	@RequestMapping("/tadkclaim")
	public String createTADKClaimReimbursement(HttpSession session, HttpServletRequest request, Model model) {
		/***************************************
		 * Fetching Details of Employee Starts
		 ****************************************************************/
		TADKModel tadkmodel = new TADKModel();
		
		userlogin = (Login) request.getSession().getAttribute("login");
		
		
		  String RID="12"; 
		  String ceiling=eligibilityreimb.getCeilinglimit(RID,userlogin.getEmplid());
		  model.addAttribute("empent",ceiling);
		 
    	
		  PersonInformation empdetails = new PersonInformation();

	      /* Getting Employee Details from API */
		
		  String urlEmployeeDetails = appGateway.getAppgateway() +
		  "/PersonDetails/getPersonInfo/" + userlogin.getEmplid();
		  
		 // System.out.println("URL for getting employee details :: " +  urlEmployeeDetails);
		  
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  
		  HttpEntity<String> emprequest = new HttpEntity<String>(headers);
		  
		  ResponseEntity<PersonInformation> employeePersonalDetailResponse =
		  restTemplate .exchange(urlEmployeeDetails, HttpMethod.GET, emprequest,
		  PersonInformation.class);
		  
		  if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
		  empdetails = employeePersonalDetailResponse.getBody();
		  
		  //System.out.println("Employee Personal Details ==>" + empdetails); 
		  }
		 
		  
		  CommonLOV[] monthlov=null;
			String urlmonthlov = appGateway.getAppgateway() + "/General/loadTADKMonth";
			ResponseEntity<CommonLOV[]> response10 = restTemplate.exchange(urlmonthlov, HttpMethod.GET, emprequest,
					CommonLOV[].class);
			if (response10.getStatusCode() == HttpStatus.OK) {
				monthlov = response10.getBody();
			}
			model.addAttribute("month", monthlov);

		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", empdetails); 
		model.addAttribute("tadkmodel", tadkmodel);
		

		
		/**************************************
		 * Fetching Details of Employee Ends
		 *******************************************************/

		return "forms/reimbursement/tadkReimbursement/tadkClaim :: tadkClaim";
	}

	/* ====================================================== SAVING TADK DATA WITHOUT ATTACHMENT   ====================================================== */

	/* ====================================================== SAVING TADK DATA WITH ATTACHMENT   ====================================================== */
	
	@PostMapping("/saveTadkReimbursement/{mode}")
	public String saveTadkReimbursement(@PathVariable("mode") String mode,
			@ModelAttribute("tadkmodel") TADKModel tadkmodel,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request,
			String statusid,BindingResult bindingResult,Model model) throws AddressException, MessagingException, IOException 
	{
		//System.out.println("Inside TADK Save Controller");

		userlogin = (Login) request.getSession().getAttribute("login");
		headers.setContentType(MediaType.APPLICATION_JSON);
		PersonInformation empdetails = new PersonInformation();
		
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"+userlogin.getEmplid();
		
		HttpEntity<String> tadkrequest = new HttpEntity<String>(headers);
		
		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, tadkrequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			empdetails = employeePersonalDetailResponse.getBody();

			//System.out.println("Employee Personal Details ==>" + empdetails);
		}
		
		//System.out.println("URL for getting employee details :: " + urlEmployeeDetails);
		//System.out.println("STATUS :: " + statusid);
		  CommonLOV[] monthlov=null;
			String urlmonthlov = appGateway.getAppgateway() + "/General/loadTADKMonth";
			ResponseEntity<CommonLOV[]> response10 = restTemplate.exchange(urlmonthlov, HttpMethod.GET, tadkrequest,
					CommonLOV[].class);
			if (response10.getStatusCode() == HttpStatus.OK) {
				monthlov = response10.getBody();
			}
			
		if (statusid.equals("save") || statusid.equals("edit")) {
			tadkmodel.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			tadkmodel.setStatus("Submitted");
		}
		
		validator.validate(tadkmodel, bindingResult);
		String RID="12"; 
		String ceiling=eligibilityreimb.getCeilinglimit(RID,userlogin.getEmplid());
		model.addAttribute("empent",ceiling);
		 
		model.addAttribute("month", monthlov);
		model.addAttribute("personInfo", empdetails);
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, tadkrequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("bindingResult", bindingResult);
		model.addAttribute("tadkmodel", tadkmodel);
		model.addAttribute("display_mode", "edit");
		
		//System.out.println("\n\n\n\n BINDING ERRORS ::::::::::::::     " + bindingResult.getAllErrors() + "\n\n\n\n");
		if (bindingResult.hasErrors()) {
			//System.out.println( "==========================================inside binding errors===========================");
			return "forms/reimbursement/tadkReimbursement/tadkClaim :: tadkClaim";
		}

		/*************************FILE ATTACHMENT PART START ONLY EXECUTE WHEN FILE SELECTED****************************/
		if(!file.isEmpty()) {

			String location = tadkmodel.getAttachment();
			location=location.substring(0,location.length()-1);
			String filePath = new File("").getAbsolutePath() + File.separator + location;

			String storePath = location;
			//System.out.println("aaaa "+location);

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

				tadkmodel.setAttachment(storePath); ////Model should be yours

			}
			catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("result", "IOEXCEPTION");
				return "forms/reimbursement/tadkReimbursement/tadkClaim :: tadkClaim";
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
				model.addAttribute("result", "ILLEGALARG");
				return "forms/reimbursement/tadkReimbursement/tadkClaim :: tadkClaim";
			}
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("result", "WRITE_ERROR");
				return "forms/reimbursement/tadkReimbursement/tadkClaim :: tadkClaim";
			}
		}else {
			if(tadkmodel.getAttachhidden()!=null) {
				tadkmodel.setAttachment(tadkmodel.getAttachhidden());
			}else {
				tadkmodel.setAttachment(null);
			}
			
		}
		/*************************FILE ATTACHMENT PART END****************************/

		/***********************DATABASE TRANSACTIONS START FOR DATA SAVE or SUBMIT**********************/
		if (statusid.equals("saved")) {
			tadkmodel.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			tadkmodel.setStatus("Submitted");
		}

		String urltadksave = appGateway.getAppgatewaypyrl_sandhya() + "/api/TaDkRmbrs/saveandcorrectTaDk";
		
		SingleResponseModel res = null;
		
		HttpEntity<TADKModel> tadkreq = new HttpEntity<TADKModel>(tadkmodel, headers);
		
		ResponseEntity<SingleResponseModel> tadkresponse = restTemplate.exchange(urltadksave, HttpMethod.POST, tadkreq, SingleResponseModel.class);

		if (tadkresponse.getStatusCode() == HttpStatus.OK) {
			res = tadkresponse.getBody();
		} else {
			System.out.println(tadkresponse.getStatusCode());
			model.addAttribute("result", "LOG_ERROR");
			return "forms/reimbursement/tadkReimbursement/tadkClaim :: tadkClaim";
		}

		model.addAttribute("display_mode", "edit");
		model.addAttribute("month", monthlov);
		model.addAttribute("tadkmodel", tadkmodel);
		//System.out.println("res.getStatus()::::::::::::"+res.getStatus());
		model.addAttribute("result", res.getStatus());
		
		if(res.getStatus().equals("Success") && statusid.equals("submit")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = appGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+tadkmodel.getClaimid()+"/"+userlogin.getEmplid();
			System.out.println("URL APPROVER DATA :: "+getapprovalperson);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emprequest = new HttpEntity<String>(headers);
			ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, emprequest, ApproverDetails.class);
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalEmpdetails.getPersonname(),userlogin.getEmplid(),"TADK Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

		return "forms/reimbursement/tadkReimbursement/tadkClaim :: tadkClaim";
	}




	
	/* ====================================================== LOADING DATA IN SEARCH PAGE   ====================================================== */
	
	@ResponseBody
	@RequestMapping("/getTADKReimbursement")
	public TADKSearchModel[] tadkReimbursementModel(HttpSession session, HttpServletRequest request, Model model) {
		userlogin = (Login) request.getSession().getAttribute("login"); // fetching Persion Number

		headers.setContentType(MediaType.APPLICATION_JSON);

		TADKSearchModel[] tadksearchmodel = null;

		String urltadkreimbursementSearch = appGateway.getAppgatewaypyrl_sandhya()
				+ "/api/TaDkRmbrs/getTaDkRmbrsBypersonnumber/" + userlogin.getEmplid();
		//System.out.println("URL for tadk claim search :: " + urltadkreimbursementSearch);
		HttpEntity<String> tadkreimbursement_search_request = new HttpEntity<String>(headers);

		ResponseEntity<TADKSearchModel[]> tadk_response = restTemplate.exchange(urltadkreimbursementSearch,
				HttpMethod.GET, tadkreimbursement_search_request, TADKSearchModel[].class);

		if (tadk_response.getStatusCode() == HttpStatus.OK) {
			tadksearchmodel = tadk_response.getBody();
			//System.out.println("TADK Search Model : " + tadksearchmodel);
		}

		return tadksearchmodel;
	}

	/* ====================================================== LOADING CLAIM PAGE IN VIEW/EDIT MODE  ======================================================*/
	
	@RequestMapping("/editTADKReimbursement/{tadkclaimid}/{display_mode}")
	public String editTADKReimbursement(@PathVariable("tadkclaimid") int tadkclaimid,
			@PathVariable("display_mode") String display_mode, Model model, HttpServletRequest requestdata) {
		//System.out.println("TADK Claim ID : " + tadkclaimid);
		//System.out.println("Display Mode : " + display_mode);
		
		
		userlogin = (Login) requestdata.getSession().getAttribute("login");
		
		
		  String RID="12"; 
		  String ceiling=eligibilityreimb.getCeilinglimit(RID,userlogin.getEmplid());
		  model.addAttribute("empent",ceiling);
		 
  	
		PersonInformation empdetails = new PersonInformation();

		/* Getting Employee Details from API */
		
		  String urlEmployeeDetails = appGateway.getAppgateway() +
		  "/PersonDetails/getPersonInfo/" + userlogin.getEmplid();
		  
		//  System.out.println("URL for getting employee details :: " +  urlEmployeeDetails);
		  
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  
		  HttpEntity<String> emprequest = new HttpEntity<String>(headers);
		  
		  ResponseEntity<PersonInformation> employeePersonalDetailResponse =
		  restTemplate .exchange(urlEmployeeDetails, HttpMethod.GET, emprequest,
		  PersonInformation.class);
		  
		  if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
		  empdetails = employeePersonalDetailResponse.getBody();
		  
		//  System.out.println("Employee Personal Details ==>" + empdetails); 
		  }
		  
		  

		TADKModel tadkmodel = null;

		String urltadkdetails = appGateway.getAppgatewaypyrl_sandhya() + "/api/TaDkRmbrs/getTaDkByTadkRmbrsId/" + tadkclaimid;

		//System.out.println("URL for getting tadk details :: " + urltadkdetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> get_tadk_request = new HttpEntity<String>(headers);

		ResponseEntity<TADKModel> tadkresponse = restTemplate.exchange(urltadkdetails, HttpMethod.GET, get_tadk_request,
				TADKModel.class);

		if (tadkresponse.getStatusCode() == HttpStatus.OK) {
			tadkmodel = tadkresponse.getBody();
			//System.out.println("data::" + tadkmodel);
		} else {
			//System.out.println("Request Failed with response code : " + tadkresponse.getStatusCode());
		}
		
		CommonLOV[] monthlov=null;
		String urlmonthlov = appGateway.getAppgateway() + "/General/loadTADKMonth";
		ResponseEntity<CommonLOV[]> response10 = restTemplate.exchange(urlmonthlov, HttpMethod.GET, get_tadk_request,
				CommonLOV[].class);
		if (response10.getStatusCode() == HttpStatus.OK) {
			monthlov = response10.getBody();
		}
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", empdetails);
		model.addAttribute("tadkmodel", tadkmodel);
		model.addAttribute("month", monthlov);
		model.addAttribute("display_mode", display_mode);
		
		
		

		////*****************Updated by Ravi for view mode Approval--START****************\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		ApprovalChildModel[] apr = null;
		String aprroveurl =appGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + tadkclaimid + "/" + userlogin.getEmplid();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK
				) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			
		}

		model.addAttribute("approverdata", apr);

		////*****************Updated by Ravi--END****************\\\\\\\\\\\\\\\\\\\\\\\\\\


		return "forms/reimbursement/tadkReimbursement/tadkClaim :: tadkClaim";
	}


	@RequestMapping(value = "/tadkApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel tadkApprovalUpdate(@PathVariable("_status") String _status,String msg, String approvedamt, long claimid, HttpServletRequest reques,Model model) throws AddressException, MessagingException, IOException {
		SingleResponseModel res = new SingleResponseModel();

		Login login = (Login) reques.getSession().getAttribute("login");


		headers.setContentType(MediaType.APPLICATION_JSON);
		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String RID="12";
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
		notify.setApprovedamount(Double.parseDouble(approvedamt) );
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
		System.out.println(res.toString());
		if(res.getStatus().equals("true")) {
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"TADK Reimbursement");
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

	@RequestMapping("/viewTADKApproval/{tadkclaimid}/{display_mode}")
	public String viewTADKApproval(@PathVariable("tadkclaimid") int tadkclaimid,
			@PathVariable("display_mode") String display_mode, Model model, HttpServletRequest requestdata) {
		//System.out.println("TADK Claim ID : " + tadkclaimid);
		//System.out.println("Display Mode : " + display_mode);
		
		
		userlogin = (Login) requestdata.getSession().getAttribute("login");
		
		
		  

		TADKModel tadkmodel = null;

		String urltadkdetails = appGateway.getAppgatewaypyrl_sandhya() + "/api/TaDkRmbrs/getTaDkByTadkRmbrsId/" + tadkclaimid;

		//System.out.println("URL for getting tadk details :: " + urltadkdetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> get_tadk_request = new HttpEntity<String>(headers);

		ResponseEntity<TADKModel> tadkresponse = restTemplate.exchange(urltadkdetails, HttpMethod.GET, get_tadk_request,
				TADKModel.class);

		if (tadkresponse.getStatusCode() == HttpStatus.OK) {
			tadkmodel = tadkresponse.getBody();
			//System.out.println("data::" + tadkmodel);
		} else {
			System.out.println("Request Failed with response code : " + tadkresponse.getStatusCode());
		}
		
		String RID="12"; 
		String ceiling=eligibilityreimb.getCeilinglimit(RID,tadkmodel.getPersonnumber());
		model.addAttribute("empent",ceiling);
		
		PersonInformation empdetails = new PersonInformation();

		
		
		
		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		//String RID="12";
		//NotificationModel approvalchange=null;
		String urlNotify = appGateway.getAppgateway()+"/Notification/getUnreadApprovalData/"+RID+"/" + tadkclaimid+"/"+userlogin.getEmplid();
		//System.out.println(urlNotify);
		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET, request1Noti, NotificationModel.class);
		if(responseNotify.getStatusCode() == HttpStatus.OK) {
			notify=responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		model.addAttribute("APPRAMT",notify.getApprovedamount());  
		/* Getting Employee Details from API */
		
		  String urlEmployeeDetails = appGateway.getAppgateway() +
		  "/PersonDetails/getPersonInfo/" + tadkmodel.getPersonnumber();
		  
		 // System.out.println("URL for getting employee details :: " +  urlEmployeeDetails);
		  
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  
		  HttpEntity<String> emprequest = new HttpEntity<String>(headers);
		  
		  ResponseEntity<PersonInformation> employeePersonalDetailResponse =
		  restTemplate .exchange(urlEmployeeDetails, HttpMethod.GET, emprequest,
		  PersonInformation.class);
		  
		  if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
		  empdetails = employeePersonalDetailResponse.getBody();
		  
		  //System.out.println("Employee Personal Details ==>" + empdetails); 
		  }
		  
		  CommonLOV[] monthlov=null;
			String urlmonthlov = appGateway.getAppgateway() + "/General/loadTADKMonth";
			ResponseEntity<CommonLOV[]> response10 = restTemplate.exchange(urlmonthlov, HttpMethod.GET, emprequest,
					CommonLOV[].class);
			if (response10.getStatusCode() == HttpStatus.OK) {
				monthlov = response10.getBody();
			}
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+tadkmodel.getPersonnumber()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);

		model.addAttribute("personInfo", empdetails);
		model.addAttribute("tadkmodel", tadkmodel);
		model.addAttribute("month", monthlov);
		model.addAttribute("display_mode", display_mode);
		
		
		

		////*****************Updated by Ravi for view mode Approval--START****************\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		ApprovalChildModel[] apr = null;
		String aprroveurl =appGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + tadkclaimid + "/"+tadkmodel.getPersonnumber();
		//System.out.println("URL APPROVAL"+aprroveurl);
		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK
				) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			
		}
		//System.out.println("APPROVER DATA: "+apr.toString());
		model.addAttribute("approverdata", apr);

		////*****************Updated by Ravi--END****************\\\\\\\\\\\\\\\\\\\\\\\\\\

		
		
		
		
		

		return "forms/reimbursement/tadkReimbursement/tadkClaimApproval :: tadkClaim";
	}





	/* Fetching Declaration Form starts -------------Asmita*/
	@RequestMapping(value="/declarationTADK/{claimid}",method=RequestMethod.GET)
	public String getDeclaration(@PathVariable("claimid") long claimid,Model model,HttpServletRequest request)
	{
		userlogin = (Login) request.getSession().getAttribute("login");
		PersonInformation empdetails = new PersonInformation();

		/* Getting Employee Details from API */

		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();

		//System.out.println("URL for getting employee details :: " + urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			empdetails = employeePersonalDetailResponse.getBody();

			///System.out.println("Employee Personal Details ==>" + empdetails);
		}
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", empdetails);

		//System.out.println("claimid ::::::::::: "+claimid);
		/**************************************
		 * Fetching Details of Employee Ends
		 *******************************************************/

		CommonLOV[] monthlov=null;
		String urlmonthlov = appGateway.getAppgateway() + "/General/loadTADKMonth";
		ResponseEntity<CommonLOV[]> response10 = restTemplate.exchange(urlmonthlov, HttpMethod.GET, emprequest,
				CommonLOV[].class);
		if (response10.getStatusCode() == HttpStatus.OK) {
			monthlov = response10.getBody();
		}
		
		
		TADKSearchModel tadksearchmodel = null;

		String urltadkreimbursementSearch = appGateway.getAppgatewaypyrl_sandhya()
				+ "/api/TaDkRmbrs/getTaDkByTadkRmbrsId/"+claimid;
		//System.out.println("URL for tadk claim search :: " + urltadkreimbursementSearch);
		HttpEntity<String> tadkreimbursement_search_request = new HttpEntity<String>(headers);

		ResponseEntity<TADKSearchModel> tadk_response = restTemplate.exchange(urltadkreimbursementSearch,
				HttpMethod.GET, tadkreimbursement_search_request, TADKSearchModel.class);

		if (tadk_response.getStatusCode() == HttpStatus.OK) {
			tadksearchmodel = tadk_response.getBody();
		//	System.out.println("TADK Search Model : " + tadksearchmodel);
		}


		model.addAttribute("tadkmodel", tadksearchmodel);

		model.addAttribute("month", monthlov);
		return "forms/reimbursement/tadkReimbursement/TADKDeclaration";
	}
	/* Fetching Declaration Form ends ---------------------Asmita*/
}
