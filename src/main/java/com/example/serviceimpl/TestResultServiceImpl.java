package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.TestResult;
import com.example.repository.TestResultRepository;
import com.example.service.TestResultService;

@Service
public class TestResultServiceImpl implements TestResultService{
	public final TestResultRepository testresultrepository;
	
	@Autowired
	public TestResultServiceImpl(TestResultRepository testresultrepository) {
		this.testresultrepository=testresultrepository;
	}

	@Override
	public TestResult saveTestResult(TestResult testresult) {
		return testresultrepository.save(testresult);
	}

	@Override
	public List<TestResult> getAllTestResults() {
		return testresultrepository.findAll();
	}

	@Override
	public boolean isTestResultExit(int resultId) {
		return testresultrepository.existsById(resultId);
	}

	@Override
	public boolean deleteTestResult(int resultId) {
		if(testresultrepository.existsById(resultId)){
			testresultrepository.deleteById(resultId);
			return true;
		}
		return false;
	}

	@Override
	public TestResult getTestresultById(int resultId) {
		return testresultrepository.findById(resultId).orElse(null);
	}

	@Override
	public boolean updateTestResult(TestResult testresult) {
		if(isTestResultExit(testresult.getResultId())) {
			testresultrepository.save(testresult);
			return true;
		}
		return false;
	}

	@Override
    public List<TestResult> getTestResultsByVacancyId(int vacancyId) {
        return testresultrepository.findByVacancies_VacancyId(vacancyId);
    }

	

}
