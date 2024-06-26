package com.example.demo.entity;



public class Vacancies {
	private int vacancyId;
	private int companyId;
	private String postDate;
	private String jobTitle;
	private String description;
	private String Requirements;
	private int noOfVacancies;
	private String openDate;
	private String closeDate;
	private Company company;
	public Vacancies() {
		super();
	}
	
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}

	
	public Vacancies(int vacancyId, int companyId, String postDate, String jobTitle, String description,
			String requirements, int noOfVacancies, String openDate, String closeDate, Company company) {
		super();
		this.vacancyId = vacancyId;
		this.companyId = companyId;
		this.postDate = postDate;
		this.jobTitle = jobTitle;
		this.description = description;
		Requirements = requirements;
		this.noOfVacancies = noOfVacancies;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.company = company;
	}



	public int getVacancyId() {
		return vacancyId;
	}
	public void setVacancyId(int vacancyId) {
		this.vacancyId = vacancyId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRequirements() {
		return Requirements;
	}
	public void setRequirements(String requirements) {
		Requirements = requirements;
	}
	public int getNoOfVacancies() {
		return noOfVacancies;
	}
	public void setNoOfVacancies(int noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}
	
	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}



	@Override
	public String toString() {
		return "Vacancies [vacancyId=" + vacancyId + ", companyId=" + companyId + ", postDate=" + postDate
				+ ", jobTitle=" + jobTitle + ", description=" + description + ", Requirements=" + Requirements
				+ ", noOfVacancies=" + noOfVacancies + ", openDate=" + openDate + ", closeDate=" + closeDate
				+ ", company=" + company + "]";
	}

	 

	
	
	

}
