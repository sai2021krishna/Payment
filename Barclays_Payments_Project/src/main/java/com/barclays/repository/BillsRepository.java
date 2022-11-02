package com.barclays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.entity.Bills;

public interface BillsRepository extends JpaRepository<Bills, Integer> {
	
	List<Bills> findByBillerCode(Integer billerCode);
	List<Bills> findAll();
	
}
