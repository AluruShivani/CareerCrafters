package com.example.demo.entity;

public class Certificates {
	private int certificateId;
	private int userId;
	private JobSeekers jobseekers;
	private String certificateType;
	private String documentFile;
	public Certificates() {
		super();
	}
	
	public Certificates(int certificateId, int userId, JobSeekers jobseekers, String certificateType,
			String documentFile) {
		super();
		this.certificateId = certificateId;
		this.userId = userId;
		this.jobseekers = jobseekers;
		this.certificateType = certificateType;
		this.documentFile = documentFile;
	}

	public JobSeekers getJobseekers() {
		return jobseekers;
	}

	public void setJobseekers(JobSeekers jobseekers) {
		this.jobseekers = jobseekers;
	}

	public int getCertificateId() {
		return certificateId;
	}
	public void setCertificateId(int certificateId) {
		this.certificateId = certificateId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getDocumentFile() {
		return documentFile;
	}
	public void setDocumentFile(String documentFile) {
		this.documentFile = documentFile;
	}

	@Override
	public String toString() {
		return "Certificates [certificateId=" + certificateId + ", userId=" + userId + ", jobseekers=" + jobseekers
				+ ", certificateType=" + certificateType + ", documentFile=" + documentFile + "]";
	}
	
	
	

}
