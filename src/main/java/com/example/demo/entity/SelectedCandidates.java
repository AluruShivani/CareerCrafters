package com.example.demo.entity;

public class SelectedCandidates {
	private int id;
	//private int vacancyId;
	//private int testId;
	//private int userId;
	private Vacancies vacancies;
	private JobSeekers jobseekers;
	private Test test;
	private String score;
	private String status;
	public SelectedCandidates() {
		super();
	}
	
	public SelectedCandidates(int id, Vacancies vacancies, JobSeekers jobseekers, Test test, String score, String status) {
		super();
		this.id = id;
		this.vacancies = vacancies;
		this.jobseekers = jobseekers;
		this.test = test;
		this.score = score;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Vacancies getVacancies() {
		return vacancies;
	}

	public void setVacancies(Vacancies vacancies) {
		this.vacancies = vacancies;
	}

	public JobSeekers getJobseekers() {
		return jobseekers;
	}

	public void setJobseekers(JobSeekers jobseekers) {
		this.jobseekers = jobseekers;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SelectedCandidtaes [id=" + id + ", vacancies=" + vacancies + ", jobseekers=" + jobseekers + ", test="
				+ test + ", score=" + score + ", status=" + status + "]";
	}
	
	
	

}
