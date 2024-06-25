package com.example.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByCompanyEmailAndPassword(String companyEmail, String password);
   
    
}
