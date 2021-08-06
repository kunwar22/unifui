package in.co.srdt.unif.controllers.dbi;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

//import in.co.srdt.unif.config.AccessToken;
import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.dbi.DBICreate;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;

@Controller
@RequestMapping("/dbi")
public class DBIController {

    @Autowired
    private ApplicationGateway appgateway;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/managedbi")
    public String managedbi(Model model) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", AppConstants.ACCESS_TOKEN);
//        headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        return "fragments/dbi/managedbi :: managedbi";
    }

    @RequestMapping("/createdbi")
    public String createdbi(Model model) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", AppConstants.ACCESS_TOKEN);
//        headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        model.addAttribute("dbidata", new DBICreate());
        model.addAttribute("status", Status.values());
        return "fragments/dbi/createdbi :: createdbi";
    }

    @ResponseBody
    @RequestMapping(value = "/submitdbi", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String submitdbi(DBICreate dbiCreate, Authentication authentication) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", AppConstants.ACCESS_TOKEN);
//        headers.add("Authorization", AccessToken.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        //dbiCreate.setStatus("Active");
//        dbiCreate.setCreatedby(authentication.getName());
//        dbiCreate.setUpdatedby(authentication.getName());
        dbiCreate.setCreatedby(authentication.name());
        dbiCreate.setUpdatedby(authentication.name());
        System.out.println("DATA "+dbiCreate.toString());

        SingleResponseModel res = null;
        String url=appgateway.getAppgateway()+"/corehr/Dbi/saveDbiCreation";
        HttpEntity<DBICreate> request = new HttpEntity<DBICreate>(dbiCreate, headers);
        ResponseEntity<SingleResponseModel> response= restTemplate.exchange(url,HttpMethod.POST,request, SingleResponseModel.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            res = response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

        return res.getStatus();
    }

    @ResponseBody
    @PostMapping("/searchdbi")
    public DBICreate[] searchdbi(HttpServletRequest requestFromDT) {

        HttpHeaders headers=new HttpHeaders();
//        headers.add("Authorization", AccessToken.getAccessToken());
        headers.add("Authorization", AppConstants.ACCESS_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String stitle = requestFromDT.getParameter("title");

        String url = appgateway.getAppgateway()+"/corehr/Dbi/getAllDbiCreationByTitle";
        DBICreate[] dbiobj = null;
        String payload = "{" + "\"title\"" + ":\"" + stitle + "\"" + "}";
        System.out.println(payload);
        HttpEntity<String> request = new HttpEntity<String>(payload, headers);

        ResponseEntity<DBICreate[]> response = restTemplate.exchange(url, HttpMethod.POST, request, DBICreate[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            dbiobj = response.getBody();
        }
        for (DBICreate a: dbiobj) {
            System.out.println(a.toString());
        }

        return dbiobj;
    }

    @RequestMapping("/getDBIById/{dbiid}")
    public String getDBIById(@PathVariable("dbiid")String dbiid, Model model) {

        HttpHeaders headers=new HttpHeaders();
//        headers.add("Authorization", AccessToken.getAccessToken());
        headers.add("Authorization", AppConstants.ACCESS_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = appgateway.getAppgateway()+"/corehr/Dbi/getDbiCreationById/"+dbiid;
        DBICreate dbiobj = null;

        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<DBICreate> response = restTemplate.exchange(url, HttpMethod.GET, request, DBICreate.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            dbiobj = response.getBody();
        }
            System.out.println(dbiobj.toString());

        model.addAttribute("dbidata", dbiobj);
        model.addAttribute("status", Status.values());
        return "fragments/dbi/createdbi :: createdbi";
    }
}
