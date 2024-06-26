package com.example.demo.entity;

import java.sql.Date;

public class Test {
	private int testId;
	private int vacancyId;
	private Date testDate;
	private String testDuration;
	private Vacancies vacancy;

	public Test() {
		super();
	}
	public Test(int testId, int vacancyId, Date testDate, String testDuration) {
		super();
		this.testId = testId;
		this.vacancyId = vacancyId;
		this.testDate = testDate;
		this.testDuration = testDuration;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getVacancyId() {
		return vacancyId;
	}
	public void setVacancyId(int vacancyId) {
		this.vacancyId = vacancyId;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public String getTestDuration() {
		return testDuration;
	}
	public void setTestDuration(String testDuration) {
		this.testDuration = testDuration;
	}
	
	
	
	public Vacancies getVacancy() {
		return vacancy;
	}
	public void setVacancy(Vacancies vacancy) {
		this.vacancy = vacancy;
	}
	@Override
	public String toString() {
		return "Test [testId=" + testId + ", vacancyId=" + vacancyId + ", testDate=" + testDate + ", testDuration="
				+ testDuration + "]";
	}
	
	

}
