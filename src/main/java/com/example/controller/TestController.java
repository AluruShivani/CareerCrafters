package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Test;
import com.example.service.TestService;

@RestController
@RequestMapping("/api/v1")
public class TestController {
	@Autowired
	TestService service;
	
	@PostMapping("/addTest")
	public ResponseEntity<Object> addTest(@RequestBody Test test){
		service.SaveTest(test);
		return new ResponseEntity<>("test added successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/viewTests")
	public ResponseEntity<Object> viewAllTest(){
		List<Test> test=service.getAllTests();
		ResponseEntity<Object> entity=new ResponseEntity<>(test,HttpStatus.OK);
		return entity;
		
	}
	
	@GetMapping("/getTest/{testId}")
	public ResponseEntity<Object> getTest(@PathVariable("testId")int testId){
		Test test;
		if(service.isTestExit(testId)) {
			test=service.getTestById(testId);
		}else {
			test=null;
		}
		ResponseEntity<Object> entity=new ResponseEntity<>(test,HttpStatus.OK);
		return entity;
	}

	@PutMapping(value="/updateTest/{testId}")
	public ResponseEntity<Object> updateTest(@PathVariable("testId")int testId,@RequestBody Test test){
		Test test1;
		boolean flag;
		if(service.isTestExit(testId)) {
			flag=service.updateTest(test);
	}else {
		flag=false;
	}
		return new ResponseEntity<>(flag,HttpStatus.OK);
}
	@GetMapping("/testsByVacancyIds")
	public ResponseEntity<Object> getTestsByVacancyIds(@RequestParam String vacancyIds) {
    	String ids[] = vacancyIds.split(",");
    	
    	List<Integer> vacList = new ArrayList<Integer>();
    	for(String id : ids) {
    		if(id.length()>0) {
    			vacList.add(Integer.parseInt(id));
    		}
    	}
    	
    	
    	
	    List<Test> tests = service.getTestsByVacancyIdIn(vacList); // Changed method name to match
	    return ResponseEntity.ok(tests);
	}

	@GetMapping("/findTestByVacancyId/{vacancyId}")
    public ResponseEntity<Object> getTestByVacancyId(@PathVariable int vacancyId) 
	{
        Test test;
        test= service.findTestByVacancyId(vacancyId);
        ResponseEntity<Object> entity = new ResponseEntity<>(test, HttpStatus.OK);
		return entity;
        
    }
	 


}
