package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRespository;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRespository customerRespository;

	@Override
	public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRespository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customer2 = new CustomerDTO();
		customer2.setCustomerId(customer.getCustomerId());
		customer2.setDateOfBirth(customer.getDateOfBirth());
		customer2.setEmailId(customer.getEmailId());
		customer2.setName(customer.getName());
		return customer2;
	}

	@Override
	public Integer addCustomer(CustomerDTO customerDTO) throws InfyBankException {
		Customer customer = new Customer();
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setName(customerDTO.getName());
		customer.setCustomerId(customerDTO.getCustomerId());
		Customer customer2 = customerRespository.save(customer);
		return customer2.getCustomerId();
	}

	@Override
	public void updateCustomer(Integer customerId, String emailId) throws InfyBankException {
		Optional<Customer> customer = customerRespository.findById(customerId);
		Customer c = customer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		c.setEmailId(emailId);
	}

	@Override
	public void deleteCustomer(Integer customerId) throws InfyBankException {
		Optional<Customer> customer = customerRespository.findById(customerId);
		customer.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customerRespository.deleteById(customerId);
	}

	@Override
	public List<CustomerDTO> getAllCustomers() throws InfyBankException {
		Iterable<Customer> customers = customerRespository.findAll();
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		customers.forEach(customer -> {
			CustomerDTO cust = new CustomerDTO();
			cust.setCustomerId(customer.getCustomerId());
			cust.setDateOfBirth(customer.getDateOfBirth());
			cust.setEmailId(customer.getEmailId());
			cust.setName(customer.getName());
			customerDTOs.add(cust);
		});
		if (customerDTOs.isEmpty())
			throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
		return customerDTOs;
	}

}
