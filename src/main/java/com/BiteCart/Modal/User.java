package com.BiteCart.Modal;

import java.sql.Timestamp;

public class User {
	private int userId;
	private String name;
	private String username;
	private String password;
	private String Email;
	private String phone;
	private String Address;
	private String role;
	private Timestamp createDate;
	private Timestamp lastLoginDate;
	public User() {
	}
	public User(String name, String username, String password, String email, String phone, String address,
			String role) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		Email = email;
		this.phone = phone;
		Address = address;
		this.role = role;
	}
	public User(int userId, String username, String password, String email, String phone, String address, String role,
			Timestamp createDate, Timestamp lastLoginDate, String name) {
		super();
		this.userId = userId;
		
		this.name=name;
		this.username = username;
		this.password = password;
		Email = email;
		this.phone = phone;
		Address = address;
		this.role = role;
		this.createDate = createDate;
		this.lastLoginDate = lastLoginDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	@Override
	public String toString() {
		return "User : [" + name + " " + Email + " " + phone + " " + Address + "]";
	}

}
