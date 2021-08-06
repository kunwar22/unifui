package in.co.srdt.unif.controllers.user.corehr;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import in.co.srdt.unif.enums.FormType;
import in.co.srdt.unif.enums.Identifying;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payroll.CalendarPeriod;
import in.co.srdt.unif.model.payroll.FinancialYearLOV;
import in.co.srdt.unif.model.payroll.PayGroups;
import in.co.srdt.unif.model.personmanagement.BiographicalInfo;
import in.co.srdt.unif.model.personmanagement.MaritalDetails;
import in.co.srdt.unif.model.personmanagement.PersonalRecordManageMaster;
import in.co.srdt.unif.model.search.BUsearchresult;
import in.co.srdt.unif.model.user.absence.CommonDescription;
import in.co.srdt.unif.model.user.corehr.DocumentView;
import in.co.srdt.unif.model.user.corehr.EmploymentInfo;
import in.co.srdt.unif.model.user.corehr.PersonalDetails;
import in.co.srdt.unif.model.user.corehr.PublicInfo;
import in.co.srdt.unif.model.user.corehr.UserDocument;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/user")
public class UserHomeController {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;
	
	

	
	/*public UserHomeController() {
	}
	
	UserHomeController(HttpHeaders headers,RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}*/
	
	
	
	
	
	@RequestMapping("/personalDetails")
	public String personDetails(Model model, HttpServletRequest req) {
		
		///System.out.println("Hello Personal Details");
		CommonLOV[] countryDetailsObj=null;
		CommonLOV[] gender=null;
		CommonLOV[] title=null;
		CommonLOV[] maritalstatus=null;
		CommonLOV[] religion=null;
		
		PersonalDetails personalDetails=null;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		Login login = (Login) req.getSession().getAttribute("login");
		
		String pId=login.getEmplid();
		
		PersonalRecordManageMaster personallrecords=null;
		
		String urlgetpersonallrecord = appgateway.getAppgateway() + "/PersonData/PersonalRecord/getPersonalRecordByPersonNo/"+login.getEmplid();
		ResponseEntity<PersonalRecordManageMaster> response35 = restTemplate.exchange(urlgetpersonallrecord, HttpMethod.GET, request, PersonalRecordManageMaster.class);
		if (response35.getStatusCode() == HttpStatus.OK) {
			personallrecords = response35.getBody();
		}
		
		
		String urlpersonaldetails = appgateway.getAppgateway()+"/PersionDetailsUser/getPersionDetailsByPersonNumber/"+pId;
		ResponseEntity<PersonalDetails> responsePersonalDetails = restTemplate.exchange(urlpersonaldetails, HttpMethod.GET, request, PersonalDetails.class);
		if(responsePersonalDetails.getStatusCode() == HttpStatus.OK) {
			personalDetails = responsePersonalDetails.getBody();
		} 
		
		String urlcountrydetails = appgateway.getAppgateway()+"/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlcountrydetails, HttpMethod.GET, request, CommonLOV[].class);
		if(response.getStatusCode() == HttpStatus.OK) {
			countryDetailsObj = response.getBody();
		} 
		
		
		String urlgender = appgateway.getAppgateway()+"/General/loadGender";
		ResponseEntity<CommonLOV[]> response8 = restTemplate.exchange(urlgender, HttpMethod.GET, request, CommonLOV[].class);
		if(response8.getStatusCode() == HttpStatus.OK) {
			gender = response8.getBody();
		} 
		
		String urltitle = appgateway.getAppgateway()+"/General/loadTitle";
		ResponseEntity<CommonLOV[]> response9 = restTemplate.exchange(urltitle, HttpMethod.GET, request, CommonLOV[].class);
		if(response9.getStatusCode() == HttpStatus.OK) {
			title = response9.getBody();
		} 
		
		String urlmaritalstat = appgateway.getAppgateway()+"/General/loadMaritalStatus";
		ResponseEntity<CommonLOV[]> response14 = restTemplate.exchange(urlmaritalstat, HttpMethod.GET, request, CommonLOV[].class);
		if(response14.getStatusCode() == HttpStatus.OK) {
			maritalstatus = response14.getBody();
		}
		
		String urlrel = appgateway.getAppgateway()+"/Religion/getAllReligionLOV";
		ResponseEntity<CommonLOV[]> response17= restTemplate.exchange(urlrel, HttpMethod.GET, request, CommonLOV[].class);
		if(response17.getStatusCode() == HttpStatus.OK) {
			religion = response17.getBody();
		}
		
		if(personalDetails.getReligionasofdate()!=null) {
		  String religionchangedate=personalDetails.getReligionasofdate();
		  religionchangedate=religionchangedate.substring(0,10);
		  personalDetails.setReligionasofdate(religionchangedate);
		}
		
		CommonLOV[] country = null;
		String urlcountry = appgateway.getAppgateway() + "/Country/getAllCountry";
		ResponseEntity<CommonLOV[]> response3 = restTemplate.exchange(urlcountry, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response3.getStatusCode() == HttpStatus.OK) {
			country = response3.getBody();
		}
		
		CommonLOV[] state = null;
		String urlstate = appgateway.getAppgateway() + "/State/getAllStateByCountry/1";
		ResponseEntity<CommonLOV[]> response4 = restTemplate.exchange(urlstate, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response4.getStatusCode() == HttpStatus.OK) {
			state = response4.getBody();
		}
		
		CommonLOV[] addresstype = null;
		String urladdresstype = appgateway.getAppgateway() + "/General/loadAddressTypeObject";
		ResponseEntity<CommonLOV[]> response5 = restTemplate.exchange(urladdresstype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response5.getStatusCode() == HttpStatus.OK) {
			addresstype = response5.getBody();
		}
		
		CommonLOV[] phone_countrycode=null;
		String urlcntrycode = appgateway.getAppgateway() + "/Country/getAllCountryPhoneCode";
		ResponseEntity<CommonLOV[]> response6 = restTemplate.exchange(urlcntrycode, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response6.getStatusCode() == HttpStatus.OK) {
			phone_countrycode = response6.getBody();
		}

		CommonLOV[] phonetype=null;
		String urlphonetype = appgateway.getAppgateway() + "/General/loadPhoneType";
		ResponseEntity<CommonLOV[]> response7 = restTemplate.exchange(urlphonetype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response7.getStatusCode() == HttpStatus.OK) {
			phonetype = response7.getBody();
		}
		
		CommonLOV[] emailtype=null;
		String urlemailtype = appgateway.getAppgateway() + "/General/loadEmailType";
		ResponseEntity<CommonLOV[]> response10 = restTemplate.exchange(urlemailtype, HttpMethod.GET, request,
				CommonLOV[].class);
		if (response10.getStatusCode() == HttpStatus.OK) {
			emailtype = response10.getBody();
		}


		model.addAttribute("personalDetails", personalDetails);
		model.addAttribute("religion", religion);
		model.addAttribute("maritalstatus", maritalstatus);
		model.addAttribute("title", title);
		model.addAttribute("gender", gender);
		model.addAttribute("CountryDetails", countryDetailsObj);
		model.addAttribute("yesorno", Identifying.values());
		model.addAttribute("status", Status.values());
		model.addAttribute("country", country);
		model.addAttribute("state", state);
		model.addAttribute("addresstype", addresstype);
		model.addAttribute("countrycode", phone_countrycode);
		model.addAttribute("phonetype", phonetype);
		model.addAttribute("emailtype", emailtype);
		model.addAttribute("personallrecords",personallrecords);
		
		return "fragments/user/corehr/personalDetails :: personalDetails";
	}
	

	
	@RequestMapping(value = "/savepersonaledit", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String savePersonalEdit(MaritalDetails maritaldetails, HttpServletRequest req, Model model)
	{
		//System.out.println("Inside Personal Details Controller");
		//System.out.println("Personal Details: "+ maritaldetails.toString());
		
		Login login = (Login)req.getSession().getAttribute("login");
		
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			maritaldetails.setCreatedby(login.getEmplid());
			maritaldetails.setUpdatedby(login.getEmplid());
			
			HttpEntity<MaritalDetails> request = new HttpEntity<MaritalDetails>(maritaldetails, headers);
			
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(appgateway.getAppgateway()+"/PersonManagement/correctMaritalDetails",HttpMethod.PUT,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			//System.out.println(res.getMessage());
			}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to save edit.");
		}
		return personDetails(model,req);
	}


	
	
	
	@RequestMapping("/employmentInfo")
	public String employmentInfo(Model model, HttpServletRequest req) {
		
		///System.out.println("Hello Employment Info");
		Login login = (Login) req.getSession().getAttribute("login");
		String pId=login.getEmplid();

		EmploymentInfo employmentInfo=null;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		
		
		String urlemploymentInfo = appgateway.getAppgateway()+"/EmployementInfo/getEmployementInfoByPersonNumber/"+pId;
		ResponseEntity<EmploymentInfo> responseemploymentInfo = restTemplate.exchange(urlemploymentInfo, HttpMethod.GET, request, EmploymentInfo.class);
		if(responseemploymentInfo.getStatusCode() == HttpStatus.OK) {
			employmentInfo = responseemploymentInfo.getBody();
		} 
		if(employmentInfo.getMarriagestatuschangedate()!=null) {
		String marriagestatdate=employmentInfo.getMarriagestatuschangedate();
		marriagestatdate=marriagestatdate.substring(0,10);
		employmentInfo.setMarriagestatuschangedate(marriagestatdate);
		}
		model.addAttribute("employmentInfo", employmentInfo);
		
		return "fragments/user/corehr/employmentInfo :: employmentInfo";
	}
	
	
	
	
	
	@RequestMapping("/publicInfo")
	public String publicInfo(Model model, HttpServletRequest req) {
		
		//System.out.println("Hello Public Info");
		
		Login login = (Login) req.getSession().getAttribute("login");
		String pId=login.getEmplid();

		PublicInfo publicInfo=null;
		
		headers.setContentType(MediaType.APPLICATION_JSON); 
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		String urlpublic = appgateway.getAppgateway()+"/PublicInfo/getPublicInfoByPersonNumber/"+pId;
		ResponseEntity<PublicInfo> responsePublicInfo = restTemplate.exchange(urlpublic, HttpMethod.GET, request, PublicInfo.class);
		if(responsePublicInfo.getStatusCode() == HttpStatus.OK) {
			publicInfo = responsePublicInfo.getBody();
		} 
		if(publicInfo.getAddressline1()!=null) {
		String completeAddr=publicInfo.getAddressline1()+", "+publicInfo.getAddressline2()+", "+publicInfo.getAddressline3()+", "+publicInfo.getCitytown()+" ("+
		publicInfo.getPincode()+"). "+publicInfo.getStatename()+", "+publicInfo.getCountryname()+".";
		publicInfo.setCompleteaddress(completeAddr);
		//System.out.println("Address :: "+completeAddr);
		}
		
		
		
		
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, HH:mm");  
	    String time = myDateObj.format(myFormatObj);  
	    //System.out.println("Time: " + time);
	    
	    model.addAttribute("time", time);
	    model.addAttribute("public", publicInfo);
	    
		return "fragments/user/corehr/publicinfo :: publicinfo";
	}
	
	@RequestMapping("/contactInfo")
	public String contactInfo(Model model) {
		
		//System.out.println("Hello Contact Info");
		
		return "fragments/user/corehr/contactInfo :: contactInfo";
	}
	
	@RequestMapping("/docrecord")
	public String docRecord(Model model,HttpServletRequest req) {
		model.addAttribute("res", "");
		CommonLOV[] doccat=null;
		Login login=(Login)req.getSession().getAttribute("login");
		
		//System.out.println("Hello "+login.getEmplid()+", Welcome in Document Record");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		
		String urldoccat = appgateway.getAppgateway()+"/DocumentCategory/getAllDocumentCategory";
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urldoccat, HttpMethod.GET, request, CommonLOV[].class);
		if(response.getStatusCode() == HttpStatus.OK) {
			doccat = response.getBody();
		} 
		
		String pid=login.getEmplid();
		DocumentView[] docs=null;
		String urlgetdoc = appgateway.getAppgateway()+"/UserDocuments/getUserDocumentsViewById/"+pid;
		ResponseEntity<DocumentView[]> response1 = restTemplate.exchange(urlgetdoc, HttpMethod.GET, request, DocumentView[].class);
		if(response1.getStatusCode() == HttpStatus.OK) {
			docs = response1.getBody();
		}

		CommonDescription pygrp= null;
		String urlpygrp= appgateway.getAppgateway()+"/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/paygroup";
		ResponseEntity<CommonDescription> pygrpresponse = restTemplate.exchange(urlpygrp, HttpMethod.GET, request, CommonDescription.class);
		if(pygrpresponse.getStatusCode() == HttpStatus.OK) {
			pygrp = pygrpresponse.getBody();
		}

		CalendarPeriod[] calcode = null;
		String urlcalcode= appgateway.getAppgateway_payroll()+"/api/paycalendar/getFinalizeCalendarsByPaygroupid/"+pygrp.getDescription();
		ResponseEntity<CalendarPeriod[]> calcoderesponse = restTemplate.exchange(urlcalcode, HttpMethod.GET, request, CalendarPeriod[].class);
		if(calcoderesponse.getStatusCode() == HttpStatus.OK) {
			calcode = calcoderesponse.getBody();
		}

		/* LOADING OF BUSINESS UNITS STARTS */
		String businessUnitPayload = "{\r\n"
				+ "  \"code\": \"\",\r\n"
				+ "  \"name\": \"\",\r\n"
				+ "  \"status\": \"Active\"\r\n"
				+ "}";
		String urlbusunit=appgateway.getAppgateway()+"/BusinessUnit/BusinessUnitSearchList";
		BUsearchresult[] busunit=null;
		HttpEntity<String> busunitreq = new HttpEntity<String>(businessUnitPayload, headers);
		ResponseEntity<BUsearchresult[]> busunitres= restTemplate.exchange(urlbusunit, HttpMethod.POST, busunitreq, BUsearchresult[].class);
		if(busunitres.getStatusCode() == HttpStatus.OK){
				busunit = busunitres.getBody();
			}
		else{
			System.out.println("Request Failed");
		}
		model.addAttribute("busunit",busunit);
		/* LOADING OF BUSINESS UNITS ENDS */


		/* LOADING OF PAY GROUP STARTS */
		String urlpaygroup=appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
		PayGroups[] pgrp=null;
		HttpEntity<String> pgreq = new HttpEntity<String>(headers);
		ResponseEntity<PayGroups[]> pgres= restTemplate.exchange(urlpaygroup, HttpMethod.GET, pgreq, PayGroups[].class);
		if(pgres.getStatusCode() == HttpStatus.OK){
			pgrp = pgres.getBody();
		}
		else{
			System.out.println("Request Failed");
		}
		model.addAttribute("pgrp",pgrp);
		
		FinancialYearLOV[] calperiod = null;
		String urlCalendars = appgateway.getAppgateway_payroll() + "/api/paycalendar/getfinancialyears";
		HttpEntity<String> calreq = new HttpEntity<String>(headers);
		
		ResponseEntity<FinancialYearLOV[]> calresponse = restTemplate.exchange(urlCalendars,
				HttpMethod.GET, calreq, FinancialYearLOV[].class);
		if (calresponse.getStatusCode() == HttpStatus.OK) {
			calperiod = calresponse.getBody();
		}
		model.addAttribute("calperiod", calperiod);
		
		
		LinkedHashMap pannumres = new LinkedHashMap();
		
		String urlpannobyperno = appgateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/pan";
		HttpEntity<String> panreq = new HttpEntity<String>(headers);
		
		ResponseEntity<LinkedHashMap> panresponse = restTemplate.exchange(urlpannobyperno,
				HttpMethod.GET, panreq, LinkedHashMap.class);
		if (panresponse.getStatusCode() == HttpStatus.OK) {
			pannumres = panresponse.getBody();
		}
		
//		System.out.println("PAN response===> "+pannumres.get("description"));
		model.addAttribute("panno", pannumres.get("description"));
		model.addAttribute("formtype", FormType.values());
		

		model.addAttribute("calcode", calcode);
		model.addAttribute("loginid",login.getEmplid());
		model.addAttribute("doccat", doccat);
		model.addAttribute("docs", docs);
		return "fragments/user/corehr/docRecord :: docRecord";
	}
	//consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE headers = "content-type!=multipart/form-data"
	
	
	
	@RequestMapping(value = "/savedocument", method = RequestMethod.POST)
	public String saveDocs(@RequestParam("file") MultipartFile file, UserDocument userDocument, HttpServletRequest request, Model model) {
		
		//System.out.println("Inside Document Save Controller");
		Login login=(Login)request.getSession().getAttribute("login");

		String location = userDocument.getFilepath();
		String filePath = new File("").getAbsolutePath()+File.separator+userDocument.getFilepath();
		String storePath=location;
		
		if(!file.isEmpty()) {
		//CREATE DIRECTORY IF NOT EXISTS
		File dir = new File(filePath);
		if( !dir.exists() )
		{
			dir.mkdirs();
		}
		
		//WRITE FILES TO DIRECTORY AND ENTRY INTO THE DATABASE

			try
			{
				
				
				String empl=login.getEmplid();
				
				
				String extension =  FilenameUtils.getExtension( file.getOriginalFilename() );
				
				String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"+"'"+empl+"."+extension+"'").format(new Date());
				
				File target = new File(filePath+File.separator+generatedFileName);
				int readByteCount = 0;
				byte[] buffer = new byte[20480];

				BufferedInputStream in= new BufferedInputStream(file.getInputStream());
				FileOutputStream out = new FileOutputStream(target);
			    while( (readByteCount = in.read(buffer)) != -1)
			    {
			        out.write(buffer, 0, readByteCount);
			    }
				
				storePath += "/"+generatedFileName;
				
					
				userDocument.setFilepath(storePath);
					
					
		     
			}
			catch(IOException e)
			{
				
				e.printStackTrace();
				model.addAttribute("res", "IOEXCEPTION");
				return docRecord(model,request);
			}
			catch(IllegalArgumentException e)
			{
			
				e.printStackTrace();
				model.addAttribute("res", "ILLEGALARG");
				return docRecord(model,request);
			}
			catch(Exception e)
			{
				
				e.printStackTrace();
				model.addAttribute("res", "WRITE_ERROR");
				return docRecord(model,request);
			}
			
			}else {
			userDocument.setFilepath(null);
			}
			
			//System.out.println("DATA :::: "+ userDocument.toString());
			String url = appgateway.getAppgateway() + "/UserDocuments/saveUserDocuments";
		
			
			SingleResponseModel msg = null;
			HttpEntity<UserDocument> request1 = new HttpEntity<UserDocument>(userDocument,headers);
	        ResponseEntity<SingleResponseModel> response= restTemplate.exchange(url ,HttpMethod.POST,request1, SingleResponseModel.class);
	        if(response.getStatusCode() == HttpStatus.OK) {
	            msg = response.getBody();
	        } else {
	          //  System.out.println(response.getStatusCode());
	            return "LOG_ERROR";
	        }
	        
	      //  System.out.println(msg.getMessage());
	        //System.out.println(msg.getStatus());
	        storePath = location;		
	        
	        model.addAttribute("res", "Success");
		return docRecord(model,request);
	}


	@ResponseBody
	@RequestMapping(value = "/propicupload", method = RequestMethod.POST)
	public String chapterVIAsubmit(@RequestParam("propic")MultipartFile profilepic, BiographicalInfo bioinfo, HttpServletRequest request, Model model) {

		/*************************VALIDATION PART START****************************/
		Login login = (Login) request.getSession().getAttribute("login");

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request2 = new HttpEntity<String>(headers);

		/*************************VALIDATION PART END****************************/

		/*************************FILE ATTACHMENT PART START ONLY EXECUTE WHEN FILE SELECTED****************************/

			if(profilepic!=null) {
				System.out.println("NOT NULL PROPIC");
				if(!profilepic.isEmpty()) {
					System.out.println("Inside File Create PROPIC");

					String location = "/EmployeeDocs/"+login.getEmplid()+"/ProfilePicture";
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

						String extension = FilenameUtils.getExtension(profilepic.getOriginalFilename());
						String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss" + "'" + empl + "." + extension + "'").format(new Date());


						File target = new File(filePath + File.separator + generatedFileName);
						int readByteCount = 0;
						byte[] buffer = new byte[20480];

						BufferedInputStream in = new BufferedInputStream(profilepic.getInputStream());
						@SuppressWarnings("resource")
						FileOutputStream out = new FileOutputStream(target);
						while ((readByteCount = in.read(buffer)) != -1) {
							out.write(buffer, 0, readByteCount);
						}

						storePath += "/" + generatedFileName;
						System.out.println(storePath);
						bioinfo.setPhotopathname(storePath);
					}
					catch (IOException e) {
						System.out.println("IOException");
						e.printStackTrace();
						//model.addAttribute("result", "IOEXCEPTION");
						return "IOEXCEPTION";
					}
					catch (IllegalArgumentException e) {
						System.out.println("IllegalArgumentException");
						e.printStackTrace();
						//model.addAttribute("result", "ILLEGALARG");
						return "ILLEGALARG";
					}
					catch (Exception e) {
						System.out.println("Exception");
						e.printStackTrace();
						//model.addAttribute("result", "WRITE_ERROR");
						return "WRITE_ERROR";
					}
				}
			}

		/*************************FILE ATTACHMENT PART END****************************/

		/***********************DATABASE TRANSACTIONS START FOR DATA SAVE or SUBMIT**********************/

		System.out.println("BIODETAILS :: "+bioinfo.toString());

		String urlsave=appgateway.getAppgateway()+"/PersonManagement/correctBiographicalinfo";

		SingleResponseModel res =null;
		HttpEntity<BiographicalInfo> request1 = new HttpEntity<BiographicalInfo>(bioinfo, headers);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlsave, HttpMethod.PUT, request1, SingleResponseModel.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println(response.getStatusCode());
			//model.addAttribute("result", "LOG_ERROR");
			return "LOG_ERROR";
		}

		//model.addAttribute("result",res.getStatus());

		return res.getStatus();

		/***********************DATABASE TRANSACTIONS END FOR DATA SAVE or SUBMIT**********************/
	}





}
