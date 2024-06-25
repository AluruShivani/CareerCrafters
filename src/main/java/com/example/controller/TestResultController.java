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

import com.example.entity.TestResult;
import com.example.service.TestResultService;

@RestController
@RequestMapping("/api/v1")
public class TestResultController {
	@Autowired
	TestResultService service;
	
	@PostMapping(value = "/addTestResult")
    public ResponseEntity<Object> addTestResult(@RequestBody TestResult testResult) {
		service.saveTestResult(testResult);
        return new ResponseEntity<>("Test result added successfully", HttpStatus.CREATED);
    }

	
	@GetMapping("/viewTestResults")
	public ResponseEntity<Object> viewTestResults(){
		List<TestResult> testresult=service.getAllTestResults();
		ResponseEntity<Object> entity=new ResponseEntity<>(testresult,HttpStatus.OK);
		return entity;
	}

	@GetMapping("/getTestResult/{resultId}")
	public ResponseEntity<Object> getTestResult(@PathVariable("resultId")int resultId){
		TestResult testresult;
		if(service.isTestResultExit(resultId)) {
			testresult=service.getTestresultById(resultId);
		}else {
			testresult=null;
		}
		ResponseEntity<Object>entity=new ResponseEntity<>(testresult,HttpStatus.OK);
		return entity;
	}
	
	@DeleteMapping("/deleteTestResult/{resultId}")
	public ResponseEntity<Object> deleteTestResult(@PathVariable("resultId")int resultId){
		boolean flag;
		if(service.isTestResultExit(resultId)) {
			flag=service.deleteTestResult(resultId);
		}else {
			flag=false;
		}
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}
	
	@PutMapping("/updateTestResult{resultId}")
	public ResponseEntity<Object> updateTestresult(@PathVariable("resultId") int resultId,@RequestBody TestResult testresult){
		TestResult testresult1;
		boolean flag;
		if(service.isTestResultExit(resultId)) {
			flag=service.updateTestResult(testresult);
		}else {
			flag=false;
		}
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}
	
	 @GetMapping("/testResults/{vacancyId}")
	    public List<TestResult> getTestResultsByVacancyId(@PathVariable int vacancyId) {
	        return service.getTestResultsByVacancyId(vacancyId);
	    }
}
