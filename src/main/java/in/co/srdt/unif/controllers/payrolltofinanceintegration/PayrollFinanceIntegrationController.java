package in.co.srdt.unif.controllers.payrolltofinanceintegration;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payroll.CalendarPeriod;
import in.co.srdt.unif.model.payrolltofinanceintegration.EbsPayrollDataHeaderInvoice;
import in.co.srdt.unif.model.payrolltofinanceintegration.EbsPayrollDataSyncDto;
import in.co.srdt.unif.model.user.absence.CommonDescription;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pyrlfinintgr")
public class PayrollFinanceIntegrationController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationGateway appgateway;

    @Autowired
    private HttpHeaders headers;



    @RequestMapping("/managepyrlintrg")
    public String managepyrlintrg(Model model, HttpServletRequest req) {

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        /*CommonDescription pygrp= null;
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
        }*/
        CommonLOV[] nature_of_employement=null;
        String urlnatofempl = appgateway.getAppgateway() + "/General/loadNatureOfEmployement";
        ResponseEntity<CommonLOV[]> response25 = restTemplate.exchange(urlnatofempl, HttpMethod.GET, request, CommonLOV[].class);
        if (response25.getStatusCode() == HttpStatus.OK) {
            nature_of_employement = response25.getBody();
        }

        //model.addAttribute("calcode",calcode);
        model.addAttribute("noe", nature_of_employement);
        
        
        
        Login login=(Login)req.getSession().getAttribute("login");
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
		model.addAttribute("calcode", calcode);

        return "fragments/payrolltofinanceintegration/pyrlfinintegration :: pyrlfinintegration";
    }

    @ResponseBody
    @RequestMapping(value = "/preparedata/{calcode}/{noeid}/{noeval}", method = RequestMethod.GET)
    public SingleResponseModel preparedata(@PathVariable("calcode") String calcode, @PathVariable("noeid") String noeid, @PathVariable("noeval") String noeval, HttpServletRequest requ) {
        Login login = (Login) requ.getSession().getAttribute("login");
        headers.setContentType(MediaType.APPLICATION_JSON);
        SingleResponseModel res = null;
        String urlincr = appgateway.getAppgateway_payroll() + "/api/ebspayrollsync/insertErPayrollDataInEbsSync/" + calcode + "/" + noeval + "/" + noeid + "/" +login.getEmplid();
        HttpEntity<String> req = new HttpEntity<String>(headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlincr, HttpMethod.GET, req, SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
        } else {
            System.out.println("Request Failed.");
            System.out.println(response.getStatusCode());
        }
        System.out.println(res.toString());
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/integratetofin", method = RequestMethod.POST)
    public String integratetofin() {
        System.out.println("INSIDE INTEGRATE FUNCTION");
        headers.setContentType(MediaType.APPLICATION_JSON);
        String urlincr = appgateway.getAppgatewayebspyrlprcs() + "/ebs/syncdata/pushPayrollDataToEbs";
        System.out.println("INSIDE INTEGRATE FUNCTION URL "+urlincr);
        HttpEntity<String> req = new HttpEntity<String>(headers);
        String res = null;
        ResponseEntity<String> response = restTemplate.exchange(urlincr, HttpMethod.POST, req, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
        } else {
            System.out.println("Request Failed.");
            System.out.println(response.getStatusCode());
        }
        System.out.println(res);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/payablepreparedata/{calcode1}", method = RequestMethod.GET)
    public SingleResponseModel payablepreparedata(@PathVariable("calcode1") String calcode1, HttpServletRequest requ) {
        Login login = (Login) requ.getSession().getAttribute("login");
        System.out.println("Inside Payable prepare.");
        //HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        SingleResponseModel res = null;
        String urlincr = appgateway.getAppgateway_payroll() + "/api/ebspayrolinvoice/payrollInvoiceDataPrepare/" + calcode1 +"/"+login.getEmplid();
        HttpEntity<String> req = new HttpEntity<String>(headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlincr, HttpMethod.POST, req, SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
        } else {
            System.out.println("Request Failed.");
            System.out.println(response.getStatusCode());
        }
        System.out.println(res.toString());
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/payableintegratetofin", method = RequestMethod.POST)
    public String payableintegratetofin() {
        System.out.println("INSIDE PAYABLE INTEGRATE FUNCTION");
        //HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        String urlincr = appgateway.getAppgatewayebspyrlprcs() + "/ebs/syncdata/processForInvoiceGenPayrollData";
        System.out.println("INSIDE INTEGRATE FUNCTION URL "+urlincr);
        HttpEntity<String> req = new HttpEntity<String>(headers);
        String res = null;
        ResponseEntity<String> response = restTemplate.exchange(urlincr, HttpMethod.POST, req, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
        } else {
            System.out.println("Request Failed.");
            System.out.println(response.getStatusCode());
        }
        System.out.println(res);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/getPyrlPrepData", method = RequestMethod.GET)
    public EbsPayrollDataSyncDto[] getPyrlPrepData() {
        System.out.println("INSIDE PAYROLL PREP GET FUNCTION");
        EbsPayrollDataSyncDto[] prepdata= null;
        headers.setContentType(MediaType.APPLICATION_JSON);
        String urlgetprep = appgateway.getAppgateway_payroll() + "/api/ebspayrollsync/getPayrollGlPreprationData";
        HttpEntity<String> req = new HttpEntity<String>(headers);
        ResponseEntity<EbsPayrollDataSyncDto[]> response = restTemplate.exchange(urlgetprep, HttpMethod.GET, req, EbsPayrollDataSyncDto[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            prepdata = response.getBody();
        } else {
            System.out.println("Request Failed.");
            System.out.println(response.getStatusCode());
        }
        System.out.println(prepdata);
        return prepdata;
    }

    @ResponseBody
    @RequestMapping(value = "/getPayablePrepData", method = RequestMethod.GET)
    public EbsPayrollDataHeaderInvoice[] getPayablePrepData() {
        
    	EbsPayrollDataHeaderInvoice[] pyblprepdata=null;
        headers.setContentType(MediaType.APPLICATION_JSON);
        String urlpreppybl = appgateway.getAppgateway_payroll() + "/api/ebspayrolinvoice/getpayrollInvoicePreparedData";
        System.out.println("INSIDE PAYABLE PREP GET FUNCTION"+urlpreppybl);
        HttpEntity<String> req = new HttpEntity<String>(headers);

        ResponseEntity<EbsPayrollDataHeaderInvoice[]> response = restTemplate.exchange(urlpreppybl, HttpMethod.GET, req, EbsPayrollDataHeaderInvoice[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            pyblprepdata = response.getBody();
        } else {
            System.out.println("Request Failed.");
            System.out.println(response.getStatusCode());
        }
        System.out.println(pyblprepdata);
        return pyblprepdata;
    }
}
