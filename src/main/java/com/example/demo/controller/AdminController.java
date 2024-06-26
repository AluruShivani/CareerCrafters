package com.example.demo.controller;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Admin;

@Controller
public class AdminController {
	@GetMapping("/admin")
	public ModelAndView adminLogin(Model model)
	{
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		ModelAndView view = new ModelAndView("admin");
		return view;
		
	}
	
	@PostMapping("/ValidateAdminLogin")
	public ModelAndView validateAdminLogin(@ModelAttribute("admin")Admin admin, Model model) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<Admin> entity = new HttpEntity<Admin>(admin, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:8091/CareerCrafters1/api/v1/adminLogin",
				HttpMethod.POST, entity, Boolean.class);
		
		boolean responseFlag = response.getBody();
		System.out.println("responseFlag= "+responseFlag);
		
		if(responseFlag == true) {
			ModelAndView view = new ModelAndView("adminhome");
			return view;
		}
		else {
			  model.addAttribute("responseText","Username/Password incorrect...");

			ModelAndView view = new ModelAndView("/adminLogin");
			return view;
		}
	}
	
	@GetMapping("/adminmenu")
	public ModelAndView adminmenu() {
		ModelAndView view = new ModelAndView("adminmenu");
		return view;
	}
	
	@GetMapping("/adminhome")
	public ModelAndView adminhome() {
		ModelAndView view = new ModelAndView("adminhome");
		return view;
	}
	
	

	
	
}
