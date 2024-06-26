package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Company;
import com.example.demo.entity.Vacancies;

import jakarta.servlet.http.HttpSession;

@Controller
public class VacancyController {
	 @GetMapping("/addVacancy")
		public ModelAndView addVacancy(Model model, HttpSession session) {
		    Vacancies vacancies = new Vacancies();
		    model.addAttribute("vacancies", vacancies);
		    Company company = (Company) session.getAttribute("company");
		    int cid = company.getCompanyId();
		    System.out.println("companyId:::" + cid);
		    model.addAttribute("companyId", company.getCompanyId());
		    ModelAndView view = new ModelAndView("/addVacancy");
		    return view;
		}
	 
	 @PostMapping("/addVacancy")
		public ModelAndView addvacancy(@ModelAttribute("vacancies") Vacancies vacancies, Model model,HttpSession session) {
		 int companyId=((Company) session.getAttribute("company")).getCompanyId();
		 
		 if(vacancies.getCompany()==null) {
			 vacancies.setCompany(new Company());
		 }
		 
		 vacancies.getCompany().setCompanyId(companyId);
		    HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    HttpEntity<Vacancies> entity = new HttpEntity<>(vacancies, headers);
		    RestTemplate restTemplate = new RestTemplate();
		    String url = "http://localhost:8091/CareerCrafters1/api/v1/addVacancy";
		    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		    String responseString = responseEntity.getBody();
		    session.setAttribute("vacancyId", responseString);
		    
		    vacancies = new Vacancies();
		    model.addAttribute("vacancies", vacancies);
		    ModelAndView view = new ModelAndView("addVacancy");
		    model.addAttribute("responseString", responseString);
		    return view;
		}
	 
	 @GetMapping("/viewvacancies")//vacancies view in admin side
	 public ModelAndView viewVacancies(Model model, HttpSession session) {
	     RestTemplate restTemplate = new RestTemplate();
	     ResponseEntity<Vacancies[]> responseEntity = restTemplate.getForEntity("http://localhost:8091/CareerCrafters1/api/v1/viewVacancies", Vacancies[].class);

	     Vacancies[] responseBody = responseEntity.getBody();
	     List<Vacancies> vacancyList = Arrays.asList(responseBody);
	     model.addAttribute("vacancyList", vacancyList);

	     // Check if company object is null
	     Company company = (Company) session.getAttribute("company");
	     if (company != null) {
	         int cid = company.getCompanyId();
	     }

	     ModelAndView view = new ModelAndView("viewvacancies");
	     return view;
	 }

	

}
