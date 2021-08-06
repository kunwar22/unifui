package in.co.srdt.unif.controllers.reimbursementsetup;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.reimbursementsetup.RembursementType;
import in.co.srdt.unif.model.reimbursementsetup.RembursementTypeMaster;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/reimbursementType")
public class ReimbursementSetupController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;
	
	ReimbursementSetupController(){
		
	}
	
	ReimbursementSetupController(HttpHeaders headers,RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}
	
   
	/* Controllers for Telephone RembType - Rajat Rawat*/
    //
    @RequestMapping("/manageRembType")
    public String reimbursementype( @ModelAttribute ("rembursementTypeMaster") RembursementTypeMaster rembursementTypeMaster,Model model)
	{
    	CommonLOV[] RembName=null;
    	String urlRembName = appgateway.getAppgateway()+"/General/loadReimbursementNameLOV";
    	headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);	
        //System.out.println("url"+urlRembName);
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlRembName, HttpMethod.GET, request, CommonLOV[].class);
		if(response.getStatusCode() == HttpStatus.OK) {
			RembName = response.getBody();
			
		} 
		model.addAttribute("RembName", RembName);
    	
		model.addAttribute("status",Status.values());
		model.addAttribute("rembursementTypeMaster", rembursementTypeMaster);	
    	return "forms/reimbursement/reimbursementsetup/manageRembType :: manageRembType";
    }
    
    @RequestMapping("/searchRembType")
    public String searchReimbursementType( Model model,HttpServletRequest req)
	{
		//System.out.println("searchRembType");
		model.addAttribute("status",Status.values());
		return "forms/reimbursement/reimbursementsetup/searchRembType :: searchReimbursementType";
    }
   
    
    @PostMapping(value = "/saveRembursementType")
	public @ResponseBody SingleResponseModel saveRembursementType(@ModelAttribute ("rembursementTypeMaster") RembursementTypeMaster rembursementTypeMaster)
	{
		
		String urlRemb=appgateway.getAppgatewayabs()+"/Rembursement/saveRembursementType";
		
		//System.out.println("saveRembursementType url:::"+urlRemb);
		
		SingleResponseModel res = new SingleResponseModel();
		try {
		
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			//System.out.println("rembursementTypeMaster ::"+ rembursementTypeMaster.toString());
			//System.out.println("absenceType ::"+ absenceTypeMaster.getAbsenceType().toString());
			
			HttpEntity<RembursementTypeMaster> request = new HttpEntity<RembursementTypeMaster>(rembursementTypeMaster, headers);
			
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlRemb,HttpMethod.POST,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
		
			}
			catch (Exception e){
				e.printStackTrace();
				res.setMessage("Failed to create Reimbursement Type .");
			}
		
		return res;
	}
    
    
    
    @ResponseBody
	@PostMapping("/seach/Rembursement/searchRembursement")
	public RembursementType[] getRembursementSearch(HttpServletRequest requestFromDT) {
		System.out.print("Inside searchRembursement");
	
		String REMB_NAME=requestFromDT.getParameter("REMB_NAME");
		
		String REMB_STATUS=requestFromDT.getParameter("REMB_STATUS");
		
		String url = appgateway.getAppgatewayabs()+"/Rembursement/getRembursementTypeSearchList";
		RembursementType[] rembursementTypes=null;

		String payload="{"+
						"\"rembursementname\""+":\""+REMB_NAME+"\","+
						"\"status\""+":\""+REMB_STATUS+"\""+
						"}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload,headers);		
		ResponseEntity<RembursementType[]> response = restTemplate.exchange(url, HttpMethod.POST, request, RembursementType[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			rembursementTypes = response.getBody();
			
		} 
			
		return rembursementTypes;
	}
    
    
    
	//@RequestMapping("/edit/editReimbursement/correctReimbursement/{Rembursid}")
	@RequestMapping("/edit/editReimbursement/correctReimbursement/{Rembursid}/{effdt}")
	//public String editReimbursement(@PathVariable("Rembursid") String Rembursid, Model model)
		//	throws URISyntaxException {
	
	public String editReimbursement(@PathVariable("Rembursid") String Rembursid,@PathVariable("effdt") String effdt, Model model,HttpServletRequest requestFromDT)
			throws URISyntaxException, ParseException  {
		

		String SearchdatasetId = Rembursid;		
		//System.out.println("Id"+SearchdatasetId);
		
		RembursementTypeMaster rembursementTypeMaster=null;
		
		String urrlRemb= appgateway.getAppgatewayabs()+"/Rembursement/getRembursementTypeEditById/" + SearchdatasetId;
		URI urlRembType = new URI(urrlRemb);
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getRembursementTypeRequest = new HttpEntity<String>(headerss);

		ResponseEntity<RembursementTypeMaster> getRembursementTypeResponse = restTemplate.exchange(urlRembType, HttpMethod.GET,
				getRembursementTypeRequest, RembursementTypeMaster.class);

		if (getRembursementTypeResponse.getStatusCode() == HttpStatus.OK) {
			rembursementTypeMaster = getRembursementTypeResponse.getBody();
			//System.out.println(rembursementTypeMaster.getRembursementtype().get(0).getStatus());
			//System.out.println(rembursementTypeMaster.getRembursementceilinglimit().toString());
		} 
		else {
			System.out.println("Request Failed");
			//System.out.println(rembursementTypeMaster.getRembursementceilinglimit());
		}
		
		if(rembursementTypeMaster.getRembursementtype().get(0).getEffectivestartdate()!=null) {
			String effdtstart=rembursementTypeMaster.getRembursementtype().get(0).getEffectivestartdate();
			effdtstart=effdtstart.substring(0,10);
			rembursementTypeMaster.getRembursementtype().get(0).setEffectivestartdate(effdtstart);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))!=0)
		{
			java.util.Date date1 = sdf.parse(effdt);
			java.util.Date date2 = sdf.parse(rembursementTypeMaster.getRembursementtype().get(0).getEffectivestartdate());
		
				if (date1.compareTo(date2) <= 0)
				{ 
					//System.out.println("earlier"); 
					rembursementTypeMaster.getRembursementtype().get(0).setAdditionalatr("Error");
				}
				else if(date1.compareTo(date2) > 0)	  
				{
					rembursementTypeMaster.getRembursementtype().get(0).setAdditionalatr(effdt);
				}
		}
		else if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))==0){
			rembursementTypeMaster.getRembursementtype().get(0).setAdditionalatr("Correct");
		}
		
		
		
		CommonLOV[] RembName=null;
    	String urlRembName = appgateway.getAppgateway()+"/General/loadReimbursementNameLOV";
    	headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);	
      //  System.out.println("url"+urlRembName);
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlRembName, HttpMethod.GET, request, CommonLOV[].class);
		if(response.getStatusCode() == HttpStatus.OK) {
			RembName = response.getBody();
			
		} 
		//System.out.println("name"+rembursementTypeMaster.getRembursementtype().get(0).getRembursementname());
		model.addAttribute("RembName", RembName);
		
		
		model.addAttribute("rembursementTypeMaster", rembursementTypeMaster);	
		return "forms/reimbursement/reimbursementsetup/manageRembType :: manageRembType";

		}
	
	   @PostMapping(value = "/correctRembursementType/{flag}")
		public @ResponseBody SingleResponseModel correctRembursementType(@ModelAttribute ("rembursementTypeMaster") RembursementTypeMaster rembursementTypeMaster ,@PathVariable("flag") String flag, Model model)
		{
		// System.out.println("correctRembursementType"+flag);
		 
		 String urlRemb="";
			
			if(flag.equals("Correct")) {
				urlRemb = appgateway.getAppgatewayabs()+"/Rembursement/correctRembursementType";
				//System.out.println("correct "+urlRemb);
			
			}
			else if(flag!="Correct") {

				urlRemb = appgateway.getAppgatewayabs()+"/Rembursement/updateRembursementType";
				//System.out.println("update "+urlRemb);
				}
		 
		 
			//String urlRemb=appgateway.getAppgatewayabs()+"/Rembursement/correctRembursementType";
			
			//System.out.println("Correct RembursementType url:::"+urlRemb);
			
			SingleResponseModel res = new SingleResponseModel();
			try {
			
				headers.setContentType(MediaType.APPLICATION_JSON);
				//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
				
				//System.out.println("rembursementTypeMaster ::"+ rembursementTypeMaster.toString());
				//System.out.println("absenceType ::"+ absenceTypeMaster.getAbsenceType().toString());
				
				HttpEntity<RembursementTypeMaster> request = new HttpEntity<RembursementTypeMaster>(rembursementTypeMaster, headers);
				
				ResponseEntity<SingleResponseModel> response= restTemplate.exchange(urlRemb,HttpMethod.PUT,request, SingleResponseModel.class);
				
					if(response.getStatusCode() == HttpStatus.OK) {
						res = response.getBody();
					} else {
						System.out.println("Request Failed");
						System.out.println(response.getStatusCode());
					}
				
					}
					catch (Exception e){
						e.printStackTrace();
						res.setMessage("Failed to create Reimbursement Type .");
					}
				
				return res;
		}    

}
