package com.barclays.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.barclays.entity.RegisteredBiller;
import com.barclays.exception.PaymentsException;

public interface RegisteredBillerService {

	ResponseEntity<List<RegisteredBiller>> getAllBillers() throws PaymentsException;

	ResponseEntity<List<RegisteredBiller>> getBillers(Integer sequenceId) throws PaymentsException;

	ResponseEntity<String> registerBiller(Integer SequenceId, RegisteredBiller registerBillerEnt)
			throws PaymentsException;

	ResponseEntity<String> deleteBiller(Integer billerSequenceId) throws PaymentsException;
	
	

}
