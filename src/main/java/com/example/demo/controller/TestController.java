package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Company;

import com.example.demo.entity.Test;
import com.example.demo.entity.Vacancies;

import jakarta.servlet.http.HttpSession;


@Controller
public class TestController {
	@GetMapping("/addTest")
	public ModelAndView addTest(@RequestParam("vacancyId") int vacancyId, Model model) {
	    System.out.println("Received vacancyId: " + vacancyId);
	    model.addAttribute("vacancyId", vacancyId);
	    Test test = new Test(); // Assuming Test is your model class
	    // Populate the test object if necessary
	    model.addAttribute("test", test);
	    // Return the model and view
	    ModelAndView modelAndView = new ModelAndView("/addTest");
	    modelAndView.addObject("vacancyId", vacancyId);
	    return modelAndView;
	}



    @PostMapping("/saveTest")
	  public ModelAndView addingTest(@ModelAttribute("test") Test test, Model model,HttpSession session) {
		
		//getting vacancies object
		 RestTemplate restTemplate = new RestTemplate();
		   String url = "http://localhost:8091/CareerCrafters1/api/v1/getVacancies/" + test.getVacancyId();
		   
		   ResponseEntity<Vacancies> response = restTemplate.getForEntity(url, Vacancies.class);
		   Vacancies vacancyObject = response.getBody();
		test.setVacancy(vacancyObject);
		
		
		////////////////
		
		
		 System.out.println("Test object: " + test);
	 HttpHeaders headers = new HttpHeaders();
   headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
   HttpEntity<Test> entity = new HttpEntity<Test>(test, headers);
   
    restTemplate = new RestTemplate();
    url = "http://localhost:8091/CareerCrafters1/api/v1/addTest";
   
   ResponseEntity<String> response1 = restTemplate.exchange(url, HttpMethod.POST, entity,String.class);
   Object responseString = response1.getBody();
   
   session.setAttribute("vacancyId",vacancyObject.getVacancyId());
   
   model.addAttribute("successMessage", "Test added successfully!");
   test = new Test();
   model.addAttribute("test", test);
   
   ModelAndView view = new ModelAndView("/addTest");
   model.addAttribute("responseString", responseString);

   return view;
	
   
	 
}
    
    @GetMapping("/viewTest")
    public ModelAndView viewTests(Model model, HttpSession session) {
        int companyId = ((Company) session.getAttribute("company")).getCompanyId();
        
        
        //get vacancies by company id
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Vacancies[]> responseEntity = restTemplate.getForEntity(
                "http://localhost:8091/CareerCrafters1/api/v1/getVacanciesByCompanyId/" + companyId,
                Vacancies[].class);

        Vacancies[] responseBody = responseEntity.getBody();
        List<Vacancies> vacancyList = Arrays.asList(responseBody);
        
        String vacancyIds ="";
        for(Vacancies vac: vacancyList) {
        	vacancyIds+=vac.getVacancyId()+",";
        }
        
        ////////////

         restTemplate = new RestTemplate();
        ResponseEntity<Test[]> responseEntity1 = restTemplate.getForEntity(
                "http://localhost:8091/CareerCrafters1/api/v1/testsByVacancyIds?vacancyIds=" + vacancyIds,
                Test[].class);

        Test[] responseBody1 = responseEntity1.getBody();
        List<Test> testList = Arrays.asList(responseBody1);
          System.out.println("Test List=" + testList);
        model.addAttribute("testList", testList);
        //model.addAttribute("vacanciesList", vacanciesList);
        ModelAndView view = new ModelAndView("/viewTest");
        return view;
    }
    
    @GetMapping("/updateTest/{testId}")
	public ModelAndView updateTest(@PathVariable("testId") int testId, Model model,HttpSession session) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Test> response = restTemplate.getForEntity("http://localhost:8091/CareerCrafters1/api/v1/getTest/" + testId,
				Test.class);
		Test test = response.getBody();
		session.setAttribute("test", test);

		ModelAndView view = new ModelAndView("updateTest");
		view.addObject("test", test);
		return view;
	}

	@PostMapping("/updatedTest")
	public String updatedTest(@ModelAttribute("test") Test test, Model model, HttpSession session) {
		
		Test oldTest = (Test)session.getAttribute("test");
		oldTest.setTestDate(test.getTestDate());
		oldTest.setTestDuration(test.getTestDuration());
		
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Test> entity = new HttpEntity<>(oldTest, headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:8091/CareerCrafters1/api/v1/updateTest/" + oldTest.getTestId();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

		model.addAttribute("responseMsg", "Test updated successfully");
		return "redirect:/viewTest";
	}
    
    
}
