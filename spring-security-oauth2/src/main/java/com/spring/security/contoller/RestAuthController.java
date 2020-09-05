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
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/getData/{phone}/{authToken}")
	public ApiResponse findData(@PathVariable String phone,@PathVariable String authToken) throws IOException {
		final String apiURL = "https://fusion.preprod.zeta.in/api/v1/ifi/140793/individualByVector/p/"+phone;
		authToken = "Bearer"+authToken;
		String response = GetApiResponse.getAuthResponseString(apiURL, authToken);
		JSONObject jsonResponse = new JSONObject(response);
		if("ENABLED".equals(jsonResponse.getString("status"))) {
			userService.saveResponse(jsonResponse.toString());
			log.info("Api Success Response: "+jsonResponse.toString());
			return new ApiResponse(HttpStatus.OK, "Success", jsonResponse);
		}else {
			log.info("Api Error Response: "+jsonResponse.toString());
			return new ApiResponse(HttpStatus.OK,"",jsonResponse);
		}
		
	}
}
