package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Profdb")

public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 
	
	private Long id;
	
	@Column(nullable = false, unique = true, length = 45)
	private String pid;
	
	public String getPID() {
		return pid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setPID(String pid) {
		this.pid = pid;
	}
	@Column(nullable =false, length = 64 )
	private String password;
	
	@Column(nullable=false, length = 64)
	private String profname;
	
	
	
	public String getProfname() {
		return profname;
	}
	public void setProfname(String profname) {
		this.profname = profname;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
