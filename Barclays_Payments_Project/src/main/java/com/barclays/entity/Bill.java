package com.barclays.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class  Bill {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ColumnDefault("0001")
	@Column(name="bill_sequence_id")
	private Integer billSequenceId;
	
	@Column(name="biller_code")
	private Integer billerCode;
	
	@JoinColumn(name="loginId")
	@Column(name="consumer_number")
	private Integer consumerNumber;
	
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="due_date")
	private Date dueDate;
	
	@Column(name="status")
	private Integer status;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "billSequenceId", unique = true)
//	private AccountTransaction accounts_Transaction;
	
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Bill(Integer billSequenceId, Integer billerCode, Integer consumerNumber, Integer amount, Date dueDate,
		Integer status) {
	super();
	this.billSequenceId = billSequenceId;
	this.billerCode = billerCode;
	this.consumerNumber = consumerNumber;
	this.amount = amount;
	this.dueDate = dueDate;
	this.status = status;
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
	public Integer getConsumerNumber() {
		return consumerNumber;
	}
	public void setConsumerNumber(Integer consumerNumber) {
		this.consumerNumber = consumerNumber;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
	
	
	
	
	