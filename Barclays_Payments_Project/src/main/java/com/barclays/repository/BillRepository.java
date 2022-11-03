package com.barclays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {
	
	List<Bill> findByBillerCode(Integer billerCode);
	
}
