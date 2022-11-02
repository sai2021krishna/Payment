package com.infy.repository;


import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Customer;

public interface CustomerRespository extends CrudRepository<Customer, Integer> {

}
