package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.SelectedCandidates;
import com.example.service.SelectedCandidatesService;

@RestController
@RequestMapping("/api/v1")
public class SelectedCandidatesController {
	@Autowired
	SelectedCandidatesService service;
	
	@PostMapping("/addSelectedCandidates")
	public ResponseEntity<Object> addSelectedCandidates(@RequestBody SelectedCandidates selecetdcandidtaes){
		service.saveSelectedCandidates(selecetdcandidtaes);
		return new ResponseEntity<>("candidate selected",HttpStatus.CREATED);
	}
	
	@GetMapping("/viewsallSelectedCandidates")
	public ResponseEntity<Object> viewAllselectedCnadidtaes(){
		List<SelectedCandidates> selectedcandidates=service.getAllSelectedCandidates();
		ResponseEntity<Object> entity=new ResponseEntity<>(selectedcandidates,HttpStatus.OK);
		return entity;
	}
	
	

}
