package com.example.demo.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.JobApply;
import com.example.demo.entity.JobSeekers;
import com.example.demo.entity.Questions;
import com.example.demo.entity.Test;
import com.example.demo.entity.TestResult;
import com.example.demo.entity.Vacancies;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class JobApplyController {
//	@GetMapping("/jobApply")
//	 public ModelAndView addJobApply(@RequestParam("vacancyId") int vacancyId,Model model) {
//		// Vacancies vacancy = new Vacancies();
//		// vacancy.setVacancyId(vacancyId);
//		 
////		 JobSeekers jobseeker=new JobSeekers();
////		 jobseeker.setUserId(userId);
////		
//		model.addAttribute("vacancyId",vacancyId);
//	     //JobApply jobapply = new JobApply();
//	     //model.addAttribute("jobApply", jobapply); // Changed attribute name to "jobApply"
//	     
//	    // jobapply.setVacancies(vacancy);
//	     //jobapply.setJobseekers(jobseeker);
//	     
//	     ModelAndView view = new ModelAndView("/jobApply");
//	     view.addObject("vacancyId",vacancyId);
//	     // Removed leading slash from view name
//	     return view;
//	 }
//
//
//	 @PostMapping("/savejobApply")
//		public ModelAndView addjobapply(@ModelAttribute("jobapply") JobApply jobapply, Model model,HttpSession session) {
//		
//		 RestTemplate restTemplate = new RestTemplate();
//		   String url = "http://localhost:8090/api/v1/getVacancies/" + jobapply.getVacancyId();
//		   
//		   ResponseEntity<Vacancies> response = restTemplate.getForEntity(url, Vacancies.class);
//		   Vacancies vacancyObject = response.getBody();
//		jobapply.setVacancies(vacancyObject);
//		
//		ResponseEntity<JobSeekers> response1 = restTemplate.getForEntity(url, JobSeekers.class);
//		JobSeekers jobseekerObject = response1.getBody();
//		jobapply.setJobseekers(jobseekerObject);
//		
//		//JobSeekers jobseeker= new JobSeekers();
//		//int userId = jobseekerObject.getUserId();
//	     
//	      
//		    HttpHeaders headers = new HttpHeaders();
//		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		    HttpEntity<JobApply> entity = new HttpEntity<JobApply>(jobapply, headers);
//		    restTemplate = new RestTemplate();
//		     url = "http://localhost:8090/api/v1/applyJob";
//		    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//		    Object responseString = responseEntity.getBody();
//		    
//		    session.setAttribute("vacancyId",vacancyObject.getVacancyId());
//		    session.setAttribute("jobapply", jobapply);
//		    session.setAttribute("userId",jobseekerObject.getUserId());
//		    
//		    
//		    jobapply = new JobApply();
//		    model.addAttribute("jobapply", jobapply);
//		    ModelAndView view = new ModelAndView("/jobApply");
//		    model.addAttribute("responseString", responseString);
//		    return view;
//		}

	 
	 @GetMapping("jobApply")
		public ModelAndView addJobApply(Model model, HttpSession session, @RequestParam("vacancyId") int vacancyId) {
			
			
			
			JobApply jobApply = new JobApply();
		    Vacancies vacancy1= new Vacancies();
		    vacancy1.setVacancyId(vacancyId);
		    
		    jobApply.setVacancies(vacancy1);
		    
		   
		    model.addAttribute("jobApply", jobApply);

		    // Assuming you need JobSeekers information for some purpose
		    JobSeekers jobSeekers1 =(JobSeekers) session.getAttribute("jobseekers");
			
			int userId = jobSeekers1.getUserId();
			model.addAttribute("userId", userId);
			System.out.println("userId" +userId);

		    // Adding testId and vacancyId to the model
		  
		    model.addAttribute("vacancyId", vacancyId);
		    System.out.println("vacancyId" +vacancyId);

		    ModelAndView view = new ModelAndView("/jobApply");
		    return view;
		}
		
		@PostMapping("/savejobApply")
		public ModelAndView saveQuestions(@ModelAttribute("jobApply") JobApply jobApply, Model model,HttpSession session) {
			
			String status = "OnProgress";
			jobApply.setStatus(status);
			
			jobApply.setFinalScore("0");
			
			
			 JobSeekers jobSeekers1 =new JobSeekers();
				int userId = ((JobSeekers)session.getAttribute("jobseekers")).getUserId();
				model.addAttribute("userId", userId);
			jobSeekers1.setUserId(userId);
			
			System.out.println("userId here.."+userId);
			//jobApply.setJobseeker(jobSeekers1);
			jobApply.setJobseekers(jobSeekers1);
			Vacancies vacancy1= jobApply.getVacancies();
			 System.out.println("vacancy here..=" +vacancy1); 
			System.out.println("JobApply: " + jobApply);
			
			 
			
		    HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    HttpEntity<JobApply> entity = new HttpEntity<JobApply>(jobApply, headers);
		    
		    RestTemplate restTemplate = new RestTemplate();
		    String url = "http://localhost:8091/CareerCrafters1/api/v1/applyJob";
		    
		    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		  
		    String responseString = response.getBody();
		    System.out.println("objj =" +responseString);
		    // Clearing the question object after submission
		    jobApply= new JobApply();
		    model.addAttribute("jobApply",jobApply);
		    
		    ModelAndView view = new ModelAndView("userhome");
		    model.addAttribute("responseString", responseString);

		    return view;
		}
	
}
