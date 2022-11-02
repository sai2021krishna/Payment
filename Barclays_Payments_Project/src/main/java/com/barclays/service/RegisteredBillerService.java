package com.barclays.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.barclays.entity.RegisteredBillers;
import com.barclays.exception.PaymentsException;

public interface RegisteredBillerService {

	ResponseEntity<List<RegisteredBillers>> getAllBillers() throws PaymentsException;

	ResponseEntity<List<RegisteredBillers>> getBillers(Integer sequenceId) throws PaymentsException;

	ResponseEntity<String> registerBiller(Integer SequenceId, RegisteredBillers registerBillerEnt)
			throws PaymentsException;

	ResponseEntity<String> deleteBiller(Integer billerSequenceId) throws PaymentsException;
	
	

}
