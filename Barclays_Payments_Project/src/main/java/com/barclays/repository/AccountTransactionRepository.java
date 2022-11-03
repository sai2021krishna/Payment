package com.barclays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.entity.AccountTransaction;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Integer>{

}
