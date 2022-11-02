package com.barclays.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.barclays.dto.AccountTransactionDTO;
import com.barclays.dto.BillsDTO;
import com.barclays.dto.RegisteredBillersDTO;
import com.barclays.dto.UserDTO;
import com.barclays.entity.Accounts_Transaction;
import com.barclays.exception.PaymentsException;



public interface UserService {
	public Integer addUser(UserDTO UserDTO) throws PaymentsException;
	public ResponseEntity<String> loginUser(UserDTO UserDTO) throws PaymentsException;
	public UserDTO getUser(Integer UserId) throws PaymentsException;
	//public void updateUser(Integer UserId, String emailId)throws PaymentsException;
	public void deleteUser(Integer UserId)throws PaymentsException;
	public List<UserDTO> getAllUsers() throws PaymentsException;
	
	
	public ResponseEntity<String> registerBiller(Integer SequenceId,RegisteredBillersDTO registerBillerDTO) throws PaymentsException;
	public ResponseEntity<List<RegisteredBillersDTO>> getAllBillers() throws PaymentsException;

	public ResponseEntity<List<RegisteredBillersDTO>> getBillers(Integer sequenceId) throws PaymentsException;
	public ResponseEntity<String>  deleteBiller(Integer billerSequenceId)throws PaymentsException;
	
	public ResponseEntity<String> generateBill(BillsDTO billsDTO) throws PaymentsException;
	
	public ResponseEntity<String> manualPay(Integer sequenceId,AccountTransactionDTO accountTransactionDTO)throws PaymentsException;
	
	
	public ResponseEntity<List<String>> getBills(Integer billerCode) throws PaymentsException;
	
	public ResponseEntity<List<String>> getAllBills() throws PaymentsException;
	
	public void listall(HttpServletResponse response) throws IOException,PaymentsException;
	
}
