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

import com.example.entity.Vacancies;

import com.example.service.VacancyService;

@RestController
@RequestMapping("/api/v1")
public class VacancyController {

	@Autowired
	VacancyService service;

	 @PostMapping("/addVacancy")
	 public ResponseEntity<Object> addVacancies(@RequestBody Vacancies vacancies) {
		 service.saveVacancies(vacancies); // Call the method on the service instance
	     return new ResponseEntity<>("Vacancy added Successfully", HttpStatus.CREATED);
	 }
	
	@GetMapping("/viewVacancies")
	public ResponseEntity<Object> getAllVacancies(){
		List<Vacancies> vacancies=service.getAllVacancies();
		ResponseEntity<Object> entity=new ResponseEntity<>(vacancies,HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/getVacancies/{vacancyId}")
	public ResponseEntity<Object> getVacancy(@PathVariable("vacancyId")int vacancyId){
		Vacancies vacancies;
		if(service.isVancancyExit(vacancyId)) {
			vacancies=service.getVacancyById(vacancyId);
	}else {
		vacancies=null;
	}
		ResponseEntity<Object> entity=new ResponseEntity<>(vacancies,HttpStatus.OK);
		return entity;
}
	@DeleteMapping("/deleteVacancy{vacancyId}")
	public ResponseEntity<Object> deleteVacancy(@PathVariable("vacancyId")int vacancyId){
		boolean flag;
		if(service.isVancancyExit(vacancyId)) {
			flag=service.deleteVacancy(vacancyId);
		}else {
			flag=false;
		}
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}
	
	@PutMapping(value="/updateVacancy/{vacancyId}")
	ResponseEntity<Object> updateVacancy(@PathVariable("vacancyId")int vacancyId,@RequestBody Vacancies vacancies){
		boolean flag;
		if(service.isVancancyExit(vacancyId)) {
			flag=service.updateVacancy(vacancies);
		}else {
			flag=false;
		}
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}
	
	@GetMapping("/getVacanciesByCompanyId/{companyId}")
    public List<Vacancies> getVacanciesByCompanyId(@PathVariable("companyId") int companyId) {
        return service.getVacanciesByCompanyId(companyId);
 }

	 
	 
}
