package in.co.srdt.unif.controllers.video;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

//import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import in.co.srdt.unif.model.video.HelpVideos;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.login.Login;
import in.co.srdt.unif.model.mss.ApprovalActions;
import in.co.srdt.unif.utils.ApplicationGateway;



@Controller
@RequestMapping("/videoPlaylist")
public class VideoController {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private ApplicationGateway appgateway;


	
	@RequestMapping("/manageVideoPlaylist")
	public String manageapprovalactions(Model model) {

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		HelpVideos[] helpvid=null;
		String urlhelpvideos= appgateway.getAppgatewaypyrl_sandhya()+"/api/common/getAllHelpPath";
		ResponseEntity<HelpVideos[]> response = restTemplate.exchange(urlhelpvideos, HttpMethod.GET, request, HelpVideos[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			helpvid = response.getBody();
		}
		model.addAttribute("help",helpvid);
		return "forms/videoPlaylist/videoPlaylist :: videoPlaylist";
	}

	 

}
