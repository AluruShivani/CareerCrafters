package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer>{
	 List<Test> findByVacancy_VacancyId(int vacancyId);
	 List<Test> findByVacancy_VacancyIdIn(List<Integer> vacancyIds);
	 @Query("SELECT t FROM Test t WHERE t.vacancy.vacancyId = :vacancyId")
	    Test findTestByVacancyId(int vacancyId);



}
