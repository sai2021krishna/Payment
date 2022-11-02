package com.barclays.dto;

public class RoleDTO {
	
	private Integer role_id;
	private String rolename;
	public RoleDTO(Integer role_id, String rolename) {
		super();
		this.role_id = role_id;
		this.rolename = rolename;
	}
	public RoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	@Override
	public String toString() {
		return "RoleDTO [role_id=" + role_id + ", rolename=" + rolename + "]";
	}
	
	
	

}
