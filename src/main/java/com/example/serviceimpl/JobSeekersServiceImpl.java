package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.JobSeekers;
import com.example.entity.Question;
import com.example.repository.JobSeekersRepository;
import com.example.service.JobSeekersService;

@Service
public class JobSeekersServiceImpl implements JobSeekersService{
	public final JobSeekersRepository jobseekersrepository;
	
	@Autowired
	public JobSeekersServiceImpl(JobSeekersRepository jobseekersrepository) {
		this.jobseekersrepository=jobseekersrepository;
	}

	@Override
	public JobSeekers saveJobSeekers(JobSeekers jobseekers) {
		return jobseekersrepository.save(jobseekers);
	}

	@Override
	public List<JobSeekers> getAllJobSeekers() {
		return jobseekersrepository.findAll();
	}

	@Override
	public boolean isJobSeekerExits(int userId) {
		return jobseekersrepository.existsById(userId);
	}

	@Override
	public JobSeekers getJobSeekersById(int userId) {
		return jobseekersrepository.findById(userId).orElse(null);
	}

	@Override
	public boolean updateJobSeekers(JobSeekers jobseekers) {
		if(isJobSeekerExits(jobseekers.getUserId())) {
			jobseekersrepository.save(jobseekers);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteJobSeekers(int userId) {
		if(jobseekersrepository.existsById(userId)) {
			jobseekersrepository.deleteById(userId);
			return true;
		}
		return false;
	}

	@Override
	public JobSeekers loginValidate(JobSeekers jobseekers) {
		// TODO Auto-generated method stub
		JobSeekers jobseekers1=jobseekersrepository.findByEmailAndPassword(jobseekers.getEmail(), jobseekers.getPassword());
		System.out.println("what is there in Jobseekers=" + jobseekers1);
        return jobseekers1;
	}

	



}
