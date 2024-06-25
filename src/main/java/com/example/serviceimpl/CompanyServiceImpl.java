package com.example.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Company;

import com.example.repository.CompanyRepository;

import com.example.service.CompanyInterface;

@Service
public class CompanyServiceImpl implements CompanyInterface {
	private final CompanyRepository companyrepository;
	
	@Autowired
	public CompanyServiceImpl(CompanyRepository companyrepository) {
		this.companyrepository=companyrepository;
	}

	@Override
	public Company saveCompany(Company company) {
		return companyrepository.save(company);
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> companyList=companyrepository.findAll();
		return companyList;
	}

	@Override
	public boolean isCompanyExist(int companyId) {
		return companyrepository.existsById(companyId);
	}

	@Override
	public Company getCompanyById(int companyId) {
		return companyrepository.findById(companyId).orElse(null);
	}

	@Override
	public boolean updateCompany(Company company) {
		if (isCompanyExist(company.getCompanyId())) {
			companyrepository.save(company);
            return true;
        }

		return false;
	}

	

	@Override
	public boolean deleteCompany(int companyId) {
		 if (companyrepository.existsById(companyId)) { // Check if company with given ID exists
			 companyrepository.deleteById(companyId); // Delete company by ID
	            return true;
	        }
		return false;

	}

	@Override
	public Company loginValidate(Company company) {
	    Company loggedInCompany = companyrepository.findByCompanyEmailAndPassword(company.getCompanyEmail(), company.getPassword());
	    return loggedInCompany; // Return the Company object obtained from the repository
	}

	@Override
	public Company getVacancyById(int companyId) {
		// TODO Auto-generated method stub
		return companyrepository.findById(companyId).orElse(null);
	}

	

	
	
	}
	
