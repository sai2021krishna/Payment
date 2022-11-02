package com.barclays.dto;

public class MasterBillerDTO {
	
	private String billerName;
	private Integer billerCode;
	
	
	
	public MasterBillerDTO() {
		
	}
	public MasterBillerDTO(String billerName, Integer billerCode) {
		super();
		this.billerName = billerName;
		this.billerCode = billerCode;
		
	}
	public String getbillerName() {
		return billerName;
	}
	public void setbillerName(String billerName) {
		this.billerName = billerName;
	}
	public Integer getBillerCode() {
		return billerCode;
	}
	public void setBillerCode(Integer billerCode) {
		this.billerCode = billerCode;
	}
	
	@Override
	public String toString() {
		return "MasterBiller [billerName=" + billerName + ", billerCode=" + billerCode
				+ "]";
	}
	
	

}
