package com.barclays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.entity.Accounts_Transaction;

public interface AccountTransactionRepository extends JpaRepository<Accounts_Transaction, Integer>{

}
