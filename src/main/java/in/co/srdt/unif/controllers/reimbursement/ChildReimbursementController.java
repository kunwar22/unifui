package in.co.srdt.unif.controllers.reimbursement;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import in.co.srdt.unif.model.rembchild.ChildModel;
import in.co.srdt.unif.model.rembchild.ChildReimburseModel;
import in.co.srdt.unif.model.rembchild.ChildSearchModel;
import in.co.srdt.unif.model.rembchild.Dependent_ChildModel;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/reimbursement")
public class ChildReimbursementController {
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

	Login userlogin = null;

	@Autowired(required = true)
	private SendMail sendnotimail;


	String location, filePath, storePath;
	/* Controller for Child Remb - Asmita_18-09 starts here */

	@RequestMapping("/child")
	public String childReimbursement(HttpSession session, HttpServletRequest request, Model model) {

		userlogin = (Login) request.getSession().getAttribute("login"); // fetching Person Number

		PersonInformation personInfo = new PersonInformation();

		/* Getting Employee Details from API */
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();
		// System.out.println("URL for getting employee details :: " +
		// urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		// Loading of Employee Details starts here

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();

			// System.out.println("Employee Personal Details ==>" + personInfo);
		}
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			comdes = response.getBody();
		}
		model.addAttribute("propic",comdes);

		model.addAttribute("personInfo", personInfo);

		return "forms/reimbursement/child/childSearch :: childSearch";
	}

	/* Use OF Gender LOV done here by Asmita -19-09 */
	@RequestMapping("/childclaim")
	public String createChildClaimReimbursement(Model model, HttpServletRequest req) {

		/* Employee Header starts */
		userlogin = (Login) req.getSession().getAttribute("login"); // fetching Persion Number

		PersonInformation personInfo = new PersonInformation();

		// Geting Employee Details from API
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();
		// System.out.println("URL for getting employee details :: " +
		// urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		// Loading of Employee Details starts here

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();

			// System.out.println("Employee Personal Details ==>" + personInfo);
		}
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			comdes = response.getBody();
		}
		model.addAttribute("propic",comdes);

		model.addAttribute("personInfo", personInfo);

		/* Employee Header ends */

		// System.out.println("==========>Creating Child Education Reimbursement Claim
		// Page<==========");

		ChildReimburseModel childreimbursemodel = new ChildReimburseModel();
		CommonLOV[] childClass = null;

		Dependent_ChildModel[] dependentChilds = null;

		String urlChildDependent = appGateway.getAppgateway()
				+ "/PersonManagement/DependentDetails/getDependentChildList/" + userlogin.getEmplid();
		String urlClassLOV = appGateway.getAppgateway() + "/General/loadReimbursementClass";

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		/* Loading of Child Class LOV starts here */
		ResponseEntity<CommonLOV[]> classLovResponse = restTemplate.exchange(urlClassLOV, HttpMethod.GET, request,
				CommonLOV[].class);

		if (classLovResponse.getStatusCode() == HttpStatus.OK) {
			childClass = classLovResponse.getBody();

		}

		// System.out.println("Child Class ==>" + childClass);

		/* Loading of Child Class LOV ends here */

		/* Loading of Dependent Child starts here */
		ResponseEntity<Dependent_ChildModel[]> dep_ChildModelResponse = restTemplate.exchange(urlChildDependent,
				HttpMethod.GET, request, Dependent_ChildModel[].class);

		if (dep_ChildModelResponse.getStatusCode() == HttpStatus.OK) {
			dependentChilds = dep_ChildModelResponse.getBody();
		}

		// System.out.println("Dependents Class ==>" + dependentChilds);

		/* Loading of Dependent Child ends here */

		model.addAttribute("childreimbursemodel", childreimbursemodel);
		model.addAttribute("childclass", childClass);
		model.addAttribute("dependentChilds", dependentChilds);

		return "forms/reimbursement/child/createChildClaim :: createChildClaim";
	}

	/* Updating Child Education Claim Reimbursement --Asmita 29-09 */

	@PostMapping(value = "/updateChildEducaticonReimbursement")
	public String updateChildEducationClaim(
			@ModelAttribute("childreimbursemodel") ChildReimburseModel childreimbursemodel,
			@RequestParam("file") MultipartFile[] files, String status_id, HttpServletRequest request, Model model,
			BindingResult bindingResult) throws IOException, MessagingException {

		String RID = "3";
		//String ceiling = eligibilityreimb.getCeilinglimit(RID, userlogin.getEmplid());
		//model.addAttribute("empent", ceiling);

		//System.out.println("childreimbursemodel::" + childreimbursemodel.toString());
		/* Employee Header starts */
		userlogin = (Login) request.getSession().getAttribute("login"); // fetching Persion Number

		PersonInformation personInfo = new PersonInformation();

		// Geting Employee Details from API
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();
		// System.out.println("URL for getting employee details :: " +
		// urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		// Loading of Employee Details starts here

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();

			// System.out.println("Employee Personal Details ==>" + personInfo);
		}
		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			comdes = response.getBody();
		}
		model.addAttribute("propic",comdes);

		model.addAttribute("personInfo", personInfo);

		/* Employee Header ends */

		validator.validate(childreimbursemodel, bindingResult);
		model.addAttribute("bindingResult", bindingResult);
		if (bindingResult.hasErrors()) {
			return "forms/reimbursement/child/createChildClaim :: createChildClaim";
		}

		List<ChildModel> chl = childreimbursemodel.getchilds();
		//System.out.println("files.length()::::::::::: " + files.length);
		if (files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isEmpty()) {
					String location = "/EmployeeDocs/" + userlogin.getEmplid() + "/ChildReimbursement";
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
						String empl = userlogin.getEmplid();

						String extension = FilenameUtils.getExtension(files[i].getOriginalFilename());
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
						//System.out.println("adl.get(" + i + ").getFilepres()::::::::::" + chl.get(i).getFilepres());
						if (chl.get(i).getFilepres().equals("Y")) {
							//System.out.println("File in i========== " + i);
							chl.get(i).setFeereceipt(storePath);
						} else {
							chl.get(i).setFeereceipt(chl.get(i).getAttachhidden());
						}
						storePath = location;
						//System.out.println(chl.get(i).getFeereceipt());
					} catch (Exception e) {
						//System.out.println("FILE ERRORRRRRR");
						e.printStackTrace();
						model.addAttribute("result", "WRITE_ERROR");
						return "forms/reimbursement/child/createChildClaim :: createChildClaim";
					}

				}
			}
		}

//////////////// update		
		if (status_id.equals("save")) {
			childreimbursemodel.setStatus("Saved");
			// System.out.println("in::"+statusid);
		} else if (status_id.equals("submit")) {
			// System.out.println("in 1::"+statusid);
			childreimbursemodel.setStatus("Submitted");
		}
		String url_UCECR = appGateway.getAppgatewaypyrl_sandhya() + "/api/childrmbrs/updateclaimchildreimburse";

		ChildReimburseModel sresm = new ChildReimburseModel();

		try {
			headers.setContentType(MediaType.APPLICATION_JSON);

			// System.out.println("----Child Claim : " + childreimbursemodel.toString());

			HttpEntity<ChildReimburseModel> crmrequest = new HttpEntity<ChildReimburseModel>(childreimbursemodel,
					headers);

			ResponseEntity<ChildReimburseModel> response1 = restTemplate.exchange(url_UCECR, HttpMethod.POST, crmrequest,
					ChildReimburseModel.class);
			// System.out.println("Response status =====> " + response.getStatusCode());

			if (response1.getStatusCode() == HttpStatus.OK) {
				sresm = response1.getBody();
				//System.out.println("Response Body : " + sresm);
				//System.out.println("Success in update" + childreimbursemodel.toString());
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
				model.addAttribute("result", "LOG_ERROR");
				return "forms/reimbursement/child/createChildClaim :: createChildClaim";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("result", "Failed to update child reimburesement");
			return "forms/reimbursement/child/createChildClaim :: createChildClaim";
			// sresm.setMessage("Failed to create Child Reimbursement!!");
		}

		// model.addAttribute("childreimbursemodel",childreimbursemodel);
		if(sresm.getSubmitStatus().equals("")){
			sresm.setSubmitStatus("Success");
		}
		model.addAttribute("result", sresm.getSubmitStatus());

		if(sresm.getSubmitStatus().equals("Success") && status_id.equals("submit")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = appGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+childreimbursemodel.getChildclaimid()+"/"+userlogin.getEmplid();
			System.out.println("URL APPROVER DATA :: "+getapprovalperson);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> mailrequest = new HttpEntity<String>(headers);
			ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, mailrequest, ApproverDetails.class);
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalPersonInfo.getPersonname(),userlogin.getEmplid(),"Child Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

		return "forms/reimbursement/child/createChildClaim :: createChildClaim";

	}

	/* Updating Child Education Claim Reimbursement ends --Asmita 29-09 */

	/* Posting Child Education Claim Reimbursement --Asmita 19-09 */

	@PostMapping(value = "/saveChildEducationClaim")
	public String saveChildEducationClaim(
			@ModelAttribute("childreimbursemodel") ChildReimburseModel childreimbursemodel,
			@RequestParam("file") MultipartFile[] files, String status_id, HttpServletRequest req, Model model,
			BindingResult bindingResult) {

		String RID = "3";
		//String ceiling = eligibilityreimb.getCeilinglimit(RID, userlogin.getEmplid());
		//model.addAttribute("empent", ceiling);

		//System.out.println("childreimbursemodel save::" + childreimbursemodel.toString());
		/* Employee Header starts */
		userlogin = (Login) req.getSession().getAttribute("login"); // fetching Persion Number
		// System.out.println("today's Date "+today);
		PersonInformation personInfo = new PersonInformation();

		// Geting Employee Details from API
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();
		// System.out.println("URL for getting employee details :: " +
		// urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		// Loading of Employee Details starts here

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();
		}

		model.addAttribute("personInfo", personInfo);

		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			comdes = response.getBody();
		}
		model.addAttribute("propic",comdes);

		CommonLOV[] childClass=null;
		String urlClassLOV = appGateway.getAppgateway() + "/General/loadReimbursementClass";
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> classLovResponse = restTemplate.exchange(urlClassLOV, HttpMethod.GET, request,
				CommonLOV[].class);
		if (classLovResponse.getStatusCode() == HttpStatus.OK) {
			childClass = classLovResponse.getBody();
		}
		

		Dependent_ChildModel[] dependentChilds = null;

		String urlChildDependent = appGateway.getAppgateway()
				+ "/PersonManagement/DependentDetails/getDependentChildList/" + userlogin.getEmplid();
		
		ResponseEntity<Dependent_ChildModel[]> dep_ChildModelResponse = restTemplate.exchange(urlChildDependent,
				HttpMethod.GET, emprequest, Dependent_ChildModel[].class);

		if (dep_ChildModelResponse.getStatusCode() == HttpStatus.OK) {
			dependentChilds = dep_ChildModelResponse.getBody();
		} else {
			System.out.println("Request Failed with response code : " + dep_ChildModelResponse.getStatusCode());
		}
		
		
		
		model.addAttribute("dependentChilds", dependentChilds);

		/* Employee Header ends */
		validator.validate(childreimbursemodel, bindingResult);
		for (ChildModel child : childreimbursemodel.getchilds()) {
		 validator.validate(child, bindingResult);
		
			}
		
		model.addAttribute("childclass", childClass);
		model.addAttribute("childreimbursemodel", childreimbursemodel);
		model.addAttribute("bindingResult", bindingResult);
		System.out.println("ALL ERRORS"+bindingResult.getAllErrors());
		if (bindingResult.hasErrors()) {
			return "forms/reimbursement/child/createChildClaim :: createChildClaim";
		}

		// file upload code here
		List<ChildModel> chl = childreimbursemodel.getchilds();
	//	System.out.println("files.length()::::::::::: " + files.length);
		if (files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				String location = "/EmployeeDocs/" + userlogin.getEmplid() + "/ChildReimbursement";
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
					String empl = userlogin.getEmplid();

					String extension = FilenameUtils.getExtension(files[i].getOriginalFilename());
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
					//System.out.println("adl.get(" + i + ").getFilepres()::::::::::" + chl.get(i).getFilepres());
					if (chl.get(i).getFilepres().equals("Y")) {
					//	System.out.println("File in i========== " + i);
						chl.get(i).setFeereceipt(storePath);
					} else {
						chl.get(i).setFeereceipt(chl.get(i).getAttachhidden());
					}
					storePath = location;
				} catch (Exception e) {
					System.out.println("FILE ERRORRRRRR");
					e.printStackTrace();
					model.addAttribute("result", "WRITE_ERROR");
					return "forms/reimbursement/child/createChildClaim :: createChildClaim";
				}

			}
		}

///////////////////////////// save
		if (status_id.equals("save")) {
			childreimbursemodel.setStatus("Saved");
			// System.out.println("in::"+statusid);
		} else if (status_id.equals("submit")) {
			// System.out.println("in 1::"+statusid);
			childreimbursemodel.setStatus("Submitted");
		}

		String url_CECR = appGateway.getAppgatewaypyrl_sandhya() + "/api/childrmbrs/claimchildreimburse";
		// String url_CECR =
		// "http://192.200.12.83:9120/api/childrmbrs/claimchildreimburse";
		ChildReimburseModel sresm = null;

		headers.setContentType(MediaType.APPLICATION_JSON);

		// System.out.println("----Child Claim : " + childreimbursemodel.toString());

		HttpEntity<ChildReimburseModel> request2 = new HttpEntity<ChildReimburseModel>(childreimbursemodel, headers);

		ResponseEntity<ChildReimburseModel> response2 = restTemplate.exchange(url_CECR, HttpMethod.POST, request2,
				ChildReimburseModel.class);
		// System.out.println("Response status =====> " + response.getStatusCode());

		if (response2.getStatusCode() == HttpStatus.OK) {
			sresm = response2.getBody();
			//System.out.println("Response Body : " + sresm);
			//System.out.println("Success in save" + childreimbursemodel.toString());
		} else {
			System.out.println("Request Failed");
			System.out.println(response2.getStatusCode());
			model.addAttribute("finalresult", "LOG_ERROR");
			return "forms/reimbursement/child/childSearch :: childSearch";
		}
		//System.out.println("sresm aftrsave" + sresm.toString());
		if(sresm.getSubmitStatus().equals("")){
			sresm.setSubmitStatus("Success");
		}
		model.addAttribute("result", sresm.getSubmitStatus());
		// }
		return "forms/reimbursement/child/createChildClaim :: createChildClaim";

	}

	/* Posting Child Education Claim Reimbursement ends --Asmita 23-10 */

	/* Child Reimbursement Search starts */

	@ResponseBody
	@RequestMapping("/getChildReimbursement")
	public ChildSearchModel[] childSearchModel(HttpSession session, HttpServletRequest request, Model model) {

		/* Employee Header starts */
		userlogin = (Login) request.getSession().getAttribute("login"); // fetching Persion Number

		PersonInformation personInfo = new PersonInformation();

		// Geting Employee Details from API
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();
		// System.out.println("URL for getting employee details :: " +
		// urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		// Loading of Employee Details starts here

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();

			// System.out.println("Employee Personal Details ==>" + personInfo);
		}

		model.addAttribute("personInfo", personInfo);

		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			comdes = response.getBody();
		}
		model.addAttribute("propic",comdes);


		/* Employee Header ends */

		ChildSearchModel[] search_data = null;

		String urlreimbursementSearch = appGateway.getAppgatewaypyrl_sandhya()
				+ "/api/childrmbrs/getclaimreimbursebypersionnbr/" + userlogin.getEmplid();
		// System.out.println("URL for child search :: " + urlreimbursementSearch);

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> reimbursement_search_request = new HttpEntity<String>(headers);

		ResponseEntity<ChildSearchModel[]> response1 = restTemplate.exchange(urlreimbursementSearch, HttpMethod.GET,
				reimbursement_search_request, ChildSearchModel[].class);

		if (response1.getStatusCode() == HttpStatus.OK) {
			search_data = response1.getBody();
			// System.out.println("Child Search Model : " + search_data);
		}

		return search_data;

	}
	/* Child Reimbursement Search ends */

	/* Child Reimbursement View ends */

	@RequestMapping("/editReimbursementDetails/{childclaimid}/{display_mode}")
	public String editChildReimbursementDetails(@PathVariable("childclaimid") int childclaimid,
			@PathVariable("display_mode") String display_mode, Model model, HttpServletRequest requestdata) {

		// System.out.println("Claim ID : " + childclaimid);

		ApprovalChildModel[] apr = null;
		String RID = "3";

		/* getting details of logged in employee */
		userlogin = (Login) requestdata.getSession().getAttribute("login");

		/* Employee Header starts */

		PersonInformation personInfo = new PersonInformation();

		// Geting Employee Details from API
		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ userlogin.getEmplid();
		// System.out.println("URL for getting employee details :: " +
		// urlEmployeeDetails);

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> emprequest = new HttpEntity<String>(headers);

		// Loading of Employee Details starts here

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();

			// System.out.println("Employee Personal Details ==>" + personInfo);
		}

		/* Employee Header ends */

		/********************************************************/
		String aprroveurl = appGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID
				+ "/" + childclaimid + "/" + userlogin.getEmplid();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr,
				ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseapr.getStatusCode());
		}
		// model.addAttribute("viewdata", res);
		// model.addAttribute("mode", mode);

		model.addAttribute("approverdata", apr);

		/********************************************************/

		ChildReimburseModel childreimbursemodel = null;

		String urlChildDetails = appGateway.getAppgatewaypyrl_sandhya() + "/api/childrmbrs/getclaimreimbursedetailsbyid/"
				+ childclaimid;

		// System.out.println("URL for getting child details :: " + urlChildDetails);

		/* setting Http Header */
		headers.setContentType(MediaType.APPLICATION_JSON);

		/* creating Http Request */
		HttpEntity<String> get_child_request = new HttpEntity<String>(headers);

		ResponseEntity<ChildReimburseModel> response = restTemplate.exchange(urlChildDetails, HttpMethod.GET,
				get_child_request, ChildReimburseModel.class);

		/* Checking if I get a response */
		if (response.getStatusCode() == HttpStatus.OK) {
			childreimbursemodel = response.getBody();
			// System.out.println("data::" + childReimburseModel);
			// System.out.println("Child Model data::" + childReimburseModel.getchilds());
		} else {
			System.out.println("Request Failed with response code : " + response.getStatusCode());
		}

		/* Code for Showing Add Fields also starts */

		CommonLOV[] childClass = null;

		Dependent_ChildModel[] dependentChilds = null;

		String urlChildDependent = appGateway.getAppgateway()
				+ "/PersonManagement/DependentDetails/getDependentChildList/" + userlogin.getEmplid();

		String urlClassLOV = appGateway.getAppgateway() + "/General/loadReimbursementClass";

		/* Loading of Child Class LOV starts here */
		ResponseEntity<CommonLOV[]> classLovResponse = restTemplate.exchange(urlClassLOV, HttpMethod.GET,
				get_child_request, CommonLOV[].class);

		if (classLovResponse.getStatusCode() == HttpStatus.OK) {
			childClass = classLovResponse.getBody();
		} else {
			System.out.println("Request Failed with response code : " + classLovResponse.getStatusCode());
		}

		// System.out.println("Child Class ==>" + childClass);

		/* Loading of Child Class LOV ends here */

		/* Loading of Dependent Child starts here */
		ResponseEntity<Dependent_ChildModel[]> dep_ChildModelResponse = restTemplate.exchange(urlChildDependent,
				HttpMethod.GET, get_child_request, Dependent_ChildModel[].class);

		if (dep_ChildModelResponse.getStatusCode() == HttpStatus.OK) {
			dependentChilds = dep_ChildModelResponse.getBody();
		} else {
			System.out.println("Request Failed with response code : " + dep_ChildModelResponse.getStatusCode());
		}

		// System.out.println("Dependents Class ==>" + dependentChilds);

		/* Loading of Dependent Child ends here */

		model.addAttribute("childclass", childClass);
		model.addAttribute("dependentChilds", dependentChilds);

		/* Code for Showing Add Fields also ends */

		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response1 = restTemplate.exchange(urlpropic, HttpMethod.GET, emprequest, CommonDescription.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			comdes = response1.getBody();
		}
		model.addAttribute("propic",comdes);


		model.addAttribute("personInfo", personInfo);
		model.addAttribute("childreimbursemodel", childreimbursemodel);
		model.addAttribute("display_mode", display_mode);
		return "forms/reimbursement/child/createChildClaim :: createChildClaim";
	}

	/* Child Reimbursement View ends */

	/* Controller for Child Remb - Asmita_18-09 ends here */

	/**********************
	 * added by rajat start here
	 *******************************/


	@RequestMapping("/viewApprovalDataChild/{childclaimid}/{display_mode}/{status}")
	public String viewApprovalDataChild(@PathVariable("childclaimid") int childclaimid, @PathVariable("status") String status,
										@PathVariable("display_mode") String display_mode, Model model, HttpServletRequest requestdata) {

		// added by rajat start here
		userlogin = (Login) requestdata.getSession().getAttribute("login");
		ApprovalChildModel[] apr = null;
		String RID = "3";
		HttpEntity<String> httpentity = new HttpEntity<String>(headers);

		ChildReimburseModel childreimburse = null;

		String urlChildDetails = appGateway.getAppgatewaypyrl_sandhya() + "/api/childrmbrs/getclaimreimbursedetailsbyid/"
				+ childclaimid;
		headers.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<ChildReimburseModel> response = restTemplate.exchange(urlChildDetails, HttpMethod.GET,
				httpentity, ChildReimburseModel.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			childreimburse = response.getBody();
		} else {
			System.out.println("Request Failed with response code : " + response.getStatusCode());
		}
		PersonInformation personInfo = new PersonInformation();

		String urlEmployeeDetails = appGateway.getAppgateway() + "/PersonDetails/getPersonInfo/"
				+ childreimburse.getpersonnumber();

		ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
				.exchange(urlEmployeeDetails, HttpMethod.GET, httpentity, PersonInformation.class);

		if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
			personInfo = employeePersonalDetailResponse.getBody();
		}

		String aprroveurl = appGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID
				+ "/" + childclaimid + "/" + childreimburse.getpersonnumber();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr,
				ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
			//System.out.println("print box Dabbe :::::"+apr.toString());
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());

		}

		//String ceiling = eligibilityreimb.getCeilinglimit(RID, childreimburse.getpersonnumber());
		//model.addAttribute("empent", ceiling);

		NotificationModel notify = null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String urlNotify = appGateway.getAppgateway() + "/Notification/getApprovalDataByPersonnoStatus/" + RID + "/"
				+ childclaimid + "/" + userlogin.getEmplid()+"/"+status;
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

		model.addAttribute("approverdata", apr);

		/********************
		 * added by rajat end here
		 *****************************************************/

		CommonLOV[] childClass = null;

		Dependent_ChildModel[] dependentChilds = null;

		String urlChildDependent = appGateway.getAppgateway()
				+ "/PersonManagement/DependentDetails/getDependentChildList/" + userlogin.getEmplid();

		String urlClassLOV = appGateway.getAppgateway() + "/General/loadReimbursementClass";

		/* Loading of Child Class LOV starts here */
		ResponseEntity<CommonLOV[]> classLovResponse = restTemplate.exchange(urlClassLOV, HttpMethod.GET, httpentity,
				CommonLOV[].class);

		if (classLovResponse.getStatusCode() == HttpStatus.OK) {
			childClass = classLovResponse.getBody();
		} else {
			System.out.println("Request Failed with response code : " + classLovResponse.getStatusCode());
		}

		ResponseEntity<Dependent_ChildModel[]> dep_ChildModelResponse = restTemplate.exchange(urlChildDependent,
				HttpMethod.GET, httpentity, Dependent_ChildModel[].class);

		if (dep_ChildModelResponse.getStatusCode() == HttpStatus.OK) {
			dependentChilds = dep_ChildModelResponse.getBody();
		} else {
			System.out.println("Request Failed with response code : " + dep_ChildModelResponse.getStatusCode());
		}

		// System.out.println("Dependents Class ==>" + dependentChilds);

		/* Loading of Dependent Child ends here */

		model.addAttribute("childclass", childClass);
		model.addAttribute("dependentChilds", dependentChilds);
		model.addAttribute("APPRAMT",notify.getApprovedamount());

		/* Code for Showing Add Fields also ends */

		CommonDescription comdes=null;
		String urlpropic= appGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+childreimburse.getpersonnumber()+"/Photo";
		ResponseEntity<CommonDescription> response1 = restTemplate.exchange(urlpropic, HttpMethod.GET, httpentity, CommonDescription.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			comdes = response1.getBody();
		}
		model.addAttribute("propic",comdes);


		model.addAttribute("personInfo", personInfo);
		model.addAttribute("childreimbursemodel", childreimburse);
		model.addAttribute("display_mode", display_mode);
		return "forms/reimbursement/child/ChildClaimApproval :: ChildClaimApproval";
	}

	/* Child Reimbursement View ends */
	
	
	
	
	
	@RequestMapping(value = "/childApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel childApprovalUpdate(@PathVariable("_status") String _status,String msg, String approvedamt, String childclaimid, HttpServletRequest reques,Model model) throws IOException, MessagingException {
		SingleResponseModel res = new SingleResponseModel();

		Login login = (Login) reques.getSession().getAttribute("login");


		headers.setContentType(MediaType.APPLICATION_JSON);
		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
		String RID="3";
	
		String urlNotify = appGateway.getAppgateway()+"/Notification/getUnreadApprovalData/"+RID+"/" + childclaimid+"/"+login.getEmplid();
		
		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET, request1Noti, NotificationModel.class);
		if(responseNotify.getStatusCode() == HttpStatus.OK) {
			notify=responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		model.addAttribute("NotifynavBar", notify);

		//System.out.println("APP AMT::"+approvedamt);
		notify.setStatus(_status);
		notify.setMessage(msg);
		notify.setApprovedamount(Double.parseDouble(approvedamt) );
		//System.out.println("Approved Button data :: "+notify.toString());

		String url = appGateway.getAppgateway() + "/Notification/submittedAprovelById";
		//System.out.println("url::"+url);
		//System.out.println("notify::"+notify.toString());
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
			String getapprovalperson = appGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+childclaimid+"/"+notify.getSubmittedbypersonno();
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"Child Reimbursement");
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
	

	/**********************
	 * added by rajat end here
	 **********************************/

}