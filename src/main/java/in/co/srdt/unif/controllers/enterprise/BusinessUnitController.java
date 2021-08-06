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
import in.co.srdt.unif.model.create.BussinessUnit;
import in.co.srdt.unif.model.search.BUsearchresult;
import in.co.srdt.unif.model.search.DatasetListResult;
import in.co.srdt.unif.utils.ApplicationGateway;


@Controller
@RequestMapping("/enterprisesetup")
public class BusinessUnitController {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;

@RequestMapping("/managebusinessunit")
public String managebusinessunit(Model model) {

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
	return "fragments/enterprise/manage/manageBusinessUnit :: searchbusinessunit";
}

@RequestMapping("/createbusinessunit")
public String createbusineesunit(Model model) {
    //System.out.print("test");
   
    BussinessUnit bussinessUnit = new BussinessUnit();
   
	String url = appgateway.getAppgateway()+"/ReferenceDataSets/ReferenceDataSetsSearchList";
	DatasetListResult[] datasetObj = null;
	String payload = "{" + "\"code\"" + ":\"\"," + "\"name\"" + ":\"\"" + "}";
	//System.out.println("ravvvv"+payload);
	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> request = new HttpEntity<String>(payload, headers);

	ResponseEntity<DatasetListResult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
			DatasetListResult[].class);

	if (response.getStatusCode() == HttpStatus.OK) {
		datasetObj = response.getBody();
	}
	model.addAttribute("BUDetails", bussinessUnit);
	
	
	model.addAttribute("status", Status.values());
	model.addAttribute("datasetlist", datasetObj);
	return "fragments/enterprise/create/createBusinessUnit :: createbusinessunit";
}

@ResponseBody
@PostMapping("/saveBusinessUnit")
public String saveBusinessUnit(HttpServletRequest requestFromDT, Model model) {

	String setBU = "";

	String strcode = requestFromDT.getParameter("code");
	String strname = requestFromDT.getParameter("name");
	String streffdt = requestFromDT.getParameter("effectStartDate");
	String strstatus = requestFromDT.getParameter("statusBU");
	String strdataset = requestFromDT.getParameter("referenceDataSets");

	
	String url = appgateway.getAppgateway()+"/BusinessUnit/saveBusinessUnit";

	String payLode = "{" +

			"\"code\"" + ":\"" + strcode + "\"," + "\"effectstartdate\"" + ":\"" + streffdt + "\"," + "\"name\""
			+ ":\"" + strname + "\"," + "\"dataset\"" + ":\"" + strdataset + "\"," + "\"statusbu\"" + ":\""
			+ strstatus + "\"" +

			"}";
	
	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

	if (response.getStatusCode() == HttpStatus.OK) {
		setBU = response.getBody();
	}
	model.addAttribute("legalAddressDetails", setBU);
	
	return setBU;
}

@ResponseBody
@PostMapping("/searchBU")
public BUsearchresult[] getBUList(HttpServletRequest requestFromDT) {

	String searchcode = requestFromDT.getParameter("code");
	String searchname = requestFromDT.getParameter("name");
	String searchstatus = requestFromDT.getParameter("statusBU");

	String url = appgateway.getAppgateway()+"/BusinessUnit/BusinessUnitSearchList";
	BUsearchresult[] BUObj = null;
	String payload = "{" + "\"code\"" + ":\"" + searchcode + "\"," + "\"name\"" + ":\"" + searchname + "\","
			+ "\"status\"" + ":\"" + searchstatus + "\"" + "}";
	
	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> request = new HttpEntity<String>(payload, headers);

	ResponseEntity<BUsearchresult[]> response = restTemplate.exchange(url, HttpMethod.POST, request,
			BUsearchresult[].class);

	if (response.getStatusCode() == HttpStatus.OK) {
		BUObj = response.getBody();
	}
	
	return BUObj;

}

@ResponseBody
@PostMapping("/correct/BusinessUnit")
public String correctBusinessUnit(HttpServletRequest requestFromDT, Model model) {

	String setBU = "";

	String strcode = requestFromDT.getParameter("code");
	String strname = requestFromDT.getParameter("name");
	String streffdt = requestFromDT.getParameter("effectStartDate");
	String strstatus = requestFromDT.getParameter("statusBU");
	String strdataset = requestFromDT.getParameter("referenceDataSets");
	
	String buactionid = requestFromDT.getParameter("actionid");
	String businessunitid = requestFromDT.getParameter("businessunitid");
	String flag=requestFromDT.getParameter("FLAG");
	flag=flag.trim();

	String url="";
	
	if(flag.equals("Correct")) {
		url = appgateway.getAppgateway()+"/BusinessUnit/correctBusinessUnit";
	}
	else if(flag!="Correct") {

		url = appgateway.getAppgateway()+"/BusinessUnit/updateBusinessUnit";
	
	}
	
	String payLode = "{" +

            "\"actionid\"" + ":\"" + buactionid + "\"," +
            "\"businessunitid\"" + ":\"" + businessunitid + "\"," + 
			"\"code\"" + ":\"" + strcode + "\"," +
			"\"dataset\"" + ":\"" + strdataset + "\"," +
			"\"datasetname\"" + ":\"\"," + 				
			"\"effectstartdate\"" + ":\"" + streffdt + "\"," + 
			"\"name\""+ ":\"" + strname + "\"," + 				
			"\"statusbu\"" + ":\""+ strstatus + "\"" +

			"}";
	
	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

	if (response.getStatusCode() == HttpStatus.OK) {
		setBU = response.getBody();
	}
	model.addAttribute("legalAddressDetails", setBU);
	return setBU;
}

@RequestMapping("edit/EditBusinessUnit/{businessunitid}/{effdt}")
public String EditBusinessUnit(@PathVariable("businessunitid") String businessunitid,@PathVariable("effdt") String effdt, Model model,HttpServletRequest requestFromDT)throws URISyntaxException, ParseException {
	
	String Searchbusinessunitid = businessunitid;

	DataSetController dataset=new DataSetController(headers,restTemplate,appgateway);
	
	BussinessUnit BusinessUnitget = null;
	String urrlBusinees= appgateway.getAppgateway()+"/BusinessUnit/getBusinessUnit/" + Searchbusinessunitid;
	URI urlBU = new URI(urrlBusinees);
	
	HttpHeaders headerss = new HttpHeaders();
	headerss.setContentType(MediaType.APPLICATION_JSON);

	HttpEntity<String> getBusinessUnitRequest = new HttpEntity<String>(headerss);

	ResponseEntity<BussinessUnit> getBusinessUnitResponse = restTemplate.exchange(urlBU, HttpMethod.GET,
			getBusinessUnitRequest, BussinessUnit.class);

	if (getBusinessUnitResponse.getStatusCode() == HttpStatus.OK) {
		
		BusinessUnitget = getBusinessUnitResponse.getBody();
		
	} else {
		System.out.println("Request Failed");
		System.out.println(getBusinessUnitResponse.getStatusCode());
	}
	
	requestFromDT.setAttribute("code","");
	requestFromDT.setAttribute("name","");

	model.addAttribute("datasetlist",dataset.getDatasetlist(requestFromDT));
	
	model.addAttribute("status", Status.values());
	model.addAttribute("BUDetails", BusinessUnitget);
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))!=0)
	{
		java.util.Date date1 = sdf.parse(effdt);
		java.util.Date date2 = sdf.parse(BusinessUnitget.getEffectstartdate());
	
			if (date1.compareTo(date2) <= 0)
			{ 
				BusinessUnitget.setAdditionalatr("Error");
			}
			else if(date1.compareTo(date2) > 0)	  
			{
				BusinessUnitget.setAdditionalatr(effdt);
			}
	}
	else if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))==0){
		BusinessUnitget.setAdditionalatr("Correct");
	}
	
	return "fragments/enterprise/create/createBusinessUnit :: createbusinessunit";

}

}
