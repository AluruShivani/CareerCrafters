package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Test;

@Service
public interface TestService {
	Test SaveTest(Test test);
	List<Test> getAllTests();
	boolean isTestExit(int testId);
	boolean updateTest(Test test);
	Test getTestById(int testId);
	  // Method to retrieve tests by vacancy IDs
    List<Test> getTestsByVacancy_VacancyId(int vacancyId);
    List<Test> getTestsByVacancyIdIn(List<Integer> vacancyIds);
    Test findTestByVacancyId(int vacancyId);


}
