package in.co.srdt.unif.controllers.enterprise;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.CreateDepartment;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.model.search.DepartmentSearch;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/enterprisesetup")
public class DepartmentController {	

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;
	
	DepartmentController(){
		
	}
	
	DepartmentController(HttpHeaders headers,RestTemplate restTemplate){
		this.headers=headers;
		this.restTemplate=restTemplate;
	}
	
	
	@RequestMapping("/managedepartment")
	public String manageDepartment(Model model) {

        CommonLOV[] datasets=null;
		
		String urlDatasets = appgateway.getAppgateway()+"/ReferenceDataSets/getAllDataSetsLOV";
		headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlDatasets, HttpMethod.GET, request, CommonLOV[].class);
		
		
		if(response.getStatusCode() == HttpStatus.OK) {
			datasets = response.getBody();
			
		} 
		
		model.addAttribute("datasets", datasets);
		
		model.addAttribute("status", Status.values());
				
		
		return "fragments/enterprise/manage/searchDepartment :: searchDepartment";
	}
	
	
	@ResponseBody
	@PostMapping(path="/searchDepartment/getDepartmentId")
	public DepartmentSearch[] departmentSearch(HttpServletRequest requestFromDT) {
		
		
		String name = requestFromDT.getParameter("name");
		String code = requestFromDT.getParameter("code");
		String status = requestFromDT.getParameter("status");
		String dataSets = requestFromDT.getParameter("dataSets");
		
		
		DepartmentSearch[] DepartmentSearch = null;
		
		String urlJob = appgateway.getAppgateway()+"/Departments/DepartmentsSearchList";
		
		String payLode = "{" +
						"\"name\"" + ":\"" +name+ "\"," +
						"\"code\"" + ":\"" +code+ "\"," +
						"\"dataset\"" + ":\"" +dataSets+ "\"," +						
						"\"status\"" + ":\"" +status+ "\"" +
						"}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<DepartmentSearch[]> response = restTemplate.exchange(urlJob, HttpMethod.POST, request, DepartmentSearch[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			DepartmentSearch = response.getBody();
		
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return DepartmentSearch;
	}
	
	
	
	
	
	

	@RequestMapping("/loadDepartmentPage")
	public String createDepartment(Model model) {		
			
		//System.out.println("Inside Department Create");
		CreateDepartment CreateDepartment=new CreateDepartment();
		
		     CommonLOV[] datasets=null;
			
			String urlDatasets = appgateway.getAppgateway()+"/ReferenceDataSets/getAllDataSetsLOV";
			headers.setContentType(MediaType.APPLICATION_JSON);
	         HttpEntity<String> request = new HttpEntity<String>(headers);
			
			ResponseEntity<CommonLOV[]> response = restTemplate.exchange(urlDatasets, HttpMethod.GET, request, CommonLOV[].class);
			
			if(response.getStatusCode() == HttpStatus.OK) {
				datasets = response.getBody();
				
			} 
			
			model.addAttribute("datasets", datasets);
			model.addAttribute("CreateDepartment", CreateDepartment);
		
		
		model.addAttribute("status", Status.values());
	
		return "fragments/enterprise/create/createDepartment :: createDeprt";
	}
	
	
	@ResponseBody
	@PostMapping(value="/createDepartmentResource")
	public String saveDepartment(HttpServletRequest requestFromDT) {
		
	
		String CR_DEPT_NAME = requestFromDT.getParameter("CR_DEPT_NAME");
		String CR_DEPT_CODE = requestFromDT.getParameter("CR_DEPT_CODE");
		String CR_DEPT_DATASET = requestFromDT.getParameter("CR_DEPT_DATASET");
		String CR_DEPT_EFFDT = requestFromDT.getParameter("CR_DEPT_EFFDT");
		String CR_DEPT_STATUS = requestFromDT.getParameter("CR_DEPT_STATUS");
		
		String setDept="";
		int temp=0;
		String url = appgateway.getAppgateway()+"/Departments/saveDepartments";
				
		String payLode = "{" +
				"\"actionid\"" + ":\"" +temp+ "\"," +
				"\"departmentsid\""  + ":\"" +temp+ "\"," +
				"\"name\"" + ":\"" +CR_DEPT_NAME+ "\"," +
				"\"code\""  + ":\"" +CR_DEPT_CODE+ "\"," +
				"\"dataset\"" + ":\"" +CR_DEPT_DATASET+ "\"," +
				"\"effectstartdate\"" + ":\"" +CR_DEPT_EFFDT+ "\"," +				
				"\"status\"" + ":\"" +CR_DEPT_STATUS+ "\"" +				
				"}";
		//System.out.println(payLode);
		
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			setDept = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return setDept;
	}
	
	
	
	



	@RequestMapping("edit/EditDepartment/{departmentsid}/{effdt}")
	public String editDepartment(@PathVariable("departmentsid") String departmentsid,@PathVariable("effdt") String effdt, Model model,HttpServletRequest requestFromDT)
			throws URISyntaxException, ParseException {
		
		
		String Searchdepartmentsid = departmentsid;
		DataSetController dataset=new DataSetController(headers,restTemplate,appgateway);

		CreateDepartment CreateDepartment=null;
		String urrlCreateDepartment= appgateway.getAppgateway()+"/Departments/getDepartments/" + Searchdepartmentsid;
		URI urlDP = new URI(urrlCreateDepartment);
	
		HttpHeaders headerss = new HttpHeaders();
		headerss.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> getDepartmentRequest = new HttpEntity<String>(headerss);

		ResponseEntity<CreateDepartment> getDepartmentResponse = restTemplate.exchange(urlDP, HttpMethod.GET,
				getDepartmentRequest, CreateDepartment.class);

		if (getDepartmentResponse.getStatusCode() == HttpStatus.OK) {
			CreateDepartment = getDepartmentResponse.getBody();
			
			
		} else {
			System.out.println("Request Failed");
			System.out.println(getDepartmentResponse.getStatusCode());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))!=0)
		{
			java.util.Date date1 = sdf.parse(effdt);
			java.util.Date date2 = sdf.parse(CreateDepartment.getEffectstartdate());
		
				if (date1.compareTo(date2) <= 0)
				{ 
					//System.out.println("earlier"); 
					CreateDepartment.setAdditionalatr("Error");
				}
				else if(date1.compareTo(date2) > 0)	  
				{
					CreateDepartment.setAdditionalatr(effdt);
				}
		}
		else if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))==0){
			CreateDepartment.setAdditionalatr("Correct");
		}
		
		
		
		requestFromDT.setAttribute("code","");
		requestFromDT.setAttribute("name","");
		model.addAttribute("datasetlist",dataset.getDatasetlist(requestFromDT));
		
		
		model.addAttribute("status", Status.values());
		model.addAttribute("CreateDepartment", CreateDepartment);
		return "fragments/enterprise/create/createDepartment:: createDeprt";

	}


		

	@ResponseBody
	@RequestMapping("edit/EditDepartment/correctDepartment")
	public String correctDepartment(HttpServletRequest requestFromDT) {
		
		String CR_DEPT_NAME = requestFromDT.getParameter("CR_DEPT_NAME");
		String CR_DEPT_CODE = requestFromDT.getParameter("CR_DEPT_CODE");
		String CR_DEPT_DATASET = requestFromDT.getParameter("CR_DEPT_DATASET");
		String CR_DEPT_EFFDT = requestFromDT.getParameter("CR_DEPT_EFFDT");
		String CR_DEPT_STATUS = requestFromDT.getParameter("CR_DEPT_STATUS");
		
		
		String CR_DEPT_ID = requestFromDT.getParameter("CR_DEPT_ID");
		String CR_DEPT_ACTIONID = requestFromDT.getParameter("CR_DEPT_ACTIONID");
		
		String flag=requestFromDT.getParameter("FLAG");
		flag=flag.trim();
		
		String url="";
		
		if(flag.equals("Correct")) {
			url = appgateway.getAppgateway()+"/Departments/correctDepartments";
			
		
		}
		else if(flag!="Correct") {

			url = appgateway.getAppgateway()+"/Departments/updateDepartments";
		}
		String setDept="";
		
		String payLode = "{" +
				"\"actionid\"" + ":\"" +CR_DEPT_ACTIONID+ "\"," +			
				"\"name\"" + ":\"" +CR_DEPT_NAME+ "\"," +
				"\"code\""  + ":\"" +CR_DEPT_CODE+ "\"," +
				"\"dataset\"" + ":\"" +CR_DEPT_DATASET+ "\"," +
				"\"effectstartdate\"" + ":\"" +CR_DEPT_EFFDT+ "\"," +				
				"\"status\"" + ":\"" +CR_DEPT_STATUS+ "\"," +
				"\"departmentsid\""  + ":\"" +CR_DEPT_ID+ "\"" +
				"}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			setDept = response.getBody();
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
		}
		
		return setDept;
	}

	
	

}
