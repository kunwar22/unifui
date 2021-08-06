package in .co.srdt.unif.controllers.payroll;

import in .co.srdt.unif.model.payroll.*;
import in .co.srdt.unif.utils.ApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ElementAssignmentController {

  @Autowired
  ApplicationGateway appgateway;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  HttpHeaders headers;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.setAutoGrowCollectionLimit(768);
  }

  @GetMapping("/payrollprocessing/payeedata/elementassignment")
  public String getEmployeeAssignmentPage() {
    return "forms/payroll/transactions/elementAssignment :: elementAssignment";
  }

  @ResponseBody
  @GetMapping("/payrollprocessing/payeedata/getpaygroups")
  public PayGroups[] getPayGroups() {
    PayGroups[] payGroups = null;
    String paygroupURL = appgateway.getAppgateway_payroll() + "/api/payrollsetup/getpaygroups";

    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity < String > request = new HttpEntity < String > (headers);

    ResponseEntity < PayGroups[] > response = restTemplate.exchange(paygroupURL, HttpMethod.GET, request, PayGroups[].class);

    if (response.getStatusCode() == HttpStatus.OK) {
      payGroups = response.getBody();
    } else {
      System.out.println("Request Failed");
      System.out.println(response.getStatusCode());
    }

    return payGroups;
  }

  @ResponseBody
  @GetMapping("/payrollprocessing/payeedata/employeelist/{paygroupid}")
  public Employee[] getEmployee(@PathVariable("paygroupid") String paygroupid) {
    Employee[] employee = null;
    String employeeURL = appgateway.getAppgateway() + "/PearsonData/Pyaroll/getAllPersonByPayGroup/" + paygroupid;

    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity < String > request = new HttpEntity < String > (headers);

    ResponseEntity < Employee[] > response = restTemplate.exchange(employeeURL, HttpMethod.GET, request, Employee[].class);

    if (response.getStatusCode() == HttpStatus.OK) {
      employee = response.getBody();
    } else {
      System.out.println("Request Failed");
      System.out.println(response.getStatusCode());
    }

    return employee;
  }

  @ResponseBody
  @GetMapping("/payrollprocessing/payeedata/employeedetails/{personnumber}")
  public Employee getEmployeeDetails(@PathVariable("personnumber") String personnumber) {
    Employee employee = null;
    String employeeDetailsURL = appgateway.getAppgateway() + "/PersonDetails/getPersonInfo/" + personnumber;

    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity < String > request = new HttpEntity < String > (headers);

    ResponseEntity < Employee > response = restTemplate.exchange(employeeDetailsURL, HttpMethod.GET, request, Employee.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      employee = response.getBody();
    } else {
      System.out.println("Request Failed");
      System.out.println(response.getStatusCode());
    }

    return employee;
  }

  @ResponseBody
  @GetMapping("/payrollprocessing/payeedata/elementbypersonid/{personnumber}")
  public ElementMapping[] getElementMappingByPerson(@PathVariable("personnumber") String personnumber) {
    ElementMapping[] elementMapping = null;
    ElementList[] elementList = null;

    String elementMappingURL = appgateway.getAppgateway_payroll() + "/api/element/getpersonelementmappingbyperson/" + personnumber;
    String elementListURL = appgateway.getAppgateway_payroll() + "/api/element/getelements";

    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity < String > request = new HttpEntity < String > (headers);

    ResponseEntity < ElementMapping[] > response = restTemplate.exchange(elementMappingURL, HttpMethod.GET, request, ElementMapping[].class);
    ResponseEntity < ElementList[] > response2 = restTemplate.exchange(elementListURL, HttpMethod.GET, request, ElementList[].class);

    if (response.getStatusCode() == HttpStatus.OK) {
      elementMapping = response.getBody();
    } else {
      System.out.println("Request Failed");
      System.out.println(response.getStatusCode());
    }

    if (response2.getStatusCode() == HttpStatus.OK) {
      elementList = response2.getBody();
    } else {
      System.out.println("Request Failed");
      System.out.println(response2.getStatusCode());
    }
    String optionsString = "";
    for (int j = 0; j < elementList.length; j++) {
      optionsString += "<option value='" + elementList[j].getElementid() + "'>" + elementList[j].getName() + "</option>";
    }
    for (int i = 0; i < elementMapping.length; i++) {
      elementMapping[i].setOptionlist(optionsString);
    }
    return elementMapping;
  }

  @ResponseBody
  @RequestMapping(value = "/payrollprocessing/payeedata/elementassignment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public Map < String, String > saveUpdateElementAssignment(@ModelAttribute("elementMapping") ElementMappingWrapper elementMappingWrapper) {

    Map < String, String > res = new HashMap < > ();

    System.out.println(elementMappingWrapper.getElementMapping().toString());

    String successMessage = "Element Assignment Successfully Done.";
    String errorMessage = "There was and error in assigning element.";

    String url = appgateway.getAppgateway_payroll() + "/api/element/elementmapping";

    HttpEntity < List < ElementMapping >> request = new HttpEntity < List < ElementMapping >> (elementMappingWrapper.getElementMapping(), headers);

    ResponseEntity < String > response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      res.put("message", successMessage);
    } else {
      res.put("message", errorMessage);
    }

    // getEmployeeDetails(personnumber)
    return res;
  }

  @ResponseBody
  @GetMapping("/payrollprocessing/payeedata/getelements")
  public ElementList[] getElementList() {
    ElementList[] elementList = null;
    String elementMappingURL = appgateway.getAppgateway_payroll() + "/api/element/getelements";

    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity < String > request = new HttpEntity < String > (headers);

    ResponseEntity < ElementList[] > response = restTemplate.exchange(elementMappingURL, HttpMethod.GET, request, ElementList[].class);

    if (response.getStatusCode() == HttpStatus.OK) {
      elementList = response.getBody();
      System.out.println(elementList[0].getName());
    } else {
      System.out.println("Request Failed");
      System.out.println(response.getStatusCode());
    }

    return elementList;
  }

  /************code added by rajat start********************************/
  @ResponseBody
  @RequestMapping(value = "/setuphrms/payrollsetup/payrollelements/saveelementmapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String saveelementmapping(@ModelAttribute("ElementMappingWrapper") ElementMappingWrapper elementMappingWrapper, Model model) {
    String successMessage = "Element Mapping successfully saved.";
    String errorMessage = "There was an error in saving the element Mapping.";
    System.out.println("data element Mapping::" + elementMappingWrapper.toString());
    String endpointurl = appgateway.getAppgateway_payroll() + "/api/element/elementmapping";
    HttpEntity < List < ElementMapping >> request = new HttpEntity < List < ElementMapping >> (elementMappingWrapper.getElementMapping(), headers);

    ResponseEntity < String > response = restTemplate.exchange(endpointurl, HttpMethod.POST, request, String.class);

    String strpersonnumber = elementMappingWrapper.getElementMapping().get(0).getPersonnumber();
    System.out.println("person number::" + strpersonnumber);

    if (response.getStatusCode() == HttpStatus.OK) {
      System.out.println("saved element mapping");
      getElementMappingByPerson(strpersonnumber);
      return successMessage;

    } else {

      return errorMessage;
    }

  }
  /*************code added by rajat end****************************/

}