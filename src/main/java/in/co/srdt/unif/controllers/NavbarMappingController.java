package in.co.srdt.unif.controllers;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.NavbarMapping;
import in.co.srdt.unif.model.ParentNavbarSearch;
import in.co.srdt.unif.model.PermissionSearch;
import in.co.srdt.unif.model.RoleSearch;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/uniftools")
public class NavbarMappingController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;
    @Autowired
    private ApplicationGateway appgateway;


    @RequestMapping("/security/navbarmapping/navbarmapping")
    public String getNavbarMapping(Model model) {
        model.addAttribute("status", Status.values());
        return "fragments/searchNavbarMap:: NavbarMapSearch";
    }

    @RequestMapping("/security/navbarmapping/createnavbarmapping")
    public String setPermissionsRoleMapping(Model model, HttpServletRequest requestFromDT) {

        String url = appgateway.getAppgatewaynbr() + "/api/permission/getpermissions";
        PermissionSearch[] permObj = null;
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<PermissionSearch[]> response = restTemplate.exchange(url, HttpMethod.GET, request,
                PermissionSearch[].class);

        String navurl = appgateway.getAppgatewaynbr() + "/api/navbar/getallnodes";
        ParentNavbarSearch[] navObj = null;

        String roleurl = appgateway.getAppgatewaynbr() + "/api/role/getroles";
        RoleSearch[] roleObj = null;

        HttpEntity<String> rolerequest = new HttpEntity<String>(headers);
        ResponseEntity<ParentNavbarSearch[]> roleresponse = restTemplate.exchange(navurl, HttpMethod.GET, request,
                ParentNavbarSearch[].class);

        HttpEntity<String> rolerequest1 = new HttpEntity<String>(headers);
        ResponseEntity<RoleSearch[]> roleresponse1 = restTemplate.exchange(roleurl, HttpMethod.GET, request,
                RoleSearch[].class);

        if (roleresponse.getStatusCode() == HttpStatus.OK) {
            navObj = roleresponse.getBody();
        }
        if (roleresponse1.getStatusCode() == HttpStatus.OK) {
            roleObj = roleresponse1.getBody();
        }

        if (response.getStatusCode() == HttpStatus.OK) {
            permObj = response.getBody();
        }
        model.addAttribute("permObj", permObj);
        model.addAttribute("rolelist", roleObj);
        model.addAttribute("navbarlist", navObj);
        return "fragments/NavbarMapping :: NavbarMappingCreate";
    }

    @PostMapping("/security/navbarmapping/navbarMapping")
    public String saveNavbarMapping(@ModelAttribute("navbarmapping") NavbarMapping navbarmapping, BindingResult bindingResult, Model model) {

        String urlstr = appgateway.getAppgatewaynbr() + "/api/rolepermission/savemapping";
        SingleResponseModel res = new SingleResponseModel();
        HttpEntity<NavbarMapping> request2 = new HttpEntity<NavbarMapping>(navbarmapping, headers);

        ResponseEntity<SingleResponseModel> responsesave = restTemplate.exchange(urlstr, HttpMethod.POST, request2, SingleResponseModel.class);
        if (responsesave.getStatusCode() == HttpStatus.OK) {
            res = responsesave.getBody();
        } else {
            System.out.println("Request Failed");
        }

        model.addAttribute("response", res);
        return "fragments/PermissionRoleMapping :: PermissionCreate";

    }

    /*@RequestMapping("/security/permissionsroles/manageroles")
    public String getRoles(Model model) {
        model.addAttribute("status", Status.values());
        return "fragments/searchRole :: RoleSearch";
    }

    @RequestMapping("/security/permissionsroles/createroles")
    public String setRoles(@ModelAttribute("permission") CreateRoleWrapper Role,Model model) {

        model.addAttribute("role", new CreateRoleWrapper());
        return "fragments/createRole :: RoleCreate";
    }*/


   /* @PostMapping("/security/permissionsroles/saveRole")
    public String saveRole(@ModelAttribute("role") CreateRoleWrapper role, Model model)
    {

        System.out.println("Request role  "+role.getCreaterole().toString());

        String urlstr="http://192.200.12.83:9190/api/role/createrole";
        //role.getCreaterole().get(0).setCreatedby("Ravi");
        SingleResponseModel res = new SingleResponseModel();
        HttpEntity<List<CreateRole>> request2 = new HttpEntity<List<CreateRole>>(role.getCreaterole(), headers);

        ResponseEntity<SingleResponseModel> responsesave= restTemplate.exchange(urlstr,HttpMethod.POST,request2, SingleResponseModel.class);
        if(responsesave.getStatusCode() == HttpStatus.OK) {
            res = responsesave.getBody();
        } else {
            System.out.println("Request Failed");

        }

        model.addAttribute("response",res);
        return "fragments/createRole :: RoleCreate";

    }*/


    //*****************************added by rajat on 16-12-20/////
    @ResponseBody
    @RequestMapping(value = "/security/navbarmapping/navbarMapping/saveMapNavbar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String, String> saveMapNavbar(@ModelAttribute("navbarMapping") NavbarMapping navbarMapping, Long[] navbarid, BindingResult bindingResult, Model model, HttpServletRequest req) {

        ArrayList<Long> navbarlist = new ArrayList<Long>(Arrays.asList(navbarid));
        Login login = (Login) req.getSession().getAttribute("login");

        navbarMapping.setCreatedby(login.getLoginid());

        Map<String, String> res = new HashMap<>();
        String successMessage = "Success";
        String errorMessage = "Error";
        String urlstr = appgateway.getAppgatewaynbr() + "/api/navbarmapping/mapnavbar";

        navbarMapping.setNavbarid(navbarlist);
        navbarMapping.setLoginid(login.getEmplid());


        HttpEntity<NavbarMapping> request = new HttpEntity<NavbarMapping>(navbarMapping, headers);
        ResponseEntity<String> response = restTemplate.exchange(urlstr, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("message", successMessage);

        } else {
            res.put("errorMessage", errorMessage);
        }

        // getEmployeeDetails(personnumber)
        return res;
    }

    ///////////////////////////////////////////////////

}
