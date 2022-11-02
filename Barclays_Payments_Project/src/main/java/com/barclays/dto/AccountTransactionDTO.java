package com.barclays.dto;

import java.sql.Date;
import java.time.LocalDate;

public class AccountTransactionDTO {

	private Integer	trans_ref_num;
	private  Integer sequence_id;
	private LocalDate date;
	private Integer amount;
	private  String transaction_type;
	private Integer bill_ref_num;
	private String description;
	
	public AccountTransactionDTO(Integer trans_ref_num, Integer sequence_id, LocalDate date, Integer amount,
			String transaction_type, Integer bill_ref_num, String description) {
		super();
		this.trans_ref_num = trans_ref_num;
		this.sequence_id = sequence_id;
		this.date = date;
		this.amount = amount;
		this.transaction_type = transaction_type;
		this.bill_ref_num = bill_ref_num;
		this.description = description;
	}
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
	@Override
	public String toString() {
		return "Accounts_Transaction [trans_ref_num=" + trans_ref_num + ", sequence_id=" + sequence_id + ", date="
				+ date + ", amount=" + amount + ", transaction_type=" + transaction_type + ", bill_ref_num="
				+ bill_ref_num + ", description=" + description + "]";
	}
	
}
