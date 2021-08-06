package in.co.srdt.unif.controllers.Miscellaneous;


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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Days;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.Miscellaneous.RepeatingPeriodSearch;
import in.co.srdt.unif.model.Miscellaneous.WeeklyHoliday;
import in.co.srdt.unif.model.Miscellaneous.WeeklyHolidayList;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;

@SessionAttributes("weekly")
@Controller
@RequestMapping("/weekly")
public class WeeklyController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationGateway appgateway;

    @Autowired
    private HttpHeaders headers;

    @RequestMapping("/manageWeekly")
    public String manageWeekly(Model model,  HttpServletRequest req) {

        //System.out.println("weekly:::::::::");
		//model.addAttribute("weekly", new WeeklyHolidayList());
        model.addAttribute("Days", Days.values());
        //model.addAttribute("weekholiday",weekholiday);
        model.addAttribute("status",Status.values());
        return "fragments/Miscellaneous/createWeekly :: createWeekly";
    }

    @RequestMapping("/createWeekly")
    public String createWeekly(Model model,  HttpServletRequest req) {
    	
       // System.out.println("CreateWeekly");
        model.addAttribute("Days", Days.values());
        model.addAttribute("status",Status.values());
        return "fragments/Miscellaneous/createWeekly :: createWeekly";
    }
    
    
    @RequestMapping(value = "/saveWeekly", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody SingleResponseModel saveWeekly(WeeklyHolidayList weeklyHolidayList )
	{
    	
    
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
		//	System.out.println("dfsdfs"+weeklyHolidayList.toString());
			String Jurl=appgateway.getAppgatewayabs()+"/WeeklyHoliday/saveAndCorrectWeeklyHoliday";
			HttpEntity<WeeklyHolidayList> request = new HttpEntity<WeeklyHolidayList>(weeklyHolidayList, headers);

			
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(Jurl,HttpMethod.POST,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			//System.out.println("Weekly Holiday Save ::"+weeklyHoliday.toString());
			/*NEW CODE*/
			//OLD CODE//res = restTemplate.postForObject("http://192.200.12.83:9194/api/courseplan/create", coursePlanWrapper, SingleResponseModel.class);
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Repeating .");
		}
		return res;
	}


    
    
    @ResponseBody
   	@PostMapping(path="/seach/RepeatingPeriod/RepeatingPeriodSearchList")
   	public RepeatingPeriodSearch[] WeeklyrepeatingPeriodSearchs(HttpServletRequest requestFromDT) {
   		
   		
   		String name = requestFromDT.getParameter("name");		
   		String code = requestFromDT.getParameter("code");
   		String status = requestFromDT.getParameter("status");
   		
   		//System.out.println("WEEKLY:::::::::RepeatingPeriodSearch:::::::::::::::::::"+name);
   		
   		RepeatingPeriodSearch[] repeatingPeriodSearchs = null;
   		
   		String urlRepeating = appgateway.getAppgatewayabs()+"/RepeatingPeriod/RepeatingPeriodSearchList";
   		
   		String payLode = "{" +
   						"\"name\"" + ":\"" +name+ "\"," +
   						"\"status\"" + ":\"" +status+ "\"," +
   						"\"code\"" + ":\"" +code+ "\"" +
   						"}";
   		
   		headers.setContentType(MediaType.APPLICATION_JSON);
   		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
   		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
   		
   		ResponseEntity<RepeatingPeriodSearch[]> response = restTemplate.exchange(urlRepeating, HttpMethod.POST, request, RepeatingPeriodSearch[].class);
   		
   		if(response.getStatusCode() == HttpStatus.OK) {
   			repeatingPeriodSearchs = response.getBody();
   			
   			
   		} else {
   			System.out.println("Request Failed");
   			System.out.println(response.getStatusCode());
   		}
   		//System.out.println("ELig");
   		
   		
   		return repeatingPeriodSearchs;
   	}
    
    
    
    @ResponseBody
	@PostMapping(path="/searchWeekly/getWeeklyId")
	public WeeklyHoliday[] WeeklyHolidaySearch(HttpServletRequest requestFromDT) {
		
		
		String day = requestFromDT.getParameter("day");		
		String year = requestFromDT.getParameter("year");
		
		//System.out.println("searchWeekly:::::::::::::::::::"+day);
		
		WeeklyHoliday[] weeklyHolidays = null;
		
		String urlweeklyHolidays = appgateway.getAppgatewayabs()+"/WeeklyHoliday/WeeklyHolidaySearchList";
		
		String payLode = "{" +
						"\"day\"" + ":\"" +day+ "\"," +
						"\"year\"" + ":\"" +year+ "\"" +
						"}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<WeeklyHoliday[]> response = restTemplate.exchange(urlweeklyHolidays, HttpMethod.POST, request, WeeklyHoliday[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			weeklyHolidays = response.getBody();
			
			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
			
		}
		//System.out.println("weekly");
		
		
		return weeklyHolidays;
	}
    
    
    
    
    @ResponseBody
	@RequestMapping("/WeeklyHoliday/getRepPeriodId/{reapetingperiodid}")
	public WeeklyHoliday[] weeklyHoliday(@PathVariable("reapetingperiodid") String reapetingperiodid, Model model) {

    	WeeklyHoliday[] sd1=null;
		String urlp = appgateway.getAppgatewayabs()+"/WeeklyHoliday/getWeeklyHolidayEditByRepPeriodId/" + reapetingperiodid;
		//System.out.println(urlp);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<WeeklyHoliday[]> response = restTemplate.exchange(urlp, HttpMethod.GET, request, WeeklyHoliday[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			sd1 = response.getBody();
			
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		//System.out.println("Repeating Period::::::::::"+sd1[0].toString());
		
		model.addAttribute("RepPeriod", sd1);

		return sd1;

	}	
    
    
    
    @GetMapping("/removechild/{index}")
	public @ResponseBody String removeChild(@SessionAttribute("weekholiday") WeeklyHolidayList weekholiday, @PathVariable int index)
	{
		//System.out.println("index : " + index);

		weekholiday.getWeeklyholiday().remove(index);
			

		
		
		return "removed";
	}

    
   
}