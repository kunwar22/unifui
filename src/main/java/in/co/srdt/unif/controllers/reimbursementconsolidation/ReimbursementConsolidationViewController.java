package in.co.srdt.unif.controllers.reimbursementconsolidation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.reimbursementconsolidation.ShowReimbursementDetails;
import in.co.srdt.unif.utils.ApplicationGateway;



@Controller
@RequestMapping("/consolidate")
public class ReimbursementConsolidationViewController {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;

	@Autowired
	ApplicationGateway apiGateway;

	
	@RequestMapping("/manageconsolidate")
	public String reimbursementDetails(Model model,HttpServletRequest request) {
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  HttpEntity<String> requ =  new HttpEntity<>(headers); 
		 
		  CommonLOV[] reimbursementType=null;
		  String lovurl=appgateway.getAppgateway() + "/General/loadReimbursementNameLOV";
		  ResponseEntity<CommonLOV[]> responseLov=restTemplate.exchange(lovurl, HttpMethod.GET, requ, CommonLOV[].class);
		 if(responseLov.getStatusCode()==HttpStatus.OK) {
			 reimbursementType=responseLov.getBody();
		 }
		 //model.addAttribute("lov", reimbursementType);
		 //System.out.println(reimbursementType.toString());
		 List<CommonLOV> reimbrs = Arrays.asList(reimbursementType).stream().filter(x-> x.id != Long.parseLong("16")).collect(Collectors.toList());
	      
		 model.addAttribute("lov", reimbrs);
		return "fragments/reimbursementconsolidation/reimbursementDetails :: reimbursementDetails";
		
	}
	
	
	 @ResponseBody
	  @RequestMapping(value = "/getReimbursementDetailsdata/{remType}/{fromDate}/{toDate}/{remStatus}", method = RequestMethod.GET)
		public ShowReimbursementDetails[] showReimbursementDetails(HttpServletRequest request, @PathVariable("remType") String remType,@PathVariable("fromDate") String fromDate,
				@PathVariable("toDate") String toDate,@PathVariable("remStatus") String remStatus,Model model) {

			//System.out.println("In Reimbursement Details Search");
			//Login login = (Login) request.getSession().getAttribute("login");
			ShowReimbursementDetails[] res =null;
			String url = appgateway.getAppgatewaypyrl_sandhya()+"/api/ShowReimburse/getReimbursementdata/"+remType+"/"+fromDate+"/"+toDate+"/"+remStatus;
			//System.out.println("URL: " + url);

			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> req = new HttpEntity<>(headers);

			ResponseEntity<ShowReimbursementDetails[]> response = restTemplate.exchange(url, HttpMethod.GET, req,ShowReimbursementDetails[].class);
			
			//System.out.println(response);

			if (response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
				System.out.println(response.getStatusCode());
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			return res;
		}


}
