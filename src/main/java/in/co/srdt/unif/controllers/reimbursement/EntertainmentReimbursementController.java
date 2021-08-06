package in.co.srdt.unif.controllers.reimbursement;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
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
import in.co.srdt.unif.model.rembentertainment.EntertainmentReimbursementmodel;
import in.co.srdt.unif.model.rembentertainment.EntertainmentSearchModel;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/reimbursement")
public class EntertainmentReimbursementController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;


	@Autowired
	SmartValidator validator;

	@Autowired(required = true)
	private EligibilityReimbursement eligibilityreimb;

	@Autowired(required = true)
	private SendMail sendnotimail;

/////*************************************Controller for Print Annexure Start by @Suraj*****************//
	
	
	
	@RequestMapping(value="/entertainmentdeclaration/{claimid}",method=RequestMethod.GET)
	public String getEntertainmentDeclaration(@PathVariable("claimid") long claimid,Model model,HttpServletRequest request)
	{
		Login login = (Login) request.getSession().getAttribute("login");
		PersonInformation empdetails = new PersonInformation();


		String urlEmployeeDetails = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();


	     headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			empdetails = employeePersonalDetailResponse.getBody();

			//System.out.println("Employee Personal Details ==>" + empdetails);
		}

		model.addAttribute("empdetails", empdetails);

		//System.out.println("claimid ::::::::::: "+claimid);
		

		EntertainmentReimbursementmodel entertainmentsearchmodel = null ;
		

		String urlentertainmentreimbursementSearch =appgateway.getAppgatewaypyrl_sandhya()+"/api/entertainmentrmbrs/getListbyentertainmentId/"+claimid;

			HttpEntity<String> entertainmentreimbursement_search_request = new HttpEntity<String>(headers);

		ResponseEntity<EntertainmentReimbursementmodel> entertainment_response = restTemplate.exchange(urlentertainmentreimbursementSearch,
				HttpMethod.GET, entertainmentreimbursement_search_request, EntertainmentReimbursementmodel.class);

		if (entertainment_response.getStatusCode() == HttpStatus.OK) {
			entertainmentsearchmodel = entertainment_response.getBody();
			//System.out.println("Transport declaration Model : " + entertainmentsearchmodel.toString());
		}

		
		model.addAttribute("entertainmenttmodel", entertainmentsearchmodel);
		//System.out.println(entertainmentsearchmodel.getPeriodfrom());
		//System.out.println(entertainmentsearchmodel.getPeriodto());
		
		
		return "forms/reimbursement/entertainment/entertainmentDeclearation.html";
	}

/////*************************************Controller for Print Annexure End by @Suraj*****************//
	
	@RequestMapping("/entertainment")
	public String entertainmentReimbursement(Model model, HttpServletRequest request) {
		EntertainmentReimbursementmodel entertainmentReimbursementmodel = new EntertainmentReimbursementmodel();

		Login login = (Login) request.getSession().getAttribute("login");
		PersonInformation res1 = new PersonInformation();
		String RID="5";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, login.getEmplid());
		model.addAttribute("empent", ceiling);
		
		String url2 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println("URL: " + url2);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> reque = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> response1 = restTemplate.exchange(url2, HttpMethod.GET, reque, PersonInformation.class);
		res1 = response1.getBody();
		//System.out.println("Header" + res1.toString());

		model.addAttribute("personInfo", res1);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, reque, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("enter", entertainmentReimbursementmodel);

		return "forms/reimbursement/entertainment/entertainmentClaim :: entertainmentClaim";
	}

	@RequestMapping("/entertainmentSearch")
	public String entertainmentSearch(Model model, HttpServletRequest request) {


		Login login = (Login) request.getSession().getAttribute("login");
		PersonInformation res1 = null;

		String url2 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println("URL: " + url2);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> reque = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> response1 = restTemplate.exchange(url2, HttpMethod.GET, reque, PersonInformation.class);
		res1 = response1.getBody();
		//System.out.println("Header" + res1.toString());

		model.addAttribute("personInfo", res1);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, reque, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);


		return "forms/reimbursement/entertainment/entertainmentSearch :: entertainmentSearch";
	}


	@RequestMapping(value = "/saveEntertainmentClaim/{mode}/{apprmode}", method = RequestMethod.POST)
	public String saveEntertainmentClaim(@PathVariable("apprmode") String apprmode, @PathVariable("mode") String mode, EntertainmentReimbursementmodel entertainmentClaim, @RequestParam("file") MultipartFile file, HttpServletRequest request, Model model, BindingResult bindingResult) throws IOException, MessagingException {

		Login login = (Login) request.getSession().getAttribute("login");
		PersonInformation res1 = null;


		String url2 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println("URL: " + url2);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> reque = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> response1 = restTemplate.exchange(url2, HttpMethod.GET, reque, PersonInformation.class);
		res1 = response1.getBody();
		//System.out.println("Header" + res1.toString());

		model.addAttribute("personInfo", res1);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, reque, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);

		//System.out.println("Claim::::" + entertainmentClaim.toString());
		SingleResponseModel res = new SingleResponseModel();
		String RID="5";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, login.getEmplid());
		model.addAttribute("empent", ceiling);
		

		validator.validate(entertainmentClaim, bindingResult);


		model.addAttribute("enter", entertainmentClaim);
		model.addAttribute("bindingResult", bindingResult);

		//System.out.println("ERRORS :: " + bindingResult.getAllErrors());
		model.addAttribute("mode", mode);
		model.addAttribute("result", "YY");
		
		if (bindingResult.hasErrors()) {

			return "forms/reimbursement/entertainment/entertainmentClaim ::entertainmentClaim";
		}

/*************************FILE ATTACHMENT PART START ONLY EXECUTE WHEN FILE SELECTED****************************/
		if(!file.isEmpty()) {

			String location = entertainmentClaim.getAttachment();
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
				byte[] buffer = new byte[4096];

				BufferedInputStream in = new BufferedInputStream(file.getInputStream());
				FileOutputStream out = new FileOutputStream(target);
				while ((readByteCount = in.read(buffer)) != -1) {
					out.write(buffer, 0, readByteCount);
				}

				storePath += "/" + generatedFileName;
				entertainmentClaim.setAttachment(storePath); ////Model should be yours

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
			entertainmentClaim.setAttachment(entertainmentClaim.getAttachhidden());

		}
		/*************************FILE ATTACHMENT PART END****************************/

		/***********************DATABASE TRANSACTIONS START FOR DATA SAVE or SUBMIT**********************/
		
		//SingleResponseModel res = new  SingleResponseModel();
		String url_entertainment = appgateway.getAppgatewaypyrl_sandhya() + "/api/entertainmentrmbrs/saveReimbursementEntertainment";
		if (apprmode.equals("save")) {
			entertainmentClaim.setStatus("Saved");
			//url_entertainment = appgateway.getAppgatewaypyrl_sandhya() + "/api/entertainmentrmbrs/saveReimbursementEntertainment";
		} else if (apprmode.equals("submit")) {
			entertainmentClaim.setStatus("Submitted");
			//url_entertainment = appgateway.getAppgatewaypyrl_sandhya() + "/api/entertainmentrmbrs/updateforapproval";
		}
		entertainmentClaim.setReimbursename("Entertainment");
		SingleResponseModel result = null;

		//System.out.println("Entertainment ::" +entertainmentClaim.getStatus()+"-----"+ entertainmentClaim.toString());
		HttpEntity<EntertainmentReimbursementmodel> req = new HttpEntity<EntertainmentReimbursementmodel>(entertainmentClaim, headers);

		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url_entertainment, HttpMethod.POST, req, SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			result = response.getBody();
		//	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+result.toString());
		} else {
			System.out.println("Something went wring while saving");
			System.out.println(response.getStatusCode());
			model.addAttribute("result", "LOG_ERROR");
			return "forms/reimbursement/entertainment/entertainmentClaim ::entertainmentClaim";
			//return "LOG_ERROR";
		}


		model.addAttribute("result", result.getMessage());

		if(result.getMessage().equals("Success") && apprmode.equals("submit")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = appgateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+entertainmentClaim.getClaimid()+"/"+login.getEmplid();
			System.out.println("URL APPROVER DATA :: "+getapprovalperson);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emprequest = new HttpEntity<String>(headers);
			ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, emprequest, ApproverDetails.class);
			if (apprdetailsres.getStatusCode() == HttpStatus.OK) {
				apprdetails = apprdetailsres.getBody();
			}
			System.out.println("Approver Details :: "+apprdetails.toString());
			ApproverDetails finalApprdetails = apprdetails;
			PersonInformation finalRes = res1;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						sendnotimail.sendnotificationmail(finalApprdetails, finalRes.getPersonname(),login.getEmplid(),"Entertainment Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

		return "forms/reimbursement/entertainment/entertainmentClaim ::entertainmentClaim";
	}

	@ResponseBody
	@RequestMapping(value = "/getEntertainmentsearchdata", method = RequestMethod.GET)
	public EntertainmentSearchModel[] entertainmentSearchModel(HttpServletRequest request, Model model) {

		//System.out.println("In Entertainment Search");
		Login login = (Login) request.getSession().getAttribute("login");
		EntertainmentSearchModel[] res = null;
		String url = appgateway.getAppgatewaypyrl_sandhya() + "/api/entertainmentrmbrs/getListbypersonnumber/" + login.getEmplid();
		//System.out.println("URL: " + url);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(headers);

		ResponseEntity<EntertainmentSearchModel[]> response = restTemplate.exchange(url, HttpMethod.GET, req, EntertainmentSearchModel[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
			System.out.println(response.getStatusCode());
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		//System.out.println("Transport Search Model : " + res[0].toString());
				
		return res;
	}


	@RequestMapping("/getviewEntertainmentdata/{claimid}/{mode}")
	public String viewData(@PathVariable("claimid") String claimid, @PathVariable("mode") String mode, Model model,
						   HttpServletRequest request) throws URISyntaxException {
		Login login = (Login) request.getSession().getAttribute("login");


		PersonInformation res = null;

		String RID="5";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, login.getEmplid());
		model.addAttribute("empent", ceiling);
		
		
		String url1 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println("URL: " + url1);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requ = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> response = restTemplate.exchange(url1, HttpMethod.GET, requ, PersonInformation.class);
		res = response.getBody();
		//System.out.println("Header" + res.toString());

		model.addAttribute("personInfo", res);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requ, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);

		String SearchentertainmentId = claimid;

		EntertainmentReimbursementmodel entertainmentReimbursementmodel = new EntertainmentReimbursementmodel();
	//	System.out.println("SearchentertainmentId" + entertainmentReimbursementmodel.toString());
		String urrlentertainmentid = appgateway.getAppgatewaypyrl_sandhya() + "/api/entertainmentrmbrs/getListbyentertainmentId/" + SearchentertainmentId;

		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> gettravelRequest = new HttpEntity<String>(headerss);

		ResponseEntity<EntertainmentReimbursementmodel> getentertainmentResponse = restTemplate.exchange(urrlentertainmentid, HttpMethod.GET,
				gettravelRequest, EntertainmentReimbursementmodel.class);

		if (getentertainmentResponse.getStatusCode() == HttpStatus.OK) {
			entertainmentReimbursementmodel = getentertainmentResponse.getBody();
			//System.out.println("suc" + transportReimbursementModel.toString());
		} else {
			System.out.println("Request Failed");
			System.out.println(getentertainmentResponse.getStatusCode());
		}

		////*****************Updated by Ravi for view mode Approval--START****************\\\\\\\\\\\\\\\\\\\\\\\\\\
		//String RID = "5";
		ApprovalChildModel[] apr = null;
		String aprroveurl = appgateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + claimid + "/" + login.getEmplid();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK
		) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		model.addAttribute("approverdata", apr);

		////*****************Updated by Ravi--END****************\\\\\\\\\\\\\\\\\\\\\\\\\\



		model.addAttribute("enter", entertainmentReimbursementmodel);
		model.addAttribute("mode", mode);

		return "forms/reimbursement/entertainment/entertainmentClaim ::entertainmentClaim";

	}

	///////////////////***************Updated By Ravi-----Approval Start-----*****************\\\\\\\\\\\\\\\\\\\\

	@RequestMapping("/viewentertainmentapprovaldata/{claimid}/{mode}/{status}")
	public String viewapprovalData(@PathVariable("claimid") String claimid,@PathVariable("status") String status, @PathVariable("mode") String mode, Model model, HttpServletRequest reques) {
		EntertainmentReimbursementmodel entertainmentReimbursementmodel = new EntertainmentReimbursementmodel();
		ApprovalChildModel[] apr = null;

		Login login = (Login) reques.getSession().getAttribute("login");
		String RID = "5";

		String url = appgateway.getAppgatewaypyrl_sandhya() + "/api/entertainmentrmbrs/getListbyentertainmentId/" + claimid;
		//System.out.println("urrk "+url);
		HttpEntity<String> req = new HttpEntity<String>(headers);
		ResponseEntity<EntertainmentReimbursementmodel> response = restTemplate.exchange(url, HttpMethod.GET, req, EntertainmentReimbursementmodel.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			entertainmentReimbursementmodel = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		String urlNotify = appgateway.getAppgateway()+"/Notification/getApprovalDataByPersonnoStatus/"+RID+"/" + claimid+"/"+login.getEmplid()+"/"+status;

		//System.out.println(urlNotify);
		headers.setContentType(MediaType.APPLICATION_JSON);

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
		String aprroveurl = appgateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + claimid + "/" + notify.getSubmittedbypersonno();


		String ceiling = eligibilityreimb.getCeilinglimit(RID, notify.getSubmittedbypersonno());
		model.addAttribute("empent", ceiling);
		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		model.addAttribute("NotifynavBar", notify);
		model.addAttribute("APPRAMT",notify.getApprovedamount());

		model.addAttribute("enter", entertainmentReimbursementmodel);
		model.addAttribute("mode", mode);

		model.addAttribute("approverdata", apr);
		//System.out.println("GGGGG: "+entertainmentReimbursementmodel.getPeriodfrom());

		PersonInformation resp = new PersonInformation();
		String url1 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + notify.getSubmittedbypersonno();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requ = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> respons = restTemplate.exchange(url1, HttpMethod.GET, requ, PersonInformation.class);
		resp = respons.getBody();
		model.addAttribute("personInfo", resp);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+ notify.getSubmittedbypersonno()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requ, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("mode", mode);

		return "forms/reimbursement/entertainment/entertainmentClaimApproval :: entertainmentClaimapproval";
	}





	@RequestMapping(value = "/entertainmentApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel entertainmentApprovalUpdate(@PathVariable("_status") String _status,String msg, double approvedamt, long claimid, HttpServletRequest reques,Model model) throws IOException, MessagingException {
		SingleResponseModel res = new SingleResponseModel();

		Login login = (Login) reques.getSession().getAttribute("login");


		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String RID="5";
		//NotificationModel approvalchange=null;
		String urlNotify = appgateway.getAppgateway()+"/Notification/getUnreadApprovalData/"+RID+"/" + claimid+"/"+login.getEmplid();
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
		//System.out.println(notify.toString());

		String url = appgateway.getAppgateway() + "/Notification/submittedAprovelById";

		HttpEntity<NotificationModel> request = new HttpEntity<NotificationModel>(notify,headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, SingleResponseModel.class);
		if(response.getStatusCode() == HttpStatus.OK) {
			res=response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}

		if(res.getStatus().equals("true")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = appgateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+claimid+"/"+notify.getSubmittedbypersonno();
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"Entertainment Reimbursement");
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
		
	  
	  
	  
		

