package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.entity.JobSeekers;
import com.example.entity.Question;

@Service
public interface JobSeekersService {
	JobSeekers saveJobSeekers(JobSeekers jobseekers);
	List<JobSeekers> getAllJobSeekers();
	boolean isJobSeekerExits(int userId);
	JobSeekers getJobSeekersById(int userId);
	boolean updateJobSeekers(JobSeekers jobseekers);
	boolean deleteJobSeekers(int userId);
	public JobSeekers loginValidate(JobSeekers jobseekers);
	

}
