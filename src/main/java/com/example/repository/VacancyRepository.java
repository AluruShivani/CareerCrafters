package com.example.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Vacancies;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancies, Integer> {
	 @Query("SELECT v FROM Vacancies v WHERE v.company.id = :companyId")
	    List<Vacancies> findByCompanyId(@Param("companyId") int companyId);
	
}
