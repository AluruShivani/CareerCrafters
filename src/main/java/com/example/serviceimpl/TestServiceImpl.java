package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Test;
import com.example.repository.TestRepository;
import com.example.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	public final TestRepository testrepository;
	
	@Autowired
	public TestServiceImpl(TestRepository testrepository) {
		this.testrepository=testrepository;
	}

	@Override
	public Test SaveTest(Test test) {
		return testrepository.save(test);
	}

	@Override
	public List<Test> getAllTests() {
		return testrepository.findAll();
	}

	@Override
	public boolean isTestExit(int testId) {
		return testrepository.existsById(testId);
	}

	@Override
	public boolean updateTest(Test test) {
		if(isTestExit(test.getTestId())){
			testrepository.save(test);
			return true;
		}
		return false;
	}

	@Override
	public Test getTestById(int testId) {
		return testrepository.findById(testId).orElse(null);
	}

	@Override
	public List<Test> getTestsByVacancy_VacancyId(int vacancyId) {
		// TODO Auto-generated method stub
		 return testrepository.findByVacancy_VacancyId(vacancyId);

	}

	@Override
	public List<Test> getTestsByVacancyIdIn(List<Integer> vacancyIds) {
		// TODO Auto-generated method stub
		 return testrepository.findByVacancy_VacancyIdIn(vacancyIds);

	}

	@Override
	public Test findTestByVacancyId(int vacancyId)
	{
		return testrepository.findTestByVacancyId(vacancyId);
		
	}

	
	


}
