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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.JobApply;
import com.example.demo.entity.JobSeekers;
import com.example.demo.entity.Questions;
import com.example.demo.entity.TestResult;
import com.example.demo.entity.Vacancies;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class TestResultController {
	
	
	 @GetMapping("/takeexam/{vacancyId}")
		public ModelAndView takeExam(Model model, @PathVariable("vacancyId") int vacancyId,HttpSession session) {
			Questions question = new Questions();
			System.out.println("oooo vacancyId" + vacancyId);
			Vacancies vacancy1 = new Vacancies();
			vacancy1.setVacancyId(vacancyId);

			question.setVacancies(vacancy1);

			model.addAttribute("question", question);
			System.out.println("oooo " + question);

			//Adding testId and vacancyId to the model
			//model.addAttribute("testId", testId);
			model.addAttribute("vacancyId", vacancyId);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Questions[]> responseEntity = restTemplate
					.getForEntity("http://localhost:8091/CareerCrafters1/api/v1/allQuestions/" + vacancyId, Questions[].class);

			Questions[] responseBody = responseEntity.getBody();
			List<Questions> quesList = Arrays.asList(responseBody);
			System.out.println("quesList" + quesList);
			System.out.println("quesList" + quesList.size());
			
			 // Set questionsList attribute in session
		    session.setAttribute("questionsList", quesList);
		   
			
			model.addAttribute("quesList", quesList);
			ModelAndView view = new ModelAndView("/takeexam");
			return view;
		}

		/////////////////////////////////////////////////////////////////////////////////////////
		////////////////////after selecting options and clicking on submit exam for that the code 
		/////////////////////////////////////////////////////////////////////////////////////////////

		@PostMapping("/submitExam")
		public String submitExam(HttpServletRequest request, HttpSession session) {
	///////////////////////////////////////////////////////////////////////////////
			JobSeekers jobSeeker = (JobSeekers) session.getAttribute("jobseekers");
			int userId = jobSeeker.getUserId();
			System.err.println("User ID: " + userId);

			////////////////////////////////////////////////////////////////////////////////
			List<Questions> questionsList = (List<Questions>) session.getAttribute("questionsList");
			int totalScore = 0;

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			for (Questions quest : questionsList) {
				int questId = quest.getQuestionId();
				////////////////////////////////////////////////////////////////////////
				Vacancies  vacancy = quest.getVacancies();
				/////////////////////////////////////
				System.err.println("vacancyId ===" + vacancy.getVacancyId());

				String selectionOption = request.getParameter("selectedOptions" + questId);
				String correctOption = quest.getCorrect();
				System.out.println("questId: " + questId + ", selectionOption: " + selectionOption + ", correctOption: "
						+ correctOption);

				String score = "0";
				String result = "incorrect";

				if (selectionOption != null && selectionOption.equals(correctOption)) {
					result = "correct";
					score = quest.getScore();
					totalScore += Integer.parseInt(quest.getScore());

					// Add score to totalScore

				}

				TestResult testResult = new TestResult();
				testResult.setJobseekers(jobSeeker);
				testResult.setQuestion(quest);
				testResult.setVacancies(vacancy);
				testResult.setSelectionOption(selectionOption);
				testResult.setResult(result);

				// Assuming you calculate score somewhere else and set it here
				testResult.setScore(score);
				////////////////////////////////////////////////////////////////////////////////////
				// Save the test result
				// setting test result object to end point
				HttpEntity<TestResult> entity = new HttpEntity<TestResult>(testResult, headers);

				String url = "http://localhost:8091/CareerCrafters1/api/v1/addTestResult";
				ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

				String responseString = response.getBody();
				System.out.println("responseString" + responseString);

				/////////////////////////////////////////////////////////////////

				String status;
				if (totalScore >= 75) {
					status = "qualified";
				} else {
					status = "better luck next time";
				}

				System.out.println("totalscore" + totalScore);

				JobApply jobapply = new JobApply();
	
				jobapply.setStatus(String.valueOf(totalScore));

				HttpEntity<JobApply> entity1 = new HttpEntity<JobApply>(jobapply, headers);

				String url1 = "http://localhost:8091/CareerCrafters1/api/v1/updateStatus/" + userId + "/" + vacancy.getVacancyId() + "/"
						+ status + "/" + totalScore;
				ResponseEntity<String> response1 = restTemplate.exchange(url1, HttpMethod.PUT, entity, String.class);

				String responseString1 = response.getBody();
				System.out.println("responseString" + responseString);

			}

			return "redirect:/userhome";
		}

	
	
//     @PostMapping("/takeexam")
//	    public String takeExam(HttpServletRequest request, HttpSession session) {
//		 int vacancyId = (int) session.getAttribute("vacancyId");
//	        // Retrieve questionsList from the session
//	        List<Questions> questionsList = (List<Questions>) session.getAttribute("questionsList");
//	        // Check if questionsList is null or empty
//	        if (questionsList == null || questionsList.isEmpty()) {
//	            // Handle the case where questionsList is null or empty
//	            // You can redirect to an error page or return an error message
//	            System.out.println("No questions found in the session.");
//	            return "error";
//	        }
//	        // Create a list to store valid test results
//	        List<TestResult> validTestResults = new ArrayList<>();
//	        // Create variables to store the score and total questions
//	        int totalQuestions = 0;
//	        int correctCount = 0; // Initialize correct count
//	        // Iterate through each question in the questionsList
//	        for (Questions question : questionsList) {
//	            // Retrieve the selected option for each question
//	            String selectedOption = request.getParameter("selectedOption_" + question.getQuestionId());
//	            // Retrieve the correct option for the question
//	            String correctOption = question.getCorrect();
//	            System.out.println("Question ID: " + question.getQuestionId() +
//	                    ", Selected Option: " + selectedOption + ", Correct Option: " + correctOption);
//	            // Check if the selected option is correct
//	            boolean isCorrect = selectedOption != null && selectedOption.equals(correctOption);
//	            if (isCorrect) {
//	                // Increase correct count if the selected option is correct
//	                correctCount++;
//	            }
//	            // Create a test result object for the current question
//	            TestResult testResult = new TestResult();
//	            JobSeekers job = (JobSeekers) session.getAttribute("jobseekers");
//	            
//	            testResult.setJobseekers(job); // Set the jobseeker
//	            testResult.setQuestion(question); // Set the question
//	            testResult.setSelectionOption(selectedOption); // Set the selected option
//	            // Set the score for the current question
//	            int score = isCorrect ? 1 : 0;
//	            testResult.setScore(score);
//	            // You can set other properties of testResult as per your requirements
//	            // Add the test result to the list of valid test results
//	            validTestResults.add(testResult);
//	            // Increment total questions count
//	            totalQuestions++;
//	        }
//	        // Calculate percentage score
//	        double percentage = ((double) correctCount / totalQuestions) * 100;
//	        // Set the result to "pass" if the percentage score is greater than or equal to 70, otherwise set it to "fail"
//	        String result = percentage >= 70 ? "pass" : "fail";
//	        // Set the result for each valid test result
//	        validTestResults.forEach(testResult -> testResult.setResult(result));
//	        // Display score and result
//	        System.out.println("Total Questions: " + totalQuestions);
//	        System.out.println("Correct Answers: " + correctCount);
//	        System.out.println("Percentage Score: " + percentage + "%");
//	        System.out.println("Result: " + result);
//	        // Assuming you have a RestTemplate bean autowired
//	        RestTemplate restTemplate = new RestTemplate();
//	        // Make a POST request to save the valid test results only
//	        ResponseEntity<String> response = restTemplate.postForEntity(
//	                "http://localhost:8090/api/v1/addTestresult",
//	                validTestResults, // Send only the valid test results
//	                String.class);
//	        // Check the response status code and handle accordingly
//	        if (response.getStatusCode() == HttpStatus.CREATED) {
//	            System.out.println("Test results saved successfully.");
//	            // Redirect or return appropriate response
//	            return "redirect:userhome"; // Redirect to a page showing the exam result
//	        } else {
//	            System.out.println("Failed to save test results.");
//	            // Handle the case where test results are not saved successfully
//	            // You can redirect to an error page or return an error message
//	            return "error";
//	        }
//
//	 
//	 }
//	 
//	 

}
