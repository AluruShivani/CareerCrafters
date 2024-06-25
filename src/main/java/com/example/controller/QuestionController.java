package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Question;
import com.example.service.QuestionService;

@RestController
@RequestMapping("/api/v1")
public class QuestionController {
	@Autowired
	QuestionService service;
	
	@PostMapping("/addQuestion")
	public ResponseEntity<Object> addQuestion(@RequestBody Question question){
		service.Savequestion(question);
		return new ResponseEntity<>("question added successfully",HttpStatus.CREATED);
	}
	
	@GetMapping("/viewQuestions")
	public ResponseEntity<Object> viewQuestions(){
		List<Question> question=service.getAllQuestions();
		ResponseEntity<Object> entity=new ResponseEntity<>(question,HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/getQuestion/{questionId}")
	public ResponseEntity<Object> getQuestiton(@PathVariable("questionId") int questionId){
		Question question;
		if(service.isQuestionExit(questionId)) {
			question=service.getQuestionById(questionId);
		}else {
			question=null;
		}
		ResponseEntity<Object> entity=new ResponseEntity<>(question,HttpStatus.OK);
		return entity;
	}
	
	@DeleteMapping("/deleteQuestion/{questionId}")
	public ResponseEntity<Object> deleteQuestion(@PathVariable("questionId") int questionId){
		boolean flag;
		if(service.isQuestionExit(questionId)) {
			flag=service.deleteQuestion(questionId);
		}else {
			flag=false;
		}
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}
	
	@PutMapping(value="/updateQuestion/{questionId}")
	public ResponseEntity<Object> updateQuestion(@PathVariable("questionId") int questionId,@RequestBody Question question){
		Question question1;
		boolean flag;
		if(service.isQuestionExit(questionId)) {
			flag=service.updateQuestion(question);
		}else {
			flag=false;
		}
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}
	
	 @GetMapping("/getQuestionsByCompanyId")
	    public ResponseEntity<Object> getQuestionsByCompanyId(@RequestParam int companyId) {
	        List<Question> questions = service.getQuestionsByCompanyId(companyId);
	        return new ResponseEntity<>(questions, HttpStatus.OK);
	    }
	 
	 @GetMapping("/allQuestions/{vacancyId}")
	    public ResponseEntity<List<Question>> getAllQuestionsByVacancyId(@PathVariable int vacancyId) {
	        List<Question> questions = service.getQuestionsByVacancyId(vacancyId);
	        return ResponseEntity.ok(questions);
	    }
	 
	 

}
