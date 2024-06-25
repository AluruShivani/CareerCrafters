package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Certificates;

@Service
public interface CertificateService {
	Certificates saveCertificates(Certificates certificates);
	List<Certificates> getAllCertificates();
	boolean isCertificatesExits(int certificateId);
	Certificates getCertificateById(int certificateId);
	boolean updateCertificates(Certificates certificates);
	  boolean deleteDocument(int certificateId);
	    List<Certificates> getDocumentsByUserId(int userId);
	    boolean isDocumentExist(int certificateId);



}
