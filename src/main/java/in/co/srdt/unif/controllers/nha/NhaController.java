package in.co.srdt.unif.controllers.nha;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.holiday.Holiday;
import in.co.srdt.unif.model.nha.CreateNha;
import in.co.srdt.unif.model.nha.CreateNhaWrapper;
import in.co.srdt.unif.model.nha.HolidayList;
import in.co.srdt.unif.model.user.absence.CommonDescription;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/nha")
public class NhaController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;
	
	public String pnumber="";
	public String locationid="";

	public String location ="WorkLocation";
	
	public NhaController() {
		
	}

	public NhaController(RestTemplate restTemplate, HttpHeaders headers) {
		super();
		this.restTemplate = restTemplate;
		this.headers = headers;
	}
	
	@RequestMapping("/manageNha/{pnum}")
	public String manageaNha(@PathVariable("pnum") String pnum,@ModelAttribute("createNhaWrapper") CreateNhaWrapper createNhaWrapper ,  Model model,HttpServletRequest req)
	{
		/* loading location on basis of person id starts */
		
		//System.out.println("Person Number : "+pnum);
		pnumber = pnum;
		
		String locid_url = appgateway.getAppgateway()+"/AbsencePersonData/getPersonDataByGivenParameter/"+pnumber+"/"+location;
		
		//System.out.println("====> URL FOR Location ID Fetching <=== "+locid_url);
		
		CommonDescription res = new CommonDescription();
		
		HttpEntity<String> locidrequest = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonDescription> locationidresp = restTemplate
				.exchange(locid_url, HttpMethod.GET, locidrequest, CommonDescription.class);

		if (locationidresp.getStatusCode() == HttpStatus.OK) {
			res = locationidresp.getBody();
			
			locationid = res.getDescription();
			
			//System.out.println("::::: Location ID ==>" + locationid);
		}

		/* loading location on basis of person id ends */

		//************************************************************************//
		
		/* fetching holiday details starts */
		
		String holiday_url = appgateway.getAppgatewayabs()+"/Person/NHA/getHolidayNameList/"+locationid+"/"+pnumber;
		//System.out.println("HOLIDAY URL ====> "+holiday_url);
		
		HolidayList holiday[] = null;
		HttpEntity<String> holiday_request = new HttpEntity<String>(headers);
		
		ResponseEntity<HolidayList[]> holiday_response = restTemplate.exchange(holiday_url, HttpMethod.GET,holiday_request,HolidayList[].class);
		if(holiday_response.getStatusCode() == HttpStatus.OK)
		{
			holiday = holiday_response.getBody();
		}
		else
		{
			//System.out.println("Unable to load holiday data");
		}
		/* fetching holiday details ends */
		
		model.addAttribute("locationid",locationid);
		model.addAttribute("pno",pnumber);
		model.addAttribute("holiday",holiday);
		model.addAttribute("createNhaWrapper", new CreateNhaWrapper());
		
		
		/* ======================= FETCHING NHA HISTORY STARTS ======================= */
		
		String getNHAUrl = appgateway.getAppgatewayabs()+"/Person/NHA/getNationalHolidayAllowance/"+pnumber;
		//System.out.println("GET URL =====>>>> "+getNHAUrl);
		
		CreateNha[] cnha = null;
		
		HttpEntity<String> getnharequest = new HttpEntity<String>(headers);
		ResponseEntity<CreateNha[]> nhagetresponse = restTemplate.exchange(getNHAUrl, HttpMethod.GET,getnharequest,CreateNha[].class);
		
		if (nhagetresponse.getStatusCode() == HttpStatus.OK) {
			cnha = nhagetresponse.getBody();
			//System.out.println("NHA Model : " + cnha.toString());
		}

		model.addAttribute("nhahistory", cnha);
		/* ======================= FETCHING NHA HISTORY ENDS ======================= */
		
		return "fragments/nha/manageNha :: manageNha";
	}
	
	@PostMapping("/saveNha")
	public @ResponseBody SingleResponseModel saveNha(@ModelAttribute("createNhaWrapper") CreateNhaWrapper createNhaWrapper ,  Model model)
	{
		String postNHAUrl = appgateway.getAppgatewayabs()+"/Person/NHA/saveNationalHolidayAllowance";
		SingleResponseModel res = new SingleResponseModel();
		HttpEntity<List<CreateNha>> postnharequest = new HttpEntity<List<CreateNha>>(createNhaWrapper.getCreatenha(),headers);
		
		ResponseEntity<SingleResponseModel> nhasaveresponse = restTemplate.exchange(postNHAUrl, HttpMethod.POST,postnharequest,SingleResponseModel.class);
		
		if(nhasaveresponse.getStatusCode()==HttpStatus.OK)
		{
			res = nhasaveresponse.getBody();
			//System.out.println("==== POSTED DATA =====> "+createNhaWrapper.toString());
			//System.out.println("==== RESPONSE =====> "+nhasaveresponse.getBody());
			//System.out.println("==== AFTER POSTING RESPONSE =====> "+res.getStatus());
		}
		else 
		{
            //System.out.println("Request Failed");

        }
		model.addAttribute("result",res.getStatus());
		
		return res;
	}


}
