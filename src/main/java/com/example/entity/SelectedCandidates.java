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
@Table(name="selectedcadidates")
public class SelectedCandidates {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="vacancyId")
	private Vacancies vacancies;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private JobSeekers jobseekers;
	
	@ManyToOne
	@JoinColumn(name="testId")
	private Test test;
	
	@Column(name="score")
	private String score;
	
	@Column(name="status")
	private String status;

}
