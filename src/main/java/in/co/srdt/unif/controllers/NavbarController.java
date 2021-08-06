package in.co.srdt.unif.controllers;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import in.co.srdt.unif.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.enums.Status;
import in.co.srdt.unif.model.create.SingleResponseModel;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.login.Role;
import in.co.srdt.unif.utils.AppConstants;
import in.co.srdt.unif.utils.ApplicationGateway;
import in.co.srdt.unif.utils.UnifTheme;

@Controller
public class NavbarController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UnifTheme unifTheme;

    @Autowired
    private HttpHeaders headers;

    @Autowired
    private ApplicationGateway appGateway;

    //private String token = "Bearer 87af7f9b-c638-47a8-bdb8-2f425349af87";
    Login login = null;
    String role = null;

    @RequestMapping("/home")
    public String homePage(HttpSession session, HttpServletRequest request, Model model) {

        login = (Login) request.getSession().getAttribute("login");
        role = (String) request.getSession().getAttribute("roles");

        if (login == null) {
            return "redirect:/login";
        }

        String url = appGateway.getAppgatewaynbr() + "/api/navbar/getrootsbyloginid/" + login.getEmplid();
        model.addAttribute("userloginid", login.getLoginid());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", AppConstants.ACCESS_TOKEN);
        HttpEntity<String> request1 = new HttpEntity<String>(headers);

        ResponseEntity<NavBar[]> response = restTemplate.exchange(url, HttpMethod.GET, request1, NavBar[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("navBar", response.getBody());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

        final String themeColor = unifTheme.getAppTheme();
        final String theme = "/css/w3-theme-" + unifTheme.getAppTheme() + ".css";
        final String hoverColor = "w3-border-bottom w3-bar-item w3-button w3-hover-" + unifTheme.getAppTheme() + " icon-change";
        final String hoverColorFN = "w3-hover-" + unifTheme.getAppTheme();

        model.addAttribute("theme", theme);
        model.addAttribute("hoverColor", hoverColor);
        model.addAttribute("hoverColorFN", hoverColorFN);
        model.addAttribute("themeColor", themeColor);
        model.addAttribute("component", "admin");

        //System.out.println(navBar[0].getRequestaddr());

        ///////****Added by Utsav to resolve white label error on launching home****\\\\\\\\
        model.addAttribute("rowcount", new SingleResponseModel());

        return "home";
    }

    @RequestMapping("/selfservice")
    public String selfservicePage(HttpSession session, HttpServletRequest request, Model model) {
        login = (Login) request.getSession().getAttribute("login");
        role = (String) request.getSession().getAttribute("roles");
        //System.out.println(role);
        //System.out.println(role.contains("HP_SelfService"));
        SingleResponseModel rowcount = new SingleResponseModel();
        if (login == null) {
            return "redirect:/login";
        } else if (login != null && role.contains("HP_SelfService")) {
            final String themeColor = unifTheme.getAppTheme();
            final String theme = "/css/w3-theme-" + unifTheme.getAppTheme() + ".css";
            final String hoverColor = "w3-border-bottom w3-bar-item w3-button w3-hover-" + unifTheme.getAppTheme() + " icon-change";
            final String hoverColorFN = "w3-hover-" + unifTheme.getAppTheme();


            /////***********ADDED BY RAVI FOR NOTIFICATION ****************\\\\\

            String url = appGateway.getAppgateway() + "/Notification/getAllUnreadNotificationByApproverNo/" + login.getEmplid();


            headers.setContentType(MediaType.APPLICATION_JSON);
            //headers.add("Authorization", AppConstants.ACCESS_TOKEN);
            HttpEntity<NotificationModel[]> request1Noti = new HttpEntity<NotificationModel[]>(headers);
            NotificationModel[] notify = null;
            List<NotificationModel> notifications = new ArrayList<>();
            ResponseEntity<NotificationModel[]> responseNotify = restTemplate.exchange(url, HttpMethod.GET, request1Noti, NotificationModel[].class);

            if (responseNotify.getStatusCode() == HttpStatus.OK) {
                notify = responseNotify.getBody();
                //System.out.println("Notify"+notify.length);
                if (notify == null || notify.length == 0) {

                    notify = new NotificationModel[0];
                }

                //notifications.add(Arrays.);
            } else {
                System.out.println("Request Failed");
                System.out.println(responseNotify.getStatusCode());
            }
            for (int i = 0; i < notify.length; i++) {
                //System.out.println("Module ID :: "+notify[i].toString());
            }
            model.addAttribute("NotifynavBar", notify);
            rowcount.setMessage(notify.length + "");
            model.addAttribute("rowcount", rowcount);

            /////*************************************************************\\\\\

            /****ADDED BY UTSAV FOR SIDEBAR DISPLAY ACCORDING TO ROLES****/

            List<Role> roles = (List<Role>) request.getSession().getAttribute("allroles");
            /*for (int z=0;z<roles.size();z++){
                System.out.println(roles.get(z).getRolename());
            }*/
            boolean REM_TELEPHONE = false;
            boolean REM_TADK = false;
            boolean REM_TRANSPORT = false;
            boolean REM_CHILD = false;
            boolean REM_ELECTRI = false;
            boolean REM_ENTERTAIN = false;
            boolean REM_CDA = false;
            boolean TAX_DECL = false;
            boolean MY_TEAM = false;
            boolean TAX_APPR = false;
            boolean GEN_TMPL = false; //2545 //2544
            boolean GEN_REM_VAL = false; //2543 //2542
            boolean GEN_REM_APPR1 = false; //2544 //2543
            boolean GEN_REM_APPR2 = false;
            boolean VIEW_INVOICE = false; //2546 //2545
            boolean MSS = false;
            
            boolean ROLE_MANAGE_VENDOR = false;
            boolean ROLE_PAYROL_INEG = false;
            
            boolean SUPER_USER = false;
            


            for (int i = 0; i <= roles.size() - 1; i++) {

                if (roles.get(i).getRoleId() == 100) {
                    REM_TELEPHONE = true;
                }
                if (roles.get(i).getRoleId() == 103) {
                    REM_TADK = true;
                }
                if (roles.get(i).getRoleId() == 107) {
                    REM_TRANSPORT = true;
                }
                if (roles.get(i).getRoleId() == 108) {
                    REM_CHILD = true;
                }
                if (roles.get(i).getRoleId() == 109) {
                    REM_ELECTRI = true;
                }
                if (roles.get(i).getRoleId() == 110) {
                    REM_ENTERTAIN = true;
                }
                if (roles.get(i).getRoleId() == 112) {
                    REM_CDA = true;
                }
                if (roles.get(i).getRoleId() == 2402) {
                    TAX_DECL = true;
                }
                if (roles.get(i).getRoleId() == 2403) {
                    MY_TEAM = true;
                }
                if (roles.get(i).getRoleId() == 2404) {
                    TAX_APPR = true;
                }
                if (roles.get(i).getRoleId() == 2544) {
                    GEN_TMPL = true;
                }
                if (roles.get(i).getRoleId() == 2) {
                	SUPER_USER = true;
                }
                if (roles.get(i).getRolename().equals("GEN_INV_HR_ROLE")) {
                    GEN_REM_VAL = true;
                }
                if (roles.get(i).getRolename().equals("GEN_INV_FIN_ROLE")) {
                    GEN_REM_APPR1 = true;
                }
                if (roles.get(i).getRolename().equals("GEN_INV_FIN_ROLE_2")) {
                    GEN_REM_APPR2 = true;
                }if (roles.get(i).getRolename().equals("ROLE_APPROVAL_ACTION")) {
                    MSS = true;
                }
                if (roles.get(i).getRoleId() == 2545) {
                    VIEW_INVOICE = true;
                }
                if (roles.get(i).getRoleId() == 2579) {
                	ROLE_MANAGE_VENDOR = true;
                }
                if (roles.get(i).getRoleId() == 2588) {
                	ROLE_PAYROL_INEG = true;
                }
            }
            model.addAttribute("MSS",MSS);
            model.addAttribute("GEN_TMPL", GEN_TMPL);
            model.addAttribute("GEN_REM_VAL", GEN_REM_VAL);
            model.addAttribute("GEN_REM_APPR1", GEN_REM_APPR1);
            model.addAttribute("GEN_REM_APPR2", GEN_REM_APPR2);
            model.addAttribute("VIEW_INVOICE", VIEW_INVOICE);
            model.addAttribute("TAX_APPR", TAX_APPR);
            model.addAttribute("MY_TEAM", MY_TEAM);
            model.addAttribute("TAX_DECL", TAX_DECL);
            model.addAttribute("REM_TELEPHONE", REM_TELEPHONE);
            model.addAttribute("REM_TADK", REM_TADK);
            model.addAttribute("REM_TRANSPORT", REM_TRANSPORT);
            model.addAttribute("REM_CHILD", REM_CHILD);
            model.addAttribute("REM_ELECTRI", REM_ELECTRI);
            model.addAttribute("REM_ENTERTAIN", REM_ENTERTAIN);
            model.addAttribute("REM_CDA", REM_CDA);
            model.addAttribute("SUPER_USER", SUPER_USER);

            /*********************************************************/


            model.addAttribute("theme", theme);
            model.addAttribute("hoverColor", hoverColor);
            model.addAttribute("hoverColorFN", hoverColorFN);
            model.addAttribute("themeColor", themeColor);
            model.addAttribute("component", "selfservice");
            model.addAttribute("ROLE_MANAGE_VENDOR", ROLE_MANAGE_VENDOR);
            model.addAttribute("ROLE_PAYROL_INEG", ROLE_PAYROL_INEG);
            
            return "selfservice";
        } else {
            return "redirect:/login";
        }

    }


    @ResponseBody
    @RequestMapping("/getchild/{parentid}/{loginid}")
    public NavBar[] getChild(@PathVariable("parentid") long parentid, @PathVariable("loginid") String loginid) {
        NavBar[] navBar = null;
        String url = appGateway.getAppgatewaynbr() + "/api/navbar/getchildbyloginid/" + parentid + "/" + loginid;

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", AppConstants.ACCESS_TOKEN);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<NavBar[]> response = restTemplate.exchange(url, HttpMethod.GET, request, NavBar[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            navBar = response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

        return navBar;
    }

    @ResponseBody
    @RequestMapping("/getrootnav/{loginid}")
    public NavBar[] getRootNav(@PathVariable("loginid") String loginid) {
        NavBar[] navBar = null;
        String url = appGateway.getAppgatewaynbr() + "/api/navbar/getrootsbyloginid/" + loginid;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", AppConstants.ACCESS_TOKEN);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<NavBar[]> response = restTemplate.exchange(url, HttpMethod.GET, request, NavBar[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            navBar = response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

        return navBar;
    }

    @ResponseBody
    @RequestMapping("/getparentnav/{parentofid}/{loginid}")
    public NavBar[] getParentByLoginId(@PathVariable("parentofid") long parentofid, @PathVariable("loginid") String loginid) {

        NavBar[] navBar = null;

        String url = appGateway.getAppgatewaynbr() + "/api/navbar/getparentbyloginid/" + parentofid + "/" + loginid;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", AppConstants.ACCESS_TOKEN);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<NavBar[]> response = restTemplate.exchange(url, HttpMethod.GET, request, NavBar[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            navBar = response.getBody();
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

        return navBar;
    }

    //***********************////////////////Created by Rajat///////////////**********************//

    @RequestMapping("/uniftools/security/navbar/managenavbar")
    public String getNavbar(Model model) {
        model.addAttribute("status", Status.values());
        return "fragments/searchNavbar :: NavbarSearch";
    }

    @GetMapping("/uniftools/security/parentbind/{parentval}")
    public @ResponseBody
    SearchParentNavbar[] removeChild(@PathVariable String parentval, Model model) {
        System.out.println(parentval);
        String urlBind = "";
        if (parentval.equals("LEAF")) {
            urlBind = appGateway.getAppgatewaynbr() + "/api/navbar/getallparents";
        } else if (parentval.equals("ROOT")) {
            urlBind = appGateway.getAppgatewaynbr() + "/api/navbar/getroots";
        }

        SearchParentNavbar[] parentObj = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", AppConstants.ACCESS_TOKEN);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<SearchParentNavbar[]> response = restTemplate.exchange(urlBind, HttpMethod.GET, request,
                SearchParentNavbar[].class);


        if (response.getStatusCode() == HttpStatus.OK) {
            parentObj = response.getBody();
        }
        model.addAttribute("parentnavbarlist", parentObj);
        return parentObj;
    }

    @RequestMapping("/uniftools/security/navbar/createnavbar")
    public String setPermissions(@ModelAttribute("navbar") CreateNavbar navbar, Model model, HttpServletRequest req) {
        Login login = (Login) req.getSession().getAttribute("login");

        model.addAttribute("navbar", new CreateNavbar());
//			String urlParentsearch = "http://192.200.12.83:9190/api/navbar/getroots";
//			SearchParentNavbar[] parentObj = null;
        //
//			//headers.setContentType(MediaType.APPLICATION_JSON);
//			HttpEntity<String> request = new HttpEntity<String>(headers);
        //
//			ResponseEntity<SearchParentNavbar[]> response = restTemplate.exchange(urlParentsearch, HttpMethod.GET, request,
//					SearchParentNavbar[].class);
        //
        //
//			if (response.getStatusCode() == HttpStatus.OK) {
//				parentObj = response.getBody();
//			}
//			model.addAttribute("parentnavbarlist",parentObj);
        model.addAttribute("status", Status.values());
        //System.out.println(permObj[0].getPermissionid());
        model.addAttribute("login", login.getEmplid());
        return "fragments/createNavbar :: NavbarCreate";
    }

//		@PostMapping("/uniftools/security/navbar/saveNavbar")
//		public String savePermission(@ModelAttribute("createnavbar") CreateNavbarWrapper createnavbar, BindingResult bindingResult,
//									 Model model) {
//			//headers.setContentType(MediaType.APPLICATION_JSON);
//			//model.addAttribute("permission",permission);
//
//			System.out.println("Request nav"+createnavbar.toString());
//
//			String urlstr=appGateway.getAppgatewaynbr()+"/api/navbar/createnavbar";
//			//permission.getCreatepermission().get(0).setCreatedby("Ravi");
//			SingleResponseModel res = new SingleResponseModel();
//			HttpEntity<List<CreateNavbar>> request2 = new HttpEntity<List<CreateNavbar>>(createnavbar.getCreatenavbar(), headers);
//
//			ResponseEntity<SingleResponseModel> responsesave= restTemplate.exchange(urlstr,HttpMethod.POST,request2, SingleResponseModel.class);
//			if(responsesave.getStatusCode() == HttpStatus.OK) {
//				res = responsesave.getBody();
//			} else {
//				System.out.println("Request Failed");
//
//			}
//
//			model.addAttribute("response",res);
//			return "fragments/createPermission :: PermissionCreate";
//
//		}

    /***********************added by rjat on 15-12-2020 start************************************************/
    @ResponseBody
    @RequestMapping(value = "/uniftools/security/navbar/saveNavbar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String, String> savePermissionRolesMapping(@ModelAttribute("createnavbar") CreateNavbarWrapper createnavbar, BindingResult bindingResult, Model model, HttpServletRequest req) {

        Login login = (Login) req.getSession().getAttribute("login");
        for(int i=0;i<createnavbar.getCreatenavbar().size();i++) {
            createnavbar.getCreatenavbar().get(i).setCreatedby(login.getLoginid());
        }


        Map<String, String> res = new HashMap<>();
        String successMessage = "Success";
        String errorMessage = "Error";
        String urlstr = appGateway.getAppgatewaynbr() + "/api/navbar/createnavbar";

        HttpEntity<List<CreateNavbar>> request = new HttpEntity<List<CreateNavbar>>(createnavbar.getCreatenavbar(), headers);
        ResponseEntity<String> response = restTemplate.exchange(urlstr, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            res.put("message", successMessage);

        } else {
            res.put("errorMessage", errorMessage);
        }

        return res;
    }


    /***********************added by rajat on 15-12-2020 end************************************************/
}
