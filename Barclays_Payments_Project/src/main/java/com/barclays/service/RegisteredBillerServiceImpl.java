package com.barclays.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

//import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.barclays.entity.RegisteredBillers;
import com.barclays.exception.PaymentsException;
import com.barclays.repository.AccountTransactionRepository;
import com.barclays.repository.AccountsRepository;
import com.barclays.repository.BillsRepository;
import com.barclays.repository.RegisteredBillersRepository;

@Service(value="paymentService")
@Transactional
public class RegisteredBillerServiceImpl implements RegisteredBillerService {
	@Autowired
	private Environment environment;


	@Autowired
	private RegisteredBillersRepository registeredBillersRepository;
	
	
	@Override
	public ResponseEntity<List<RegisteredBillers>> getAllBillers() throws PaymentsException {
	
		Iterable<RegisteredBillers> billers = registeredBillersRepository.findAll();
		List<RegisteredBillers> RegisteredBillerss = new ArrayList<>();
		billers.forEach(biller -> {
			RegisteredBillers rb = new RegisteredBillers();
			
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
	public ResponseEntity<List<RegisteredBillers>> getBillers(Integer AccountNumber) throws PaymentsException {
	
		Iterable<RegisteredBillers> billers = registeredBillersRepository.findByAccountNumber(AccountNumber);
		List<RegisteredBillers> RegisteredBillerss = new ArrayList<>();
		
		billers.forEach(biller -> {
			RegisteredBillers rb = new RegisteredBillers();
			
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
	public ResponseEntity<String> registerBiller(Integer AccountNumber,RegisteredBillers registerBillerEnt) throws PaymentsException {
		
		RegisteredBillers registerBiller = new RegisteredBillers();
		
		registerBiller.setBillerCode(registerBillerEnt.getBillerCode());
		registerBiller.setConsumerNumber(registerBillerEnt.getConsumerNumber());
		registerBiller.setAccountNumber(AccountNumber);
		
		
		RegisteredBillers biller2 = registeredBillersRepository.save(registerBiller);//PERSISTING IN DATABASE
		
		String successMessage = ""; //environment.getProperty("API.REGISTERED_BILLER");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}
	@Override
	public ResponseEntity<String> deleteBiller(Integer billerSequenceId) throws PaymentsException {
		
		Optional<RegisteredBillers> register= registeredBillersRepository.findById(billerSequenceId);
		register.orElseThrow(() -> new PaymentsException("Service.BILLER_NOT_FOUND"));
		registeredBillersRepository.deleteById(billerSequenceId);
		String successMessage = environment.getProperty("API.BILLER_DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}
}
