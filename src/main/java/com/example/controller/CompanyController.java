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

import com.example.entity.Company;
import com.example.entity.Vacancies;
import com.example.service.CompanyInterface;

@RestController
@RequestMapping("/api/v1")

public class CompanyController {
	@Autowired
	CompanyInterface service;

	@PostMapping(value = "/addCompany")
	public ResponseEntity<Object> addCompany(@RequestBody Company company) {

		service.saveCompany(company);
		return new ResponseEntity<>("Company added successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/viewCompanies")
	public ResponseEntity<Object> getAllComapies(){
		List<Company> company=service.getAllCompanies();
		ResponseEntity<Object> entity=new ResponseEntity<>(company,HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/getCompany/{companyId}")
	public ResponseEntity<Object> getCompany(@PathVariable("companyId")int companyId){
		Company company;
		if(service.isCompanyExist(companyId)) {
			company=service.getCompanyById(companyId);
		}else {
			company=null;
		}ResponseEntity<Object>entity=new ResponseEntity<>(company,HttpStatus.OK);
		return entity;
	}

	@DeleteMapping("/deleteComapny/{companyId}")
	public ResponseEntity<Object> deleteCompany(@PathVariable("companyId")int comapnyId){
		boolean flag;
		if(service.isCompanyExist(comapnyId)) {
			flag=service.deleteCompany(comapnyId);
		}else {
			flag=false;
		}return new ResponseEntity<>(flag,HttpStatus.OK);
	}
	
	@PutMapping(value="/updateCompany/{companyId}")
	public ResponseEntity<Object> updateCompany(@PathVariable("companyId") int companyId,@RequestBody Company company){
		Company compny;
		boolean flag;
		if(service.isCompanyExist(companyId)) {
			
				flag=service.updateCompany(company);
			}else {
				flag=false;
			}
			return new ResponseEntity<>(flag,HttpStatus.OK);
		}

	@PostMapping(value="/companyLogin")
	public ResponseEntity<Object> companyLogin(@RequestBody Company company){
	    Company loggedInCompany = service.loginValidate(company);
	    if (loggedInCompany != null) {
	        // If login is successful, return the Company object
	        return new ResponseEntity<>(loggedInCompany, HttpStatus.OK);
	    } else {
	        // If login fails, return an appropriate response
	        return new ResponseEntity<>("Email/Password incorrect...", HttpStatus.UNAUTHORIZED);
	    }
	}
	
	@GetMapping("/getVacancy/{companyId}")
	public ResponseEntity<Object> getVacancy(@PathVariable("companyId")int companyId){
		Company company;
		if(service.isCompanyExist(companyId)) {
			company=service.getCompanyById(companyId);
		}else {
			company=null;
		}ResponseEntity<Object>entity=new ResponseEntity<>(company,HttpStatus.OK);
		return entity;
	}

	
	}










