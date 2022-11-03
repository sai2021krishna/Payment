package com.barclays.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barclays.entity.Bill;
import com.barclays.exception.PaymentsException;
import com.barclays.repository.BillRepository;

@Service
public class BillService {
	
	@Autowired
	BillRepository billsRepository;
	
	@Autowired
	private Environment environment;
	
	public ResponseEntity<String> generateBill(Bill bills) {
		Bill bill = new Bill();
		
		bill.setAmount(bills.getAmount());
		bill.setBillerCode(bills.getBillerCode());
		bill.setConsumerNumber(bills.getConsumerNumber());
		bill.setDueDate(bills.getDueDate());
		bill.setStatus(bills.getStatus());
		
		Bill bill2 = billsRepository.save(bill); //persisting data in database
		String successMessage = environment.getProperty("API.GENERATE_BILL")+ bill2.getBillSequenceId();
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}
	
	public ResponseEntity<List<String>> getBills(Integer billerCode) throws PaymentsException {
		Iterable<Bill> billers = billsRepository.findByBillerCode(billerCode);
		List<String> str = new ArrayList<>();
		
		billers.forEach(biller -> {
			String temp = "";
			String st="";
			
			if(biller.getStatus().equals(0))
			{ 
				temp = temp+"is Pending";
			}
			else if(biller.getStatus().equals(1))
			{ 
				temp = temp+"is Completed";
			}
			else if(biller.getStatus().equals(2))
			{ 
				temp = temp+"is Partially Paid";
			}
			else if(biller.getStatus().equals(3))
			{ 
				temp = temp+"has Passed due date";
			}
			st=st+"BillSequenceId:"+biller.getBillSequenceId()
			+" BillConsumerNumber:"+biller.getConsumerNumber()+" BillAmount : "+biller.getAmount() + " Bill payment " + temp;
			str.add(st);
			
			
		});
		
		return new ResponseEntity<>(str, HttpStatus.OK);
	}

}
