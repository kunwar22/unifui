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

import in.co.srdt.unif.enums.Yes;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.reimbcda.CdaVehicle;
import in.co.srdt.unif.model.reimbcda.CdaVehicleSearch;
import in.co.srdt.unif.model.rembchild.ApprovalChildModel;
import in.co.srdt.unif.model.search.LocationSearchResult;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/cdaVehicle")
public class CdaVehicleController {

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

	CdaVehicleController() {

	}

	CdaVehicleController(HttpHeaders headers, RestTemplate restTemplate) {
		this.headers = headers;
		this.restTemplate = restTemplate;
	}

	@RequestMapping("/manageCdaTelephone")
	public String manageCdaTelephone(Model model, HttpServletRequest req) {

		CdaVehicle cdaVehicle = new CdaVehicle();
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

		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			comdes = response.getBody();
		}
		model.addAttribute("propic",comdes);

		model.addAttribute("cdaVehicle", cdaVehicle);
		model.addAttribute("personInfo", personInformation);
		return "forms/reimbursement/cdaAndVehical/cdaSearch :: cdaSearch";
	}

	@RequestMapping("/CreateCda")
	public String createcdaremb(@ModelAttribute("cdaVehicle") CdaVehicle cdaVehicle, Model model,
			HttpServletRequest req) {
		Login login = (Login) req.getSession().getAttribute("login");
		String RID = "7";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, login.getEmplid());
		model.addAttribute("empent", ceiling);
		CdaVehicle cdaVehicle2 = new CdaVehicle();
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
		ResponseEntity<LocationSearchResult[]> responseLoc = restTemplate.exchange(urlLocation, HttpMethod.POST,
				request, LocationSearchResult[].class);

		if (responseLoc.getStatusCode() == HttpStatus.OK) {
			locationId = responseLoc.getBody();
		} else {
			System.out.println("Request Failed of Location");
			System.out.println(responseLoc.getStatusCode());
		}
		CommonLOV[] vehicalused = null;
		String urlvehicalused = appgateway.getAppgateway() + "/General/loadVehicleUsedLOV";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestveh = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlvehicalused, HttpMethod.GET, requestveh,
				CommonLOV[].class);

		if (responseveh.getStatusCode() == HttpStatus.OK) {
			vehicalused = responseveh.getBody();
		}
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
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			comdes = response.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("result", "");
		model.addAttribute("Vehicalused", vehicalused);
		model.addAttribute("viewdata", cdaVehicle2);
		model.addAttribute("location", locationId);
		model.addAttribute("cdaVehicle", cdaVehicle);
		model.addAttribute("personInfo", personInformation);
		model.addAttribute("ApprovalHod", Yes.values());
		SingleResponseModel dates = null;
		String urldates = appgateway.getAppgatewaypyrl_sandhya() + "/api/cdaVehicleClaim/getMaxhodDate/"
				+ login.getEmplid();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestveh1 = new HttpEntity<String>(headers);
		ResponseEntity<SingleResponseModel> response1 = restTemplate.exchange(urldates, HttpMethod.GET, requestveh1,
				SingleResponseModel.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			dates = response1.getBody();
		}
		model.addAttribute("datess", dates.getStatus());
		model.addAttribute("fileatach", dates.getMessage());
		return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";
	}

	@RequestMapping(value = "/saveCdaVehicle", method = RequestMethod.POST)
	public String saveCdaVehicalRem(@ModelAttribute("cdaVehicle") CdaVehicle cdaVehicle,
			@RequestParam("file") MultipartFile[] files, String statusid, HttpServletRequest request, Model model,
			BindingResult bindingResult) {

		model.addAttribute("result", "");
		createcdaremb(cdaVehicle, model, request);
		Login login = (Login) request.getSession().getAttribute("login");
		String RID = "7";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, login.getEmplid());
		model.addAttribute("empent", ceiling);
		String location = cdaVehicle.getAttachment1();
		String filePath = new File("").getAbsolutePath() + File.separator + location;
		String storePath = location;
		validator.validate(cdaVehicle, bindingResult);
		model.addAttribute("bindingResult", bindingResult);
		model.addAttribute("ApprovalHod", Yes.values());
		if (bindingResult.hasErrors()) {
			return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";
		}

		if (statusid.equals("save")) {
			cdaVehicle.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			cdaVehicle.setStatus("Submitted");
		}

		// CREATE DIRECTORY IF NOT EXISTS
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		int i = 0;
		String f = "n";
		for (MultipartFile file : files) {
			i += 1;
			if (!file.isEmpty()) {
				f = "y";
				// WRITE FILES TO DIRECTORY AND ENTRY INTO THE DATABASE
				try {
					// CREATING UNIQUE FILENAME
					String empl = login.getEmplid();
					String extension = FilenameUtils.getExtension(file.getOriginalFilename());
					String generatedFileName = new SimpleDateFormat(
							"yyyyMMddhhmmssS" + "'" + empl + "_" + (i) + "." + extension + "'").format(new Date());
					File target = new File(filePath + File.separator + generatedFileName);
					int readByteCount = 0;
					byte[] buffer = new byte[4096];
					BufferedInputStream in = new BufferedInputStream(file.getInputStream());
					FileOutputStream out = new FileOutputStream(target);
					while ((readByteCount = in.read(buffer)) != -1) {
						out.write(buffer, 0, readByteCount);
					}
					storePath += "/" + generatedFileName;
					String payLoad = "";
					String url = "";
					if (cdaVehicle.getFilepres2().equals("Y")) {
						if (i == 2) {
							cdaVehicle.setAttachment1(storePath);
							cdaVehicle.setFilepath1(storePath);
							cdaVehicle.setFilename1(storePath);

						}
					} else {
						cdaVehicle.setAttachment(cdaVehicle.getAttachhidden1());
					}
					if (cdaVehicle.getFilepres1().equals("Y")) {
						if (i == 1) {
							cdaVehicle.setAttachment(storePath);
							cdaVehicle.setFilename(storePath);
							cdaVehicle.setFilepath(storePath);

						}
					} else {
						cdaVehicle.setAttachment1(cdaVehicle.getAttachhidden2());
					}
				} catch (IOException e) {
					e.printStackTrace();
					model.addAttribute("result", "IOEXCEPTION");
					return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					model.addAttribute("result", "ILLEGALARG");
					return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("result", "WRITE_ERROR");
					return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";
				}
				storePath = location;
			}
		}

		String urlCda = appgateway.getAppgatewaypyrl_sandhya() + "/api/cdaVehicleClaim/saveandcorrectCdaVehicleClaim";
		SingleResponseModel res = new SingleResponseModel();
		try {

			headers.setContentType(MediaType.APPLICATION_JSON);
			cdaVehicle.setReimbursename("CDA and Vehicle Allowance");
			if (f == "n") {
				if (!cdaVehicle.getFilepres2().equals("Y")) {
					cdaVehicle.setAttachment1(cdaVehicle.getAttachhidden2());
				}
				if (!cdaVehicle.getFilepres1().equals("Y")) {
					cdaVehicle.setAttachment(cdaVehicle.getAttachhidden1());
				}
			}
			HttpEntity<CdaVehicle> requestcda = new HttpEntity<CdaVehicle>(cdaVehicle, headers);
			ResponseEntity<SingleResponseModel> responsecda = restTemplate.exchange(urlCda, HttpMethod.POST, requestcda,
					SingleResponseModel.class);
			if (responsecda.getStatusCode() == HttpStatus.OK) {
				res = responsecda.getBody();

			} else {
				System.out.println("Request Failed");
				System.out.println(responsecda.getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage("Failed to create Cda Vehical.");
		}
		System.out.println("data : " + cdaVehicle.toString());
		model.addAttribute("result", res.getStatus());
		return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";
	}

	@ResponseBody

	@RequestMapping(value = "getCdasearchdataList")
	public CdaVehicleSearch[] cdaVehicleSearchList(HttpServletRequest request, Model model) {
		Login login = (Login) request.getSession().getAttribute("login");
		CdaVehicleSearch[] cdaVehicleSearchs = null;
		String urlCda = appgateway.getAppgatewaypyrl_sandhya()
				+ "/api/cdaVehicleClaim/getListCdaVehicleClaimbypersonnumber/" + login.getLoginid();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request1 = new HttpEntity<String>(headers);
		ResponseEntity<CdaVehicleSearch[]> response1 = restTemplate.exchange(urlCda, HttpMethod.GET, request1,
				CdaVehicleSearch[].class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			cdaVehicleSearchs = response1.getBody();

		}
		return cdaVehicleSearchs;
	}

	@RequestMapping("/edit/editCda/correctcda/{cdavehicleid}/{mode}")
	public String editcdavehicle(@PathVariable("cdavehicleid") String cdavehicleid, Model model,
			@PathVariable("mode") String mode, HttpServletRequest req) throws URISyntaxException {
		model.addAttribute("result", "");
		String SearchdatasetId = cdavehicleid;
		PersonInformation personInformation = new PersonInformation();
		CdaVehicle cdaVehicle = new CdaVehicle();
		String urrlCdaVehicle = appgateway.getAppgatewaypyrl_sandhya()
				+ "/api/cdaVehicleClaim/getListbyCdaVehicleClaimId/" + SearchdatasetId;
		URI urlCDA = new URI(urrlCdaVehicle);
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> getCdaVehicleRequest = new HttpEntity<String>(headerss);
		ResponseEntity<CdaVehicle> getCdaVehicleResponse = restTemplate.exchange(urlCDA, HttpMethod.GET,
				getCdaVehicleRequest, CdaVehicle.class);
		if (getCdaVehicleResponse.getStatusCode() == HttpStatus.OK) {
			cdaVehicle = getCdaVehicleResponse.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(getCdaVehicleResponse.getStatusCode());
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
		if (cdaVehicle.getPeriodfrom() != null) {
			String effdtstart = cdaVehicle.getPeriodfrom();
			effdtstart = effdtstart.substring(0, 10);
			cdaVehicle.setPeriodfrom(effdtstart);
		}
		if (cdaVehicle.getPeriodto() != null) {
			String effdtstart = cdaVehicle.getPeriodto();
			effdtstart = effdtstart.substring(0, 10);
			cdaVehicle.setPeriodto(effdtstart);
		}
		if (cdaVehicle.getCreateddate() != null) {
			String effdtstart = cdaVehicle.getCreateddate();
			effdtstart = effdtstart.substring(0, 10);
			cdaVehicle.setCreateddate(effdtstart);
		}
		CommonLOV[] vehicalused = null;
		String urlvehicalused = appgateway.getAppgateway() + "/General/loadVehicleUsedLOV";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestveh = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlvehicalused, HttpMethod.GET, requestveh,
				CommonLOV[].class);
		if (responseveh.getStatusCode() == HttpStatus.OK) {
			vehicalused = responseveh.getBody();
		}
		Login login = (Login) req.getSession().getAttribute("login");
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
		String RID = "7";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, login.getEmplid());
		model.addAttribute("empent", ceiling);

		//// Approval--START****************\\\\\\\\\\\\\\\\\\\\\\\\\\

		ApprovalChildModel[] apr = null;
		String aprroveurl = appgateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID
				+ "/" + cdavehicleid + "/" + login.getEmplid();
		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr,
				ApprovalChildModel[].class);
		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response2 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			comdes = response2.getBody();
		}
		model.addAttribute("propic",comdes);

		model.addAttribute("approverdata", apr);
         //approval end
		model.addAttribute("Vehicalused", vehicalused);
		model.addAttribute("mode", mode);
		model.addAttribute("location", locationId);
		model.addAttribute("cdaVehicle", cdaVehicle);
		model.addAttribute("personInfo", personInformation);
		model.addAttribute("ApprovalHod", Yes.values());
		SingleResponseModel dates = null;
		String urldates = appgateway.getAppgatewaypyrl_sandhya() + "/api/cdaVehicleClaim/getMaxhodDate/"
				+ login.getEmplid();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestveh1 = new HttpEntity<String>(headers);
		ResponseEntity<SingleResponseModel> response1 = restTemplate.exchange(urldates, HttpMethod.GET, requestveh1,
				SingleResponseModel.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			dates = response1.getBody();

		}
		model.addAttribute("datess", dates.getStatus());
		String attchmentEnable="";
		model.addAttribute("atchmentEnable", cdaVehicle.getStatus());
		return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";

	}

	@RequestMapping(value = "/updateCdaVehicle", method = RequestMethod.POST)
	public String updateCdaVehicalRem(@ModelAttribute("cdaVehicle") CdaVehicle cdaVehicle,
			@RequestParam("file") MultipartFile[] files, String statusid, HttpServletRequest request, Model model,
			BindingResult bindingResult) throws IOException, MessagingException {

		Login login = (Login) request.getSession().getAttribute("login");
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

		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			comdes = response.getBody();
		}
		model.addAttribute("propic",comdes);

		model.addAttribute("personInfo", personInformation);

		String RID = "7";
		String ceiling = eligibilityreimb.getCeilinglimit(RID, login.getEmplid());
		model.addAttribute("empent", ceiling);
		String location = cdaVehicle.getAttachment1();
		String filePath = new File("").getAbsolutePath() + File.separator + location;
		String storePath = location;
		validator.validate(cdaVehicle, bindingResult);
		model.addAttribute("bindingResult", bindingResult);
		if (statusid.equals("save")) {
			cdaVehicle.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			cdaVehicle.setStatus("Submitted");
		}
		model.addAttribute("result", "");
		model.addAttribute("mode", "edit");
		model.addAttribute("ApprovalHod", Yes.values());
		System.out.print("out...."+cdaVehicle.toString());
		
		
		if (bindingResult.hasErrors()) {
			System.out.print("in...."+cdaVehicle.toString());
			return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";
		}
		// CREATE DIRECTORY IF NOT EXISTS
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		int i = 0;
		String f = "n";
		for (MultipartFile file : files) {
			i += 1;
			if (!file.isEmpty()) {
				f = "y";
				// WRITE FILES TO DIRECTORY AND ENTRY INTO THE DATABASE
				try {
					// CREATING UNIQUE FILENAME
					String empl = login.getEmplid();
					String extension = FilenameUtils.getExtension(file.getOriginalFilename());
					String generatedFileName = new SimpleDateFormat(
							"yyyyMMddhhmmssS" + "'" + empl + "_" + (i) + "." + extension + "'").format(new Date());

					File target = new File(filePath + File.separator + generatedFileName);
					int readByteCount = 0;
					byte[] buffer = new byte[4096];
					BufferedInputStream in = new BufferedInputStream(file.getInputStream());
					FileOutputStream out = new FileOutputStream(target);
					while ((readByteCount = in.read(buffer)) != -1) {
						out.write(buffer, 0, readByteCount);
					}
					storePath += "/" + generatedFileName;					
					String payLoad = "";
					if (cdaVehicle.getFilepres2().equals("Y")) {
						if (i == 2) {
							cdaVehicle.setAttachment1(storePath);
							cdaVehicle.setFilepath1(storePath);// txt
							cdaVehicle.setFilename1(storePath);
						}
					} else {
						cdaVehicle.setAttachment(cdaVehicle.getAttachhidden1());
					}
					if (cdaVehicle.getFilepres1().equals("Y")) {
						if (i == 1) {
							cdaVehicle.setAttachment(storePath);
							cdaVehicle.setFilename(storePath);// jpg
							cdaVehicle.setFilepath(storePath);

						}
					} else {
						cdaVehicle.setAttachment1(cdaVehicle.getAttachhidden2());
					}
				} catch (IOException e) {
					e.printStackTrace();
					model.addAttribute("result", "IOEXCEPTION");
					return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					model.addAttribute("result", "ILLEGALARG");
					return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("result", "WRITE_ERROR");
					return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";
				}
				storePath = location;
			}
		}

		String urlCda = appgateway.getAppgatewaypyrl_sandhya() + "/api/cdaVehicleClaim/saveandcorrectCdaVehicleClaim";
		SingleResponseModel res = new SingleResponseModel();
		try {

			headers.setContentType(MediaType.APPLICATION_JSON);
			cdaVehicle.setReimbursename("CDA and Vehicle Allowance");
			if (f == "n") {
				if (!cdaVehicle.getFilepres2().equals("Y")) {
					cdaVehicle.setAttachment1(cdaVehicle.getAttachhidden2());
				}
				if (!cdaVehicle.getFilepres1().equals("Y")) {
					cdaVehicle.setAttachment(cdaVehicle.getAttachhidden1());
				}
			}
			HttpEntity<CdaVehicle> requestcda = new HttpEntity<CdaVehicle>(cdaVehicle, headers);
			ResponseEntity<SingleResponseModel> responsecda = restTemplate.exchange(urlCda, HttpMethod.POST, requestcda,
					SingleResponseModel.class);
			if (responsecda.getStatusCode() == HttpStatus.OK) {
				res = responsecda.getBody();

			} else {
				System.out.println("Request Failed");
				System.out.println(responsecda.getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage("Failed to create Cda Vehical.");
		}
		model.addAttribute("result", res.getStatus());
		if(res.getStatus().equals("Success") && statusid.equals("submit")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = appgateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+cdaVehicle.getClaimid()+"/"+login.getEmplid();
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalPersonInformation.getPersonname(),login.getEmplid(),"CDA Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

		return "forms/reimbursement/cdaAndVehical/createCdaAndVehical :: createCdaRemb";
	}

	@RequestMapping("/viewcdaapproval/{cdavehicleid}/{mode}")
	public String viewcdavehicleapproval(@PathVariable("cdavehicleid") String cdavehicleid, Model model,
			@PathVariable("mode") String mode, HttpServletRequest req) throws URISyntaxException {
		ApprovalChildModel[] apr = null;
		String SearchdatasetId = cdavehicleid;
		PersonInformation personInformation = new PersonInformation();
		CdaVehicle cdaVehicle = new CdaVehicle();
		String urrlCdaVehicle = appgateway.getAppgatewaypyrl_sandhya()
				+ "/api/cdaVehicleClaim/getListbyCdaVehicleClaimId/" + SearchdatasetId;
		URI urlCDA = new URI(urrlCdaVehicle);
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> getCdaVehicleRequest = new HttpEntity<String>(headerss);
		ResponseEntity<CdaVehicle> getCdaVehicleResponse = restTemplate.exchange(urlCDA, HttpMethod.GET,
				getCdaVehicleRequest, CdaVehicle.class);
		if (getCdaVehicleResponse.getStatusCode() == HttpStatus.OK) {
			cdaVehicle = getCdaVehicleResponse.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(getCdaVehicleResponse.getStatusCode());
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
		if (cdaVehicle.getPeriodfrom() != null) {
			String effdtstart = cdaVehicle.getPeriodfrom();
			effdtstart = effdtstart.substring(0, 10);
			cdaVehicle.setPeriodfrom(effdtstart);
		}
		if (cdaVehicle.getPeriodto() != null) {
			String effdtstart = cdaVehicle.getPeriodto();
			effdtstart = effdtstart.substring(0, 10);
			cdaVehicle.setPeriodto(effdtstart);
		}
		if (cdaVehicle.getCreateddate() != null) {
			String effdtstart = cdaVehicle.getCreateddate();
			effdtstart = effdtstart.substring(0, 10);
			cdaVehicle.setCreateddate(effdtstart);
		}

		CommonLOV[] vehicalused = null;
		String urlvehicalused = appgateway.getAppgateway() + "/General/loadVehicleUsedLOV";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestveh = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlvehicalused, HttpMethod.GET, requestveh,
				CommonLOV[].class);
		if (responseveh.getStatusCode() == HttpStatus.OK) {
			vehicalused = responseveh.getBody();
		}
		Login login = (Login) req.getSession().getAttribute("login");
		String RID = "7";
		String urlNotify = appgateway.getAppgateway() + "/Notification/getUnreadApprovalData/" + RID + "/"
				+ cdavehicleid + "/" + login.getEmplid();
		NotificationModel notify = null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);

		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET,
				request1Noti, NotificationModel.class);
		if (responseNotify.getStatusCode() == HttpStatus.OK) {
			notify = responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		model.addAttribute("APPRAMT", notify.getApprovedamount());
		String ceiling = eligibilityreimb.getCeilinglimit(RID, notify.getSubmittedbypersonno());
		model.addAttribute("empent", ceiling);
		String aprroveurl = appgateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID
				+ "/" + cdavehicleid + "/" + notify.getSubmittedbypersonno();
		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr,
				ApprovalChildModel[].class);
		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		model.addAttribute("Vehicalused", vehicalused);
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
		ResponseEntity<CommonDescription> response1 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			comdes = response1.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("mode", mode);
		model.addAttribute("location", locationId);
		model.addAttribute("cdaVehicle", cdaVehicle);
		model.addAttribute("personInfo", personInformation);
		model.addAttribute("approverdata", apr);
		model.addAttribute("ApprovalHod", Yes.values());
		return "forms/reimbursement/cdaAndVehical/createCdaAndVehicalApproval :: createCdaRembApproval";

	}

	@RequestMapping(value = "/CDAApprovalUpdate/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel cdaApprovalUpdate(@PathVariable("_status") String _status, String msg,
			String approvedamt, double cdaclaimamountV, long claimid, HttpServletRequest reques, Model model) throws IOException, MessagingException {
		SingleResponseModel res = new SingleResponseModel();
		Login login = (Login) reques.getSession().getAttribute("login");
		headers.setContentType(MediaType.APPLICATION_JSON);
		NotificationModel notify = null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String RID = "7";
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
		notify.setApprovedamount(Double.parseDouble(approvedamt));
		String url = appgateway.getAppgateway() + "/Notification/submittedAprovelById";
		HttpEntity<NotificationModel> request = new HttpEntity<NotificationModel>(notify, headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request,
				SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"CDA Reimbursement");
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
