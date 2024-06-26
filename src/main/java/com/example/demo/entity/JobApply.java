package com.example.demo.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class JobApply {
    private int applyId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyDate;
    private JobSeekers jobseekers;
    private String finalScore;
    private String status;
    private int vacancyId;
    public int getVacancyId() {
		return vacancyId;
	}

	public void setVacancyId(int vacancyId) {
		this.vacancyId = vacancyId;
	}

	private Vacancies vacancies;

    public JobApply() {
        super();
    }

    

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public JobSeekers getJobseekers() {
        return jobseekers;
    }

    public void setJobseekers(JobSeekers jobseekers) {
        this.jobseekers = jobseekers;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vacancies getVacancies() {
        return vacancies;
    }

    public void setVacancies(Vacancies vacancies) {
        this.vacancies = vacancies;
    }

	@Override
	public String toString() {
		return "JobApply [applyId=" + applyId + ", applyDate=" + applyDate + ", jobseekers=" + jobseekers
				+ ", finalScore=" + finalScore + ", status=" + status + ", vacancyId=" + vacancyId + ", vacancies="
				+ vacancies + "]";
	}

    
}
