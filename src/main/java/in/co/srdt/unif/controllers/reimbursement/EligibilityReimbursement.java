package in.co.srdt.unif.controllers.reimbursement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.eligibilityreimb.AllCeilingLimits;
import in.co.srdt.unif.model.eligibilityreimb.EligibilityById;
import in.co.srdt.unif.model.eligibilityreimb.PersonData;
import in.co.srdt.unif.utils.ApplicationGateway;

@Service
public class EligibilityReimbursement {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers;
	
	@Autowired
	private ApplicationGateway appgateway;
	
	private String rep_period;
	private String abs_id;
	
	public String getCeilinglimit(String rID,String emplid) {
		//System.out.println("called");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		String entitlementamt=null;
		int flag=0,cnt=0,i;
		AllCeilingLimits[] allCeilingLimits=null;
		EligibilityById[] eligibilityById=null;
		PersonData personData=null;
		
		String urlgetallceilings = appgateway.getAppgatewayabs()+"/PersonEligibility/Reimb/getAllCeilingLimitListByReimid/"+rID;
		ResponseEntity<AllCeilingLimits[]> response = restTemplate.exchange(urlgetallceilings, HttpMethod.GET, request, AllCeilingLimits[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			allCeilingLimits = response.getBody();
		}else {
			System.out.println("Response fail: "+response.getStatusCode());
		}
		
		for(i=0;i<allCeilingLimits.length;i++) {
			//System.out.println("EID FOR CEILING LIMIT :: "+allCeilingLimits[i].getEligibilityid());
			flag=0;cnt=0;
			String urlgeteligibility=appgateway.getAppgatewayabs()+"/PersonEligibility/getPersonEligibilityById/"+allCeilingLimits[i].getEligibilityid();
			ResponseEntity<EligibilityById[]> response2 = restTemplate.exchange(urlgeteligibility, HttpMethod.GET, request, EligibilityById[].class);
			if (response2.getStatusCode() == HttpStatus.OK) {
				eligibilityById=response2.getBody();
			}
			else {
				System.out.println("Response2 fail: "+response2.getStatusCode());
			}
			
			for(int j=0;j<eligibilityById.length;j++) {	
			//	System.out.println("CRITERIA FOR THE E_ID :: "+eligibilityById[j].getEligibilitycriterianame());
				flag=0;cnt=0;
				String urlpersondata=appgateway.getAppgateway()+"/AbsencePersonData/getPersonDataByGivenParameter/"+emplid+"/"+eligibilityById[j].getEligibilitycriterianame();
				ResponseEntity<PersonData> response3 = restTemplate.exchange(urlpersondata, HttpMethod.GET, request, PersonData.class);
				if (response2.getStatusCode() == HttpStatus.OK) {
					personData=response3.getBody();
				}
				else {
					System.out.println("Response3 fail: "+response3.getStatusCode());
				}
				if(!eligibilityById[j].getCriteriavalueid().contains(personData.getDescription())) {
					flag=1;cnt=0;
					break;
				}
				else {
					entitlementamt=allCeilingLimits[i].getReimbmaxceilinglimit();
					cnt++;flag=0;
				}
			}
			if(flag==0 && cnt==eligibilityById.length-1) {
				break;
			}
		}
		return entitlementamt;
	}
	

}
