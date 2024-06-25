package com.example.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Question;
import com.example.repository.QuestionRepository;
import com.example.service.QuestionService;

@Service
public class QuestionImpl implements QuestionService{
	public final QuestionRepository questionrepository;
	
	@Autowired
	public QuestionImpl(QuestionRepository questionrepository) {
	this.questionrepository=questionrepository;
	}

	@Override
	public Question Savequestion(Question question) {
		return questionrepository.save(question);
	}

	@Override
	public List<Question> getAllQuestions() {
		return questionrepository.findAll();
	}

	@Override
	public boolean isQuestionExit(int questionId) {
		return questionrepository.existsById(questionId);
	}

	@Override
	public Question getQuestionById(int questionId) {
		return questionrepository.findById(questionId).orElse(null);
	}

	@Override
	public boolean updateQuestion(Question question) {
		if(isQuestionExit(question.getQuestionId())) {
			questionrepository.save(question);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteQuestion(int questionId) {
		if(questionrepository.existsById(questionId)) {
			questionrepository.deleteById(questionId);
			return true;
		}
		return false;
	}

	@Override
	public List<Question> getQuestionsByTestId(int testId) {
		return questionrepository.findByTestId(testId);
	}

	@Override
	public List<Question> getQuestionsByVacancyId(int vacancyId) {
		return questionrepository.findByVacancyId(vacancyId);
	}

	@Override
	public List<Question> getQuestionsByCompanyId(int companyId) {
		return questionrepository.findByCompanyId(companyId);
	}

	
	

	

	
}
