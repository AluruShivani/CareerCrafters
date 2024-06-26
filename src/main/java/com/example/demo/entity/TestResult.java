package com.example.demo.entity;

public class TestResult {
	private int resultId;
	private int questionId;
	private String selectionOption;
	private String result;
	private String score;
	private int userId;
	private int vacancyId;
	private JobSeekers jobseekers;
	private Vacancies vacancies;
	private Questions question;
	public TestResult() {
		super();
	}
	
	
	public Questions getQuestion() {
		return question;
	}


	public void setQuestion(Questions question) {
		this.question = question;
	}


	public JobSeekers getJobseekers() {
		return jobseekers;
	}

	public void setJobseekers(JobSeekers jobseekers) {
		this.jobseekers = jobseekers;
	}

	public Vacancies getVacancies() {
		return vacancies;
	}

	public void setVacancies(Vacancies vacancies) {
		this.vacancies = vacancies;
	}

	

	public TestResult(int resultId, int questionId, String selectionOption, String result, String score, int userId,
			int vacancyId, JobSeekers jobseekers, Vacancies vacancies, Questions question) {
		super();
		this.resultId = resultId;
		this.questionId = questionId;
		this.selectionOption = selectionOption;
		this.result = result;
		this.score = score;
		this.userId = userId;
		this.vacancyId = vacancyId;
		this.jobseekers = jobseekers;
		this.vacancies = vacancies;
		this.question = question;
	}


	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getSelectionOption() {
		return selectionOption;
	}
	public void setSelectionOption(String selectionOption) {
		this.selectionOption = selectionOption;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getVacancyId() {
		return vacancyId;
	}
	public void setVacancyId(int vacancyId) {
		this.vacancyId = vacancyId;
	}


	@Override
	public String toString() {
		return "TestResult [resultId=" + resultId + ", questionId=" + questionId + ", selectionOption="
				+ selectionOption + ", result=" + result + ", score=" + score + ", userId=" + userId + ", vacancyId="
				+ vacancyId + ", jobseekers=" + jobseekers + ", vacancies=" + vacancies + ", question=" + question
				+ "]";
	}

	
	
	

}
