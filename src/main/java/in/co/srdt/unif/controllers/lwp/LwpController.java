package in .co.srdt.unif.controllers.lwp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import in .co.srdt.unif.model.login.Login;
import in .co.srdt.unif.model.newperson.Newperson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import in .co.srdt.unif.model.create.CreateAor;
import in .co.srdt.unif.model.create.SingleResponseModel;
import in .co.srdt.unif.model.lwp.CreateLwp;
import in .co.srdt.unif.model.lwp.CreateLwpWrapper;
import in .co.srdt.unif.model.search.PersonRecordSearch;
import in .co.srdt.unif.utils.ApplicationGateway;

import org.springframework.http.ResponseEntity;

@Controller
@SessionAttributes("pid")
@RequestMapping("/lwp")
public class LwpController {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private HttpHeaders headers;

  @Autowired
  private ApplicationGateway appgateway;

  LwpController() {

  }

  LwpController(HttpHeaders headers, RestTemplate restTemplate) {
    this.headers = headers;
    this.restTemplate = restTemplate;
  }

  @RequestMapping("/manageLwp")
  public String manageaLwp(Model model, HttpServletRequest req) {
    String pnum = (String) req.getSession().getAttribute("pnum");
    //System.out.println("::::"+pnum);
    model.addAttribute("pnum", pnum);
    return "fragments/lwp/manageLwp :: manageLwp";
  }

  @RequestMapping("/voidLwp/{pnum}")
  public String voidLwp(@PathVariable String pnum, Model model, HttpServletRequest request) {
    String url = appgateway.getAppgatewayabs() + "/Person/LWP/getAllLeaveWithoutPayByPersonNumber/" + pnum;
    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
    HttpEntity < String > req = new HttpEntity < > (headers);

    CreateLwp[] res = null;
    CreateLwpWrapper lwp = new CreateLwpWrapper();
    ResponseEntity < CreateLwp[] > responsesave = restTemplate.exchange(url, HttpMethod.GET, req, CreateLwp[].class);

    if (responsesave.getStatusCode() == HttpStatus.OK) {
      res = responsesave.getBody();
    } else {
      System.out.println("Request Failed");
    }
    model.addAttribute("Personnumber", pnum);
    model.addAttribute("CreateLwp", res);

    //System.out.println("::::::::::in");

    return "fragments/lwp/manageLwp :: voidLwp";
  }

  @RequestMapping("/activeLwp")
  public String activeLwp(Model model, HttpServletRequest req, @ModelAttribute("pnum") String pnum) {
    model.addAttribute("Personnumber", pnum);
    return "fragments/lwp/manageLwp :: activeLwp";
  }

  /** EDITTED BY SNIGDHAA ABOVE THIS ON 30-OCT-2020 **/
  @ResponseBody
  @RequestMapping("/SearchPersonPopup")
  public PersonRecordSearch[] personRecordSearchs(HttpServletRequest requestFromDT) {

    String persondepartment = requestFromDT.getParameter("persondepartment");
    String personid = requestFromDT.getParameter("personid");
    String personlegalentity = requestFromDT.getParameter("personlegalentity");
    String personname = requestFromDT.getParameter("personname");
    String personnumber = requestFromDT.getParameter("personnumber");

    //System.out.println("PersonRecordSearch ");
    PersonRecordSearch[] personRecordSearchs = null;

    String urlperson = appgateway.getAppgateway() + "/PersonData/PersonalRecord/getPersonalRecordBySearch";
    PersonRecordSearch[] mgrObj = null;
    String payLode = "{" +
      "\"persondepartment\"" + ":\"" + persondepartment + "\"," +
      "\"personid\"" + ":\"" + personid + "\"," +
      "\"personlegalentity\"" + ":\"" + personlegalentity + "\"," +
      "\"personname\"" + ":\"" + personname + "\"," +
      "\"personnumber\"" + ":\"" + personnumber + "\"" +
      "}";

    //	System.out.println("Payload::::" + payLode);

    headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

    //headers.add("Authorization", AppConstants.ACCESS_TOKEN);
    HttpEntity < String > request = new HttpEntity < String > (payLode, headers);

    ResponseEntity < PersonRecordSearch[] > response = restTemplate.exchange(urlperson, HttpMethod.POST, request, PersonRecordSearch[].class);

    if (response.getStatusCode() == HttpStatus.OK) {
      personRecordSearchs = response.getBody();

    } else {
      System.out.println("Request Failed");
      System.out.println(response.getStatusCode());
    }

    return personRecordSearchs;

  }

  @PostMapping(value = "/saveLWP")
  public @ResponseBody SingleResponseModel saveLwp(@ModelAttribute("createLwpWrapper") CreateLwpWrapper createLwpWrapper, HttpServletRequest req, Model model, @ModelAttribute("pid") String pid) {
    Login login = (Login) req.getSession().getAttribute("login");
    System.out.println("in save method");
    String pnum = (String) req.getSession().getAttribute("pnum");
    //System.out.println("::::"+pnum);
    model.addAttribute("pnum", pnum);
    for (int i = 0; i < createLwpWrapper.getCreatelwp().size(); i++) {
      System.out.println("count::: " + i);

      createLwpWrapper.getCreatelwp().get(i).setPersonid(pid);
      createLwpWrapper.getCreatelwp().get(i).setPersonnumber(pnum);

      createLwpWrapper.getCreatelwp().get(i).setCreatedby(login.getEmplid());
      createLwpWrapper.getCreatelwp().get(i).setUpdatedby(login.getEmplid());

    }
    System.out.println("data::: " + createLwpWrapper.getCreatelwp().toString());
    SingleResponseModel res = new SingleResponseModel();
    String urlLWP = appgateway.getAppgatewayabs() + "/Person/LWP/saveAllLeaveWithoutPay";
    HttpEntity < List < CreateLwp >> request2 = new HttpEntity < List < CreateLwp >> (createLwpWrapper.getCreatelwp(), headers);

    ResponseEntity < SingleResponseModel > responsesave = restTemplate.exchange(urlLWP, HttpMethod.POST, request2, SingleResponseModel.class);
    if (responsesave.getStatusCode() == HttpStatus.OK) {
      res = responsesave.getBody();
      System.out.println("response" + res.getMessage());
      System.out.println("response" + res.getStatus());
    } else {
      System.out.println("Request Failed");
    }

    //System.out.println("res:: "+res.toString());
    model.addAttribute("response", res);
    //System.out.println("in save method end");
    return res;
  }

  @GetMapping("/removechild/{index}/{flg}")
  public @ResponseBody String removechild(@ModelAttribute("createLwpWrapper") CreateLwpWrapper createLwpWrapper, @PathVariable int index) {
    System.out.println("flg : " + index);
    //	createLwpWrapper.getCreatelwp().remove(index);
    return "removed";
  }

}