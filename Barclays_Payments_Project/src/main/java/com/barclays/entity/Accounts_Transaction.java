package com.barclays.entity;

import java.sql.Date;
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
public class Accounts_Transaction {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ColumnDefault("150")
	private Integer	trans_ref_num;
	private  Integer sequence_id;
	private LocalDate date;
	private Integer amount;
	private  String transaction_type;
	private Integer bill_ref_num;
	private String description;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "billSequenceId", unique = true)
//	private Bills bill;
	
//	public Bills getBill() {
//		return bill;
//	}
//	public void setBill(Bills bill) {
//		this.bill = bill;
//	}
	public Integer getBill_ref_num() {
		return bill_ref_num;
	}
	public void setBill_ref_num(Integer bill_ref_num) {
		this.bill_ref_num = bill_ref_num;
	}
	public Integer getSequence_id() {
		return sequence_id;
	}
	public void setSequence_id(Integer sequence_id) {
		this.sequence_id = sequence_id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public Integer getTrans_ref_num() {
		return trans_ref_num;
	}
	public void setTrans_ref_num(Integer trans_ref_num) {
		this.trans_ref_num = trans_ref_num;
	}

	@Override
	public String toString() {
		return "Accounts_Transaction [trans_ref_num=" + trans_ref_num + ", sequence_id=" + sequence_id + ", date="
				+ date + ", amount=" + amount + ", transaction_type=" + transaction_type + ", bill_ref_num="
				+ bill_ref_num + ", description=" + description + "]";
	}
	
	
	
}



















/*Sequence Id --> FK from Accounts
Transaction Reference-->Unique
Date 
Ammount
Debiy/Credit
Description
Bill Reference Number--> PK */
