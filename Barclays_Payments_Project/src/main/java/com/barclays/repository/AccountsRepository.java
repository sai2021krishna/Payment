package com.barclays.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barclays.entity.Accounts;


public interface AccountsRepository extends JpaRepository<Accounts, Integer> {

}
