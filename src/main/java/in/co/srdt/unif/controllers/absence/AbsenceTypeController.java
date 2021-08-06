package in.co.srdt.unif.controllers.absence;


import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

// import java.net.URI;
// import java.net.URISyntaxException;
// import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.Eligibility.EligibilitySearch;
import in.co.srdt.unif.model.absence.create.AbsenceSearchResult;
import in.co.srdt.unif.model.absence.create.AbsenceTypeMaster;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/absencesetup")
public class AbsenceTypeController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;	

	@RequestMapping("/manageabsencetype")
	public String manageabsencetype(Model model) {
		
		//System.out.println("sdsd");
		model.addAttribute("status", Status.values());
		return "fragments/absence/searchAbsence :: searchAbsence";
	}

	@RequestMapping("/createabsencetype")
	public String createabsencetype(@ModelAttribute("absencetypemaster") AbsenceTypeMaster absencetypemaster,
			Model model, HttpServletRequest req) {
	
		//System.out.println("Inside Absence Create"); 
		model.addAttribute("absencetypemaster", new AbsenceTypeMaster());
		
		  	CommonLOV[] AccuralFreq=null;
		  	CommonLOV[] Accuralon=null;
			CommonLOV[] RepeatingID=null;
			
			String urlAccuralFreq = appgateway.getAppgatewayabs()+"/General/loadAccrualFrequncy";
			String urlAccuralon = appgateway.getAppgatewayabs()+"/General/loadAccrualOn";
			String urlRepeating = appgateway.getAppgatewayabs()+"/RepeatingPeriod/getAllRepeatingPeriodLOV";
			
			
			headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>(headers);	
	        
			ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlAccuralFreq, HttpMethod.GET, request, CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlAccuralon, HttpMethod.GET, request, CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlRepeating, HttpMethod.GET, request, CommonLOV[].class);
			
			if(response.getStatusCode() == HttpStatus.OK) {
				AccuralFreq = response.getBody();
				
			} 
			if(response1.getStatusCode() == HttpStatus.OK) {
				Accuralon = response1.getBody();
				
			}
			if(response2.getStatusCode() == HttpStatus.OK) {
				RepeatingID = response2.getBody();
				
			}
		model.addAttribute("repeating", RepeatingID);			
		model.addAttribute("Accuralon", Accuralon);			
		model.addAttribute("AccuralFreq", AccuralFreq);		
		model.addAttribute("absencetypemaster", absencetypemaster);
		model.addAttribute("status", Status.values());
		return "fragments/absence/createAbsencetypeSetup :: createabsencetype";
	}
	
	
	
	@ResponseBody
	@PostMapping("/seach/Eligibility/searchEligibility")
	public EligibilitySearch[] getEligibilitySearch(HttpServletRequest requestFromDT) {
		//System.out.print("Inside Eligibility Search");
	
		String searchname=requestFromDT.getParameter("name");
		String searchcode=requestFromDT.getParameter("code");
		
		String url = appgateway.getAppgatewayabs()+"/Eligibility/EligibilitySearchList";
		EligibilitySearch[] eligibilitysearch=null;

		String payload="{"+
						"\"name\""+":\""+searchname+"\","+
						"\"code\""+":\""+searchcode+"\""+
						"}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload,headers);		
		ResponseEntity<EligibilitySearch[]> response = restTemplate.exchange(url, HttpMethod.POST, request, EligibilitySearch[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			eligibilitysearch = response.getBody();
			
		} 
			
		return eligibilitysearch;
	}

	@PostMapping(value = "/saveAbsence")
	public @ResponseBody SingleResponseModel saveAbsence(@ModelAttribute ("absenceTypeMaster") AbsenceTypeMaster absenceTypeMaster,
			@RequestParam(value="absence[0].carryforward", required=false) String CR_ABS_CARRY_val,
			@RequestParam(value="absence[0].incash", required=false) String CR_ABS_INCASH,
			@RequestParam(value="absence[0].islapse", required=false) String CR_ABS_Lapes,
			@RequestParam(value="absence[0].partofweeklyoff", required=false) String CR_ABS_WEEKLYOFF,
			@RequestParam(value="absence[0].partofholiday", required=false) String CR_ABS_PARTHOLIDAY,
			@RequestParam(value="absence[0].partofbeforeweeklyoff", required=false) String CR_ABS_PROCEEDING_WEEK,
			@RequestParam(value="absence[0].partofbeforeholiday", required=false) String CR_ABS_PROCEEDING_HOLIDAY,
			@RequestParam(value="absence[0].partofafterweeklyoff", required=false) String CR_ABS_SUCCEEDING_WEEK,
			@RequestParam(value="absence[0].partofafterholiday", required=false) String CR_ABS_SUCCEEDING_HOLIDAY,
	        @RequestParam(value="absence[0].isprorate", required=false) String CR_ABS_ISPRORATE)
	{
		
		 if(CR_ABS_CARRY_val==null) {
			 
			 absenceTypeMaster.getAbsence().get(0).setCarryforward("No");
		 }
		 if(CR_ABS_INCASH==null) {
			 absenceTypeMaster.getAbsence().get(0).setIncash("No");
		 }
		 if(CR_ABS_Lapes==null) {
			 absenceTypeMaster.getAbsence().get(0).setIslapse("No");
		 }
		 if(CR_ABS_WEEKLYOFF==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofweeklyoff("No");
		 }
		 if(CR_ABS_PARTHOLIDAY==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofholiday("No");
		 }
		 if(CR_ABS_PROCEEDING_WEEK==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofbeforeweeklyoff("No");
		 }
		 if(CR_ABS_PROCEEDING_HOLIDAY==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofbeforeholiday("No");
		 }
		 if(CR_ABS_SUCCEEDING_WEEK==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofafterweeklyoff("No");
		 }
		 if(CR_ABS_SUCCEEDING_HOLIDAY==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofafterholiday("No");
		 }
		 if(CR_ABS_ISPRORATE==null) {
			 absenceTypeMaster.getAbsence().get(0).setIsprorate("No");
		 }
		String urlAbse=appgateway.getAppgatewayabs()+"/AbsenceType/saveAbsence";
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			//System.out.println("absenceTypeMaster ::"+ absenceTypeMaster.toString());
			//System.out.println("absenceType ::"+ absenceTypeMaster.getAbsenceType().toString());
			
			HttpEntity<AbsenceTypeMaster> request = new HttpEntity<AbsenceTypeMaster>(absenceTypeMaster, headers);
			
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlAbse,HttpMethod.POST,request, SingleResponseModel.class);
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
	@PostMapping("/searchAbsence")
	public AbsenceSearchResult[] AbsenceSearchResultList(HttpServletRequest requestFromDT) {

		String searchcode = requestFromDT.getParameter("code");
		String searchname = requestFromDT.getParameter("name");
		String searchstatus = requestFromDT.getParameter("statusAbs");
		//System.out.println("in");
		String url = appgateway.getAppgatewayabs()+"/AbsenceType/AbsenceSearchList";
		AbsenceSearchResult[] absObj = null;
		String payload = "{" + "\"code\"" + ":\"" + searchcode + "\"," + "\"name\"" + ":\"" + searchname + "\","
				+ "\"status\"" + ":\"" + searchstatus + "\"" + "}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<AbsenceSearchResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				AbsenceSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			absObj = response.getBody();
		}
		
		return absObj;

	}
	
	
	
	
	@RequestMapping("/edit/EditAbsence/{absencetypeid}/{effdt}")
	public String editAbsence(@PathVariable("absencetypeid") String absencetypeid,@PathVariable("effdt") String effdt, Model model,HttpServletRequest requestFromDT)
			throws URISyntaxException, ParseException {
		
		model.addAttribute("response", new SingleResponseModel());
		String Searchabsencetypeid = absencetypeid;
		AbsenceTypeMaster absencetypemaster=null;
		
		
		  	CommonLOV[] AccuralFreq=null;
		  	CommonLOV[] Accuralon=null;
			CommonLOV[] RepeatingID=null;
			
			String urlAccuralFreq = appgateway.getAppgatewayabs()+"/General/loadAccrualFrequncy";
			String urlAccuralon = appgateway.getAppgatewayabs()+"/General/loadAccrualOn";
			String urlRepeating = appgateway.getAppgatewayabs()+"/RepeatingPeriod/getAllRepeatingPeriodLOV";
			
			//System.out.println("Searchabsencetypeid........."+Searchabsencetypeid);
			
			headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> request = new HttpEntity<String>(headers);	
	        
			ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlAccuralFreq, HttpMethod.GET, request, CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response1 = restTemplate.exchange(urlAccuralon, HttpMethod.GET, request, CommonLOV[].class);
			ResponseEntity<CommonLOV[]> response2 = restTemplate.exchange(urlRepeating, HttpMethod.GET, request, CommonLOV[].class);
			
			if(response.getStatusCode() == HttpStatus.OK) {
				AccuralFreq = response.getBody();
				
			} 
			if(response1.getStatusCode() == HttpStatus.OK) {
				Accuralon = response1.getBody();
				
			}
			if(response2.getStatusCode() == HttpStatus.OK) {
				RepeatingID = response2.getBody();
				
			}
			
			
			String urrlAbsence= appgateway.getAppgatewayabs()+"/AbsenceType/getAbsence/" + Searchabsencetypeid;
			URI urlAB = new URI(urrlAbsence);
			
			HttpHeaders headerss = new HttpHeaders();
			headerss.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> getabsencetypeRequest = new HttpEntity<String>(headerss);
			
			ResponseEntity<AbsenceTypeMaster> getabsencetypeResponse = restTemplate.exchange(urlAB, HttpMethod.GET,
					getabsencetypeRequest, AbsenceTypeMaster.class);

			if (getabsencetypeResponse.getStatusCode() == HttpStatus.OK) {
				absencetypemaster = getabsencetypeResponse.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(getabsencetypeResponse.getStatusCode());
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))!=0)
			{
				java.util.Date date1 = sdf.parse(effdt);
				java.util.Date date2 = sdf.parse(absencetypemaster.getAbsence().get(0).getEffectstartdate());
			
					if (date1.compareTo(date2) <= 0)
					{ 
						//System.out.println("earlier"); 
						absencetypemaster.setAddtionalatr("Error");
					}
					else if(date1.compareTo(date2) > 0)	  
					{
						absencetypemaster.setAddtionalatr(effdt);
					}
			}
			else if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))==0){
				absencetypemaster.setAddtionalatr("Correct");
			}
			

			String flag=absencetypemaster.getAbsence().get(0).getEffectstartdate();
			if(flag!=null) {
			flag=flag.substring(0,10);	
			absencetypemaster.getAbsence().get(0).setEffectstartdate(flag);
			}
			
			
			
			
		//System.out.println("absenceTypeMaster ::"+ absencetypemaster.toString());
	
			
		model.addAttribute("repeating", RepeatingID);			
		model.addAttribute("Accuralon", Accuralon);			
		model.addAttribute("AccuralFreq", AccuralFreq);		
		model.addAttribute("master", absencetypemaster);
		model.addAttribute("absencetypemaster",absencetypemaster);
		model.addAttribute("status", Status.values());
		return "fragments/absence/createAbsencetypeSetup :: createabsencetype";

	}
	
	
	
	@RequestMapping(value = "/correctAbsence/{flag}", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public  @ResponseBody SingleResponseModel correctAbsence(@PathVariable("flag") String flag, Model model,@ModelAttribute ("absenceTypeMaster") AbsenceTypeMaster absenceTypeMaster,
			@RequestParam(value="absence[0].carryforward", required=false) String CR_ABS_CARRY_val,
			@RequestParam(value="absence[0].incash", required=false) String CR_ABS_INCASH,
			@RequestParam(value="absence[0].islapse", required=false) String CR_ABS_Lapes,
			@RequestParam(value="absence[0].partofweeklyoff", required=false) String CR_ABS_WEEKLYOFF,
			@RequestParam(value="absence[0].partofholiday", required=false) String CR_ABS_PARTHOLIDAY,
			@RequestParam(value="absence[0].partofbeforeweeklyoff", required=false) String CR_ABS_PROCEEDING_WEEK,
			@RequestParam(value="absence[0].partofbeforeholiday", required=false) String CR_ABS_PROCEEDING_HOLIDAY,
			@RequestParam(value="absence[0].partofafterweeklyoff", required=false) String CR_ABS_SUCCEEDING_WEEK,
			@RequestParam(value="absence[0].partofafterholiday", required=false) String CR_ABS_SUCCEEDING_HOLIDAY,
			@RequestParam(value="absence[0].isprorate", required=false) String CR_ABS_ISPRORATE)
	
	{
		
		//System.out.println("incash "+CR_ABS_INCASH);
		 if(CR_ABS_CARRY_val==null) {
			 absenceTypeMaster.getAbsence().get(0).setCarryforward("No");
		 }
		 if(CR_ABS_INCASH==null) {
			 absenceTypeMaster.getAbsence().get(0).setIncash("No");
		 }
		 if(CR_ABS_Lapes==null) {
			 absenceTypeMaster.getAbsence().get(0).setIslapse("No");
		 }
		 if(CR_ABS_WEEKLYOFF==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofweeklyoff("No");
		 }
		 if(CR_ABS_PARTHOLIDAY==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofholiday("No");
		 }
		 if(CR_ABS_PROCEEDING_WEEK==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofbeforeweeklyoff("No");
		 }
		 if(CR_ABS_PROCEEDING_HOLIDAY==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofbeforeholiday("No");
		 }
		 if(CR_ABS_SUCCEEDING_WEEK==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofafterweeklyoff("No");
		 }
		 if(CR_ABS_SUCCEEDING_HOLIDAY==null) {
			 absenceTypeMaster.getAbsence().get(0).setPartofafterholiday("No");
		 }
		 
		 if(CR_ABS_ISPRORATE==null) {
			 absenceTypeMaster.getAbsence().get(0).setIsprorate("No");
		 }
		 
		SingleResponseModel res = new SingleResponseModel();
        String urlAbse="";
		
		if(flag.equals("Correct")) {
			urlAbse = appgateway.getAppgatewayabs()+"/AbsenceType/correctAbsence";
			
		
		}
		else if(flag!="Correct") {

			urlAbse = appgateway.getAppgatewayabs()+"/AbsenceType/updateAbsence";
		
			}
		
		///System.out.println("url"+urlAbse);
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
		///	System.out.println(CR_ABS_CARRY_val);
			//System.out.println("absenceTypeMaster ::"+ absenceTypeMaster.toString());
			//System.out.println("absenceType ::"+ absenceTypeMaster.getAbsenceType().toString());
			
			HttpEntity<AbsenceTypeMaster> request = new HttpEntity<AbsenceTypeMaster>(absenceTypeMaster, headers);
			
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlAbse,HttpMethod.PUT,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
		
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Absence.");
		}
		//System.out.println("correct");
		return res;
	}
	
}
