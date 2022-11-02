package com.barclays.dto;

import java.time.LocalDate;

public class BillsDTO {
	
	private Integer billSequenceId;
	private Integer billerCode;
	private String consumerNumber;
	private Integer amount;
	private String dueDate;
	private String status;
	
	
	public BillsDTO() {
		
	}
	public BillsDTO(Integer billSequenceId, Integer billerCode, String consumerNumber, Integer amount,
			String dueDate, String status ) {
		super();
		this.billSequenceId = billSequenceId;
		this.billerCode = billerCode;
		this.consumerNumber = consumerNumber;
		this.amount = amount;
		this.dueDate = dueDate;
		this.status= status;
	}
	public Integer getBillSequenceId() {
		return billSequenceId;
	}
	public void setBillSequenceId(Integer billSequenceId) {
		this.billSequenceId = billSequenceId;
	}
	public Integer getBillerCode() {
		return billerCode;
	}
	public void setBillerCode(Integer billerCode) {
		this.billerCode = billerCode;
	}
	public String getConsumerNumber() {
		return consumerNumber;
	}
	public void setConsumerNumber(String consumerNumber) {
		this.consumerNumber = consumerNumber;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	@Override
	public String toString() {
		return "Bills [billSequenceId=" + billSequenceId + ", billerCode=" + billerCode
				+ ", consumerNumber=" + consumerNumber + ", amount=" + amount + ", dueDate=" + dueDate
				+", status="+status+"]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
