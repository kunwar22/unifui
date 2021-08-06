package in.co.srdt.unif.controllers.reimbursement;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.rembchild.ApprovalChildModel;
import in.co.srdt.unif.model.rembtelephone.TelephoneModel;
import in.co.srdt.unif.model.rembtelephone.TelephoneSearchModel;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/reimbursement")
public class TelephoneReimbursementController {
	/* Controllers for Telephone Remb - Snigdhaa Vaish*/

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	HttpHeaders httpHeaders;

	@Autowired
	ApplicationGateway apiGateway;

	@Autowired
	SmartValidator validator;

	@Autowired
	private HttpHeaders headers;

	@Autowired(required = true)
	private EligibilityReimbursement eligibilityreimb;

	@Autowired(required = true)
	private SendMail sendnotimail;



	@RequestMapping("/telephone")
	public String telephoneReimbursement(HttpServletRequest request, Model model) {
		model.addAttribute("result", "");

		TelephoneSearchModel res = new TelephoneSearchModel();
		model.addAttribute("viewdata", res);
		Login login = (Login) request.getSession().getAttribute("login");
		PersonInformation resp = new PersonInformation();

		String RID = "1";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, login.getEmplid());
		model.addAttribute("empent", ceiling);

		String url = apiGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println("URL: " + url);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> response = restTemplate.exchange(url, HttpMethod.GET, req, PersonInformation.class);
		resp = response.getBody();
		model.addAttribute("personInfo", resp);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		return "forms/reimbursement/telephone/telephoneClaim :: telephoneClaim";
	}

	@RequestMapping("/telephoneSearch")
	public String telephoneReimbursementSearch(HttpServletRequest request, Model model) {

		Login login = (Login) request.getSession().getAttribute("login");
		PersonInformation resp = new PersonInformation();
		String url = apiGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> response = restTemplate.exchange(url, HttpMethod.GET, req, PersonInformation.class);
		resp = response.getBody();
		if (response.getStatusCode() == HttpStatus.OK) {
			resp = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		model.addAttribute("personInfo", resp);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);

		return "forms/reimbursement/telephone/telephoneSearch :: telephoneSearch";
	}


	@RequestMapping(value = "/telephoneSave/{mode}", method = RequestMethod.POST)
	public String telephoneReimbursementSubmit(@PathVariable("mode") String modes,@ModelAttribute TelephoneModel tele, @RequestParam("file") MultipartFile file, String statusid, HttpServletRequest request, Model model, BindingResult bindingResult) throws IOException, MessagingException {
		
		/*************************VALIDATION PART START****************************/
		model.addAttribute("result",new String());
		//System.out.println("Inside Telephone Save Controller");
		Login login = (Login) request.getSession().getAttribute("login");
		String RID = "1";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, login.getEmplid());
		model.addAttribute("empent", ceiling);

		PersonInformation resp = null;

		String url1 = apiGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println("URL: " + statusid);

		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> response2 = restTemplate.exchange(url1, HttpMethod.GET, req, PersonInformation.class);
		resp = response2.getBody();

		model.addAttribute("personInfo", resp);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		validator.validate(tele, bindingResult);

		model.addAttribute("bindingResult", bindingResult);
		model.addAttribute("viewdata", tele);
		model.addAttribute("mode","blank");

	//	System.out.println("ALL ERRORS "+bindingResult.getAllErrors());

		if (bindingResult.hasErrors()) {
			model.addAttribute("result","errrrrr");
			return "forms/reimbursement/telephone/telephoneClaim :: telephoneClaim";
		}
		/*************************VALIDATION PART END****************************/

		/*************************FILE ATTACHMENT PART START ONLY EXECUTE WHEN FILE SELECTED****************************/
		if(!file.isEmpty()) {
			
		String location = tele.getAttachments();
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
			String empl = login.getEmplid();

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss" + "'" + empl + "." + extension + "'").format(new Date());


			File target = new File(filePath + File.separator + generatedFileName);
			int readByteCount = 0;
			byte[] buffer = new byte[20480];

			BufferedInputStream in = new BufferedInputStream(file.getInputStream());
			@SuppressWarnings("resource")
			FileOutputStream out = new FileOutputStream(target);
			while ((readByteCount = in.read(buffer)) != -1) {
				out.write(buffer, 0, readByteCount);
			}

			storePath += "/" + generatedFileName;
			tele.setAttachments(storePath); ////Model should be yours
			
			}
			catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("result", "IOEXCEPTION");
				return "forms/reimbursement/telephone/telephoneClaim :: telephoneClaim";
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
				model.addAttribute("result", "ILLEGALARG");
				return "forms/reimbursement/telephone/telephoneClaim :: telephoneClaim";
			}
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("result", "WRITE_ERROR");
				return "forms/reimbursement/telephone/telephoneClaim :: telephoneClaim";
			}
		}else {
			//System.out.println("tele.getAttachhidden()---------: "+tele.getAttachhidden());
			tele.setAttachments(tele.getAttachhidden());
		}
		/*************************FILE ATTACHMENT PART END****************************/
		
		/***********************DATABASE TRANSACTIONS START FOR DATA SAVE or SUBMIT**********************/
		if (statusid.equals("saved")) {
			tele.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			tele.setStatus("Submitted");
		}
		
		//System.out.println("STATUS_ID :::: " + statusid.toString());
		//System.out.println("DATA TO BE SAVED :::: " + tele.toString());
		String urlsave="";
		//System.out.println("MODESSSSSSSSSSSSSSSSSSSSSS: "+modes);
		if(modes.equals("teleformupdate")) {
			urlsave = apiGateway.getAppgatewaypyrl_sandhya() + "/api/teleclaim/updateclaimdetails";
		}
		if(modes.equals("teleform")) {
			urlsave = apiGateway.getAppgatewaypyrl_sandhya() + "/api/teleclaim/taketeleclaimreimburse";
		}
		//System.out.println("URLSAVE :: "+urlsave);
		
		

		TelephoneModel res =null;
		HttpEntity<TelephoneModel> request1 = new HttpEntity<TelephoneModel>(tele, httpHeaders);
		//ResponseEntity<String> response = restTemplate.exchange(urlsave, HttpMethod.POST, request1, String.class);
		ResponseEntity<TelephoneModel> response = restTemplate.exchange(urlsave, HttpMethod.POST, request1, TelephoneModel.class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println(response.getStatusCode());
			model.addAttribute("result", "LOG_ERROR");
			return "forms/reimbursement/telephone/telephoneClaim :: telephoneClaim";
		}
		
		String result =res.getSubmitStatus();
		if(res.getSubmitStatus()!=null) {
			result=res.getSubmitStatus();
		}
		else {
			result="Success";
		}
		//System.out.println("resultresultresultresultresultresultresultresultresult: "+result);
		model.addAttribute("result", result);

		if(res.getSubmitStatus().equals("") && statusid.equals("submit")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = apiGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+tele.getTelephoneclaimid()+"/"+login.getEmplid();
			System.out.println("URL APPROVER DATA :: "+getapprovalperson);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emailrequest = new HttpEntity<String>(headers);
			ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, emailrequest, ApproverDetails.class);
			if (apprdetailsres.getStatusCode() == HttpStatus.OK) {
				apprdetails = apprdetailsres.getBody();
			}
			System.out.println("Approver Details :: "+apprdetails.toString());
			ApproverDetails finalApprdetails = apprdetails;
			PersonInformation finalResp = resp;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						sendnotimail.sendnotificationmail(finalApprdetails, finalResp.getPersonname(),login.getEmplid(),"Telephone Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

		return "forms/reimbursement/telephone/telephoneClaim :: telephoneClaim";
		
		/***********************DATABASE TRANSACTIONS END FOR DATA SAVE or SUBMIT**********************/
	}


	@ResponseBody
	@RequestMapping(value = "/gettelesearchdata", method = RequestMethod.GET)
	public TelephoneSearchModel[] telephonesearch(HttpServletRequest request, Model model) {
		Login login = (Login) request.getSession().getAttribute("login");
		TelephoneSearchModel[] res = null;
		String url = apiGateway.getAppgatewaypyrl_sandhya() + "/api/teleclaim/getteleclaimsbypersonnbr/" + login.getEmplid();
		//System.out.println("URL:::::::::"+url);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);

		ResponseEntity<TelephoneSearchModel[]> response = restTemplate.exchange(url, HttpMethod.GET, req, TelephoneSearchModel[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		return res;
	}

	@RequestMapping("/viewteledata/{claimid}/{mode}")
	public String viewData(@PathVariable("claimid") String claimid, @PathVariable("mode") String mode, Model model, HttpServletRequest reques) {
		TelephoneSearchModel res = new TelephoneSearchModel();
		ApprovalChildModel[] apr = null;
		Login login = (Login) reques.getSession().getAttribute("login");
		String RID = "1";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, login.getEmplid());
		model.addAttribute("empent", ceiling);


		String url = apiGateway.getAppgatewaypyrl_sandhya() + "/api/teleclaim/getteleclaimsyid/" + claimid;
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<String>(httpHeaders);
		ResponseEntity<TelephoneSearchModel> response = restTemplate.exchange(url, HttpMethod.GET, req, TelephoneSearchModel.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		String aprroveurl = apiGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + claimid + "/" + login.getEmplid();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(httpHeaders);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		model.addAttribute("viewdata", res);
		model.addAttribute("mode", mode);

		model.addAttribute("approverdata", apr);
		//System.out.println(apr[0].getApproverpersonname());

		PersonInformation resp = new PersonInformation();
		String url1 = apiGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requ = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> respons = restTemplate.exchange(url1, HttpMethod.GET, requ, PersonInformation.class);
		resp = respons.getBody();
		model.addAttribute("personInfo", resp);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		return "forms/reimbursement/telephone/telephoneClaim :: telephoneClaim";
	}


	///////////////////***************Updated By Ravi-----Approval Start-----*****************\\\\\\\\\\\\\\\\\\\\
	@RequestMapping("/viewteleapprovaldata/{claimid}/{mode}")
	public String viewapprovalData(@PathVariable("claimid") String claimid, @PathVariable("mode") String mode, Model model, HttpServletRequest reques) {
		TelephoneSearchModel res = new TelephoneSearchModel();
		ApprovalChildModel[] apr = null;

		Login login = (Login) reques.getSession().getAttribute("login");
		String RID = "1";
		


		String url = apiGateway.getAppgatewaypyrl_sandhya() + "/api/teleclaim/getteleclaimsyid/" + claimid;
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<String>(httpHeaders);
		ResponseEntity<TelephoneSearchModel> response = restTemplate.exchange(url, HttpMethod.GET, req, TelephoneSearchModel.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		String urlNotify = apiGateway.getAppgateway()+"/Notification/getUnreadApprovalData/"+RID+"/" + claimid+"/"+login.getEmplid();

		//System.out.println(urlNotify);
		headers.setContentType(MediaType.APPLICATION_JSON);
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
		
		String aprroveurl = apiGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + claimid + "/" + notify.getSubmittedbypersonno();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(httpHeaders);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		String ceiling = eligibilityreimb.getCeilinglimit(RID, notify.getSubmittedbypersonno());
		model.addAttribute("empent", ceiling);
		model.addAttribute("NotifynavBar", notify);


		model.addAttribute("viewdata", res);
		model.addAttribute("mode", mode);

		model.addAttribute("approverdata", apr);
		//System.out.println(apr[0].getApproverpersonname());

		PersonInformation resp = new PersonInformation();
		String url1 = apiGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + notify.getSubmittedbypersonno();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requ = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> respons = restTemplate.exchange(url1, HttpMethod.GET, requ, PersonInformation.class);
		resp = respons.getBody();
		model.addAttribute("personInfo", resp);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+notify.getSubmittedbypersonno()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		return "forms/reimbursement/telephone/telephoneClaimApproval :: telephoneClaimApproval";
	}
	
	
	@RequestMapping(value = "/telephoneApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel telephoneApprovalUpdate(@PathVariable("_status") String _status,String msg, double approvedamt, long claimid, HttpServletRequest reques,Model model) throws IOException, MessagingException {
		SingleResponseModel res = new SingleResponseModel();

		Login login = (Login) reques.getSession().getAttribute("login");


		headers.setContentType(MediaType.APPLICATION_JSON);
		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String RID="1";
		//NotificationModel approvalchange=null;
		String urlNotify = apiGateway.getAppgateway()+"/Notification/getUnreadApprovalData/"+RID+"/" + claimid+"/"+login.getEmplid();
		//System.out.println(urlNotify);
		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET, request1Noti, NotificationModel.class);
		if(responseNotify.getStatusCode() == HttpStatus.OK) {
			notify=responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		model.addAttribute("NotifynavBar", notify);

//		TelephoneModel tele = new TelephoneModel();
//		String reimburseStatusURL=apiGateway.getAppgateway() + "/ApprovalStatusUpdate/postTelephoneApproval";

		notify.setStatus(_status);
		notify.setMessage(msg);
		notify.setApprovedamount(approvedamt);
		//System.out.println("Approved Button data :: "+notify.toString());

		String url = apiGateway.getAppgateway() + "/Notification/submittedAprovelById";

		HttpEntity<NotificationModel> request = new HttpEntity<NotificationModel>(notify,httpHeaders);
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
				System.out.println(response.getStatusCode());

			}
		}*/

		if(res.getStatus().equals("true")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = apiGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+claimid+"/"+notify.getSubmittedbypersonno();
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"Telephone Reimbursement");
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

	/* End of TelephoneApproval Remb Controller*/

	
	/* CLAIM FORM STARTS -------------Asmita */
	
	@RequestMapping(value="/claimTelephone/{claimid}",method=RequestMethod.GET)
	public String getClaimForm(@PathVariable("claimid") long claimid,Model model,HttpServletRequest request)
	{
		Login login = (Login) request.getSession().getAttribute("login");
		PersonInformation empdetails = new PersonInformation();

		/* Getting Employee Details from API */

		String urlEmployeeDetails = apiGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"+ login.getEmplid();

		//System.out.println("URL for getting employee details :: " + urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			empdetails = employeePersonalDetailResponse.getBody();

			//System.out.println("Employee Personal Details ==>" + empdetails);
		}

		model.addAttribute("personInfo", empdetails);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
	//	System.out.println("claimid ::::::::::: "+claimid);
		/**************************************
		 * Fetching Details of Employee Ends
		 *******************************************************/
		
		
		
		TelephoneSearchModel telephoneSearchModel = null;

		String urltelereimbursementSearch = apiGateway.getAppgatewaypyrl_sandhya() + "/api/teleclaim/getteleclaimsyid/" + claimid;
		//System.out.println("URL for tadk claim search :: " + urltelereimbursementSearch);
		HttpEntity<String> tadkreimbursement_search_request = new HttpEntity<String>(headers);

		ResponseEntity<TelephoneSearchModel> tele_response = restTemplate.exchange(urltelereimbursementSearch,
				HttpMethod.GET, tadkreimbursement_search_request, TelephoneSearchModel.class);

		if (tele_response.getStatusCode() == HttpStatus.OK) {
			telephoneSearchModel = tele_response.getBody();
			//System.out.println("TADK Search Model : " + telephoneSearchModel);
		}


		model.addAttribute("viewdata", telephoneSearchModel);

		
		return "forms/reimbursement/telephone/telephoneClaimForm.html";
	}
	
	
	
	/* CLAIM FORM ENDS ---------------------Asmita*/
	
	
	
}
