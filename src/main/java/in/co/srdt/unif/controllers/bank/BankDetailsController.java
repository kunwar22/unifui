package in.co.srdt.unif.controllers.bank;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import in.co.srdt.unif.model.user.absence.CommonDescription;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.AccountType;
import in.co.srdt.unif.model.bank.CreateBankAccountsDetails;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.model.bank.BankAccountSearch;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/bankAccount")
public class BankDetailsController {	

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	@Autowired
	SmartValidator validator;
	
	BankDetailsController(){
		
	}
	
	BankDetailsController(HttpHeaders headers,RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}
	
	@RequestMapping("/manageBankAccount")
	public String manageabsencetype(Model model, HttpServletRequest req) {
		
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
		//////////Added for Profile Pic _Utsav\\\\\\\\\\
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo",personInformation);
		
		return "fragments/bank/manageBankAccount :: searchBankAccount";
	}
	
	
	
	@RequestMapping("/CreateBankAccount")
	public String createBankAccount(@ModelAttribute("bankAccountsDetails") CreateBankAccountsDetails bankAccountsDetails ,
			Model model, HttpServletRequest req) {		
			
		//System.out.println("Create bankAccountsDetails::::::::::::::::::::");
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
		//////////Added for Profile Pic _Utsav\\\\\\\\\\
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("bankAccountsDetails", bankAccountsDetails);
		
		
//		for(AccountType act : AccountType.values())
//		{
//		  //  System.out.println(env.name() + " :: "+ env.getUrl());
//			model.addAttribute("AccountType",act.getDescription());
//		}
		
//		AccountType[] types = AccountType.values();
//		String[] arr = new String[types.length];
//		int i = 0;
//		for(AccountType x : types) {
//			arr[i] = x.getDescription();
//			++i;
//		}
		
		model.addAttribute("AccountType",AccountType.values());	
		
		return "fragments/bank/createBankDetails :: createBankDet";
	}
	
	
	@RequestMapping(value = "/saveBankAccount", method = RequestMethod.POST)
	public String saveBankAccount(@ModelAttribute("CreateBankAccountsDetails") CreateBankAccountsDetails createBankAccountsDetails,Model model, BindingResult bindingResult, HttpServletRequest req)
	{
		
		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation personInformation =null;
		String url = appgateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
	//	System.out.println("URL: "+url);

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
		//////////Added for Profile Pic _Utsav\\\\\\\\\\
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("bankAccountsDetails", createBankAccountsDetails);
		
		/*
		 * AccountType[] types = AccountType.values(); String[] arr = new
		 * String[types.length]; int i = 0; for(AccountType x : types) { arr[i] =
		 * x.getDescription(); ++i; }
		 */
		
		model.addAttribute("AccountType",AccountType.values());	
		
		validator.validate(createBankAccountsDetails, bindingResult);
		model.addAttribute("bindingResult", bindingResult);

		if (bindingResult.hasErrors()) {
		//	System.out.println("in error");
			//model.addAttribute("result","Validation Failed");
			return "fragments/bank/createBankDetails :: createBankDet";
		}
		
		
		String urlBANK=appgateway.getAppgateway()+"/PersonManagement/BankAccountsDetails/saveBankAccountsDetails";
		SingleResponseModel res = new SingleResponseModel();
		try {
		//System.out.println("save bank::"+createBankAccountsDetails.toString());
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			HttpEntity<CreateBankAccountsDetails> request = new HttpEntity<CreateBankAccountsDetails>(createBankAccountsDetails, headers);
			
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlBANK,HttpMethod.POST,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
				//System.out.println("BankAccounts ::"+ createBankAccountsDetails.toString());
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
		}
		catch (Exception e){
			e.printStackTrace();
			//res.setMessage("Failed to create Bank Account.");
			model.addAttribute("result", "LOG_ERROR");
		}
		System.out.println("Message ::::: "+res.getMessage());
		model.addAttribute("result", res.getMessage());
		return "fragments/bank/createBankDetails :: createBankDet";
	}
	
	
	@ResponseBody
	@RequestMapping("/getBankDetails")
	public BankAccountSearch[] BankAccountSearch( Model model,HttpServletRequest request) {
		
		
		Login login = (Login) request.getSession().getAttribute("login");
		BankAccountSearch[] BANK = null;
		String urlBank = appgateway.getAppgateway()+"/PersonManagement/BankAccountsDetails/getAllBankAccountsDetailsByPersonNo/" +login.getEmplid();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request1 = new HttpEntity<String>(headers);

		ResponseEntity<BankAccountSearch[]> response = restTemplate.exchange(urlBank, HttpMethod.GET, request1,
				BankAccountSearch[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			BANK = response.getBody();
			//System.out.println("timezoneData ::::::::::::::::::::" + sd[0].getDescription());
		}
		
		return BANK;

	}
	
	
	
	@RequestMapping("edit/EditBankDetails/{bankaccountsid}/{mode}")
	public String CreateBankAccountsDetails(@PathVariable("bankaccountsid") String bankaccountsid,@PathVariable("mode") String mode, Model model,HttpServletRequest req)
			throws URISyntaxException, ParseException {
		
		//System.out.println("EditBankDetails By ID");
		
		String SearchBankAccounts = bankaccountsid;
		
		CreateBankAccountsDetails bankAccountsDetails=null;
		String urrlCreateBank= appgateway.getAppgateway()+"/PersonManagement/BankAccountsDetails/getBankAccountsDetailsById/" + SearchBankAccounts;
		URI urlBANK = new URI(urrlCreateBank);
	
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getBankAccountsRequest = new HttpEntity<String>(headerss);

		ResponseEntity<CreateBankAccountsDetails> getBankAccountsResponse = restTemplate.exchange(urlBANK, HttpMethod.GET,
				getBankAccountsRequest, CreateBankAccountsDetails.class);

		if (getBankAccountsResponse.getStatusCode() == HttpStatus.OK) {
			bankAccountsDetails = getBankAccountsResponse.getBody();
			
			
		} else {
			System.out.println("Request Failed");
			System.out.println(getBankAccountsResponse.getStatusCode());
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
		model.addAttribute("personInfo",personInformation);
		//////////Added for Profile Pic _Utsav\\\\\\\\\\
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		/*
		 * AccountType[] types = AccountType.values(); String[] arr = new
		 * String[types.length]; int i = 0; for(AccountType x : types) { arr[i] =
		 * x.getDescription(); ++i; }
		 */
		
		model.addAttribute("AccountType",AccountType.values());
		model.addAttribute("bankAccountsDetails", bankAccountsDetails);
		model.addAttribute("mode",mode);
		return "fragments/bank/createBankDetails :: createBankDet";

	}
	
	
//	@PostMapping(value = "/correctBankAccount")
//	public @ResponseBody SingleResponseModel correctBankAccount(@ModelAttribute("CreateBankAccountsDetails") CreateBankAccountsDetails createBankAccountsDetails)
//	{
	
	@RequestMapping(value = "/correctBankAccount/{mode}", method = RequestMethod.POST)
	public String correctBankAccount(@PathVariable("mode") String mode,@ModelAttribute("createBankAccountsDetails") CreateBankAccountsDetails createBankAccountsDetails,Model model, BindingResult bindingResult, HttpServletRequest req)
	{
		
		Login login = (Login) req.getSession().getAttribute("login");
		PersonInformation personInformation =new PersonInformation();
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
		//////////Added for Profile Pic _Utsav\\\\\\\\\\
		CommonDescription comdes=null;
		String urlpropic= appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requestPesro, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("bankAccountsDetails", createBankAccountsDetails);
		System.out.println("BANK DETAILS ======> "+createBankAccountsDetails.toString());
		model.addAttribute("mode", mode);
		/*
		 * AccountType[] types = AccountType.values(); String[] arr = new
		 * String[types.length]; int i = 0; for(AccountType x : types) { arr[i] =
		 * x.getDescription(); ++i; }
		 */
		
		model.addAttribute("AccountType",AccountType.values());
		
		
		validator.validate(createBankAccountsDetails, bindingResult);
		model.addAttribute("bindingResult", bindingResult);

		if (bindingResult.hasErrors()) {
			//System.out.println("in error");
			//model.addAttribute("result","Validation Failed");
			
			return "fragments/bank/createBankDetails :: createBankDet";
		}
		
		//System.out.println("correctBankAccount");
		String urlBANK=appgateway.getAppgateway()+"/PersonManagement/BankAccountsDetails/correctBankAccountsDetails";
		SingleResponseModel res = new SingleResponseModel();
		
		
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			HttpEntity<CreateBankAccountsDetails> request = new HttpEntity<CreateBankAccountsDetails>(createBankAccountsDetails, headers);
			System.out.println("====== BEFORE RESPONSE  Bank model : ========== "+createBankAccountsDetails.toString());
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlBANK,HttpMethod.PUT,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			
			System.out.println("====== AFTER RESPONSE  Bank model : ========== "+createBankAccountsDetails.toString());
		}
		catch (Exception e){
			e.printStackTrace();
			//res.setMessage("Failed to Correct Bank Account.");
			model.addAttribute("result", "LOG_ERROR");
		}
		System.out.println("Message ::::: "+res.getMessage());
		System.out.println("mode ::::: "+mode);
		model.addAttribute("result", res.getMessage());
		model.addAttribute("mode", mode);
		model.addAttribute("personInfo",personInformation);	
		model.addAttribute("bankAccountsDetails", createBankAccountsDetails);
		System.out.println("BANK HERE===========> "+createBankAccountsDetails.toString());
		return "fragments/bank/createBankDetails :: createBankDet";
	}


}
