package com.barclays;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.barclays.dto.UserDTO;
import com.barclays.entity.Bills;
import com.barclays.entity.User;
import com.barclays.exception.PaymentsException;
import com.barclays.repository.UserRespository;
import com.barclays.service.UserService;
import com.barclays.service.UserServiceImpl;

@SpringBootTest
class BarclaysPaymentsProjectTests {
	
	@Mock
	UserRespository userRepository;
	
	@InjectMocks
	UserService userService = new UserServiceImpl();
	
	@Test
	public void authenticateUserTestValidCredentials() throws PaymentsException {
		
		UserDTO User = new UserDTO();
		User.setLoginId(1000001);
		User.setPassword("123");
		ResponseEntity<String> actual = userService.loginUser(User);
		Assertions.assertEquals("User successfully logged in :1000001ASAccount_Holder", actual);
	} 
	@Test
	public void authenticateUserTestInValidCredentials() throws PaymentsException {
		UserDTO User = new UserDTO();
		User.setLoginId(1000001);
		User.setPassword("1");
		
	    PaymentsException exception=Assertions.assertThrows(PaymentsException.class,()->userService.loginUser(User));
		Assertions.assertEquals("Service.INCORRECT", exception.getMessage());
	}
}

