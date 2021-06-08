package com.accenture.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="users_id")
	private Long id;
	
	@Column(name = "users_username", nullable = false, unique = true, length = 45)
	private String username;
	
	@Column(name = "users_password", nullable = false, length = 64)
	private String password;
	
	@Column(name = "users_name", nullable = false, length = 20)
	private String name;
	
	@Column(name = "users_role", nullable = false, length = 20)
	private String access;	
	
	public User() {
		
	}
	
	public User(Long id, String username, String password, String name, String access) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.access = access;
	}
	
	public User(String username, String password, String name, String access) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.access = access;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}
	
}
