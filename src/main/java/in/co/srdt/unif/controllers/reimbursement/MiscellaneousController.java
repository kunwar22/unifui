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

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.rembchild.ApprovalChildModel;
import in.co.srdt.unif.model.rembmiscellaneous.AccountExpence;
import in.co.srdt.unif.model.rembmiscellaneous.MiscelleneousReimburse;
import in.co.srdt.unif.model.search.LocationSearchResult;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/miscelleneous")
public class MiscellaneousController {

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


	MiscellaneousController(){

	}

	MiscellaneousController(HttpHeaders headers,RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}


	@RequestMapping("/manageMiscelleneous")
	public String manageCdaTelephone(Model model, HttpServletRequest req) {

		//System.out.println("Manage miscelleneous::::::::::::::::::::");

		MiscelleneousReimburse miscelleneousReimburse  = new MiscelleneousReimburse();

		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation personInformation =null;
		String url = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
		//System.out.println("URL: "+url);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url,HttpMethod.GET, requestPesro,PersonInformation.class);

		if(responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}

		model.addAttribute("miscRemb",miscelleneousReimburse);
		model.addAttribute("personInfo",personInformation);

		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		return "forms/reimbursement/miscellaneousReimbursement/miscellaneousSearch :: SearchMiscellaneous";
	}



	@RequestMapping("/CreateMiscelleneous")
	public String createMiscelleneous(@ModelAttribute("MiscReimb") MiscelleneousReimburse miscelleneousReimburse,Model model, HttpServletRequest req) {
		//System.out.println("Miscelleneous Reimburse Create");

		MiscelleneousReimburse resp =new MiscelleneousReimburse();


		String searchName = "";
		String searchCode = "";
		String searchCountry = "";
		String searchStatus = "Active";

		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway()+"/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";

		//System.out.println("PAYLOAD :: "+payLode);
		//System.out.println("URL:::::"+urlLocation+"Payload:::"+payLode);
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<LocationSearchResult[]> response = restTemplate.exchange(urlLocation, HttpMethod.POST, request,
				LocationSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			locationId = response.getBody();
		} else {
			System.out.println("Request Failed of Location");
			System.out.println(response.getStatusCode());
		}


		CommonLOV[] om_project=null;

		String urlom_project= appgateway.getAppgateway()+"/General/loadProjectOM";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestveh = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseom = restTemplate.exchange(urlom_project, HttpMethod.GET, requestveh, CommonLOV[].class);


		if(responseom.getStatusCode() == HttpStatus.OK) {
			om_project = responseom.getBody();

		}


		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation personInformation =null;
		String url = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
		//System.out.println("URL: "+url);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url,HttpMethod.GET, requestPesro,PersonInformation.class);

		if(responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}


		AccountExpence[] expencecode =null;
		String urlexpence = appgateway.getAppgatewaypyrl_sandhya()+"/api/accountexpensecode/getaccountexpense";
		//System.out.println("URL: "+url);

		//headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestexpence = new HttpEntity<String>(headers);
		ResponseEntity<AccountExpence[]> responseexpence = restTemplate.exchange(urlexpence,HttpMethod.GET, requestexpence,AccountExpence[].class);

		if(responseexpence.getStatusCode() == HttpStatus.OK) {
			expencecode = responseexpence.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseexpence.getStatusCode());
		}


		CommonLOV[] vehicalused=null;

		String urlvehicalused= appgateway.getAppgateway()+"/General/loadVehicleUsedLOV";
		//headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestvehical = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlvehicalused, HttpMethod.GET, requestvehical, CommonLOV[].class);


		if(responseveh.getStatusCode() == HttpStatus.OK) {
			vehicalused = responseveh.getBody();

		}

		String RID="13";
		//String ceiling=eligibilityreimb.getCeilinglimit(RID,login.getEmplid());
		//System.out.println("Ceiling CTG::"+ceiling);
		//model.addAttribute("empent",ceiling);

		model.addAttribute("Vehicalused",vehicalused);
		model.addAttribute("om_project",om_project);
		model.addAttribute("expencecode",expencecode);

		model.addAttribute("viewdata",resp);
		model.addAttribute("location", locationId);
		model.addAttribute("MiscReimb", miscelleneousReimburse);
		model.addAttribute("personInfo",personInformation);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);

		return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";
	}



	@RequestMapping(value = "/miscellaneousSave", method = RequestMethod.POST)
	public String miscellaneousReimbursementSubmit(MiscelleneousReimburse miscelleneousReimburse, @RequestParam("file") MultipartFile file, String statusid, HttpServletRequest req, Model model, BindingResult bindingResult) {

		Login login = (Login) req.getSession().getAttribute("login");
		/*********LOV Binding Start*************/
		String searchName = "";
		String searchCode = "";
		String searchCountry = "";
		String searchStatus = "Active";

		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway()+"/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";

		//System.out.println("PAYLOAD :: "+payLode);
		//System.out.println("URL:::::"+urlLocation+"Payload:::"+payLode);
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request1 = new HttpEntity<String>(payLode, headers);

		ResponseEntity<LocationSearchResult[]> response = restTemplate.exchange(urlLocation, HttpMethod.POST, request1,
				LocationSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			locationId = response.getBody();
		} else {
			System.out.println("Request Failed of Location");
			System.out.println(response.getStatusCode());
		}


		CommonLOV[] om_project=null;

		String urlom_project= appgateway.getAppgateway()+"/General/loadProjectOM";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestveh = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseom = restTemplate.exchange(urlom_project, HttpMethod.GET, requestveh, CommonLOV[].class);


		if(responseom.getStatusCode() == HttpStatus.OK) {
			om_project = responseom.getBody();

		}



		PersonInformation personInformation =null;
		String url = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
		//System.out.println("URL: "+url);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url,HttpMethod.GET, requestPesro,PersonInformation.class);

		if(responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}

		AccountExpence[] expencecode =null;
		String urlexpence = appgateway.getAppgatewaypyrl_sandhya()+"/api/accountexpensecode/getaccountexpense";
		//System.out.println("URL: "+url);

		//headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestexpence = new HttpEntity<String>(headers);
		ResponseEntity<AccountExpence[]> responseexpence = restTemplate.exchange(urlexpence,HttpMethod.GET, requestexpence,AccountExpence[].class);

		if(responseexpence.getStatusCode() == HttpStatus.OK) {
			expencecode = responseexpence.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseexpence.getStatusCode());
		}


		CommonLOV[] vehicalused=null;

		String urlvehicalused= appgateway.getAppgateway()+"/General/loadVehicleUsedLOV";
		//headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestvehical = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlvehicalused, HttpMethod.GET, requestvehical, CommonLOV[].class);


		if(responseveh.getStatusCode() == HttpStatus.OK) {
			vehicalused = responseveh.getBody();

		}



		model.addAttribute("Vehicalused",vehicalused);
		model.addAttribute("om_project",om_project);
		model.addAttribute("expencecode",expencecode);
		model.addAttribute("location", locationId);

		model.addAttribute("personInfo",personInformation);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		/*********LOV Binding End*************/

		/*************************Changed By @Suraj START****************************/

		/*************************VALIDATION PART START****************************/
		//model.addAttribute("result",new String());
		//System.out.println("Inside Telephone Save Controller");

		String RID="13";
		//String ceiling=eligibilityreimb.getCeilinglimit(RID,login.getEmplid());
		//System.out.println("Ceiling CTG::"+ceiling);
		//model.addAttribute("empent",ceiling);

		PersonInformation resp = null;

		String url1 = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
		//System.out.println("URL: " + statusid);


		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<PersonInformation> response2 = restTemplate.exchange(url1, HttpMethod.GET, request, PersonInformation.class);
		resp = response2.getBody();

		model.addAttribute("hdata", resp);

		validator.validate(miscelleneousReimburse, bindingResult);

		model.addAttribute("bindingResult", bindingResult);

		model.addAttribute("MiscReimb", miscelleneousReimburse);

		if (bindingResult.hasErrors()) {
			model.addAttribute("result","Validation Failed");
			return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";
		}
		/*************************VALIDATION PART END****************************/


		/*************************FILE ATTACHMENT PART START ONLY EXECUTE WHEN FILE SELECTED****************************/
		if(!file.isEmpty()) {

			String location = miscelleneousReimburse.getAttachment();
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
				miscelleneousReimburse.setAttachment(storePath); ////Model should be yours

			}
			catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("result", "IOEXCEPTION");
				return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
				model.addAttribute("result", "ILLEGALARG");
				return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";
			}
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("result", "WRITE_ERROR");
				return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";
			}
		}else {
			miscelleneousReimburse.setAttachment(null);
		}
		/*************************FILE ATTACHMENT PART END****************************/


		/***********************DATABASE TRANSACTIONS START FOR DATA SAVE or SUBMIT**********************/
		if (statusid.equals("save")) {
			miscelleneousReimburse.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			miscelleneousReimburse.setStatus("submitted");
		}

		//System.out.println("STATUS_ID :::: " + statusid.toString());
		
		String urlsave=appgateway.getAppgatewaypyrl_sandhya()+"/api/miscelleneousrmbrs/saveandcorrectMiscelleneousReimbursement";

		//System.out.println("URLSAVE :: "+urlsave);



		String res="";
		HttpEntity<MiscelleneousReimburse> request2 = new HttpEntity<MiscelleneousReimburse>(miscelleneousReimburse, headers);
		ResponseEntity<String> response1 = restTemplate.exchange(urlsave, HttpMethod.POST, request2, String.class);

		if (response1.getStatusCode() == HttpStatus.OK) {
			res = response1.getBody();
			//System.out.println("DATA TO BE SAVED :::: " + miscelleneousReimburse.toString());
		} else {
			System.out.println(response.getStatusCode());
			model.addAttribute("result", "LOG_ERROR");
			return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";
		}

		model.addAttribute("result", "Success");
		return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";

		/***********************DATABASE TRANSACTIONS END FOR DATA SAVE or SUBMIT**********************/



	}




	@ResponseBody
	@RequestMapping(value = "/getMissearchdata", method = RequestMethod.GET)
	public MiscelleneousReimburse[] miscelleneousReimburses(HttpServletRequest request,Model model)
	{
		//System.out.println("In getMissearchdata Search");

		MiscelleneousReimburse[] miscelleneousReimburses = null;
		Login login = (Login) request.getSession().getAttribute("login");



		String urlMis = appgateway.getAppgatewaypyrl_sandhya()+"/api/miscelleneousrmbrs/getMiscelleneousrmbrsbyPersonnumber/"+login.getLoginid();

		//System.out.println("URL: "+urlMis);


		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request1 = new HttpEntity<String>(headers);

		ResponseEntity<MiscelleneousReimburse[]> response1 = restTemplate.exchange(urlMis, HttpMethod.GET, request1,
				MiscelleneousReimburse[].class);

		if (response1.getStatusCode() == HttpStatus.OK) {
			miscelleneousReimburses = response1.getBody();

		}

		return miscelleneousReimburses;
	}



	@RequestMapping("/edit/editMis/correctMis/{misrmbid}/{mode}")
	public String editmisrmbrsid(@PathVariable("misrmbid") String misrmbrsid, Model model,@PathVariable("mode")String mode,HttpServletRequest req)
			throws URISyntaxException {

		//System.out.println("MIS ID Edit: "+misrmbrsid+" Mode: "+mode);

		String Searchmisrmbrsid = misrmbrsid;
		//	PersonInformation personInformation = new PersonInformation();

		MiscelleneousReimburse miscelleneousReimburse =new MiscelleneousReimburse();
		//System.out.println("miscelleneousReimburse:++++++++++++++++++++++++++++++++ "+miscelleneousReimburse.toString());

		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation personInformation =null;
		String url = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
		//System.out.println("URL: "+url);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url,HttpMethod.GET, requestPesro,PersonInformation.class);

		if(responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}
		model.addAttribute("personInfo",personInformation);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("MiscReimb",miscelleneousReimburse);




		String urrlMISRemb= appgateway.getAppgatewaypyrl_sandhya()+"/api/miscelleneousrmbrs/getMiscelleneousrmbrsbyId/" + Searchmisrmbrsid;
		//String urrlCGIRemb= "http://192.200.12.83:9120/api/ctgreimburseClaim/getListbyCtgReimburseClaimId/" + Searchctgrmbrsid;

		//System.out.println("url::"+urrlMISRemb);
		URI urlGI = new URI(urrlMISRemb);
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);


		HttpEntity<String> getMISRembRequest = new HttpEntity<String>(headerss);

		ResponseEntity<MiscelleneousReimburse> getMISRembResponse = restTemplate.exchange(urrlMISRemb, HttpMethod.GET,
				getMISRembRequest, MiscelleneousReimburse.class);

		if (getMISRembResponse.getStatusCode() == HttpStatus.OK) {
			miscelleneousReimburse = getMISRembResponse.getBody();

		}
		else {
			System.out.println("Request Failed");
			System.out.println(getMISRembResponse.getStatusCode());
		}



		String searchName = "";
		String searchCode = "";
		String searchCountry = "";
		String searchStatus = "";

		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway()+"/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";

		//System.out.println("PAYLOAD :: "+payLode);

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


		if(miscelleneousReimburse.getBilldate()!=null) {
			String effdtstart=miscelleneousReimburse.getBilldate();
			effdtstart=effdtstart.substring(0,10);
			miscelleneousReimburse.setBilldate(effdtstart);
		}


		CommonLOV[] om_project=null;
		String urlom_project = appgateway.getAppgateway()+"/General/loadProjectOM";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestom = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> responseom = restTemplate.exchange(urlom_project, HttpMethod.GET, requestom, CommonLOV[].class);
		if(responseom.getStatusCode() == HttpStatus.OK) {
			om_project = responseom.getBody();

		}

		String RID="13";
		//String ceiling=eligibilityreimb.getCeilinglimit(RID,login.getEmplid());
		//System.out.println("Ceiling CTG::"+ceiling);
		//model.addAttribute("empent",ceiling);




		//// *****************Updated by Ravi for view mode
		//// Approval--START****************\\\\\\\\\\\\\\\\\\\\\\\\\\
		ApprovalChildModel[] apr = null;

		String aprroveurl = appgateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + misrmbrsid + "/" + login.getEmplid();
        // System.out.println("print aprroveurl box endpont::"+aprroveurl);
		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		//System.out.println("SV____" + apr.length + " " + RID + "/" + misrmbrsid + "/" + login.getEmplid());
		model.addAttribute("approverdata", apr);

		//// *****************Updated by
		//// Ravi--END****************\\\\\\\\\\\\\\\\\\\\\\\\\\


		AccountExpence[] expencecode =null;
		String urlexpence = appgateway.getAppgatewaypyrl_sandhya()+"/api/accountexpensecode/getaccountexpense";
		//System.out.println("URL: "+url);

		//headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestexpence = new HttpEntity<String>(headers);
		ResponseEntity<AccountExpence[]> responseexpence = restTemplate.exchange(urlexpence,HttpMethod.GET, requestexpence,AccountExpence[].class);

		if(responseexpence.getStatusCode() == HttpStatus.OK) {
			expencecode = responseexpence.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseexpence.getStatusCode());
		}
		model.addAttribute("expencecode",expencecode);

		CommonLOV[] vehicalused=null;

		String urlvehicalused= appgateway.getAppgateway()+"/General/loadVehicleUsedLOV";
		//headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestvehical = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlvehicalused, HttpMethod.GET, requestvehical, CommonLOV[].class);


		if(responseveh.getStatusCode() == HttpStatus.OK) {
			vehicalused = responseveh.getBody();

		}

		//System.out.println("Dataaa "+ miscelleneousReimburse.toString());

		model.addAttribute("Vehicalused",vehicalused);
		model.addAttribute("om_project",om_project);
		//model.addAttribute("expencecode",expencecode);
		model.addAttribute("om_project",om_project);
		//model.addAttribute("viewdata",resp);
		model.addAttribute("location", locationId);
		model.addAttribute("MiscReimb", miscelleneousReimburse);
		model.addAttribute("personInfo",personInformation);

		return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";

	}





	@RequestMapping(value = "/miscellaneousUpdate", method = RequestMethod.POST)
	public String miscellaneousReimbursementUpdate(@ModelAttribute MiscelleneousReimburse miscelleneousReimburse, @RequestParam("file") MultipartFile file, String statusid, HttpServletRequest req, Model model, BindingResult bindingResult) throws IOException, MessagingException {
		//System.out.println("in Update");


		String searchName = "";
		String searchCode = "";
		String searchCountry = "";
		String searchStatus = "Active";

		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway()+"/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";

		//System.out.println("PAYLOAD :: "+payLode);
		//System.out.println("URL:::::"+urlLocation+"Payload:::"+payLode);
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

		ResponseEntity<LocationSearchResult[]> response = restTemplate.exchange(urlLocation, HttpMethod.POST, request,
				LocationSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			locationId = response.getBody();
		} else {
			System.out.println("Request Failed of Location");
			System.out.println(response.getStatusCode());
		}


		CommonLOV[] om_project=null;

		String urlom_project= appgateway.getAppgateway()+"/General/loadProjectOM";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestveh = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseom = restTemplate.exchange(urlom_project, HttpMethod.GET, requestveh, CommonLOV[].class);


		if(responseom.getStatusCode() == HttpStatus.OK) {
			om_project = responseom.getBody();

		}


		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation personInformation =null;
		String url = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
		//System.out.println("URL: "+url);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url,HttpMethod.GET, requestPesro,PersonInformation.class);

		if(responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}



		AccountExpence[] expencecode =null;
		String urlexpence = appgateway.getAppgatewaypyrl_sandhya()+"/api/accountexpensecode/getaccountexpense";
		//System.out.println("URL: "+url);

		//headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestexpence = new HttpEntity<String>(headers);
		ResponseEntity<AccountExpence[]> responseexpence = restTemplate.exchange(urlexpence,HttpMethod.GET, requestexpence,AccountExpence[].class);

		if(responseexpence.getStatusCode() == HttpStatus.OK) {
			expencecode = responseexpence.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseexpence.getStatusCode());
		}


		CommonLOV[] vehicalused=null;

		String urlvehicalused= appgateway.getAppgateway()+"/General/loadVehicleUsedLOV";
		//headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestvehical = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlvehicalused, HttpMethod.GET, requestvehical, CommonLOV[].class);


		if(responseveh.getStatusCode() == HttpStatus.OK) {
			vehicalused = responseveh.getBody();

		}


		String mode="edit";
		

		String RID="13";
		///String ceiling=eligibilityreimb.getCeilinglimit(RID,login.getEmplid());
		//System.out.println("Ceiling CTG::"+ceiling);
		///model.addAttribute("empent",ceiling);




		model.addAttribute("Vehicalused",vehicalused);
		model.addAttribute("om_project",om_project);
		model.addAttribute("expencecode",expencecode);
		model.addAttribute("om_project",om_project);
		//model.addAttribute("viewdata",resp);
		model.addAttribute("location", locationId);
		model.addAttribute("MiscReimb", miscelleneousReimburse);
		model.addAttribute("personInfo",personInformation);
		model.addAttribute("result","success");
		model.addAttribute("mode",mode);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		//System.out.println("Inside miscellaneous Save Controller");

		validator.validate(miscelleneousReimburse, bindingResult);

		model.addAttribute("bindingResult", bindingResult);

		//System.out.println("ALL ERRORS "+bindingResult.getAllErrors());

		if (bindingResult.hasErrors()) {
			return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";
		}

		/*************************FILE ATTACHMENT PART START ONLY EXECUTE WHEN FILE SELECTED****************************/
		if(!file.isEmpty()) {

			String location = miscelleneousReimburse.getAttachment();
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
				miscelleneousReimburse.setAttachment(storePath); ////Model should be yours

			}
			catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("result", "IOEXCEPTION");
				return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
				model.addAttribute("result", "ILLEGALARG");
				return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";
			}
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("result", "WRITE_ERROR");
				return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";
			}
		}else {

			miscelleneousReimburse.setAttachment(miscelleneousReimburse.getAttachhidden());
		}
		/*************************FILE ATTACHMENT PART END****************************/


		/***********************DATABASE TRANSACTIONS START FOR DATA SAVE or SUBMIT**********************/
		if (statusid.equals("save")) {
			miscelleneousReimburse.setStatus("Saved");
		} else if (statusid.equals("submit")) {
			miscelleneousReimburse.setStatus("Submitted");
		}

		String urlMis=appgateway.getAppgatewaypyrl_sandhya()+"/api/miscelleneousrmbrs/saveandcorrectMiscelleneousReimbursement";
		//System.out.println("s UPPPP::"+miscelleneousReimburse.getStatus());
		//System.out.println("URLL :"+urlMis);
		System.out.println("Stringgg :"+miscelleneousReimburse.toString());
		SingleResponseModel respo = new SingleResponseModel();
		try {

			headers.setContentType(MediaType.APPLICATION_JSON);
			// headers.setBearerAuth(AppConstants.ACCESS_TOKEN);

			HttpEntity<MiscelleneousReimburse> request1 = new HttpEntity<MiscelleneousReimburse>(miscelleneousReimburse, headers);

			ResponseEntity<SingleResponseModel> response1= restTemplate.exchange(urlMis,HttpMethod.POST,request1, SingleResponseModel.class);
			if(response1.getStatusCode() == HttpStatus.OK) {
				respo = response1.getBody();
			//	System.out.println("sucess ::"+ miscelleneousReimburse.toString());
			} else {
				System.out.println("Request Failed");
				System.out.println(response1.getStatusCode());
			}
		}
		catch (Exception e){
			e.printStackTrace();
			respo.setMessage("Failed to create miscelleneous Reimbursement.");
		}

		model.addAttribute("miscRemb",miscelleneousReimburse);
		model.addAttribute("personInfo",personInformation);
		model.addAttribute("result","Success");

		if(respo.getStatus().equals("Success") && statusid.equals("submit")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = appgateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+miscelleneousReimburse.getClaimid()+"/"+login.getEmplid();
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalPersonInformation.getPersonname(),login.getEmplid(),"Miscellaneous Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

		return "forms/reimbursement/miscellaneousReimbursement/miscellaneousCreate :: createMiscellaneous";

	}


	@RequestMapping("/viewapproval/{misrmbid}/{mode}/{status}")
	public String viewmisrmbrsid(@PathVariable("misrmbid") String misrmbrsid,@PathVariable("status") String status, Model model,@PathVariable("mode")String mode,HttpServletRequest req)
			throws URISyntaxException {

		String Searchmisrmbrsid = misrmbrsid;
		//	PersonInformation personInformation = new PersonInformation();
		Login login = (Login) req.getSession().getAttribute("login");

		String RID="13";


		MiscelleneousReimburse miscelleneousReimburse =new MiscelleneousReimburse();
		/*Added By Rajat for Get Requested headder and box Start*/

		String urlNotify = appgateway.getAppgateway()+"/Notification/getApprovalDataByPersonnoStatus/"+RID+"/" + misrmbrsid+"/"+login.getEmplid()+"/"+status;

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

		/*Added By Rajat for Get Requested headder and box Start*/


		PersonInformation personInformation =null;
		String url = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+notify.getSubmittedbypersonno();
		//System.out.println("URL: "+url);

		/*
		 * String
		 * ceiling=eligibilityreimb.getCeilinglimit(RID,notify.getSubmittedbypersonno())
		 * ; System.out.println("Ceiling CTG::"+ceiling);
		 * model.addAttribute("empent",ceiling);
		 */


		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestPesro = new HttpEntity<String>(headers);
		ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url,HttpMethod.GET, requestPesro,PersonInformation.class);

		if(responsePerso.getStatusCode() == HttpStatus.OK) {
			personInformation = responsePerso.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responsePerso.getStatusCode());
		}
		model.addAttribute("personInfo",personInformation);
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+notify.getSubmittedbypersonno()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("MiscReimb",miscelleneousReimburse);




		String urrlMISRemb= appgateway.getAppgatewaypyrl_sandhya()+"/api/miscelleneousrmbrs/getMiscelleneousrmbrsbyId/" + Searchmisrmbrsid;
		//String urrlCGIRemb= "http://192.200.12.83:9120/api/ctgreimburseClaim/getListbyCtgReimburseClaimId/" + Searchctgrmbrsid;

		//	System.out.println("url::"+urrlMISRemb);
		URI urlGI = new URI(urrlMISRemb);
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);


		HttpEntity<String> getMISRembRequest = new HttpEntity<String>(headerss);

		ResponseEntity<MiscelleneousReimburse> getMISRembResponse = restTemplate.exchange(urrlMISRemb, HttpMethod.GET,
				getMISRembRequest, MiscelleneousReimburse.class);

		if (getMISRembResponse.getStatusCode() == HttpStatus.OK) {
			miscelleneousReimburse = getMISRembResponse.getBody();

		}
		else {
			System.out.println("Request Failed");
			System.out.println(getMISRembResponse.getStatusCode());
		}



		String searchName = "";
		String searchCode = "";
		String searchCountry = "";
		String searchStatus = "";

		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway()+"/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";

		//System.out.println("PAYLOAD :: "+payLode);

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


		if(miscelleneousReimburse.getBilldate()!=null) {
			String effdtstart=miscelleneousReimburse.getBilldate();
			effdtstart=effdtstart.substring(0,10);
			miscelleneousReimburse.setBilldate(effdtstart);
		}


		CommonLOV[] om_project=null;
		String urlom_project = appgateway.getAppgateway()+"/General/loadProjectOM";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestom = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> responseom = restTemplate.exchange(urlom_project, HttpMethod.GET, requestom, CommonLOV[].class);
		if(responseom.getStatusCode() == HttpStatus.OK) {
			om_project = responseom.getBody();

		}





		////*****************Updated by Ravi for view mode Approval--START****************\\\\\\\\\\\\\\\\\\\\\\\\\\
		ApprovalChildModel[] apr = null;


		String aprroveurl =appgateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + misrmbrsid + "/" + notify.getSubmittedbypersonno();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		model.addAttribute("approverdata", apr);

		////*****************Updated by Ravi--END****************\\\\\\\\\\\\\\\\\\\\\\\\\\


		AccountExpence[] expencecode =null;
		String urlexpence = appgateway.getAppgatewaypyrl_sandhya()+"/api/accountexpensecode/getaccountexpense";
		//System.out.println("URL: "+url);

		//headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestexpence = new HttpEntity<String>(headers);
		ResponseEntity<AccountExpence[]> responseexpence = restTemplate.exchange(urlexpence,HttpMethod.GET, requestexpence,AccountExpence[].class);

		if(responseexpence.getStatusCode() == HttpStatus.OK) {
			expencecode = responseexpence.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseexpence.getStatusCode());
		}
		model.addAttribute("expencecode",expencecode);

		CommonLOV[] vehicalused=null;

		String urlvehicalused= appgateway.getAppgateway()+"/General/loadVehicleUsedLOV";
		//headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestvehical = new HttpEntity<String>(headers);

		ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlvehicalused, HttpMethod.GET, requestvehical, CommonLOV[].class);


		if(responseveh.getStatusCode() == HttpStatus.OK) {
			vehicalused = responseveh.getBody();

		}

		//System.out.println("Dataaa "+ miscelleneousReimburse.toString());

		model.addAttribute("Vehicalused",vehicalused);
		model.addAttribute("om_project",om_project);
		//model.addAttribute("expencecode",expencecode);

		//model.addAttribute("viewdata",resp);
		model.addAttribute("location", locationId);
		model.addAttribute("MiscReimb", miscelleneousReimburse);
		model.addAttribute("personInfo",personInformation);
		return "forms/reimbursement/miscellaneousReimbursement/miscellaneousApproval :: createMiscellaneousApproval";

	}






//	@RequestMapping(value = "/misApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//	@ResponseBody
//	public SingleResponseModel MisApprovalUpdate(@PathVariable("_status") String _status,String msg, double approvedamt, long claimid, HttpServletRequest reques,Model model) {
//		SingleResponseModel res = new SingleResponseModel();
//
//		Login login = (Login) reques.getSession().getAttribute("login");
//
//
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.add("Authorization", AppConstants.ACCESS_TOKEN);
//		NotificationModel notify=null;
//		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
//		String RID="13";
//		//NotificationModel approvalchange=null;
//		String urlNotify =appgateway.getAppgateway()+"/Notification/getUnreadApprovalData/"+RID+"/" + claimid+"/"+login.getEmplid();
//		System.out.println(urlNotify);
//		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET, request1Noti, NotificationModel.class);
//		if(responseNotify.getStatusCode() == HttpStatus.OK) {
//			notify=responseNotify.getBody();
//		} else {
//			System.out.println("Request Failed");
//			System.out.println(responseNotify.getStatusCode());
//		}
//		model.addAttribute("NotifynavBar", notify);
//
//
//		notify.setStatus(_status);
//		notify.setMessage(msg);
//		notify.setApprovedamount(approvedamt);
//		System.out.println(notify.toString());
//
//		String url =appgateway.getAppgateway() + "/Notification/submittedAprovelById";
//
//		HttpEntity<NotificationModel> request = new HttpEntity<NotificationModel>(notify,headers);
//		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, SingleResponseModel.class);
//		if(response.getStatusCode() == HttpStatus.OK) {
//			res=response.getBody();
//		} else {
//			System.out.println("Request Failed");
//			System.out.println(responseNotify.getStatusCode());
//		}
//
//
//		return res;
//	}

	@RequestMapping(value = "/misApproval/{_status}",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel MisApprovalUpdate(@PathVariable("_status") String _status,String msg, 
			double approvedamt, long claimid, HttpServletRequest reques, Model model) throws IOException, MessagingException {
		SingleResponseModel res = new SingleResponseModel();

		Login login = (Login) reques.getSession().getAttribute("login");

		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		NotificationModel notify = null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String RID = "13";
		// NotificationModel approvalchange=null;
		String urlNotify = appgateway.getAppgateway() + "/Notification/getUnreadApprovalData/" + RID + "/" + claimid
				+ "/" + login.getEmplid();
		// System.out.println(urlNotify);
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
		// System.out.println(notify.toString());

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
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"Miscellaneous Reimbursement");
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

	
	@RequestMapping(value="/claimform/{claimid}",method=RequestMethod.GET)
	public String getDeclaration(@PathVariable("claimid") long claimid,Model model,HttpServletRequest request)
	{
		Login userlogin = (Login) request.getSession().getAttribute("login");
		PersonInformation personInfo = new PersonInformation();

		/* Getting Employee Details from API */

		String urlEmployeeDetails = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
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
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo", personInfo);

		System.out.println("miscellaneous claimid ::::::::::: "+claimid);
		/**************************************
		 * Fetching Details of Employee Ends
		 *******************************************************/
/* LOCATION DATA FETCH STARTS */
		
		String searchName = "";
		String searchCode = "";
		String searchCountry = "";
		String searchStatus = "Active";
		
		LocationSearchResult[] locationId = null;

		String urlLocation = appgateway.getAppgateway()+"/Location/locationSearchList";

		String payLode = "{" + "\"name\"" + ":\"" + searchName + "\"," + "\"code\"" + ":\"" + searchCode + "\","
				+ "\"country\"" + ":\"" + searchCountry + "\"," + "\"status\"" + ":\"" + searchStatus + "\"" + "}";

		//System.out.println("PAYLOAD :: "+payLode);
		//System.out.println("URL:::::"+urlLocation+"Payload:::"+payLode);
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> locrequest = new HttpEntity<String>(payLode, headers);

		ResponseEntity<LocationSearchResult[]> response = restTemplate.exchange(urlLocation, HttpMethod.POST, locrequest,
				LocationSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			locationId = response.getBody();
		} else {
			System.out.println("Request Failed of Location");
			System.out.println(response.getStatusCode());
		}
		
		model.addAttribute("location", locationId);
		
		/* LOCATION DATA FETCH STARTS */
		MiscelleneousReimburse miscelleneousReimburse = null;

		String urrlMISRemb= appgateway.getAppgatewaypyrl_sandhya()+"/api/miscelleneousrmbrs/getMiscelleneousrmbrsbyId/" + claimid;
		//System.out.println("URL for electricity claim search :: " + urlelectricreimbursementSearch);
		HttpEntity<String> miscreimbursement_search_request = new HttpEntity<String>(headers);

		ResponseEntity<MiscelleneousReimburse> misc_response = restTemplate.exchange(urrlMISRemb,
				HttpMethod.GET, miscreimbursement_search_request, MiscelleneousReimburse.class);

		if (misc_response.getStatusCode() == HttpStatus.OK) {
			miscelleneousReimburse = misc_response.getBody();
			//System.out.println("Electricity Search Model : " + electricitySearchModel);
		}

//		AccountExpence[] expencecode =null;
//		String urlexpence = appgateway.getAppgatewaypyrl_sandhya()+"/api/accountexpensecode/getaccountexpense";
//		//System.out.println("URL: "+url);
//
//		//headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<String> requestexpence = new HttpEntity<String>(headers);
//		ResponseEntity<AccountExpence[]> responseexpence = restTemplate.exchange(urlexpence,HttpMethod.GET, requestexpence,AccountExpence[].class);
//
//		if(responseexpence.getStatusCode() == HttpStatus.OK) {
//			expencecode = responseexpence.getBody();
//		} else {
//			System.out.println("Request Failed");
//			System.out.println(responseexpence.getStatusCode());
//		}
//		
//		model.addAttribute("expencecode",expencecode);
		model.addAttribute("MiscReimb", miscelleneousReimburse);


		return "forms/reimbursement/miscellaneousReimbursement/miscellaneousClaimForm";
	}

}
