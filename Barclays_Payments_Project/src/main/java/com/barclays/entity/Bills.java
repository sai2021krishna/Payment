package com.barclays.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class  Bills {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ColumnDefault("0001")
	private Integer billSequenceId;
	private Integer billerCode;
	private String consumerNumber;
	private Integer amount;
	private String dueDate;
	private String status;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "billSequenceId", unique = true)
//	private Accounts_Transaction accounts_Transaction;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
	
	
	
	
	