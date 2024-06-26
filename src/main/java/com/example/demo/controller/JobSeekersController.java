package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Certificates;
import com.example.demo.entity.Company;
import com.example.demo.entity.JobApply;
import com.example.demo.entity.JobSeekers;
import com.example.demo.entity.Questions;
import com.example.demo.entity.TestResult;
import com.example.demo.entity.Vacancies;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class JobSeekersController {
	 @GetMapping("/jobseekersLogin")
		public ModelAndView jobseekersLogin(Model model)
		{
			JobSeekers jobseekers = new JobSeekers();
			model.addAttribute("jobseekers", jobseekers);
			ModelAndView view = new ModelAndView("jobseekersLogin");
			return view;
			
		}
	 
	 @GetMapping("/usermenu")
		public ModelAndView usermenu() {
			ModelAndView view = new ModelAndView("usermenu");
			return view;
		}
	 
	 @GetMapping("/userhome")
		public ModelAndView userhome() {
			ModelAndView view = new ModelAndView("userhome");
			return view;
		}
	 @PostMapping("/ValidateUserLogin")
	    public ModelAndView validateJobseekersLogin(@ModelAttribute("jobSeekers") JobSeekers jobSeekers, Model model, HttpSession session) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        
	        HttpEntity<JobSeekers> entity = new HttpEntity<>(jobSeekers, headers);
	        
	        RestTemplate restTemplate = new RestTemplate();
	         
	        ResponseEntity<JobSeekers> response = restTemplate.exchange("http://localhost:8091/CareerCrafters1/api/v1/jobseekersLogin",
	                HttpMethod.POST, entity, JobSeekers.class);
	        
	        JobSeekers responseFlag = response.getBody();
	        System.out.println("responseFlag= " + responseFlag);
	        
	        if (responseFlag!=null) {
	        	
	        	//put jobseekers id in session
	        	session.setAttribute("jobseekers", responseFlag);
	            ModelAndView view = new ModelAndView("/userhome");
	            return view;
	        } else {
	        	 //model.addAttribute("jobSeekers", new JobSeekers());
	            model.addAttribute("responseText", "Email/Password incorrect...");

	            ModelAndView view = new ModelAndView("/jobseekersLogin");
	            return view;
	        }

	 }

		
		 @GetMapping("/viewjobseekers")
		 public ModelAndView jobseekersList(Model model) {
		     RestTemplate restTemplate = new RestTemplate();
		     ResponseEntity<JobSeekers[]> responseEntity = restTemplate.getForEntity("http://localhost:8091/CareerCrafters1/api/v1/viewJobSeekers",JobSeekers[].class);
		     JobSeekers[] responseBody = responseEntity.getBody();
		     List<JobSeekers> viewjobseekers = Arrays.asList(responseBody);
		     model.addAttribute("viewjobseekers", viewjobseekers);
		     ModelAndView view = new ModelAndView("viewjobseekers");
		     return view;
		 }
		 
		 @GetMapping("/newregisterjobseeker")
		    public ModelAndView newRegisterJobSeeker(Model model) {
		        JobSeekers jobseekers = new JobSeekers();
		        model.addAttribute("jobseekers", jobseekers);
		        return new ModelAndView("newregisterjobseeker");
		    }
		
		 @PostMapping("/newregisterjobseeker")
			public ModelAndView addJobseeker(@ModelAttribute("jobseekers") JobSeekers jobseeker,@RequestParam("profilePic1") MultipartFile profilePic1, Model model) 
			{
				System.out.println("In the add jobseeker method");
				
				try {
					// Create the upload directory if not exists
			        Files.createDirectories(Path.of("./temp_uploads"));
					
			        // Handle file upload for logo
					byte[] profilePicBytes = profilePic1.getBytes();
					String profilePicName = "temp_" + System.currentTimeMillis() + "_" + profilePic1.getOriginalFilename();
					Files.write(Paths.get("./temp_uploads", profilePicName), profilePicBytes);
					
					// Set the logo name in the company object
					jobseeker.setProfilePic(profilePicName);
					HttpHeaders headers = new HttpHeaders();
					headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
					HttpEntity<JobSeekers> entity = new HttpEntity<>(jobseeker, headers);
					RestTemplate restTemplate = new RestTemplate();
					ResponseEntity<String> response = restTemplate.exchange("http://localhost:8090/api/v1/addJobseekers", HttpMethod.POST,
							entity, String.class);
					String responseMsg = response.getBody();
					System.out.println("REsponse object="+responseMsg);
					
					////////////////////////code to upload product picture
					HttpHeaders picHeaders = new HttpHeaders();
					picHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
					MultiValueMap<String, Object> picMap = new LinkedMultiValueMap<>();
					picMap.add("file", new FileSystemResource("./temp_uploads/" + profilePicName));
					picMap.add("filename", profilePicName);
					HttpEntity<MultiValueMap<String, Object>> picEntity = new HttpEntity<>(picMap, picHeaders);
					
					// Send a request to upload the product picture
					String uploadPicUrl = "http://localhost:8091/CareerCrafters1/api/files/upload";
					ResponseEntity<String> picResponse = restTemplate.exchange(uploadPicUrl, HttpMethod.POST, picEntity,
							String.class);
					
					String picResponseString = picResponse.getBody();
					System.out.println("responseMsg=" + responseMsg);
					
					if (response.getStatusCode().is2xxSuccessful()) 
					{
						System.out.println("if invoked");
						ModelAndView view = new ModelAndView("jobseekersLogin");
						view.addObject("responseMsg", responseMsg);
						return view;
					}
					else
					{
						System.out.println("else invoked");
						ModelAndView view = new ModelAndView("newregisterjobseeker");
						model.addAttribute("responseText", responseMsg);
						return view;
					}
				}
				catch (Exception e)
				{
					System.out.println("Error:"+ e);
					// Handle exceptions related to file operations or HTTP requests
					ModelAndView errorView = new ModelAndView("newregisterjobseeker");
					model.addAttribute("responseText", e.getMessage());
					return errorView;
				}
			}
		 
		 
		 @GetMapping("/ViewAllVacancies")//vacancies view in admin side
		 public ModelAndView viewVacancies(Model model, HttpSession session) {
		     RestTemplate restTemplate = new RestTemplate();
		     ResponseEntity<Vacancies[]> responseEntity = restTemplate.getForEntity("http://localhost:8091/CareerCrafters1/api/v1/viewVacancies", Vacancies[].class);

		     Vacancies[] responseBody = responseEntity.getBody();
		     List<Vacancies> vacancyList = Arrays.asList(responseBody);
		     model.addAttribute("vacancyList", vacancyList);

		     // Check if company object is null
		     Company company = (Company) session.getAttribute("company");
		     if (company != null) {
		         int cid = company.getCompanyId();
		     }

		     ModelAndView view = new ModelAndView("ViewAllVacancies");
		     return view;
		 }

		 
		 @GetMapping("/JobSeekerProfile")
			public ModelAndView getProfile(Model model, HttpSession session) {
				JobSeekers jobseekers = (JobSeekers) session.getAttribute("jobseekers");
				int uid = jobseekers.getUserId();
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<JobSeekers> responseEntity = restTemplate
						.getForEntity("http://localhost:8091/CareerCrafters1/api/v1/getJobSeekers/" + uid, JobSeekers.class);
				JobSeekers jobseeker1 = responseEntity.getBody();
				//System.out.println("jobseeker1" + jobseeker1.getCompanyName());
				String imageUrl =  jobseeker1.getProfilepic();
				jobseeker1.setProfilepic(imageUrl);
				//System.out.println("company1 = " + company1.getCompanyName());
				model.addAttribute("jobseeker1", jobseeker1);
				ModelAndView view = new ModelAndView("JobSeekerProfile");
				return view;

			}
		    
		
		 @GetMapping("/jobApplicationHistory")
			public String showJobApplicationHistory(Model model, HttpSession session) {
			    // Retrieve the job seeker's ID from the session
			    JobSeekers jobseeker = (JobSeekers) session.getAttribute("jobseekers");
			    if (jobseeker == null) {
			        // If job seeker ID is not found in session, redirect to job seeker login page
			        System.out.println("Job seeker ID not found in session. Redirecting to login page.");
			        return "redirect:userhome";
			    }
			    // Retrieve all job applications for the job seeker
			    ResponseEntity<Object> response;
			    try {
					RestTemplate restTemplate = new RestTemplate();
			        response = restTemplate.getForEntity("http://localhost:8091/CareerCrafters1/api/v1/allJobApplications/" + jobseeker.getUserId(), Object.class);
			    } catch (HttpClientErrorException ex) {
			        // If the request fails, display an error message
			        model.addAttribute("errorMessage", "Failed to retrieve job application history");
			        System.out.println("Failed to retrieve job application history from the backend API.");
			        return "error"; // This is the HTML page to display error messages
			    }
			    // Check the response and handle accordingly
			    if (response.getStatusCode() == HttpStatus.OK) {
			        // If the request is successful, retrieve job application history from the response
			        List<JobApply> jobApplications = (List<JobApply>) response.getBody();
			        // Add job application history to the model
			        model.addAttribute("jobApplications", jobApplications);
			        // Return the name of the HTML page for displaying job application history
			        System.out.println("Job application history retrieved successfully."+jobApplications);
			        return "jobApplicationHistory"; // This is the HTML page where you display the job application history
			    } else {
			        // If the request fails, display an error message
			        model.addAttribute("errorMessage", "Failed to retrieve job application history");
			        System.out.println("Failed to retrieve job application history from the backend API.");
			        return "error"; // This is the HTML page to display error messages
			    }
			}
		 
		 @GetMapping("/gettakeexam")
		    public ModelAndView getTakeExam(HttpSession session, @RequestParam("vacancyId") int vacancyId) {
		        // Check if job seeker is logged in
		        JobSeekers jobseeker = (JobSeekers) session.getAttribute("jobseekers");
		        
		        if (jobseeker == null) {
		            // Redirect to login page if not logged in
		            return new ModelAndView("redirect:jobApplicationHistory");
		        }
		        int userId = jobseeker.getUserId();
		      
		        // Retrieve questions for the given vacancyId synchronously
		        RestTemplate restTemplate = new RestTemplate();
		        ResponseEntity<List<Questions>> response = restTemplate.exchange(
		                "http://localhost:8091/CareerCrafters1/api/v1/allQuestions/{vacancyId}", HttpMethod.GET, null,
		                new ParameterizedTypeReference<List<Questions>>() {}, vacancyId);
		        List<Questions> questionsList = response.getBody();
		        // Pass the userId, vacancyId, and questionsList to the view
		        ModelAndView modelAndView = new ModelAndView("takeexam");
		        modelAndView.addObject("userId", userId);
		        modelAndView.addObject("vacancyId", vacancyId);
		        modelAndView.addObject("questionsList", questionsList);
		        // Set questionsList in session
		        session.setAttribute("vacancyId", vacancyId);
		        session.setAttribute("questionsList", questionsList);
		        return modelAndView;
		    }
		 
//		
		 @GetMapping("/addDocuments")
		    public ModelAndView showAddDocumentPage(Model model) {
		        Certificates certificate = new Certificates();
		        model.addAttribute("certificate", certificate);
				ModelAndView view = new ModelAndView("addDocuments");
		        return view;
		    }

		 
		 @PostMapping("/addingDocuments")
		    public String addingDocuments(@ModelAttribute("certificate") Certificates certificate, HttpSession session, Model model, @RequestParam("documentFile1") MultipartFile documentFile1) {
		        System.out.println("Inside addingDocuments method");
		        // Retrieving the JobSeekers object from the session
		        JobSeekers jobseeker = (JobSeekers) session.getAttribute("jobseekers");
		        //Long userId=jobseeker.getUserId();
		        // Check if job seeker is not null
		        if (jobseeker != null) {
		            int userId = jobseeker.getUserId();
		            // Now you have the job seeker's ID, you can use it as needed
		            System.out.println("Job Seeker ID: " + userId);
		            try {
		                // Create the upload directory if not exists
		                Files.createDirectories(Path.of("./temp_uploads"));

		                // Handle file upload for document
		                byte[] documentFileBytes = documentFile1.getBytes();
		                String documentFileName = "temp_" + System.currentTimeMillis() + "_" + documentFile1.getOriginalFilename();
		                Files.write(Paths.get("./temp_uploads", documentFileName), documentFileBytes);

		                // Set the file name in the document object
		                certificate.setDocumentFile(documentFileName);
		                certificate.setJobseekers(jobseeker);

		                // Prepare the HTTP headers
		                HttpHeaders headers = new HttpHeaders();
		                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		                HttpEntity<Certificates> entity = new HttpEntity<>(certificate, headers);

		                // Make a REST API call to add the document
		                RestTemplate restTemplate = new RestTemplate();
		                ResponseEntity<String> response = restTemplate.exchange("http://localhost:8091/CareerCrafters1/api/v1/addCertificates", HttpMethod.POST,
		                        entity, String.class);
		                String responseMsg = response.getBody();
		                System.out.println("Response object in documents=" + responseMsg);

		                // Code to upload document file
		                HttpHeaders picHeaders = new HttpHeaders();
		                picHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		                MultiValueMap<String, Object> picMap = new LinkedMultiValueMap<>();
		                picMap.add("file", new FileSystemResource("./temp_uploads/" + documentFileName));
		                picMap.add("filename", documentFileName);
		                HttpEntity<MultiValueMap<String, Object>> picEntity = new HttpEntity<>(picMap, picHeaders);
		                // Send a request to upload the document file
		                String uploadPicUrl = "http://localhost:8090/api/files/upload";
		                ResponseEntity<String> picResponse = restTemplate.exchange(uploadPicUrl, HttpMethod.POST, picEntity,
		                        String.class);
		                String picResponseString = picResponse.getBody();

		                System.out.println("responseMsg=" + responseMsg);

		                // Clean up: Delete the temporary document file
		                Files.deleteIfExists(Paths.get("./temp_uploads", documentFileName));

		                // Reset document object for the next upload
		                certificate = new Certificates();
		                model.addAttribute("certificate", certificate);
		                model.addAttribute("responseString", picResponseString);
		                model.addAttribute("job", "*Thank you for submitting");
		                return "redirect:/userhome";
		            } catch (IOException e) {
		                model.addAttribute("responseString", e.getMessage());
		                return "redirect:/addDocuments";
		            }
		        } else {
		            // Handle the case where jobseeker is null or not found in the session
		            // Redirect to login page or display an error message
		            System.out.println("Job seeker not found in the session.");
		            return "redirect:/userhome"; // Example redirect to login page
		        }
		    }
		 
		 @PostMapping("/updateDocument")
		    public ModelAndView updateDocument(@ModelAttribute("certificate") Certificates certificate, 
		        @RequestParam("documentFile1") MultipartFile file, 
		        HttpSession session, Model model) {

		      try {
		        
		        JobSeekers jobSeeker = (JobSeekers)session.getAttribute("jobseeker");
		        
		        if(jobSeeker != null) {
		        
		          // Save document file temporarily
		          byte[] fileBytes = file.getBytes();
		          String fileName1 = "temp_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
		          Files.write(Paths.get("./temp_uploads", fileName1), fileBytes);
		          
		          // Set document file name
		          certificate.setDocumentFile(fileName1);
		          // Send request to update document
		          HttpHeaders headers = new HttpHeaders();
		          headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		          HttpEntity<Certificates> entity = new HttpEntity<>(certificate, headers);

		          RestTemplate restTemplate = new RestTemplate();
		          String url = "http://localhost:8091/CareerCrafters1/api/v1/updateCertificates/" + certificate.getCertificateId();
		          ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

		          // Upload updated file
		          HttpHeaders fileHeaders = new HttpHeaders();
		          fileHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

		          MultiValueMap<String, Object> fileMap = new LinkedMultiValueMap<>();
		          fileMap.add("file", new FileSystemResource("./temp_uploads/" + fileName1));
		          fileMap.add("filename", fileName1);

		          HttpEntity<MultiValueMap<String, Object>> fileEntity = new HttpEntity<>(fileMap, fileHeaders);

		          String uploadUrl = "http://localhost:8091/CareerCrafters1/api/files/upload";
		          ResponseEntity<String> fileResponse = restTemplate.exchange(uploadUrl, HttpMethod.POST, fileEntity, String.class);

		          // Delete temporary file
		          Files.deleteIfExists(Paths.get("./temp_uploads", fileName1));
		          
		          // Reset document object
		          certificate = new Certificates();
		          model.addAttribute("certificate", certificate);

		          ModelAndView view = new ModelAndView("viewDocuments");
		          model.addAttribute("response", response.getBody());
		          return view;
		          
		        } else {
		          return new ModelAndView("redirect:/userhome"); 
		        }

		      } catch (IOException e) {
		        ModelAndView errorView = new ModelAndView("error");
		        model.addAttribute("message", e.getMessage());
		        return errorView;
		      }
		    }

		 @GetMapping("/editDocument/{certificateId}")
		    public ModelAndView editDocument(@PathVariable int certificateId, Model model) {
		    	RestTemplate restTemplate = new RestTemplate();
		      Certificates certificate = restTemplate.getForObject("http://localhost:8091/CareerCrafters1/api/v1/getCertificates/"+certificateId, Certificates.class);
		      model.addAttribute("certificate", certificate);
		      return new ModelAndView("editDocument"); 
		    }

		    @GetMapping("/deleteDocument/{certificateId}")
		    public ModelAndView deleteDocument(@PathVariable int getDocuments, Model model){

		      RestTemplate restTemplate = new RestTemplate();

		      String url = "http://localhost:8090/api/v1/deleteDocument/"+getDocuments;

		      ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);

		      if(response.getStatusCode() == HttpStatus.OK) {
		        // document deleted successfully
		        model.addAttribute("message", "Document deleted successfully");
		        ModelAndView view = new ModelAndView("viewDocuments");
		        return view;

		      } else {
		        // error deleting document
		        model.addAttribute("message", "Error deleting document"); 
		        return new ModelAndView("error");
		      }

		    }

		    @GetMapping("/viewDocuments")
			  public ModelAndView viewDocuments(Model model,HttpSession session)
				{
		        JobSeekers jobseeker = (JobSeekers) session.getAttribute("jobseekers");
		        int userId=jobseeker.getUserId();
		        System.out.println("userId : " + userId);
					RestTemplate restTemplate = new RestTemplate();
					ResponseEntity<Certificates[]> responseEntity = restTemplate  
								.getForEntity("http://localhost:8091/CareerCrafters1/api/v1/getDocuments/"+userId, Certificates[].class);
					Certificates[] responseBody =  responseEntity.getBody(); 
					List< Certificates> docList = Arrays.asList(responseBody);
					System.out.println("docList "+docList.size());  
					model.addAttribute("documentList",docList);
					ModelAndView view = new ModelAndView("viewDocuments");
					return view;
				}
		}



