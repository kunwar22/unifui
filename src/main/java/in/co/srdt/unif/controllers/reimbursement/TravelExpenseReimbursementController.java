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

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import in.co.srdt.unif.controllers.SendMail;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.NotificationModel;
import in.co.srdt.unif.model.approvalnotification.ApproverDetails;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.rembchild.ApprovalChildModel;
import in.co.srdt.unif.model.travelExpenseRemb.TravelExpenseDetails;
import in.co.srdt.unif.model.travelExpenseRemb.TravelingExpense;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.model.user.absence.CommonDescription;
import in.co.srdt.unif.utils.ApplicationGateway;

//@SessionAttributes("travelExpense")
@Controller
@RequestMapping("/reimbursement")
public class TravelExpenseReimbursementController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;
    
    @Autowired
    ApplicationGateway apiGateway;

    @Autowired
    SmartValidator validator;

    @Autowired(required = true)
    private EligibilityReimbursement eligibilityreimb;

    @Autowired(required = true)
    private SendMail sendnotimail;

    Login userlogin = null;

    @RequestMapping("/travelExpense")
    public String travelExpenseReimbursementSearch(HttpServletRequest request, Model model, Authentication authentication) {

       // HttpHeaders headers=new HttpHeaders();
       // headers.add("Authorization", AccessToken.getAccessToken());
    	userlogin = (Login) request.getSession().getAttribute("login");
        headers.setContentType(MediaType.APPLICATION_JSON);

        
        PersonInformation resp = new PersonInformation();
        String url = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+ userlogin.getEmplid();
        
        HttpEntity<String> req = new HttpEntity<>(headers);

        ResponseEntity<PersonInformation> response = restTemplate.exchange(url, HttpMethod.GET, req, PersonInformation.class);
        resp = response.getBody();
        if (response.getStatusCode() == HttpStatus.OK) {
            resp = response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        model.addAttribute("personInfo", resp);
        CommonDescription comdes = null;
        String urlpropic = apiGateway.getAppgateway()+"/AbsencePersonData/getPersonDataByGivenParameter/" + userlogin.getEmplid() + "/Photo";
        ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
        if (response007.getStatusCode() == HttpStatus.OK) {
            comdes = response007.getBody();
        }
        model.addAttribute("propic", comdes);

        return "forms/reimbursement/travelExpense/travelExpenseSearch :: travelExpenseSearch";
    }


    @RequestMapping("/travelExpenseClaim")
    public String travelExpenseReimbursementClaim(@ModelAttribute("travelExpense") TravelingExpense travelExpense, Model model, HttpServletRequest request, Authentication authentication) {

       // HttpHeaders headers=new HttpHeaders();
      //  headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        
        PersonInformation res = null;

        String url = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+ userlogin.getEmplid();
        
        HttpEntity<String> requ = new HttpEntity<>(headers);
        ResponseEntity<PersonInformation> response = restTemplate.exchange(url, HttpMethod.GET, requ, PersonInformation.class);
        res = response.getBody();
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        model.addAttribute("personInfo", res);
        CommonDescription comdes = null;
        String urlpropic = apiGateway.getAppgateway()+"/AbsencePersonData/getPersonDataByGivenParameter/" + userlogin.getEmplid() + "/Photo";
        ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, requ, CommonDescription.class);
        if (response007.getStatusCode() == HttpStatus.OK) {
            comdes = response007.getBody();
        }


        CommonLOV[] ExpenseType = null;
        String urlexpenseType = apiGateway.getAppgateway()+"/General/loadRemExpenseType";
        
        HttpEntity<String> requestveh = new HttpEntity<String>(headers);
        ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlexpenseType, HttpMethod.GET, requestveh,
                CommonLOV[].class);

        if (responseveh.getStatusCode() == HttpStatus.OK) {
            ExpenseType = responseveh.getBody();
        }

        String optionsString = "";
        for (int j = 0; j < ExpenseType.length; j++) {
            optionsString += "<option value='" + ExpenseType[j].getDescription() + "'>" + ExpenseType[j].getDescription() + "</option>";
        }
        model.addAttribute("optionsString", optionsString);
        model.addAttribute("expenseType", ExpenseType);
        model.addAttribute("propic", comdes);
        model.addAttribute("travelExpense", travelExpense);

        String flag="false";
        model.addAttribute("flag", flag);



        return "forms/reimbursement/travelExpense/travelExpenseClaim :: travelExpenseClaim";

    }

    @ResponseBody
    @RequestMapping(value = "/getTravelSearchData", method = RequestMethod.GET)
    public TravelingExpense[] travelExpensesearch(HttpServletRequest request, Model model,Authentication authentication) {

       // HttpHeaders headers=new HttpHeaders();
       // headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        
        TravelingExpense[] res = null;
        String url = apiGateway.getAppgatewaypyrl_sandhya()+"/traveling/DetailsByPerson/" + userlogin.getEmplid();
       // String url="http://localhost:9090/traveling/DetailsByPerson/650";
        
        HttpEntity<String> req = new HttpEntity<>(headers);

        ResponseEntity<TravelingExpense[]> response = restTemplate.exchange(url, HttpMethod.GET, req, TravelingExpense[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return res;
    }


    @RequestMapping(value = "/travelExpenseSave/{mode}" , method = RequestMethod.POST )
    public String travelExpenseReimbursementSubmit(@ModelAttribute("travelExpense") TravelingExpense travelExpense,@RequestParam("file")MultipartFile[] file, @PathVariable("mode") String modes,   String statusid, HttpServletRequest request, Model model, BindingResult bindingResult, Authentication authentication) throws IOException, MessagingException {

     //   HttpHeaders headers=new HttpHeaders();
     //   headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        

        travelExpenseReimbursementClaim(travelExpense,model,request,authentication);

        CommonLOV[] ExpenseType = null;

        String urlexpenseType = apiGateway.getAppgateway()+"/General/loadRemExpenseType";
        
        HttpEntity<String> requestveh = new HttpEntity<String>(headers);
        ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlexpenseType, HttpMethod.GET, requestveh,
                CommonLOV[].class);

        if (responseveh.getStatusCode() == HttpStatus.OK) {
            ExpenseType = responseveh.getBody();
        }
        model.addAttribute("expenseType", ExpenseType);
        String optionsString = "";
        for (int j = 0; j < ExpenseType.length; j++) {

            optionsString += "<option value='" + ExpenseType[j].getDescription() + "'>" + ExpenseType[j].getDescription() + "</option>";
        }
        model.addAttribute("optionsString", optionsString);

        /*************************VALIDATION PART START****************************/
        model.addAttribute("result",new String());

        
        PersonInformation resp = null;
        String RID = "17";
        String url1 = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/" + userlogin.getEmplid();
        
        HttpEntity<String> req = new HttpEntity<>(headers);
        ResponseEntity<PersonInformation> response2 = restTemplate.exchange(url1, HttpMethod.GET, req, PersonInformation.class);
        resp = response2.getBody();

        model.addAttribute("personInfo", resp);
        CommonDescription comdes=null;
        String urlpropic= apiGateway.getAppgateway()+"/AbsencePersonData/getPersonDataByGivenParameter/"+ userlogin.getEmplid()+"/Photo";
        ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
        if (response007.getStatusCode() == HttpStatus.OK) {
            comdes = response007.getBody();
        }
        model.addAttribute("propic",comdes);
        validator.validate(travelExpense, bindingResult);

        model.addAttribute("bindingResult", bindingResult);
        model.addAttribute("travelExpense", travelExpense);
        model.addAttribute("mode","blank");

        List<TravelExpenseDetails> adl= new ArrayList<>();
        adl=travelExpense.getExpensedetails();

        if (bindingResult.hasErrors()) {
            model.addAttribute("result","errrrrr");

            return "forms/reimbursement/travelExpense/travelExpenseClaim :: travelExpenseClaim";
        }
        /*************************VALIDATION PART END****************************/

        for(int i=0; i<file.length; i++) {
            if(file[i]!=null) {

                if(!file[i].isEmpty()) {
                    //System.out.println("Inside File Create travel");
                    String location = "/EmployeeDocs/"+ userlogin.getEmplid()+"/TravelExpenseReimbursement";
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
                        String empl =  userlogin.getEmplid();

                        String extension = FilenameUtils.getExtension(file[i].getOriginalFilename());
                        String generatedFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss" + "'" + empl +"_"+i+ "." + extension + "'").format(new Date());


                        File target = new File(filePath + File.separator + generatedFileName);
                        int readByteCount = 0;
                        byte[] buffer = new byte[20480];

                        BufferedInputStream in = new BufferedInputStream(file[i].getInputStream());
                        @SuppressWarnings("resource")
                        FileOutputStream out = new FileOutputStream(target);
                        while ((readByteCount = in.read(buffer)) != -1) {
                            out.write(buffer, 0, readByteCount);
                        }

                        storePath += "/" + generatedFileName;
                      //  System.out.println(storePath);
                        travelExpense.getExpensedetails().get(i).setAttachments(storePath);
                    }
                    catch (IOException e) {

                        System.out.println("IOException");
                        e.printStackTrace();
                        model.addAttribute("result", "IOEXCEPTION");
                        return "forms/reimbursement/travelExpense/travelExpenseClaim :: travelExpenseClaim";
                    }
                    catch (IllegalArgumentException e) {

                        System.out.println("IllegalArgumentException");
                        e.printStackTrace();
                        model.addAttribute("result", "ILLEGALARG");
                        return "forms/reimbursement/travelExpense/travelExpenseClaim :: travelExpenseClaim";
                    }
                    catch (Exception e) {
                       // System.out.println("in.....7"+filePath);
                        System.out.println("Exception");
                        e.printStackTrace();
                        model.addAttribute("result", "WRITE_ERROR");
                        return "forms/reimbursement/travelExpense/travelExpenseClaim :: travelExpenseClaim";
                    }
                }
            }
        }

        /***********************DATABASE TRANSACTIONS START FOR DATA SAVE or SUBMIT**********************/
        if (statusid.equals("saved")) {
            travelExpense.setStatus("Saved");
        } else if (statusid.equals("submit")) {
            travelExpense.setStatus("Submitted");
        }

        String urlsave="";

      //  System.out.println("in.....4");
        SingleResponseModel res =null;
        if(modes.equals("travelformUpdate")) {
             urlsave = apiGateway.getAppgatewaypyrl_sandhya()+"/traveling/updateDetails";
           // urlsave="http://localhost:9090/traveling/updateDetails";
            System.out.println("in.....5"+travelExpense.toString());
            HttpEntity<TravelingExpense> request1 = new HttpEntity<TravelingExpense>(travelExpense, headers);
            //ResponseEntity<String> response = restTemplate.exchange(urlsave, HttpMethod.POST, request1, String.class);

            ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlsave, HttpMethod.PUT, request1, SingleResponseModel.class);
        //    System.out.println("in.....6");
            if (response.getStatusCode() == HttpStatus.OK) {
                res = response.getBody();
           //     System.out.println("data:::"+res.toString());
            } else {
                System.out.println(response.getStatusCode());
                System.out.println("error data:::"+res.toString());
                model.addAttribute("result", "LOG_ERROR");
                return "forms/reimbursement/travelExpense/travelExpenseClaim :: travelExpenseClaim";
            }
        }
        if(modes.equals("travelformSave")) {
            urlsave = apiGateway.getAppgatewaypyrl_sandhya()+"/traveling/saveDetails";
          System.out.println("in.....7"+travelExpense.toString());
            HttpEntity<TravelingExpense> request1 = new HttpEntity<TravelingExpense>(travelExpense, headers);
            //ResponseEntity<String> response = restTemplate.exchange(urlsave, HttpMethod.POST, request1, String.class);
            ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlsave, HttpMethod.POST, request1, SingleResponseModel.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                res = response.getBody();
            } else {
                System.out.println(response.getStatusCode());
                model.addAttribute("result", "LOG_ERROR");
                return "forms/reimbursement/travelExpense/travelExpenseClaim :: travelExpenseClaim";
            }
        }



        String result =res.getStatus();

        if(res.getStatus()!=null) {
            result=res.getStatus();
        }
        else {
            result="Success";
        }

        model.addAttribute("result", result);


        if(res.getStatus().equals("") && statusid.equals("submit")) {
            ApproverDetails apprdetails=null;
            String getapprovalperson = apiGateway.getAppgateway()+"/Notification/getEmailNotificationPersonDetails/"+RID+"/"+travelExpense.getClaimid()+"/"+userlogin.getEmplid();

            
            HttpEntity<String> emailrequest = new HttpEntity<String>(headers);
            ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, emailrequest, ApproverDetails.class);
            if (apprdetailsres.getStatusCode() == HttpStatus.OK) {
                apprdetails = apprdetailsres.getBody();
            }

            ApproverDetails finalApprdetails = apprdetails;
            PersonInformation finalResp = resp;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sendnotimail.sendnotificationmail(finalApprdetails, finalResp.getPersonname(),userlogin.getEmplid(),"Telephone Reimbursement");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
        model.addAttribute("result", res.getStatus());
  //    System.out.println("result::"+ res.getStatus());
        return "forms/reimbursement/travelExpense/travelExpenseClaim :: travelExpenseClaim";

        /***********************DATABASE TRANSACTIONS END FOR DATA SAVE or SUBMIT**********************/
    }







    @RequestMapping("/viewtravelExpensedata/byclaimId/{claimid}/{mode}")
    public String viewData(@PathVariable("claimid") String claimid, @PathVariable("mode") String mode, Model model, HttpServletRequest reques, Authentication authentication) {

       // HttpHeaders headers=new HttpHeaders();
       // headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        TravelingExpense res = new TravelingExpense();
        ApprovalChildModel[] apr = null;
        
        String RID = "17";


        String url = apiGateway.getAppgatewaypyrl_sandhya()+"/traveling/DetailsByClaimId/" + claimid;

        
        HttpEntity<String> req = new HttpEntity<String>(headers);
        ResponseEntity<TravelingExpense> response = restTemplate.exchange(url, HttpMethod.GET, req, TravelingExpense.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();

        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        String aprroveurl = apiGateway.getAppgateway()+"/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + claimid + "/" + userlogin.getEmplid();

        HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
        ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

        if (responseapr.getStatusCode() == HttpStatus.OK) {
            apr = responseapr.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(responseapr.getStatusCode());
        }
        model.addAttribute("travelExpense", res);
        model.addAttribute("mode", mode);
        model.addAttribute("approverdata", apr);

        PersonInformation resp = new PersonInformation();
        String url1 = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/" + userlogin.getEmplid();
        
        HttpEntity<String> requ = new HttpEntity<>(headers);
        ResponseEntity<PersonInformation> respons = restTemplate.exchange(url1, HttpMethod.GET, requ, PersonInformation.class);
        resp = respons.getBody();
        model.addAttribute("personInfo", resp);
        CommonDescription comdes=null;
        String urlpropic= apiGateway.getAppgateway()+"/AbsencePersonData/getPersonDataByGivenParameter/"+userlogin.getEmplid()+"/Photo";
        ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
        if (response007.getStatusCode() == HttpStatus.OK) {
            comdes = response007.getBody();
        }
        model.addAttribute("propic",comdes);


        CommonLOV[] ExpenseType = null;
        String urlexpenseType = apiGateway.getAppgateway()+"/General/loadRemExpenseType";
        
        HttpEntity<String> requestveh = new HttpEntity<String>(headers);
        ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlexpenseType, HttpMethod.GET, requestveh,
                CommonLOV[].class);

        if (responseveh.getStatusCode() == HttpStatus.OK) {
            ExpenseType = responseveh.getBody();
        }

        String optionsString = "";
        for (int j = 0; j < ExpenseType.length; j++) {
            optionsString += "<option value='" + ExpenseType[j].getDescription() + "'>" + ExpenseType[j].getDescription() + "</option>";
        }
        model.addAttribute("optionsString", optionsString);
        model.addAttribute("expenseType", ExpenseType);

        String flag="true";
        model.addAttribute("flag", flag);





        return "forms/reimbursement/travelExpense/travelExpenseClaim :: travelExpenseClaim";
    }



    @RequestMapping("/viewtravelExpenseapprovaldata/{claimid}/{mode}/{status}")
    public String viewTravelapprovalData(@PathVariable("claimid") String claimid, @PathVariable("status") String status, @PathVariable("mode") String mode, Model model, HttpServletRequest reques, Authentication authentication) {

        //HttpHeaders headers=new HttpHeaders();
        //headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        userlogin = (Login) reques.getSession().getAttribute("login");

        TravelingExpense res = new TravelingExpense();
        ApprovalChildModel[] apr = null;

        
        String RID = "17";

        String url = apiGateway.getAppgatewaypyrl_sandhya()+"/traveling/DetailsByClaimId/" + claimid;

        
        HttpEntity<String> req = new HttpEntity<String>(headers);
        ResponseEntity<TravelingExpense> response = restTemplate.exchange(url, HttpMethod.GET, req, TravelingExpense.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

        String urlNotify = apiGateway.getAppgateway()+"/Notification/getApprovalDataByPersonnoStatus/"+RID+"/" + claimid+"/"+userlogin.getEmplid()+"/"+status;


        NotificationModel notify=null;
        HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);

        ResponseEntity<NotificationModel> responseNotify = restTemplate.exchange(urlNotify, HttpMethod.GET, request1Noti, NotificationModel.class);

        if(responseNotify.getStatusCode() == HttpStatus.OK) {
            notify=responseNotify.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(responseNotify.getStatusCode());
        }
        model.addAttribute("APPRAMT",notify.getApprovedamount());

        String aprroveurl = apiGateway.getAppgateway()+"/ApprovalNotification/getReimbApprovaleLableWithStatus/" + RID + "/" + claimid + "/" + notify.getSubmittedbypersonno();

        HttpEntity<ApprovalChildModel[]> reqapr = new HttpEntity<ApprovalChildModel[]>(headers);
        ResponseEntity<ApprovalChildModel[]> responseapr = restTemplate.exchange(aprroveurl, HttpMethod.GET, reqapr, ApprovalChildModel[].class);

        if (responseapr.getStatusCode() == HttpStatus.OK) {
            apr = responseapr.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

        String ceiling = eligibilityreimb.getCeilinglimit(RID, notify.getSubmittedbypersonno());
        model.addAttribute("empent", ceiling);
        model.addAttribute("NotifynavBar", notify);

        model.addAttribute("travelExpense", res);
        model.addAttribute("mode", mode);

        model.addAttribute("approverdata", apr);

        PersonInformation resp = new PersonInformation();
        String url1 = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/" + notify.getSubmittedbypersonno();
        System.out.println(url1);
        HttpEntity<String> requ = new HttpEntity<>(headers);
        ResponseEntity<PersonInformation> respons = restTemplate.exchange(url1, HttpMethod.GET, requ, PersonInformation.class);
        resp = respons.getBody();
        model.addAttribute("personInfo", resp);
        CommonDescription comdes=null;
        String urlpropic= apiGateway.getAppgateway()+"/AbsencePersonData/getPersonDataByGivenParameter/"+notify.getSubmittedbypersonno()+"/Photo";
        ResponseEntity<CommonDescription> response007 = restTemplate.exchange(urlpropic, HttpMethod.GET, req, CommonDescription.class);
        if (response007.getStatusCode() == HttpStatus.OK) {
            comdes = response007.getBody();
        }
        model.addAttribute("propic",comdes);


        /***************************************code added by rajat on  28-01-2021*********************************************************/
        CommonLOV[] ExpenseType = null;
        String urlexpenseType = apiGateway.getAppgateway()+"/General/loadRemExpenseType";
        
        HttpEntity<String> requestveh = new HttpEntity<String>(headers);
        ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urlexpenseType, HttpMethod.GET, requestveh,
                CommonLOV[].class);

        if (responseveh.getStatusCode() == HttpStatus.OK) {
            ExpenseType = responseveh.getBody();
        }

        String optionsString = "";
        for (int j = 0; j < ExpenseType.length; j++) {
            optionsString += "<option value='" + ExpenseType[j].getDescription() + "'>" + ExpenseType[j].getDescription() + "</option>";
        }
        model.addAttribute("optionsString", optionsString);
        model.addAttribute("expenseType", ExpenseType);

/***************************************code added by rajat on  28-01-2021*********************************************************/



        return "forms/reimbursement/travelExpense/travelExpenseClaimApproval :: travelExpenseClaimApproval";
    }







    @RequestMapping(value = "/travelExpenseApproval/{_status}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public SingleResponseModel travelExpenseApprovalUpdate(@ModelAttribute("travelExpense") TravelingExpense travelExpense,@PathVariable("_status") String _status,String msg, double approvedamt, long claimid, HttpServletRequest reques,Model model, Authentication authentication) throws IOException, MessagingException {

      //  HttpHeaders headers=new HttpHeaders();
       // headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        SingleResponseModel res = new SingleResponseModel();
        SingleResponseModel res2 =null;
        
        NotificationModel notify=null;
        HttpEntity<NotificationModel> request1Noti = new HttpEntity<NotificationModel>(headers);
        String RID="17";

        String urlNotify = apiGateway.getAppgateway()+"/Notification/getUnreadApprovalData/"+RID+"/" + claimid+"/"+userlogin.getEmplid();

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

        String url = apiGateway.getAppgateway()+"/Notification/submittedAprovelById";

        HttpEntity<NotificationModel> request = new HttpEntity<NotificationModel>(notify,headers);
        System.out.println("data::::"+travelExpense.toString());
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.POST, request, SingleResponseModel.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            res=response.getBody();
            /********************************/
            String urlsave = apiGateway.getAppgatewaypyrl_sandhya()+"/traveling/updateTravleingApprovelAmt";
            // urlsave="http://localhost:9090/traveling/updateDetails";
            System.out.println("in.....5"+travelExpense.toString());
            HttpEntity<TravelingExpense> request1 = new HttpEntity<TravelingExpense>(travelExpense, headers);
            //ResponseEntity<String> response = restTemplate.exchange(urlsave, HttpMethod.POST, request1, String.class);

            ResponseEntity<SingleResponseModel> response1 = restTemplate.exchange(urlsave, HttpMethod.POST, request1, SingleResponseModel.class);
            //    System.out.println("in.....6");
            if (response1.getStatusCode() == HttpStatus.OK) {
                res2 = response1.getBody();
                //     System.out.println("data:::"+res.toString());
            } else {
                System.out.println(response1.getStatusCode());
                System.out.println("error data:::"+res.toString());
                model.addAttribute("result", "LOG_ERROR");

            }
            /***********************************************************/
        } else {
            System.out.println("Request Failed");
            System.out.println(responseNotify.getStatusCode());
        }

        if(res.getStatus().equals("true")) {
            ApproverDetails apprdetails=null;
            String getapprovalperson = apiGateway.getAppgateway()+"/Notification/getEmailNotificationPersonDetails/"+RID+"/"+claimid+"/"+notify.getSubmittedbypersonno();
            
            HttpEntity<String> emprequest = new HttpEntity<String>(headers);
            ResponseEntity<ApproverDetails> apprdetailsres =  restTemplate .exchange(getapprovalperson, HttpMethod.GET, emprequest, ApproverDetails.class);
            if (apprdetailsres.getStatusCode() == HttpStatus.OK) {
                apprdetails = apprdetailsres.getBody();
            }

            ApproverDetails finalApprdetails = apprdetails;
            NotificationModel finalNotify = notify;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sendnotimail.sendnotificationmail(finalApprdetails, finalNotify.getSubmittedbypersonname(), finalNotify.getSubmittedbypersonno(),"TravelExpense Reimbursement");
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




    @GetMapping("/travelexpense/removemanager/{index}/{flg}")
    public @ResponseBody String removeChild(@ModelAttribute("travelExpense") TravelingExpense travelExpense, @PathVariable("index") int index,@PathVariable String flg)
    {
        //System.out.println("index : " + index+" flag:::"+flg);

            travelExpense.getExpensedetails().remove(index);
            //System.out.println("removed");

       /* for(int z=0; z<travelExpense.getExpensedetails().size();z++){
            System.out.println(travelExpense.getExpensedetails().get(z).toString());
        }*/
        return "removed";
    }





    @RequestMapping(value="/travelExpenseDeclaration/{claimid}",method=RequestMethod.GET)
    public String getDeclaration(@PathVariable("claimid") int claimid,Model model,HttpServletRequest request, Authentication authentication)
    {
      //  HttpHeaders headers=new HttpHeaders();
      //  headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        
        PersonInformation empdetails = new PersonInformation();

        TravelingExpense traveldetails = new TravelingExpense();

        /* Getting Employee Details from API */

        String urlEmployeeDetails = apiGateway.getAppgateway()+"/PersonDetails/getPersonInfo/"+ userlogin.getEmplid();

        //System.out.println("URL for getting employee details :: " + urlEmployeeDetails);

        

        HttpEntity<String> emprequest = new HttpEntity<String>(headers);

        ResponseEntity<PersonInformation> employeePersonalDetailResponse = restTemplate
                .exchange(urlEmployeeDetails, HttpMethod.GET, emprequest, PersonInformation.class);

        if (employeePersonalDetailResponse.getStatusCode() == HttpStatus.OK) {
            empdetails = employeePersonalDetailResponse.getBody();

            //System.out.println("Employee Personal Details ==>" + empdetails);
        }

        model.addAttribute("personInfo", empdetails);

        //System.out.println("claimid ::::::::::: "+claimid);
        /**************************************
         * Fetching Details of Employee Ends
         *******************************************************/

        /* Fetching Travel Details starts */

        String urlTravelDetails = apiGateway.getAppgatewaypyrl_sandhya()+"/traveling/DetailsByClaimId/"+claimid;

       // System.out.println("claimid ::::::::::: "+claimid);

        HttpEntity<String> travelrequest = new HttpEntity<String>(headers);

        ResponseEntity<TravelingExpense> travelDetailResponse = restTemplate
                .exchange(urlTravelDetails, HttpMethod.GET, travelrequest, TravelingExpense.class);

        if (travelDetailResponse.getStatusCode() == HttpStatus.OK) {
            traveldetails = travelDetailResponse.getBody();

            //System.out.println("Travel Details ==>" + traveldetails);
        }

        model.addAttribute("travelDetails", traveldetails);

        /* Fetching Travel Details ends */
        return "forms/reimbursement/travelExpense/travelExpensesDeclaration :: travelExpensesRequest";
    }
    /* Fetching Declaration Form ends ---------------------Asmita  02-01-2021 */



}
