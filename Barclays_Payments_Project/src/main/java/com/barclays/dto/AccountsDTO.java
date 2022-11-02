package com.barclays.dto;

public class AccountsDTO {

	
	private Integer sequenceId;
	private String accountNo;
	private String name;
	private String email;
	private Long currentBalance;
	
	public AccountsDTO()
	{
		
	}
	public AccountsDTO(Integer sequenceId, String accountNo, String name, String email, Long currentBalance) {
		super();
		this.sequenceId = sequenceId;
		this.accountNo = accountNo;
		this.name = name;
		this.email = email;
		this.currentBalance = currentBalance;
	}
	public Integer getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(Long currentBalance) {
		this.currentBalance = currentBalance;
	}
	@Override
	public String toString() {
		return "Accounts [sequenceId=" + sequenceId + ", accountNo=" + accountNo + ", name=" + name + ", email=" + email
				+ ", currentBalance=" + currentBalance + "]";
	}
	
	
	
	
}
