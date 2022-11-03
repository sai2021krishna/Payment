package com.barclays.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.dto.AccountTransactionDTO;
import com.barclays.dto.BillsDTO;
import com.barclays.dto.RegisteredBillersDTO;
import com.barclays.entity.RegisteredBiller;
import com.barclays.exception.PaymentsException;
import com.barclays.service.ExportToCsvService;
import com.barclays.service.RegisteredBillerService;
import com.barclays.service.UserService;

@RestController
@RequestMapping(value = "/pay")
@Validated
public class RegisteredBillerController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegisteredBillerService registeredBillerService;
	
	@Autowired
	private ExportToCsvService exportToCsvService;

	@Autowired
	private Environment environment;

	
	//getallregisteredbillersdata
	
	@GetMapping(value = "/registeredBillers")
	public ResponseEntity<List<RegisteredBiller>> getAllbillers() throws PaymentsException {
		return registeredBillerService.getAllBillers();
		
	}
	
	@GetMapping(value = "/registeredBillers/{sequenceId}")
	public ResponseEntity<List<RegisteredBiller>> getbillers(@PathVariable Integer sequenceId) throws PaymentsException {
		return registeredBillerService.getBillers(sequenceId);
		
	}

	@PostMapping(value = "/registerBiller/{sequenceId}")
	public ResponseEntity<String> registerBiller(@PathVariable Integer sequenceId, @RequestBody RegisteredBiller
			registerBillers)
			throws PaymentsException {
		
		return registeredBillerService.registerBiller(sequenceId,registerBillers );
		
	}	
	
	@DeleteMapping(value = "/deleteBillers/{billerSequenceId}")
	public ResponseEntity<String> deletebiller(@PathVariable Integer billerSequenceId) throws PaymentsException {
		return registeredBillerService.deleteBiller(billerSequenceId);
		
	}

	//..........................
	
	
	@GetMapping("/transactions/export")
    public void exportToCSV(HttpServletResponse response) throws IOException, PaymentsException {
        response.setContentType("text/csv");
        
         
        exportToCsvService.listall(response);
        
         
    }
	
	
	
}
