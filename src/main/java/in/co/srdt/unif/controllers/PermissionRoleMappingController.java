package in.co.srdt.unif.controllers;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.*;
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
import java.util.*;

@Controller
@RequestMapping("/uniftools")
public class PermissionRoleMappingController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;
    @Autowired
    private ApplicationGateway appgateway;


    @RequestMapping("/security/permissionsrolesmapping/managepermissionsrolesmapping")
    public String getPermissionsRoleMapping(Model model) {
        model.addAttribute("status", Status.values());
        return "fragments/searchPermissionRolemapping :: PermissionRoleMappingSearch";
    }

    @RequestMapping("/security/permissionsrolesmapping/createpermissionsrolesmapping")
    public String setPermissionsRoleMapping(@ModelAttribute("permissionmapping") CreatePermissionWrapper permission, Model model, HttpServletRequest requestFromDT) {

        String url = appgateway.getAppgatewaynbr() + "/api/permission/getpermissions";
        PermissionSearch[] permObj = null;
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<PermissionSearch[]> response = restTemplate.exchange(url, HttpMethod.GET, request,
                PermissionSearch[].class);


        if (response.getStatusCode() == HttpStatus.OK) {
            permObj = response.getBody();
        }


        model.addAttribute("permissionlist", permObj);
        String roleurl = appgateway.getAppgatewaynbr() + "/api/role/getroles";
        RoleSearch[] roleObj = null;

        HttpEntity<String> rolerequest = new HttpEntity<String>(headers);

        ResponseEntity<RoleSearch[]> roleresponse = restTemplate.exchange(roleurl, HttpMethod.GET, request,
                RoleSearch[].class);


        if (roleresponse.getStatusCode() == HttpStatus.OK) {
            roleObj = roleresponse.getBody();
        }
        model.addAttribute("rolelist", roleObj);

        return "fragments/PermissionRoleMapping :: PermissionRoleMappingCreate";
    }

//    @PostMapping("/security/permissionsrolesmapping/savePermissionrolesmapping")
//    public String savePermissionRolesMapping(@ModelAttribute("permissionmapping") PermissionRoleMapping permissionmapping, BindingResult bindingResult,
//                                             Model model) {
//
//        String urlstr=appgateway.getAppgatewaynbr()+"/api/rolepermission/savemapping";
//        //permission.getCreatepermission().get(0).setCreatedby("Ravi");
//        SingleResponseModel res = new SingleResponseModel();
//        HttpEntity<PermissionRoleMapping> request2 = new HttpEntity<PermissionRoleMapping>(permissionmapping, headers);
//
//        ResponseEntity<SingleResponseModel> responsesave= restTemplate.exchange(urlstr,HttpMethod.POST,request2, SingleResponseModel.class);
//        if(responsesave.getStatusCode() == HttpStatus.OK) {
//            res = responsesave.getBody();
//        }
//        else {
//            System.out.println("Request Failed");
//        }
//
//        model.addAttribute("response",res);
//        return "fragments/PermissionRoleMapping :: PermissionCreate";
//
//    }


    @ResponseBody
    @RequestMapping(value = "/security/permissionsrolesmapping/savePermissionrolesmapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String, String> savePermissionRolesMapping(@ModelAttribute("permissionmapping") PermissionRoleMapping permissionmapping, Long[] permissionid, BindingResult bindingResult, Model model, HttpServletRequest req) {
        ArrayList<Long> permisionlist = new ArrayList<Long>(Arrays.asList(permissionid));
        Login login = (Login) req.getSession().getAttribute("login");
        permissionmapping.setCreatedby(login.getLoginid());

        Map<String, String> res = new HashMap<>();
        String successMessage = "Success";
        String errorMessage = "Error";
        String urlstr = appgateway.getAppgatewaynbr() + "/api/rolepermission/savemapping";

        HttpEntity<PermissionRoleMapping> request = new HttpEntity<PermissionRoleMapping>(permissionmapping, headers);
        ResponseEntity<String> response = restTemplate.exchange(urlstr, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("message", successMessage);

        } else {
            res.put("errorMessage", errorMessage);
        }

        // getEmployeeDetails(personnumber)
        return res;
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


}
