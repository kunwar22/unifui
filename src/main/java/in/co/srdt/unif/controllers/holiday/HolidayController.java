package in.co.srdt.unif.controllers.holiday;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Identifying;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.Eligibility.Eligibility;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.utils.ApplicationGateway;
import in.co.srdt.unif.model.holiday.Holiday;
import in.co.srdt.unif.model.holiday.HolidayLocationLOV;
import in.co.srdt.unif.model.holiday.HolidaySearch;


@Controller
@RequestMapping("/holiday")
public class HolidayController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationGateway appgateway;

	@Autowired
    private HttpHeaders headers;
    
    @RequestMapping("/manageHoliday")
	public String manageEnterprise(Model model, @ModelAttribute("Holiday") Eligibility Holiday, HttpServletRequest req) {
           
  //  System.out.println("Inside Holiday");
    
    model.addAttribute("status", Status.values());
    return "fragments/holiday/manageHoliday :: searchHoliday";
    }
  
    
    @RequestMapping("/createHoliday")
	public String createHoliday(@ModelAttribute("holiday") Holiday holiday,
			Model model, HttpServletRequest req) {
	
		//System.out.println("Inside Holiday Create");
		model.addAttribute("holiday", new Holiday());
		
		  	HolidayLocationLOV[] Location=null;
		  	CommonLOV[] holidaytype=null;
		  	
			String urlLocation = appgateway.getAppgateway()+"/Holiday/getAllLocation";
			String urlholidaytype = appgateway.getAppgateway()+"/Holiday/loadHolidayType";
			
			//System.out.println("urlLocation"+urlLocation);
			
			headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>(headers);	
	        
			ResponseEntity<HolidayLocationLOV[]> response = restTemplate.exchange(urlLocation, HttpMethod.GET, request, HolidayLocationLOV[].class);
			ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlholidaytype, HttpMethod.GET, request, CommonLOV[].class);
			
			if(response.getStatusCode() == HttpStatus.OK) {
				Location = response.getBody();
				
			}
			if(response1.getStatusCode() == HttpStatus.OK) {
				holidaytype = response1.getBody();
				
			}
		model.addAttribute("status", Status.values());
		model.addAttribute("holidaytype", holidaytype);	
		model.addAttribute("location", Location);			
		model.addAttribute("Identifying", Identifying.values());
		model.addAttribute("holiday", holiday);
		
		 return "fragments/holiday/holiday :: createreHoliday";
	}

    @PostMapping(value = "/saveHoliday")
	public @ResponseBody SingleResponseModel saveHoliday(@ModelAttribute("holiday") Holiday holiday,
			@RequestParam(value="location[0].locationid", required=false) String CR_HLDY_LOCATION_val
			)
	{
		
		 if(CR_HLDY_LOCATION_val==null) {
			 
			
			 holiday.getLocation().get(0).setLocationid(0);
		 }
		
		String urlHldy=appgateway.getAppgateway()+"/Holiday/saveHoliday";
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			//System.out.println("holiday ::"+ holiday.toString());
			
			HttpEntity<Holiday> request = new HttpEntity<Holiday>(holiday, headers);
			
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlHldy,HttpMethod.POST,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
		
			
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Absence .");
		}
		
		return res;
	}
	
    
    @ResponseBody
	@PostMapping(path="/searchholiday/getHolidayById")
	public HolidaySearch[] holidaySearch(HttpServletRequest requestFromDT) {
		
		
		String fromdate = requestFromDT.getParameter("fromdate");
		String holidayname = requestFromDT.getParameter("holidayname");
		String todate = requestFromDT.getParameter("todate");
		String status = requestFromDT.getParameter("status");
	
		
			
		HolidaySearch[] holidaySearchs = null;
		
		String urlholiday = appgateway.getAppgateway()+"/Holiday/holidaySearchList";
		
		String payLode = "{" +
						"\"fromdate\"" + ":\"" +fromdate+ "\"," +
						"\"holidayname\"" + ":\"" +holidayname+ "\"," +
						"\"todate\"" + ":\"" +todate+ "\"," +						
						"\"status\"" + ":\"" +status+ "\"" +	
						"}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<HolidaySearch[]> response = restTemplate.exchange(urlholiday, HttpMethod.POST, request, HolidaySearch[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			holidaySearchs = response.getBody();
		
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return holidaySearchs;
	}
	
    
    @RequestMapping("/edit/EditHolidayById/{holidayid}")
	public String EditHoliday(@PathVariable("holidayid") String holidayid, Model model,HttpServletRequest requestFromDT)
			throws URISyntaxException, ParseException {
		
		
		String Searchholidayid = holidayid;
	
		Holiday Holiday=null;
		String urrlHoliday= appgateway.getAppgateway()+"/Holiday/getHoliday/" + Searchholidayid;
		URI urlHLDY = new URI(urrlHoliday);
	
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getHolidayRequest = new HttpEntity<String>(headerss);

		ResponseEntity<Holiday> getHolidayResponse = restTemplate.exchange(urlHLDY, HttpMethod.GET,
				getHolidayRequest, Holiday.class);

		if (getHolidayResponse.getStatusCode() == HttpStatus.OK) {
			Holiday = getHolidayResponse.getBody();
			
			
		} else {
			System.out.println("Request Failed");
			System.out.println(getHolidayResponse.getStatusCode());
		}
		
		

	  	HolidayLocationLOV[] Location=null;
	  	CommonLOV[] holidaytype=null;
	  	
		String urlLocation = appgateway.getAppgateway()+"/Holiday/getAllLocation";
		String urlholidaytype = appgateway.getAppgateway()+"/Holiday/loadHolidayType";
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);	
        
		ResponseEntity<HolidayLocationLOV[]> response = restTemplate.exchange(urlLocation, HttpMethod.GET, request, HolidayLocationLOV[].class);
		ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlholidaytype, HttpMethod.GET, request, CommonLOV[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			Location = response.getBody();
			
		}
		if(response1.getStatusCode() == HttpStatus.OK) {
			holidaytype = response1.getBody();
			
		}
		
		
			
		String effstartdate=Holiday.getDate();
		effstartdate=effstartdate.substring(0,10);
		Holiday.setDate(effstartdate);
		
		//System.out.println(Holiday.toString());
		//System.out.println(Holiday.getLocation().size());
		
		if(Holiday.getActionid()!=0) {
			for(int i=0;i<Location.length;i++) {
				for(int j=0;j<Holiday.getLocation().size();j++) {
					if(Location[i].getId()==Holiday.getLocation().get(j).getLocationid()) {
						Location[i].setChecked("checked");
					}
				}
				
			}
		}
		
		//System.out.println(Holiday.getLocation().get(0));
		
		model.addAttribute("status", Status.values());
		model.addAttribute("holidaytype", holidaytype);	
		model.addAttribute("location", Location);			
		model.addAttribute("Identifying", Identifying.values());
		model.addAttribute("holiday", Holiday);
		 return "fragments/holiday/holiday :: createreHoliday";

	}
    
    
    
    
    
    @PostMapping(value = "/correctHoliday")
  	public @ResponseBody SingleResponseModel correctHoliday(@ModelAttribute("holiday") Holiday holiday,
  			@RequestParam(value="location[0].locationid", required=false) String CR_HLDY_LOCATION_val
  			)
  	{
  		 if(CR_HLDY_LOCATION_val==null) {
  			 
  			
  			 holiday.getLocation().get(0).setLocationid(0);
  		 }
  		
  		String urlHldy=appgateway.getAppgateway()+"/Holiday/correctHoliday";
  		SingleResponseModel res = new SingleResponseModel();
  		try {
  		
  			headers.setContentType(MediaType.APPLICATION_JSON);
  			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
  			
  			
  			HttpEntity<Holiday> request = new HttpEntity<Holiday>(holiday, headers);
  			
  			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlHldy,HttpMethod.PUT,request, SingleResponseModel.class);
  			if(response.getStatusCode() == HttpStatus.OK) {
  				res = response.getBody();
  			} else {
  				System.out.println("Request Failed");
  				System.out.println(response.getStatusCode());
  			}
  		
  			
  		}
  		catch (Exception e){
  			e.printStackTrace();
  			res.setMessage("Failed to create Absence .");
  		}
  		
  		return res;
  	}
  	

}