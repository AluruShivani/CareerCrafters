package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Certificates;
import com.example.repository.CertificateRepository;
import com.example.service.CertificateService;

@Service
public class CertificateServiceImpl implements CertificateService{
	public final CertificateRepository certificaterepository;
	
	@Autowired
	public CertificateServiceImpl(CertificateRepository certificaterepository) {
		this.certificaterepository=certificaterepository;
	}

	@Override
	public Certificates saveCertificates(Certificates certificates) {
		return certificaterepository.save(certificates);
	}

	@Override
	public List<Certificates> getAllCertificates() {
		return certificaterepository.findAll();
	}

	@Override
	public boolean isCertificatesExits(int certificateId) {
		return certificaterepository.existsById(certificateId);
	}

	@Override
	public Certificates getCertificateById(int certificateId) {
		return certificaterepository.findById(certificateId).orElse(null);
	}

	@Override
	public boolean updateCertificates(Certificates certificates) {
		if(isCertificatesExits(certificates.getCertificateId())) {
			certificaterepository.save(certificates);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteDocument(int certificateId) {
		  if (isDocumentExist(certificateId)) {
			  certificaterepository.deleteById((int) certificateId);
	            return true;
	        }
	        return false;
	    }


	@Override
	public List<Certificates> getDocumentsByUserId(int userId) {
		List<Certificates> doclist=certificaterepository.findByJobseekersUserId(userId);
		return doclist;
	}


	@Override
	public boolean isDocumentExist(int certificateId) {
		return certificaterepository.existsById(certificateId);

	}

}
