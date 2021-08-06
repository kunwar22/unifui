package in.co.srdt.unif.controllers;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.*;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.search.JobSearchResult;
import in.co.srdt.unif.utils.ApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/uniftools")
public class PermissionRoleController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    @Autowired
    private ApplicationGateway appgateway;

    @RequestMapping("/security/permissionsroles/managepermissions")
    public String getPermissions(Model model) {
        model.addAttribute("status", Status.values());
        return "fragments/searchPermission :: PermissionSearch";
    }


    @RequestMapping("/security/permissionsroles/createpermissions")
    public String setPermissions(@ModelAttribute("permission") CreatePermissionWrapper permission, Model model) {

        model.addAttribute("permission", new CreatePermissionWrapper());
        return "fragments/createPermission :: PermissionCreate";
    }

//    @PostMapping("/security/permissionsroles/savePermission")
//    public String savePermission(@ModelAttribute("permission") CreatePermissionWrapper permission, BindingResult bindingResult,
//                                 Model model) {
//
//        String urlstr = appgateway.getAppgatewaynbr() + "/api/permission/createpermission";
//        SingleResponseModel res = new SingleResponseModel();
//        HttpEntity<List<CreatePermission>> request2 = new HttpEntity<List<CreatePermission>>(permission.getCreatepermission(), headers);
//
//        ResponseEntity<SingleResponseModel> responsesave = restTemplate.exchange(urlstr, HttpMethod.POST, request2, SingleResponseModel.class);
//        if (responsesave.getStatusCode() == HttpStatus.OK) {
//            res = responsesave.getBody();
//        } else {
//            System.out.println("Request Failed");
//
//        }
//        model.addAttribute("response", res);
//        return "fragments/createPermission :: PermissionCreate";
//
//    }

    @ResponseBody
    @RequestMapping(value = "/security/permissionsroles/savePermission", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String, String> savePermission(@ModelAttribute("permission") CreatePermissionWrapper permission, BindingResult bindingResult, Model model, HttpServletRequest req) {

        Login login = (Login) req.getSession().getAttribute("login");
        for(int i=0;i<permission.getCreatepermission().size();i++) {
            permission.getCreatepermission().get(i).setCreatedby(login.getLoginid());
        }
        Map<String, String> res = new HashMap<>();
        String successMessage = "Success";
        String errorMessage = "Error";
        String urlstr = appgateway.getAppgatewaynbr() + "/api/permission/createpermission";
        HttpEntity<List<CreatePermission>> request = new HttpEntity<List<CreatePermission>>(permission.getCreatepermission(), headers);
        ResponseEntity<String> response = restTemplate.exchange(urlstr, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("message", successMessage);

        } else {
            res.put("errorMessage", errorMessage);
        }

        // getEmployeeDetails(personnumber)
        return res;
    }


    @RequestMapping("/security/permissionsroles/manageroles")
    public String getRoles(Model model) {
        model.addAttribute("status", Status.values());
        return "fragments/searchRole :: RoleSearch";
    }

    @RequestMapping("/security/permissionsroles/createroles")
    public String setRoles(@ModelAttribute("permission") CreateRoleWrapper Role, Model model) {

        model.addAttribute("role", new CreateRoleWrapper());
        return "fragments/createRole :: RoleCreate";
    }


    //    @PostMapping("/security/permissionsroles/saveRole")
//    public String saveRole(@ModelAttribute("role") CreateRoleWrapper role, Model model) {
//
//        System.out.println("Request role  " + role.getCreaterole().toString());
//
//        String urlstr = appgateway.getAppgatewaynbr() + "/api/role/createrole";
//        //role.getCreaterole().get(0).setCreatedby("Ravi");
//        SingleResponseModel res = new SingleResponseModel();
//        HttpEntity<List<CreateRole>> request2 = new HttpEntity<List<CreateRole>>(role.getCreaterole(), headers);
//
//        ResponseEntity<SingleResponseModel> responsesave = restTemplate.exchange(urlstr, HttpMethod.POST, request2, SingleResponseModel.class);
//        if (responsesave.getStatusCode() == HttpStatus.OK) {
//            res = responsesave.getBody();
//        } else {
//            System.out.println("Request Failed");
//
//        }
//
//        model.addAttribute("response", res);
//        return "fragments/createRole :: RoleCreate";
//
//    }
    @ResponseBody
    @RequestMapping(value = "/security/permissionsroles/saveRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String, String> saveRole(@ModelAttribute("role") CreateRoleWrapper role, Model model, HttpServletRequest req) {

        Login login = (Login) req.getSession().getAttribute("login");
        for(int i=0;i<role.getCreaterole().size();i++) {
            role.getCreaterole().get(i).setCreatedby(login.getLoginid());
        }

        Map<String, String> res = new HashMap<>();
        String successMessage = "Success";
        String errorMessage = "Error";
        String urlstr = appgateway.getAppgatewaynbr() + "/api/role/createrole";
        HttpEntity<List<CreateRole>> request = new HttpEntity<List<CreateRole>>(role.getCreaterole(), headers);
        ResponseEntity<String> response = restTemplate.exchange(urlstr, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("message", successMessage);

        } else {
            res.put("errorMessage", errorMessage);
        }

        // getEmployeeDetails(personnumber)
        return res;
    }


    /***********************added by rajat start here on 14-12-2020***************************************/


    @ResponseBody
    @RequestMapping(value = "/security/permissionsroles/getPermissionList")
    public SearchPermission[] permissionSearch(HttpServletRequest requestFromDT) {

        SearchPermission[] searchPermission = null;
        String urlSearchPermission = appgateway.getAppgatewaynbr() + "api/permission/getpermissions";

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<SearchPermission[]> response = restTemplate.exchange(urlSearchPermission, HttpMethod.GET, request,
                SearchPermission[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            searchPermission = response.getBody();

        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return searchPermission;
    }


    @GetMapping("/removechild/{index}/{flg}")
    public @ResponseBody
    String removeChild(@SessionAttribute("CreatePermissionWrapper") CreatePermissionWrapper createPermissionWrapper,
                       @SessionAttribute("createRoleWrapper") CreateRoleWrapper createRoleWrapper, @PathVariable int index, @PathVariable String flg) {

        //System.out.println("flg : " + flg);
        if (flg.equals("permision")) {
            createPermissionWrapper.getCreatepermission().remove(index);
        }
        if (flg.equals("role")) {
            createRoleWrapper.getCreaterole().remove(index);
        }

        return "removed";
    }


    @ResponseBody
    @RequestMapping(value = "/security/permissionsroles/getRolesList")
    public RoleSearch[] rolesSearch(HttpServletRequest requestFromDT) {

        String roleName = requestFromDT.getParameter("roleName");
        System.out.println("roleName::" + roleName);
        RoleSearch[] searchRoles = null;

        if (roleName == null) {
            roleName = "";
        } else if (roleName == "null") {
            roleName = "";
        } else {
            roleName = roleName;
        }

        String urlRoles = appgateway.getAppgatewaynbr() + "/api/role/search";
        String payLode = "{" +
                "\"rolename\"" + ":\"" + roleName + "\"" +
                "}";
        System.out.println("payLode" + payLode);
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.add("Authorization", AppConstants.ACCESS_TOKEN);
        HttpEntity<String> request = new HttpEntity<String>(payLode, headers);

        ResponseEntity<RoleSearch[]> response = restTemplate.exchange(urlRoles, HttpMethod.POST, request, RoleSearch[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            searchRoles = response.getBody();

        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
        return searchRoles;
    }


    /***********************added by rajat end here on 14-12-2020***************************************/
}
