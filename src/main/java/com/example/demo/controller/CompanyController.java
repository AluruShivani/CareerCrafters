package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Company;
import com.example.demo.entity.JobApply;
import com.example.demo.entity.JobSeekers;
import com.example.demo.entity.Questions;
import com.example.demo.entity.Test;
import com.example.demo.entity.TestResult;
import com.example.demo.entity.Vacancies;

import jakarta.servlet.http.HttpSession;
@Controller
public class CompanyController {
	@GetMapping("/company")
	public ModelAndView comapanyLogin(Model model)
	{
		Company company = new Company();
		model.addAttribute("company", company);
		ModelAndView view = new ModelAndView("company");
		return view;
		
	}
	
	@PostMapping("/ValidateCompanyLogin")
	public ModelAndView validateComapnyLogin(@ModelAttribute("company") Company company, Model model, HttpSession session) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<Company> entity = new HttpEntity<>(company, headers);
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Company> response = restTemplate.exchange("http://localhost:8091/CareerCrafters1/api/v1/companyLogin",
	            HttpMethod.POST, entity, Company.class);

	    Company responseFlag = response.getBody();
	    System.out.println("responseFlag= " + responseFlag);

	    if (responseFlag!=null) {
	        // Put company ID in session
	        session.setAttribute("company", responseFlag);

	        ModelAndView view = new ModelAndView("/companyhome");
	        return view;
	    } else {
	        model.addAttribute("responseText", "Email/Password incorrect...");

	        ModelAndView view = new ModelAndView("Company/companyLogin");
	        return view;
	    }
	}
	
	@GetMapping("/companymenu")
	public ModelAndView companymenu() {
		ModelAndView view = new ModelAndView("companymenu");
		return view;
	}
	
	@GetMapping("/companyhome")
    public String companyHome() {
		ModelAndView view = new ModelAndView("companyhome");
        return "Company/companyhome"; // Assuming "adminhome" is the name of your Thymeleaf template
    }
	
	
	 @GetMapping("/viewCompanies")
	 public ModelAndView viewCompanies(Model model) {
	     RestTemplate restTemplate = new RestTemplate();
	     ResponseEntity<Company[]> responseEntity = restTemplate.getForEntity("http://localhost:8091/CareerCrafters1/api/v1/viewCompanies", Company[].class);
	     Company[] responseBody = responseEntity.getBody();
	     List<Company> companyList = Arrays.asList(responseBody);
	     model.addAttribute("companyList", companyList);
	     ModelAndView view = new ModelAndView("viewCompanies");
	     return view;
	 }
	 
	 

	
	 
	 @GetMapping("/newregistercompany")
	    public ModelAndView newRegisterCompany(Model model) {
	        Company company = new Company();
	        model.addAttribute("company", company);
	        return new ModelAndView("newregistercompany");
	    }
	
	
	 


	 @GetMapping("/companyprofile")
		public ModelAndView getProfile(Model model, HttpSession session) {
			Company company = (Company) session.getAttribute("company");
			int cid = company.getCompanyId();
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Company> responseEntity = restTemplate
					.getForEntity("http://localhost:8091/CareerCrafters1/api/v1/getCompany/" + cid, Company.class);
			Company company1 = responseEntity.getBody();
			System.out.println("company1" + company1.getCompanyName());
			String imageUrl =  company1.getLogo();
			company1.setLogo(imageUrl);
			System.out.println("company1 = " + company1.getCompanyName());
			model.addAttribute("company1", company1);
			ModelAndView view = new ModelAndView("companyprofile");
			return view;

		}
	    
	    
	    @PostMapping("/newregistercompany")
	    public ModelAndView addCompany(@ModelAttribute("company") Company company,
	                                   @RequestParam("logo1") MultipartFile logo1,
	                                   
	                                   Model model) {
	        try {
	            // Create the upload directory if not exists
	            Files.createDirectories(Path.of("./temp_uploads"));

	            // Handle file upload for logo
	            byte[] logoBytes = logo1.getBytes();
	            String logoName = "temp_" + System.currentTimeMillis() + "_" + logo1.getOriginalFilename();
	            Files.write(Paths.get("./temp_uploads", logoName), logoBytes);

	            // Set the logo name in the company object
	            company.setLogo(logoName);

	            // Create the request entity with company object
	            HttpHeaders headers = new HttpHeaders();
	            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	            HttpEntity<Company> entity = new HttpEntity<>(company, headers);

	            // Send a request to add the company
	            RestTemplate restTemplate = new RestTemplate();
	            ResponseEntity<String> response = restTemplate.exchange("http://localhost:8091/CareerCrafters1/api/v1/addCompany",
	                    HttpMethod.POST, entity, String.class);

	            // Send a request to upload the product picture
	            HttpHeaders picHeaders = new HttpHeaders();
	            picHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
	            MultiValueMap<String, Object> picMap = new LinkedMultiValueMap<>();
	            picMap.add("file", new FileSystemResource("./temp_uploads/" + logoName));
	            picMap.add("filename", logoName); // Pass the filename parameter
	            HttpEntity<MultiValueMap<String, Object>> picEntity = new HttpEntity<>(picMap, picHeaders);
	            String uploadPicUrl = "http://localhost:8091/CareerCrafters1/api/files/upload";
	            ResponseEntity<String> picResponse = restTemplate.exchange(uploadPicUrl, HttpMethod.POST, picEntity, String.class);

	            // Process the response
	            if (response.getStatusCode().is2xxSuccessful()) {
	                ModelAndView view = new ModelAndView("company");
	                view.addObject("responseMsg", response.getBody());
	                return view;
	            } else {
	                ModelAndView view = new ModelAndView("newregistercompany");
	                model.addAttribute("responseText", response.getBody());
	                return view;
	            }
	        } catch (IOException e) {
	            // Handle exceptions related to file operations or HTTP requests
	            ModelAndView errorView = new ModelAndView("newregistercompany");
	            model.addAttribute("responseText", e.getMessage());
	            return errorView;
	        }
	    }

	    
	    @GetMapping("/viewVacancy")
		public String viewAllVacancies(Model model, HttpSession session) {
	    	Company company = (Company) session.getAttribute("company");
	    	
		    int companyId = company.getCompanyId();
		    System.out.println("companyId"+companyId);
		    // Fetch vacancies associated with the companyId from the backend
		    RestTemplate restTemplate = new RestTemplate();
		    String url = "http://localhost:8091/CareerCrafters1/api/v1/getVacanciesByCompanyId/" + companyId;
		    Vacancies[] vacanciesArray = restTemplate.getForObject(url, Vacancies[].class);
		    List<Vacancies> vacancies = Arrays.asList(vacanciesArray);
		    // Add vacancies to the model
		    model.addAttribute("vacancies", vacancies);
		    // Return the viewVacancy.html file
		    return "viewVacancy";
		}
	    
	    @GetMapping("/viewTestResults")
	    public String viewTestResults(Model model,HttpSession session) {
	        Vacancies vacancies = (Vacancies) session.getAttribute("vacancies");
	        System.out.println(vacancies);
	        int vacancyId = vacancies.getVacancyId();
	        System.out.println("vacancyId: " + vacancyId);

	        RestTemplate restTemplate = new RestTemplate();
	        String url = "http://localhost:8091/CareerCrafters1/api/v1/testResults/" + vacancyId;
	        
	        ResponseEntity<TestResult[]> responseEntity = restTemplate.getForEntity(url, TestResult[].class);
	        List<TestResult> testResults = Arrays.asList(responseEntity.getBody());

	        model.addAttribute("testResults", testResults);
	        return "viewTestResults";
	    }
	    
	    @GetMapping("/ViewCompanyJobApply")
		public ModelAndView viewJobApplyByCompanyId(Model model, HttpSession session)
		{

			RestTemplate restTemplate = new RestTemplate();
			int companyId = ((Company) session.getAttribute("company")).getCompanyId();
			System.out.println("CompanyId="+companyId);

			ResponseEntity<JobApply[]> responseEntity = restTemplate
					.getForEntity("http://localhost:8091/CareerCrafters1/api/v1/findJobApplyByCompanyId/" + companyId, JobApply[].class);
			
			JobApply[] responseBody = responseEntity.getBody();
			List<JobApply> JobApplyList = Arrays.asList(responseBody);
			System.out.println("Job Apply List in company="+JobApplyList);
			model.addAttribute("JobApplyList", JobApplyList);
			ModelAndView view = new ModelAndView("ViewCompanyJobApply");
			return view;

		}
	    
	 
}
