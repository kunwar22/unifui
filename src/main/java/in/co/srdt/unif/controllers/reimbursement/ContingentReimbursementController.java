package in.co.srdt.unif.controllers.reimbursement;

import java.io.BufferedInputStream;
//import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import in.co.srdt.unif.model.rembcontingent.AdhocListModel;
import in.co.srdt.unif.model.rembcontingent.AdhocModel;
import in.co.srdt.unif.model.rembcontingent.AdhocSearchModel;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/reimbursement")
public class ContingentReimbursementController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders httpHeaders;
	
	@Autowired
	ApplicationGateway apiGateway;
	
	//@Autowired(required=true)
	//private EligibilityReimbursement eligibilityreimb;
	
	@Autowired
	SmartValidator validator;


	@Autowired(required = true)
	private SendMail sendnotimail;


	/* Controllers for adhocLocal Remb - Snigdhaa Vaish*/

/****** OPEN NEW PAGE ******/
    @RequestMapping("/adhocLocal")
    public String adhoclocalReimbursement(HttpServletRequest request,Model model){
    	String url;
    	AdhocModel ad = new AdhocModel();
    	model.addAttribute("adhocdata",ad);
    	
    	Login login = (Login) request.getSession().getAttribute("login");
    	PersonInformation resp = new PersonInformation();
    	url = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> respons = restTemplate.exchange(url,HttpMethod.GET, req,PersonInformation.class);
		resp=respons.getBody();
    	model.addAttribute("personInfo",resp);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response1 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			comdes = response1.getBody();
		}
		model.addAttribute("propic",comdes);


		String RID="6";
    	//String ceiling=eligibilityreimb.getCeilinglimit(RID,login.getEmplid());
    	//model.addAttribute("empent",ceiling);
    	
    	CommonLOV[] lovstation= null;    	
    	CommonLOV[] lovtrans= null;
    	CommonLOV[] lovvehicle= null; 
    	String urlstation=apiGateway.getAppgateway()+"/General/loadNearestStationLOV";
    	String urltrans=apiGateway.getAppgateway()+"/General/loadTransportModeLOV";
    	String urlvehicle=apiGateway.getAppgateway()+"/General/loadVehicleUsedLOV";
    	ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlstation,HttpMethod.GET, req,CommonLOV[].class);
    	lovstation=response.getBody();
    	model.addAttribute("station",lovstation);
    	response = restTemplate.exchange(urltrans,HttpMethod.GET, req,CommonLOV[].class);
    	lovtrans=response.getBody();
    	//System.out.println(lovtrans[0].getId());
    	model.addAttribute("trans",lovtrans);
    	response = restTemplate.exchange(urlvehicle,HttpMethod.GET, req,CommonLOV[].class);
    	lovvehicle=response.getBody();
    	model.addAttribute("lovvehicle",lovvehicle);	
    	
    	model.addAttribute("stat"," ");
    	
    	return "forms/reimbursement/contingent/adhocLocalClaim :: adhocLocalClaim";
    }
    
/****** SAVE/UPDATE PAGE ******/
    @RequestMapping(value = "/adhocSaveBtn/{mode}", method = RequestMethod.POST)
    public String adhocReimbursementSubmit(@PathVariable String mode,@ModelAttribute AdhocModel adhoc, @RequestParam("file") MultipartFile[] file, HttpServletRequest request, Model model, BindingResult bindingResult) throws IOException, MessagingException {
    	Login login=(Login)request.getSession().getAttribute("login");
		String RID="6"; 
    	//String ceiling=eligibilityreimb.getCeilinglimit(RID,login.getEmplid());
    	//model.addAttribute("empent",ceiling);

    	//System.out.println("/****** SAVE/UPDATE PAGE ******/");
    	
    	PersonInformation resp = new PersonInformation();
    	String urlheader = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> respons = restTemplate.exchange(urlheader,HttpMethod.GET, req,PersonInformation.class);
		resp=respons.getBody();
    	model.addAttribute("personInfo",resp);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response1 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			comdes = response1.getBody();
		}
		model.addAttribute("propic",comdes);
    	
    	CommonLOV[] lovstation= null;    	
    	CommonLOV[] lovtrans= null;
    	CommonLOV[] lovvehicle= null; 
    	String urlstation=apiGateway.getAppgateway()+"/General/loadNearestStationLOV";
    	String urltrans=apiGateway.getAppgateway()+"/General/loadTransportModeLOV";
    	String urlvehicle=apiGateway.getAppgateway()+"/General/loadVehicleUsedLOV";
    	ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlstation,HttpMethod.GET, req,CommonLOV[].class);		
    	lovstation=response.getBody();
    	model.addAttribute("station",lovstation);
    	response = restTemplate.exchange(urltrans,HttpMethod.GET, req,CommonLOV[].class);		
    	lovtrans=response.getBody();
    	model.addAttribute("trans",lovtrans);
    	response = restTemplate.exchange(urlvehicle,HttpMethod.GET, req,CommonLOV[].class);		
    	lovvehicle=response.getBody();
    	model.addAttribute("lovvehicle",lovvehicle);
    	
    	//System.out.println("adhoc.toString(): "+adhoc.toString());
    	model.addAttribute("adhocdata", adhoc);
		
    	//System.out.println("dataaaa--- "+adhoc.getTravelbillclaimdetails());
    	List<AdhocListModel> adl= new ArrayList<>();
    	adl=adhoc.getTravelbillclaimdetails();
    	for(int i=0;i<adl.size();i++) {
    		validator.validate(adl.get(i), bindingResult);  
    	}
    	
    	model.addAttribute("stat",mode);
    	model.addAttribute("mode","edit");
		model.addAttribute("bindingResult", bindingResult);
		model.addAttribute("result","Saving...");
		
		if(bindingResult.hasErrors()) {
			return "forms/reimbursement/contingent/adhocLocalClaim :: adhocLocalClaim";
		}    	
		
		//System.out.println("files.length()::::::::::: "+file.length);
		if(file.length>0) {
			for(int i=0;i<file.length;i++) {
				String location = adl.get(i).getAttachments();
				String filePath = new File("").getAbsolutePath()+File.separator+location;
				String storePath=location;
				
				//CREATE DIRECTORY IF NOT EXISTS
				File dir = new File(filePath);
				if( !dir.exists() )
				{
					dir.mkdirs();
				}
				
				//WRITE FILES TO DIRECTORY AND ENTRY INTO THE DATABASE
				try
				{
					//CREATING UNIQUE FILENAME
					String empl=login.getEmplid();
					
					String extension =  FilenameUtils.getExtension( file[i].getOriginalFilename() );
					String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"+"'"+empl+"_"+(i+1)+"."+extension+"'").format(new Date());
					
					File target = new File(filePath+File.separator+generatedFileName);
					int readByteCount = 0;
					byte[] buffer = new byte[4096];
					
					BufferedInputStream in= new BufferedInputStream(file[i].getInputStream());
					FileOutputStream out = new FileOutputStream(target);
				    while( (readByteCount = in.read(buffer)) != -1)
				    {
				    	out.write(buffer, 0, readByteCount);
				    }
					
					storePath += "/"+generatedFileName;
					
					String payLoad = "";
					String url = "";
					//System.out.println("adl.get("+i+").getFilepres()::::::::::"+adl.get(i).getFilepres());
					if(adl.get(i).getFilepres().equals("Y")) {
						//System.out.println("File in i========== "+i);
						adl.get(i).setAttachments(storePath);
					}
					else {
						adl.get(i).setAttachments(adl.get(i).getAttachhidden());
					}
			        storePath = location;
				}
				catch(Exception e)
				{
					//System.out.println("FILE ERRORRRRRR");
					e.printStackTrace();
					model.addAttribute("result","WRITE_ERROR");
					return "forms/reimbursement/contingent/adhocLocalClaim :: adhocLocalClaim";
				}
				
			}
		}
		String url;
		if(mode.equals("Save")) {
			url = apiGateway.getAppgatewaypyrl_sandhya()+"/api/contigentclaim/savecontigent";
		}else if(mode.equals("Save&Submit")) {
			url = apiGateway.getAppgatewaypyrl_sandhya()+"/api/contigentclaim/savecontigent";
		}
		else {
			url = apiGateway.getAppgatewaypyrl_sandhya()+"/api/contigentclaim/correctcontigent";
		}
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    	
    	//System.out.println("Before Saving-------"+adhoc.toString());
		
    	HttpEntity<AdhocModel> reques = new HttpEntity<AdhocModel>(adhoc, httpHeaders);
		ResponseEntity<AdhocModel> response3;
		if(mode.equals("Save")) {
			response3 = restTemplate.exchange(url, HttpMethod.POST, reques,AdhocModel.class);
			System.out.println("Save");
		}
		else if(mode.equals("Submit")){
			adhoc.setStatus("Submitted");
			response3 = restTemplate.exchange(url, HttpMethod.PUT, reques,AdhocModel.class);
			System.out.println("Submit");
		}
		else if(mode.equals("Save&Submit")){
			adhoc.setStatus("Submitted");
			response3 = restTemplate.exchange(url, HttpMethod.POST, reques,AdhocModel.class);
			System.out.println("Save&Submit");
		}
		else {
			response3 = restTemplate.exchange(url, HttpMethod.PUT, reques,AdhocModel.class);
		}
		
		if(response3.getStatusCode() == HttpStatus.OK) {
			System.out.println("adhoc "+adhoc.toString());
			adhoc=response3.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response3.getStatusCode());
		}



		//System.out.println("After Response-----"+adhoc.toString());
		
    	model.addAttribute("result",adhoc.getSubmitStatus());

		if(adhoc.getSubmitStatus().equals("Success") && mode.equals("Submit")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = apiGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+adhoc.getClaimid()+"/"+login.getEmplid();
			System.out.println("URL APPROVER DATA :: "+getapprovalperson);
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emprequest = new HttpEntity<String>(httpHeaders);
			ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, emprequest, ApproverDetails.class);
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalResp.getPersonname(),login.getEmplid(),"Contingent Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

		return "forms/reimbursement/contingent/adhocLocalClaim :: adhocLocalClaim";
		
    }
    
/****** OPEN SEARCH PAGE ******/
    @RequestMapping("/adhoclocalSearch")
    public String adhoclocalReimbursementSearch(HttpServletRequest request,Model model){
    	Login login = (Login) request.getSession().getAttribute("login");
		PersonInformation resp =new PersonInformation();
    	String url = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> response = restTemplate.exchange(url,HttpMethod.GET, req,PersonInformation.class);
		resp=response.getBody();
		if(response.getStatusCode() == HttpStatus.OK) {
			resp = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		model.addAttribute("personInfo",resp);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response1 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			comdes = response1.getBody();
		}
		model.addAttribute("propic",comdes);

        return "forms/reimbursement/contingent/adhocLocalSearch :: adhocLocalSearch";
    }
    
/****** LOAD DATA ON SEARCH PAGE ******/
    @ResponseBody
    @RequestMapping(value = "/getAdhocSearchData", method = RequestMethod.GET)
	public AdhocSearchModel[] adhocsearch(HttpServletRequest request,Model model)
	{	
    	Login login = (Login) request.getSession().getAttribute("login");
    	AdhocSearchModel[] res = null;    	
    	String url = apiGateway.getAppgatewaypyrl_sandhya()+"/api/contigentclaim/getcontigentbypersonnbr/"+login.getEmplid();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<AdhocSearchModel[]> response = restTemplate.exchange(url,HttpMethod.GET, req,AdhocSearchModel[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		return res;
	}
    
/****** OPEN PAGE IN EDIT MODE ******/        
    @RequestMapping(value="/viewadhocdata/{claimid}/{mode}", method = RequestMethod.GET)
	public String viewAdhocData(@PathVariable("claimid") String claimid,@PathVariable("mode")String mode, Model model,HttpServletRequest reques)
	{
     	AdhocModel res = new AdhocModel();
    	

     	Login login = (Login) reques.getSession().getAttribute("login");
		PersonInformation resp = new PersonInformation();
    	String url1 = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requ = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> respons = restTemplate.exchange(url1,HttpMethod.GET, requ,PersonInformation.class);
		resp=respons.getBody();
		model.addAttribute("personInfo",resp);

		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response2 = restTemplate.exchange(urlpropic, HttpMethod.GET, requ, CommonDescription.class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			comdes = response2.getBody();
		}
		model.addAttribute("propic",comdes);
   
    	String url = apiGateway.getAppgatewaypyrl_sandhya()+"/api/contigentclaim/getcontigentclaimsbyid/"+claimid;
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<AdhocModel> response = restTemplate.exchange(url,HttpMethod.GET, req,AdhocModel.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
    	CommonLOV[] lovstation= null;    	
    	CommonLOV[] lovtrans= null;
    	CommonLOV[] lovvehicle= null; 
    	String urlstation=apiGateway.getAppgateway()+"/General/loadNearestStationLOV";
    	String urltrans=apiGateway.getAppgateway()+"/General/loadTransportModeLOV";
    	String urlvehicle=apiGateway.getAppgateway()+"/General/loadVehicleUsedLOV";
    	ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlstation,HttpMethod.GET, req,CommonLOV[].class);		
    	lovstation=response1.getBody();
    	model.addAttribute("station",lovstation);
    	response1 = restTemplate.exchange(urltrans,HttpMethod.GET, req,CommonLOV[].class);		
    	lovtrans=response1.getBody();
    	model.addAttribute("trans",lovtrans);
    	response1 = restTemplate.exchange(urlvehicle,HttpMethod.GET, req,CommonLOV[].class);		
    	lovvehicle=response1.getBody();
    	model.addAttribute("lovvehicle",lovvehicle);
    	
		model.addAttribute("adhocdata", res);
		model.addAttribute("mode", mode);
		
		
		////*****************Updated by Ravi for view mode Approval--START****************\\\\\\\\\\\\\\\\\\\\\\\\\\
		String RID="6"; 
		ApprovalChildModel[] apr = null;
		String aprroveurl = apiGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + claimid + "/" + login.getEmplid();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(httpHeaders);
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
		
		
		return "forms/reimbursement/contingent/adhocLocalClaim :: adhocLocalClaim";
	}



	@RequestMapping(value="/viewadhocdataApproval/{claimid}/{mode}/{status}", method = RequestMethod.GET)
	public String viewadhocdataApprovalData(@PathVariable("claimid") String claimid,@PathVariable("status") String status, @PathVariable("mode")String mode, Model model,HttpServletRequest reques)
	{
		AdhocModel res = new AdhocModel();
		ApprovalChildModel[] apr = null;
		String RID = "6";

		Login login = (Login) reques.getSession().getAttribute("login");
		PersonInformation resp = new PersonInformation();
//    	String url1 = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
//    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<String> requ = new HttpEntity<>(httpHeaders);
//		ResponseEntity<PersonInformation> respons = restTemplate.exchange(url1,HttpMethod.GET, requ,PersonInformation.class);
//		resp=respons.getBody();
//		model.addAttribute("personInfo",resp);


		String urlNotify = apiGateway.getAppgateway()+"/Notification/getApprovalDataByPersonnoStatus/"+RID+"/" + claimid+"/"+login.getEmplid()+"/"+status;
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(httpHeaders);

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


		//String ceiling = eligibilityreimb.getCeilinglimit(RID, notify.getSubmittedbypersonno());
		//model.addAttribute("empent", ceiling);
		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(httpHeaders);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");

		}

		model.addAttribute("NotifynavBar", notify);



		String url = apiGateway.getAppgatewaypyrl_sandhya()+"/api/contigentclaim/getcontigentclaimsbyid/"+claimid;
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<AdhocModel> response = restTemplate.exchange(url,HttpMethod.GET, req,AdhocModel.class);

		if(response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		CommonLOV[] lovstation= null;
		CommonLOV[] lovtrans= null;
		CommonLOV[] lovvehicle= null;
		String urlstation=apiGateway.getAppgateway()+"/General/loadNearestStationLOV";
		String urltrans=apiGateway.getAppgateway()+"/General/loadTransportModeLOV";
		String urlvehicle=apiGateway.getAppgateway()+"/General/loadVehicleUsedLOV";
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlstation,HttpMethod.GET, req,CommonLOV[].class);
		lovstation=response1.getBody();
		model.addAttribute("station",lovstation);
		response1 = restTemplate.exchange(urltrans,HttpMethod.GET, req,CommonLOV[].class);
		lovtrans=response1.getBody();
		model.addAttribute("trans",lovtrans);
		response1 = restTemplate.exchange(urlvehicle,HttpMethod.GET, req,CommonLOV[].class);
		lovvehicle=response1.getBody();
		model.addAttribute("lovvehicle",lovvehicle);

		model.addAttribute("adhocdata", res);
		model.addAttribute("mode", mode);


		////*****************Updated by Ravi for view mode Approval--START****************\\\\\\\\\\\\\\\\\\\\\\\\\\
		PersonInformation personinfo = new PersonInformation();
		String url1 = apiGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + notify.getSubmittedbypersonno();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requ = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> respons = restTemplate.exchange(url1, HttpMethod.GET, requ, PersonInformation.class);
		personinfo = respons.getBody();
		model.addAttribute("personInfo", personinfo);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+notify.getSubmittedbypersonno()+"/Photo";
		ResponseEntity<CommonDescription> response2 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response2.getStatusCode() == HttpStatus.OK) {
			comdes = response2.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("mode", mode);


		model.addAttribute("approverdata", apr);

		////*****************Updated by Ravi--END****************\\\\\\\\\\\\\\\\\\\\\\\\\\


		return "forms/reimbursement/contingent/adhocLocalapproval :: adhocLocalClaimApproval";
	}


	@RequestMapping(value = "/downloadf/{filepath}", method = RequestMethod.POST)
    public String adhocDownload(@PathVariable String filep) {
    	
    	String filepath = new File("").getAbsolutePath()+filep;
    	//System.out.println("filepath: "+filepath);
    	return "success";
    }
    
/************* SUBMIT BUTTON CLICK ***********/    
    @RequestMapping("/adhoclocalSubmit/{id}")
    public String adhoclocalReimbursementSubmit(@PathVariable String id, HttpServletRequest request,Model model){
    	
    	Login login = (Login) request.getSession().getAttribute("login");
		PersonInformation resp =new PersonInformation();
    	String url = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+login.getEmplid();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<PersonInformation> response = restTemplate.exchange(url,HttpMethod.GET, req,PersonInformation.class);
		resp=response.getBody();
		if(response.getStatusCode() == HttpStatus.OK) {
			resp = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		model.addAttribute("personInfo",resp);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response1 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response1.getStatusCode() == HttpStatus.OK) {
			comdes = response1.getBody();
		}
		model.addAttribute("propic",comdes);
		AdhocModel res = new AdhocModel();
    	url = apiGateway.getAppgatewaypyrl_sandhya()+"/api/contigentclaim/getcontigentclaimsbyid/"+id;
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<AdhocModel> response2 = restTemplate.exchange(url,HttpMethod.GET, req,AdhocModel.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			res = response2.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response2.getStatusCode());
		}
		
		model.addAttribute("adhoc",res.getTravelbillclaimdetails());
		long totalamt=0;
		for(int i=0;i<res.getTravelbillclaimdetails().size();i++) {
			totalamt+=res.getTravelbillclaimdetails().get(i).getClaimamt();
		}
		
		LocalDate currentdate=LocalDate.now();
		Month currentMonth=currentdate.getMonth();
		int currentYear=currentdate.getYear();
		model.addAttribute("month",currentMonth);
		model.addAttribute("year",currentYear);		
		model.addAttribute("totalamt",totalamt);

    	return "forms/reimbursement/contingent/contingentBill :: contingentBill";
    }
    
    /* End of adhocLocal Remb Controller*/
    
    
    
    

	@RequestMapping(value = "/addhoclocalApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel addhoclocalApprovalUpdate(@PathVariable("_status") String _status,String msg, double approvedamt, long claimid, HttpServletRequest reques,Model model) throws IOException, MessagingException {
		SingleResponseModel res = new SingleResponseModel();

		Login login = (Login) reques.getSession().getAttribute("login");
		
		//System.out.println("amt::::::::::"+approvedamt);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(httpHeaders);
		String RID="6"; 
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


		notify.setStatus(_status);
		notify.setMessage(msg);
		notify.setApprovedamount(approvedamt);
		//System.out.println(notify.toString());

		String url = apiGateway.getAppgateway() + "/Notification/submittedAprovelById";

		HttpEntity<NotificationModel> request = new HttpEntity<NotificationModel>(notify,httpHeaders);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, SingleResponseModel.class);
		if(response.getStatusCode() == HttpStatus.OK) {
			res=response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}


		if(res.getStatus().equals("true") && _status.equals("Approved")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = apiGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+claimid+"/"+notify.getSubmittedbypersonno();
			System.out.println("URL APPROVER DATA :: "+getapprovalperson);
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emprequest = new HttpEntity<String>(httpHeaders);
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"Contingent Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}else if(res.getStatus().equals("true") && _status.equals("Rejected")){

			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emprequest = new HttpEntity<String>(httpHeaders);
			PersonInformation personInformation = null;
			String url1 = apiGateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + notify.getSubmittedbypersonno();
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
						sendnotimail.sendrejectionmail(finalPersonInformation.getEmailaid(),finalNotify.getSubmittedbypersonname(),claimid,"Contingent Reimbursement");
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
