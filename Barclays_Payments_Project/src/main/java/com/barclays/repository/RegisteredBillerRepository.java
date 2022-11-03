package com.barclays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.entity.RegisteredBiller;


public interface RegisteredBillerRepository extends JpaRepository<RegisteredBiller, Integer> {

	//List<RegisteredBiller> findBySequenceId(Integer SequenceId);//QUERY METHODS
	//SELECT ALLL THINGS FROM REGISTEREDTABLE WHERE SEQUENCEID FROM API = SEQUENCE ID IN TABLE

	Iterable<RegisteredBiller> findByAccountNumber(Integer accountNumber);
}
