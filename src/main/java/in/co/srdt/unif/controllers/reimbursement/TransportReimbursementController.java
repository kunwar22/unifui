package in.co.srdt.unif.controllers.reimbursement;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.rembchild.ApprovalChildModel;
import in.co.srdt.unif.model.rembtransport.TransportReimbursementModel;
import in.co.srdt.unif.model.rembtransport.TransportSearchModel;
import in.co.srdt.unif.model.rembtransport.TransportTravelHistory;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;


@Controller
@RequestMapping("/reimbursement")
@SessionAttributes("transportmodel")
public class TransportReimbursementController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;

	@Autowired
	ApplicationGateway apiGateway;

	@Autowired
	SmartValidator validator;

	@Autowired(required = true)
	private EligibilityReimbursement eligibilityreimb;

	@Autowired
	EligibilityReimbursement eligibility;

	@Autowired(required = true)
	private SendMail sendnotimail;

	Login userlogin = null;

	/*
	 * ============================== LOADING SEARCH PAGE ===============================
	 */

	@RequestMapping("/transportManager")
	public String transportReimbursement(HttpSession session, HttpServletRequest request, Model model) {
		/***************************************
		 * Fetching Details of Employee Starts
		 ****************************************************************/

		userlogin = (Login) request.getSession().getAttribute("login");
		PersonInformation empdetails = new PersonInformation();

		/* Getting Employee Details from API */

		String urlEmployeeDetails = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();

		//System.out.println("URL for getting employee details :: " + urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			empdetails = employeePersonalDetailResponse.getBody();

			//System.out.println("Employee Personal Details ==>" + empdetails);
		}
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", empdetails);

		/**************************************
		 * Fetching Details of Employee Ends *
		 *******************************************************/

		return "forms/reimbursement/transport/transportManager :: transportManager";
	}

	
	/* ============= LOADING TRANSPORT CLAIM PAGE ============ */
	@RequestMapping("/transport")
	public String transportReimbursement(Model model, HttpServletRequest request) {
		model.addAttribute("hdata", new PersonInformation());

		/***************************************
		 * Fetching Details of Employee Starts
		 ****************************************************************/
		TransportReimbursementModel transportmodel = new TransportReimbursementModel();
		userlogin = (Login) request.getSession().getAttribute("login");

		String RID = "2";
		String ceilingtwo = eligibilityreimb.getCeilinglimit(RID, userlogin.getEmplid());
		model.addAttribute("empent", ceilingtwo);
		
		String RIDFOUR="16";
		String ceilingfour = eligibilityreimb.getCeilinglimit(RIDFOUR, userlogin.getEmplid());
		model.addAttribute("empentfour", ceilingfour);

		PersonInformation empdetails = null;

		String url1 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + userlogin.getEmplid();
		//System.out.println("URL: " + url1);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requ = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> response = restTemplate.exchange(url1, HttpMethod.GET, requ,
				PersonInformation.class);
		empdetails = response.getBody();
		//System.out.println("Header" + empdetails.toString());
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requ, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", empdetails);
		/**************************************
		 * Fetching Details of Employee Ends *
		 *******************************************************/

		model.addAttribute("transportmodel", transportmodel);

		return "forms/reimbursement/transport/transportClaim :: transportClaim";
	}

	// @RequestMapping("/transportManager")
	// public String transportmManger(HttpServletRequest request, Model model) {
	//
	// Login login = (Login) request.getSession().getAttribute("login");
	// PersonInformation res = null;
	//
	// String url1 = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/" +
	// login.getEmplid();
	// System.out.println("URL: " + url1);
	// headers.setContentType(MediaType.APPLICATION_JSON); HttpEntity<String> requ =
	// new HttpEntity<>(headers);
	// ResponseEntity<PersonInformation> response = restTemplate.exchange(url1,
	// HttpMethod.GET, requ, PersonInformation.class);
	// res = response.getBody();
	// System.out.println("Header" + res.toString());
	//
	// model.addAttribute("hdata",res);
	//
	//
	//
	// return "forms/reimbursement/transport/transportManager :: transportManager";
	// }
	//

	///// *****************Controller for Print Annexure Start
	///// by @Suraj*****************//

	@RequestMapping(value = "/declarationTransport/{travelid}", method = RequestMethod.GET)
	public String getDeclarationTransport(@PathVariable("travelid") long travelid, Model model,
			HttpServletRequest request) {
		Login login = (Login) request.getSession().getAttribute("login");
		PersonInformation empdetails = new PersonInformation();

		String urlEmployeeDetails = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate.exchange(urlEmployeeDetails,
				HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			empdetails = employeePersonalDetailResponse.getBody();

			//System.out.println("Employee Personal Details ==>" + empdetails);
		}

		model.addAttribute("empdetails", empdetails);

		//System.out.println("claimid ::::::::::: " + travelid);

		TransportSearchModel transportsearchmodel = new TransportSearchModel();

		String urltourreimbursementSearch = appgateway.getAppgatewaypyrl_sandhya() + "/api/travelrmbrs/gettraveldetailsbyid/"
				+ travelid;

		HttpEntity<String> transportreimbursement_search_request = new HttpEntity<String>(headers);

		ResponseEntity<TransportSearchModel> transport_response = restTemplate.exchange(urltourreimbursementSearch,
				HttpMethod.GET, transportreimbursement_search_request, TransportSearchModel.class);

		if (transport_response.getStatusCode() == HttpStatus.OK) {
			transportsearchmodel = transport_response.getBody();
			// System.out.println("Transport Search Model : " + transportsearchmodel);
		}

		model.addAttribute("transportmodel", transportsearchmodel);
//		model.addAttribute("transportmodel", transportsearchmodel.getHistories());

		return "forms/reimbursement/transport/transportDeclearation";
	}

	///// *************************************Controller for Print Annexure End by
	///// @Suraj*****************//

	/* Controller for saving Transport data*/ 
	@RequestMapping(value = "/saveTransportClaim", method = RequestMethod.POST)
	public String saveTransportClaim(@ModelAttribute("transportmodel") TransportReimbursementModel transportmodel, String attachment, String statusid,
			Model model, @RequestParam("file") MultipartFile file, HttpServletRequest req,
			BindingResult bindingResult, SessionStatus status) throws ParseException {
		//System.out.println("Inside Transport Save");
		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation res1 = null;

		String url1 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println("URL: " + url1);

		transportmodel.setPersonid(login.getEmplid());
		transportmodel.setPersonnumber(login.getEmplid());
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requ = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> response1 = restTemplate.exchange(url1, HttpMethod.GET, requ,
				PersonInformation.class);
		res1 = response1.getBody();
		//System.out.println("Header" + res1.toString());
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requ, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", res1);
		String RID = "2";
		String ceilingtwo = eligibilityreimb.getCeilinglimit(RID, userlogin.getEmplid());
		model.addAttribute("empent", ceilingtwo);
		
		String RIDFOUR="16";
		String ceilingfour = eligibilityreimb.getCeilinglimit(RIDFOUR, userlogin.getEmplid());
		model.addAttribute("empentfour", ceilingfour);
		validator.validate(transportmodel, bindingResult);

		for (TransportTravelHistory transportTravelHistory : transportmodel.getHistories()) {
			validator.validate(transportTravelHistory, bindingResult);
		}
		//System.out.println("======TRAVEL DATA =========> " + transportmodel.toString());

		model.addAttribute("transportmodel", transportmodel);
		model.addAttribute("bindingResult", bindingResult);

		for(int z=0; z<transportmodel.getHistories().size(); z++){
			SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
			// Get the two dates to be compared
			Date d1 = sdfo.parse(transportmodel.getHistories().get(z).getFromdate());
			Date d2 = sdfo.parse(transportmodel.getHistories().get(z).getTodate());
			// Compare the dates using compareTo()
			if (d1.compareTo(d2) > 0) {
				System.out.println("Date1 is after Date2");
				model.addAttribute("result", "DATEERROR");
				return "forms/reimbursement/transport/transportClaim :: transportClaim";
			}
		}

		//System.out.println("ERRORS :: " + bindingResult.getAllErrors());

		if (bindingResult.hasErrors()) {
			return "forms/reimbursement/transport/transportClaim :: transportClaim";
		}

		if (!file.isEmpty()) {
			String location = transportmodel.getAttachments();
			String filePath = new File("").getAbsolutePath() + File.separator + location;
			String storePath = location;

			// CREATE DIRECTORY IF NOT EXISTS
			File dir = new File(filePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// WRITE FILES TO DIRECTORY AND ENTRY INTO THE DATABASE

			try {

				// CREATING UNIQUE FILENAME
				String empl = login.getEmplid();
				// String empl="P10002";

				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				String generatedFileName = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss" + "'" + empl + "." + extension + "'").format(new Date());

				File target = new File(filePath + File.separator + generatedFileName);
				int readByteCount = 0;
				byte[] buffer = new byte[4096];

				BufferedInputStream in = new BufferedInputStream(file.getInputStream());
				FileOutputStream out = new FileOutputStream(target);
				while ((readByteCount = in.read(buffer)) != -1) {
					// System.out.println("file read");
					out.write(buffer, 0, readByteCount);
				}

				storePath += "/" + generatedFileName;

				/*
				 * String payLoad = ""; String url = "";
				 */

				transportmodel.setAttachments(storePath);
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("result", "IOEXCEPTION");
				return "forms/reimbursement/transport/transportClaim :: transportClaim";
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				model.addAttribute("result", "ILLEGALARG");
				return "forms/reimbursement/transport/transportClaim :: transportClaim";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("result", "WRITE_ERROR");
				return "forms/reimbursement/transport/transportClaim :: transportClaim";
			}
		} else {
			if (transportmodel.getAttachhidden() != null) {
				transportmodel.setAttachments(transportmodel.getAttachhidden());
			} else {
				transportmodel.setAttachments(null);
			}
		}

		if (statusid.equals("save")) {
			transportmodel.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			transportmodel.setStatus("submitted");
		}
		String url_transport = appgateway.getAppgatewaypyrl_sandhya() + "/api/travelrmbrs/claimtravelreimburse";
		// SingleResponseModel res = new SingleResponseModel();

		TransportReimbursementModel res = null;
		headers.setContentType(MediaType.APPLICATION_JSON);

		transportmodel.setReimbursename("Transport");

		//System.out.println("Transport ::" + transportmodel.toString());

		HttpEntity<TransportReimbursementModel> request = new HttpEntity<TransportReimbursementModel>(transportmodel,
				headers);

		ResponseEntity<TransportReimbursementModel> response = restTemplate.exchange(url_transport, HttpMethod.POST,
				request, TransportReimbursementModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
			//System.out.println("RESPONSE ===========" + res.toString());

		} else {
			System.out.println(response.getStatusCode());
			model.addAttribute("result", "LOG_ERROR");
			return "forms/reimbursement/transport/transportClaim :: transportClaim";
			// return "LOG_ERROR";
		}

		model.addAttribute("result", res.getSubmitStatus());
		if(res.getSubmitStatus().equals("Success")) {
			status.setComplete();
		}
		return "forms/reimbursement/transport/transportClaim :: transportClaim";
		/*if(res.getSubmitStatus().equals("alreadyApplied"))
		{
		return "forms/reimbursement/transport/transportClaim :: transportClaim";
		}
		else
		{
			return "forms/reimbursement/transport/transportManager :: transportManager";
		}*/
			
		// return "Success";
	}

	/* LOADING DATA FOR SEARCH PAGE */
	@ResponseBody
	@RequestMapping(value = "/getsearchdata", method = RequestMethod.GET)
	public TransportSearchModel[] telephonesearch(HttpServletRequest request, Model model) {

		//System.out.println("In transport Search");
		Login login = (Login) request.getSession().getAttribute("login");
		TransportSearchModel[] res = null;
		String url = appgateway.getAppgatewaypyrl_sandhya() + "/api/travelrmbrs/gettravelsbypersonnumber/" + login.getEmplid();
		//System.out.println("URL: " + url);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(headers);

		ResponseEntity<TransportSearchModel[]> response = restTemplate.exchange(url, HttpMethod.GET, req,
				TransportSearchModel[].class);
		//System.out.println(response);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
			//System.out.println(response.getStatusCode());
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		return res;
	}

	/* Getting DATA FOR VIEW AND EDIT PAGE */
	@RequestMapping("/getviewdata/{travelid}/{mode}")
	public String viewData(@PathVariable("travelid") String travelid, @PathVariable("mode") String mode, Model model,
			HttpServletRequest request){

		ApprovalChildModel[] apr = null;

		Login login = (Login) request.getSession().getAttribute("login");
		String RID = "2";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, userlogin.getEmplid());
		model.addAttribute("empent", ceiling);
		
		
		String RIDFOUR="16";
		String ceilingfour = eligibilityreimb.getCeilinglimit(RIDFOUR, userlogin.getEmplid());
		model.addAttribute("empentfour", ceilingfour);
		PersonInformation res = null;

		String url1 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println("URL: " + url1);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requ = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> response = restTemplate.exchange(url1, HttpMethod.GET, requ,
				PersonInformation.class);
		res = response.getBody();
		//System.out.println("Header" + res.toString());
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requ, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", res);

		String Searchtravelid = travelid;
		//System.out.println("Searchtravelid" + Searchtravelid);
		TransportReimbursementModel transportmodel = null;

		String urrltravelid = appgateway.getAppgatewaypyrl_sandhya() + "/api/travelrmbrs/gettraveldetailsbyid/"
				+ Searchtravelid;

		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> gettravelRequest = new HttpEntity<String>(headerss);

		ResponseEntity<TransportReimbursementModel> gettravelResponse = restTemplate.exchange(urrltravelid, HttpMethod.GET,
				gettravelRequest, TransportReimbursementModel.class);

		if (gettravelResponse.getStatusCode() == HttpStatus.OK) {
			transportmodel = gettravelResponse.getBody();
			// System.out.println("suc" + transportReimbursementModel.toString());
		} else {
			System.out.println("Request Failed");
			System.out.println(gettravelResponse.getStatusCode());
		}

		//// *****************Updated by Ravi for view mode
		//// Approval--START****************\\\\\\\\\\\\\\\\\\\\\\\\\\

		String aprroveurl = apiGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID
				+ "/" + travelid + "/" + login.getEmplid();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr,
				ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		model.addAttribute("approverdata", apr);

		//// *****************Updated by
		//// Ravi--END****************\\\\\\\\\\\\\\\\\\\\\\\\\\

		// System.out.println("data::::" +
		// transportReimbursementModel.getInsurancevalidity());

		model.addAttribute("transportmodel", transportmodel);
		model.addAttribute("mode", mode);

		return "forms/reimbursement/transport/transportClaim :: transportClaim";

	}

	/////////////////// ***************Updated By Ravi-----Approval
	/////////////////// Start-----*****************\\\\\\\\\\\\\\\\\\\\
	@RequestMapping("/viewtransapprovaldata/{claimid}/{mode}")
	public String viewtransapprovaldata(@PathVariable("claimid") String claimid, @PathVariable("mode") String mode,
			Model model, HttpServletRequest reques) {
		TransportSearchModel res = new TransportSearchModel();
		ApprovalChildModel[] apr = null;

		Login login = (Login) reques.getSession().getAttribute("login");
		String RID = "2";
		String url = apiGateway.getAppgatewaypyrl_sandhya() + "/api/travelrmbrs/gettraveldetailsbyid/" + claimid;
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<String>(headers);
		ResponseEntity<TransportSearchModel> response = restTemplate.exchange(url, HttpMethod.GET, req,
				TransportSearchModel.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		String urlNotify = apiGateway.getAppgateway() + "/Notification/getUnreadApprovalData/" + RID + "/" + claimid
				+ "/" + login.getEmplid();

		//System.out.println(urlNotify);

		headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		NotificationModel notify = null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);

		// NotificationModel approvalchange=null;
		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET,
				request1Noti, NotificationModel.class);

		if (responseNotify.getStatusCode() == HttpStatus.OK) {
			notify = responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		
		model.addAttribute("APPRAMT",notify.getApprovedamount()); 
		String aprroveurl = apiGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID
				+ "/" + claimid + "/" + notify.getSubmittedbypersonno();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr,
				ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		String ceiling = eligibility.getCeilinglimit(RID, login.getEmplid());
		model.addAttribute("empent", ceiling);
		
		
		String RIDFOUR="16";
		String ceilingfour = eligibilityreimb.getCeilinglimit(RIDFOUR, userlogin.getEmplid());
		model.addAttribute("empentfour", ceilingfour);

		model.addAttribute("NotifynavBar", notify);

		model.addAttribute("transport", res);
		model.addAttribute("mode", mode);

		model.addAttribute("approverdata", apr);
		// System.out.println(apr[0].getApproverpersonname());

		PersonInformation resp = new PersonInformation();
		String url1 = apiGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + notify.getSubmittedbypersonno();

		HttpEntity<String> requ = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> respons = restTemplate.exchange(url1, HttpMethod.GET, requ,
				PersonInformation.class);
		resp = respons.getBody();
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+notify.getSubmittedbypersonno()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requ, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", resp);

		return "forms/reimbursement/transport/transportClaimApproval :: transportClaimApproval";
	}

	
	@RequestMapping(value = "/transportUpdate", method = RequestMethod.POST)
	public String transportReimbursementUpdate(Model model, String statusid, String attachment,
			@ModelAttribute("transportmodel") TransportReimbursementModel transportmodel, @RequestParam("file") MultipartFile file,
			HttpServletRequest request, BindingResult bindingResult,SessionStatus status, String attachhidden) throws ParseException, IOException, MessagingException {

		Login login = (Login) request.getSession().getAttribute("login");
		String RID = "2";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, userlogin.getEmplid());
		model.addAttribute("empent", ceiling);
		
		String RIDFOUR="16";
		String ceilingfour = eligibilityreimb.getCeilinglimit(RIDFOUR, userlogin.getEmplid());
		model.addAttribute("empentfour", ceilingfour);
		PersonInformation res1 = null;

		String url1 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println("URL: " + url1);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request1 = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> response = restTemplate.exchange(url1, HttpMethod.GET, request1,
				PersonInformation.class);
		res1 = response.getBody();
		model.addAttribute("personInfo", res1);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, request1, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		//System.out.println("=========>In TransportUpdate<==============");
		SingleResponseModel res = new SingleResponseModel();
		//System.out.println("transport Updated Data!!!! " + transportmodel.toString());

		validator.validate(transportmodel, bindingResult);
		for (TransportTravelHistory transportTravelHistory : transportmodel.getHistories()) {
			validator.validate(transportTravelHistory, bindingResult);
		}

		model.addAttribute("transportmodel", transportmodel);
		model.addAttribute("bindingResult", bindingResult);
		model.addAttribute("mode", "edit");

	///	System.out.println("ERRORS :: " + bindingResult.getAllErrors());

		for(int z=0; z<transportmodel.getHistories().size(); z++){
			SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
			// Get the two dates to be compared
			Date d1 = sdfo.parse(transportmodel.getHistories().get(z).getFromdate());
			Date d2 = sdfo.parse(transportmodel.getHistories().get(z).getTodate());
			// Compare the dates using compareTo()
			if (d1.compareTo(d2) > 0) {
				System.out.println("Date1 is after Date2");
				model.addAttribute("result", "DATEERROR");
				return "forms/reimbursement/transport/transportClaim :: transportClaim";
			}
		}

		if (bindingResult.hasErrors()) {
			return "forms/reimbursement/transport/transportClaim :: transportClaim";
		}

		if (!file.isEmpty()) {
			String location = "/EmployeeDocs/"+login.getEmplid()+"/TransportReimbursement";

			String filePath = new File("").getAbsolutePath() + File.separator + location;
			String storePath = location;
			// WRITE FILES TO DIRECTORY AND ENTRY INTO THE DATABASE

			try {

				// CREATING UNIQUE FILENAME
				String empl = login.getEmplid();
				// String empl="P10002";

				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				String generatedFileName = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss" + "'" + empl + "." + extension + "'").format(new Date());

				// CREATE DIRECTORY IF NOT EXISTS
				File dir = new File(filePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				File target = new File(filePath + File.separator + generatedFileName);
				int readByteCount = 0;
				byte[] buffer = new byte[4096];

				BufferedInputStream in = new BufferedInputStream(file.getInputStream());
				FileOutputStream out = new FileOutputStream(target);
				while ((readByteCount = in.read(buffer)) != -1) {
					// System.out.println("file read");
					out.write(buffer, 0, readByteCount);
				}

				storePath += "/" + generatedFileName;

				String payLoad = "";
				String url4 = "";

				transportmodel.setAttachments(storePath);

			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("result", "IOEXCEPTION");
				return "forms/reimbursement/transport/transportClaim :: transportClaim";
				// return "LOG_ERROR";
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				model.addAttribute("result", "ILLEGALARG");
				return "forms/reimbursement/transport/transportClaim :: transportClaim";
				// return "LOG_ERROR";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("result", "WRITE_ERROR");
				return "forms/reimbursement/transport/transportClaim :: transportClaim";
				// return "LOG_ERROR";
			}
		} else {
			if (attachhidden != null) {
				//System.out.println("ATTACHHIDDEN :: "+attachhidden);
				transportmodel.setAttachments(attachhidden);
			} else {
				transportmodel.setAttachments(null);
			}
		}
		if (statusid.equals("save")) {
			transportmodel.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			transportmodel.setStatus("submitted");
		}

		String url_transport = appgateway.getAppgatewaypyrl_sandhya() + "/api/travelrmbrs/updatetravelclaimdetails";
		// SingleResponseModel res = new SingleResponseModel();

		TransportReimbursementModel res4 = null;
		headers.setContentType(MediaType.APPLICATION_JSON);

		transportmodel.setReimbursename("Transport");

		//System.out.println("Transport UPDATE CASE ::" + transportmodel.toString());

		HttpEntity<TransportReimbursementModel> request4 = new HttpEntity<TransportReimbursementModel>(transportmodel,
				headers);

		ResponseEntity<TransportReimbursementModel> response4 = restTemplate.exchange(url_transport, HttpMethod.POST, request4,
				TransportReimbursementModel.class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			res4 = response4.getBody();
			//System.out.println("INSIDE UPDATE RESPONSE=============>"+res4.getSubmitStatus());
		} else {
			System.out.println(response.getStatusCode());
			model.addAttribute("result", "LOG_ERROR");
			return "forms/reimbursement/transport/transportClaim :: transportClaim";
			// return "LOG_ERROR";
		}

		model.addAttribute("transportmodel", transportmodel);
		model.addAttribute("result", res4.getSubmitStatus());
		if(res4.getSubmitStatus().equals("Success")) {
			status.setComplete();
		}

		if(res4.getSubmitStatus().equals("Success") && statusid.equals("submit")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = apiGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+transportmodel.getTravelid()+"/"+userlogin.getEmplid();
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalRes.getPersonname(),userlogin.getEmplid(),"Transport Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

		return "forms/reimbursement/transport/transportClaim :: transportClaim";

	}
	
	//CODE FOR DELETING TRANSPORT HISTORY STARTS HERE 
	
	 @GetMapping("/transporthistory/removehistory/{index}")
		public @ResponseBody TransportReimbursementModel removehistory(@SessionAttribute("transportmodel") TransportReimbursementModel transportmodel, @PathVariable int index)
		{
			//System.out.println("index : " + index);
			
			//System.out.println("History Size ::::: " + transportmodel.getHistories().size());
			
			transportmodel.getHistories().remove(index);    			
			
			//System.out.println("History Size ::::: " + transportmodel.getHistories().size());
			//System.out.println("UPDATED TRAVEL HISTORY =======>> "+ transportmodel.getHistories());
			
			return transportmodel;
		}
	 
	 
	/* CODE FOR DELETING TRANSPORT HISTORY ENDS HERE */
	 

	@RequestMapping(value = "/transportApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel telephoneApprovalUpdate(@PathVariable("_status") String _status, String msg,
			double claimedamt,double approvedamt, long travelid, HttpServletRequest reques, Model model) throws IOException, MessagingException {
		SingleResponseModel res = new SingleResponseModel();

		Login login = (Login) reques.getSession().getAttribute("login");

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		NotificationModel notify = null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String RID = "2";
		// NotificationModel approvalchange=null;
		String urlNotify = apiGateway.getAppgateway() + "/Notification/getUnreadApprovalData/" + RID + "/" + travelid
				+ "/" + login.getEmplid();
		//System.out.println(urlNotify);
		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET,
				request1Noti, NotificationModel.class);
		if (responseNotify.getStatusCode() == HttpStatus.OK) {
			notify = responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		model.addAttribute("NotifynavBar", notify);

		// TransportReimbursementController trans = new
		// TransportReimbursementController();
		String reimburseStatusURL = apiGateway.getAppgateway() + "/travelrmbrs/claimtravelreimburse";

		notify.setStatus(_status);
		notify.setMessage(msg);
		notify.setApprovedamount(approvedamt);
		//System.out.println(notify.toString());

		String url = apiGateway.getAppgateway() + "/Notification/submittedAprovelById";

		HttpEntity<NotificationModel> request = new HttpEntity<NotificationModel>(notify, headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request,
				SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}

		/*
		 * if(res.getStatus().equals("Success")) {
		 * tele.setReimburseid(notify.getModuleid()); ////change on the basis of
		 * different reimbursment////// tele.setTelephoneclaimid(claimid);
		 * tele.setStatus(_status); tele.setApprovedamt(approvedamt);
		 * 
		 * HttpEntity<TelephoneModel> request1 = new HttpEntity<TelephoneModel>(tele,
		 * httpHeaders); ResponseEntity<TelephoneModel> responseTelephone =
		 * restTemplate.exchange(reimburseStatusURL, HttpMethod.POST, request1,
		 * TelephoneModel.class);
		 * 
		 * if (responseTelephone.getStatusCode() == HttpStatus.OK) { tele =
		 * responseTelephone.getBody(); } else {
		 * System.out.println(response.getStatusCode());
		 * 
		 * } }
		 */

		if(res.getStatus().equals("true")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = apiGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+travelid+"/"+notify.getSubmittedbypersonno();
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"Transport Reimbursement");
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