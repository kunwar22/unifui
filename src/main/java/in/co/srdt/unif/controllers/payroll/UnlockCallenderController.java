package in.co.srdt.unif.controllers.payroll;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UnlockCallenderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;
    
    @Autowired
    ApplicationGateway appgateway;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(3500);
    }
    
    @GetMapping("/finalizeunlockcalendar")
    public String getUnlockPayrollCallender(Model model){

        SingleResponseModel status = new SingleResponseModel();
        String urlclaimId = appgateway.getAppgateway_payroll() + "/api/runpayroll/getcalenderstatus";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlclaimId, HttpMethod.GET, request,
                SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            status = response.getBody();
        }
        model.addAttribute("claimId", status);
        
        
        CommonLOV[] lockedCalender = null;
		String urllockedCalender = appgateway.getAppgateway_payroll() + "/api/runpayroll/getLockedCalcodelist";
		//String urllockedCalender = "http://localhost:9127/api/runpayroll/getLockedCalcodelist";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestveh = new HttpEntity<String>(headers);
		ResponseEntity<CommonLOV[]> responseveh = restTemplate.exchange(urllockedCalender, HttpMethod.GET, requestveh,
				CommonLOV[].class);

		if (responseveh.getStatusCode() == HttpStatus.OK) {
			lockedCalender = responseveh.getBody();
		}
		model.addAttribute("lockedCalender", lockedCalender);
        return "forms/payroll/transactions/unlockPayrollCalender :: payrollCalenderUnlock";
    }


    @ResponseBody
    @GetMapping("/payrollprocessing/unlockPayrollCalender/{calid}")
    public Map<String, String> unlockPayrollCalender(@PathVariable("calid") String calid, Model model) {

        Map<String, String> res = new HashMap<>();
        SingleResponseModel status = new SingleResponseModel();
        String urlcalUnlock = appgateway.getAppgateway_payroll() + "/api/runpayroll/unlockcalendar/"+ calid;;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlcalUnlock, HttpMethod.GET, request,
                SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("unlockPayrollCalender", response.getBody().getMessage());
        }
        return res;

    }


    @ResponseBody
    @GetMapping("/payrollprocessing/finalizePayrollCalender/{calid}")
    public Map<String, String> finalizePayrollCalender(@PathVariable("calid") String calid, Model model) {
        Map<String, String> res = new HashMap<>();
        SingleResponseModel status = new SingleResponseModel();
        String urlfinalizecal = appgateway.getAppgateway_payroll() + "/api/runpayroll/finalizedcalendar/"+ calid;;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<SingleResponseModel> response = restTemplate.exchange(urlfinalizecal, HttpMethod.GET, request,
                SingleResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("finalizePayrollCalender", response.getBody().getMessage());
        }
        return res;

    }


}
