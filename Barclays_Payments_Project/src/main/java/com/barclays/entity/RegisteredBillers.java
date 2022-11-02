package com.barclays.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RegisteredBillers {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billerSequenceId;
	private Integer billerCode;
	private String consumerNumber;
	private Integer accountNumber;
	
	
	
	
	public Integer getBillerSequenceId() {
		return billerSequenceId;
	}
	public void setBillerSequenceId(Integer billerSequenceId) {
		this.billerSequenceId = billerSequenceId;
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
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Override
	public String toString() {
		return "RegisteredBillers [billerSequenceId=" + billerSequenceId + ", billerCode=" + billerCode
				+ ", consumerNumber=" + consumerNumber + ", accountNumber=" + accountNumber + ", autopay=" 
				+ ", autopayLimit=" +  "]";
	}
	
	

}
