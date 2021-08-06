package in.co.srdt.unif.controllers.elementautoassign;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.elementautoassign.AutoAssignSearch;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.mss.ApprovalActions;
import in.co.srdt.unif.model.payroll.PaygroupLOV;
import in.co.srdt.unif.model.taxdeclaration.chapter6A.Chapter6AWrapper;
import in.co.srdt.unif.model.taxdeclaration.hra.HouseRentModel;
import in.co.srdt.unif.model.taxdeclaration.interesthomeloan.PayTaxEmplList;
import in.co.srdt.unif.model.taxdeclaration.previousemplincome.PreviousEmployementModel;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/autoassign")
public class AutoAssignController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationGateway appgateway;

    @Autowired
    private HttpHeaders headers;

    @Autowired
    SmartValidator validator;

    private AutoAssignSearch[] assignsave=null;

    @RequestMapping("/loadautoassign")
    public String loadautoassign(Model model) {

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        CommonLOV[] nature_of_employement = null;
        String urlnatofempl = appgateway.getAppgateway() + "/General/loadNatureOfEmployement";
        ResponseEntity<CommonLOV[]> response25 = restTemplate.exchange(urlnatofempl, HttpMethod.GET, request,
                CommonLOV[].class);
        if (response25.getStatusCode() == HttpStatus.OK) {
            nature_of_employement = response25.getBody();
        }
        PaygroupLOV[] paygroup = null;
        String urlpaygroup = appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
        ResponseEntity<PaygroupLOV[]> response31 = restTemplate.exchange(urlpaygroup, HttpMethod.GET, request,
                PaygroupLOV[].class);
        if (response31.getStatusCode() == HttpStatus.OK) {
            paygroup = response31.getBody();
        }
        model.addAttribute("natempl", nature_of_employement);
        model.addAttribute("paygrp", paygroup);

        return "fragments/elementautoassign/autoassign :: autoassign";
    }


    @ResponseBody
    @RequestMapping(value = "/searchassign/{pygrp}/{natempl}/{hiredate}", method = RequestMethod.GET)
    public AutoAssignSearch[] getapprovalactions(@PathVariable("pygrp")String pygrp, @PathVariable("natempl")String natempl, @PathVariable("hiredate") String hiredate) {

        //System.out.println("PYGRP  :" +pygrp);
        //System.out.println("NATEMPL:" +natempl);
        //System.out.println("HRDATE :" +hiredate);

        headers.setContentType(MediaType.APPLICATION_JSON);
        AutoAssignSearch[] res =null;
        String urlasgn = appgateway.getAppgateway()+"/PearsonData/Pyaroll/getElementMappingForAutoassign/"+pygrp+"/"+natempl+"/"+hiredate;
        //System.out.println("URL :: "+urlasgn);
        HttpEntity<String> req = new HttpEntity<String>(headers);
        ResponseEntity<AutoAssignSearch[]> response = restTemplate.exchange(urlasgn, HttpMethod.GET, req, AutoAssignSearch[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
            assignsave = res;
            System.out.println(response.getStatusCode());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        System.out.println(res.toString());
        return res;
    }



    @RequestMapping("/autoassignsave")
    public String saveautoassign(Model model, HttpServletRequest req) {

        headers.setContentType(MediaType.APPLICATION_JSON);
        Login login = (Login)req.getSession().getAttribute("login");
        SingleResponseModel res =null;
        String urlsave=appgateway.getAppgateway_payroll()+"/api/element/autoElementmapping";
        System.out.println("Length :: "+assignsave.length);

        for (int z=0; z<assignsave.length; z++){
            assignsave[z].setEfectenddate("4712-12-31");
            System.out.println(assignsave[z].toString());
        }

        HttpEntity<AutoAssignSearch[]> request1 = new HttpEntity<>(assignsave, headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlsave, HttpMethod.POST, request1, SingleResponseModel.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
        } else {
            System.out.println(response.getStatusCode());
            model.addAttribute("result", "LOG_ERROR");
            return "fragments/elementautoassign/autoassign :: autoassign";
        }
        //System.out.println("RESPONSE ::" +res.getStatus());
        model.addAttribute("result", res.getMessage());
        return "fragments/elementautoassign/autoassign :: autoassign";
    }

}
