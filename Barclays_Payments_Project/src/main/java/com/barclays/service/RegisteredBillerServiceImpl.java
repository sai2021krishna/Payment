package com.barclays.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barclays.entity.RegisteredBiller;
import com.barclays.exception.PaymentsException;
import com.barclays.repository.RegisteredBillerRepository;
import com.barclays.utility.LoggingAspect;

@Service(value="paymentService")
@Transactional
public class RegisteredBillerServiceImpl implements RegisteredBillerService {
	@Autowired
	private Environment environment;


	@Autowired
	private RegisteredBillerRepository registeredBillerRepository;
	
	public static final Log LOGGER = LogFactory.getLog(RegisteredBillerServiceImpl.class); //(RegisteredBillerServiceImpl.class);
	
	
	@Override
	public ResponseEntity<List<RegisteredBiller>> getAllBillers() throws PaymentsException {
	
		Iterable<RegisteredBiller> billers = registeredBillerRepository.findAll();
		List<RegisteredBiller> RegisteredBillerss = new ArrayList<>();
		billers.forEach(biller -> {
			RegisteredBiller rb = new RegisteredBiller();
			
			rb.setBillerCode(biller.getBillerCode());
			rb.setBillerSequenceId(biller.getBillerSequenceId());
			rb.setConsumerNumber(biller.getConsumerNumber());
			rb.setAccountNumber(biller.getAccountNumber());
			RegisteredBillerss.add(rb);
			
		});
		LOGGER.info("All billers Extracted");
		if (RegisteredBillerss.isEmpty())
			throw new PaymentsException("Service.BILLER_NOT_FOUND");
		
		return new ResponseEntity<>(RegisteredBillerss, HttpStatus.OK);
	}
	@Override
	public ResponseEntity<List<RegisteredBiller>> getBillers(Integer AccountNumber) throws PaymentsException {
	
		Iterable<RegisteredBiller> billers = registeredBillerRepository.findByAccountNumber(AccountNumber);
		List<RegisteredBiller> RegisteredBillerss = new ArrayList<>();
		
		billers.forEach(biller -> {
			RegisteredBiller rb = new RegisteredBiller();
			
			rb.setBillerCode(biller.getBillerCode());
			rb.setBillerSequenceId(biller.getBillerSequenceId());
			rb.setConsumerNumber(biller.getConsumerNumber());
			rb.setAccountNumber(biller.getAccountNumber());
			RegisteredBillerss.add(rb);
		});
		if (RegisteredBillerss.isEmpty())
			throw new PaymentsException("Service.BILLER_NOT_FOUND");
		return new ResponseEntity<>(RegisteredBillerss, HttpStatus.OK);
		
	}
	
	@Override
	public ResponseEntity<String> registerBiller(Integer AccountNumber,RegisteredBiller registerBillerEnt) throws PaymentsException {
		
		RegisteredBiller registerBiller = new RegisteredBiller();
		
		registerBiller.setBillerCode(registerBillerEnt.getBillerCode());
		registerBiller.setConsumerNumber(registerBillerEnt.getConsumerNumber());
		registerBiller.setAccountNumber(AccountNumber);
		
		
		RegisteredBiller biller2 = registeredBillerRepository.save(registerBiller);//PERSISTING IN DATABASE
		
		String successMessage = ""; //environment.getProperty("API.REGISTERED_BILLER");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}
	@Override
	public ResponseEntity<String> deleteBiller(Integer billerSequenceId) throws PaymentsException {
		
		Optional<RegisteredBiller> register= registeredBillerRepository.findById(billerSequenceId);
		register.orElseThrow(() -> new PaymentsException("Service.BILLER_NOT_FOUND"));
		registeredBillerRepository.deleteById(billerSequenceId);
		String successMessage = environment.getProperty("API.BILLER_DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}
}
