package in.co.srdt.unif.controllers.reimbursement;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

import in.co.srdt.unif.enums.Identifying;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.rembchild.ApprovalChildModel;
import in.co.srdt.unif.model.rembmedicalreimb.MedHospitalTypeLOVModel;
import in.co.srdt.unif.model.rembmedicalreimb.MedicalBillListModel;
import in.co.srdt.unif.model.rembmedicalreimb.MedicalDependentListModel;
import in.co.srdt.unif.model.rembmedicalreimb.MedicalReimDependentModel;
import in.co.srdt.unif.model.rembmedicalreimb.MedicalReimbursementModel;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/reimbursement")
public class MedicalReimbursementController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HttpHeaders httpHeaders;
	
	@Autowired
	ApplicationGateway apiGateway;
	
	@Autowired(required=true)
	private EligibilityReimbursement eligibilityreimb;
	
	@Autowired
	SmartValidator validator;

	@Autowired(required = true)
	private SendMail sendnotimail;
	
	String RID="10";
/* Controllers for Medical Reimbursement - Snigdhaa Vaish*/
	
/********* GET HEADER DATA *********/
	public PersonInformation headerdata(HttpServletRequest request,Model model,String emplid) {
		PersonInformation resp =new PersonInformation();
    	String url = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+emplid;
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

		return resp;
	}
	
/****** OPEN SEARCH PAGE ******/
    @RequestMapping("/medireimbsearch")
    public String medicalReimbursementSearch(HttpServletRequest request,Model model){
    	
    	Login login = (Login) request.getSession().getAttribute("login");
    	PersonInformation resp = headerdata(request,model,login.getEmplid());		
		model.addAttribute("personInfo",resp);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);

        return "forms/reimbursement/medicalReimbursement/medicalReimbursementSearch :: medicalReimbursementSearch";
    }

/****** LOAD DATA ON SEARCH PAGE ******/
    @ResponseBody
    @RequestMapping(value = "/getMedicReimbSearchData", method = RequestMethod.GET)
	public MedicalReimbursementModel[] adhocsearch(HttpServletRequest request,Model model)
	{	
    	Login login = (Login) request.getSession().getAttribute("login");
    	MedicalReimbursementModel[] mr = null;    	
    	String url = apiGateway.getAppgatewaypyrl_sandhya()+"/api/MedicalRmbrsment/getRecordByPersonNumber/"+login.getEmplid();
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<MedicalReimbursementModel[]> response = restTemplate.exchange(url,HttpMethod.GET, req,MedicalReimbursementModel[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			mr = response.getBody();			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		return mr;
	}
    
/****** OPEN NEW PAGE ******/
    @RequestMapping("/medicReimb")
    public String medicReimbursement(HttpServletRequest request,Model model){
    	
    	MedicalReimbursementModel mr = new MedicalReimbursementModel();
    	model.addAttribute("medrdata",mr);
    	
    	Login login = (Login) request.getSession().getAttribute("login");
    	PersonInformation resp = headerdata(request,model,login.getEmplid());
		model.addAttribute("personInfo",resp);


    	/*String RID="10"; 
    	String ceiling=eligibilityreimb.getCeilinglimit(RID,login.getEmplid());
    	model.addAttribute("empent",ceiling);*/
    	
    	CommonLOV[] claimtype=null;
    	CommonLOV[] illnessype=null;
    	MedHospitalTypeLOVModel[] hospitaltype= null;
    	MedicalReimDependentModel[] depen=null;
    	
    	String urlclaim=apiGateway.getAppgateway()+"/General/loadClaimType";
    	String urlhostype=apiGateway.getAppgatewaypyrl_sandhya()+"/api/hospitallov/gethospitaltypelov";
    	String urlillype=apiGateway.getAppgatewaypyrl_sandhya()+"/api/IllnessType/getillnesTypeLOV";
    	//String urldepname=apiGateway.getAppgateway()+"PersonManagement/DependentDetails/getDependentDetailsById/"+login.getEmplid();
    	String urldepname=apiGateway.getAppgateway()+"/PersonManagement/DependentDetails/getDependentDetailsForMedical/"+login.getEmplid();
    	
    	HttpEntity<String> req = new HttpEntity<>(httpHeaders);

		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);

    	ResponseEntity<MedHospitalTypeLOVModel[]> response = restTemplate.exchange(urlhostype,HttpMethod.GET, req,MedHospitalTypeLOVModel[].class);		
    	hospitaltype=response.getBody();
    	model.addAttribute("hospitaltype",hospitaltype);
    	
    	ResponseEntity<CommonLOV[]> respons = restTemplate.exchange(urlclaim,HttpMethod.GET, req,CommonLOV[].class);		
    	claimtype=respons.getBody();
    	model.addAttribute("claimtype",claimtype);
    	
    	respons = restTemplate.exchange(urlillype,HttpMethod.GET, req,CommonLOV[].class);		
    	illnessype=respons.getBody();
    	model.addAttribute("illnesstype",illnessype);
    	
    	ResponseEntity<MedicalReimDependentModel[]> respon = restTemplate.exchange(urldepname,HttpMethod.GET, req,MedicalReimDependentModel[].class);		
    	depen=respon.getBody();
    	model.addAttribute("depname",depen);
    	
    	model.addAttribute("prescriptionenclose",Identifying.values());
    	model.addAttribute("spantext","1 of 1");
    	model.addAttribute("spantext2","1 of 1");
    	model.addAttribute("stat"," ");
    	
    	return "forms/reimbursement/medicalReimbursement/medicalReimbursement :: medicalReimbursement";
    }

/****** OPEN PAGE IN EDIT MODE ******/        
    @RequestMapping(value="/viewmedrdata/{claimid}/{mode}", method = RequestMethod.GET)
	public String viewMedicReimbData(@PathVariable("claimid") String claimid,@PathVariable("mode")String mode, Model model,HttpServletRequest reques)
	{
    	MedicalReimbursementModel res = new MedicalReimbursementModel();
    	String url = apiGateway.getAppgatewaypyrl_sandhya()+"/api/MedicalRmbrsment/getRecordByClaimID/"+claimid;
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<MedicalReimbursementModel> response = restTemplate.exchange(url,HttpMethod.GET, req,MedicalReimbursementModel.class);
			
		if(response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println(response.getStatusCode());
		}
		
		model.addAttribute("medrdata",res);
		model.addAttribute("mode", mode);
		
		Login login = (Login) reques.getSession().getAttribute("login");
    	PersonInformation resp = headerdata(reques,model,login.getEmplid());
		model.addAttribute("personInfo",resp);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
    	/*String RID="10"; 
    	String ceiling=eligibilityreimb.getCeilinglimit(RID,login.getEmplid());
    	model.addAttribute("empent",ceiling);*/
    	
    	CommonLOV[] claimtype=null;
    	CommonLOV[] illnessype=null;
    	MedHospitalTypeLOVModel[] hospitaltype= null;
    	MedicalReimDependentModel[] depen=null;
    	MedHospitalTypeLOVModel names=null;
    	
    	String urlclaim=apiGateway.getAppgateway()+"/General/loadClaimType";
    	String urlhostype=apiGateway.getAppgatewaypyrl_sandhya()+"/api/hospitallov/gethospitaltypelov";
    	String urlillype=apiGateway.getAppgatewaypyrl_sandhya()+"/api/IllnessType/getillnesTypeLOV";
    	//String urldepname=apiGateway.getAppgateway()+"PersonManagement/DependentDetails/getDependentDetailsById/"+login.getEmplid();
    	String urldepname=apiGateway.getAppgateway()+"/PersonManagement/DependentDetails/getDependentDetailsForMedical/"+login.getEmplid();
    	String urlhosname=apiGateway.getAppgatewaypyrl_sandhya()+"/api/hospitallov/gethospitaltypelovbyid/"+res.getHospitaltype();
    	
    	ResponseEntity<MedHospitalTypeLOVModel[]> respons = restTemplate.exchange(urlhostype,HttpMethod.GET, req,MedHospitalTypeLOVModel[].class);		
    	hospitaltype=respons.getBody();
    	model.addAttribute("hospitaltype",hospitaltype);
    	
    	ResponseEntity<CommonLOV[]> respon = restTemplate.exchange(urlclaim,HttpMethod.GET, req,CommonLOV[].class);		
    	claimtype=respon.getBody();
    	model.addAttribute("claimtype",claimtype);
    	
    	respon = restTemplate.exchange(urlillype,HttpMethod.GET, req,CommonLOV[].class);		
    	illnessype=respon.getBody();
    	model.addAttribute("illnesstype",illnessype);
    	
    	ResponseEntity<MedicalReimDependentModel[]> respo = restTemplate.exchange(urldepname,HttpMethod.GET, req,MedicalReimDependentModel[].class);		
    	depen=respo.getBody();
    	model.addAttribute("depname",depen);
    	
    	ResponseEntity<MedHospitalTypeLOVModel> res1 = restTemplate.exchange(urlhosname,HttpMethod.GET, req,MedHospitalTypeLOVModel.class);		
    	names=res1.getBody();
    	model.addAttribute("hospitalname",names.getHospitalnamelov());
    	
    	List<MedicalDependentListModel> medp=res.getMedicaldependent();
    	List<MedicalBillListModel> medb=res.getMedicalbill();

    	model.addAttribute("prescriptionenclose",Identifying.values());
    	model.addAttribute("spantext","1 of "+medb.size());
    	model.addAttribute("spantext2","1 of "+medp.size());
    	model.addAttribute("stat"," ");
    	
    	////////APPROVAL VIEW MODE SET\\\\\\\\\\\\\\
		
		ApprovalChildModel[] apr = null;
		String aprroveurl =apiGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/" 
				+ RID + "/" + claimid + "/" + login.getEmplid();

		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(httpHeaders);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (responseapr.getStatusCode() == HttpStatus.OK
				) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");			
		}

		model.addAttribute("approverdata", apr);

		return "forms/reimbursement/medicalReimbursement/medicalReimbursement :: medicalReimbursement";
	}
    
/****** SET HOSPITAL NAME LOV *****/
    @ResponseBody
    @RequestMapping("/medicHosName/{id}")
    public MedHospitalTypeLOVModel medichosnameLov(@PathVariable String id,HttpServletRequest request,Model model){
    	
    	MedHospitalTypeLOVModel names=null;
    	
    	String url=apiGateway.getAppgatewaypyrl_sandhya()+"/api/hospitallov/gethospitaltypelovbyid/"+id;
    	HttpEntity<String> req = new HttpEntity<>(httpHeaders);
    	ResponseEntity<MedHospitalTypeLOVModel> response = restTemplate.exchange(url,HttpMethod.GET, req,MedHospitalTypeLOVModel.class);		
    	names=response.getBody();

    	return names;
    }
    
/****** SET DEPENDENT DETAILS *****/
    @ResponseBody
    @RequestMapping("/medicDepDet/{id}")
    public MedicalReimDependentModel medicdepdtlLov(@PathVariable String id,HttpServletRequest request,Model model){
    	
    	Login login = (Login) request.getSession().getAttribute("login");
    	MedicalReimDependentModel[] depen=null;
    	//String urldepname=apiGateway.getAppgateway()+"PersonManagement/DependentDetails/getDependentDetailsById/"+login.getEmplid();
    	String urldepname=apiGateway.getAppgateway()+"/PersonManagement/DependentDetails/getDependentDetailsForMedical/"+login.getEmplid();
    	HttpEntity<String> req = new HttpEntity<>(httpHeaders);
    	ResponseEntity<MedicalReimDependentModel[]> respon = restTemplate.exchange(urldepname,HttpMethod.GET, req,MedicalReimDependentModel[].class);		
    	depen=respon.getBody();
    	
    	for(int i=0;i<depen.length;i++) {
    		if(depen[i].getDependentdetailsid()==Long.parseLong(id)) {
    			return depen[i];
    		}
    	}
    	
    	return depen[0];
    }
    
/****** SET SELF DETAILS *****/
    @ResponseBody
    @RequestMapping("/medicselfdtl")
    public PersonInformation medicselfdtl(HttpServletRequest request,Model model){
    	
    	Login login = (Login) request.getSession().getAttribute("login");
    	PersonInformation tl=headerdata(request, model, login.getEmplid());
    	
    	return tl;
    }
    
/****** SAVE/UPDATE PAGE ******/
    @RequestMapping(value = "/medrSaveBtn/{mode}", method = RequestMethod.POST)
    public String medicalReimbursementSubmit(@PathVariable String mode,@ModelAttribute MedicalReimbursementModel mr, @RequestParam("file") MultipartFile file, HttpServletRequest reques, Model model, BindingResult bindingResult) throws IOException, MessagingException {
    	SingleResponseModel rs=null; 
    //************************ LOAD DATA ON PAGE *********************
    	Login login = (Login) reques.getSession().getAttribute("login");
    	PersonInformation resp = headerdata(reques,model,login.getEmplid());
    			//new PersonInformation();

		model.addAttribute("personInfo",resp);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		/*String RID="10"; 
    	String ceiling=eligibilityreimb.getCeilinglimit(RID,login.getEmplid());
    	model.addAttribute("empent",ceiling);*/
    	
    	CommonLOV[] claimtype=null;
    	CommonLOV[] illnessype=null;
    	MedHospitalTypeLOVModel[] hospitaltype= null;
    	MedicalReimDependentModel[] depen=null;
    	MedHospitalTypeLOVModel names=null;
    	
    	String urlclaim=apiGateway.getAppgateway()+"/General/loadClaimType";
    	String urlhostype=apiGateway.getAppgatewaypyrl_sandhya()+"/api/hospitallov/gethospitaltypelov";
    	String urlillype=apiGateway.getAppgatewaypyrl_sandhya()+"/api/IllnessType/getillnesTypeLOV";
    	//String urldepname=apiGateway.getAppgateway()+"PersonManagement/DependentDetails/getDependentDetailsById/"+login.getEmplid();
    	String urldepname=apiGateway.getAppgateway()+"/PersonManagement/DependentDetails/getDependentDetailsForMedical/"+login.getEmplid();
    	String urlhosname=apiGateway.getAppgatewaypyrl_sandhya()+"/api/hospitallov/gethospitaltypelovbyid/"+mr.getHospitaltype();
    	//System.out.println("hellooo   "+urldepname+"hell  "+urlhosname);
    	ResponseEntity<MedHospitalTypeLOVModel[]> respons = restTemplate.exchange(urlhostype,HttpMethod.GET, req,MedHospitalTypeLOVModel[].class);		
    	hospitaltype=respons.getBody();
    	model.addAttribute("hospitaltype",hospitaltype);
    	
    	ResponseEntity<CommonLOV[]> respon = restTemplate.exchange(urlclaim,HttpMethod.GET, req,CommonLOV[].class);		
    	claimtype=respon.getBody();
    	model.addAttribute("claimtype",claimtype);
    	
    	respon = restTemplate.exchange(urlillype,HttpMethod.GET, req,CommonLOV[].class);		
    	illnessype=respon.getBody();
    	model.addAttribute("illnesstype",illnessype);
    	
    	ResponseEntity<MedicalReimDependentModel[]> respo = restTemplate.exchange(urldepname,HttpMethod.GET, req,MedicalReimDependentModel[].class);		
    	depen=respo.getBody();
    	model.addAttribute("depname",depen);
    	
    	ResponseEntity<MedHospitalTypeLOVModel> res1 = restTemplate.exchange(urlhosname,HttpMethod.GET, req,MedHospitalTypeLOVModel.class);		
    	names=res1.getBody();
    	model.addAttribute("hospitalname",names.getHospitalnamelov());
    	
    	List<MedicalDependentListModel> medp=mr.getMedicaldependent();
    	List<MedicalBillListModel> medb=mr.getMedicalbill();

    	model.addAttribute("prescriptionenclose",Identifying.values());
    	model.addAttribute("spantext","1 of "+medb.size());
    	model.addAttribute("spantext2","1 of "+medp.size());
    	model.addAttribute("stat","Save");
    	model.addAttribute("mode","edit");
    	model.addAttribute("result","saving");
    	
    //************************ CHECK VALIDATION ********************************
    	validator.validate(mr, bindingResult); 
    	
    	List<MedicalDependentListModel> md= new ArrayList<>();
    	List<MedicalBillListModel> mb= new ArrayList<>();
    	md=mr.getMedicaldependent();
    	mb=mr.getMedicalbill();
    	
    	for(int i=0;i<md.size();i++) {
    		validator.validate(md.get(i), bindingResult);  
    	}
    	for(int i=0;i<mb.size();i++) {
    		validator.validate(mb.get(i), bindingResult);  
    	}
    	
    	model.addAttribute("bindingResult", bindingResult);
    	
    	if(bindingResult.hasErrors()) {
    		
    		System.out.println("Binding Errors ====>> "+bindingResult.getFieldErrors().get(0).toString());
    		mr.setAttachment("");
    		model.addAttribute("medrdata",mr);
    		System.out.println("binding error::::::::::"+bindingResult);
    		model.addAttribute("fieldError",bindingResult.getFieldErrors());
    		return "forms/reimbursement/medicalReimbursement/medicalReimbursement :: medicalReimbursement";
		}
    	
    //************************ ADD ATTACHMENT **********************************	
    	if(!file.isEmpty()) {
    		String location = mr.getAttachment();
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
    			
    			String payLoad = "";
    			String url = "";
    			
    			mr.setAttachment(storePath);
    			storePath = location;
    			}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    			model.addAttribute("result","WRITE_ERROR");
    			return "forms/reimbursement/medicalReimbursement/medicalReimbursement :: medicalReimbursement";
    		}

    	}
    	else {
    		mr.setAttachment(mr.getAttachhidden());
    	}
    			
	//****************Save or Update Data if everything is correct*******************
		String urlsave=apiGateway.getAppgatewaypyrl_sandhya()+"/api/MedicalRmbrsment/addandcorrectmedicalrmbrsment";    	    
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    	//System.out.println("urlsave::"+urlsave);
    	
		HttpEntity<MedicalReimbursementModel> request = new HttpEntity<MedicalReimbursementModel>(mr, httpHeaders);
		System.out.println("MediDetails :: "+mr.toString());
		ResponseEntity<SingleResponseModel> response3 = restTemplate.exchange(urlsave, HttpMethod.POST, request,SingleResponseModel.class);
		
		if(response3.getStatusCode() == HttpStatus.OK) {
			//System.out.println("Save 2::"+mr.toString());
			rs=response3.getBody();				
		} else {
			System.out.println("Request Failed");
			System.out.println(response3.getStatusCode());
		    model.addAttribute("result","LOG_ERROR");
		    return "forms/reimbursement/medicalReimbursement/medicalReimbursement :: medicalReimbursement";
		}
		
		model.addAttribute("result",rs.getMessage());
		model.addAttribute("medrdata",mr);

		if(rs.getMessage().equals("Success") && mr.getStatus().equals("Submitted")) {
			ApproverDetails apprdetails=null;
			String getapprovalperson = apiGateway.getAppgateway() + "/Notification/getEmailNotificationPersonDetails/"+RID+"/"+mr.getClaimid()+"/"+login.getEmplid();
			System.out.println("URL APPROVER DATA :: "+getapprovalperson);
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> emprequest = new HttpEntity<String>(httpHeaders);
			ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, emprequest, ApproverDetails.class);
			if (apprdetailsres.getStatusCode() == HttpStatus.OK) {
				apprdetails = apprdetailsres.getBody();
			}
			System.out.println("Approver Details :: "+apprdetails.toString());
			ApproverDetails finalApprdetails = apprdetails;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						sendnotimail.sendnotificationmail(finalApprdetails,resp.getPersonname(),login.getEmplid(),"Medical Reimbursement");
					} catch (MessagingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

		}

		return "forms/reimbursement/medicalReimbursement/medicalReimbursement :: medicalReimbursement";
    }

    
    /************* DECLARATION BUTTON CLICK ***********/    
    @RequestMapping("/medicreimbAnnexure/{id}")
    public String medicalReimbursementSubmit(@PathVariable String id, HttpServletRequest request,Model model){
    	
    	Login login = (Login) request.getSession().getAttribute("login");
    	PersonInformation resp = headerdata(request,model,login.getEmplid());
		model.addAttribute("personInfo",resp);
		
		MedicalReimbursementModel res = new MedicalReimbursementModel();
    	String url = apiGateway.getAppgatewaypyrl_sandhya()+"/api/MedicalRmbrsment/getRecordByClaimID/"+id;
    	HttpEntity<String> req = new HttpEntity<>(httpHeaders);
    	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+login.getEmplid()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		ResponseEntity<MedicalReimbursementModel>response2 = restTemplate.exchange(url,HttpMethod.GET, req,MedicalReimbursementModel.class);
		
		if(response2.getStatusCode() == HttpStatus.OK) {
			res = response2.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response2.getStatusCode());
		}
		
		model.addAttribute("medr",res);
		model.addAttribute("meddep",res.getMedicaldependent());
		String admdate=res.getAdmissiondate();
		if(admdate==null) {
			admdate=" ";
		}
		String disdate=res.getDischargedate();
		if(disdate==null) {
			disdate=" ";
		}
		model.addAttribute("admdate",admdate);
		model.addAttribute("disdate",disdate);
		model.addAttribute("medbil",res.getMedicalbill());
		
		return "forms/reimbursement/medicalReimbursement/medicalreimAnnexure :: medicalreimAnnexure";
    }

    /*---------------------- APPROVAL -------------------------------*/


	@RequestMapping("/medireimbApproval/{claimid}/{mode}/{status}")
	public String medicalReimbursementApproval(@PathVariable("claimid") int claimid, @PathVariable("status") String status,
											   @PathVariable("mode") String mode, Model model, HttpServletRequest request){

		Login login = (Login) request.getSession().getAttribute("login");

		String RID="10";
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		NotificationModel notify=null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(httpHeaders);

		String urlNotify = apiGateway.getAppgateway()+"/Notification/getApprovalDataByPersonnoStatus/"+RID+"/" + claimid+"/"+login.getEmplid()+"/"+status;
		ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET, request1Noti, NotificationModel.class);
		if(responseNotify.getStatusCode() == HttpStatus.OK) {
			notify=responseNotify.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		model.addAttribute("NotifynavBar", notify);
		model.addAttribute("APPRAMT",notify.getApprovedamount());
		MedicalReimbursementModel res = new MedicalReimbursementModel();
		String url = apiGateway.getAppgatewaypyrl_sandhya()+"/api/MedicalRmbrsment/getRecordByClaimID/"+claimid;
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<>(httpHeaders);
		ResponseEntity<MedicalReimbursementModel> response = restTemplate.exchange(url,HttpMethod.GET, req,MedicalReimbursementModel.class);

		if(response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
			
		} else {
			System.out.println(response.getStatusCode());
		}
		String claimeeperno = res.getPersonnumber();
		model.addAttribute("medrdata",res);
		model.addAttribute("mode", mode);

		PersonInformation resp = headerdata(request,model,res.getPersonnumber());
		CommonDescription comdes=null;
		String urlpropic= apiGateway.getAppgateway() + "/AbsencePersonData/getPersonDataByGivenParameter/"+res.getPersonnumber()+"/Photo";
		ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
		if (response007.getStatusCode() == HttpStatus.OK) {
			comdes = response007.getBody();
		}
		model.addAttribute("propic",comdes);
		model.addAttribute("personInfo",resp);

    	/*String ceiling=eligibilityreimb.getCeilinglimit(RID,login.getEmplid());
    	model.addAttribute("empent",ceiling);*/

		CommonLOV[] claimtype=null;
		CommonLOV[] illnessype=null;
		MedHospitalTypeLOVModel[] hospitaltype= null;
		MedicalReimDependentModel[] depen=null;
		MedHospitalTypeLOVModel names=null;

		String urlclaim=apiGateway.getAppgateway()+"/General/loadClaimType";
		String urlhostype=apiGateway.getAppgatewaypyrl_sandhya()+"/api/hospitallov/gethospitaltypelov";
		String urlillype=apiGateway.getAppgatewaypyrl_sandhya()+"/api/IllnessType/getillnesTypeLOV";
		//String urldepname=apiGateway.getAppgateway()+"PersonManagement/DependentDetails/getDependentDetailsById/"+login.getEmplid();
//		String urldepname=apiGateway.getAppgateway()+"/PersonManagement/DependentDetails/getDependentDetailsForMedical/"+login.getEmplid();
		String urldepname=apiGateway.getAppgateway()+"/PersonManagement/DependentDetails/getDependentDetailsForMedical/"+claimeeperno; 
		String urlhosname=apiGateway.getAppgatewaypyrl_sandhya()+"/api/hospitallov/gethospitaltypelovbyid/"+res.getHospitaltype();

		ResponseEntity<MedHospitalTypeLOVModel[]> respons = restTemplate.exchange(urlhostype,HttpMethod.GET, req,MedHospitalTypeLOVModel[].class);
		hospitaltype=respons.getBody();
		model.addAttribute("hospitaltype",hospitaltype);

		ResponseEntity<CommonLOV[]> respon = restTemplate.exchange(urlclaim,HttpMethod.GET, req,CommonLOV[].class);
		claimtype=respon.getBody();
		model.addAttribute("claimtype",claimtype);

		respon = restTemplate.exchange(urlillype,HttpMethod.GET, req,CommonLOV[].class);
		illnessype=respon.getBody();
		model.addAttribute("illnesstype",illnessype);

		ResponseEntity<MedicalReimDependentModel[]> respo = restTemplate.exchange(urldepname,HttpMethod.GET, req,MedicalReimDependentModel[].class);
		depen=respo.getBody();
		model.addAttribute("depname",depen);

		ResponseEntity<MedHospitalTypeLOVModel> res1 = restTemplate.exchange(urlhosname,HttpMethod.GET, req,MedHospitalTypeLOVModel.class);
		names=res1.getBody();
		model.addAttribute("hospitalname",names.getHospitalnamelov());

		List<MedicalDependentListModel> medp=res.getMedicaldependent();
		List<MedicalBillListModel> medb=res.getMedicalbill();

		model.addAttribute("prescriptionenclose",Identifying.values());
		model.addAttribute("spantext","1 of "+medb.size());
		model.addAttribute("spantext2","1 of "+medp.size());
		model.addAttribute("stat"," ");

		ApprovalChildModel[] apr = null;

		String aprroveurl = apiGateway.getAppgateway() + "/ApprovalNotification/getReimbApprovaleLableWithStatus/"
				+ RID + "/" + claimid + "/" + res.getPersonnumber();
		HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(httpHeaders);
		ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			apr = responseapr.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}

		model.addAttribute("approverdata", apr);

		return "forms/reimbursement/medicalReimbursement/medicalReimbApproval :: medicalReimbApproval";
	}

	@RequestMapping(value = "/mediRApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public SingleResponseModel medirApprovalUpdate(@PathVariable("_status") String _status, String msg
			,double approvedamt, String claimid, double taxableincome, double nontaxableincome , HttpServletRequest reques, Model model) throws IOException, MessagingException {
		SingleResponseModel res = new SingleResponseModel();

		Login login = (Login) reques.getSession().getAttribute("login");

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		NotificationModel notify = null;
		HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(httpHeaders);
		String RID = "10";

		String urlNotify = apiGateway.getAppgateway() + "/Notification/getUnreadApprovalData/" + RID + "/" + claimid
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
		System.out.println("TAXIN "+taxableincome+" NONTAXIN "+nontaxableincome);
		notify.setStatus(_status);
		notify.setMessage(msg);
		notify.setApprovedamount(approvedamt);
		notify.setTaxableincome(taxableincome);
		notify.setNontaxableincome(nontaxableincome);
		
		String url = apiGateway.getAppgateway() + "/Notification/submittedAprovelById";
//System.out.println("NOTIFY: "+notify.toString());
		HttpEntity<NotificationModel> request = new HttpEntity<NotificationModel>(notify, httpHeaders);
		ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request,
				SingleResponseModel.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			res = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(responseNotify.getStatusCode());
		}
		//System.out.println("res.toString::"+res.toString());

		if(res.getStatus().equals("true")) {
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
						sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"Medical Reimbursement");
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
