package com.barclays.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.entity.Bill;
import com.barclays.exception.PaymentsException;
import com.barclays.service.BillService;


@RestController
@RequestMapping(value = "/bills")
@Validated
public class BillController {
	
	@Autowired
	BillService billsService;

	@PostMapping(value = "/generateBill")
	public ResponseEntity<String> generatebill(@Valid @RequestBody Bill bills)
			throws PaymentsException {
		
		return billsService.generateBill(bills);
		
	}
	
	@GetMapping(value ="/getBills/{billerCode}")
	public ResponseEntity<List<String>> getbills(@PathVariable Integer billerCode) throws PaymentsException {
		return billsService.getBills(billerCode);
		
	}
}

