package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.entity.JobSeekers;
import com.example.entity.Question;
import com.example.service.JobSeekersService;

@RestController
@RequestMapping("/api/v1")
public class JobSeekersController {
	@Autowired
	JobSeekersService service;
	
	@PostMapping("/addJobseekers")
	public ResponseEntity<Object> addJobSeekers(@RequestBody JobSeekers jobseekers){
		service.saveJobSeekers(jobseekers);
		return new ResponseEntity<>("JobSeeker added successfully",HttpStatus.CREATED);
	}
	
	@GetMapping("/viewJobSeekers")
	public ResponseEntity<Object> viewJobSeekers(){
		List<JobSeekers> jobseekers=service.getAllJobSeekers();
		ResponseEntity<Object> entity=new ResponseEntity<>(jobseekers,HttpStatus.OK);
		return entity;
	}

	@GetMapping("/getJobSeekers/{userId}")
	public ResponseEntity<Object> getJobSeekers(@PathVariable("userId")int userId){
		JobSeekers jobseekers;
		if(service.isJobSeekerExits(userId)) {
			jobseekers=service.getJobSeekersById(userId);
		}else {
			jobseekers=null;
		}
		ResponseEntity<Object>entity=new ResponseEntity<>(jobseekers,HttpStatus.OK);
		return entity;
	}
	
	@DeleteMapping("/deleteJobSeekers/{userId}")
	public ResponseEntity<Object> deleteJobSeekers(@PathVariable("userId")int userId){
		boolean flag;
		if(service.isJobSeekerExits(userId)) {
			flag=service.deleteJobSeekers(userId);
		}else {
			flag=false;
		}
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}
	
	@PutMapping(value="/updateJobSeekers/{userId}")
	public ResponseEntity<Object> updateJobSeekers(@PathVariable("userId")int userId,@RequestBody JobSeekers jobseekers){
		JobSeekers jobseekers1;
		boolean flag;
		if(service.isJobSeekerExits(userId)) {
			flag=service.updateJobSeekers(jobseekers);
		}else {
			flag=false;
		}
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}
	
	 @PostMapping(value="/jobseekersLogin")
		public ResponseEntity<Object> jobseekersLogin(@RequestBody JobSeekers jobseekers){
			JobSeekers jobseekers1 = service.loginValidate(jobseekers);
			return new ResponseEntity<>(jobseekers1,HttpStatus.OK);
		}
	 
	 
	 

}
