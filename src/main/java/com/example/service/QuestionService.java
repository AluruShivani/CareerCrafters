package com.example.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Question;

@Service
public interface QuestionService {
	Question Savequestion(Question question);
	List<Question> getAllQuestions();
	boolean isQuestionExit(int questionId);
	Question getQuestionById(int questionId);
	boolean updateQuestion(Question question);
	boolean deleteQuestion(int questionId);
	List<Question> getQuestionsByTestId(int testId);
    List<Question> getQuestionsByVacancyId(int vacancyId);
    List<Question> getQuestionsByCompanyId(int companyId);
    }

