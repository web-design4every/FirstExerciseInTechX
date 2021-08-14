package com.springbootproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "user")
@Table(name = "user")
public class User extends BaseEntity{

	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;	

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
