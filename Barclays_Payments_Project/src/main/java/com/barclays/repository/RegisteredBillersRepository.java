package com.barclays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.entity.RegisteredBillers;


public interface RegisteredBillersRepository extends JpaRepository<RegisteredBillers, Integer> {

	//List<RegisteredBillers> findBySequenceId(Integer SequenceId);//QUERY METHODS
	//SELECT ALLL THINGS FROM REGISTEREDTABLE WHERE SEQUENCEID FROM API = SEQUENCE ID IN TABLE

	Iterable<RegisteredBillers> findByAccountNumber(Integer accountNumber);
}
