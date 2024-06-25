package com.example.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Question;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	@Query("SELECT q FROM Question q WHERE q.test.testId = :testId")
    List<Question> findByTestId(@Param("testId") int testId);
	
	 @Query("SELECT q FROM Question q JOIN q.vacancies v WHERE v.company.companyId = :companyId")
	    List<Question> findByCompanyId(@Param("companyId") int companyId);
	 
	 @Query("SELECT q FROM Question q WHERE q.vacancies.vacancyId = :vacancyId")
	    List<Question> findByVacancyId(@Param("vacancyId") int vacancyId);


}
