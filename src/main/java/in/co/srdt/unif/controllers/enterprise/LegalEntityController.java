package in.co.srdt.unif.controllers.enterprise;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.create.CreateLeagalEntity;
import in.co.srdt.unif.model.create.LegalEntity;
import in.co.srdt.unif.model.search.LegalEntitySearch;
import in.co.srdt.unif.model.LegalJuriById;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/enterprisesetup")
public class LegalEntityController {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApplicationGateway appgateway;
	
	@Autowired
	private HttpHeaders headers;
	
	@RequestMapping("/managelegalentitye")
	public String searchlegalentity(Model model) {
		
		CommonLOV[] cd=null;
		
		String url = appgateway.getAppgateway()+"/Country/getAllCountry";
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			cd = response.getBody();
			
		} 
		
		model.addAttribute("CountryDetails", cd);

		model.addAttribute("status", Status.values());
		
		return "fragments/enterprise/manage/manageLegalEntity :: searchlegalentity";
	}

	@RequestMapping("/createLegalEntity")
	public String createLegalEntity(Model model) {		
		
		CreateLeagalEntity createLeagalEntity=new  CreateLeagalEntity();
			
		//System.out.println("Inside Legal Entity");
	
		CommonLOV[] cd=null;
		
		String url = appgateway.getAppgateway()+"/Country/getAllCountry";
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(headers);
       
       	ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);
		
		
		if(response.getStatusCode() == HttpStatus.OK) {
			cd = response.getBody();
		}

		model.addAttribute("LeagalEntity", createLeagalEntity);
		model.addAttribute("status", Status.values());
		model.addAttribute("CountryDetails", cd);
		return "fragments/enterprise/create/createLegalEntity :: createlegalentity";
	}
	
		
	@ResponseBody
	@PostMapping("/seach/LegalAddress/searchLegalAddress")
	public LegalEntity[] getlegaladdress(HttpServletRequest requestFromDT) {
		
		String searchcountry=requestFromDT.getParameter("country");
		String searchstatus=requestFromDT.getParameter("status");
		
		String url = appgateway.getAppgateway()+"/LegalAddress/legalAddressSearchList";
		LegalEntity[] legaddr=null;

		String payload="{"+
						"\"country\""+":\""+searchcountry+"\","+
						"\"status\""+":\""+searchstatus+"\""+
						"}";
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(payload,headers);		
		ResponseEntity<LegalEntity[]> response = restTemplate.exchange(url, HttpMethod.POST, request, LegalEntity[].class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			legaddr = response.getBody();
			
		} 
			
		return legaddr;
	}
	
	
	//*********************START-LegalJurisdictionsByCountr*********************//
		@ResponseBody
		@RequestMapping("/seach/LegalJurisdiction/searchLegalJurisdiction/{countryId}")
		public LegalJuriById legalJuriById(@PathVariable("countryId") String countryId, Model model) {
			
			
			  LegalJuriById Lj=null;
			  String urlLegalJurisction = appgateway.getAppgateway()+"/LegalEntity/getLegalJurisdictionsByCountry/"+countryId;
			  
			 
			  headers.setContentType(MediaType.APPLICATION_JSON); HttpEntity<String>
			  request = new HttpEntity<String>(headers);
			  
			  ResponseEntity<LegalJuriById> response = restTemplate.exchange(urlLegalJurisction,
			  HttpMethod.GET, request, LegalJuriById.class);
			  
			  if(response.getStatusCode() == HttpStatus.OK) { 
				  Lj = response.getBody();
			 // System.out.println("LegalJurisdictions ::::::::::::::::::::"+Lj.getTinorpan());
			  }
			  model.addAttribute("LegalJuri", Lj);
			 
			
		      return Lj;
			
		}
		
		
		//************END-LegalJurisdictionsByCountry*********//
		

        //******* START- Save legal entity *****///
		
		@ResponseBody
		@PostMapping(value="/LegalEntity/CreateLegalEntity")
		public String saveLegalEntity(HttpServletRequest requestFromDT) {
			
		
			String CR_LE_COUNTRY = requestFromDT.getParameter("CR_LE_COUNTRY");
			String CR_LE_EFFDT = requestFromDT.getParameter("CR_LE_EFFDT");
			String CR_LE_EFFENDDT = requestFromDT.getParameter("CR_LE_EFFENDDT");
			String CR_LE_IDJURI = requestFromDT.getParameter("CR_LE_IDJURI");
			String CR_LE_PAYROLL = requestFromDT.getParameter("CR_LE_PAYROLL");
			String CR_LE_EMPLOYER = requestFromDT.getParameter("CR_LE_EMPLOYER");
			String CR_LE_ADDRESS = requestFromDT.getParameter("CR_LE_ADDRESS");
			String CR_LE_IDNTI = requestFromDT.getParameter("CR_LE_IDNTI");
			String CR_LE_NAME = requestFromDT.getParameter("CR_LE_NAME");
			String CR_LE_PLCREG = requestFromDT.getParameter("CR_LE_PLCREG");
			//String CR_LE_UNIT = requestFromDT.getParameter("CR_LE_UNIT");
			//String CR_LE_PAN = requestFromDT.getParameter("CR_LE_PAN");
			
			String 	CR_LE_REGN_DATE = requestFromDT.getParameter("CR_LE_REGN_DATE"); 
			String 	CR_LE_PF_REGN_DATE = requestFromDT.getParameter("CR_LE_PF_REGN_DATE"); 
			String 	CR_LE_PF_SIGNATORY_NAME = requestFromDT.getParameter("CR_LE_PF_SIGNATORY_NAME"); 
			String 	CR_LE_PF_NUMBER = requestFromDT.getParameter("CR_LE_PF_NUMBER"); 
			String 	CR_LE_PFRULES_CHECKBOX = requestFromDT.getParameter("CR_LE_PFRULES_CHECKBOX"); 
			String 	CR_LE_ESI_REGN_DATE = requestFromDT.getParameter("CR_LE_ESI_REGN_DATE"); 
			String 	CR_LE_ESI_SIGNATORY_NAME = requestFromDT.getParameter("CR_LE_ESI_SIGNATORY_NAME"); 
			String 	CR_LE_ESI_NUMBER = requestFromDT.getParameter("CR_LE_ESI_NUMBER"); 
			String 	CR_LE_PI_REGN_DATE = requestFromDT.getParameter("CR_LE_PI_REGN_DATE"); 
			String 	CR_LE_PI_SIGNATORY_NAME = requestFromDT.getParameter("CR_LE_PI_SIGNATORY_NAME"); 
			String 	CR_LE_PI_NUMBER = requestFromDT.getParameter("CR_LE_PI_NUMBER"); 
			String 	CR_LE_IT_CIT_TDS = requestFromDT.getParameter("CR_LE_IT_CIT_TDS"); 
			String 	CR_LE_IT_TAN_NUMBER = requestFromDT.getParameter("CR_LE_IT_TAN_NUMBER"); 
			String 	CR_LE_IT_TAN_CIRCLE = requestFromDT.getParameter("CR_LE_IT_TAN_CIRCLE"); 
			String 	CR_LE_LAB_SIGNATORY_NAME = requestFromDT.getParameter("CR_LE_LAB_SIGNATORY_NAME"); 
			String 	CR_LE_LAB_ADDRESS = requestFromDT.getParameter("CR_LE_LAB_ADDRESS");
			
			
			
			String CR_LE_REGNO = requestFromDT.getParameter("CR_LE_REGNO");
			String CR_LE_TIN_PAN = requestFromDT.getParameter("CR_LE_TIN_PAN");
			
			
					
			//System.out.println("Create Legal Entity "+CR_LE_COUNTRY+" "+CR_LE_EFFDT+" "+CR_LE_EFFENDDT+" "+CR_LE_IDJURI+" "+CR_LE_PAYROLL+" end");
			//System.out.println("Create Legal Entity "+CR_LE_EMPLOYER+" "+CR_LE_ADDRESS+" "+CR_LE_IDNTI+" "+CR_LE_NAME+" "+CR_LE_PLCREG+" end");
			//System.out.println("Create Legal Entity "+CR_LE_UNIT+" "+CR_LE_PAN+" end");
			String setLegalEntity="";
			
			String urlLegalEntity = appgateway.getAppgateway()+"/LegalEntity/saveLegalEntity";
			
			
			String payLode = "{" +
			
					"\"country\"" + ":\"" +CR_LE_COUNTRY+ "\"," +
					"\"effectenddate\""  + ":\"" +CR_LE_EFFENDDT+ "\"," +
					"\"effectstartdate\"" + ":\"" +CR_LE_EFFDT+ "\"," +
					"\"identifyingjurisdictions\"" + ":\"" +CR_LE_IDJURI+ "\"," +	
					"\"islegalemployer\"" + ":\"" +CR_LE_PAYROLL+ "\"," +
					"\"ispayrollstatutoryunit\""  + ":\"" +CR_LE_EMPLOYER+ "\"," +
					"\"legaladdress\"" + ":\"" +CR_LE_ADDRESS+ "\"," +
					"\"legalentityidentifier\"" + ":\"" +CR_LE_IDNTI+ "\"," +	
					"\"name\"" + ":\"" +CR_LE_NAME+ "\"," +
					"\"placeofregistration\""  + ":\"" +CR_LE_PLCREG+ "\"," +
					"\"registrationnumber\"" + ":\"" +CR_LE_REGNO+ "\"," +					
					"\"tinpan\"" + ":\"" +CR_LE_TIN_PAN+ "\"," +
					"\"registrationdate\"" + ":\"" +CR_LE_REGN_DATE+ "\"," +
					"\"pfregistrationdate\"" + ":\"" +CR_LE_PF_REGN_DATE+ "\"," +
					"\"pfnumber\"" + ":\"" +CR_LE_PF_NUMBER+ "\"," +
					"\"pfrulesforemployee\"" + ":\"" +CR_LE_PFRULES_CHECKBOX+ "\"," +
					"\"pfsignatoryname\"" + ":\"" +CR_LE_PF_SIGNATORY_NAME+ "\"," +
					"\"esinumber\"" + ":\"" +CR_LE_ESI_NUMBER+ "\"," +
					"\"esiregistrationdate\"" + ":\"" +CR_LE_ESI_REGN_DATE+ "\"," +
					"\"esisignatoryname\"" + ":\"" +CR_LE_ESI_SIGNATORY_NAME+ "\"," +
					"\"pinumber\"" + ":\"" +CR_LE_PI_NUMBER+ "\"," +
					"\"piregistrationdate\"" + ":\"" +CR_LE_PI_REGN_DATE+ "\"," +
					"\"pisignatoryname\"" + ":\"" +CR_LE_PI_SIGNATORY_NAME+ "\"," +
					"\"tancircle\"" + ":\"" +CR_LE_IT_TAN_CIRCLE+ "\"," +
					"\"tannumber\"" + ":\"" +CR_LE_IT_TAN_NUMBER+ "\"," +
					"\"citortds\"" + ":\"" +CR_LE_IT_CIT_TDS+ "\"," +
					"\"laboursignatoryname\"" + ":\"" +CR_LE_LAB_SIGNATORY_NAME+ "\"," +
					"\"labouraddress\"" + ":\"" +CR_LE_LAB_ADDRESS+ "\"" +
					
					"}";
			
		//	System.out.println("PAYLOAD ::"+payLode);
					
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(urlLegalEntity, HttpMethod.POST, request, String.class);
			
			if(response.getStatusCode() == HttpStatus.OK) {
				setLegalEntity = response.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			
			return setLegalEntity;
		}
		
		//******END- Save legal entity ****/////
	
		
		//get legal entity by search start
		
		@ResponseBody
		@PostMapping(path="/searchLegalEntity/getLegalEntityId")
		public LegalEntitySearch[] legalEntitySearch(HttpServletRequest requestFromDT)
		{
					
			String Country = requestFromDT.getParameter("COUNTRY");
			String LegalEntityIdentifier = requestFromDT.getParameter("ENTITY");
			String name = requestFromDT.getParameter("NAME");
				
			LegalEntitySearch[] LegalEntity = null;
					
			String urlLegal = appgateway.getAppgateway()+"/LegalEntity/legalEntitySearchList";
					
			String payLode = "{" +
									"\"country\"" + ":\"" +Country+ "\"," +
									"\"legalentityidentifier\"" + ":\"" +LegalEntityIdentifier+ "\"," +					
									"\"name\"" + ":\"" +name+ "\"" +
									"}";
					
			headers.setContentType(MediaType.APPLICATION_JSON);
			//headers.add("Authorization", AppConstants.ACCESS_TOKEN);
			HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
				
			ResponseEntity<LegalEntitySearch[]> response = restTemplate.exchange(urlLegal, HttpMethod.POST, request, LegalEntitySearch[].class);
					
			if(response.getStatusCode() == HttpStatus.OK) 
			{
				LegalEntity = response.getBody();
				//System.out.println("if "+LegalEntity);
			}
			else 
			{
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			
			for(int i=0;i<LegalEntity.length;i++) {
				if(LegalEntity[i].getEffectenddate()==null) {
					LegalEntity[i].setEffectenddate("");
				}
			}
					
			return LegalEntity;
		}
				
				//********END- get legal entity **********//
				
				

		@RequestMapping("/edit/EditLegalEntity/{legalentityid}/{effdt}")
		public String editLegalEntity(@PathVariable("legalentityid") String legalentityid,@PathVariable("effdt") String effdt, Model model,HttpServletRequest requestFromDT)
				throws URISyntaxException,ParseException {
			
			model.addAttribute("status", Status.values());
			String Searchlegalentityid = legalentityid;	
			
			CreateLeagalEntity createLeagalEntity=null;
			String urlLegalEntity= appgateway.getAppgateway()+"/LegalEntity/getLegalEntity/" + Searchlegalentityid;
			URI urlLE = new URI(urlLegalEntity);
			HttpHeaders headerss = new HttpHeaders();
			headerss.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> getLegalEntityRequest = new HttpEntity<String>(headerss);

			ResponseEntity<CreateLeagalEntity> getLegalEntityResponse = restTemplate.exchange(urlLE, HttpMethod.GET,
					getLegalEntityRequest, CreateLeagalEntity.class);

			if (getLegalEntityResponse.getStatusCode() == HttpStatus.OK) {
				
				createLeagalEntity = getLegalEntityResponse.getBody();
				
				
			} else {
				System.out.println("Request Failed");
				System.out.println(getLegalEntityResponse.getStatusCode());
			}

			model.addAttribute("LeagalEntity", createLeagalEntity);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))!=0)
			{
				java.util.Date date1 = sdf.parse(effdt);
				java.util.Date date2 = sdf.parse(createLeagalEntity.getEffectstartdate());
			
					if (date1.compareTo(date2) <= 0)
					{ 
						//System.out.println("earlier"); 
						createLeagalEntity.setAdditionalatr("Error");
					}
					else if(date1.compareTo(date2) > 0)	  
					{
						createLeagalEntity.setAdditionalatr(effdt);
					}
			}
			else if(sdf.parse(effdt).compareTo(sdf.parse("1900-01-01"))==0){
				createLeagalEntity.setAdditionalatr("Correct");
			}
			
			CommonLOV[] cd=null;
			
			String url = appgateway.getAppgateway()+"/Country/getAllCountry";
			headers.setContentType(MediaType.APPLICATION_JSON);
		    HttpEntity<String> request = new HttpEntity<String>(headers);
		    
		    headers.setContentType(MediaType.APPLICATION_JSON);
		   
			ResponseEntity<CommonLOV[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommonLOV[].class);
			
			
			if(response.getStatusCode() == HttpStatus.OK) {
				cd = response.getBody();
				} 
			
			model.addAttribute("CountryDetails", cd);
			
			return "fragments/enterprise/create/createLegalEntity :: createlegalentity";

		}
				
		
//******* START- Correct legal entity *****///

		@ResponseBody
		@PostMapping(value="/LegalEntity/CorrectLegalEntity")
		public String correctLegalEntity(HttpServletRequest requestFromDT) {
			
			String CR_LEGAL_ID = requestFromDT.getParameter("CR_LEGAL_ID");
			String CR_LEGAL_ACTIONID = requestFromDT.getParameter("CR_LEGAL_ACTIONID");
		
			String CR_LE_COUNTRY = requestFromDT.getParameter("CR_LE_COUNTRY");
			String CR_LE_EFFDT = requestFromDT.getParameter("CR_LE_EFFDT");
			String CR_LE_EFFENDDT = requestFromDT.getParameter("CR_LE_EFFENDDT");
			String CR_LE_IDJURI = requestFromDT.getParameter("CR_LE_IDJURI");
			String CR_LE_PAYROLL = requestFromDT.getParameter("CR_LE_PAYROLL");
			String CR_LE_EMPLOYER = requestFromDT.getParameter("CR_LE_EMPLOYER");
			String CR_LE_ADDRESS = requestFromDT.getParameter("CR_LE_ADDRESS");
			String CR_LE_IDNTI = requestFromDT.getParameter("CR_LE_IDNTI");
			String CR_LE_NAME = requestFromDT.getParameter("CR_LE_NAME");
			String CR_LE_PLCREG = requestFromDT.getParameter("CR_LE_PLCREG");
			String CR_LE_UNIT = requestFromDT.getParameter("CR_LE_UNIT");
			String CR_LE_PAN = requestFromDT.getParameter("CR_LE_PAN");			
			String CR_LE_REGNO = requestFromDT.getParameter("CR_LE_REGNO");
			String CR_LE_TIN_PAN = requestFromDT.getParameter("CR_LE_TIN_PAN");
			
			String 	CR_LE_REGN_DATE = requestFromDT.getParameter("CR_LE_REGN_DATE"); 
			String 	CR_LE_PF_REGN_DATE = requestFromDT.getParameter("CR_LE_PF_REGN_DATE"); 
			String 	CR_LE_PF_SIGNATORY_NAME = requestFromDT.getParameter("CR_LE_PF_SIGNATORY_NAME"); 
			String 	CR_LE_PF_NUMBER = requestFromDT.getParameter("CR_LE_PF_NUMBER"); 
			String 	CR_LE_PFRULES_CHECKBOX = requestFromDT.getParameter("CR_LE_PFRULES_CHECKBOX"); 
			String 	CR_LE_ESI_REGN_DATE = requestFromDT.getParameter("CR_LE_ESI_REGN_DATE"); 
			String 	CR_LE_ESI_SIGNATORY_NAME = requestFromDT.getParameter("CR_LE_ESI_SIGNATORY_NAME"); 
			String 	CR_LE_ESI_NUMBER = requestFromDT.getParameter("CR_LE_ESI_NUMBER"); 
			String 	CR_LE_PI_REGN_DATE = requestFromDT.getParameter("CR_LE_PI_REGN_DATE"); 
			String 	CR_LE_PI_SIGNATORY_NAME = requestFromDT.getParameter("CR_LE_PI_SIGNATORY_NAME"); 
			String 	CR_LE_PI_NUMBER = requestFromDT.getParameter("CR_LE_PI_NUMBER"); 
			String 	CR_LE_IT_CIT_TDS = requestFromDT.getParameter("CR_LE_IT_CIT_TDS"); 
			String 	CR_LE_IT_TAN_NUMBER = requestFromDT.getParameter("CR_LE_IT_TAN_NUMBER"); 
			String 	CR_LE_IT_TAN_CIRCLE = requestFromDT.getParameter("CR_LE_IT_TAN_CIRCLE"); 
			String 	CR_LE_LAB_SIGNATORY_NAME = requestFromDT.getParameter("CR_LE_LAB_SIGNATORY_NAME"); 
			String 	CR_LE_LAB_ADDRESS = requestFromDT.getParameter("CR_LE_LAB_ADDRESS");
			
			
			String flag=requestFromDT.getParameter("FLAG");
			flag=flag.trim();
			
					
			//System.out.println(flag+" Create Legal Entity "+CR_LE_COUNTRY+" "+CR_LE_EFFDT+" "+CR_LE_EFFENDDT+" "+CR_LE_IDJURI+" "+CR_LE_PAYROLL+" end");
			//System.out.println("Create Legal Entity "+CR_LE_EMPLOYER+" "+CR_LE_ADDRESS+" "+CR_LE_IDNTI+" "+CR_LE_NAME+" "+CR_LE_PLCREG+" end");
			//System.out.println("Create Legal Entity "+CR_LE_UNIT+" "+CR_LE_PAN+" end");
			String setLegalEntity="";
			String urlLegalEntity="";
			
			if(flag.equals("Correct")) {
				urlLegalEntity = appgateway.getAppgateway()+"/LegalEntity/correctLegalEntity";
				
			}
			else if(flag!="Correct") {

				urlLegalEntity = appgateway.getAppgateway()+"/LegalEntity/updateLegalEntity";
			
				}
			
			
			String payLode = "{" +
			
					"\"actionid\"" + ":\"" +CR_LEGAL_ACTIONID+ "\"," +
					"\"legalentityid\""  + ":\"" +CR_LEGAL_ID+ "\"," +					
					"\"country\"" + ":\"" +CR_LE_COUNTRY+ "\"," +
					"\"effectenddate\""  + ":\"" +CR_LE_EFFENDDT+ "\"," +
					"\"effectstartdate\"" + ":\"" +CR_LE_EFFDT+ "\"," +
					"\"identifyingjurisdictions\"" + ":\"" +CR_LE_IDJURI+ "\"," +	
					"\"islegalemployer\"" + ":\"" +CR_LE_PAYROLL+ "\"," +
					"\"ispayrollstatutoryunit\""  + ":\"" +CR_LE_EMPLOYER+ "\"," +
					"\"legaladdress\"" + ":\"" +CR_LE_ADDRESS+ "\"," +
					"\"legalentityidentifier\"" + ":\"" +CR_LE_IDNTI+ "\"," +	
					"\"name\"" + ":\"" +CR_LE_NAME+ "\"," +
					"\"placeofregistration\""  + ":\"" +CR_LE_PLCREG+ "\"," +
					"\"registrationnumber\"" + ":\"" +CR_LE_REGNO+ "\"," +					
					"\"tinpan\"" + ":\"" +CR_LE_TIN_PAN+ "\"," +
					"\"registrationdate\"" + ":\"" +CR_LE_REGN_DATE+ "\"," +
					"\"pfregistrationdate\"" + ":\"" +CR_LE_PF_REGN_DATE+ "\"," +
					"\"pfnumber\"" + ":\"" +CR_LE_PF_NUMBER+ "\"," +
					"\"pfrulesforemployee\"" + ":\"" +CR_LE_PFRULES_CHECKBOX+ "\"," +
					"\"pfsignatoryname\"" + ":\"" +CR_LE_PF_SIGNATORY_NAME+ "\"," +
					"\"esinumber\"" + ":\"" +CR_LE_ESI_NUMBER+ "\"," +
					"\"esiregistrationdate\"" + ":\"" +CR_LE_ESI_REGN_DATE+ "\"," +
					"\"esisignatoryname\"" + ":\"" +CR_LE_ESI_SIGNATORY_NAME+ "\"," +
					"\"pinumber\"" + ":\"" +CR_LE_PI_NUMBER+ "\"," +
					"\"piregistrationdate\"" + ":\"" +CR_LE_PI_REGN_DATE+ "\"," +
					"\"pisignatoryname\"" + ":\"" +CR_LE_PI_SIGNATORY_NAME+ "\"," +
					"\"tancircle\"" + ":\"" +CR_LE_IT_TAN_CIRCLE+ "\"," +
					"\"tannumber\"" + ":\"" +CR_LE_IT_TAN_NUMBER+ "\"," +
					"\"citortds\"" + ":\"" +CR_LE_IT_CIT_TDS+ "\"," +
					"\"laboursignatoryname\"" + ":\"" +CR_LE_LAB_SIGNATORY_NAME+ "\"," +
					"\"labouraddress\"" + ":\"" +CR_LE_LAB_ADDRESS+ "\"" +
					
					"}";
			//System.out.println("PAYLOAD :: "+payLode);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(payLode, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(urlLegalEntity, HttpMethod.PUT, request, String.class);
			
			if(response.getStatusCode() == HttpStatus.OK) {
				setLegalEntity = response.getBody();
			} else {
				System.out.println("Request Failed");
				System.out.println(response.getStatusCode());
			}
			
			return setLegalEntity;
		}
		
		//******END- Correct legal entity ****/////
	
		

}
