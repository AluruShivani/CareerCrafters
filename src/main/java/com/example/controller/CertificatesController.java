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

import com.example.entity.Certificates;
import com.example.service.CertificateService;

@RestController
@RequestMapping("/api/v1")
public class CertificatesController {
	@Autowired
	CertificateService service;
	
	@PostMapping("/addCertificates")
	public ResponseEntity<Object> addCertificates(@RequestBody Certificates certificates){
		service.saveCertificates(certificates);
		return new ResponseEntity<>("certificates are added successfully",HttpStatus.CREATED);
	}

	@GetMapping("/viewCertificates")
	public ResponseEntity<Object> viewCertificates(){
		List<Certificates> certificates=service.getAllCertificates();
		ResponseEntity<Object> entity=new ResponseEntity<>(certificates,HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/getCertificates/{certificateId}")
	public ResponseEntity<Object> getCertificates(@PathVariable("certificateId")int certificateId){
		Certificates certificates;
		if(service.isCertificatesExits(certificateId)) {
			certificates=service.getCertificateById(certificateId);
		}else {
			certificates=null;
		}
		ResponseEntity<Object>entity=new ResponseEntity<>(certificates,HttpStatus.OK);
		return entity;
	}
	
	@PutMapping(value="/updateCertificates/{certificateId}")
	public ResponseEntity<Object> updateCertificates(@PathVariable("certificateId")int certificateId,@RequestBody Certificates certificates){
		Certificates certificates1;
		boolean flag;
		if(service.isCertificatesExits(certificateId)) {
			flag=service.updateCertificates(certificates);
		}else {
			flag=false;
		}
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}
	
	@GetMapping("/getDocuments/{userId}")
	public ResponseEntity<Object> getMethodName(@PathVariable("userId") int userId) {
		List<Certificates> company = service.getDocumentsByUserId(userId);
		ResponseEntity<Object> entity = new ResponseEntity<>(company, HttpStatus.OK);
		return entity;
	}
	
	@DeleteMapping("/deleteDocument/{certificateId}")
	public ResponseEntity<Object> deleteDocument(@PathVariable("certificateId") int certificateId) {
		System.out.println("Deleting Document with Id: " + certificateId);

		boolean deleted = service.deleteDocument(certificateId);
		if (deleted) {
			System.out.println("Document deleted successfully");
			return new ResponseEntity<>("Document deleted successfully", HttpStatus.OK);
		} else {
			System.out.println("Document not found for Id: " + certificateId);
			return new ResponseEntity<>("Document not found", HttpStatus.NOT_FOUND);
		}
	}


}
