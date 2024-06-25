package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="admin")
public class Admin {
	@Id
	@Column(name="userName")
	private String userName;
	
	@Column(name="password")
	private String password;

}
