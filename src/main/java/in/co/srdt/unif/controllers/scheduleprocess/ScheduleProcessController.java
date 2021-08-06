package in.co.srdt.unif.controllers.scheduleprocess;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.elementautoassign.AutoAssignSearch;
import in.co.srdt.unif.model.payroll.IdentifiedEmployees;
import in.co.srdt.unif.model.payroll.IdentifiedEmployeesWrapper;
import in.co.srdt.unif.model.payroll.PaygroupLOV;
import in.co.srdt.unif.model.scheduleprocess.AutoIncrementWrapper;
import in.co.srdt.unif.model.scheduleprocess.EmployeeAutoIncrement;
import in.co.srdt.unif.model.search.BUsearchresult;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/scheduleprocess")
public class ScheduleProcessController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationGateway appgateway;

    @Autowired
    private HttpHeaders headers;

    @Autowired
    SmartValidator validator;

    private List<EmployeeAutoIncrement> emplauto;
    private EmployeeAutoIncrement[] emplarr;
    private List<EmployeeAutoIncrement> emplarrfinal = new ArrayList<>();
    private int monthh;
    private String yearr;

    @RequestMapping("/managescheduleprocess")
    public String managescheduleprocess(Model model) {

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        return "fragments/scheduleprocess/managescheduleprocess :: managescheduleprocess";
    }

    @RequestMapping("/manageemployeeautoincrement/{mode}")
    public String manageemployeeautoincrement(Model model, @PathVariable("mode") String mode) {

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        CommonLOV[] nature_of_employement = null;
        String urlnatofempl = appgateway.getAppgateway() + "/General/loadNatureOfEmployement";
        ResponseEntity<CommonLOV[]> response25 = restTemplate.exchange(urlnatofempl, HttpMethod.GET, request,
                CommonLOV[].class);
        if (response25.getStatusCode() == HttpStatus.OK) {
            nature_of_employement = response25.getBody();
        }
        model.addAttribute("natempl", nature_of_employement);

        BUsearchresult[] buobj = null;
        String urlbu = appgateway.getAppgateway()+"/BusinessUnit/BusinessUnitSearchList";
        String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\","
                + "\"status\"" + ":\"\"" + "}";
        HttpEntity<String> request1 = new HttpEntity<String>(payload, headers);
        ResponseEntity<BUsearchresult[]> response = restTemplate.exchange(urlbu, HttpMethod.POST, request1,
                BUsearchresult[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            buobj = response.getBody();
        }
        model.addAttribute("bures", buobj);
        model.addAttribute("mode",mode);
        return "fragments/scheduleprocess/employeeautoincrement :: employeeautoincrement";
    }


    @ResponseBody
    @RequestMapping(value = "/searincrempl/{bu}/{natempl}/{month}/{year}", method = RequestMethod.GET)
    public EmployeeAutoIncrement[] getapprovalactions(@PathVariable("natempl")String natempl, @PathVariable("bu") String bu, @PathVariable("month") String month,@PathVariable("year") String year) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        EmployeeAutoIncrement[] res =null;

        monthh=getMonthNumber(month);
        yearr=year;
        String urlincr = appgateway.getAppgateway()+"/api/autoincrement/getalljobdataforincrement/"+natempl+"/"+bu+"/"+month;

        //System.out.println(urlincr);
        //System.out.println("URL :: "+urlasgn);
        HttpEntity<String> req = new HttpEntity<String>(headers);
        ResponseEntity<EmployeeAutoIncrement[]> response = restTemplate.exchange(urlincr, HttpMethod.GET, req, EmployeeAutoIncrement[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
            emplarr=res;
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return res;
    }


    private int getMonthNumber(String monthName) {
        return Month.valueOf(monthName.toUpperCase()).getValue()-1;
    }


    @ResponseBody
    @RequestMapping(value = "/searchemplbck", method = RequestMethod.GET)
    public EmployeeAutoIncrement[] searchemplbck() {
        return emplarr;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(1024);
    }



    @RequestMapping(value = "/saveautoincr", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveautoincr(AutoIncrementWrapper autoIncrementWrapper, Model model) {
        emplarrfinal.clear();
        emplauto=autoIncrementWrapper.getEmployeeAutoIncrements();
        String[] days={"-01-01","-02-01","-03-01","-04-01","-05-01","-06-01","-07-01","-08-01","-09-01","-10-01","-11-01","-12-01"};
        String effdate=yearr+days[monthh];
        //System.out.println(effdate);
        for(int x=0;x<emplauto.size();x++){
            if(emplauto.get(x).getFlag().equals("on")){
                emplarr[x].setFlag("on");
                emplarr[x].setEffectivestartdate(effdate);
                emplarr[x].setEffectiveenddate("4712-12-31");
                emplarrfinal.add(emplarr[x]);
            }else{
                emplarr[x].setFlag("off");
            }
        }

        for (int y=0; y<emplarrfinal.size(); y++){
            System.out.println("EMPLARRFINAL :: "+emplarrfinal.get(y).toString());
        }

        model.addAttribute("incrdata",emplarrfinal);
        return "fragments/scheduleprocess/saveautoincr :: saveautoincr";
    }

    @ResponseBody
    @RequestMapping(value = "/saveautoincrfinal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveautoincrfinal(AutoIncrementWrapper autoIncrementWrapper, Model model) {
        String url = appgateway.getAppgateway()+"/api/autoincrement/updatejobdetails";
        SingleResponseModel res=null;
        System.out.println("FINAL DATA"+autoIncrementWrapper.toString());
        HttpEntity<List<EmployeeAutoIncrement>> request = new HttpEntity<List<EmployeeAutoIncrement>>(autoIncrementWrapper.getEmployeeAutoIncrements(), headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(url, HttpMethod.PUT, request, SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res=response.getBody();
        } else {
            System.out.println(response.getStatusCode());
        }
        System.out.println(res.toString());
        return res.getStatus();
    }



}
