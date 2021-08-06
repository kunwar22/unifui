package in.co.srdt.unif.controllers.enterprise;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.search.DatasetListResult;
import in.co.srdt.unif.model.search.GradeSearchResult;
import in.co.srdt.unif.model.create.Grades;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;

@SessionAttributes({ "grades" })
@Controller
@RequestMapping("/enterprisesetup")
public class GradeController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;

	@RequestMapping("/managegrade")
	public String searchSalaryGrade(Model model) {

		model.addAttribute("status", Status.values());
		return "fragments/enterprise/manage/searchGrade :: searchGrade";
	}

	@RequestMapping("/createGrade")
	public String createSalaryGrade(Model model,@ModelAttribute("grades") Grades grades) {

		String url = appgateway.getAppgateway()+"/ReferenceDataSets/ReferenceDataSetsSearchList";
		DatasetListResult[] datasetObj = null;
		String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\"" + "}";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<DatasetListResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				DatasetListResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			datasetObj = response.getBody();
		}


		model.addAttribute("status", Status.values());
		model.addAttribute("datasetlist", datasetObj);
		return "fragments/enterprise/create/createGrade :: createGrade";
	}
	
	@RequestMapping(value = "/saveGrade", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody String saveGrade(@ModelAttribute("grades") Grades grades, SessionStatus status)
	{
		SingleResponseModel res = new SingleResponseModel();
		try {
			/*NEW CODE*/
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.setBearerAuth(AppConstants.ACCESS_TOKEN);
			
			HttpEntity<Grades> request = new HttpEntity<Grades>(grades, headers);
			
			ResponseEntity<SingleResponseModel> response= restTemplate.exchange(appgateway.getAppgateway()+"/Grades/saveGradesAndGradeSteps",HttpMethod.POST,request, SingleResponseModel.class);
			if(response.getStatusCode() == HttpStatus.OK) {
				res = response.getBody();
				
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			//System.out.println(res.getMessage());
			//System.out.println(res.getStatus());
			status.setComplete();
			/*if(res.getStatus().equals("Success")) {
				status.setComplete();
			}*/
			/*NEW CODE*/
			//OLD CODE//res = restTemplate.postForObject("http://192.200.12.83:9194/api/courseplan/create", coursePlanWrapper, SingleResponseModel.class);
		}
		catch (Exception e){
			e.printStackTrace();
			res.setMessage("Failed to create Grade.");
		}
		return "Data Posted \n" + res.getMessage();
	}

	
	@ResponseBody
	@PostMapping("/searchGrade")
	public GradeSearchResult[] getGradeList(HttpServletRequest requestFromDT) {

		String searchcode = requestFromDT.getParameter("code");
		String searchname = requestFromDT.getParameter("name");
		String searchstatus = requestFromDT.getParameter("statusGrade");
		
		String url = appgateway.getAppgateway()+"/Grades/gradesSearchList";
		GradeSearchResult[] gradeObj = null;
		String payload = "{" + "\"code\"" + ":\"" + searchcode + "\"," + "\"name\"" + ":\"" + searchname + "\","
				+ "\"status\"" + ":\"" + searchstatus + "\"" + "}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<GradeSearchResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				GradeSearchResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			gradeObj = response.getBody();
		}
		
		return gradeObj;

	}
	

	@ModelAttribute("grades")
	private Grades getGrades() {
		return new Grades();
	}
	
	@PostMapping("/nextandprev")
	public String nextandprev( @ModelAttribute("grades")  Grades grades,
		Model model,String cmd,String currentcmd, HttpServletRequest req) {
		
		String url = appgateway.getAppgateway()+"/ReferenceDataSets/ReferenceDataSetsSearchList";
		DatasetListResult[] datasetObj = null;
		String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\"" + "}";

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload, headers);

		ResponseEntity<DatasetListResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
				DatasetListResult[].class);

		if (response.getStatusCode() == HttpStatus.OK) {
			datasetObj = response.getBody();
		}


		model.addAttribute("status", Status.values());
		model.addAttribute("datasetlist", datasetObj);

		
		//System.out.println("CMD :: "+cmd);
		cmd=cmd.replaceAll(",$","");
		//System.out.println("CMD :: "+cmd);
		//System.out.println("DATA :: "+ grades.toString());
		
		return "fragments/enterprise/create/createGrade :: " + cmd + "";
	}


}
