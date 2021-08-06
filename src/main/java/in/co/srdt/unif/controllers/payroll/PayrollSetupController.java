package in.co.srdt.unif.controllers.payroll;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.payroll.*;
import in.co.srdt.unif.utils.ApplicationGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class PayrollSetupController {

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
    
    @GetMapping("/setuphrms/payrollsetup/salarycomponents/componentstype")
    public String getSalaryComponentTypes(){
        return "forms/payroll/setup/searchSalaryComponentTypes :: searchSalaryComponentTypes";
    }

    @ResponseBody
    @PostMapping("/setuphrms/payrollsetup/salarycomponents/searchcomponentstype")
    public ComponentType[] searchSalaryComponentTypes(HttpServletRequest searchComponentType) {
        ComponentType[] componentType = null;
        String url = appgateway.getAppgateway_payroll()+"/api/payrollsetup/searchcomptype";

        String payLode = "{" +
                "\"code\"" + ":\"" +searchComponentType.getParameter("componentCode")+ "\"," +
                "\"name\"" + ":\"" +searchComponentType.getParameter("componentName")+ "\"" +
                "}";
        //System.out.println(payLode);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

        ResponseEntity<ComponentType[]> response = restTemplate.exchange(url, HttpMethod.POST, request, ComponentType[].class);

        if(response.getStatusCode() == HttpStatus.OK) {
            componentType = response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return componentType;
    }

    @GetMapping("/setuphrms/payrollsetup/salarycomponents/addcomponentstype")
    public String addSalaryComponentTypes(Model model){
        ComponentType componentType = new ComponentType();
        componentType.setComponentid("");
        model.addAttribute("componentType", componentType);
        return "forms/payroll/setup/addSalaryComponentTypes :: addSalaryComponentTypes";
    }

    @ResponseBody
    @PostMapping("/setuphrms/payrollsetup/salarycomponents/addcomponentstype")
    public String addNewSalaryComponentTypes(@ModelAttribute("componentType") ComponentType componentType){
        //System.out.println(componentType.toString());
        String message = "Component Type added successfully.";

        HttpEntity<ComponentType> request = new HttpEntity<ComponentType>(componentType, headers);
        String url = appgateway.getAppgateway_payroll()+"/api/payrollsetup/createcomptype";

        ResponseEntity<ComponentType> response = restTemplate.exchange(url, HttpMethod.POST,request, ComponentType.class);

        if(response.getStatusCode() == HttpStatus.OK){
            //System.out.println(response.toString());
            return message;
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
            return "ERROR";
        }
    }

    @ResponseBody
    @GetMapping("/setuphrms/payrollsetup/payrollelements/getAllComponentType")
    public ComponentType[] getAllComponentType(){
        ComponentType[] componentType = null;
        String url = appgateway.getAppgateway_payroll()+"/api/payrollsetup/getcomptypes";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<ComponentType[]> response = restTemplate.exchange(url, HttpMethod.GET, request, ComponentType[].class);

        if(response.getStatusCode() == HttpStatus.OK) {
            componentType = response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return componentType;
    }


    @GetMapping("/setuphrms/payrollsetup/salarycomponents/salarycomponent")
    public String getSalaryComponent(){
        return "forms/payroll/setup/searchSalaryComponent :: searchSalaryComponent";
    }

    @ResponseBody
    @PostMapping("/setuphrms/payrollsetup/salarycomponents/searchsalarycomponent")
    public SalaryComponentSearch[] searchSalaryComponent(HttpServletRequest searchComponent) {
        SalaryComponentSearch[] salaryComponentSearch = null;
        String url = appgateway.getAppgateway_payroll()+"/api/payrollsetup/searchsalarycomp";

        String payLode = "{" +
                "\"componentid\"" + ":\"" +searchComponent.getParameter("componentid")+ "\"," +
                "\"name\"" + ":\"" +searchComponent.getParameter("name")+ "\"" +
                "}";
        //System.out.println(payLode);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

        ResponseEntity<SalaryComponentSearch[]> response = restTemplate.exchange(url, HttpMethod.POST, request, SalaryComponentSearch[].class);

        if(response.getStatusCode() == HttpStatus.OK) {
            salaryComponentSearch = response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return salaryComponentSearch;
    }

    @GetMapping("/setuphrms/payrollsetup/salarycomponents/addsalarycomponent")
    public String addSalaryComponent(Model model){
        SalaryComponent salaryComponent = new SalaryComponent();
        salaryComponent.setSalarycompid("");
        model.addAttribute("salaryComponent", salaryComponent);
        return "forms/payroll/setup/addSalaryComponent :: addSalaryComponent";
    }

    @ResponseBody
    @PostMapping("/setuphrms/payrollsetup/salarycomponents/addsalarycomponent")
    public String addNewSalaryComponent(@ModelAttribute("salaryComponent") SalaryComponent salaryComponent){

       // System.out.println(salaryComponent.toString());
        String message = "Salary Component added successfully.";

        HttpEntity<SalaryComponent> request = new HttpEntity<SalaryComponent>(salaryComponent, headers);
        String url = appgateway.getAppgateway_payroll()+"/api/payrollsetup/createsalarycomp";

        ResponseEntity<SalaryComponent> response= restTemplate.exchange(url, HttpMethod.POST,request, SalaryComponent.class);

        if(response.getStatusCode() == HttpStatus.OK){
            //System.out.println(response.toString());
            return message;
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
            return "ERROR";
        }
    }

    @GetMapping("/setuphrms/payrollsetup/payrollelements/payrollgroups")
    public String getPayrollGroups(){
        return "forms/payroll/setup/searchPayrollGroup :: searchPayrollGroup";
    }

    @ResponseBody
    @GetMapping("/setuphrms/payrollsetup/payrollelements/getpayrollgroup")
    public PayGroups[] SavePayGroup(){
        PayGroups[] payGroups = null;
        String url = appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<PayGroups[]> response = restTemplate.exchange(url, HttpMethod.GET, request, PayGroups[].class);

        if(response.getStatusCode() == HttpStatus.OK) {
            payGroups = response.getBody();
         //   System.out.println(payGroups[0].getName());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return payGroups;
    }

    @GetMapping("/setuphrms/payrollsetup/payrollelements/addpayrollgroups")
    public String addPayrollGroups(){
        return "forms/payroll/setup/addPayrollGroup :: addPayrollGroup";
    }

    @ResponseBody
    @RequestMapping(value="/setuphrms/payrollsetup/payrollcalendar/savepayrollgroup", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String savePayrollGroup(@ModelAttribute("payGroups") PayGroups payGroups){
        String successMessage = "Paygroup Added Successfully.";
        String errorMessage = "There was an error saving the paygroup.";
       // System.out.println(payGroups.toString());
        String url = appgateway.getAppgateway_payroll()+"/api/payrollsetup/createpaygroup";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PayGroups> request = new HttpEntity<PayGroups>(payGroups, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            return successMessage;
        } else {
            return errorMessage;
        }
    }

    @GetMapping("/setuphrms/payrollsetup/payrollelements/savedcalculations")
    public String getSavedCalculations(){
        return "forms/payroll/setup/searchSavedCalculations :: searchSavedCalculations";
    }

    @GetMapping("/setuphrms/payrollsetup/payrollelements/addsavedcalculations")
    public String addSavedCalculations(){
        return "forms/payroll/setup/addSavedCalculations :: addSavedCalculations";
    }

    @GetMapping("/setuphrms/payrollsetup/payrollcalendar/calendar")
    public String getPayrollCalendar(Model model){
        PayGroups[] paygroups = null;
        String url = appgateway.getAppgateway_payroll()+"/api/payrollsetup/getpaygroups";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<PayGroups[]> response = restTemplate.exchange(url, HttpMethod.GET, request, PayGroups[].class);

        if(response.getStatusCode() == HttpStatus.OK) {
            paygroups = response.getBody();
           // System.out.println(paygroups[0].getName());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        model.addAttribute("payGroups", paygroups);

        FinancialYearLOV[] finyearlov =null;
        String urlfinancialyear = appgateway.getAppgateway_payroll() + "/api/paycalendar/getfinancialyears";
        ResponseEntity<FinancialYearLOV[]> response1 = restTemplate.exchange(urlfinancialyear, HttpMethod.GET, request, FinancialYearLOV[].class);
        if (response1.getStatusCode() == HttpStatus.OK) {
            finyearlov = response1.getBody();
        }
        model.addAttribute("finyear",finyearlov);
       // System.out.println(finyearlov.toString());
        return "forms/payroll/setup/searchPayrollCalendar :: searchPayrollCalendar";
    }

    @ResponseBody
    @GetMapping("/setuphrms/payrollsetup/payrollcalendar/calendar/{paygroupid}")
    public CalendarPeriod[] getPayCalendar(@PathVariable("paygroupid") String paygroupid){
        CalendarPeriod[] calendarPeriods = null;
        String url = appgateway.getAppgateway_payroll()+"/api/paycalendar/getpayrollcalendarsbypaygroupid/" + paygroupid;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<CalendarPeriod[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CalendarPeriod[].class);

        if(response.getStatusCode() == HttpStatus.OK) {
            calendarPeriods = response.getBody();
            //System.out.println(calendarPeriods[0].getDays());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return calendarPeriods;
    }

    @GetMapping("/setuphrms/payrollsetup/payrollcalendar/addpayrollcalendar")
    public String addPayrollCalendar(Model model){
        return "forms/payroll/setup/addPayrollCalendar :: addPayrollCalendar";
    }


    @ResponseBody
    @RequestMapping(value="/setuphrms/payrollsetup/payrollcalendar/addpayrollcalendar", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String savePayrollCalendar(@ModelAttribute("calendarPeriod") CalendarPeriod calendarPeriod){
       // System.out.println(calendarPeriod.toString());
        String message = "Pay Calendar Added Successfully.";

        HttpEntity<List<CalendarPeriod>> request = new HttpEntity<List<CalendarPeriod>>(Collections.singletonList(calendarPeriod), headers);
        String url = appgateway.getAppgateway_payroll()+"/api/paycalendar/createcalendar";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            //System.out.println(response.toString());
            return message;
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
            return "ERROR";
        }
    }

    @GetMapping("/setuphrms/payrollsetup/payrollelements/payrollelements")
    public String loadPayrollElementPage(){
        return "forms/payroll/setup/searchPayrollElement :: searchPayrollElement";
    }

    @GetMapping("/setuphrms/payrollsetup/payrollelements/addpayrollelements")
    public String addPayrollElementPage(Model model,HttpServletRequest req){
    //	System.out.println("in add payroll element");
    	Element element=new Element();
    	model.addAttribute("element", element);
        return "forms/payroll/setup/addPayrollElement :: addPayrollElement";
    }

    @ResponseBody
    @RequestMapping(value="/setuphrms/payrollsetup/payrollelements/searchpayrollelements",method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Element[] searchPayrollElement(PayrollSetupSearchModel psm,Model model){
    	//System.out.println("HIIIIIIIIIII "+psm.getElementName()+"-"+psm.getElementType());
    	String url1="/api/element/getelements";    	
        Element[] elements = null;
        String url = appgateway.getAppgateway_payroll()+url1;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<Element[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Element[].class);

        if(response.getStatusCode() == HttpStatus.OK) {
            elements = response.getBody();
            //System.out.println(elements.toString());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return elements;
    }
       
    @ResponseBody
    @RequestMapping(value="/setuphrms/payrollsetup/payrollelements/savepayrollelement", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String savePayrollElement(@ModelAttribute("element") Element element) {
        String successMessage = "Element successfully saved.";
        String errorMessage = "There was an error in saving the element.";
       // System.out.println("data::"+element.toString());
        HttpEntity<Element> request = new HttpEntity<Element>(element, headers);
        String endpointurl = appgateway.getAppgateway_payroll()+"/api/element/createelement";

        ResponseEntity<String> response = restTemplate.exchange(endpointurl, HttpMethod.POST, request, String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            return successMessage;
        } else {
            return errorMessage;
        }
    }
    
    
    
    @ResponseBody
    @RequestMapping(value="/setuphrms/payrollsetup/payrollelements/updatepayrollelement", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updatePayrollElement(@ModelAttribute("element") Element element) {
    	 
        String successMessage = "Element successfully update.";
        String errorMessage = "There was an error in update the element.";
        //System.out.println("update element"+element.toString());
        HttpEntity<Element> request = new HttpEntity<Element>(element, headers);
        String endpointurl = appgateway.getAppgateway_payroll()+"/api/element/updateelement";

        ResponseEntity<String> response = restTemplate.exchange(endpointurl, HttpMethod.POST, request, String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            return successMessage;
        } else {
            return errorMessage;
        }
    }
    
    
    
    
    @RequestMapping("/setuphrms/payrollsetup/payrollelements/editpayrollelement/{elementId}/{effdt}")
	public String editpayrollelement(@PathVariable("elementId") int elementId,@PathVariable("effdt") String effdt,
			 Model model, HttpServletRequest requestdata) {
    	//Element element = null;
    	//System.out.println("date"+effdt);
    	Element element=new Element();
    	String urlelement = appgateway.getAppgateway_payroll()+"/api/element/getelementbyid/" + elementId;
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> get_element_request = new HttpEntity<String>(headers);
		ResponseEntity<Element> get_element_response = restTemplate.exchange(urlelement, HttpMethod.GET, get_element_request,
				Element.class);
		if (get_element_response.getStatusCode() == HttpStatus.OK) {
			element = get_element_response.getBody();
			//System.out.println("data element::" + element.toString());
		} else {
			System.out.println("Request Failed with response code : " + get_element_response.getStatusCode());
		}
		//int len = element.getElementdetails().size();
		element.getElementdetails().get(0).setStartdate(effdt);
		element.setElementdetails(Arrays.asList(element.getElementdetails().get(0)));
		model.addAttribute("element", element);
		return "forms/payroll/setup/addPayrollElement :: addPayrollElement";
	}
    
    @RequestMapping("/setuphrms/payrollsetup/payrollelements/viewpayrollelement/{elementId}")
	public String viewpayrollelement(@PathVariable("elementId") int elementId,
		Model model, HttpServletRequest requestdata) {
    	//Element element = null;
    	Element element=new Element();
		String urlelement = appgateway.getAppgateway_payroll()+"/api/element/getelementbyid/" + elementId;
		//System.out.println("url:::"+urlelement);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> get_element_request = new HttpEntity<String>(headers);
		ResponseEntity<Element> get_element_response = restTemplate.exchange(urlelement, HttpMethod.GET, get_element_request,
				Element.class);
		if (get_element_response.getStatusCode() == HttpStatus.OK) {
			element = get_element_response.getBody();
			//System.out.println("data element::" + element.toString());
		} else {
			System.out.println("Request Failed with response code : " + get_element_response.getStatusCode());
		}
		model.addAttribute("element", element);
		return "forms/payroll/setup/viewPayrollElement :: viewPayrollElement";
	}
    
    
    
    
}
