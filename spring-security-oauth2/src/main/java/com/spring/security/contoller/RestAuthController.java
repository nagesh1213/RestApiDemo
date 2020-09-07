package com.spring.security.contoller;

import java.io.IOException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.model.User;
import com.spring.security.service.UserService;
import com.spring.security.utility.ApiResponse;
import com.spring.security.utility.GetApiResponse;

@RestController
@RequestMapping("/api")
public class RestAuthController {
	
	private static final Logger log = LoggerFactory.getLogger(RestAuthController.class);


	@Autowired
	private UserService userService;
	
	
	@PostMapping("/addUser")
	public ApiResponse addNewUser(@RequestBody User userDetails) throws Exception {
		if (userService.findByUsername(userDetails.getUsername()) != null) {
			return new ApiResponse(HttpStatus.BAD_REQUEST, "User already Exist...!",
					userService.findByUsername(userDetails.getUsername()));
		} else {
			userService.createUser(userDetails);
			return new ApiResponse(HttpStatus.OK, "User Saved...!", userService.findAll());
		}

	}

	@GetMapping("/getData/{phone}")
	public ApiResponse findData(@PathVariable String phone) throws IOException {
		final String apiURL = "https://fusion.preprod.zeta.in/api/v1/ifi/140793/individualByVector/p/"+phone;
		String authToken = "eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwidGFnIjoieFlBS29XcFo2UTNhZjBxaDN6ZXhiQSIsImFsZyI6IkExMjhHQ01LVyIsIml2IjoiUXpoX3JMZXFOYVRTNUxRMiJ9.fFBFSITles3NP-W_PBrXFnPB6DdXDiVVNu_kGNHWmsQ.d3jjANTP44cjLrvmZKKU1Q.XDm-zKc3GmE5ZRn3yS9nuC8DVkIZESrj29jcu352dahvM8zAP3R0E07XETHuEtqgu_6wdmRaIs22sI8JSx7PNfoOs9a2UHOI0arvVeb26hSxhquGlVAC8dfD3Ifqn4u0MyToh6JkzIlQgK7de8qc96ieVXQuTFGaxwlVoTLujLvlPowR0tIkDCciHSu9c5la9Q92zOt5BHVEzw_VlrCSXIkiQBxL3pgNQSDt9JfuR-MQa0vK1_qOZ4ozfogs2lIbOyb5z_FdAFQU_kiYvYgDZomkT4QJJ2RTBr9IJDY0U7w_FVvnINsmpyBPQVDvQbeU_W2cMFk7B_7mnjPqpOX4yhJ1fXhZ9IyoGV0vRznET9A.Feajrju7oElabSVVvTXlrA";
		String response = GetApiResponse.getAuthResponseString(apiURL, authToken);
		JSONObject jsonResponse = new JSONObject(response);
		if("ENABLED".equals(jsonResponse.getString("status"))) {
			userService.saveResponse(jsonResponse.toString());
			log.info("Api Success Response: "+jsonResponse.toString());
			return new ApiResponse(HttpStatus.OK, "Success", jsonResponse.toString());
		}else {
			log.info("Api Error Response: "+jsonResponse.toString());
			return new ApiResponse(HttpStatus.OK,"",jsonResponse);
		}
		
	}
}
