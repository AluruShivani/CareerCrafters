package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="certificates")
public class Certificates {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="certificateId")
	private int certificateId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private JobSeekers jobseekers;
	
	@Column(name="certificateType")
	private String certificateType;
	
	@Column(name="documentFile")
	private String documentFile;
}
