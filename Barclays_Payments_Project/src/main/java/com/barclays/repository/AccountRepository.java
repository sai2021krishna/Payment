package com.barclays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Integer> {

}
