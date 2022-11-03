package com.barclays.service;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.barclays.entity.AccountTransaction;
import com.barclays.exception.PaymentsException;
import com.barclays.repository.AccountTransactionRepository;
import com.opencsv.CSVWriter;


@Service
public class ExportToCsvServiceImpl implements ExportToCsvService {
	
	@Autowired
	AccountTransactionRepository accountTransactionRepository;
	
	@Override
	public void listall(HttpServletResponse response) throws IOException,PaymentsException {
		
		String outputFileName = "D:\\Barclays training material\\final project\\CapstonePayment\\Barclays_Payments_Project\\ReportFile\\AccountTransaction" + ".csv";
        // Change the file location
        File reportFile = new File(outputFileName);
		
        Iterable<AccountTransaction> transactions = accountTransactionRepository.findAll();
		List<AccountTransaction> trans = new ArrayList<>();
		
		transactions.forEach(tr -> {
			AccountTransaction at = new AccountTransaction();
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
        ICsvBeanWriter csvWriter2 = new CsvBeanWriter(new FileWriter(outputFileName), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Transaction Reference Number", "Amount", "Bill Reference Number", "Date", "Description"," SequenceId","Transaction Type"};
        String[] nameMapping = {"trans_ref_num", "amount", "bill_ref_num", "date","description", "sequence_id","transaction_type"};
         
        csvWriter.writeHeader(csvHeader);
        csvWriter2.writeHeader(csvHeader);
         
        for (AccountTransaction t : trans) {
        	csvWriter2.write(t, nameMapping);
            csvWriter.write(t, nameMapping);
        }
         
        csvWriter.close();
        csvWriter2.close();
		
        
//        String mimeType = "text/csv";
//        response.setContentType(mimeType);
//        String reportFileName = "AccountTransaction.csv";
//        response.setHeader("Content-Disposition", String.format("attachment; filename=\""+reportFileName+"\""));
//        response.setContentLength((int) reportFile.length());
//        InputStream inputStream = new BufferedInputStream(new FileInputStream(reportFile));
//
//        FileCopyUtils.copy(inputStream, response.getOutputStream());
//        response.flushBuffer();
	}
}
