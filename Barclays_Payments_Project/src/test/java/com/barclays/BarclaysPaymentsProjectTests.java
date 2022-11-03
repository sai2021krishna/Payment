package com.barclays;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.barclays.entity.User;
import com.barclays.exception.PaymentsException;
import com.barclays.repository.UserRepository;
import com.barclays.service.UserService;

@SpringBootTest
class BarclaysPaymentsProjectTests {
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService = new UserService();
	
	@Test
	public void authenticateUserTestValidCredentials() throws PaymentsException {
		
		User User = new User();
		User.setLoginId(1000001);
		User.setPassword("123");
		ResponseEntity<String> resp=new ResponseEntity<>("User logged in Successfully with User id: User [ loginId= "+User.getLoginId().shortValue()+"password=" +User.getPassword()+"]", HttpStatus.OK);
		Mockito.when(userService.loginUser(User)).thenReturn(resp);
		//("User added Successfully with User id: User [ loginId= "+User.getLoginId().shortValue()+"password=" +User.getPassword()+"]")
		ResponseEntity<String> actual = userService.loginUser(User);
		Assertions.assertEquals("User logged in Successfully with User id: User [ loginId= "+User.getLoginId().shortValue()+"password=" +User.getPassword()+"]", actual);
	} 
	@Test
	public void authenticateUserTestInValidCredentials() throws PaymentsException {
		User User = new User();
		User.setLoginId(1000001);
		User.setPassword("1");
	    PaymentsException exception=Assertions.assertThrows(PaymentsException.class,()->userService.loginUser(User));
		Assertions.assertEquals("Service.INCORRECT", exception.getMessage());
	}
}

