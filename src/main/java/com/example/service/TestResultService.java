package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.TestResult;

@Service
public interface TestResultService {
	TestResult saveTestResult(TestResult testresult);
	List<TestResult> getAllTestResults();
	boolean isTestResultExit(int resultId);
	boolean deleteTestResult(int resultId);
	TestResult getTestresultById(int resultId);
	boolean updateTestResult(TestResult testresult);
	List<TestResult> getTestResultsByVacancyId(int vacancyId);
}
