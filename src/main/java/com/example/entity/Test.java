//TestEntity
package com.example.entity;
import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Test")

public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testId")
    private int testId;
    
    @Column(name = "testDate")
    private Date testDate;
    
    @Column(name = "testDuration")
    private Integer testDuration;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vacancyId")
    private Vacancies vacancy; // Assuming Vacancies is the entity name
	public Test() {
		super();
	}
	public Test(int testId, Date testDate, Integer testDuration, Vacancies vacancy) {
		super();
		this.testId = testId;
		this.testDate = testDate;
		this.testDuration = testDuration;
		this.vacancy = vacancy;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public Integer getTestDuration() {
		return testDuration;
	}
	public void setTestDuration(Integer testDuration) {
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
		return "Test [testId=" + testId + ", testDate=" + testDate + ", testDuration=" + testDuration + ", vacancy="
				+ vacancy + "]";
	}
    
    
}