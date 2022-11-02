package com.barclays.api;
//IS THIS WORKING

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.dto.UserDTO;
import com.barclays.exception.PaymentsException;
import com.barclays.service.UserService;

@RestController
@RequestMapping(value = "/payments")
@Validated
public class UserAPI {

	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;

//	@GetMapping(value = "/users")
//	public ResponseEntity<List<UserDTO>> getAllusers() throws PaymentsException {
//		List<UserDTO> userList = userService.getAllUsers();
//		return new ResponseEntity<>(userList, HttpStatus.OK);
//	}

//	@GetMapping(value = "/users/{loginId}")
//	public ResponseEntity<UserDTO> getuserDetails(@PathVariable @Min(value = 1000, message = "{user.loginid.invalid}") @Max(value = 10000, message = "{user.loginid.invalid}") Integer loginId)  throws PaymentsException{  
//	//public ResponseEntity<user> getuserDetails(@PathVariable Integer userId) throws Exception {
//		UserDTO user = userService.getUser(loginId);
//		return new ResponseEntity<>(user, HttpStatus.OK);
//	}

//	@PostMapping(value = "/users")
//	public ResponseEntity<String> adduser(@Valid @RequestBody UserDTO user) throws PaymentsException {
//		Integer loginId = userService.addUser(user);
//		String successMessage = environment.getProperty("API.INSERT_SUCCESS") + loginId;
//		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
//	}

	@PostMapping(value = "/login")
	public ResponseEntity<String> loginUser(@Valid @RequestBody UserDTO user) throws PaymentsException {
		return userService.loginUser(user);
		
	}

//	@PutMapping(value = "/users/{userId}")
//	public ResponseEntity<String> updateuser(@PathVariable Integer userId, @RequestBody UserDTO user)
//			throws PaymentsException {
//		userService.updateUser(userId, user.getEmailId());
//		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
//		return new ResponseEntity<>(successMessage, HttpStatus.OK);
//	}

//	@DeleteMapping(value = "/users/{loginId}")
//	public ResponseEntity<String> deleteuser(@PathVariable Integer loginId) throws PaymentsException {
//		userService.deleteUser(loginId);
//		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
//		return new ResponseEntity<>(successMessage, HttpStatus.OK);
//	}
	
	
	
	
	
	
	
	
	
	
	
}
