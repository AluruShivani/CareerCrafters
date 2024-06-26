package com.example.demo.entity;



public class Company {
	private int companyId;
	private String companyName;
	private String companyEmail;
	private String mobile;
	private String location;
	private String state;
	private String city;
	private String companyLevel;
	private String url;
	private String logo;
	private String password;
	private String registerDate;
	public Company() {
		super();
	}
	
	public Company(int companyId, String companyName, String companyEmail, String mobile, String location,
			String state, String city, String companyLevel, String url, String logo, String password,
			String registerDate) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyEmail = companyEmail;
		this.mobile = mobile;
		this.location = location;
		this.state = state;
		this.city = city;
		this.companyLevel = companyLevel;
		this.url = url;
		this.logo = logo;
		this.password = password;
		this.registerDate = registerDate;
	}

	public int getCompanyId() {
	    return companyId;
	}

	public void setCompanyId(int companyId) {
	    this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCompanyLevel() {
		return companyLevel;
	}
	public void setCompanyLevel(String companyLevel) {
		this.companyLevel = companyLevel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "Company [comapanyId=" + companyId + ", companyName=" + companyName + ", companyemail=" + companyEmail
				+ ", mobile=" + mobile + ", location=" + location + ", state=" + state + ", city=" + city
				+ ", comapnyLevel=" + companyLevel + ", url=" + url + ", logo=" + logo + ", password=" + password
				+ ", registerDate=" + registerDate + "]";
	}
	
	
	

}
