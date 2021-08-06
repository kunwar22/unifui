package in.co.srdt.unif.controllers.reimbursement;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import in.co.srdt.unif.enums.Identifying;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.create.DoubleResponseModel;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.rembchild.ApprovalChildModel;
import in.co.srdt.unif.model.rembctg.CtgBasicPay;
import in.co.srdt.unif.model.rembctg.CtgReimburse;
import in.co.srdt.unif.model.search.LocationSearchResult;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/ctgReimbursement")
public class CtgRembController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;

	@Autowired
	SmartValidator validator;

	@Autowired(required = true)
	private SendMail sendnotimail;
	
	CtgRembController() {

	}

	CtgRembController(HttpHeaders headers, RestTemplate restTemplate) {
		this.headers = headers;
		this.restTemplate = restTemplate;
	}

	@RequestMapping("/manageCtgReimb")
	public String manageCtgReimb(Model model, HttpServletRequest req) {

		CtgReimburse ctgReimburse = new CtgReimburse();
		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation personInformation = null;
		String url = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url, HttpMethod.GET, requestPesro,
				PersonInformation.class);

		if (responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println(responsePerso.getStatusCode());
		}
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", personInformation);
		model.addAttribute("CtgReimburse", ctgReimburse);

		return "forms/reimbursement/ctgReimbursement/CtgSearch :: ctgSearch";
	}

	@RequestMapping("/CreateCTGRemb")
	public String createctgremb(@ModelAttribute("CtgReimburse") CtgReimburse ctgReimburse, Model model,
			HttpServletRequest req) {
		CtgReimburse resp = new CtgReimburse();

		String searchName = "";
		String searchCode = "";
		String searchCountry = "";
		String searchStatus = "Active";

		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway() + "/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<LocationSearchResult[]> response = restTemplate.exchange(urlLocation, HttpMethod.POST, request,
				LocationSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			locationId = response.getBody();
		} else {
			System.out.println("Request Failed of Location");
			System.out.println(response.getStatusCode());
		}

		CommonLOV[] om_project = null;

		String urlom_project = appgateway.getAppgateway() + "/General/loadProjectOM";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestveh = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseom = restTemplate.exchange(urlom_project, HttpMethod.GET, requestveh,
				CommonLOV[].class);

		if (responseom.getStatusCode() == HttpStatus.OK) {
			om_project = responseom.getBody();

		}

		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation personInformation = null;
		String url = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url, HttpMethod.GET, requestPesro,
				PersonInformation.class);

		if (responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}

		CtgBasicPay ctgBasicPay = null;
		String urlctgbsicpay = appgateway.getAppgateway() + "/PersonCommonData/getPersonBasicPayById/"
				+ login.getEmplid();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestBsicpay = new HttpEntity<String>(headers);
		ResponseEntity<CtgBasicPay> responseBsicpay = restTemplate.exchange(urlctgbsicpay, HttpMethod.GET,
				requestBsicpay, CtgBasicPay.class);

		if (responseBsicpay.getStatusCode() == HttpStatus.OK) {
			ctgBasicPay = responseBsicpay.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseBsicpay.getStatusCode());
		}

		String RID = "11";

		model.addAttribute("ctgBasicPay", ctgBasicPay);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", personInformation);
		model.addAttribute("samecity", Identifying.values());
		model.addAttribute("joinenclose", Identifying.values());
		model.addAttribute("ctgclaim", Identifying.values());
		model.addAttribute("effectchain", Identifying.values());
		model.addAttribute("vehicalclaim", Identifying.values());
		model.addAttribute("previousorg", Identifying.values());
		model.addAttribute("recieptenc", Identifying.values());
		model.addAttribute("personalconv", Identifying.values());
		model.addAttribute("ownvehical", Identifying.values());
		
		model.addAttribute("fair", Identifying.values());

		model.addAttribute("om_project", om_project);
		model.addAttribute("viewdata", resp);
		model.addAttribute("location", locationId);
		model.addAttribute("CtgReimburse", new CtgReimburse());
		model.addAttribute("personInfo", personInformation);

		return "forms/reimbursement/ctgReimbursement/ctgCreate :: createCtgRemb";
	}

	@RequestMapping(value = "/saveCtgReimbursement", method = RequestMethod.POST)
	public String saveCtgReimburs(@ModelAttribute("CtgReimburse") CtgReimburse CtgReimburse,
			@RequestParam("file") MultipartFile[] files, String statusid, HttpServletRequest request, Model model,
			BindingResult bindingResult) {

		if (statusid.equals("save")) {
			CtgReimburse.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			CtgReimburse.setStatus("Submitted");
		}

		Login login = (Login) request.getSession().getAttribute("login");
		String RID = "11";
			/********************************************************************************************/
		CtgReimburse resp = new CtgReimburse();

		String searchName = "";
		String searchCode = "";
		String searchCountry = "";
		String searchStatus = "Active";

		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway() + "/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestloc = new HttpEntity<String>(payLode, headers);

		ResponseEntity<LocationSearchResult[]> responseloc = restTemplate.exchange(urlLocation, HttpMethod.POST,
				requestloc, LocationSearchResult[].class);

		if (responseloc.getStatusCode() == HttpStatus.OK) {
			locationId = responseloc.getBody();
		} else {
			System.out.println("Request Failed of Location");
			System.out.println(responseloc.getStatusCode());
		}

		CommonLOV[] om_project = null;

		String urlom_project = appgateway.getAppgateway() + "/General/loadProjectOM";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestveh = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseom = restTemplate.exchange(urlom_project, HttpMethod.GET, requestveh,
				CommonLOV[].class);

		if (responseom.getStatusCode() == HttpStatus.OK) {
			om_project = responseom.getBody();

		}

		PersonInformation personInformation = null;
		String urlP = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
	
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(urlP, HttpMethod.GET, requestPesro,
				PersonInformation.class);

		if (responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}

		CtgBasicPay ctgBasicPay = null;
		String urlctgbsicpay = appgateway.getAppgateway() + "/PersonCommonData/getPersonBasicPayById/"
				+ login.getEmplid();
	
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestBsicpay = new HttpEntity<String>(headers);
		ResponseEntity<CtgBasicPay> responseBsicpay = restTemplate.exchange(urlctgbsicpay, HttpMethod.GET,
				requestBsicpay, CtgBasicPay.class);

		if (responseBsicpay.getStatusCode() == HttpStatus.OK) {
			ctgBasicPay = responseBsicpay.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseBsicpay.getStatusCode());
		}

		model.addAttribute("ctgBasicPay", ctgBasicPay);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", personInformation);
		model.addAttribute("samecity", Identifying.values());
		model.addAttribute("joinenclose", Identifying.values());
		model.addAttribute("ctgclaim", Identifying.values());
		model.addAttribute("effectchain", Identifying.values());
		model.addAttribute("vehicalclaim", Identifying.values());
		model.addAttribute("previousorg", Identifying.values());
		model.addAttribute("recieptenc", Identifying.values());
		model.addAttribute("personalconv", Identifying.values());
		model.addAttribute("ownvehical", Identifying.values());
		model.addAttribute("fair", Identifying.values());

		model.addAttribute("om_project", om_project);
		model.addAttribute("viewdata", resp);
		model.addAttribute("location", locationId);
		model.addAttribute("CtgReimburse", CtgReimburse);
		model.addAttribute("personInfo", personInformation);

		validator.validate(CtgReimburse, bindingResult);
		model.addAttribute("bindingResult", bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("result", "Validation Failed");
			return "forms/reimbursement/ctgReimbursement/ctgCreate :: createCtgRemb";
		}
		/********************************************************************************************/
		
		int f0=0; int f1=0; int f2=0; int f3=0; int f4=0;
		 
		if (files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				String location = CtgReimburse.getFilename2();
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
					String extension = FilenameUtils.getExtension(files[i].getOriginalFilename());
					if (extension == "") {
						continue;
					}
					String generatedFileName = new SimpleDateFormat(
							"yyyy-MM-dd-HH-mm-ss" + "'" + empl + "_" + (i + 1) + "." + extension + "'")
									.format(new Date());
					File target = new File(filePath + File.separator + generatedFileName);
					int readByteCount = 0;
					byte[] buffer = new byte[4096];

					BufferedInputStream in = new BufferedInputStream(files[i].getInputStream());
					FileOutputStream out = new FileOutputStream(target);
					while ((readByteCount = in.read(buffer)) != -1) {
						out.write(buffer, 0, readByteCount);
					}

					storePath += "/" + generatedFileName;

					String payLoad = "";
					String url = "";
					
					if (CtgReimburse.getFilepres4().equals("Y")) {
						if(f0==0) {
							f0=1;
							CtgReimburse.setAttachment4(storePath);
							continue;
						}
					}else {
						CtgReimburse.setAttachment4(CtgReimburse.getAttachhidden4());
					}

					if (CtgReimburse.getFilepres1().equals("Y")) {
						if(f1==0) {
							f1=1;
							CtgReimburse.setAttachment1(storePath);
							continue;
						}
					}else {
						CtgReimburse.setAttachment1(CtgReimburse.getAttachhidden1());
					}
					
					if (CtgReimburse.getFilepres2().equals("Y")) {
						if(f2==0) {
							f2=1;
							CtgReimburse.setAttachment2(storePath);
							continue;
						}
					}else {
						CtgReimburse.setAttachment2(CtgReimburse.getAttachhidden2());
					}
					
					if (CtgReimburse.getFilepres3().equals("Y")) {
						if(f3==0) {
							f3=1;
							CtgReimburse.setAttachment3(storePath);
							continue;
						}
					}else {
						CtgReimburse.setAttachment3(CtgReimburse.getAttachhidden3());
					}
					
					if (CtgReimburse.getFilepres5().equals("Y")) {
						if(f4==0) {
							f4=1;
							CtgReimburse.setAttachment5(storePath);
							continue;
						}
					}else {
						CtgReimburse.setAttachment5(CtgReimburse.getAttachhidden5());
					}

					storePath = location;
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("result", "WRITE_ERROR");
					return "forms/reimbursement/ctgReimbursement/ctgCreate :: createCtgRemb";
				}

			}
		}
		
		/********************************************************************************************/
		String urlCtg = appgateway.getAppgatewaypyrl_sandhya()
				+ "/api/ctgreimburseClaim/saveandcorrectCtgreimburseClaim";
//		SingleResponseModel respo = new SingleResponseModel();
		System.out.println("urlCtg "+urlCtg);
		DoubleResponseModel respo = new DoubleResponseModel();
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CtgReimburse> request1 = new HttpEntity<CtgReimburse>(CtgReimburse, headers);
			ResponseEntity<DoubleResponseModel> response1 = restTemplate.exchange(urlCtg, HttpMethod.POST, request1,
					DoubleResponseModel.class);
			if (response1.getStatusCode() == HttpStatus.OK) {
				respo = response1.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response1.getStatusCode());
				model.addAttribute("result", "LOG_ERROR");
				return "forms/reimbursement/ctgReimbursement/ctgCreate :: createCtgRemb";
			}
		} catch (Exception e) {
			e.printStackTrace();
			respo.setMessage("Failed to create CTG Reimbursement.");
		}

		
		if(respo.getMessage().equals("TransferDateAlreadyExist"))
		{
			model.addAttribute("result", respo.getMessage());
		}
		else if(respo.getMessage().equals("JourneyDateAlreadyExist"))
		{
			model.addAttribute("result", respo.getMessage());
		}
		else
		{
			model.addAttribute("result", respo.getStatus()); 
		}
		

		return "forms/reimbursement/ctgReimbursement/ctgCreate :: createCtgRemb";

	}
	@ResponseBody
	@RequestMapping(value = "/getCtgsearchdata", method = RequestMethod.GET)
	public CtgReimburse[] ctgReimburses(HttpServletRequest request, Model model) {

		CtgReimburse[] ctgReimburses = null;
		Login login = (Login) request.getSession().getAttribute("login");

		String urlCtg = appgateway.getAppgatewaypyrl_sandhya()
				+ "/api/ctgreimburseClaim/getListCtgReimburseClaimbypersonnumber/" + login.getLoginid();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request1 = new HttpEntity<String>(headers);

		ResponseEntity<CtgReimburse[]> response1 = restTemplate.exchange(urlCtg, HttpMethod.GET, request1,
				CtgReimburse[].class);

		if (response1.getStatusCode() == HttpStatus.OK) {
			ctgReimburses = response1.getBody();
		}

		return ctgReimburses;
	}

	@RequestMapping("/edit/editCtg/correctctg/{ctgrmbrsid}/{mode}")
	public String editctgrmbrsid(@PathVariable("ctgrmbrsid") String ctgrmbrsid, Model model,
			@PathVariable("mode") String mode, HttpServletRequest req) throws URISyntaxException {

		String Searchctgrmbrsid = ctgrmbrsid;

		CtgReimburse ctgReimburse = new CtgReimburse();

		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation personInformation = null;
		String url = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url, HttpMethod.GET, requestPesro,
				PersonInformation.class);

		if (responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}
		model.addAttribute("personInfo", personInformation);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("CtgReimburse", ctgReimburse);

		String urrlCGIRemb = appgateway.getAppgatewaypyrl_sandhya()
				+ "/api/ctgreimburseClaim/getListbyCtgReimburseClaimId/" + Searchctgrmbrsid;
		URI urlGI = new URI(urrlCGIRemb);
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getCGIRembRequest = new HttpEntity<String>(headerss);

		ResponseEntity<CtgReimburse> getCGIRembResponse = restTemplate.exchange(urlGI, HttpMethod.GET,
				getCGIRembRequest, CtgReimburse.class);

		if (getCGIRembResponse.getStatusCode() == HttpStatus.OK) {
			ctgReimburse = getCGIRembResponse.getBody();

		} else {
			System.out.println("Request Failed");
			System.out.println(getCGIRembResponse.getStatusCode());
		}

		String searchName = "";
		String searchCode = "";
		String searchCountry = "";
		String searchStatus = "";

		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway() + "/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<LocationSearchResult[]> response = restTemplate.exchange(urlLocation, HttpMethod.POST, request,
				LocationSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			locationId = response.getBody();
		} else {
			System.out.println("Request Failed of Location");
			System.out.println(response.getStatusCode());
		}

		if (ctgReimburse.getJourneydate() != null) {
			String effdtstart = ctgReimburse.getJourneydate();
			effdtstart = effdtstart.substring(0, 10);
			ctgReimburse.setJourneydate(effdtstart);
		}

		if (ctgReimburse.getTransferdate() != null) {
			String effdtstart = ctgReimburse.getTransferdate();
			effdtstart = effdtstart.substring(0, 10);
			ctgReimburse.setTransferdate(effdtstart);
		}

		CommonLOV[] om_project = null;
		String urlom_project = appgateway.getAppgateway() + "/General/loadProjectOM";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestom = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> responseom = restTemplate.exchange(urlom_project, HttpMethod.GET, requestom,
				CommonLOV[].class);
		if (responseom.getStatusCode() == HttpStatus.OK) {
			om_project = responseom.getBody();

		}

		CtgReimburse resp = new CtgReimburse();

		CtgBasicPay ctgBasicPay = null;
		String urlctgbsicpay = appgateway.getAppgateway() + "/PersonCommonData/getPersonBasicPayById/"
				+ login.getEmplid();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestBsicpay = new HttpEntity<String>(headers);
		ResponseEntity<CtgBasicPay> responseBsicpay = restTemplate.exchange(urlctgbsicpay, HttpMethod.GET,
				requestBsicpay, CtgBasicPay.class);

		if (responseBsicpay.getStatusCode() == HttpStatus.OK) {
			ctgBasicPay = responseBsicpay.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseBsicpay.getStatusCode());
		}

		String RID = "11";
		//// *****************Updated by Ravi for view mode
		//// Approval--START****************\\\\\\\\\\\\\\\\\\\\\\\\\\
		ApprovalChildModel[] apr = null;

		String aprroveurl = appgateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + ctgrmbrsid + "/" + login.getEmplid();
		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		model.addAttribute("approverdata", apr);

		//// *****************Updated by
		//// Ravi--END****************\\\\\\\\\\\\\\\\\\\\\\\\\\

		model.addAttribute("ctgBasicPay", ctgBasicPay);

		model.addAttribute("samecity", Identifying.values());
		model.addAttribute("joinenclose", Identifying.values());
		model.addAttribute("ctgclaim", Identifying.values());
		model.addAttribute("effectchain", Identifying.values());
		model.addAttribute("vehicalclaim", Identifying.values());
		model.addAttribute("previousorg", Identifying.values());
		model.addAttribute("recieptenc", Identifying.values());
		model.addAttribute("personalconv", Identifying.values());
		model.addAttribute("ownvehical", Identifying.values());
		model.addAttribute("fair", Identifying.values());

		model.addAttribute("CtgReimburse", ctgReimburse);
		model.addAttribute("om_project", om_project);
		model.addAttribute("viewdata", resp);
		model.addAttribute("location", locationId);
		model.addAttribute("personInfo", personInformation);
		return "forms/reimbursement/ctgReimbursement/ctgCreate :: createCtgRemb";

	}

	@RequestMapping(value = "/updateCtgReimbursement", method = RequestMethod.POST)

	public String updateCtgReimburs(@ModelAttribute CtgReimburse CtgReimburse,
			@RequestParam("file") MultipartFile[] files, String statusid, HttpServletRequest request, Model model,
			BindingResult bindingResult) throws IOException, MessagingException {
		
		if (statusid.equals("save")) {
			CtgReimburse.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			CtgReimburse.setStatus("Submitted");
		}

		Login login = (Login) request.getSession().getAttribute("login");
		String RID = "11";
		/********************************************************************************************/
		CtgReimburse resp = new CtgReimburse();

		String mode = "edit";

		String searchName = "";
		String searchCode = "";
		String searchCountry = "";
		String searchStatus = "Active";

		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway() + "/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestloc = new HttpEntity<String>(payLode, headers);

		ResponseEntity<LocationSearchResult[]> responseloc = restTemplate.exchange(urlLocation, HttpMethod.POST,
				requestloc, LocationSearchResult[].class);

		if (responseloc.getStatusCode() == HttpStatus.OK) {
			locationId = responseloc.getBody();
		} else {
			System.out.println("Request Failed of Location");
			System.out.println(responseloc.getStatusCode());
		}

		CommonLOV[] om_project = null;

		String urlom_project = appgateway.getAppgateway() + "/General/loadProjectOM";

		HttpEntity<String> requestveh = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseom = restTemplate.exchange(urlom_project, HttpMethod.GET, requestveh,
				CommonLOV[].class);

		if (responseom.getStatusCode() == HttpStatus.OK) {
			om_project = responseom.getBody();

		}

		PersonInformation personInformation = null;
		String urlP = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();

		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(urlP, HttpMethod.GET, requestPesro,
				PersonInformation.class);

		if (responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}

		CtgBasicPay ctgBasicPay = null;
		String urlctgbsicpay = appgateway.getAppgateway() + "/PersonCommonData/getPersonBasicPayById/"
				+ login.getEmplid();

		HttpEntity<String> requestBsicpay = new HttpEntity<String>(headers);
		ResponseEntity<CtgBasicPay> responseBsicpay = restTemplate.exchange(urlctgbsicpay, HttpMethod.GET,
				requestBsicpay, CtgBasicPay.class);

		if (responseBsicpay.getStatusCode() == HttpStatus.OK) {
			ctgBasicPay = responseBsicpay.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseBsicpay.getStatusCode());
		}
		model.addAttribute("ctgBasicPay", ctgBasicPay);
		model.addAttribute("CtgReimburse", CtgReimburse);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", personInformation);
		model.addAttribute("samecity", Identifying.values());
		model.addAttribute("joinenclose", Identifying.values());
		model.addAttribute("ctgclaim", Identifying.values());
		model.addAttribute("effectchain", Identifying.values());
		model.addAttribute("vehicalclaim", Identifying.values());
		model.addAttribute("previousorg", Identifying.values());
		model.addAttribute("recieptenc", Identifying.values());
		model.addAttribute("personalconv", Identifying.values());
		model.addAttribute("ownvehical", Identifying.values());

		model.addAttribute("om_project", om_project);
		model.addAttribute("viewdata", resp);
		model.addAttribute("location", locationId);
		model.addAttribute("personInfo", personInformation);

		validator.validate(CtgReimburse, bindingResult);
		model.addAttribute("bindingResult", bindingResult);

		model.addAttribute("mode", mode);

		if (bindingResult.hasErrors()) {
			model.addAttribute("result", "Validation Failed");
			return "forms/reimbursement/ctgReimbursement/ctgCreate :: createCtgRemb";
		}
		/********************************************************************************************/
		
		int f0=0; int f1=0; int f2=0; int f3=0; int f4=0;
		 
		if (files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				String location = CtgReimburse.getFilename2();
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
					String extension = FilenameUtils.getExtension(files[i].getOriginalFilename());
					if (extension == "") {
						continue;
					}
					String generatedFileName = new SimpleDateFormat(
							"yyyy-MM-dd-HH-mm-ss" + "'" + empl + "_" + (i + 1) + "." + extension + "'")
									.format(new Date());
					File target = new File(filePath + File.separator + generatedFileName);
					int readByteCount = 0;
					byte[] buffer = new byte[4096];

					BufferedInputStream in = new BufferedInputStream(files[i].getInputStream());
					FileOutputStream out = new FileOutputStream(target);
					while ((readByteCount = in.read(buffer)) != -1) {
						out.write(buffer, 0, readByteCount);
					}

					storePath += "/" + generatedFileName;

					String payLoad = "";
					String url = "";

					if (CtgReimburse.getFilepres4().equals("Y")) {
						if(f0==0) {
							f0=1;
							CtgReimburse.setAttachment4(storePath);
							continue;
						}
					}else {
						CtgReimburse.setAttachment4(CtgReimburse.getAttachhidden4());
					}

					if (CtgReimburse.getFilepres1().equals("Y")) {
						if(f1==0) {
							f1=1;
							CtgReimburse.setAttachment1(storePath);
							continue;
						}
					}else {
						CtgReimburse.setAttachment1(CtgReimburse.getAttachhidden1());
					}
					
					if (CtgReimburse.getFilepres2().equals("Y")) {
						if(f2==0) {
							f2=1;
							CtgReimburse.setAttachment2(storePath);
							continue;
						}
					}else {
						CtgReimburse.setAttachment2(CtgReimburse.getAttachhidden2());
					}
					
					if (CtgReimburse.getFilepres3().equals("Y")) {
						if(f3==0) {
							f3=1;
							CtgReimburse.setAttachment3(storePath);
							continue;
						}
					}else {
						CtgReimburse.setAttachment3(CtgReimburse.getAttachhidden3());
					}
					
					if (CtgReimburse.getFilepres5().equals("Y")) {
						if(f4==0) {
							f4=1;
							CtgReimburse.setAttachment5(storePath);
							continue;
						}
					}else {
						CtgReimburse.setAttachment5(CtgReimburse.getAttachhidden5());
					}

					storePath = location;
				} catch (Exception e) {
					//System.out.println("FILE ERRORRRRRR");
					e.printStackTrace();
					model.addAttribute("result", "WRITE_ERROR");
					return "forms/reimbursement/ctgReimbursement/ctgCreate :: createCtgRemb";
				}

			}
		}
		
		String urlCtg = appgateway.getAppgatewaypyrl_sandhya()
				+ "/api/ctgreimburseClaim/saveandcorrectCtgreimburseClaim";
		DoubleResponseModel respo = new DoubleResponseModel();
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CtgReimburse> request1 = new HttpEntity<CtgReimburse>(CtgReimburse, headers);
			System.out.println("CtgReimburse ::: "+CtgReimburse.toString());
			ResponseEntity<DoubleResponseModel> response1 = restTemplate.exchange(urlCtg, HttpMethod.POST, request1,
					DoubleResponseModel.class);
			if (response1.getStatusCode() == HttpStatus.OK) {
				respo = response1.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response1.getStatusCode());
				model.addAttribute("result", "LOG_ERROR");
				return "forms/reimbursement/ctgReimbursement/ctgCreate :: createCtgRemb";

			}
		} catch (Exception e) {
			e.printStackTrace();
			respo.setMessage("Failed to create CTG Reimbursement.");
		}

		if(respo.getMessage().equals("TransferDateAlreadyExist"))
		{
			model.addAttribute("result", respo.getMessage());
		}
		else if(respo.getMessage().equals("JourneyDateAlreadyExist"))
		{
			model.addAttribute("result", respo.getMessage());
		}
		else
		{
			model.addAttribute("result", respo.getStatus()); 
		}

		if(respo.getStatus().equals("Success") && statusid.equals("submit")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = appgateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+CtgReimburse.getClaimid()+"/"+login.getEmplid();
			System.out.println("URL APPROVER DATA :: "+getapprovalperson);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emprequest = new HttpEntity<String>(headers);
			ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, emprequest, ApproverDetails.class);
			if (apprdetailsres.getStatusCode() == HttpStatus.OK) {
				apprdetails = apprdetailsres.getBody();
			}
			System.out.println("Approver Details :: "+apprdetails.toString());
			ApproverDetails finalApprdetails = apprdetails;
			PersonInformation finalPersonInformation = personInformation;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						sendnotimail.sendnotificationmail(finalApprdetails, finalPersonInformation.getPersonname(),login.getEmplid(),"CTG Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

		return "forms/reimbursement/ctgReimbursement/ctgCreate :: createCtgRemb";

	}

	@RequestMapping(value = "/ctgApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel ctgApprovalUpdate(@ModelAttribute("ctgReimburse") CtgReimburse ctgReimburse,@PathVariable("_status") String _status, String msg,
			double approvedamt, long claimid, HttpServletRequest reques, Model model) throws IOException, MessagingException {
		SingleResponseModel res = new SingleResponseModel();
		SingleResponseModel res2 =null;
		
		Login login = (Login) reques.getSession().getAttribute("login");

		headers.setContentType(MediaType.APPLICATION_JSON);
		NotificationModel notify = null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String RID = "11";
		String urlNotify = appgateway.getAppgateway() + "/Notification/getUnreadApprovalData/" + RID + "/" + claimid
				+ "/" + login.getEmplid();
		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET,
				request1Noti, NotificationModel.class);
		if (responseNotify.getStatusCode() == HttpStatus.OK) {
			notify = responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		model.addAttribute("NotifynavBar", notify);

		notify.setStatus(_status);
		notify.setMessage(msg);
		notify.setApprovedamount(approvedamt);

		String url = appgateway.getAppgateway() + "/Notification/submittedAprovelById";

		HttpEntity<NotificationModel> request = new HttpEntity<NotificationModel>(notify, headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request,
				SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		
		
			 String urlsave = appgateway.getAppgatewaypyrl_sandhya()+"/api/ctgreimburseClaim/updateTravleingApprovelAmt";
	            
	            HttpEntity<CtgReimburse> request1 = new HttpEntity<CtgReimburse>(ctgReimburse, headers);
	            
	            ResponseEntity<SingleResponseModel> response1 = restTemplate.exchange(urlsave, HttpMethod.POST, request1, SingleResponseModel.class);
	            
	            if (response1.getStatusCode() == HttpStatus.OK) {
	                res2 = response1.getBody();
	             
	            } else {
	                System.out.println(response1.getStatusCode());
	                System.out.println("error data:::"+res.toString());
	                model.addAttribute("result", "LOG_ERROR");

	            }
		
		
		
		
		
		
		
		
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		//Rejected
		//Approved
		if(res.getStatus().equals("true") && _status.equals("Approved")) {
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"CTG Reimbursement");
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
			String url1 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + notify.getSubmittedbypersonno();
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
						sendnotimail.sendrejectionmail(finalPersonInformation.getEmailaid(),finalNotify.getSubmittedbypersonname(),claimid,"CTG Reimbursement");
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


	@RequestMapping("/viewctgapproval/{ctgrmbrsid}/{mode}/{status}")
	public String viewctgapproval(@PathVariable("ctgrmbrsid") String ctgrmbrsid, @PathVariable("status") String status, Model model,
								  @PathVariable("mode") String mode, HttpServletRequest req) throws URISyntaxException {

		Login login = (Login) req.getSession().getAttribute("login");

		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String RID="11";
		String urlNotify = appgateway.getAppgateway()+"/Notification/getApprovalDataByPersonnoStatus/"+RID+"/" + ctgrmbrsid+"/"+login.getEmplid()+"/"+status;
		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET, request1Noti, NotificationModel.class);
		if(responseNotify.getStatusCode() == HttpStatus.OK) {
			notify=responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		model.addAttribute("NotifynavBar", notify);
		model.addAttribute("APPRAMT",notify.getApprovedamount());
		String Searchctgrmbrsid = ctgrmbrsid;

		CtgReimburse ctgReimburse = new CtgReimburse();

		PersonInformation personInformation = null;
		String url = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + notify.getSubmittedbypersonno();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url, HttpMethod.GET, requestPesro,
				PersonInformation.class);

		if (responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+notify.getSubmittedbypersonno()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", personInformation);

		model.addAttribute("CtgReimburse", ctgReimburse);

		String urrlCGIRemb = appgateway.getAppgatewaypyrl_sandhya()
				+ "/api/ctgreimburseClaim/getListbyCtgReimburseClaimId/" + Searchctgrmbrsid;
		URI urlGI = new URI(urrlCGIRemb);
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getCGIRembRequest = new HttpEntity<String>(headerss);

		ResponseEntity<CtgReimburse> getCGIRembResponse = restTemplate.exchange(urlGI, HttpMethod.GET,
				getCGIRembRequest, CtgReimburse.class);

		if (getCGIRembResponse.getStatusCode() == HttpStatus.OK) {
			ctgReimburse = getCGIRembResponse.getBody();

		} else {
			System.out.println("Request Failed");
			System.out.println(getCGIRembResponse.getStatusCode());
		}

		String searchName = "";
		String searchCode = "";
		String searchCountry = "";
		String searchStatus = "";

		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway() + "/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<LocationSearchResult[]> response = restTemplate.exchange(urlLocation, HttpMethod.POST, request,
				LocationSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			locationId = response.getBody();
		} else {
			System.out.println("Request Failed of Location");
			System.out.println(response.getStatusCode());
		}

		if (ctgReimburse.getJourneydate() != null) {
			String effdtstart = ctgReimburse.getJourneydate();
			effdtstart = effdtstart.substring(0, 10);
			ctgReimburse.setJourneydate(effdtstart);
		}

		if (ctgReimburse.getTransferdate() != null) {
			String effdtstart = ctgReimburse.getTransferdate();
			effdtstart = effdtstart.substring(0, 10);
			ctgReimburse.setTransferdate(effdtstart);
		}

		CommonLOV[] om_project = null;
		String urlom_project = appgateway.getAppgateway() + "/General/loadProjectOM";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestom = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> responseom = restTemplate.exchange(urlom_project, HttpMethod.GET, requestom,
				CommonLOV[].class);
		if (responseom.getStatusCode() == HttpStatus.OK) {
			om_project = responseom.getBody();
		}

		CtgReimburse resp = new CtgReimburse();

		CtgBasicPay ctgBasicPay = null;
		String urlctgbsicpay = appgateway.getAppgateway() + "/PersonCommonData/getPersonBasicPayById/"
				+ notify.getSubmittedbypersonno();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestBsicpay = new HttpEntity<String>(headers);
		ResponseEntity<CtgBasicPay> responseBsicpay = restTemplate.exchange(urlctgbsicpay, HttpMethod.GET,
				requestBsicpay, CtgBasicPay.class);

		if (responseBsicpay.getStatusCode() == HttpStatus.OK) {
			ctgBasicPay = responseBsicpay.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseBsicpay.getStatusCode());
		}

		//// *****************Updated by Ravi for view mode
		//// Approval--START****************\\\\\\\\\\\\\\\\\\\\\\\\\\
		ApprovalChildModel[] apr = null;

		String aprroveurl = appgateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID
				+ "/" + ctgrmbrsid + "/" + notify.getSubmittedbypersonno();

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

		model.addAttribute("mode", mode);
		model.addAttribute("ctgBasicPay", ctgBasicPay);

		model.addAttribute("samecity", Identifying.values());
		model.addAttribute("joinenclose", Identifying.values());
		model.addAttribute("ctgclaim", Identifying.values());
		model.addAttribute("effectchain", Identifying.values());
		model.addAttribute("vehicalclaim", Identifying.values());
		model.addAttribute("previousorg", Identifying.values());
		model.addAttribute("recieptenc", Identifying.values());
		model.addAttribute("personalconv", Identifying.values());
		model.addAttribute("ownvehical", Identifying.values());

		model.addAttribute("CtgReimburse", ctgReimburse);
		model.addAttribute("om_project", om_project);
		model.addAttribute("viewdata", resp);
		model.addAttribute("location", locationId);
		model.addAttribute("personInfo", personInformation);
		return "forms/reimbursement/ctgReimbursement/ctgapproval :: ctgapproval";

	}

}

