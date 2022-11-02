package com.barclays.entity;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loginId;
	private String password;
	private String linkedAccountSequenceId;
	private Integer roleId;
	private String role_name;
	
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLinkedAccountSequenceId() {
		return linkedAccountSequenceId;
	}
	public void setLinkedAccountSequenceId(String linkedAccountSequenceId) {
		this.linkedAccountSequenceId = linkedAccountSequenceId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
	public String getRoleName() {
		return role_name;
	}
	public void setRoleName(String role_name) {
		this.role_name = role_name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(linkedAccountSequenceId, loginId, password, role_name, roleId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(linkedAccountSequenceId, other.linkedAccountSequenceId)
				&& Objects.equals(loginId, other.loginId) && Objects.equals(password, other.password)
				&& Objects.equals(role_name, other.role_name) && Objects.equals(roleId, other.roleId);
	}
	@Override
	public String toString() {
		return "User [loginId=" + loginId + ", password=" + password + ", linkedAccountSequenceId="
				+ linkedAccountSequenceId + ", roleId=" + roleId + ", role=" + role_name + "]";
	}
	
	
	
	}
