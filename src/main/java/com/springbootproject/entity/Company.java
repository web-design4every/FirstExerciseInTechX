package com.springbootproject.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "company")
@Table(name = "company")
public class Company extends BaseEntity{
	
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}	
}
