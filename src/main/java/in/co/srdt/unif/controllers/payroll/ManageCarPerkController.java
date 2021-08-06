package in.co.srdt.unif.controllers.payroll;

import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.payroll.ManageCarPerk;
import in.co.srdt.unif.model.user.PersonInformation;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManageCarPerkController {

    @Autowired
    private ApplicationGateway appgateway;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    @GetMapping("/managecarperk")
    public String getManageCarPerkPage(Model model, HttpServletRequest req) {

        Login login = (Login) req.getSession().getAttribute("login");
        PersonInformation personInformation = null;
        String url = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + login.getEmplid();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestPesro = new HttpEntity<String>(httpHeaders);
        ResponseEntity<PersonInformation> responsePerso = restTemplate.exchange(url, HttpMethod.GET, requestPesro,
                PersonInformation.class);
        if (responsePerso.getStatusCode() == HttpStatus.OK) {
            personInformation = responsePerso.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(responsePerso.getStatusCode());
        }
        model.addAttribute("personInfo", personInformation);
        return "forms/payroll/transactions/manageCarPerk :: carPerk";
    }

    @ResponseBody
    @RequestMapping(value = "getperqList/getdata")
    public ManageCarPerk[] carParqList(HttpServletRequest request, Model model) {
        ManageCarPerk[] manageCarPerks = null;
        String urlcarPerq = appgateway.getAppgateway_payroll() + "/api/paycarperk/getcarperk";
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request1 = new HttpEntity<String>(httpHeaders);
        ResponseEntity<ManageCarPerk[]> response = restTemplate.exchange(urlcarPerq, HttpMethod.GET, request1,
                ManageCarPerk[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            manageCarPerks = response.getBody();

        }
        return manageCarPerks;
    }

    @ResponseBody
    @RequestMapping(value = "/carperq/savecarperq", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String savePayrollElement(@ModelAttribute("manageCarPerk") ManageCarPerk manageCarPerk, HttpServletRequest req) {
        String successMessage = "Car Perq successfully saved.";
        String errorMessage = "There was an error in saving the car perq.";
       
        Login login = (Login) req.getSession().getAttribute("login");
        manageCarPerk.setCreatedBy(login.getEmplid());
        System.out.println("manageCarPerk "+manageCarPerk);
        HttpEntity<ManageCarPerk> request = new HttpEntity<ManageCarPerk>(manageCarPerk, httpHeaders);
        String endpointurl = appgateway.getAppgateway_payroll() + "/api/paycarperk/addcarperk";

        ResponseEntity<String> response = restTemplate.exchange(endpointurl, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return successMessage;
        } else {
            return errorMessage;
        }
    }


}