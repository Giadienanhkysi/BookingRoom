package com.mycompany.bookingroom.model;

import java.sql.Timestamp;

public class User {
	private int id;
	private String username, password, first_name, last_name, gender, 
	phone, email, address, token;
	private Timestamp created_at, updated_at, last_login;
	
	public User() {

	}

	public User(int id, String username, String password, String first_name, String last_name, String gender,
			String phone, String email, String address, String token, Timestamp created_at, Timestamp updated_at,
			Timestamp last_login) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.token = token;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.last_login = last_login;
	}

	public User(int id, String username, String password, String first_name, String last_name, String gender,
			String phone, String email, String address, String token) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.token = token;
	}

	public User(String username, String password, String first_name, String last_name, String gender, String phone,
			String email, String address, String token) {
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Timestamp getLast_login() {
		return last_login;
	}

	public void setLast_login(Timestamp last_login) {
		this.last_login = last_login;
	}
}
