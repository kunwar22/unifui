package in.co.srdt.unif.controllers.taxdeclaration;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.taxdeclaration.chapter6A.Chapter6AWrapper;
import in.co.srdt.unif.model.taxdeclaration.hra.HouseRentModel;
import in.co.srdt.unif.model.taxdeclaration.interesthomeloan.PayTaxEmplList;
import in.co.srdt.unif.model.taxdeclaration.previousemplincome.PreviousEmployementModel;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/houserent")
public class TaxDeclarationController {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationGateway appgateway;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	SmartValidator validator;
	
	@RequestMapping("/managehouserent")
	public String managehouserent(Model model, HttpServletRequest req) {
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		Login login = (Login)req.getSession().getAttribute("login");
		
		HouseRentModel hradetails=null;
		String urlgethradetails = appgateway.getAppgateway_payroll()+"/api/taxHouseRent/getallhouserent/"+login.getEmplid();
		System.out.println("url::"+urlgethradetails);
		ResponseEntity<HouseRentModel> response = restTemplate.exchange(urlgethradetails, HttpMethod.GET, request, HouseRentModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			hradetails = response.getBody();
		}
		model.addAttribute("hradetails",hradetails);

		/*for(int i=0; i<hradetails.getRent().size(); i++){
			System.out.println("DATE :: "+hradetails.getRent().get(i).getEff_startdt());
		}*/

		PreviousEmployementModel prevempincome=null;
		String urlprevempincome = appgateway.getAppgateway_payroll()+"/api/previousEmplmntTax/getPreviousEmplmnt/"+login.getEmplid();
		ResponseEntity<PreviousEmployementModel> response1 = restTemplate.exchange(urlprevempincome, HttpMethod.GET, request, PreviousEmployementModel.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			prevempincome = response1.getBody();
		}
		model.addAttribute("prevempincome",prevempincome);
		
		
		PayTaxEmplList paytax=null;
		String urlpaytax = appgateway.getAppgateway_payroll()+"/api/paytaxEmplIncLossSource/getPayTaxEmplIncLoss/"+login.getEmplid();
		ResponseEntity<PayTaxEmplList> response3 = restTemplate.exchange(urlpaytax, HttpMethod.GET, request, PayTaxEmplList.class);
		if (response3.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			paytax = response3.getBody();
		}
		model.addAttribute("paytax",paytax);
		//System.out.println("PAYTAx"+paytax.toString());
		
		Chapter6AWrapper chapter6A=null;
		String urlchapter6A = appgateway.getAppgateway_payroll()+"/api/employeeChapter6A/getTaxEmployeeChapter6AList/"+login.getEmplid();
		ResponseEntity<Chapter6AWrapper> response4 = restTemplate.exchange(urlchapter6A, HttpMethod.GET, request, Chapter6AWrapper.class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			chapter6A = response4.getBody();
		}
		model.addAttribute("chapter6A",chapter6A);
		
		/*
		System.out.println("HRA_RENT_DETAILS :: "+hradetails.getRent().size());
		System.out.println("HRA_OWNER_DETAILS :: "+hradetails.getOwner().size());
		for(int i=0;i<hradetails.getRent().size();i++) {
			System.out.println("HRA_RENT_DETAILS "+i+" :: "+hradetails.getRent().get(i).toString());
		}*/

		return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
	}


	
@RequestMapping(value = "/hraSave", method = RequestMethod.POST)	
public String hrasubmit(@RequestParam("rentfiles")MultipartFile[] rentfiles, @RequestParam("ownerfiles")MultipartFile[] ownerfiles, HouseRentModel hramodel, HttpServletRequest request, Model model) {

		System.out.println("Multipart RENTFILES length :: "+rentfiles.length);
		System.out.println("Multipart OWNERFILES length :: "+ownerfiles.length);
		/*************************VALIDATION PART START****************************/
		Login login = (Login) request.getSession().getAttribute("login");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request2 = new HttpEntity<String>(headers);

		model.addAttribute("hradetails",hramodel);

		PreviousEmployementModel prevempincome=null;
		String urlprevempincome = appgateway.getAppgateway_payroll()+"/api/previousEmplmntTax/getPreviousEmplmnt/"+login.getEmplid();
		ResponseEntity<PreviousEmployementModel> response2 = restTemplate.exchange(urlprevempincome, HttpMethod.GET, request2, PreviousEmployementModel.class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			prevempincome = response2.getBody();
		}
		model.addAttribute("prevempincome",prevempincome);


		PayTaxEmplList paytax=null;
		String urlpaytax = appgateway.getAppgateway_payroll()+"/api/paytaxEmplIncLossSource/getPayTaxEmplIncLoss/"+login.getEmplid();
		ResponseEntity<PayTaxEmplList> response3 = restTemplate.exchange(urlpaytax, HttpMethod.GET, request2, PayTaxEmplList.class);
		if (response3.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			paytax = response3.getBody();
		}
		model.addAttribute("paytax",paytax);

		Chapter6AWrapper chapter6A=null;
		String urlchapter6A = appgateway.getAppgateway_payroll()+"/api/employeeChapter6A/getTaxEmployeeChapter6AList/"+login.getEmplid();
		ResponseEntity<Chapter6AWrapper> response4 = restTemplate.exchange(urlchapter6A, HttpMethod.GET, request2, Chapter6AWrapper.class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			chapter6A = response4.getBody();
		}
		model.addAttribute("chapter6A",chapter6A);

		double finalamt=0.0;
		for(int x=0; x<hramodel.getRent().size(); x++){
			finalamt+=hramodel.getRent().get(x).getD_amt();
		}
		System.out.println(finalamt);
		if (finalamt >= 100000.0) {
			System.out.println("Inside PAN validation and amount");
			for (int y = 0; y < hramodel.getOwner().size(); y++) {
				if (hramodel.getOwner().get(y).getPan().equals("") || hramodel.getOwner().get(y).getPan()==null) {
					System.out.println("Inside PAN validation and amount is"+finalamt);
					model.addAttribute("result", "PANREQUIRED");

					return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
				}
			}
		}
	

		/*************************VALIDATION PART END****************************/

		/*************************FILE ATTACHMENT PART START ONLY EXECUTE WHEN FILE SELECTED****************************/
		
		System.out.println("RENTLIST :: "+hramodel.getRent().size());
		System.out.println("OWNERLIST :: "+hramodel.getOwner().size());
		
		
		
		for(int i=0; i<ownerfiles.length; i++) {
			if(ownerfiles[i]!=null) {
				System.out.println("NOT NULL OWNER");
			if(!ownerfiles[i].isEmpty()) {
				System.out.println("Inside File Create OWNER");
				String location = "/EmployeeDocs/"+login.getEmplid()+"/HRADeclaration/Owner";
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

					String extension = FilenameUtils.getExtension(ownerfiles[i].getOriginalFilename());
					String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss" + "'" + empl +"_"+i+ "." + extension + "'").format(new Date());


					File target = new File(filePath + File.separator + generatedFileName);
					int readByteCount = 0;
					byte[] buffer = new byte[20480];

					BufferedInputStream in = new BufferedInputStream(ownerfiles[i].getInputStream());
					@SuppressWarnings("resource")
					FileOutputStream out = new FileOutputStream(target);
					while ((readByteCount = in.read(buffer)) != -1) {
						out.write(buffer, 0, readByteCount);
					}

					storePath += "/" + generatedFileName;
					System.out.println(storePath);
					hramodel.getOwner().get(i).setAttachment(storePath);
					}
					catch (IOException e) {
						System.out.println("IOException");
						e.printStackTrace();
						model.addAttribute("result", "IOEXCEPTION");
						return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
					}
					catch (IllegalArgumentException e) {
						System.out.println("IllegalArgumentException");
						e.printStackTrace();
						model.addAttribute("result", "ILLEGALARG");
						return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
					}
					catch (Exception e) {
						System.out.println("Exception");
						e.printStackTrace();
						model.addAttribute("result", "WRITE_ERROR");
						return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
					}
				}
		}
		}
		
		for(int m=0;m<rentfiles.length;m++) {
			if(!rentfiles[m].isEmpty()) {
				System.out.println("FILE on ROW "+m+" present.");
			}
		}
		
		for(int z=0; z<rentfiles.length; z++) {
			if(rentfiles[z]!=null) 
			{	System.out.println("NOT NULL RENT");
				if(!rentfiles[z].isEmpty()) 
				{
				System.out.println("Inside File Create RENT");
				String location = "/EmployeeDocs/"+login.getEmplid()+"/HRADeclaration/Rent";
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

					String extension = FilenameUtils.getExtension(rentfiles[z].getOriginalFilename());
					String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss" + "'" + empl +"_"+z+ "." + extension + "'").format(new Date());


					File target = new File(filePath + File.separator + generatedFileName);
					int readByteCount = 0;
					byte[] buffer = new byte[20480];

					BufferedInputStream in = new BufferedInputStream(rentfiles[z].getInputStream());
					@SuppressWarnings("resource")
					FileOutputStream out = new FileOutputStream(target);
					while ((readByteCount = in.read(buffer)) != -1) {
						out.write(buffer, 0, readByteCount);
					}

					storePath += "/" + generatedFileName;
					System.out.println(storePath);
					hramodel.getRent().get(z).setAttachment(storePath);
					}
					catch (IOException e) {
						System.out.println("IOException");
						e.printStackTrace();
						model.addAttribute("result", "IOEXCEPTION");
						return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
					}
					catch (IllegalArgumentException e) {
						System.out.println("IllegalArgumentException");
						e.printStackTrace();
						model.addAttribute("result", "ILLEGALARG");
						return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
					}
					catch (Exception e) {
						System.out.println("Exception");
						e.printStackTrace();
						model.addAttribute("result", "WRITE_ERROR");
						return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
					}
				
				}
		}
		
		}
		
		for(int k=0;k<hramodel.getRent().size();k++) {
			hramodel.getRent().get(k).setPersonNumber(login.getEmplid());
			hramodel.getRent().get(k).setCreatedby(login.getEmplid());
			if(hramodel.getRent().get(k).getD_amt()>0 && (!hramodel.getRent().get(k).getStatus().equals("Verified") && !hramodel.getRent().get(k).getStatus().equals("verified"))){
				hramodel.getRent().get(k).setStatus("Declared");
			}
			System.out.println("HRA_RENT_DETAILS "+k+" :: "+hramodel.getRent().get(k).toString());
		}
		for(int j=0;j<hramodel.getOwner().size();j++) {
			hramodel.getOwner().get(j).setPersonNumber(login.getEmplid());
			hramodel.getOwner().get(j).setCreatedby(login.getEmplid());
			System.out.println("HRA_Owner_DETAILS "+j+" :: "+hramodel.getOwner().get(j).toString());
		}


		/*************************FILE ATTACHMENT PART END****************************/
		
		/***********************DATABASE TRANSACTIONS START FOR DATA SAVE or SUBMIT**********************/
		
		String urlsave=appgateway.getAppgateway_payroll()+"/api/taxHouseRent/addhouserent";
		
		SingleResponseModel res =null;
		HttpEntity<HouseRentModel> request1 = new HttpEntity<HouseRentModel>(hramodel, headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlsave, HttpMethod.POST, request1, SingleResponseModel.class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println(response.getStatusCode());
			model.addAttribute("result", "LOG_ERROR");
			return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
		}

		HouseRentModel hradetails=null;

		String urlgethradetails = appgateway.getAppgateway_payroll()+"/api/taxHouseRent/getallhouserent/"+login.getEmplid();
		ResponseEntity<HouseRentModel> response1 = restTemplate.exchange(urlgethradetails, HttpMethod.GET, request2, HouseRentModel.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			hradetails = response1.getBody();
		}
		model.addAttribute("hradetails",hradetails);
		model.addAttribute("result",res.getMessage());

		return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
		
		/***********************DATABASE TRANSACTIONS END FOR DATA SAVE or SUBMIT**********************/
	}



@RequestMapping(value = "/paytaxSave", method = RequestMethod.POST)	
public String paytaxSubmit(@RequestParam("paytaxfiles")MultipartFile[] paytaxfiles, PayTaxEmplList paytaxwrapper, HttpServletRequest request, Model model) {
	

	System.out.println("Multipart PAYTAX length :: "+paytaxfiles.length);
	/*************************VALIDATION PART START****************************/
	Login login = (Login) request.getSession().getAttribute("login");

	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> request2 = new HttpEntity<String>(headers);

	//model.addAttribute("paytax",paytaxwrapper);

	HouseRentModel hradetails=null;

	String urlgethradetails = appgateway.getAppgateway_payroll()+"/api/taxHouseRent/getallhouserent/"+login.getEmplid();
	ResponseEntity<HouseRentModel> response1 = restTemplate.exchange(urlgethradetails, HttpMethod.GET, request2, HouseRentModel.class);
	if (response1.getStatusCode() == HttpStatus.OK) {
		System.out.println("STATUS OK");
		hradetails = response1.getBody();
	}

	model.addAttribute("hradetails",hradetails);


	PreviousEmployementModel prevempincome=null;
	String urlprevempincome = appgateway.getAppgateway_payroll()+"/api/previousEmplmntTax/getPreviousEmplmnt/"+login.getEmplid();
	ResponseEntity<PreviousEmployementModel> response2 = restTemplate.exchange(urlprevempincome, HttpMethod.GET, request2, PreviousEmployementModel.class);
	if (response2.getStatusCode() == HttpStatus.OK) {
		System.out.println("STATUS OK");
		prevempincome = response2.getBody();
	}
	model.addAttribute("prevempincome",prevempincome);

	Chapter6AWrapper chapter6A=null;
	String urlchapter6A = appgateway.getAppgateway_payroll()+"/api/employeeChapter6A/getTaxEmployeeChapter6AList/"+login.getEmplid();
	ResponseEntity<Chapter6AWrapper> response4 = restTemplate.exchange(urlchapter6A, HttpMethod.GET, request2, Chapter6AWrapper.class);
	if (response4.getStatusCode() == HttpStatus.OK) {
		System.out.println("STATUS OK");
		chapter6A = response4.getBody();
	}
	model.addAttribute("chapter6A",chapter6A);


	model.addAttribute("paytax",paytaxwrapper);

	/*************************VALIDATION PART END****************************/

	/*************************FILE ATTACHMENT PART START ONLY EXECUTE WHEN FILE SELECTED****************************/
	
	System.out.println("RENTLIST :: "+paytaxwrapper.getPerson().size());
	
	for(int i=0; i<paytaxfiles.length; i++) {
		if(paytaxfiles[i]!=null) {
			System.out.println("NOT NULL PAYTAX");
		if(!paytaxfiles[i].isEmpty()) {
			System.out.println("Inside File Create PAYTAX");
			String location = "/EmployeeDocs/"+login.getEmplid()+"/HRADeclaration/HomeLoanInterest";
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

				String extension = FilenameUtils.getExtension(paytaxfiles[i].getOriginalFilename());
				String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss" + "'" + empl +"_"+i+ "." + extension + "'").format(new Date());


				File target = new File(filePath + File.separator + generatedFileName);
				int readByteCount = 0;
				byte[] buffer = new byte[20480];

				BufferedInputStream in = new BufferedInputStream(paytaxfiles[i].getInputStream());
				@SuppressWarnings("resource")
				FileOutputStream out = new FileOutputStream(target);
				while ((readByteCount = in.read(buffer)) != -1) {
					out.write(buffer, 0, readByteCount);
				}

				storePath += "/" + generatedFileName;
				System.out.println(storePath);
				paytaxwrapper.getPerson().get(i).setAttachments(storePath);
				}
				catch (IOException e) {
					System.out.println("IOException");
					e.printStackTrace();
					model.addAttribute("result", "IOEXCEPTION");
					return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
				}
				catch (IllegalArgumentException e) {
					System.out.println("IllegalArgumentException");
					e.printStackTrace();
					model.addAttribute("result", "ILLEGALARG");
					return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
				}
				catch (Exception e) {
					System.out.println("Exception");
					e.printStackTrace();
					model.addAttribute("result", "WRITE_ERROR");
					return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
				}
			}
	}
	}
	
	
	for(int k=0;k<paytaxwrapper.getPerson().size();k++) {
		paytaxwrapper.getPerson().get(k).setPersonNumber(login.getEmplid());
		//paytaxwrapper.getPaytaxsave().get(k).setLookupdescr("Declared");
		if(paytaxwrapper.getPerson().get(k).getDecAmount()>0 && (!paytaxwrapper.getPerson().get(k).getStatus().equals("Verified") && !paytaxwrapper.getPerson().get(k).getStatus().equals("verified"))){
			paytaxwrapper.getPerson().get(k).setStatus("Declared");
		}
		System.out.println("HRA_RENT_DETAILS "+k+" :: "+paytaxwrapper.getPerson().get(k).toString());
	}
	
	/*************************FILE ATTACHMENT PART END****************************/
	
	/***********************DATABASE TRANSACTIONS START FOR DATA SAVE or SUBMIT**********************/
	
	String urlsave=appgateway.getAppgateway_payroll()+"/api/paytaxEmplIncLossSource/savePayTaxEmpl";
	paytaxwrapper.setPersonnumber(login.getEmplid());
	SingleResponseModel res =null;
	HttpEntity<PayTaxEmplList> request1 = new HttpEntity<PayTaxEmplList>(paytaxwrapper, headers);
	ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlsave, HttpMethod.POST, request1, SingleResponseModel.class);
	
	if (response.getStatusCode() == HttpStatus.OK) {
		res = response.getBody();
	} else {
		System.out.println(response.getStatusCode());
		model.addAttribute("result", "LOG_ERROR");
		return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
	}
	
	model.addAttribute("result",res.getMessage());
	PayTaxEmplList paytax1=null;
	String urlpaytax = appgateway.getAppgateway_payroll()+"/api/paytaxEmplIncLossSource/getPayTaxEmplIncLoss/"+login.getEmplid();
	ResponseEntity<PayTaxEmplList> response5 = restTemplate.exchange(urlpaytax, HttpMethod.GET, request2, PayTaxEmplList.class);
	if (response5.getStatusCode() == HttpStatus.OK) {
		System.out.println("STATUS OK");
		paytax1 = response5.getBody();
	}
	model.addAttribute("paytax",paytax1);

	return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
	
	/***********************DATABASE TRANSACTIONS END FOR DATA SAVE or SUBMIT**********************/
}




@RequestMapping(value = "/prevSave", method = RequestMethod.POST)	
public String presubmit(@RequestParam("previncomefile")MultipartFile previncomefile,PreviousEmployementModel preemp, HttpServletRequest request, Model model) {
	

	
	Login login = (Login) request.getSession().getAttribute("login");

	/*************************FILE ATTACHMENT PART START ONLY EXECUTE WHEN FILE SELECTED****************************/

	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> request2 = new HttpEntity<String>(headers);

	model.addAttribute("prevempincome",preemp);

	HouseRentModel hradetails=null;
	String urlgethradetails = appgateway.getAppgateway_payroll()+"/api/taxHouseRent/getallhouserent/"+login.getEmplid();
	ResponseEntity<HouseRentModel> response1 = restTemplate.exchange(urlgethradetails, HttpMethod.GET, request2, HouseRentModel.class);
	if (response1.getStatusCode() == HttpStatus.OK) {
		System.out.println("STATUS OK");
		hradetails = response1.getBody();
	}
	model.addAttribute("hradetails",hradetails);



	PayTaxEmplList paytax=null;
	String urlpaytax = appgateway.getAppgateway_payroll()+"/api/paytaxEmplIncLossSource/getPayTaxEmplIncLoss/"+login.getEmplid();
	ResponseEntity<PayTaxEmplList> response3 = restTemplate.exchange(urlpaytax, HttpMethod.GET, request2, PayTaxEmplList.class);
	if (response3.getStatusCode() == HttpStatus.OK) {
		System.out.println("STATUS OK");
		paytax = response3.getBody();
	}
	model.addAttribute("paytax",paytax);

	Chapter6AWrapper chapter6A=null;
	String urlchapter6A = appgateway.getAppgateway_payroll()+"/api/employeeChapter6A/getTaxEmployeeChapter6AList/"+login.getEmplid();
	ResponseEntity<Chapter6AWrapper> response4 = restTemplate.exchange(urlchapter6A, HttpMethod.GET, request2, Chapter6AWrapper.class);
	if (response4.getStatusCode() == HttpStatus.OK) {
		System.out.println("STATUS OK");
		chapter6A = response4.getBody();
	}
	model.addAttribute("chapter6A",chapter6A);



	if(previncomefile!=null) {
			System.out.println("NOT NULL PREV");
		if(!previncomefile.isEmpty()) {
			System.out.println("Inside File Create PREV");
			String location = "/EmployeeDocs/"+login.getEmplid()+"/PreviousEmployerIncome";
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

				String extension = FilenameUtils.getExtension(previncomefile.getOriginalFilename());
				String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss" + "'" + empl + "." + extension + "'").format(new Date());


				File target = new File(filePath + File.separator + generatedFileName);
				int readByteCount = 0;
				byte[] buffer = new byte[20480];

				BufferedInputStream in = new BufferedInputStream(previncomefile.getInputStream());
				@SuppressWarnings("resource")
				FileOutputStream out = new FileOutputStream(target);
				while ((readByteCount = in.read(buffer)) != -1) {
					out.write(buffer, 0, readByteCount);
				}

				storePath += "/" + generatedFileName;
				System.out.println(storePath);
				preemp.setAttachment(storePath);
				}
				catch (IOException e) {
					System.out.println("IOException");
					e.printStackTrace();
					model.addAttribute("result", "IOEXCEPTION");
					return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
				}
				catch (IllegalArgumentException e) {
					System.out.println("IllegalArgumentException");
					e.printStackTrace();
					model.addAttribute("result", "ILLEGALARG");
					return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
				}
				catch (Exception e) {
					System.out.println("Exception");
					e.printStackTrace();
					model.addAttribute("result", "WRITE_ERROR");
					return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
				}
			}
	}
	
	/*************************FILE ATTACHMENT PART END****************************/
	
	/***********************DATABASE TRANSACTIONS START FOR DATA SAVE or SUBMIT**********************/
	preemp.setPersonNumber(login.getEmplid());
	if (preemp.getStatus()!=212) {
		preemp.setStatus(211);
	}

	System.out.println("PREV DATA"+preemp.toString());
	
	String urlsave=appgateway.getAppgateway_payroll()+"/api/previousEmplmntTax/addPreviousEmplmnt";
	SingleResponseModel res =null;
	HttpEntity<PreviousEmployementModel> request1 = new HttpEntity<PreviousEmployementModel>(preemp, headers);
	ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlsave, HttpMethod.POST, request1, SingleResponseModel.class);
	
	if (response.getStatusCode() == HttpStatus.OK) {
		res = response.getBody();
	} else {
		System.out.println(response.getStatusCode());
		model.addAttribute("result", "LOG_ERROR");
		return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
	}
	
	model.addAttribute("result",res.getMessage());

	PreviousEmployementModel prevempincome=null;
	String urlprevempincome = appgateway.getAppgateway_payroll()+"/api/previousEmplmntTax/getPreviousEmplmnt/"+login.getEmplid();
	ResponseEntity<PreviousEmployementModel> response2 = restTemplate.exchange(urlprevempincome, HttpMethod.GET, request2, PreviousEmployementModel.class);
	if (response2.getStatusCode() == HttpStatus.OK) {
		System.out.println("STATUS OK");
		prevempincome = response2.getBody();
	}
	model.addAttribute("prevempincome",prevempincome);

	return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
	
	/***********************DATABASE TRANSACTIONS END FOR DATA SAVE or SUBMIT**********************/
}



	@RequestMapping(value = "/chapterVIASave", method = RequestMethod.POST)
	public String chapterVIAsubmit(@RequestParam("chapterVIAfiles")MultipartFile[] chapterVIAfiles, Chapter6AWrapper chapter6AWrapper, HttpServletRequest request, Model model) {

			

		System.out.println("Multipart VIAFILES length :: "+chapterVIAfiles.length);
		/*************************VALIDATION PART START****************************/
		Login login = (Login) request.getSession().getAttribute("login");

		model.addAttribute("chapter6A",chapter6AWrapper);

		HouseRentModel hradetails=null;

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request2 = new HttpEntity<String>(headers);

		String urlgethradetails = appgateway.getAppgateway_payroll()+"/api/taxHouseRent/getallhouserent/"+login.getEmplid();
		ResponseEntity<HouseRentModel> response1 = restTemplate.exchange(urlgethradetails, HttpMethod.GET, request2, HouseRentModel.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			hradetails = response1.getBody();
		}

		model.addAttribute("hradetails",hradetails);


		PreviousEmployementModel prevempincome=null;
		String urlprevempincome = appgateway.getAppgateway_payroll()+"/api/previousEmplmntTax/getPreviousEmplmnt/"+login.getEmplid();
		ResponseEntity<PreviousEmployementModel> response2 = restTemplate.exchange(urlprevempincome, HttpMethod.GET, request2, PreviousEmployementModel.class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			prevempincome = response2.getBody();
		}
		model.addAttribute("prevempincome",prevempincome);


		PayTaxEmplList paytax=null;
		String urlpaytax = appgateway.getAppgateway_payroll()+"/api/paytaxEmplIncLossSource/getPayTaxEmplIncLoss/"+login.getEmplid();
		ResponseEntity<PayTaxEmplList> response3 = restTemplate.exchange(urlpaytax, HttpMethod.GET, request2, PayTaxEmplList.class);
		if (response3.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			paytax = response3.getBody();
		}
		model.addAttribute("paytax",paytax);


		/*************************VALIDATION PART END****************************/

		/*************************FILE ATTACHMENT PART START ONLY EXECUTE WHEN FILE SELECTED****************************/

		System.out.println("CHAPTERVIAMODEL :: "+chapter6AWrapper.getTaxEmployeeChapter6A().size());



		for(int i=0; i<chapterVIAfiles.length; i++) {
			if(chapterVIAfiles[i]!=null) {
				System.out.println("NOT NULL CHAPTERVIA");
				if(!chapterVIAfiles[i].isEmpty()) {
					System.out.println("Inside File Create CHAPTERVIA");
					String location = "/EmployeeDocs/"+login.getEmplid()+"/HRADeclaration/ChapterVIA";
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

						String extension = FilenameUtils.getExtension(chapterVIAfiles[i].getOriginalFilename());
						String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss" + "'" + empl +"_"+i+ "." + extension + "'").format(new Date());


						File target = new File(filePath + File.separator + generatedFileName);
						int readByteCount = 0;
						byte[] buffer = new byte[20480];

						BufferedInputStream in = new BufferedInputStream(chapterVIAfiles[i].getInputStream());
						@SuppressWarnings("resource")
						FileOutputStream out = new FileOutputStream(target);
						while ((readByteCount = in.read(buffer)) != -1) {
							out.write(buffer, 0, readByteCount);
						}

						storePath += "/" + generatedFileName;
						System.out.println(storePath);
						chapter6AWrapper.getTaxEmployeeChapter6A().get(i).setAttachment(storePath);
					}
					catch (IOException e) {
						System.out.println("IOException");
						e.printStackTrace();
						model.addAttribute("result", "IOEXCEPTION");
						return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
					}
					catch (IllegalArgumentException e) {
						System.out.println("IllegalArgumentException");
						e.printStackTrace();
						model.addAttribute("result", "ILLEGALARG");
						return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
					}
					catch (Exception e) {
						System.out.println("Exception");
						e.printStackTrace();
						model.addAttribute("result", "WRITE_ERROR");
						return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
					}
				}
			}
		}

		for(int k=0;k<chapter6AWrapper.getTaxEmployeeChapter6A().size();k++) {
			chapter6AWrapper.getTaxEmployeeChapter6A().get(k).setPersonnumber(login.getEmplid());
			if(chapter6AWrapper.getTaxEmployeeChapter6A().get(k).getDamount()>0 && (!chapter6AWrapper.getTaxEmployeeChapter6A().get(k).getStatus().equals("Verified") && !chapter6AWrapper.getTaxEmployeeChapter6A().get(k).getStatus().equals("verified"))){
				chapter6AWrapper.getTaxEmployeeChapter6A().get(k).setStatus("Declared");
			}
			System.out.println("CHAPTER_VI_A_DETAILS "+k+" :: "+chapter6AWrapper.getTaxEmployeeChapter6A().get(k).toString());
		}
		/*************************FILE ATTACHMENT PART END****************************/

		/***********************DATABASE TRANSACTIONS START FOR DATA SAVE or SUBMIT**********************/

		String urlsave=appgateway.getAppgateway_payroll()+"/api/employeeChapter6A/saveAndCorrectTaxEmployeeChapter6A";

		SingleResponseModel res =null;
		HttpEntity<Chapter6AWrapper> request1 = new HttpEntity<Chapter6AWrapper>(chapter6AWrapper, headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlsave, HttpMethod.POST, request1, SingleResponseModel.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println(response.getStatusCode());
			model.addAttribute("result", "LOG_ERROR");
			return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";
		}

		model.addAttribute("result",res.getMessage());

		Chapter6AWrapper chapter6A=null;
		String urlchapter6A = appgateway.getAppgateway_payroll()+"/api/employeeChapter6A/getTaxEmployeeChapter6AList/"+login.getEmplid();
		ResponseEntity<Chapter6AWrapper> response4 = restTemplate.exchange(urlchapter6A, HttpMethod.GET, request2, Chapter6AWrapper.class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			System.out.println("STATUS OK");
			chapter6A = response4.getBody();
		}
		model.addAttribute("chapter6A",chapter6A);
		return "fragments/taxdeclaration/employeetaxdeclaration :: employeetaxdeclaration";

		/***********************DATABASE TRANSACTIONS END FOR DATA SAVE or SUBMIT**********************/
	}


}
