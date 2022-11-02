package com.barclays.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.barclays.dto.AccountTransactionDTO;
import com.barclays.dto.BillsDTO;
import com.barclays.dto.RegisteredBillersDTO;
import com.barclays.dto.UserDTO;
import com.barclays.entity.Accounts;
import com.barclays.entity.Accounts_Transaction;
import com.barclays.entity.Bills;
import com.barclays.entity.EmailDetails;
import com.barclays.entity.RegisteredBillers;
import com.barclays.entity.User;
import com.barclays.exception.PaymentsException;
import com.barclays.repository.AccountTransactionRepository;
import com.barclays.repository.AccountsRepository;
import com.barclays.repository.BillsRepository;
import com.barclays.repository.RegisteredBillersRepository;
import com.barclays.repository.UserRespository;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private Environment environment;

	@Autowired
	private UserRespository userRespository;

	@Autowired
	private RegisteredBillersRepository registeredBillersRepository;
	
	@Autowired
	private BillsRepository billsRepository;
	
	@Autowired
	private AccountTransactionRepository accountTransactionRepository;
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public UserDTO getUser(Integer userId) throws PaymentsException {
		Optional<User> optional = userRespository.findById(userId);
		User user = optional.orElseThrow(() -> new PaymentsException("Service.CUSTOMER_NOT_FOUND"));
		UserDTO user2 = new UserDTO();
		user2.setLoginId(user.getLoginId());
		user2.setPassword(user.getPassword());
		user2.setRoleId(user.getRoleId());
		
		user2.setLinkedAccountSequenceId(user.getLinkedAccountSequenceId());
		
		return user2;
	}

	@Override
	public Integer addUser(UserDTO userDTO) throws PaymentsException {
		User user = new User();
		user.setLoginId(userDTO.getLoginId());
		user.setPassword(userDTO.getPassword());
		user.setRoleName(userDTO.getRoleName());
		user.setLinkedAccountSequenceId(userDTO.getLinkedAccountSequenceId());
		if(userDTO.getRoleName().equals("Bank_Manager"))
		{
			user.setRoleId(1);
		}
		else
		{
			user.setRoleId(2);
		}
		
		User user2 = userRespository.save(user);
		return user2.getLoginId();
	}

//	@Override
//	public void updateUser(Integer userId, String emailId) throws PaymentsException {
//		Optional<User> user = userRespository.findById(userId);
//		User c = user.orElseThrow(() -> new PaymentsException("Service.CUSTOMER_NOT_FOUND"));
//		c.setEmailId(emailId);
//	}

	@Override
	public void deleteUser(Integer loginId) throws PaymentsException {
		Optional<User> user = userRespository.findById(loginId);
		user.orElseThrow(() -> new PaymentsException("Service.CUSTOMER_NOT_FOUND"));
		userRespository.deleteById(loginId);
	}

	@Override
	public List<UserDTO> getAllUsers() throws PaymentsException {
		Iterable<User> users = userRespository.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();
		users.forEach(user -> {
			UserDTO us = new UserDTO();
			us.setLoginId(user.getLoginId());
			us.setPassword(user.getPassword());
			us.setRoleId(user.getRoleId());
			us.setLinkedAccountSequenceId(user.getLinkedAccountSequenceId());
			userDTOs.add(us);
		});
		if (userDTOs.isEmpty())
			throw new PaymentsException("Service.USERS_NOT_FOUND");
		return userDTOs;
	}

	@Override
	public ResponseEntity<String> loginUser(UserDTO UserDTO) throws PaymentsException {
		Optional<User> optional = userRespository.findById(UserDTO.getLoginId());
		User user = optional.orElseThrow(() -> new PaymentsException("Service.USERS_NOT_FOUND"));
		if(user.getPassword().equals(UserDTO.getPassword()))
		{
			String successMessage = environment.getProperty("API.LOGGED_IN") + user.getLoginId()+
					"AS"+user.getRoleName();
			return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
			
		}
		else
		{
			throw new PaymentsException("Service.INCORRECT");
		}
	}

	@Override
	public ResponseEntity<String> registerBiller(Integer SequenceId,RegisteredBillersDTO registerBillerDTO) throws PaymentsException {
		
		RegisteredBillers registerBiller = new RegisteredBillers();
		
		registerBiller.setBillerCode(registerBillerDTO.getBillerCode());
		registerBiller.setConsumerNumber(registerBillerDTO.getConsumerNumber());
		registerBiller.setSequenceId(SequenceId);
		
		
		RegisteredBillers biller2 = registeredBillersRepository.save(registerBiller);//PERSISTING IN DATABASE
		
		String successMessage = environment.getProperty("API.REGISTERED_BILLER");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<List<RegisteredBillersDTO>> getAllBillers() throws PaymentsException {
	
		Iterable<RegisteredBillers> billers = registeredBillersRepository.findAll();
		List<RegisteredBillersDTO> registeredBillersDTOs = new ArrayList<>();
		billers.forEach(biller -> {
			RegisteredBillersDTO rb = new RegisteredBillersDTO();
			
			rb.setBillerCode(biller.getBillerCode());
			rb.setBillerSequenceId(biller.getBillerSequenceId());
			rb.setConsumerNumber(biller.getConsumerNumber());
			rb.setSequenceId(biller.getSequenceId());
			registeredBillersDTOs.add(rb);
		});
		if (registeredBillersDTOs.isEmpty())
			throw new PaymentsException("Service.BILLER_NOT_FOUND");
		
		return new ResponseEntity<>(registeredBillersDTOs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RegisteredBillersDTO>> getBillers(Integer sequenceId) throws PaymentsException {
	
		Iterable<RegisteredBillers> billers = registeredBillersRepository.findBySequenceId(sequenceId);
		List<RegisteredBillersDTO> registeredBillersDTOs = new ArrayList<>();
		
		billers.forEach(biller -> {
			RegisteredBillersDTO rb = new RegisteredBillersDTO();
			
			rb.setBillerCode(biller.getBillerCode());
			rb.setBillerSequenceId(biller.getBillerSequenceId());
			rb.setConsumerNumber(biller.getConsumerNumber());
			rb.setSequenceId(biller.getSequenceId());
			registeredBillersDTOs.add(rb);
		});
		if (registeredBillersDTOs.isEmpty())
			throw new PaymentsException("Service.BILLER_NOT_FOUND");
		return new ResponseEntity<>(registeredBillersDTOs, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<String> deleteBiller(Integer billerSequenceId) throws PaymentsException {
		
		Optional<RegisteredBillers> register= registeredBillersRepository.findById(billerSequenceId);
		register.orElseThrow(() -> new PaymentsException("Service.BILLER_NOT_FOUND"));
		registeredBillersRepository.deleteById(billerSequenceId);
		String successMessage = environment.getProperty("API.BILLER_DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}
//bill generation by manager
	@Override
	public ResponseEntity<String> generateBill(BillsDTO billsDTO) {
		Bills bill = new Bills();
		
		bill.setAmount(billsDTO.getAmount());
		bill.setBillerCode(billsDTO.getBillerCode());
		bill.setConsumerNumber(billsDTO.getConsumerNumber());
		bill.setDueDate(billsDTO.getDueDate());
		bill.setStatus(billsDTO.getStatus());
		
		Bills bill2 = billsRepository.save(bill); //persisting data in database
		String successMessage = environment.getProperty("API.GENERATE_BILL")+ bill2.getBillSequenceId();
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}

	
	//Manual Payment by account Holder
	@Override
	public ResponseEntity<String> manualPay(Integer SequenceId,AccountTransactionDTO accountTransactionDTO) throws PaymentsException {
		Accounts_Transaction accountTrans = new Accounts_Transaction();
		accountTrans.setAmount(accountTransactionDTO.getAmount());
		accountTrans.setDate(LocalDate.now());
		accountTrans.setBill_ref_num(accountTransactionDTO.getBill_ref_num());
		accountTrans.setSequence_id(SequenceId);
		accountTrans.setTransaction_type("Debit");
		accountTrans.setDescription(accountTransactionDTO.getDescription());
		
		
		
		Accounts_Transaction accountTrans2 = accountTransactionRepository.save(accountTrans);
		//...................................................................................
		Optional<Accounts> accounts= accountsRepository.findById(SequenceId);
		Accounts a = accounts.orElseThrow(() -> new PaymentsException("Service.USER_NOT_FOUND"));
		a.setCurrentBalance(a.getCurrentBalance()- accountTrans2.getAmount());
		
		Optional<Bills> bill= billsRepository.findById(accountTrans2.getBill_ref_num());
		
		Bills b= bill.orElseThrow(() -> new PaymentsException("Service.USER_NOT_FOUND"));
		
		b.setStatus("Completed");
		
		String successMessage = environment.getProperty("API.PAYMENT_SUCCESSFULL")+ accountTrans2.getBill_ref_num();;
		
		EmailDetails details=new EmailDetails();
		details.setRecipient(a.getEmail());
		details.setMsgBody("Payment Successful for Bill Number"+accountTrans2.getBill_ref_num());
		details.setSubject("Payment Information");
		emailService.sendSimpleMail(details);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<List<String>> getBills(Integer billerCode) throws PaymentsException {
		Iterable<Bills> billers = billsRepository.findByBillerCode(billerCode);
		List<String> str = new ArrayList<>();
		
		billers.forEach(biller -> {
			String st="";
			if(biller.getStatus().equals("Completed"))
			{
				st=st+"BillSequenceId:"+biller.getBillSequenceId()
				+" BillConsumerNumber:"+biller.getConsumerNumber()+" BillAmount : "+biller.getAmount();
				str.add(st);
			}
			
		});
		
		return new ResponseEntity<>(str, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<String>> getAllBills() throws PaymentsException {
		Iterable<Bills> billers = billsRepository.findAll();
		List<String> str = new ArrayList<>();
		
		billers.forEach(biller -> {
			String st="";
			if(biller.getStatus().equals("Completed"))
			{
				st=st+"BillSequenceId : "+biller.getBillSequenceId()
				+" BillConsumerNumber : "+biller.getConsumerNumber()+" BillAmount : "+biller.getAmount();
				str.add(st);
			}
			
		});
		
		
		return new ResponseEntity<>(str, HttpStatus.OK);
	}

	@Override
	public void listall(HttpServletResponse response) throws IOException,PaymentsException {
		
		Iterable<Accounts_Transaction> transactions = accountTransactionRepository.findAll();
		List<Accounts_Transaction> trans = new ArrayList<>();
		
		transactions.forEach(tr -> {
			Accounts_Transaction at = new Accounts_Transaction();
			at.setAmount(tr.getAmount());
			at.setBill_ref_num(tr.getBill_ref_num());
			at.setDate(tr.getDate());
			at.setDescription(tr.getDescription());
			at.setSequence_id(tr.getSequence_id());
			at.setTransaction_type(at.getTransaction_type());
			
			trans.add(at);
		});
		if (trans.isEmpty())
			throw new PaymentsException("Service.BILLER_NOT_FOUND");
		
		
		
		//...................
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        response.reset();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Transaction Reference Number", "Amount", "Bill Reference Number", "Date", "Description"," SequenceId","Transaction Type"};
        String[] nameMapping = {"trans_ref_num", "amount", "bill_ref_num", "date","description", "sequence_id","transaction_type"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (Accounts_Transaction t : trans) {
            csvWriter.write(t, nameMapping);
        }
         
        csvWriter.close();
		
		
	}

	
		
	



}
